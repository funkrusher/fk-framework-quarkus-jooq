/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables.interfaces;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public interface ITask extends Serializable {

    /**
     * Setter for <code>testshop.task.taskId</code>.
     */
    public ITask setTaskId(Long value);

    /**
     * Getter for <code>testshop.task.taskId</code>.
     */
    public Long getTaskId();

    /**
     * Setter for <code>testshop.task.createdAt</code>.
     */
    public ITask setCreatedAt(LocalDateTime value);

    /**
     * Getter for <code>testshop.task.createdAt</code>.
     */
    public LocalDateTime getCreatedAt();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface ITask
     */
    public void from(ITask from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface ITask
     */
    public <E extends ITask> E into(E into);
}
