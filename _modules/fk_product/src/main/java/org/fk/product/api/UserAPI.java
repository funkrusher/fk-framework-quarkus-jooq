package org.fk.product.api;


import org.fk.database1.testshop.tables.dtos.UserDto;
import org.fk.database1.testshop.tables.interfaces.IUser;
import org.fk.database1.testshop.tables.records.UserRecord;
import org.jooq.Record2;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * UserAPI
 */
public class UserAPI extends UserDto {

    private List<RoleAPI> roles = new ArrayList<RoleAPI>();

    public UserAPI() {}

    public UserAPI(IUser value) { this.from(value); }

    public static @Nullable UserAPI createOrNull(Record2<UserRecord, List<RoleAPI>> r) {
        if (r.value1().getUserId() == null) {
            return null;
        } else {
            return new UserAPI(r.value1())
                .setRoles(r.value2());
        }
    }

    public List<RoleAPI> getRoles() {
        return roles;
    }

    public UserAPI setRoles(List<RoleAPI> roles) {
        this.roles = roles;
        keeper.touch("roles");
        return this;
    }
}