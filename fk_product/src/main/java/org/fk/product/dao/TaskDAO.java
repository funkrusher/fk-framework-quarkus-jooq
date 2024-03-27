package org.fk.product.dao;

import org.fk.codegen.testshop.tables.Task;
import org.fk.codegen.testshop.tables.interfaces.ITask;
import org.fk.codegen.testshop.tables.records.TaskRecord;
import org.fk.core.dao.AbstractDAO;
import org.jooq.DSLContext;

/**
 * TaskDAO
 */
public class TaskDAO extends AbstractDAO<TaskRecord, ITask, Long> {

    public TaskDAO(DSLContext dsl) {
        super(dsl, Task.TASK);
    }

    @Override
    public Long getId(TaskRecord object) {
        return object.getTaskId();
    }
}
