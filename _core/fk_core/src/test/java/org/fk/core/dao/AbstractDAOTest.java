package org.fk.core.dao;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.fk.core.exception.MappingException;
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
import org.jooq.DSLContext;
import org.jooq.Record2;
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
public class AbstractDAOTest {
    @InjectCoreTestUtil
    static CoreTestUtil testDbUtil;

    @Inject
    CoreTestDatabase database;

    private DSLContext dsl;

    private Basic1DAO basic1DAO;

    private Basic2DAO basic2DAO;

    private Nested1DAO nested1DAO;

    private static final Map<Integer, UUID> insertedUuids = new HashMap<>();

    @BeforeEach
    void setup() {
        RequestContext request = new RequestContext(1, 1);
        this.dsl = database.dsl(request);

        this.basic1DAO = new Basic1DAO(dsl);
        this.basic2DAO = new Basic2DAO(dsl);
        this.nested1DAO = new Nested1DAO(dsl);
    }

    @Test
    @Order(1)
    void testInsert() throws IOException {
        // ------
        // basic1
        // ------
        final Basic1Record basic1Record1 = createBasic1Record(Optional.of(1));
        final Basic1Record basic1Record2 = createBasic1Record(empty()); // autoinc by db
        final Basic1DTO basic1DTO1 = createBasic1DTO(Optional.of(3));
        final Basic1DTO basic1DTO2 = createBasic1DTO(empty()); // autoinc by db

        assertEquals(1, basic1DAO.insert(basic1Record1));
        assertEquals(1, basic1DAO.insert(basic1Record2));
        assertEquals(1, basic1DAO.insert(basic1DTO1));
        assertEquals(1, basic1DAO.insert(basic1DTO2));

        validateBasic1Equal(basic1Record1, resolveBasic1sFromDb(basic1Record1.getAutoIncId()).getFirst());
        validateBasic1Equal(basic1Record2, resolveBasic1sFromDb(basic1Record2.getAutoIncId()).getFirst());
        validateBasic1Equal(basic1DTO1, resolveBasic1sFromDb(basic1DTO1.getAutoIncId()).getFirst());
        validateBasic1Equal(basic1DTO2, resolveBasic1sFromDb(basic1DTO2.getAutoIncId()).getFirst());

        // ------
        // basic2
        // ------
        final Basic2Record basic2Record1 = createBasic2Record(Optional.of(UUID.randomUUID()));
        final Basic2Record basic2Record2 = createBasic2Record(empty()); // uuid by abstraction
        final Basic2DTO basic2DTO1 = createBasic2DTO(Optional.of(UUID.randomUUID()));
        final Basic2DTO basic2DTO2 = createBasic2DTO(empty()); // uuid by abstraction

        assertEquals(1, basic2DAO.insert(basic2Record1));
        assertEquals(1, basic2DAO.insert(basic2Record2));
        assertEquals(1, basic2DAO.insert(basic2DTO1));
        assertEquals(1, basic2DAO.insert(basic2DTO2));

        validateBasic2Equal(basic2Record1, resolveBasic2sFromDb(basic2Record1.getUuidId()).getFirst());
        validateBasic2Equal(basic2Record2, resolveBasic2sFromDb(basic2Record2.getUuidId()).getFirst());
        validateBasic2Equal(basic2DTO1, resolveBasic2sFromDb(basic2DTO1.getUuidId()).getFirst());
        validateBasic2Equal(basic2DTO2, resolveBasic2sFromDb(basic2DTO2.getUuidId()).getFirst());

        // -------
        // nested1
        // -------
        final Nested1Record nested1Record1 = createNested1Record(
                Optional.of(basic1Record1.getAutoIncId()), Optional.of(basic2Record1.getUuidId()));
        final Nested1Record nested1Record2 = createNested1Record(
                Optional.of(basic1Record2.getAutoIncId()), Optional.of(basic2Record2.getUuidId()));
        final Nested1DTO nested1DTO1 = createNested1DTO(
                Optional.of(basic1DTO1.getAutoIncId()), Optional.of(basic2DTO1.getUuidId()));
        final Nested1DTO nested1DTO2 = createNested1DTO(
                Optional.of(basic1DTO2.getAutoIncId()), Optional.of(basic2DTO2.getUuidId()));

        assertEquals(1, nested1DAO.insert(nested1Record1));
        assertEquals(1, nested1DAO.insert(nested1Record2));
        assertEquals(1, nested1DAO.insert(nested1DTO1));
        assertEquals(1, nested1DAO.insert(nested1DTO2));

        validateNested1Equal(nested1Record1, resolveNested1sFromDb(basic1Record1.getAutoIncId()).getFirst());
        validateNested1Equal(nested1Record2, resolveNested1sFromDb(basic1Record2.getAutoIncId()).getFirst());
        validateNested1Equal(nested1DTO1, resolveNested1sFromDb(basic1DTO1.getAutoIncId()).getFirst());
        validateNested1Equal(nested1DTO2, resolveNested1sFromDb(basic1DTO2.getAutoIncId()).getFirst());

        // ----------------
        // final validation
        // ----------------
        assertBasic1Count(4);
        assertBasic2Count(4);
        assertNested1Count(4);

        insertedUuids.put(1, basic2Record1.getUuidId());
        insertedUuids.put(2, basic2Record2.getUuidId());
        insertedUuids.put(3, basic2DTO1.getUuidId());
        insertedUuids.put(4, basic2DTO2.getUuidId());
    }

    @Test
    @Order(2)
    void testInserts() throws IOException {
        // ------
        // basic1
        // ------
        final Basic1Record basic1Record1 = createBasic1Record(Optional.of(5));
        final Basic1Record basic1Record2 = createBasic1Record(Optional.of(6));
        final Basic1Record basic1Record3 = createBasic1Record(empty()); // autoinc by db
        final List<IBasic1> basic1Recs = List.of(basic1Record1, basic1Record2, basic1Record3);

        final Basic1DTO basic1DTO1 = createBasic1DTO(Optional.of(8));
        final Basic1DTO basic1DTO2 = createBasic1DTO(Optional.of(9));
        final Basic1DTO basic1DTO3 = createBasic1DTO(empty()); // autoinc by db
        final List<IBasic1> basic1Dtos = List.of(basic1DTO1, basic1DTO2, basic1DTO3);

        assertEquals(3, basic1DAO.insert(basic1Recs));
        assertEquals(3, basic1DAO.insert(basic1Dtos));

        validateBasic1Equal(basic1Recs, resolveBasic1sFromDb(basic1Recs.stream().map(IBasic1::getAutoIncId).toList()));
        validateBasic1Equal(basic1Dtos, resolveBasic1sFromDb(basic1Dtos.stream().map(IBasic1::getAutoIncId).toList()));

        // ------
        // basic2
        // ------
        final Basic2Record basic2Record1 = createBasic2Record(Optional.of(UUID.randomUUID()));
        final Basic2Record basic2Record2 = createBasic2Record(Optional.of(UUID.randomUUID()));
        final Basic2Record basic2Record3 = createBasic2Record(empty()); // uuid by abstraction
        final List<IBasic2> basic2Recs = List.of(basic2Record1, basic2Record2, basic2Record3);

        final Basic2DTO basic2DTO1 = createBasic2DTO(Optional.of(UUID.randomUUID()));
        final Basic2DTO basic2DTO2 = createBasic2DTO(Optional.of(UUID.randomUUID()));
        final Basic2DTO basic2DTO3 = createBasic2DTO(empty()); // uuid by abstraction
        final List<IBasic2> basic2Dtos = List.of(basic2DTO1, basic2DTO2, basic2DTO3);

        assertEquals(3, basic2DAO.insert(basic2Recs));
        assertEquals(3, basic2DAO.insert(basic2Dtos));

        validateBasic2Equal(basic2Recs, resolveBasic2sFromDb(basic2Recs.stream().map(IBasic2::getUuidId).toList()));
        validateBasic2Equal(basic2Dtos, resolveBasic2sFromDb(basic2Dtos.stream().map(IBasic2::getUuidId).toList()));

        // -------
        // nested1
        // -------
        final Nested1Record nested1Record1 = createNested1Record(
                Optional.of(basic1Record1.getAutoIncId()), Optional.of(basic2Record1.getUuidId()));
        final Nested1Record nested1Record2 = createNested1Record(
                Optional.of(basic1Record2.getAutoIncId()), Optional.of(basic2Record2.getUuidId()));
        final Nested1Record nested1Record3 = createNested1Record(
                Optional.of(basic1Record3.getAutoIncId()), Optional.of(basic2Record3.getUuidId()));
        final List<INested1> nested1Recs = List.of(nested1Record1, nested1Record2, nested1Record3);

        assertEquals(3, nested1DAO.insert(nested1Recs));

        validateNested1Equal(nested1Recs, resolveNested1sFromDb(nested1Recs.stream().map(INested1::getAutoIncId).toList()));

        // -----
        // other
        // -----

        assertEquals(0, basic1DAO.insert(new ArrayList<>()));
        assertEquals(0, basic2DAO.insert(new ArrayList<>()));
        assertEquals(0, nested1DAO.insert(new ArrayList<>()));

        // ----------------
        // final validation
        // ----------------
        assertBasic1Count(10);
        assertBasic2Count(10);
        assertNested1Count(7);

        insertedUuids.put(5, basic2Record1.getUuidId());
        insertedUuids.put(6, basic2Record2.getUuidId());
        insertedUuids.put(7, basic2Record3.getUuidId());
    }

    @Test
    @Order(3)
    void testUpdate() throws IOException {
        // ------
        // basic1
        // ------
        final Basic1Record basic1Record1 = createBasic1Record(Optional.of(1));
        basic1Record1.setString2("abc");

        final Basic1DTO basic1DTO1 = createBasic1DTO(Optional.of(3));
        basic1DTO1.setString2("abc");

        basic1DAO.update(basic1Record1);
        basic1DAO.update(basic1DTO1);

        validateBasic1Equal(basic1Record1, resolveBasic1sFromDb(basic1Record1.getAutoIncId()).getFirst());
        validateBasic1Equal(basic1DTO1, resolveBasic1sFromDb(basic1DTO1.getAutoIncId()).getFirst());

        // ------
        // basic2
        // ------

        final Basic2Record basic2Record1 = createBasic2Record(Optional.of(insertedUuids.get(1)));
        basic1Record1.setString2("abc");

        final Basic2DTO basic2DTO1 = createBasic2DTO(Optional.of(insertedUuids.get(2)));
        basic1DTO1.setString2("abc");

        basic2DAO.update(basic2Record1);
        basic2DAO.update(basic2DTO1);

        validateBasic2Equal(basic2Record1, resolveBasic2sFromDb(basic2Record1.getUuidId()).getFirst());
        validateBasic2Equal(basic2DTO1, resolveBasic2sFromDb(basic2DTO1.getUuidId()).getFirst());

        // -------
        // nested1
        // -------

        final Nested1Record nested1Record1 = createNested1Record(Optional.of(1), Optional.of(insertedUuids.get(1)));
        nested1Record1.setString2("abc");

        final Nested1DTO nested1DTO1 = createNested1DTO(Optional.of(2), Optional.of(insertedUuids.get(2)));
        nested1DTO1.setString2("abc");

        nested1DAO.update(nested1Record1);
        nested1DAO.update(nested1DTO1);

        validateNested1Equal(nested1Record1, resolveNested1sFromDb(nested1Record1.getAutoIncId()).getFirst());
        validateNested1Equal(nested1DTO1, resolveNested1sFromDb(nested1DTO1.getAutoIncId()).getFirst());

        // ----------------
        // final validation
        // ----------------
        assertBasic1Count(10);
        assertBasic2Count(10);
        assertNested1Count(7);
    }

    @Test
    @Order(4)
    void testUpdates() throws IOException {
        // ------
        // basic1
        // ------
        final Basic1Record basic1Record1 = createBasic1Record(Optional.of(1));
        basic1Record1.setString2("xyz");
        final Basic1Record basic1Record2 = createBasic1Record(Optional.of(2));
        basic1Record1.setString2("xyz");
        final List<IBasic1> basic1Recs = List.of(basic1Record1, basic1Record2);

        final Basic1DTO basic1DTO1 = createBasic1DTO(Optional.of(3));
        basic1DTO1.setString2("xyz");
        final Basic1DTO basic1DTO2 = createBasic1DTO(Optional.of(4));
        basic1DTO2.setString2("xyz");
        final List<IBasic1> basic1Dtos = List.of(basic1DTO1, basic1DTO2);

        basic1DAO.update(basic1Recs);
        basic1DAO.update(basic1Dtos);

        validateBasic1Equal(basic1Recs, resolveBasic1sFromDb(basic1Recs.stream().map(IBasic1::getAutoIncId).toList()));
        validateBasic1Equal(basic1Dtos, resolveBasic1sFromDb(basic1Dtos.stream().map(IBasic1::getAutoIncId).toList()));

        // ------
        // basic2
        // ------

        final Basic2Record basic2Record1 = createBasic2Record(Optional.of(insertedUuids.get(1)));
        basic1Record1.setString2("xyz");
        final Basic2Record basic2Record2 = createBasic2Record(Optional.of(insertedUuids.get(2)));
        basic2Record2.setString2("xyz");
        final List<IBasic2> basic2Recs = List.of(basic2Record1, basic2Record2);

        final Basic2DTO basic2DTO1 = createBasic2DTO(Optional.of(insertedUuids.get(3)));
        basic2DTO1.setString2("xyz");
        final Basic2DTO basic2DTO2 = createBasic2DTO(Optional.of(insertedUuids.get(4)));
        basic2DTO2.setString2("xyz");
        final List<IBasic2> basic2Dtos = List.of(basic2DTO1, basic2DTO2);

        basic2DAO.update(basic2Recs);
        basic2DAO.update(basic2Dtos);

        validateBasic2Equal(basic2Recs, resolveBasic2sFromDb(basic2Recs.stream().map(IBasic2::getUuidId).toList()));
        validateBasic2Equal(basic2Dtos, resolveBasic2sFromDb(basic2Dtos.stream().map(IBasic2::getUuidId).toList()));

        // -------
        // nested1
        // -------

        final Nested1Record nested1Record1 = createNested1Record(Optional.of(1), Optional.of(insertedUuids.get(1)));
        nested1Record1.setString2("xyz");
        final Nested1Record nested1Record2 = createNested1Record(Optional.of(2), Optional.of(insertedUuids.get(2)));
        nested1Record2.setString2("xyz");
        final List<INested1> nested1Recs = List.of(nested1Record1, nested1Record2);

        final Nested1DTO nested1DTO1 = createNested1DTO(Optional.of(3), Optional.of(insertedUuids.get(3)));
        nested1DTO1.setString2("abc");
        final Nested1DTO nested1DTO2 = createNested1DTO(Optional.of(4), Optional.of(insertedUuids.get(4)));
        nested1DTO2.setString2("abc");
        final List<INested1> nested1Dtos = List.of(nested1DTO1, nested1DTO2);

        nested1DAO.update(nested1Recs);
        nested1DAO.update(nested1Dtos);

        validateNested1Equal(nested1Recs, resolveNested1sFromDb(nested1Recs.stream().map(INested1::getAutoIncId).toList()));
        validateNested1Equal(nested1Dtos, resolveNested1sFromDb(nested1Dtos.stream().map(INested1::getAutoIncId).toList()));

        // -----
        // other
        // -----

        basic1DAO.update(new ArrayList<>());
        basic2DAO.update(new ArrayList<>());
        nested1DAO.update(new ArrayList<>());

        // ----------------
        // final validation
        // ----------------
        assertBasic1Count(10);
        assertBasic2Count(10);
        assertNested1Count(7);
    }


    @Test
    @Order(5)
    void testExistsById() throws IOException {

        Record2<Integer, UUID> id1 = nested1DAO.compositeKeyRecord(1, insertedUuids.get(1));
        Record2<Integer, UUID> id2 = nested1DAO.compositeKeyRecord(2, insertedUuids.get(2));
        Record2<Integer, UUID> nonExist1 = nested1DAO.compositeKeyRecord(8000, insertedUuids.get(1));
        Record2<Integer, UUID> nonExist2 = nested1DAO.compositeKeyRecord(8001, insertedUuids.get(2));

        Assertions.assertTrue(basic1DAO.existsById(1));
        Assertions.assertTrue(basic1DAO.existsById(2));
        Assertions.assertTrue(basic2DAO.existsById(insertedUuids.get(1)));
        Assertions.assertTrue(basic2DAO.existsById(insertedUuids.get(2)));
        Assertions.assertTrue(nested1DAO.existsById(id1));
        Assertions.assertTrue(nested1DAO.existsById(id2));

        Assertions.assertFalse(basic1DAO.existsById(8000));
        Assertions.assertFalse(basic1DAO.existsById(8001));
        Assertions.assertFalse(basic2DAO.existsById(UUID.randomUUID()));
        Assertions.assertFalse(basic2DAO.existsById(UUID.randomUUID()));
        Assertions.assertFalse(nested1DAO.existsById(nonExist1));
        Assertions.assertFalse(nested1DAO.existsById(nonExist2));

        // ----------------
        // final validation
        // ----------------
        assertBasic1Count(10);
        assertBasic2Count(10);
        assertNested1Count(7);
    }

    @Test
    @Order(6)
    void testCountAll() throws IOException {

        assertEquals(10, basic1DAO.countAll());
        assertEquals(10, basic2DAO.countAll());
        assertEquals(7, nested1DAO.countAll());

        // ----------------
        // final validation
        // ----------------
        assertBasic1Count(10);
        assertBasic2Count(10);
        assertNested1Count(7);
    }

    @Test
    @Order(7)
    void testFindById() throws IOException {

        Record2<Integer, UUID> id1 = nested1DAO.compositeKeyRecord(1, insertedUuids.get(1));
        Record2<Integer, UUID> id2 = nested1DAO.compositeKeyRecord(2, insertedUuids.get(2));
        Record2<Integer, UUID> nonExist1 = nested1DAO.compositeKeyRecord(8000, insertedUuids.get(1));
        Record2<Integer, UUID> nonExist2 = nested1DAO.compositeKeyRecord(8001, insertedUuids.get(2));

        Assertions.assertNotNull(basic1DAO.fetch(1));
        Assertions.assertNotNull(basic1DAO.fetch(2));
        Assertions.assertNotNull(basic2DAO.fetch(insertedUuids.get(1)));
        Assertions.assertNotNull(basic2DAO.fetch(insertedUuids.get(2)));
        Assertions.assertNotNull(nested1DAO.fetch(id1));
        Assertions.assertNotNull(nested1DAO.fetch(id2));

        Assertions.assertNull(basic1DAO.fetch(8000));
        Assertions.assertNull(basic1DAO.fetch(8001));
        Assertions.assertNull(basic2DAO.fetch(UUID.randomUUID()));
        Assertions.assertNull(basic2DAO.fetch(UUID.randomUUID()));
        Assertions.assertNull(nested1DAO.fetch(nonExist1));
        Assertions.assertNull(nested1DAO.fetch(nonExist2));

        // ----------------
        // final validation
        // ----------------
        assertBasic1Count(10);
        assertBasic2Count(10);
        assertNested1Count(7);
    }


    @Test
    @Order(8)
    void testDelete() throws IOException {
        // -------
        // nested1
        // -------

        final Nested1Record nested1Record1 = createNested1Record(Optional.of(1), Optional.of(insertedUuids.get(1)));
        final Nested1DTO nested1DTO1 = createNested1DTO(Optional.of(2), Optional.of(insertedUuids.get(2)));

        nested1DAO.delete(nested1Record1);
        nested1DAO.delete(nested1DTO1);

        assertEquals(0, resolveNested1sFromDb(nested1Record1.getAutoIncId()).size());
        assertEquals(0, resolveNested1sFromDb(nested1DTO1.getAutoIncId()).size());

        // ------
        // basic1
        // ------
        final Basic1Record basic1Record1 = createBasic1Record(Optional.of(1));
        final Basic1DTO basic1DTO1 = createBasic1DTO(Optional.of(2));

        basic1DAO.delete(basic1Record1);
        basic1DAO.delete(basic1DTO1);

        assertEquals(0, resolveBasic1sFromDb(basic1Record1.getAutoIncId()).size());
        assertEquals(0, resolveBasic1sFromDb(basic1DTO1.getAutoIncId()).size());

        // ------
        // basic2
        // ------

        final Basic2Record basic2Record1 = createBasic2Record(Optional.of(insertedUuids.get(1)));
        final Basic2DTO basic2DTO1 = createBasic2DTO(Optional.of(insertedUuids.get(2)));

        basic2DAO.delete(basic2Record1);
        basic2DAO.delete(basic2DTO1);

        assertEquals(0, resolveBasic2sFromDb(basic2Record1.getUuidId()).size());
        assertEquals(0, resolveBasic2sFromDb(basic2DTO1.getUuidId()).size());

        // ----------------
        // final validation
        // ----------------
        assertBasic1Count(8);
        assertBasic2Count(8);
        assertNested1Count(5);
    }

    @Test
    @Order(9)
    void testDeletes() throws IOException {
        // -------
        // nested1
        // -------

        final Nested1Record nested1Record1 = createNested1Record(Optional.of(3), Optional.of(insertedUuids.get(3)));
        final Nested1Record nested1Record2 = createNested1Record(Optional.of(4), Optional.of(insertedUuids.get(4)));
        final List<INested1> nested1Recs = List.of(nested1Record1, nested1Record2);

        final Nested1DTO nested1DTO1 = createNested1DTO(Optional.of(5), Optional.of(insertedUuids.get(5)));
        final Nested1DTO nested1DTO2 = createNested1DTO(Optional.of(6), Optional.of(insertedUuids.get(6)));
        final List<INested1> nested1Dtos = List.of(nested1DTO1, nested1DTO2);

        nested1DAO.delete(nested1Recs);
        nested1DAO.delete(nested1Dtos);

        assertEquals(0, resolveNested1sFromDb(nested1Recs.stream().map(INested1::getAutoIncId).toList()).size());
        assertEquals(0, resolveNested1sFromDb(nested1Dtos.stream().map(INested1::getAutoIncId).toList()).size());

        // ------
        // basic1
        // ------
        final Basic1Record basic1Record1 = createBasic1Record(Optional.of(3));
        final Basic1Record basic1Record2 = createBasic1Record(Optional.of(4));
        final List<IBasic1> basic1Recs = List.of(basic1Record1, basic1Record2);

        final Basic1DTO basic1DTO1 = createBasic1DTO(Optional.of(5));
        final Basic1DTO basic1DTO2 = createBasic1DTO(Optional.of(6));
        final List<IBasic1> basic1Dtos = List.of(basic1DTO1, basic1DTO2);

        basic1DAO.delete(basic1Recs);
        basic1DAO.delete(basic1Dtos);

        assertEquals(0, resolveBasic1sFromDb(basic1Recs.stream().map(IBasic1::getAutoIncId).toList()).size());
        assertEquals(0, resolveBasic1sFromDb(basic1Dtos.stream().map(IBasic1::getAutoIncId).toList()).size());

        // ------
        // basic2
        // ------

        final Basic2Record basic2Record1 = createBasic2Record(Optional.of(insertedUuids.get(3)));
        final Basic2Record basic2Record2 = createBasic2Record(Optional.of(insertedUuids.get(4)));
        final List<IBasic2> basic2Recs = List.of(basic2Record1, basic2Record2);

        final Basic2DTO basic2DTO1 = createBasic2DTO(Optional.of(insertedUuids.get(5)));
        final Basic2DTO basic2DTO2 = createBasic2DTO(Optional.of(insertedUuids.get(6)));
        final List<IBasic2> basic2Dtos = List.of(basic2DTO1, basic2DTO2);

        basic2DAO.delete(basic2Recs);
        basic2DAO.delete(basic2Dtos);

        assertEquals(0, resolveBasic2sFromDb(basic2Recs.stream().map(IBasic2::getUuidId).toList()).size());
        assertEquals(0, resolveBasic2sFromDb(basic2Dtos.stream().map(IBasic2::getUuidId).toList()).size());

        // -----
        // other
        // -----

        basic1DAO.delete(new ArrayList<>());
        basic2DAO.delete(new ArrayList<>());
        nested1DAO.delete(new ArrayList<>());

        // ----------------
        // final validation
        // ----------------
        assertBasic1Count(4);
        assertBasic2Count(4);
        assertNested1Count(1);
    }

    @Test
    @Order(10)
    void deleteById() throws IOException {

        Record2<Integer, UUID> id1 = nested1DAO.compositeKeyRecord(7, insertedUuids.get(7));
        Record2<Integer, UUID> nonExist1 = nested1DAO.compositeKeyRecord(8000, insertedUuids.get(1));

        nested1DAO.deleteById(id1);
        basic1DAO.deleteById(7);
        basic2DAO.deleteById(insertedUuids.get(7));

        nested1DAO.deleteById(nonExist1);
        basic1DAO.deleteById(8000);
        basic2DAO.deleteById(UUID.randomUUID());

        assertEquals(0, resolveBasic1sFromDb(7).size());
        assertEquals(0, resolveBasic2sFromDb(insertedUuids.get(7)).size());
        assertEquals(0, resolveNested1sFromDb(7).size());

        // ----------------
        // final validation
        // ----------------
        assertBasic1Count(3);
        assertBasic2Count(3);
        assertNested1Count(0);
    }

    @Test
    @Order(11)
    void deleteByIds() throws IOException {
        basic1DAO.deleteById(8, 9, 10);

        assertEquals(0, resolveBasic1sFromDb(8, 9, 10).size());

        // ----------------
        // final validation
        // ----------------
        assertBasic1Count(0);
        assertBasic2Count(3);
        assertNested1Count(0);
    }

    @Test
    @Order(12)
    void testMissingRequest() throws IOException {
        // this should never happen, only if the dsl is called this way (Wrongly)
        DSLContext errorDsl = DSL.using(this.dsl.parsingConnection());

        MappingException exception = assertThrows(MappingException.class, () -> {
            new Basic1DAO(errorDsl);
        });
        assertTrue(exception.getMessage().contains("request is missing"));
    }

    @Test
    @Order(13)
    void testMissingRequestClientId() throws IOException {
        RequestContext request = new RequestContext((Integer) null, 1);
        DSLContext errorDsl = database.dsl(request);

        MappingException exception = assertThrows(MappingException.class, () -> {
            new Basic1DAO(errorDsl);
        });
        assertTrue(exception.getMessage().contains("contains field clientId"));
    }

    // -------------------------------------------------------------------------
    // Helper-functions and classes
    // -------------------------------------------------------------------------

    private Basic1Record createBasic1Record(Optional<Integer> maybeAutoIncId) {
        Basic1Record rec = new Basic1Record();
        maybeAutoIncId.ifPresent(rec::setAutoIncId);
        rec.setClientId(1);
        rec.setString1("test1");
        return rec;
    }
    private Basic1DTO createBasic1DTO(Optional<Integer> maybeAutoIncId) {
        Basic1DTO dto = new Basic1DTO();
        maybeAutoIncId.ifPresent(dto::setAutoIncId);
        dto.setClientId(1);
        dto.setString1("test1");
        return dto;
    }
    private Basic2Record createBasic2Record(Optional<UUID> maybeUuidId) {
        Basic2Record rec = new Basic2Record();
        maybeUuidId.ifPresent(rec::setUuidId);
        rec.setClientId(1);
        rec.setString1("test1");
        return rec;
    }
    private Basic2DTO createBasic2DTO(Optional<UUID> maybeUuidId) {
        Basic2DTO dto = new Basic2DTO();
        maybeUuidId.ifPresent(dto::setUuidId);
        dto.setClientId(1);
        dto.setString1("test1");
        return dto;
    }
    private Nested1Record createNested1Record(Optional<Integer> maybeAutoIncId, Optional<UUID> maybeUuidId) {
        Nested1Record rec = new Nested1Record();
        maybeAutoIncId.ifPresent(rec::setAutoIncId);
        maybeUuidId.ifPresent(rec::setUuidId);
        rec.setString1("test1");
        return rec;
    }
    private Nested1DTO createNested1DTO(Optional<Integer> maybeAutoIncId, Optional<UUID> maybeUuidId) {
        Nested1DTO dto = new Nested1DTO();
        maybeAutoIncId.ifPresent(dto::setAutoIncId);
        maybeUuidId.ifPresent(dto::setUuidId);
        dto.setString1("test1");
        return dto;
    }

    private void validateBasic1Equal(List<IBasic1> expecteds, List<Basic1Record> existings) {
        assertEquals(existings.size(), expecteds.size());
        for (IBasic1 expected : expecteds) {
            Basic1Record existing = existings.stream().filter(x -> x.getAutoIncId().equals(expected.getAutoIncId())).toList().getFirst();
            validateBasic1Equal(expected, existing);
        }
    }
    private void validateBasic1Equal(IBasic1 expected, Basic1Record existing) {
        assertNotNull(expected);
        assertNotNull(existing);
        assertEquals(expected.getAutoIncId(), existing.getAutoIncId());
        assertEquals(expected.getClientId(), existing.getClientId());
        assertEquals(expected.getString1(), existing.getString1());
    }
    private void validateBasic2Equal(List<IBasic2> expecteds, List<Basic2Record> existings) {
        assertEquals(existings.size(), expecteds.size());
        for (IBasic2 expected : expecteds) {
            Basic2Record existing = existings.stream().filter(x -> x.getUuidId().equals(expected.getUuidId())).toList().getFirst();
            validateBasic2Equal(expected, existing);
        }
    }
    private void validateBasic2Equal(IBasic2 expected, Basic2Record existing) {
        assertNotNull(expected);
        assertNotNull(existing);
        assertEquals(expected.getUuidId(), existing.getUuidId());
        assertEquals(expected.getClientId(), existing.getClientId());
        assertEquals(expected.getString1(), existing.getString1());
    }
    private void validateNested1Equal(List<INested1> expecteds, List<Nested1Record> existings) {
        assertEquals(existings.size(), expecteds.size());
        for (INested1 expected : expecteds) {
            Nested1Record existing = existings.stream()
                    .filter(x -> x.getUuidId().equals(expected.getUuidId())
                            && x.getAutoIncId().equals(expected.getAutoIncId())).toList().getFirst();
            validateNested1Equal(expected, existing);
        }
    }
    private void validateNested1Equal(INested1 expected, Nested1Record existing) {
        assertNotNull(expected);
        assertNotNull(existing);
        assertEquals(expected.getAutoIncId(), existing.getAutoIncId());
        assertEquals(expected.getUuidId(), existing.getUuidId());
        assertEquals(expected.getString1(), existing.getString1());
    }

    private List<Basic1Record> resolveBasic1sFromDb(Integer... ids) {
        return resolveBasic1sFromDb(asList(ids));
    }
    private List<Basic1Record> resolveBasic1sFromDb(List<Integer> ids) {
        return dsl.select()
                .from(Basic1.BASIC1)
                .where(Basic1.BASIC1.AUTOINCID.in(ids))
                .fetchInto(Basic1Record.class);
    }
    private List<Basic2Record> resolveBasic2sFromDb(UUID... ids) {
        return resolveBasic2sFromDb(asList(ids));
    }
    private List<Basic2Record> resolveBasic2sFromDb(List<UUID> ids) {
        return dsl.select()
                .from(Basic2.BASIC2)
                .where(Basic2.BASIC2.UUIDID.in(ids))
                .fetchInto(Basic2Record.class);
    }

    private List<Nested1Record> resolveNested1sFromDb(Integer... ids) {
        return resolveNested1sFromDb(asList(ids));
    }
    private List<Nested1Record> resolveNested1sFromDb(List<Integer> ids) {
        return dsl.select()
                .from(Nested1.NESTED1)
                .where(Nested1.NESTED1.AUTOINCID.in(ids))
                .fetchInto(Nested1Record.class);
    }
    private void assertBasic1Count(int expectedCount) {
        int count = dsl.fetchCount(dsl.selectFrom(Basic1.BASIC1));
        assertEquals(count, expectedCount);
    }
    private void assertBasic2Count(int expectedCount) {
        int count = dsl.fetchCount(dsl.selectFrom(Basic2.BASIC2));
        assertEquals(count, expectedCount);
    }
    private void assertNested1Count(int expectedCount) {
        int count = dsl.fetchCount(dsl.selectFrom(Nested1.NESTED1));
        assertEquals(count, expectedCount);
    }

    /**
     * Basic1DTO
     */
    public static class Basic1DTO extends Basic1Dto implements IBasic1 {
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
    }

    /**
     * Basic1DAO
     */
    public static class Basic1DAO extends AbstractDAO<Basic1Record, IBasic1, Integer> {

        public Basic1DAO(DSLContext dsl) {
            super(dsl, Basic1.BASIC1);
        }

        @Override
        public Integer getId(Basic1Record object) {
            return object.getAutoIncId();
        }
    }

    /**
     * Basic2DAO
     */
    public static class Basic2DAO extends AbstractDAO<Basic2Record, IBasic2, UUID> {

        public Basic2DAO(DSLContext dsl) {
            super(dsl, Basic2.BASIC2);
        }

        @Override
        public UUID getId(Basic2Record object) {
            return object.getUuidId();
        }
    }

    /**
     * Nested1DAO
     */
    public static class Nested1DAO extends AbstractDAO<Nested1Record, INested1, Record2<Integer, UUID>> {

        public Nested1DAO(DSLContext dsl) {
            super(dsl, Nested1.NESTED1);
        }

        @Override
        public Record2<Integer, UUID> getId(Nested1Record object) {
            return this.compositeKeyRecord(object.getAutoIncId(), object.getUuidId());
        }
    }
}