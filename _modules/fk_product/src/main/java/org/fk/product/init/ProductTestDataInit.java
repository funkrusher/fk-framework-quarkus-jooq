package org.fk.product.init;

import org.fk.core.init.DataInit;
import org.jooq.DSLContext;

public class ProductTestDataInit implements DataInit {
    @Override
    public String getDataInitId() {
        return "product_test_data";
    }
    @Override
    public void execute(DSLContext dsl) {
    }
}
