package org.fk.core.dto;


import jakarta.validation.Valid;
import org.fk.codegen.testshop.tables.dtos.Client;
import org.fk.codegen.testshop.tables.interfaces.IClient;

/**
 * ClientDTO
 */
@Valid
public class ClientDTO extends Client implements IClient {
}
