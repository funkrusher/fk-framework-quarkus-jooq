package org.fk.core.util.query;

import io.quarkus.security.credential.Credential;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.quarkus.test.security.TestSecurity;
import org.fk.core.auth.TenantCredential;
import org.fk.core.test.CoreTestProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.fk.core.auth.FkRoles.ADMIN;
import static org.fk.core.auth.FkSecurityIdentity.MASTER_TENANT_ID;

/**
 * PostControllerV1Test
 */
@QuarkusTest
@TestProfile(CoreTestProfile.class)
public class QueryParametersTest {

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
    public void test1() throws IOException {

        QueryParameters qp = new QueryParameters();
    }
}