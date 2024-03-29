package org.fk.product.dto;


import jakarta.validation.Valid;
import org.fk.database.testshop.tables.dtos.Client;
import org.fk.database.testshop.tables.interfaces.IClient;

/**
 * ClientDTO
 */
public class ClientDTO extends Client implements IClient {
}
