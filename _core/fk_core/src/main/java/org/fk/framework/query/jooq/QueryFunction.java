package org.fk.framework.query.jooq;

import org.fk.framework.query.model.FkQuery;
import org.jooq.Record1;
import org.jooq.SelectFinalStep;

@FunctionalInterface
public interface QueryFunction<T> {
    SelectFinalStep<Record1<T>> apply(FkQuery query);
}
