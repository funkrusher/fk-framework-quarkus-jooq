package org.fk.core.jooq;

import org.jooq.*;

public class JooqHelper {

    public static final <T1, T2, T3, T4, T5, T6, U> RecordMapper<Record6<T1, T2, T3, T4, T5, T6>, U> nullableMapping(
        Function6<T1, T2, T3, T4, T5, T6, ? extends U> function) {
        return row -> row.value1() == null ? null : Records.mapping(function).apply(row);
    }
}
