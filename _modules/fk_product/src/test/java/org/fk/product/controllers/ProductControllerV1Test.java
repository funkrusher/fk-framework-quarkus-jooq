package org.fk.product.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.security.credential.Credential;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.TestProfile;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import jakarta.inject.Inject;
import org.fk.core.auth.TenantCredential;
import org.fk.core.jackson.ObjectMapperProducer;
import org.fk.product.dto.*;
import org.fk.database1.testshop2.tables.Product;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.fk.product.test.InjectProductTestUtil;
import org.fk.product.test.ProductTestProfile;
import org.fk.product.test.ProductTestLifecycleManager;
import org.fk.product.test.ProductTestUtil;
import org.fk.product.type.ProductTypeId;
import org.jooq.DSLContext;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

import static io.restassured.RestAssured.given;
import static java.time.ZoneOffset.UTC;
import static org.fk.core.auth.FkSecurityIdentity.MASTER_TENANT_ID;
import static org.fk.core.auth.FkRoles.ADMIN;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ProductResourceV1Test
 */
@QuarkusTest
@TestProfile(ProductTestProfile.class)
@QuarkusTestResource(ProductTestLifecycleManager.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductControllerV1Test {

    @InjectProductTestUtil
    static ProductTestUtil testDbUtil;
    @InjectMock
    SecurityIdentity identity;

    private ObjectMapper jsonMapper = ObjectMapperProducer.create();

    @BeforeEach
    void setup() {
        Set<String> roles = new HashSet<>();
        String adminRole = ADMIN;
        roles.add(adminRole);
        Set<Credential> credentials = new HashSet<>();
        TenantCredential tenantCredential = new TenantCredential(MASTER_TENANT_ID);
        credentials.add(tenantCredential);
        Mockito.when(identity.hasRole(adminRole)).thenReturn(true);
        Mockito.when(identity.getRoles()).thenReturn(roles);
        Mockito.when(identity.getCredential(TenantCredential.class)).thenReturn(tenantCredential);
        Mockito.when(identity.getCredentials()).thenReturn(credentials);
    }

    private static Long insertedId = 0L;

    @Test
    @TestSecurity(authorizationEnabled = false)
    @Order(1)
    void testCreate() throws IOException {

        String json = "{\n" +
            "  \"clientId\": 1,\n" +
            "  \"typeId\": \"book\"," +
            "  \"price\": 10.00\n" +
            "}";

        ExtractableResponse<Response> er = given()
            .contentType(ContentType.JSON)
            .body(json)
            .when()
            .post("/api/v1/products")
            .then()
            .statusCode(201)
            .extract();
        ProductDTO responseDTO = jsonMapper.readValue(er.body().asString(), ProductDTO.class);

        // verify rest-result is as expected...
        assertEquals(1, responseDTO.getClientId());

        // verify database-content is as expected...
        DSLContext dslContext = testDbUtil.createDSLContext();
        ProductRecord record = dslContext.select().from(Product.PRODUCT).where(Product.PRODUCT.PRODUCTID.eq(responseDTO.getProductId())).fetchOneInto(ProductRecord.class);
        assertNotNull(record);
        assertEquals(record.getProductId(), responseDTO.getProductId());

        insertedId = record.getProductId();
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    @Order(2)
    void testUpdate() throws IOException {

        String json = "{\n" +
            "  \"clientId\": 1,\n" +
            "  \"productId\": " + insertedId + ",\n" +
            "  \"typeId\": \"clothing\"," +
            "  \"price\": 22.00\n" +
            "}";

        ExtractableResponse<Response> er = given()
            .contentType(ContentType.JSON)
            .body(json)
            .when()
            .put("/api/v1/products/" + insertedId)
            .then()
            .statusCode(200)
            .extract();
        ProductDTO responseDTO = jsonMapper.readValue(er.body().asString(), ProductDTO.class);

        // verify rest-result is as expected...
        assertEquals(1, responseDTO.getClientId());

        // verify database-content is as expected...
        DSLContext dslContext = testDbUtil.createDSLContext();
        ProductRecord record = dslContext.select().from(Product.PRODUCT).where(Product.PRODUCT.PRODUCTID.eq(responseDTO.getProductId())).fetchOneInto(ProductRecord.class);
        assertNotNull(record);
        assertEquals(record.getProductId(), responseDTO.getProductId());
        assertEquals(record.getPrice(), responseDTO.getPrice());
    }


    @Test
    @TestSecurity(authorizationEnabled = false)
    @Order(3)
    void testRead() {
        given()
            .when().get("/api/v1/products/1")
            .then()
            .statusCode(200)
            .body(startsWith("{\"productId\":1,\"clientId\":1,\"price\":10.20"));

        DSLContext dslContext = testDbUtil.createDSLContext();
        ProductRecord record = dslContext.select().from(Product.PRODUCT).where(Product.PRODUCT.PRODUCTID.eq(1L)).fetchOneInto(ProductRecord.class);
        assertNotNull(record);
        assertEquals(1L, record.getProductId());
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    @Order(4)
    void testDelete() throws IOException {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(1L);
        productDTO.setClientId(1);

        String json = jsonMapper.writeValueAsString(productDTO);

        given()
            .contentType(ContentType.JSON)
            .body(json)
            .when().delete("/api/v1/products")
            .then()
            .statusCode(204);

        DSLContext dslContext = testDbUtil.createDSLContext();
        Optional<org.jooq.Record> rec = dslContext.select().from(Product.PRODUCT).where(Product.PRODUCT.PRODUCTID.eq(1L)).fetchOptional();
        assertTrue(rec.isEmpty());
    }


}