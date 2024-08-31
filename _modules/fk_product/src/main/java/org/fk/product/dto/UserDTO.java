package org.fk.product.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlTransient;
import org.fk.core.dto.BookKeeper;
import org.fk.core.dto.DTO;
import org.fk.database1.testshop.tables.interfaces.IUser;
import org.fk.database1.testshop.tables.records.UserRecord;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.jooq.Record2;
import org.jooq.Record3;

import javax.annotation.Nullable;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDTO
 */
public record UserDTO (
    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------

    Integer userId,
    Integer clientId,
    String email,
    String firstname,
    String lastname,

    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------
    List<RoleDTO> roles
)  implements DTO {

    public static @Nullable UserDTO createOrNull(Record2<UserRecord, List<RoleDTO>> r) {
        if (r.value1().getUserId() == null) {
            return null;
        } else {
            UserRecord userRecord = r.value1();
            List<RoleDTO> roles = r.value2();

            return new UserDTO(
                userRecord.getUserId(),
                userRecord.getClientId(),
                userRecord.getEmail(),
                userRecord.getFirstname(),
                userRecord.getLastname(),
                roles
            );
        }
    }
}