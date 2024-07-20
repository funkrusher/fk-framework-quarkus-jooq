package org.fk.product.qute;

import io.quarkus.qute.i18n.Message;
import io.quarkus.qute.i18n.MessageBundle;

@MessageBundle(value = "product_msg")
public interface ProductMessages {

    @Message
    String product_paginate_localizationTest();

}
