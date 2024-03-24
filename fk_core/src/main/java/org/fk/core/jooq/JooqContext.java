package org.fk.core.jooq;

import org.fk.core.util.request.RequestContext;
import org.jooq.*;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.jooq.impl.DefaultTransactionProvider;

public class JooqContext implements AutoCloseable {

    private RequestContext requestContext;

    private DSLContext ctx;

    private boolean isOpen;


    public JooqContext(RequestContext requestContext, Configuration configuration) {
        DSLContext ctx = DSL.using(configuration);
        ctx.startTransaction().execute();

        this.isOpen = true;
        this.requestContext = requestContext;
        this.ctx = ctx;
    }

    public RequestContext getRequestContext() {
        return requestContext;
    }

    public DSLContext getCtx() {
        return ctx;
    }

    public void commit() {
        this.ctx.commit().execute();
        this.isOpen = false;
    }

    @Override
    public void close()  {
        if (this.isOpen) {
            try {
                this.ctx.rollback().execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
