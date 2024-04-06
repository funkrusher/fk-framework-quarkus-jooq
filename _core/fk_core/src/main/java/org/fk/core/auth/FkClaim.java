package org.fk.core.auth;

import java.util.List;

public class FkClaim {

    private Integer clientId;

    private Integer userId;

    private List<String> roles;

    public FkClaim() {
    }

    public FkClaim(final Integer clientId, final Integer userId, final List<String> roles) {
        this.clientId = clientId;
        this.userId = userId;
        this.roles = roles;
    }

    public Integer getClientId() {
        return clientId;
    }

    public Integer getUserId() {
        return userId;
    }


    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
