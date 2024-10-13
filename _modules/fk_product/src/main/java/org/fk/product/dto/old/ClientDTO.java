package org.fk.product.dto.old;

import org.fk.database1.testshop.tables.dtos.ClientDto;
import org.fk.database1.testshop.tables.interfaces.IClient;
import org.fk.database1.testshop.tables.records.ClientRecord;
import org.jooq.Record1;

/**
 * ClientDTO
 */
public class ClientDTO extends ClientDto<ClientDTO> {

    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------

    public ClientDTO() {
        super();
    }

    public ClientDTO(IClient value) {
        super(value);
    }

    public static ClientDTO create(Record1<ClientRecord> r) {
        return new ClientDTO(r.value1());
    }
}
