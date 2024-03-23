package org.fk.core.jooq;

import org.fk.core.util.request.RequestContext;
import org.jooq.DSLContext;

public class JooqContext {

    private RequestContext requestContext;

    private DSLContext ctx;

    public JooqContext(RequestContext requestContext, DSLContext ctx) {
        this.requestContext = requestContext;
        this.ctx = ctx;
    }

    public RequestContext getRequestContext() {
        return requestContext;
    }

    public DSLContext getCtx() {
        return ctx;
    }
}
