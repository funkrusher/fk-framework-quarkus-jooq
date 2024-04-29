package org.fk.core.testcontainers;

import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.shaded.org.awaitility.Awaitility;
import org.testcontainers.utility.DockerImageName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class FkMariaDb implements AutoCloseable {

    private final MariaDBContainer<?> container;

    public FkMariaDb(final String databaseName) {
        // Start the MariaDB test container
        // Speed up the execution with help of tempfs (it still starts up slow, but runs statements very fast)
        // see: https://vladmihalcea.com/how-to-run-integration-tests-at-warp-speed-with-docker-and-tmpfs/
        //noinspection resource
        container = new MariaDBContainer<>(DockerImageName.parse("mariadb:10.7.8"))
                //.withReuse(true) // need to delete database where reuse=true (could boost performance)
                .withDatabaseName(databaseName)
                .withUsername("root")
                .withPassword("")
                .withTmpFs(Map.of("/var/lib/mysql", "rw"));
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

    public MariaDBContainer<?> getContainer() {
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
