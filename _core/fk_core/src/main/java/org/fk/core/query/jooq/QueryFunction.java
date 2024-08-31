package org.fk.core.query.jooq;

import org.fk.core.query.model.FkQuery;
import org.jooq.JSON;
import org.jooq.Record1;
import org.jooq.SelectFinalStep;

@FunctionalInterface
public interface QueryFunction {
    SelectFinalStep<Record1<JSON>> apply(FkQuery query);
}
