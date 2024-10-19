package org.fk.task.dao;

import org.fk.database1.testshop.tables.Task;
import org.fk.database1.testshop.tables.records.TaskRecord;
import org.fk.framework.dao.AbstractDAO;
import org.jooq.DSLContext;

/**
 * TaskDAO
 */
public class TaskDAO extends AbstractDAO<TaskRecord, Long> {

    public TaskDAO(DSLContext dsl) {
        super(dsl, Task.TASK);
    }
}
