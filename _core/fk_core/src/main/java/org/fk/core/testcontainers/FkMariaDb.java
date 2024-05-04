package org.fk.core.testcontainers;

import org.fk.core.exception.MappingException;
import org.jboss.logging.Logger;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.shaded.org.awaitility.Awaitility;
import org.testcontainers.utility.DockerImageName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Map;

public class FkMariaDb implements AutoCloseable {
    public static final Logger LOGGER = Logger.getLogger(FkMariaDb.class);
    private final MariaDBContainer<?> container;


    public FkMariaDb(final String databaseName) {
        // Start the MariaDB test container
        // Speed up the execution with help of tempfs (it still starts up slow, but runs statements very fast)
        // see: https://vladmihalcea.com/how-to-run-integration-tests-at-warp-speed-with-docker-and-tmpfs/
        MariaDBContainer<?> initContainer = null;
        try {
            initContainer = createContainer(databaseName);
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

    private MariaDBContainer<?> createContainer(final String databaseName) {
        // Suppress "Use try-with-resources" sonarlint issue
        // - it is allowed to manually use start() and stop() with testcontainers
        // - using try-with-resources would close the container too early.
        // see: https://java.testcontainers.org/test_framework_integration/manual_lifecycle_control/
        //noinspection resource
        @SuppressWarnings("java:S2095")
        MariaDBContainer<?> tempContainer = new MariaDBContainer<>(DockerImageName.parse("mariadb:10.7.8"))
                //.withReuse(true) // need to delete database where reuse=true (could boost performance)
                .withDatabaseName(databaseName)
                .withUsername("root")
                .withPassword("")
                .withTmpFs(Map.of("/var/lib/mysql", "rw"));
        return tempContainer;
    }

    private void waitUntilContainerStarted(MariaDBContainer<?> initContainer) {
        Awaitility.await()
                .atMost(Duration.ofSeconds(30))
                .pollInterval(Duration.ofSeconds(1))
                .until(initContainer::isRunning);
    }

    public MariaDBContainer<?> getContainer() {
        // Testcontainers-API is producing Wildcards, which Sonarlint does not like.
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
