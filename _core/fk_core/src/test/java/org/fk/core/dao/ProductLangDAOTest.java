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
import org.fk.core.test.database.coretestdatabase.tables.ProductLang;
import org.fk.core.test.database.coretestdatabase.tables.records.ProductLangRecord;
import org.fk.core.test.database.coretestdatabase.tables.records.ProductRecord;
import org.jooq.DSLContext;
import org.jooq.Record2;
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
class ProductLangDAOTest {

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

    private List<ProductLangRecord> resolveRecordsFromDb(Long... ids) {
        return dsl.select()
                .from(ProductLang.PRODUCT_LANG)
                .where(ProductLang.PRODUCT_LANG.PRODUCTID.in(List.of(ids)))
                .fetchInto(ProductLangRecord.class);
    }

    private void assertCount(int expectedCount) {
        int count = dsl.fetchCount(dsl.selectFrom(ProductLang.PRODUCT_LANG));
        assertEquals(count, expectedCount);
    }


    @Test
    @Order(1)
    void testInsertRecordFixedId() throws IOException {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(1L);
        productDTO.setClientId(1);
        productDTO.setPrice(new BigDecimal("22.00"));
        productDTO.setTypeId("book");

        ProductDAO productDAO = new ProductDAO(dsl);
        productDAO.insert(productDTO);

        ProductLangDTO lang1 = new ProductLangDTO();
        lang1.setProductId(1L);
        lang1.setLangId(1);
        lang1.setName("test");
        lang1.setDescription("test1");
        ProductLangDTO lang2 = new ProductLangDTO();
        lang2.setProductId(1L);
        lang2.setLangId(2);
        lang2.setName("test2");
        lang2.setDescription("test2");

        ProductLangDAO productLangDAO = new ProductLangDAO(dsl);
        productLangDAO.insert(lang1, lang2);

        // make sure 2 lang records exist
        List<ProductLangRecord> existingRecords = resolveRecordsFromDb(1L);
        assertEquals(2, existingRecords.size());

        assertCount(2);
    }

    @Test
    @Order(2)
    void deleteByIds() throws IOException {
        ProductLangDAO productLangDAO = new ProductLangDAO(dsl);

        Record2<Long, Integer> id1 = productLangDAO.compositeKeyRecord(1L, 1);
        Record2<Long, Integer> id2 = productLangDAO.compositeKeyRecord(1L, 2);

        productLangDAO.deleteById(id1, id2);

        // make sure 0 lang records exist
        List<ProductLangRecord> existingRecords = resolveRecordsFromDb(1L);
        assertEquals(0, existingRecords.size());

        assertCount(0);
    }


}