package org.fk.framework.test;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.fk.core.test.database.CoreTestDatabaseTestcontainer;
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
public class CoreTestLifecycleManager implements QuarkusTestResourceLifecycleManager {

    CoreTestDatabaseTestcontainer databaseTestcontainer;

    @Override
    public Map<String, String> start() {
        try {
            this.databaseTestcontainer = new CoreTestDatabaseTestcontainer();
            MariaDBContainer<?> container = databaseTestcontainer.getFkMariadb().getContainer();

            // populate the db with the basis-data for each product, and set them initialized!
            DSLContext dsl = DSL.using(container.getJdbcUrl(), container.getUsername(), container.getPassword());
            CoreInit coreInit = new CoreInit();
            coreInit.init(dsl);

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
                new CoreTestUtil(databaseTestcontainer.getFkMariadb().getContainer()),
                new TestInjector.AnnotatedAndMatchesType(InjectCoreTestUtil.class, CoreTestUtil.class));
    }

    public MariaDBContainer<?> getContainer() {
        return databaseTestcontainer.getFkMariadb().getContainer();
    }
}