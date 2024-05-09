package org.fk.product.test;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.fk.core.init.DataInitPlan;
import org.fk.database1.Database1Testcontainer;
import org.fk.product.init.ProductBasicDataInit;
import org.fk.product.init.ProductTestDataInit;
import org.fk.root.init.RootBasicDataInit;
import org.fk.root.init.RootTestDataInit;
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

    Database1Testcontainer databaseTestcontainer;

    @Override
    public Map<String, String> start() {
        try {
            this.databaseTestcontainer = new Database1Testcontainer();
            MariaDBContainer<?> container = databaseTestcontainer.getFkMariadb().getContainer();

            // execute the data-init plan for this modules test-suite.
            DSLContext dsl = DSL.using(container.getJdbcUrl(), container.getUsername(), container.getPassword());
            new DataInitPlan()
                    .add(new RootBasicDataInit())
                    .add(new RootTestDataInit())
                    .add(new ProductBasicDataInit())
                    .add(new ProductTestDataInit())
                    .execute(dsl);

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