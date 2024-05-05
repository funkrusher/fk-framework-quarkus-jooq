package org.fk.core.dao;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.fk.core.request.RequestContext;
import org.fk.core.test.CoreTestLifecycleManager;
import org.fk.core.test.CoreTestProfile;
import org.fk.core.test.CoreTestUtil;
import org.fk.core.test.InjectCoreTestUtil;
import org.fk.core.test.database.CoreTestDatabase;
import org.fk.core.test.database.coretestdatabase.tables.Product;
import org.fk.core.test.database.coretestdatabase.tables.records.ProductRecord;
import org.jooq.DSLContext;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * ProductDAOTest
 */
@QuarkusTest
@TestProfile(CoreTestProfile.class)
@QuarkusTestResource(CoreTestLifecycleManager.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductDAOTest {

    @InjectCoreTestUtil
    static CoreTestUtil testDbUtil;

    @Inject
    CoreTestDatabase database;

    private DSLContext dsl;

    @BeforeEach
    void setup() {
        RequestContext request = new RequestContext(1, 1);
        this.dsl = database.dsl(request);
    }

    private ProductRecord createTestRecord(Optional<Long> maybeProductId) {
        ProductRecord productRecord = new ProductRecord();
        maybeProductId.ifPresent(productRecord::setProductId);
        productRecord.setClientId(1);
        productRecord.setPrice(new BigDecimal("22.00"));
        productRecord.setTypeId("book");
        return productRecord;
    }

    private ProductDTO createTestDTO(Optional<Long> maybeProductId) {
        ProductDTO productDTO = new ProductDTO();
        maybeProductId.ifPresent(productDTO::setProductId);
        productDTO.setClientId(1);
        productDTO.setPrice(new BigDecimal("22.00"));
        productDTO.setTypeId("book");
        return productDTO;
    }

    private void validateRecordEqual(ProductRecord expectedRecord, ProductRecord existingRecord) {
        assertNotNull(expectedRecord);
        assertNotNull(existingRecord);
        assertEquals(expectedRecord.getProductId(), existingRecord.getProductId());
        assertEquals(expectedRecord.getClientId(), existingRecord.getClientId());
        assertEquals(expectedRecord.getTypeId(), existingRecord.getTypeId());
    }

    private void validateDTOEqual(ProductDTO expectedDTO, ProductRecord existingRecord) {
        assertNotNull(expectedDTO);
        assertNotNull(existingRecord);
        assertEquals(expectedDTO.getProductId(), existingRecord.getProductId());
        assertEquals(expectedDTO.getClientId(), existingRecord.getClientId());
        assertEquals(expectedDTO.getTypeId(), existingRecord.getTypeId());
    }

    private List<ProductRecord> resolveRecordsFromDb(Long... ids) {
        return dsl.select()
                .from(Product.PRODUCT)
                .where(Product.PRODUCT.PRODUCTID.in(List.of(ids)))
                .fetchInto(ProductRecord.class);
    }

    private void assertCount(int expectedCount) {
        int count = dsl.fetchCount(dsl.selectFrom(Product.PRODUCT));
        assertEquals(count, expectedCount);
    }


    @Test
    @Order(1)
    void testInsertRecordFixedId() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductRecord productRecord = createTestRecord(Optional.of(60000L));
        int result = productDAO.insert(productRecord);
        assertEquals(1, result);

        ProductRecord existingRecord = resolveRecordsFromDb(productRecord.getProductId()).getFirst();
        validateRecordEqual(productRecord, existingRecord);

        assertCount(1);
    }

    @Test
    @Order(2)
    void testInsertRecordsFixedId() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductRecord productRecord1 = createTestRecord(Optional.of(60001L));
        ProductRecord productRecord2 = createTestRecord(Optional.of(60002L));
        ProductRecord productRecord3 = createTestRecord(Optional.of(60003L));

        int result = productDAO.insert(List.of(productRecord1, productRecord2, productRecord3));
        assertEquals(3, result);

        List<ProductRecord> existingRecords = resolveRecordsFromDb(60001L, 60002L, 60003L);
        assertEquals(3, existingRecords.size());
        validateRecordEqual(productRecord1, existingRecords.get(0));
        validateRecordEqual(productRecord2, existingRecords.get(1));
        validateRecordEqual(productRecord3, existingRecords.get(2));

        assertCount(4);
    }


    @Test
    @Order(3)
    void testInsertRecordAutoInc() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductRecord productRecord = createTestRecord(empty());
        int result = productDAO.insert(productRecord);
        assertEquals(1, result);

        ProductRecord existingRecord = resolveRecordsFromDb(productRecord.getProductId()).getFirst();
        validateRecordEqual(productRecord, existingRecord);

        assertCount(5);
    }

    @Test
    @Order(4)
    void testInsertRecordsAutoInc() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductRecord productRecord1 = createTestRecord(empty());
        ProductRecord productRecord2 = createTestRecord(empty());
        ProductRecord productRecord3 = createTestRecord(empty());

        int result = productDAO.insert(List.of(productRecord1, productRecord2, productRecord3));
        assertEquals(3, result);

        List<ProductRecord> existingRecords = resolveRecordsFromDb(productRecord1.getProductId(), productRecord2.getProductId(), productRecord3.getProductId());
        assertEquals(3, existingRecords.size());
        validateRecordEqual(productRecord1, existingRecords.get(0));
        validateRecordEqual(productRecord2, existingRecords.get(1));
        validateRecordEqual(productRecord3, existingRecords.get(2));

        assertCount(8);
    }

    @Test
    @Order(5)
    void testInsertDTOFixedId() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductDTO productDTO = createTestDTO(Optional.of(60008L));
        int result = productDAO.insert(productDTO);
        assertEquals(1, result);

        ProductRecord existingRecord = resolveRecordsFromDb(productDTO.getProductId()).getFirst();
        validateDTOEqual(productDTO, existingRecord);

        assertCount(9);
    }

    @Test
    @Order(6)
    void testInsertDTOsFixedId() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductDTO productDTO1 = createTestDTO(Optional.of(60009L));
        ProductDTO productDTO2 = createTestDTO(Optional.of(60010L));
        ProductDTO productDTO3 = createTestDTO(Optional.of(60011L));

        int result = productDAO.insert(List.of(productDTO1, productDTO2, productDTO3));
        assertEquals(3, result);

        List<ProductRecord> existingRecords = resolveRecordsFromDb(60009L, 60010L, 60011L);
        assertEquals(3, existingRecords.size());
        validateDTOEqual(productDTO1, existingRecords.get(0));
        validateDTOEqual(productDTO2, existingRecords.get(1));
        validateDTOEqual(productDTO3, existingRecords.get(2));

        assertCount(12);
    }

    @Test
    @Order(7)
    void testInsertDTOAutoInc() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductDTO productDTO = createTestDTO(empty());
        int result = productDAO.insert(productDTO);
        assertEquals(1, result);

        ProductRecord existingRecord = resolveRecordsFromDb(productDTO.getProductId()).getFirst();
        validateDTOEqual(productDTO, existingRecord);

        assertCount(13);
    }

    @Test
    @Order(8)
    void testInsertDTOsAutoInc() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductDTO productDTO1 = createTestDTO(empty());
        ProductDTO productDTO2 = createTestDTO(empty());
        ProductDTO productDTO3 = createTestDTO(empty());

        int result = productDAO.insert(List.of(productDTO1, productDTO2, productDTO3));
        assertEquals(3, result);

        List<ProductRecord> existingRecords = resolveRecordsFromDb(productDTO1.getProductId(), productDTO2.getProductId(), productDTO3.getProductId());
        assertEquals(3, existingRecords.size());
        validateDTOEqual(productDTO1, existingRecords.get(0));
        validateDTOEqual(productDTO2, existingRecords.get(1));
        validateDTOEqual(productDTO3, existingRecords.get(2));

        assertCount(16);
    }

    @Test
    @Order(9)
    void testUpdateRecord() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductRecord productRecord = createTestRecord(Optional.of(60000L));
        productRecord.setPrice(new BigDecimal("33.00"));
        productDAO.update(productRecord);

        ProductRecord existingRecord = resolveRecordsFromDb(productRecord.getProductId()).getFirst();
        validateRecordEqual(productRecord, existingRecord);

        assertCount(16);
    }

    @Test
    @Order(10)
    void testUpdateRecords() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductRecord productRecord1 = createTestRecord(Optional.of(60001L));
        ProductRecord productRecord2 = createTestRecord(Optional.of(60002L));
        ProductRecord productRecord3 = createTestRecord(Optional.of(60003L));

        productRecord1.setPrice(new BigDecimal("77.00"));
        productRecord2.setPrice(new BigDecimal("88.00"));
        productRecord3.setPrice(new BigDecimal("99.00"));

        productDAO.update(List.of(productRecord1, productRecord2, productRecord3));

        List<ProductRecord> existingRecords = resolveRecordsFromDb(60001L, 60002L, 60003L);
        assertEquals(3, existingRecords.size());
        validateRecordEqual(productRecord1, existingRecords.get(0));
        validateRecordEqual(productRecord2, existingRecords.get(1));
        validateRecordEqual(productRecord3, existingRecords.get(2));

        assertCount(16);
    }

    @Test
    @Order(11)
    void testUpdateDTO() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductDTO productDTO = createTestDTO(Optional.of(60000L));
        productDTO.setPrice(new BigDecimal("44.00"));
        productDAO.update(productDTO);

        ProductRecord existingRecord = resolveRecordsFromDb(productDTO.getProductId()).getFirst();
        validateDTOEqual(productDTO, existingRecord);

        assertCount(16);
    }

    @Test
    @Order(12)
    void testUpdateDTOs() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductDTO productDTO1 = createTestDTO(Optional.of(60009L));
        ProductDTO productDTO2 = createTestDTO(Optional.of(60010L));
        ProductDTO productDTO3 = createTestDTO(Optional.of(60011L));

        productDTO1.setPrice(new BigDecimal("77.00"));
        productDTO2.setPrice(new BigDecimal("88.00"));
        productDTO3.setPrice(new BigDecimal("99.00"));

        productDAO.update(List.of(productDTO1, productDTO2, productDTO3));

        List<ProductRecord> existingRecords = resolveRecordsFromDb(60009L, 60010L, 60011L);
        assertEquals(3, existingRecords.size());
        validateDTOEqual(productDTO1, existingRecords.get(0));
        validateDTOEqual(productDTO2, existingRecords.get(1));
        validateDTOEqual(productDTO3, existingRecords.get(2));

        assertCount(16);
    }

    @Test
    @Order(13)
    void testDeleteRecord() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductRecord productRecord = createTestRecord(Optional.of(60000L));
        productDAO.delete(productRecord);

        List<ProductRecord> existingRecords = resolveRecordsFromDb(productRecord.getProductId());
        assertEquals(0, existingRecords.size());

        assertCount(15);
    }

    @Test
    @Order(14)
    void testDeleteRecords() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductRecord productRecord1 = createTestRecord(Optional.of(60001L));
        ProductRecord productRecord2 = createTestRecord(Optional.of(60002L));
        ProductRecord productRecord3 = createTestRecord(Optional.of(60003L));

        productDAO.delete(List.of(productRecord1, productRecord2, productRecord3));

        List<ProductRecord> existingRecords = resolveRecordsFromDb(60001L, 60002L, 60003L);
        assertEquals(0, existingRecords.size());

        assertCount(12);
    }

    @Test
    @Order(15)
    void testDeleteDTO() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductDTO productDTO = createTestDTO(Optional.of(60004L));
        productDAO.delete(productDTO);

        List<ProductRecord> existingRecords = resolveRecordsFromDb(productDTO.getProductId());
        assertEquals(0, existingRecords.size());

        assertCount(11);
    }

    @Test
    @Order(16)
    void testDeleteDTOs() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductDTO productDTO1 = createTestDTO(Optional.of(60005L));
        ProductDTO productDTO2 = createTestDTO(Optional.of(60006L));
        ProductDTO productDTO3 = createTestDTO(Optional.of(60007L));

        productDAO.delete(List.of(productDTO1, productDTO2, productDTO3));

        List<ProductRecord> existingRecords = resolveRecordsFromDb(60005L, 60006L, 60007L);
        assertEquals(0, existingRecords.size());

        assertCount(8);
    }

    @Test
    @Order(17)
    void testExistsById() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        boolean shouldExist = productDAO.existsById(60008L);
        boolean shouldNotExist = productDAO.existsById(90000L);

        Assertions.assertTrue(shouldExist);
        Assertions.assertFalse(shouldNotExist);

        assertCount(8);
    }

    @Test
    @Order(18)
    void testCountAll() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        int count = productDAO.countAll();

        assertEquals(8, count);
        assertCount(8);
    }

    @Test
    @Order(19)
    void testFindById() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductRecord productRecord1 = productDAO.fetch(60008L);
        ProductRecord productRecord2 = productDAO.fetch(90000L);

        assertNotNull(productRecord1);
        Assertions.assertNull(productRecord2);

        assertCount(8);
    }

    @Test
    @Order(20)
    void deleteById() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        productDAO.deleteById(60008L);

        List<ProductRecord> existingRecords = resolveRecordsFromDb(60008L);
        assertEquals(0, existingRecords.size());

        assertCount(7);
    }

    @Test
    @Order(21)
    void deleteByIds() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        productDAO.deleteById(60009L, 60010L);

        List<ProductRecord> existingRecords = resolveRecordsFromDb(60009L, 60010L);
        assertEquals(0, existingRecords.size());

        assertCount(5);
    }


}