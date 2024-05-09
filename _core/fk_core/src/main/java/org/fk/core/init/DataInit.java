package org.fk.core.init;

import org.jooq.DSLContext;

/**
 * DataInit
 * - implement this Interface to provide DML with Jooq to Insert Data for following use-cases:
 *   - basic-data
 *   - test-data
 */
public interface DataInit {

    /**
     * Return the unique-id of this DataInit
     * @return id of this dataInit
     */
    String getDataInitId();

    /**
     * Execute this DataInit with the given @{@link DSLContext}
     * @param dsl dsl
     */
    void execute(final DSLContext dsl);
}
