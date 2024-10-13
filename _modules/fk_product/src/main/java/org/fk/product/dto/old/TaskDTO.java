package org.fk.product.dto.old;


import org.fk.database1.testshop.tables.dtos.TaskDto;
import org.fk.database1.testshop.tables.interfaces.ITask;
import org.fk.database1.testshop.tables.records.TaskRecord;
import org.jooq.Record1;

/**
 * TaskDTO
 */
public class TaskDTO extends TaskDto<TaskDTO> {

    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------

    public TaskDTO() {
    }

    public TaskDTO(ITask value) {
        this.from(value);
    }

    public static TaskDTO create(Record1<TaskRecord> r) {
        return new TaskDTO(r.value1());
    }
}