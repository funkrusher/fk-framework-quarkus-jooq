package org.fk.product.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlTransient;
import org.fk.core.dto.BookKeeper;
import org.fk.core.dto.DTO;
import org.fk.database1.testshop.tables.dtos.RoleDto;
import org.fk.database1.testshop.tables.interfaces.IRole;
import org.fk.database1.testshop.tables.records.RoleRecord;
import org.fk.database1.testshop.tables.records.UserRecord;
import org.fk.database1.testshop.tables.records.UserRoleRecord;
import org.jooq.Record1;
import org.jooq.Record2;

import java.io.Serial;
import java.util.List;

/**
 * RoleDTO
 */
public class RoleDTO extends RoleDto {

    public RoleDTO() {}

    public RoleDTO(IRole value) { this.from(value); }

    public static RoleDTO create(Record1<RoleRecord> r) {
        return new RoleDTO(r.value1());
    }
}