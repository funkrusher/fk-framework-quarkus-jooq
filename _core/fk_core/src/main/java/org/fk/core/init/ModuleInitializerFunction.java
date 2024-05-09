package org.fk.core.init;

import org.fk.core.exception.MappingException;
import org.jooq.DSLContext;

/**
 * ModuleInitializerFunction
 * - helps users of @{@link ModuleInitializer} to write their initialization-code in this functions run.
 */
@FunctionalInterface
public interface ModuleInitializerFunction {
    void run(DSLContext dsl) throws MappingException;
}
