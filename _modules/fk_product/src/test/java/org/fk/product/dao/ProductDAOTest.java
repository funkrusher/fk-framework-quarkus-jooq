package org.fk.product.dao;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.fk.core.jooq.RequestContext;
import org.fk.database1.Database1;
import org.fk.database1.testshop.tables.Product;
import org.fk.database1.testshop.tables.records.ProductRecord;
import org.fk.product.dao.ProductDAO;
import org.fk.product.dto.ProductDTO;
import org.fk.product.test.InjectProductTestUtil;
import org.fk.product.test.ProductTestLifecycleManager;
import org.fk.product.test.ProductTestProfile;
import org.fk.product.test.ProductTestUtil;
import org.fk.product.type.ProductTypeId;
import org.jooq.DSLContext;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static java.util.Optional.empty;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * ProductDAOTest
 */
@QuarkusTest
@TestProfile(ProductTestProfile.class)
@QuarkusTestResource(ProductTestLifecycleManager.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductDAOTest {

    @InjectProductTestUtil
    static ProductTestUtil testDbUtil;

    @Inject
    Database1 database1;

    private DSLContext dsl;

    @BeforeEach
    public void setup() {
        RequestContext request = new RequestContext(1, 1);
        this.dsl = database1.dsl(request);
    }

    private ProductRecord createTestRecord(Optional<Long> maybeProductId) {
        ProductRecord productRecord = new ProductRecord();
        maybeProductId.ifPresent(productRecord::setProductId);
        productRecord.setClientId(1);
        productRecord.setPrice(new BigDecimal("22.00"));
        productRecord.setTypeId(ProductTypeId.BOOK.getValue());
        return productRecord;
    }

    private ProductDTO createTestDTO(Optional<Long> maybeProductId) {
        ProductDTO productDTO = new ProductDTO();
        maybeProductId.ifPresent(productDTO::setProductId);
        productDTO.setClientId(1);
        productDTO.setPrice(new BigDecimal("22.00"));
        productDTO.setTypeId(ProductTypeId.BOOK.getValue());
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

    private void assertCount(long expectedCount) {
        int count = dsl.fetchCount(dsl.selectFrom(Product.PRODUCT));
        assertEquals(count, expectedCount);
    }


    @Test
    @Order(1)
    public void testInsertRecordFixedId() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductRecord productRecord = createTestRecord(Optional.of(60000L));
        int result = productDAO.insert(productRecord);
        assertEquals(result, 1);

        ProductRecord existingRecord = resolveRecordsFromDb(productRecord.getProductId()).getFirst();
        validateRecordEqual(productRecord, existingRecord);

        assertCount(15628);
    }

    @Test
    @Order(2)
    public void testInsertRecordsFixedId() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductRecord productRecord1 = createTestRecord(Optional.of(60001L));
        ProductRecord productRecord2 = createTestRecord(Optional.of(60002L));
        ProductRecord productRecord3 = createTestRecord(Optional.of(60003L));

        int result = productDAO.insert(List.of(productRecord1, productRecord2, productRecord3));
        assertEquals(result, 3);

        List<ProductRecord> existingRecords = resolveRecordsFromDb(60001L, 60002L, 60003L);
        assertEquals(existingRecords.size(), 3);
        validateRecordEqual(productRecord1, existingRecords.get(0));
        validateRecordEqual(productRecord2, existingRecords.get(1));
        validateRecordEqual(productRecord3, existingRecords.get(2));

        assertCount(15631);
    }


    @Test
    @Order(3)
    public void testInsertRecordAutoInc() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductRecord productRecord = createTestRecord(empty());
        int result = productDAO.insert(productRecord);
        assertEquals(result, 1);

        ProductRecord existingRecord = resolveRecordsFromDb(productRecord.getProductId()).getFirst();
        validateRecordEqual(productRecord, existingRecord);

        assertCount(15632);
    }

    @Test
    @Order(4)
    public void testInsertRecordsAutoInc() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductRecord productRecord1 = createTestRecord(empty());
        ProductRecord productRecord2 = createTestRecord(empty());
        ProductRecord productRecord3 = createTestRecord(empty());

        int result = productDAO.insert(List.of(productRecord1, productRecord2, productRecord3));
        assertEquals(result, 3);

        List<ProductRecord> existingRecords = resolveRecordsFromDb(productRecord1.getProductId(), productRecord2.getProductId(), productRecord3.getProductId());
        assertEquals(existingRecords.size(), 3);
        validateRecordEqual(productRecord1, existingRecords.get(0));
        validateRecordEqual(productRecord2, existingRecords.get(1));
        validateRecordEqual(productRecord3, existingRecords.get(2));

        assertCount(15635);
    }

    @Test
    @Order(5)
    public void testInsertDTOFixedId() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductDTO productDTO = createTestDTO(Optional.of(60008L));
        int result = productDAO.insert(productDTO);
        assertEquals(result, 1);

        ProductRecord existingRecord = resolveRecordsFromDb(productDTO.getProductId()).getFirst();
        validateDTOEqual(productDTO, existingRecord);

        assertCount(15636);
    }

    @Test
    @Order(6)
    public void testInsertDTOsFixedId() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductDTO productDTO1 = createTestDTO(Optional.of(60009L));
        ProductDTO productDTO2 = createTestDTO(Optional.of(60010L));
        ProductDTO productDTO3 = createTestDTO(Optional.of(60011L));

        int result = productDAO.insert(List.of(productDTO1, productDTO2, productDTO3));
        assertEquals(result, 3);

        List<ProductRecord> existingRecords = resolveRecordsFromDb(60009L, 60010L, 60011L);
        assertEquals(existingRecords.size(), 3);
        validateDTOEqual(productDTO1, existingRecords.get(0));
        validateDTOEqual(productDTO2, existingRecords.get(1));
        validateDTOEqual(productDTO3, existingRecords.get(2));

        assertCount(15639);
    }

    @Test
    @Order(7)
    public void testInsertDTOAutoInc() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductDTO productDTO = createTestDTO(empty());
        int result = productDAO.insert(productDTO);
        assertEquals(result, 1);

        ProductRecord existingRecord = resolveRecordsFromDb(productDTO.getProductId()).getFirst();
        validateDTOEqual(productDTO, existingRecord);

        assertCount(15640);
    }

    @Test
    @Order(8)
    public void testInsertDTOsAutoInc() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductDTO productDTO1 = createTestDTO(empty());
        ProductDTO productDTO2 = createTestDTO(empty());
        ProductDTO productDTO3 = createTestDTO(empty());

        int result = productDAO.insert(List.of(productDTO1, productDTO2, productDTO3));
        assertEquals(result, 3);

        List<ProductRecord> existingRecords = resolveRecordsFromDb(productDTO1.getProductId(), productDTO2.getProductId(), productDTO3.getProductId());
        assertEquals(existingRecords.size(), 3);
        validateDTOEqual(productDTO1, existingRecords.get(0));
        validateDTOEqual(productDTO2, existingRecords.get(1));
        validateDTOEqual(productDTO3, existingRecords.get(2));

        assertCount(15643);
    }

    @Test
    @Order(9)
    public void testUpdateRecord() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductRecord productRecord = createTestRecord(Optional.of(60000L));
        productRecord.setPrice(new BigDecimal("33.00"));
        productDAO.update(productRecord);

        ProductRecord existingRecord = resolveRecordsFromDb(productRecord.getProductId()).getFirst();
        validateRecordEqual(productRecord, existingRecord);

        assertCount(15643);
    }

    @Test
    @Order(10)
    public void testUpdateRecords() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductRecord productRecord1 = createTestRecord(Optional.of(60001L));
        ProductRecord productRecord2 = createTestRecord(Optional.of(60002L));
        ProductRecord productRecord3 = createTestRecord(Optional.of(60003L));

        productRecord1.setPrice(new BigDecimal("77.00"));
        productRecord2.setPrice(new BigDecimal("88.00"));
        productRecord3.setPrice(new BigDecimal("99.00"));

        productDAO.update(List.of(productRecord1, productRecord2, productRecord3));

        List<ProductRecord> existingRecords = resolveRecordsFromDb(60001L, 60002L, 60003L);
        assertEquals(existingRecords.size(), 3);
        validateRecordEqual(productRecord1, existingRecords.get(0));
        validateRecordEqual(productRecord2, existingRecords.get(1));
        validateRecordEqual(productRecord3, existingRecords.get(2));

        assertCount(15643);
    }

    @Test
    @Order(11)
    public void testUpdateDTO() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductDTO productDTO = createTestDTO(Optional.of(60000L));
        productDTO.setPrice(new BigDecimal("44.00"));
        productDAO.update(productDTO);

        ProductRecord existingRecord = resolveRecordsFromDb(productDTO.getProductId()).getFirst();
        validateDTOEqual(productDTO, existingRecord);

        assertCount(15643);
    }

    @Test
    @Order(12)
    public void testUpdateDTOs() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductDTO productDTO1 = createTestDTO(Optional.of(60009L));
        ProductDTO productDTO2 = createTestDTO(Optional.of(60010L));
        ProductDTO productDTO3 = createTestDTO(Optional.of(60011L));

        productDTO1.setPrice(new BigDecimal("77.00"));
        productDTO2.setPrice(new BigDecimal("88.00"));
        productDTO3.setPrice(new BigDecimal("99.00"));

        productDAO.update(List.of(productDTO1, productDTO2, productDTO3));

        List<ProductRecord> existingRecords = resolveRecordsFromDb(60009L, 60010L, 60011L);
        assertEquals(existingRecords.size(), 3);
        validateDTOEqual(productDTO1, existingRecords.get(0));
        validateDTOEqual(productDTO2, existingRecords.get(1));
        validateDTOEqual(productDTO3, existingRecords.get(2));

        assertCount(15643);
    }

    @Test
    @Order(13)
    public void testDeleteRecord() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductRecord productRecord = createTestRecord(Optional.of(60000L));
        productDAO.delete(productRecord);

        List<ProductRecord> existingRecords = resolveRecordsFromDb(productRecord.getProductId());
        assertEquals(existingRecords.size(), 0);

        assertCount(15642);
    }

    @Test
    @Order(14)
    public void testDeleteRecords() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductRecord productRecord1 = createTestRecord(Optional.of(60001L));
        ProductRecord productRecord2 = createTestRecord(Optional.of(60002L));
        ProductRecord productRecord3 = createTestRecord(Optional.of(60003L));

        productDAO.delete(List.of(productRecord1, productRecord2, productRecord3));

        List<ProductRecord> existingRecords = resolveRecordsFromDb(60001L, 60002L, 60003L);
        assertEquals(existingRecords.size(), 0);

        assertCount(15639);
    }

    @Test
    @Order(15)
    public void testDeleteDTO() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductDTO productDTO = createTestDTO(Optional.of(60004L));
        productDAO.delete(productDTO);

        List<ProductRecord> existingRecords = resolveRecordsFromDb(productDTO.getProductId());
        assertEquals(existingRecords.size(), 0);

        assertCount(15638);
    }

    @Test
    @Order(16)
    public void testDeleteDTOs() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductDTO productDTO1 = createTestDTO(Optional.of(60005L));
        ProductDTO productDTO2 = createTestDTO(Optional.of(60006L));
        ProductDTO productDTO3 = createTestDTO(Optional.of(60007L));

        productDAO.delete(List.of(productDTO1, productDTO2, productDTO3));

        List<ProductRecord> existingRecords = resolveRecordsFromDb(60005L, 60006L, 60007L);
        assertEquals(existingRecords.size(), 0);

        assertCount(15635);
    }

    @Test
    @Order(17)
    public void testExistsById() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        boolean shouldExist = productDAO.existsById(60008L);
        boolean shouldNotExist = productDAO.existsById(90000L);

        Assertions.assertTrue(shouldExist);
        Assertions.assertFalse(shouldNotExist);

        assertCount(15635);
    }

    @Test
    @Order(18)
    public void testCountAll() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        long count = productDAO.countAll();

        assertEquals(count, 15635L);
        assertCount(15635);
    }

    @Test
    @Order(19)
    public void testFindById() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        ProductRecord productRecord1 = productDAO.findById(60008L);
        ProductRecord productRecord2 = productDAO.findById(90000L);

        assertNotNull(productRecord1);
        Assertions.assertNull(productRecord2);

        assertCount(15635);
    }

    @Test
    @Order(20)
    public void deleteById() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        productDAO.deleteById(60008L);

        List<ProductRecord> existingRecords = resolveRecordsFromDb(60008L);
        assertEquals(existingRecords.size(), 0);

        assertCount(15634);
    }

    @Test
    @Order(21)
    public void deleteByIds() throws IOException {
        ProductDAO productDAO = new ProductDAO(dsl);

        productDAO.deleteById(60009L, 60010L);

        List<ProductRecord> existingRecords = resolveRecordsFromDb(60009L, 60010L);
        assertEquals(existingRecords.size(), 0);

        assertCount(15632);
    }


}