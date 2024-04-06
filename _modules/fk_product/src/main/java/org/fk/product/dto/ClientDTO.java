package org.fk.product.dto;


import jakarta.validation.Valid;
import org.fk.database1.testshop.tables.dtos.Client;
import org.fk.database1.testshop.tables.interfaces.IClient;

/**
 * ClientDTO
 */
public class ClientDTO extends Client implements IClient {
}
