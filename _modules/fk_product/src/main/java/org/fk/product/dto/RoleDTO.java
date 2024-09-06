package org.fk.product.dto;


import org.fk.database1.testshop.tables.dtos.RoleDto;
import org.fk.database1.testshop.tables.interfaces.IRole;
import org.fk.database1.testshop.tables.records.RoleRecord;
import org.jooq.Record1;

/**
 * RoleDTO
 */
public class RoleDTO extends RoleDto<RoleDTO> {

    public RoleDTO() {}

    public RoleDTO(IRole value) { this.from(value); }

    public static RoleDTO create(Record1<RoleRecord> r) {
        return new RoleDTO(r.value1());
    }
}