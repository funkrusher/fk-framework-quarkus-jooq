package org.fk.core.init;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.DSL.name;

/**
 * DataInitPlan
 * <p>
 * - executes a list of @{@link DataInit}s in the given ordering.
 * - used to initialize basic-data and test-data before the application is ready.
 * - used only in local dev-environment or in testing-environments.
 * - persists the executed plan, to detect if we already have executed it (optional)
 */
public class DataInitPlan {
    private boolean withPersistedPlan = false;

    final static Table<Record> DATA_INIT_TABLE = table(
            name("DataInit"));
    final static Field<String> DATA_INIT_ID_FIELD =  field(
            name("DataInit", "dataInitId"), String.class);

    private final List<DataInit> dataInits = new ArrayList<>();

    /**
     * Set if the plan is persisted into a database-table
     * and used for an 'already-executed-detection'.
     *
     * @param withPersistedPlan withPersistedPlan
     * @return this
     */
    public DataInitPlan withPersistedPlan(boolean withPersistedPlan) {
        this.withPersistedPlan = withPersistedPlan;
        return this;
    }

    /**
     * Add given @{@link DataInit} to the init-plan.
     *
     * @param dataInit dataInit
     * @return this
     */
    public DataInitPlan add(DataInit dataInit) {
        this.dataInits.add(dataInit);
        return this;
    }

    /**
     * Execute this init-plan, by executing all registered @{@link DataInit}s
     * in the given ordering.
     *
     * @param dsl dsl
     */
    public void execute(DSLContext dsl) {
        dsl.transaction(tx1 -> {
            // execute all dataInits transactional (with rollback if one crashes)
            for (DataInit dataInit : dataInits) {
                execute(tx1.dsl(), dataInit);
            }
        });
    }

    private void execute(DSLContext dsl, DataInit dataInit) {
        if (withPersistedPlan) {
            Optional<Record> maybeInit = dsl.selectFrom(DATA_INIT_TABLE)
                    .where(DATA_INIT_ID_FIELD.eq(dataInit.getDataInitId()))
                    .forUpdate()
                    .fetchOptional();
            if (maybeInit.isPresent()) {
                return;
            }
        }
        dataInit.execute(dsl);

        if (withPersistedPlan) {
            // finally set init! (no errors)
            dsl.insertInto(DATA_INIT_TABLE, DATA_INIT_ID_FIELD)
                    .values(dataInit.getDataInitId())
                    .execute();
        }
    }
}
