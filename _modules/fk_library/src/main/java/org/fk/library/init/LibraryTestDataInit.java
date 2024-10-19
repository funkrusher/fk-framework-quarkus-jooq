package org.fk.library.init;

import org.fk.framework.init.DataInit;
import org.jooq.DSLContext;

public class LibraryTestDataInit implements DataInit {
    @Override
    public String getDataInitId() {
        return "library_test_data";
    }
    @Override
    public void execute(DSLContext dsl) {
    }
}
