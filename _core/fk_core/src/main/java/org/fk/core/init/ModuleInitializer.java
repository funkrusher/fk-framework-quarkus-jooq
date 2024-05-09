package org.fk.core.init;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jetbrains.annotations.Blocking;
import org.jooq.*;
import org.jooq.Record;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static org.jooq.impl.DSL.*;

/**
 * ModuleInitializer
 * - initializes all Basicdata of a module. Basicdata is:
 *   - Type-Tables (for example: a table containing type-ids, ...)
 *   - Basic-Settings (Language-Table with langIds, ...)
 *
 * Will only be used in:
 * - IT/Unit-Tests
 * - for first initialization of your local developer-database.
 */
@Singleton
public class ModuleInitializer {
    private ConcurrentHashMap<String, String> moduleDependentOn = new ConcurrentHashMap<>();
    private Map<String, ModuleInitializerFunction> executionPlan = new HashMap();


    @ConfigProperty(name = "org.fk.init", defaultValue = "false")
    Boolean fkInit;
    public boolean isEnabled() {
        return fkInit;
    }

    @Blocking
    public void init(DSLContext dsl, String moduleId, List<String> dependentOn, Name schemaName, ModuleInitializerFunction myFunctor) {

        // queue for execution

        moduleDependentOn.put(moduleId, )



        final Table<Record> moduleInitTable = table(name(schemaName, name("ModuleInit")));
        final Field<String> initializedField =  field(name(schemaName, name("ModuleInit"), name("initialized")), String.class);

        Optional<org.jooq.Record> maybeInit = dsl.selectFrom(moduleInitTable).where(initializedField.eq(moduleId)).forUpdate().fetchOptional();
        if (maybeInit.isEmpty()) {
            dsl.transaction(tx1 -> {
                // call our callback...
                myFunctor.run(tx1.dsl());

                // finally set init! (no errors)
                tx1.dsl().insertInto(moduleInitTable, initializedField)
                        .values(moduleId)
                        .execute();
            });
        }
    }
}
