package org.fk.root.init;

import org.fk.framework.init.DataInit;
import org.jooq.DSLContext;
public class RootBasicDataInit implements DataInit {
    @Override
    public String getDataInitId() {
        return "root_basic_data";
    }
    @Override
    public void execute(DSLContext dsl) {
    }
}
