package org.fk.database;

import org.fk.core.testcontainers.FkMariaDb;

public interface DatabaseFunction {
    void run(FkMariaDb fkMariadb) throws Exception;
}
