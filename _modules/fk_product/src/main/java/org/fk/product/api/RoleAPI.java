package org.fk.product.api;


import org.fk.database1.testshop.tables.dtos.RoleDto;
import org.fk.database1.testshop.tables.interfaces.IRole;
import org.fk.database1.testshop.tables.records.RoleRecord;
import org.jooq.Record1;

/**
 * RoleDTO
 */
public class RoleAPI extends RoleDto {

    public RoleAPI() {}

    public RoleAPI(IRole value) { this.from(value); }

    public static RoleAPI create(Record1<RoleRecord> r) {
        return new RoleAPI(r.value1());
    }
}