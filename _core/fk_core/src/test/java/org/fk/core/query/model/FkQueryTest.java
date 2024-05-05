package org.fk.core.query.model;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.UriInfo;
import org.fk.core.query.model.FkQuery;
import org.fk.core.test.CoreTestProfile;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * QueryParametersTest
 */
@QuarkusTest
@TestProfile(CoreTestProfile.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FkQueryTest {

    @BeforeEach
    public void setup() {
    }

    @Test
    @Order(1)
    void test1() {
        MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
        map.put("page", List.of("0"));
        map.put("pageSize", List.of("10"));
        map.put("sort", List.of("productId:asc"));
        map.put("filter", List.of("productId:=:1,price:>=:10"));

        // TODO: do not mock classes you do not own! we need to create a wrapper-class for UriInfo.
        UriInfo mockUriInfo = mock(UriInfo.class);
        Mockito.when(mockUriInfo.getQueryParameters()).thenReturn(map);

        FkQuery qp = new FkQuery(mockUriInfo);
        qp.init(); // normally called by quarkus-framework in PostContruct.

        Assertions.assertEquals(0, qp.getPage());
        Assertions.assertEquals(10, qp.getPageSize());
    }
}