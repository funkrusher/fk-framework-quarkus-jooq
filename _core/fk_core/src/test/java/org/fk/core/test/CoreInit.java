package org.fk.core.test;

import jakarta.enterprise.context.ApplicationScoped;
import org.jooq.DSLContext;


@ApplicationScoped
public class CoreInit {

    public void init(DSLContext dsl) {
        dsl.transaction(tx1 -> {
        });
    }
}
