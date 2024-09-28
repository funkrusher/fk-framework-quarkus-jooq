package org.fk.product.quartz;

import org.fk.core.task.AbstractTaskDTO;

public class FiledItemTaskDTO extends AbstractTaskDTO {

    private Integer clientId;
    private String name;

    public Integer getClientId() {
        return this.clientId;
    }

    public FiledItemTaskDTO setClientId(Integer clientId) {
        this.clientId = clientId;
        this.keeper.touch("clientId");
        return this;
    }

    public String getName() {return this.name;}

    public FiledItemTaskDTO setName(String name) {
        this.name = name;
        this.keeper.touch("name");
        return this;
    }

    @Override
    public Class getExecutorClass() {
        return FiledItemTask.class;
    }
}
