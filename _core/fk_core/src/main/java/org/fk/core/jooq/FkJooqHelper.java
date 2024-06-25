package org.fk.core.jooq;

import org.jetbrains.annotations.Nullable;
import org.jooq.Record;
import org.jooq.RecordMapper;

import java.util.function.Function;

public class FkJooqHelper {

    private static class NullOnFirstNullRecordMapper<R extends Record, E> implements RecordMapper<R, E> {
        private final Function<R, E> mapper;

        public NullOnFirstNullRecordMapper(Function<R, E> mapper) {
            this.mapper = mapper;
        }

        @Override
        public @Nullable E map(R r) {
            return (r.getValue(0) == null) ? null : mapper.apply(r);
        }
    }

    public static <R extends Record, E> RecordMapper<R, E> nullOnFirstNull(Function<R, E> mapper) {
        return new NullOnFirstNullRecordMapper<>(mapper);
    }
}
