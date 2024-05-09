package org.fk.root.init;

import org.fk.core.init.DataInit;
import org.jooq.DSLContext;

public class RootTestDataInit implements DataInit {
    @Override
    public String getDataInitId() {
        return "root_test_data";
    }
    @Override
    public void execute(DSLContext dsl) {
    }
}
