package org.fk.core.jooq;

import org.fk.core.util.request.RequestContext;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultExecuteListener;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.name;

/**
 * FkExecuteListener
 * <p>
 * is used to append a clientId=X criteria to all possible SQL-statements of our queries for safe multi-tenancy
 * </p>
 */
public class FkExecuteListener extends DefaultExecuteListener {

    private RequestContext request;

    public FkExecuteListener(RequestContext request) {
        this.request = request;
    }

    @Override
    public void renderStart(ExecuteContext ctx) {
        if (ctx.query() instanceof Select) {

            // Operate on jOOQ's internal query model
            SelectQuery<?> select = null;

            // Check if the query was constructed using the "model" API
            if (ctx.query() instanceof SelectQuery) {
                select = (SelectQuery<?>) ctx.query();
            }

            // Check if the query was constructed using the DSL API
            else if (ctx.query() instanceof SelectFinalStep) {
                select = ((SelectFinalStep<?>) ctx.query()).getQuery();
            }

            if (select != null) {
                // Use a more appropriate predicate expression
                // to form more generic predicates which work on all tables

                String sql = ctx.query().getSQL();
                Select<?> s =  select;
                Table<?> table = s.asTable();

                // Do something with the table
                Field<Integer> clientIdField = table.field(DSL.field(DSL.name("clientId"), Integer.class));
                if (clientIdField != null) {
                    Field<Integer> field = DSL.field(DSL.name("clientId"), Integer.class);
                    select.addConditions(field.eq(request.getClientId()));
                } else {
                    select.addConditions(DSL.trueCondition());
                }
            }
        }
    }
}
