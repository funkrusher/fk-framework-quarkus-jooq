package org.fk.product.dao;

import org.fk.database1.testshop.tables.Task;
import org.fk.database1.testshop.tables.records.TaskRecord;
import org.fk.core.dao.AbstractDAO;
import org.jooq.DSLContext;

/**
 * TaskDAO
 */
public class TaskDAO extends AbstractDAO<TaskRecord, Long> {

    public TaskDAO(DSLContext dsl) {
        super(dsl, Task.TASK);
    }
}
