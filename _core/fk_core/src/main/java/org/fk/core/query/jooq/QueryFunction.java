package org.fk.core.query.jooq;

import org.fk.core.query.model.FkQuery;
import org.jooq.Record1;
import org.jooq.SelectFinalStep;

@FunctionalInterface
public interface QueryFunction<T> {
    SelectFinalStep<Record1<T>> apply(FkQuery query);
}
