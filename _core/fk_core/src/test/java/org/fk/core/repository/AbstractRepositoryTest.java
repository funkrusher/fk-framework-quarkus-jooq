package org.fk.core.repository;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.jooq.FkQueryJooqMapper;
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
import org.jooq.Record;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.*;

import static org.jooq.impl.DSL.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestProfile(CoreTestProfile.class)
@QuarkusTestResource(CoreTestLifecycleManager.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AbstractRepositoryTest {
    @InjectCoreTestUtil
    static CoreTestUtil testDbUtil;

    @Inject
    CoreTestDatabase database;

    private DSLContext dsl;

    private MyRepository myRepository;

    @BeforeEach
    void setup() {
        RequestContext request = new RequestContext(1, 1);
        this.dsl = database.dsl(request);
        this.myRepository = new MyRepository(dsl);

        List<Basic1Record> basic1s = new ArrayList<>();
        List<Nested1Record> nested1s = new ArrayList<>();
        List<Basic2Record> basic2s = new ArrayList<>();
        for (int i = 1; i < 201; i++) {
            UUID insertId = UUID.randomUUID();
            basic1s.add(new Basic1Record()
                    .setClientId(1)
                    .setAutoIncId(i)
                    .setString1("test-" + i));
            basic2s.add(new Basic2Record()
                    .setClientId(1)
                    .setUuidId(insertId));

            if (i > 180) {
                nested1s.add(new Nested1Record()
                        .setAutoIncId(i)
                        .setUuidId(insertId)
                        .setInteger1(i)
                        .setDecimal1(new BigDecimal(String.valueOf(i))));
            } else {
                nested1s.add(new Nested1Record()
                        .setAutoIncId(i)
                        .setUuidId(insertId)
                        .setDecimal1(new BigDecimal(String.valueOf(i))));
            }
        }
        dsl.insertInto(Basic1.BASIC1).set(basic1s).execute();
        dsl.insertInto(Basic2.BASIC2).set(basic2s).execute();
        dsl.insertInto(Nested1.NESTED1).set(nested1s).execute();
    }

    @Test
    @Order(1)
    void testQuery() {
        // TODO we tested the basic functionality here in one big test, split this up and branch out.

        FkFilter filter1 = new FkFilter("string1", FkFilterOperator.IN, List.of("test-40", "test-45", "test-78"));
        FkSorter sorter1 = new FkSorter("autoIncId", FkSorterOperator.ASC);
        FkSorter sorter2 = new FkSorter("autoIncId", FkSorterOperator.DESC);

        FkFilter nestedFilter1 = new FkFilter("Nested1.integer1", FkFilterOperator.IN, List.of("195", "197", "192"));
        FkSorter nestedSorter1 = new FkSorter("Nested1.decimal1", FkSorterOperator.ASC);
        FkSorter nestedSorter2 = new FkSorter("Nested1.decimal1", FkSorterOperator.DESC);

        FkQuery paginateQuery = new FkQuery()
                .setPage(0)
                .setPageSize(10);

        FkQuery paginateFilterQuery = new FkQuery()
                .setPage(0)
                .setPageSize(10)
                .setFilters(List.of(filter1));

        FkQuery paginateFilterSortedQuery1 = new FkQuery()
                .setPage(0)
                .setPageSize(10)
                .setFilters(List.of(filter1))
                .setSorter(sorter1);

        FkQuery paginateFilterSortedQuery2 = new FkQuery()
                .setPage(0)
                .setPageSize(10)
                .setFilters(List.of(filter1))
                .setSorter(sorter2);

        FkQuery paginateFilterNestedQuery1 = new FkQuery()
                .setPage(0)
                .setPageSize(10)
                .setFilters(List.of(nestedFilter1))
                .setSorter(nestedSorter1);

        FkQuery paginateFilterNestedQuery2 = new FkQuery()
                .setPage(0)
                .setPageSize(10)
                .setFilters(List.of(nestedFilter1))
                .setSorter(nestedSorter2);

        List<Basic1DTO> result1 = myRepository.query(paginateQuery);
        assertEquals(10, result1.size());
        assertEquals(200, myRepository.count(paginateQuery.getFilters()));

        List<Basic1DTO> result2 = myRepository.query(paginateFilterQuery);
        assertEquals(3, result2.size());
        assertTrue(result2.stream().anyMatch(x -> x.getAutoIncId().equals(40)));
        assertTrue(result2.stream().anyMatch(x -> x.getAutoIncId().equals(45)));
        assertTrue(result2.stream().anyMatch(x -> x.getAutoIncId().equals(78)));
        assertEquals(3, myRepository.count(paginateFilterQuery.getFilters()));

        List<Basic1DTO> result3 = myRepository.query(paginateFilterSortedQuery1);
        assertEquals(3, result3.size());
        assertEquals(40, (int) result3.get(0).getAutoIncId());
        assertEquals(45, (int) result3.get(1).getAutoIncId());
        assertEquals(78, (int) result3.get(2).getAutoIncId());
        assertEquals(3, myRepository.count(paginateFilterSortedQuery1.getFilters()));

        List<Basic1DTO> result4 = myRepository.query(paginateFilterSortedQuery2);
        assertEquals(3, result4.size());
        assertEquals(78, (int) result4.get(0).getAutoIncId());
        assertEquals(45, (int) result4.get(1).getAutoIncId());
        assertEquals(40, (int) result4.get(2).getAutoIncId());
        assertEquals(3, myRepository.count(paginateFilterSortedQuery2.getFilters()));

        List<Basic1DTO> result5 = myRepository.query(paginateFilterNestedQuery1);
        assertEquals(3, result5.size());
        assertEquals(192, (int) result5.get(0).getAutoIncId());
        assertEquals(195, (int) result5.get(1).getAutoIncId());
        assertEquals(197, (int) result5.get(2).getAutoIncId());

        assertEquals(3, myRepository.count(paginateFilterNestedQuery1.getFilters()));

        List<Basic1DTO> result6 = myRepository.query(paginateFilterNestedQuery2);
        assertEquals(3, result6.size());
        assertEquals(197, (int) result6.get(0).getAutoIncId());
        assertEquals(195, (int) result6.get(1).getAutoIncId());
        assertEquals(192, (int) result6.get(2).getAutoIncId());
        assertEquals(3, myRepository.count(paginateFilterNestedQuery2.getFilters()));


    }

    // -------------------------------------------------------------------------
    // Helper-functions and classes
    // -------------------------------------------------------------------------

    /**
     * Basic1DTO
     */
    public static class Basic1DTO extends Basic1Dto implements IBasic1 {

        private List<Nested1DTO> nested1s;

        public Basic1DTO setNested1s(List<Nested1DTO> nested1s) {
            this.nested1s = nested1s;
            this.keeper.touch("nested1s");
            return this;
        }

        public List<Nested1DTO> getNested1s() {
            return this.nested1s;
        }
    }

    /**
     * Basic2DTO
     */
    public static class Basic2DTO extends Basic2Dto implements IBasic2 {
    }

    /**
     * Nested1DTO
     */
    public static class Nested1DTO extends Nested1Dto implements INested1 {
        private List<Basic2DTO> basic2s;

        public Nested1DTO setBasic2s(List<Basic2DTO> basic2s) {
            this.basic2s = basic2s;
            this.keeper.touch("basic2s");
            return this;
        }

        public List<Basic2DTO> getBasic2s() {
            return this.basic2s;
        }
    }

    /**
     * MyRepository
     */
    public class MyRepository extends AbstractRepository<Basic1DTO, Integer> {
        public MyRepository(DSLContext dsl) {
            super(dsl, Basic1DTO.class, Basic1.BASIC1.AUTOINCID);
        }

        @Override
        public SelectFinalStep<Record> prepareQuery(FkQuery query) throws InvalidDataException {
            final FkQueryJooqMapper queryJooqMapper = new FkQueryJooqMapper(query, Basic1.BASIC1)
                    .addMappableFields(Basic1.BASIC1.fields())
                    .addMappableFields(Basic2.BASIC2.fields())
                    .addMappableFields(Nested1.NESTED1.fields());

            // typical multiset query for 1:n relationships.
            return dsl()
                    .select(
                            asterisk(),
                            multiset(
                                    selectDistinct(asterisk())
                                            .where(Nested1.NESTED1.AUTOINCID.eq(Basic1.BASIC1.AUTOINCID))
                            ).as("nested1s"))
                    .from(Basic1.BASIC1)
                            .leftJoin(Nested1.NESTED1)
                            .on(Nested1.NESTED1.AUTOINCID
                                    .eq(Basic1.BASIC1.AUTOINCID))
                    .where(queryJooqMapper.getFilters())
                    .and(Basic1.BASIC1.CLIENTID.eq(request().getClientId()))
                    .groupBy(Basic1.BASIC1.AUTOINCID)
                    .orderBy(queryJooqMapper.getSorter())
                    .offset(queryJooqMapper.getOffset())
                    .limit(queryJooqMapper.getLimit());
        }
    }
}