package org.fk.core.util.query;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import org.fk.core.test.CoreTestProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import java.io.IOException;

/**
 * QueryParametersTest
 */
@QuarkusTest
@TestProfile(CoreTestProfile.class)
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