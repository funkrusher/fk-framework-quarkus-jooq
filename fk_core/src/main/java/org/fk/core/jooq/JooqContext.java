package org.fk.core.jooq;

import org.fk.core.util.request.RequestContext;
import org.jooq.*;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.jooq.impl.DefaultTransactionProvider;

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
