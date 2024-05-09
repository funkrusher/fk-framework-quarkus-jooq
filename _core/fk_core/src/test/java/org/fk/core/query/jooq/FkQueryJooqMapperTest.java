package org.fk.core.query.jooq;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.fk.core.dao.AbstractDAO;
import org.fk.core.dao.AbstractDAOTest;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.exception.MappingException;
import org.fk.core.query.model.*;
import org.fk.core.request.RequestContext;
import org.fk.core.test.CoreTestLifecycleManager;
import org.fk.core.test.CoreTestProfile;
import org.fk.core.test.CoreTestUtil;
import org.fk.core.test.InjectCoreTestUtil;
import org.fk.core.test.database.CoreTestDatabase;
import org.fk.core.test.database.coretestdatabase.tables.Basic1;
import org.fk.core.test.database.coretestdatabase.tables.Basic2;
import org.fk.core.test.database.coretestdatabase.tables.Nested1;
import org.fk.core.test.database.coretestdatabase.tables.dtos.Basic1Dto;
import org.fk.core.test.database.coretestdatabase.tables.dtos.Basic2Dto;
import org.fk.core.test.database.coretestdatabase.tables.dtos.Nested1Dto;
import org.fk.core.test.database.coretestdatabase.tables.interfaces.IBasic1;
import org.fk.core.test.database.coretestdatabase.tables.interfaces.IBasic2;
import org.fk.core.test.database.coretestdatabase.tables.interfaces.INested1;
import org.fk.core.test.database.coretestdatabase.tables.records.Basic1Record;
import org.fk.core.test.database.coretestdatabase.tables.records.Basic2Record;
import org.fk.core.test.database.coretestdatabase.tables.records.Nested1Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestProfile(CoreTestProfile.class)
@QuarkusTestResource(CoreTestLifecycleManager.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FkQueryJooqMapperTest {

    @BeforeEach
    void setup() {
    }

    @Test
    @Order(1)
    void testGetSorter() throws IOException {
        FkSorter fkSorter1 = new FkSorter("string1", FkSorterOperator.ASC);
        FkSorter fkSorter2 = new FkSorter("string1", FkSorterOperator.DESC);
        FkSorter fkSorter3 = new FkSorter("string2", FkSorterOperator.ASC);
        FkSorter fkSorter4 = new FkSorter("string2", FkSorterOperator.DESC);
        FkSorter fkSorter5 = new FkSorter("non-mappable", FkSorterOperator.ASC);

        // Define mappable fields
        final List<Field<?>> mappableFields = new ArrayList<>();
        mappableFields.addAll(List.of(Basic1.BASIC1.fields()));
        mappableFields.addAll(List.of(Nested1.NESTED1.fields()));

        // Execute mapper
        assertEquals(getSorterSql(new FkQueryJooqMapper(Basic1.BASIC1, mappableFields)
                        .getSorter(new FkQuery()).stream().toList()),
                "select * from xyz");

        assertEquals(getSorterSql(new FkQueryJooqMapper(Basic1.BASIC1, mappableFields)
                        .getSorter(new FkQuery().setSorter(fkSorter1)).stream().toList()),
                "select * from xyz order by `coreTestDatabase`.`Basic1`.`string1` asc");

        assertEquals(getSorterSql(new FkQueryJooqMapper(Basic1.BASIC1, mappableFields)
                        .getSorter(new FkQuery().setSorter(fkSorter2)).stream().toList()),
                "select * from xyz order by `coreTestDatabase`.`Basic1`.`string1` desc");

        assertEquals(getSorterSql(new FkQueryJooqMapper(Basic1.BASIC1, mappableFields)
                        .getSorter(new FkQuery().setSorter(fkSorter3)).stream().toList()),
                "select * from xyz order by `coreTestDatabase`.`Basic1`.`string2` asc");

        assertEquals(getSorterSql(new FkQueryJooqMapper(Basic1.BASIC1, mappableFields)
                        .getSorter(new FkQuery().setSorter(fkSorter4)).stream().toList()),
                "select * from xyz order by `coreTestDatabase`.`Basic1`.`string2` desc");

        InvalidDataException exception1 = assertThrows(InvalidDataException.class, () -> {
            new FkQueryJooqMapper(Basic1.BASIC1, mappableFields)
                    .getSorter(new FkQuery().setSorter(fkSorter5)).stream().toList();
        });
        assertTrue(exception1.getMessage().contains("invalid sorter-field"));

        assertEquals(getSorterSql(new FkQueryJooqMapper(Nested1.NESTED1, mappableFields)
                        .getSorter(new FkQuery().setSorter(fkSorter1)).stream().toList()),
                "select * from xyz order by `coreTestDatabase`.`Nested1`.`string1` asc");

        assertEquals(getSorterSql(new FkQueryJooqMapper(Nested1.NESTED1, mappableFields)
                        .getSorter(new FkQuery().setSorter(fkSorter2)).stream().toList()),
                "select * from xyz order by `coreTestDatabase`.`Nested1`.`string1` desc");

        assertEquals(getSorterSql(new FkQueryJooqMapper(Nested1.NESTED1, mappableFields)
                        .getSorter(new FkQuery().setSorter(fkSorter3)).stream().toList()),
                "select * from xyz order by `coreTestDatabase`.`Nested1`.`string2` asc");

        assertEquals(getSorterSql(new FkQueryJooqMapper(Nested1.NESTED1, mappableFields)
                        .getSorter(new FkQuery().setSorter(fkSorter4)).stream().toList()),
                "select * from xyz order by `coreTestDatabase`.`Nested1`.`string2` desc");

        InvalidDataException exception2 = assertThrows(InvalidDataException.class, () -> {
            new FkQueryJooqMapper(Nested1.NESTED1, mappableFields)
                    .getSorter(new FkQuery().setSorter(fkSorter5)).stream().toList();
        });
        assertTrue(exception2.getMessage().contains("invalid sorter-field"));
    }

    @Test
    @Order(2)
    void testGetFilters() throws IOException {
        FkFilter fkFilter1 = new FkFilter("string1", FkFilterOperator.EQUALS, List.of("test"));
        FkFilter fkFilter2 = new FkFilter("string2", FkFilterOperator.NOT_EQUALS, List.of("test"));
        FkFilter fkFilter3 = new FkFilter("non-mappable", FkFilterOperator.EQUALS, List.of("test"));

        // Define mappable fields
        final List<Field<?>> mappableFields = new ArrayList<>();
        mappableFields.addAll(List.of(Basic1.BASIC1.fields()));
        mappableFields.addAll(List.of(Nested1.NESTED1.fields()));

        // Execute mapper
        assertEquals(getFiltersSql(new FkQueryJooqMapper(Basic1.BASIC1, mappableFields)
                        .getFilters(new FkQuery().setFilters(List.of(fkFilter1))).stream().toList()),
                "select * from xyz where `coreTestDatabase`.`Basic1`.`string1` = ?");

        assertEquals(getFiltersSql(new FkQueryJooqMapper(Basic1.BASIC1, mappableFields)
                        .getFilters(new FkQuery().setFilters(List.of(fkFilter2))).stream().toList()),
                "select * from xyz where `coreTestDatabase`.`Basic1`.`string2` <> ?");

        assertEquals(getFiltersSql(new FkQueryJooqMapper(Basic1.BASIC1, mappableFields)
                        .getFilters(new FkQuery().setFilters(List.of(fkFilter1, fkFilter2))).stream().toList()),
                "select * from xyz where (`coreTestDatabase`.`Basic1`.`string1` = ? and `coreTestDatabase`.`Basic1`.`string2` <> ?)");
    }

    // -------------------------------------------------------------------------
    // Helper-functions and classes
    // -------------------------------------------------------------------------

    String getSorterSql(List<SortField<Object>> sorter) {
        DSLContext dsl = DSL.using(SQLDialect.MARIADB);
        return dsl.select().from("xyz").orderBy(sorter).getSQL();
    }

    String getFiltersSql(Collection<Condition> conditions) {
        DSLContext dsl = DSL.using(SQLDialect.MARIADB);
        return dsl.select().from("xyz").where(DSL.and(conditions)).getSQL();
    }

}