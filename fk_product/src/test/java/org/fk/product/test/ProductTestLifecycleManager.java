package org.fk.product.test;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.fk.database.DatabaseTestcontainer;
import org.fk.product.init.ProductInit;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.testcontainers.containers.MariaDBContainer;

import java.util.Map;

/**
 * TestDbLifecycleManager
 * <p>
 * - starts mariadb-testcontainer
 * - apply flyway migration
 * - provides mariadb-testcontainer configuration to datasource-configuration of application in application.properties.
 * </p>
 */
public class ProductTestLifecycleManager implements QuarkusTestResourceLifecycleManager {

    DatabaseTestcontainer databaseTestcontainer;

    @Override
    public Map<String, String> start() {
        try {
            this.databaseTestcontainer = new DatabaseTestcontainer();
            MariaDBContainer<?> container = databaseTestcontainer.getFkMariadb().getContainer();

            // populate the db with the basis-data for each product, and set them initialized!
            DSLContext dsl = DSL.using(container.getJdbcUrl(), container.getUsername(), container.getPassword());
            ProductInit productInit = new ProductInit();
            productInit.init(dsl);

            return databaseTestcontainer.getLifecycleManagerStartResult();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() {
        try {
            databaseTestcontainer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void inject(Object testInstance) {
    }

    @Override
    public void inject(TestInjector testInjector) {
        testInjector.injectIntoFields(
                new ProductTestUtil(databaseTestcontainer.getFkMariadb().getContainer()),
                new TestInjector.AnnotatedAndMatchesType(InjectProductTestUtil.class, ProductTestUtil.class));
    }

    public MariaDBContainer<?> getContainer() {
        return databaseTestcontainer.getFkMariadb().getContainer();
    }
}