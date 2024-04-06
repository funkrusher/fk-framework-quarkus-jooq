package org.fk.core.testcontainers;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.shaded.org.awaitility.Awaitility;
import org.testcontainers.utility.DockerImageName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Map;

public class FkPostgres implements AutoCloseable {

    private PostgreSQLContainer<?> container;

    public FkPostgres() {
        // Start the MariaDB test container
        // Speed up the execution with help of tempfs (it still starts up slow, but runs statements very fast)
        // see: https://vladmihalcea.com/how-to-run-integration-tests-at-warp-speed-with-docker-and-tmpfs/
        container = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16.2"))
                //.withReuse(true) // need to delete database where reuse=true (could boost performance)
                .withDatabaseName("testlibrary")
                .withUsername("tester")
                .withPassword("test123")
                .withTmpFs(Map.of("/var/lib/postgres", "rw"));
        container.start();

        waitUntilContainerStarted();

        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Current working directory is: " + currentDirectory);
    }

    private void waitUntilContainerStarted() {
        Awaitility.await()
                .atMost(Duration.ofSeconds(30))
                .pollInterval(Duration.ofSeconds(1))
                .until(container::isRunning);
    }

    public PostgreSQLContainer<?> getContainer() {
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
    public void close() throws Exception {
        container.stop();
    }
}
