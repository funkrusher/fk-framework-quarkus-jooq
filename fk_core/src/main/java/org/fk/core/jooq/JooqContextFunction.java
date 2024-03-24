package org.fk.core.jooq;

import org.jooq.Configuration;

/**
 * Interface that helps to throw checked-exceptions within the lambda-function further upwards.
 */
@FunctionalInterface
public interface JooqContextFunction {
    void run(JooqContext jooqContext) throws Throwable;
}