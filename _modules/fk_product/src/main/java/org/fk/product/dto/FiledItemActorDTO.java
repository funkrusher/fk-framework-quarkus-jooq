package org.fk.product.dto;

import org.fk.core.dto.AbstractDTO;

public class FiledItemActorDTO extends AbstractDTO {

    private Integer clientId;
    private String name;

    public Integer getClientId() {
        return this.clientId;
    }

    public FiledItemActorDTO setClientId(Integer clientId) {
        this.clientId = clientId;
        this.keeper.touch("clientId");
        return this;
    }

    public String getName() {return this.name;}

    public FiledItemActorDTO setName(String name) {
        this.name = name;
        this.keeper.touch("name");
        return this;
    }
}
