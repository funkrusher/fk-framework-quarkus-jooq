package org.fk.dtos;


import jakarta.validation.Valid;
import org.fk.generated.testshop.tables.interfaces.IClient;
import org.fk.generated.testshop.tables.dtos.Client;

/**
 * ClientDTO
 */
@Valid
public class ClientDTO extends Client implements IClient {
}
