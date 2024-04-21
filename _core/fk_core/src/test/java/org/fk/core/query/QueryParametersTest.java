package org.fk.core.query;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import org.fk.core.query.QueryParameters;
import org.fk.core.test.CoreTestProfile;
import org.junit.jupiter.api.*;

import java.io.IOException;

/**
 * QueryParametersTest
 */
@QuarkusTest
@TestProfile(CoreTestProfile.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QueryParametersTest {

    @BeforeEach
    public void setup() {
    }

    @Test
    @Order(1)
    public void test1() throws IOException {
        QueryParameters qp = new QueryParameters();
    }
}