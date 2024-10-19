package org.fk.product.controllers;

import io.quarkus.security.credential.Credential;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.test.InjectMock;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.quarkus.test.security.TestSecurity;
import org.fk.framework.auth.TenantCredential;
import org.fk.database1.testshop.tables.Post;
import org.fk.database1.testshop.tables.records.PostRecord;
import org.fk.product.test.InjectProductTestUtil;
import org.fk.product.test.ProductTestLifecycleManager;
import org.fk.product.test.ProductTestProfile;
import org.fk.product.test.ProductTestUtil;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.fk.framework.auth.FkRoles.ADMIN;
import static org.fk.framework.auth.FkSecurityIdentity.MASTER_TENANT_ID;
import static org.junit.jupiter.api.Assertions.*;

/**
 * PostControllerV1Test
 */
@QuarkusTest
@TestProfile(ProductTestProfile.class)
@QuarkusTestResource(ProductTestLifecycleManager.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PostControllerV1Test {

    @InjectProductTestUtil
    static ProductTestUtil testDbUtil;
    @InjectMock
    SecurityIdentity identity;

    @BeforeEach
    public void setup() {
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

    @Test
    @TestSecurity(authorizationEnabled = false)
    @Order(1)
    void testCreate() throws IOException {
        given()
                .when().get("/api/v1/posts")
                .then()
                .statusCode(201);

        DSLContext dslContext = testDbUtil.createDSLContext();
        Result<PostRecord> records = dslContext.selectFrom(Post.POST).fetch();
        assertEquals(1, records.size());
    }
}