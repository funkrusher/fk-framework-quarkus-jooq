package org.fk.product.dto;


import jakarta.validation.Valid;
import org.fk.database1.testshop.tables.interfaces.IClient;
import org.fk.database1.testshop.tables.pojos.ClientDto;

/**
 * ClientDTO
 */
public class ClientDTO extends ClientDto implements IClient {

    public ClientDTO() {
        super();
    }

}
