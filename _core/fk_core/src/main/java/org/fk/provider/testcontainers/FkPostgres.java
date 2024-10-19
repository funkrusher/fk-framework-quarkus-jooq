package org.fk.provider.testcontainers;

import org.fk.framework.exception.MappingException;
import org.jboss.logging.Logger;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.shaded.org.awaitility.Awaitility;
import org.testcontainers.utility.DockerImageName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Map;

public class FkPostgres implements AutoCloseable {
    public static final Logger LOGGER = Logger.getLogger(FkPostgres.class);
    private final PostgreSQLContainer<?> container;

    public FkPostgres() {
        // Start the MariaDB test container
        // Speed up the execution with help of tempfs (it still starts up slow, but runs statements very fast)
        // see: https://vladmihalcea.com/how-to-run-integration-tests-at-warp-speed-with-docker-and-tmpfs/
        PostgreSQLContainer<?> initContainer = null;
        try {
            initContainer = createContainer();
            initContainer.start();

            waitUntilContainerStarted(initContainer);

            String currentDirectory = System.getProperty("user.dir");
            LOGGER.debug("Current working directory is: " + currentDirectory);

            this.container = initContainer;

        } catch (Exception e) {
            if (initContainer != null) {
                initContainer.stop();
            }
            throw new MappingException("Failed to initialize MariaDBContainer", e);
        }
    }

    private PostgreSQLContainer<?> createContainer() {
        // Suppress "Use try-with-resources" sonarlint issue
        // - it is allowed to manually use start() and stop() with testcontainers
        // - using try-with-resources would close the container too early.
        // see: https://java.testcontainers.org/test_framework_integration/manual_lifecycle_control/
        //noinspection resource
        @SuppressWarnings("java:S2095")
        PostgreSQLContainer<?> tempContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16.2"))
                //.withReuse(true) // need to delete database where reuse=true (could boost performance)
                .withDatabaseName("testlibrary")
                .withUsername("tester")
                .withPassword("test123")
                .withTmpFs(Map.of("/var/lib/postgres", "rw"));
        return tempContainer;
    }


    private void waitUntilContainerStarted(PostgreSQLContainer<?> initContainer) {
        Awaitility.await()
                .atMost(Duration.ofSeconds(30))
                .pollInterval(Duration.ofSeconds(1))
                .until(initContainer::isRunning);
    }

    @SuppressWarnings("java:S1452")
    public PostgreSQLContainer<?> getContainer() {
        // TODO: Testcontainers-API is producing Wildcards, which Sonarlint does not like.
        // We need to cope with this Wildcard-Warning until Testcontainers V2 gets a better API.
        // see: https://github.com/testcontainers/testcontainers-java/issues/318#issuecomment-290692749
        return container;
    }

    public String getJdbcUrl() {
        return container.getJdbcUrl();
    }

    public String getUsername() {
        return container.getUsername();
    }

    public String getPassword() {
        return container.getPassword();
    }

    public Connection createConnection() throws SQLException {
        // The Connection must be closed after use. Please wrap it in "Try-With-Resources", when you use it.
        return DriverManager.getConnection(getJdbcUrl(), getUsername(), getPassword());
    }

    @Override
    public void close() {
        container.stop();
    }
}
