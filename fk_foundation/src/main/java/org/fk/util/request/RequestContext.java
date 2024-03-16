package org.fk.util.request;

import org.fk.auth.FkSecurityIdentity;

/**
 * RequestContext
 * <p>
 * contains request-scoped information about the user/client/language of the request/caller
 * to provide a basis for logic-decisions within our layers.
 * </p>
 */
public class RequestContext {

    private Integer clientId;

    private Integer langId;

    public RequestContext(Integer clientId, Integer langId) {
        this.clientId = clientId;
        this.langId = langId;
    }

    public RequestContext(FkSecurityIdentity fkSecurityIdentity, Integer langId) {
        this.clientId = fkSecurityIdentity.getClientId();
        this.langId = langId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public Integer getLangId() {
        return langId;
    }

}
