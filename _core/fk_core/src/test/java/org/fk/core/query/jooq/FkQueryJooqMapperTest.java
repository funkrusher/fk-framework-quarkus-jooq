package org.fk.core.query.jooq;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.model.*;
import org.fk.core.test.CoreTestLifecycleManager;
import org.fk.core.test.CoreTestProfile;
import org.fk.core.test.database.coretestdatabase.tables.Basic1;
import org.fk.core.test.database.coretestdatabase.tables.Nested1;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestProfile(CoreTestProfile.class)
@QuarkusTestResource(CoreTestLifecycleManager.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FkQueryJooqMapperTest {

    private List<Field<?>> mappableFields;

    private static final Table<?> BASIC1 = Basic1.BASIC1;
    private static final Table<?> NESTED1 = Nested1.NESTED1;

    @BeforeEach
    void setup() {
        // Define mappable fields
        this.mappableFields = new ArrayList<>();
        mappableFields.addAll(List.of(Basic1.BASIC1.fields()));
        mappableFields.addAll(List.of(Nested1.NESTED1.fields()));
    }

    @Test
    void testGetSorter() {
        FkSorter fkSorter1 = new FkSorter("string1", FkSorterOperator.ASC);
        FkSorter fkSorter2 = new FkSorter("string1", FkSorterOperator.DESC);
        FkSorter fkSorter3 = new FkSorter("string2", FkSorterOperator.ASC);
        FkSorter fkSorter4 = new FkSorter("string2", FkSorterOperator.DESC);
        FkSorter fkSorter5 = new FkSorter("Nested1.string1", FkSorterOperator.ASC);
        FkSorter fkSorter6 = new FkSorter("Nested1.string1", FkSorterOperator.DESC);

        // single sorter
        assertEquals(getSorterSql(new FkQueryJooqMapper(new FkQuery(), Basic1.BASIC1)
                        .addMappableFields(mappableFields)
                        .getSorter().stream().toList()),
                "select * from xyz");
        validateSorter(BASIC1, fkSorter1,"select * from xyz order by `coreTestDatabase`.`Basic1`.`string1` asc");
        validateSorter(BASIC1, fkSorter2,"select * from xyz order by `coreTestDatabase`.`Basic1`.`string1` desc");
        validateSorter(BASIC1, fkSorter3,"select * from xyz order by `coreTestDatabase`.`Basic1`.`string2` asc");
        validateSorter(BASIC1, fkSorter4,"select * from xyz order by `coreTestDatabase`.`Basic1`.`string2` desc");
        validateSorter(NESTED1, fkSorter1,"select * from xyz order by `coreTestDatabase`.`Nested1`.`string1` asc");
        validateSorter(NESTED1, fkSorter2,"select * from xyz order by `coreTestDatabase`.`Nested1`.`string1` desc");
        validateSorter(NESTED1, fkSorter3,"select * from xyz order by `coreTestDatabase`.`Nested1`.`string2` asc");
        validateSorter(NESTED1, fkSorter4,"select * from xyz order by `coreTestDatabase`.`Nested1`.`string2` desc");
        validateSorter(BASIC1, fkSorter5,"select * from xyz order by `coreTestDatabase`.`Nested1`.`string1` asc");
        validateSorter(BASIC1, fkSorter6,"select * from xyz order by `coreTestDatabase`.`Nested1`.`string1` desc");

        // invalid sorter
        FkSorter invalidSorter1 = new FkSorter("non-mappable", FkSorterOperator.ASC);

        assertInvalidSorter(BASIC1, invalidSorter1);
        assertInvalidSorter(NESTED1, invalidSorter1);
    }

    @Test
    void testNotFoundFilters() {
        FkFilter invalidFilter1 = new FkFilter("non-mappable", FkFilterOperator.EQUALS, List.of("test"));

        assertInvalidFilter(BASIC1, invalidFilter1);
        assertInvalidFilter(NESTED1, invalidFilter1);
    }

    @Test
    void testGetStringFilters() {
        FkFilter fkFilter1 = new FkFilter("string1", FkFilterOperator.EQUALS, List.of("test"));
        FkFilter fkFilter2 = new FkFilter("string1", FkFilterOperator.NOT_EQUALS, List.of("test"));
        FkFilter fkSorter3 = new FkFilter("Nested1.string1", FkFilterOperator.EQUALS, List.of("test"));
        FkFilter fkSorter4 = new FkFilter("Nested1.string1", FkFilterOperator.NOT_EQUALS, List.of("test"));

        // single filters
        validateFilter(BASIC1, fkFilter1, "select * from xyz where `coreTestDatabase`.`Basic1`.`string1` = ?");
        validateFilter(BASIC1, fkFilter2, "select * from xyz where `coreTestDatabase`.`Basic1`.`string1` <> ?");
        validateFilter(BASIC1, fkSorter3, "select * from xyz where `coreTestDatabase`.`Nested1`.`string1` = ?");
        validateFilter(BASIC1, fkSorter4, "select * from xyz where `coreTestDatabase`.`Nested1`.`string1` <> ?");

        // unsupported filters
        FkFilter unsupportedFilter1 = new FkFilter("string1", FkFilterOperator.GREATER_THAN, List.of("test"));
        FkFilter unsupportedFilter2 = new FkFilter("string1", FkFilterOperator.GREATER_THAN_OR_EQUALS, List.of("test"));
        FkFilter unsupportedFilter3 = new FkFilter("string1", FkFilterOperator.LESS_THAN, List.of("test"));
        FkFilter unsupportedFilter4 = new FkFilter("string1", FkFilterOperator.LESS_THAN_OR_EQUALS, List.of("test"));

        assertInvalidFilter(BASIC1, unsupportedFilter1);
        assertInvalidFilter(BASIC1, unsupportedFilter2);
        assertInvalidFilter(BASIC1, unsupportedFilter3);
        assertInvalidFilter(BASIC1, unsupportedFilter4);
    }

    @Test
    void testGetIntegerFilters() {
        FkFilter fkFilter1 = new FkFilter("integer1", FkFilterOperator.EQUALS, List.of("1"));
        FkFilter fkFilter2 = new FkFilter("integer1", FkFilterOperator.NOT_EQUALS, List.of("1"));
        FkFilter fkFilter3 = new FkFilter("integer1", FkFilterOperator.GREATER_THAN, List.of("1"));
        FkFilter fkFilter4 = new FkFilter("integer1", FkFilterOperator.GREATER_THAN_OR_EQUALS, List.of("1"));
        FkFilter fkFilter5 = new FkFilter("integer1", FkFilterOperator.LESS_THAN, List.of("1"));
        FkFilter fkFilter6 = new FkFilter("integer1", FkFilterOperator.LESS_THAN_OR_EQUALS, List.of("1"));

        // single filters
        validateFilter(BASIC1, fkFilter1, "select * from xyz where `coreTestDatabase`.`Basic1`.`integer1` = ?");
        validateFilter(BASIC1, fkFilter2, "select * from xyz where `coreTestDatabase`.`Basic1`.`integer1` <> ?");
        validateFilter(BASIC1, fkFilter3, "select * from xyz where `coreTestDatabase`.`Basic1`.`integer1` > ?");
        validateFilter(BASIC1, fkFilter4, "select * from xyz where `coreTestDatabase`.`Basic1`.`integer1` >= ?");
        validateFilter(BASIC1, fkFilter5, "select * from xyz where `coreTestDatabase`.`Basic1`.`integer1` < ?");
        validateFilter(BASIC1, fkFilter6, "select * from xyz where `coreTestDatabase`.`Basic1`.`integer1` <= ?");

        // multiple filters
        assertEquals(getFiltersSql(new FkQueryJooqMapper(new FkQuery().setFilters(List.of(fkFilter1, fkFilter2)), Basic1.BASIC1)
                        .addMappableFields(mappableFields)
                        .getFilters().stream().toList()),
                "select * from xyz where (`coreTestDatabase`.`Basic1`.`integer1` = ? and `coreTestDatabase`.`Basic1`.`integer1` <> ?)");

        // invalid value filters
        FkFilter invalidValueFilter1 = new FkFilter("integer1", FkFilterOperator.EQUALS, List.of("test"));
        FkFilter invalidValueFilter2 = new FkFilter("integer1", FkFilterOperator.NOT_EQUALS, List.of("test"));
        FkFilter invalidValueFilter3 = new FkFilter("integer1", FkFilterOperator.GREATER_THAN, List.of("test"));
        FkFilter invalidValueFilter4 = new FkFilter("integer1", FkFilterOperator.GREATER_THAN_OR_EQUALS, List.of("test"));
        FkFilter invalidValueFilter5 = new FkFilter("integer1", FkFilterOperator.LESS_THAN, List.of("test"));
        FkFilter invalidValueFilter6 = new FkFilter("integer1", FkFilterOperator.LESS_THAN_OR_EQUALS, List.of("test"));

        assertInvalidFilter(BASIC1, invalidValueFilter1);
        assertInvalidFilter(BASIC1, invalidValueFilter2);
        assertInvalidFilter(BASIC1, invalidValueFilter3);
        assertInvalidFilter(BASIC1, invalidValueFilter4);
        assertInvalidFilter(BASIC1, invalidValueFilter5);
        assertInvalidFilter(BASIC1, invalidValueFilter6);
    }

    @Test
    void testGetLongFilters() {
        FkFilter fkFilter1 = new FkFilter("long1", FkFilterOperator.EQUALS, List.of("1"));
        FkFilter fkFilter2 = new FkFilter("long1", FkFilterOperator.NOT_EQUALS, List.of("1"));
        FkFilter fkFilter3 = new FkFilter("long1", FkFilterOperator.GREATER_THAN, List.of("1"));
        FkFilter fkFilter4 = new FkFilter("long1", FkFilterOperator.GREATER_THAN_OR_EQUALS, List.of("1"));
        FkFilter fkFilter5 = new FkFilter("long1", FkFilterOperator.LESS_THAN, List.of("1"));
        FkFilter fkFilter6 = new FkFilter("long1", FkFilterOperator.LESS_THAN_OR_EQUALS, List.of("1"));

        // single filters
        validateFilter(BASIC1, fkFilter1, "select * from xyz where `coreTestDatabase`.`Basic1`.`long1` = ?");
        validateFilter(BASIC1, fkFilter2, "select * from xyz where `coreTestDatabase`.`Basic1`.`long1` <> ?");
        validateFilter(BASIC1, fkFilter3, "select * from xyz where `coreTestDatabase`.`Basic1`.`long1` > ?");
        validateFilter(BASIC1, fkFilter4, "select * from xyz where `coreTestDatabase`.`Basic1`.`long1` >= ?");
        validateFilter(BASIC1, fkFilter5, "select * from xyz where `coreTestDatabase`.`Basic1`.`long1` < ?");
        validateFilter(BASIC1, fkFilter6, "select * from xyz where `coreTestDatabase`.`Basic1`.`long1` <= ?");

        // multiple filters
        assertEquals(getFiltersSql(new FkQueryJooqMapper(new FkQuery().setFilters(List.of(fkFilter1, fkFilter2)), Basic1.BASIC1)
                        .addMappableFields(mappableFields)
                        .getFilters().stream().toList()),
                "select * from xyz where (`coreTestDatabase`.`Basic1`.`long1` = ? and `coreTestDatabase`.`Basic1`.`long1` <> ?)");

        // invalid value filters
        FkFilter invalidValueFilter1 = new FkFilter("long1", FkFilterOperator.EQUALS, List.of("test"));
        FkFilter invalidValueFilter2 = new FkFilter("long1", FkFilterOperator.NOT_EQUALS, List.of("test"));
        FkFilter invalidValueFilter3 = new FkFilter("long1", FkFilterOperator.GREATER_THAN, List.of("test"));
        FkFilter invalidValueFilter4 = new FkFilter("long1", FkFilterOperator.GREATER_THAN_OR_EQUALS, List.of("test"));
        FkFilter invalidValueFilter5 = new FkFilter("long1", FkFilterOperator.LESS_THAN, List.of("test"));
        FkFilter invalidValueFilter6 = new FkFilter("long1", FkFilterOperator.LESS_THAN_OR_EQUALS, List.of("test"));

        assertInvalidFilter(BASIC1, invalidValueFilter1);
        assertInvalidFilter(BASIC1, invalidValueFilter2);
        assertInvalidFilter(BASIC1, invalidValueFilter3);
        assertInvalidFilter(BASIC1, invalidValueFilter4);
        assertInvalidFilter(BASIC1, invalidValueFilter5);
        assertInvalidFilter(BASIC1, invalidValueFilter6);
    }

    @Test
    void testGetBigDecimalFilters() {
        FkFilter fkFilter1 = new FkFilter("decimal1", FkFilterOperator.EQUALS, List.of("10.00"));
        FkFilter fkFilter2 = new FkFilter("decimal1", FkFilterOperator.NOT_EQUALS, List.of("10.00"));
        FkFilter fkFilter3 = new FkFilter("decimal1", FkFilterOperator.GREATER_THAN, List.of("10.00"));
        FkFilter fkFilter4 = new FkFilter("decimal1", FkFilterOperator.GREATER_THAN_OR_EQUALS, List.of("10.00"));
        FkFilter fkFilter5 = new FkFilter("decimal1", FkFilterOperator.LESS_THAN, List.of("10.00"));
        FkFilter fkFilter6 = new FkFilter("decimal1", FkFilterOperator.LESS_THAN_OR_EQUALS, List.of("10.00"));

        // single filters
        validateFilter(BASIC1, fkFilter1, "select * from xyz where `coreTestDatabase`.`Basic1`.`decimal1` = ?");
        validateFilter(BASIC1, fkFilter2, "select * from xyz where `coreTestDatabase`.`Basic1`.`decimal1` <> ?");
        validateFilter(BASIC1, fkFilter3, "select * from xyz where `coreTestDatabase`.`Basic1`.`decimal1` > ?");
        validateFilter(BASIC1, fkFilter4, "select * from xyz where `coreTestDatabase`.`Basic1`.`decimal1` >= ?");
        validateFilter(BASIC1, fkFilter5, "select * from xyz where `coreTestDatabase`.`Basic1`.`decimal1` < ?");
        validateFilter(BASIC1, fkFilter6, "select * from xyz where `coreTestDatabase`.`Basic1`.`decimal1` <= ?");

        // multiple filters
        assertEquals(getFiltersSql(new FkQueryJooqMapper(new FkQuery().setFilters(List.of(fkFilter1, fkFilter2)), Basic1.BASIC1)
                        .addMappableFields(mappableFields)
                        .getFilters().stream().toList()),
                "select * from xyz where (`coreTestDatabase`.`Basic1`.`decimal1` = ? and `coreTestDatabase`.`Basic1`.`decimal1` <> ?)");

        // invalid value filters
        FkFilter invalidValueFilter1 = new FkFilter("decimal1", FkFilterOperator.EQUALS, List.of("test"));
        FkFilter invalidValueFilter2 = new FkFilter("decimal1", FkFilterOperator.NOT_EQUALS, List.of("test"));
        FkFilter invalidValueFilter3 = new FkFilter("decimal1", FkFilterOperator.GREATER_THAN, List.of("test"));
        FkFilter invalidValueFilter4 = new FkFilter("decimal1", FkFilterOperator.GREATER_THAN_OR_EQUALS, List.of("test"));
        FkFilter invalidValueFilter5 = new FkFilter("decimal1", FkFilterOperator.LESS_THAN, List.of("test"));
        FkFilter invalidValueFilter6 = new FkFilter("decimal1", FkFilterOperator.LESS_THAN_OR_EQUALS, List.of("test"));

        assertInvalidFilter(BASIC1, invalidValueFilter1);
        assertInvalidFilter(BASIC1, invalidValueFilter2);
        assertInvalidFilter(BASIC1, invalidValueFilter3);
        assertInvalidFilter(BASIC1, invalidValueFilter4);
        assertInvalidFilter(BASIC1, invalidValueFilter5);
        assertInvalidFilter(BASIC1, invalidValueFilter6);
    }

    @Test
    void testGetLocalDateTimeFilters() {
        FkFilter fkFilter1 = new FkFilter("dateTime1", FkFilterOperator.EQUALS, List.of("1715284124629"));
        FkFilter fkFilter2 = new FkFilter("dateTime1", FkFilterOperator.NOT_EQUALS, List.of("1715284124629"));
        FkFilter fkFilter3 = new FkFilter("dateTime1", FkFilterOperator.GREATER_THAN, List.of("1715284124629"));
        FkFilter fkFilter4 = new FkFilter("dateTime1", FkFilterOperator.GREATER_THAN_OR_EQUALS, List.of("1715284124629"));
        FkFilter fkFilter5 = new FkFilter("dateTime1", FkFilterOperator.LESS_THAN, List.of("1715284124629"));
        FkFilter fkFilter6 = new FkFilter("dateTime1", FkFilterOperator.LESS_THAN_OR_EQUALS, List.of("1715284124629"));

        // single filters
        validateFilter(BASIC1, fkFilter1, "select * from xyz where `coreTestDatabase`.`Basic1`.`dateTime1` = ?");
        validateFilter(BASIC1, fkFilter2, "select * from xyz where `coreTestDatabase`.`Basic1`.`dateTime1` <> ?");
        validateFilter(BASIC1, fkFilter3, "select * from xyz where `coreTestDatabase`.`Basic1`.`dateTime1` > ?");
        validateFilter(BASIC1, fkFilter4, "select * from xyz where `coreTestDatabase`.`Basic1`.`dateTime1` >= ?");
        validateFilter(BASIC1, fkFilter5, "select * from xyz where `coreTestDatabase`.`Basic1`.`dateTime1` < ?");
        validateFilter(BASIC1, fkFilter6, "select * from xyz where `coreTestDatabase`.`Basic1`.`dateTime1` <= ?");

        // multiple filters
        assertEquals(getFiltersSql(new FkQueryJooqMapper(new FkQuery().setFilters(List.of(fkFilter1, fkFilter2)), Basic1.BASIC1)
                        .addMappableFields(mappableFields)
                        .getFilters().stream().toList()),
                "select * from xyz where (`coreTestDatabase`.`Basic1`.`dateTime1` = ? and `coreTestDatabase`.`Basic1`.`dateTime1` <> ?)");

        // invalid value filters
        FkFilter invalidValueFilter1 = new FkFilter("dateTime1", FkFilterOperator.EQUALS, List.of("test"));
        FkFilter invalidValueFilter2 = new FkFilter("dateTime1", FkFilterOperator.NOT_EQUALS, List.of("test"));
        FkFilter invalidValueFilter3 = new FkFilter("dateTime1", FkFilterOperator.GREATER_THAN, List.of("test"));
        FkFilter invalidValueFilter4 = new FkFilter("dateTime1", FkFilterOperator.GREATER_THAN_OR_EQUALS, List.of("test"));
        FkFilter invalidValueFilter5 = new FkFilter("dateTime1", FkFilterOperator.LESS_THAN, List.of("test"));
        FkFilter invalidValueFilter6 = new FkFilter("dateTime1", FkFilterOperator.LESS_THAN_OR_EQUALS, List.of("test"));

        assertInvalidFilter(BASIC1, invalidValueFilter1);
        assertInvalidFilter(BASIC1, invalidValueFilter2);
        assertInvalidFilter(BASIC1, invalidValueFilter3);
        assertInvalidFilter(BASIC1, invalidValueFilter4);
        assertInvalidFilter(BASIC1, invalidValueFilter5);
        assertInvalidFilter(BASIC1, invalidValueFilter6);
    }


    // -------------------------------------------------------------------------
    // Helper-functions and classes
    // -------------------------------------------------------------------------

    void validateSorter(Table<?> table, FkSorter sorter, String sql) {
        assertEquals(getSorterSql(new FkQueryJooqMapper(new FkQuery().setSorter(sorter), table)
                        .addMappableFields(mappableFields)
                        .getSorter().stream().toList()),
                sql);
    }
    void assertInvalidSorter(Table<?> table, FkSorter invalidSorter) {
        assertThrows(InvalidDataException.class, () -> {
            new FkQueryJooqMapper(new FkQuery().setSorter(invalidSorter), table)
                    .addMappableFields(mappableFields)
                    .getSorter().stream().toList();
        });
    }
    void validateFilter(Table<?> table, FkFilter filter, String sql) {
        assertEquals(getFiltersSql(new FkQueryJooqMapper(new FkQuery().setFilters(List.of(filter)), table)
                        .addMappableFields(mappableFields)
                        .getFilters().stream().toList()),
                sql);
    }
    void assertInvalidFilter(Table<?> table, FkFilter invalidValueFilter) {
        assertThrows(InvalidDataException.class, () -> {
            new FkQueryJooqMapper(new FkQuery().setFilters(List.of(invalidValueFilter)), table)
                    .addMappableFields(mappableFields)
                    .getFilters().stream().toList();
        });
    }

    String getSorterSql(List<SortField<Object>> sorter) {
        DSLContext dsl = DSL.using(SQLDialect.MARIADB);
        return dsl.select().from("xyz").orderBy(sorter).getSQL();
    }

    String getFiltersSql(Collection<Condition> conditions) {
        DSLContext dsl = DSL.using(SQLDialect.MARIADB);
        return dsl.select().from("xyz").where(DSL.and(conditions)).getSQL();
    }

}