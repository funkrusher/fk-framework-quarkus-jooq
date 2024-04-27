/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables.pojos;


import java.time.LocalDateTime;

import org.fk.database1.testshop.tables.interfaces.ITask;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Task implements ITask {

    private static final long serialVersionUID = 1L;

    private Long taskId;
    private LocalDateTime createdAt;

    public Task() {}

    public Task(ITask value) {
        this.taskId = value.getTaskId();
        this.createdAt = value.getCreatedAt();
    }

    public Task(
        Long taskId,
        LocalDateTime createdAt
    ) {
        this.taskId = taskId;
        this.createdAt = createdAt;
    }

    /**
     * Getter for <code>testshop.task.taskId</code>.
     */
    @Override
    public Long getTaskId() {
        return this.taskId;
    }

    /**
     * Setter for <code>testshop.task.taskId</code>.
     */
    @Override
    public Task setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }

    /**
     * Getter for <code>testshop.task.createdAt</code>.
     */
    @Override
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Setter for <code>testshop.task.createdAt</code>.
     */
    @Override
    public Task setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Task (");

        sb.append(taskId);
        sb.append(", ").append(createdAt);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(ITask from) {
        setTaskId(from.getTaskId());
        setCreatedAt(from.getCreatedAt());
    }

    @Override
    public <E extends ITask> E into(E into) {
        into.from(this);
        return into;
    }
}