package org.fk.product.qute;

import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.CheckedTemplate;
import org.fk.product.api.ProductAPI;

import java.util.List;

@CheckedTemplate
public class ProductTemplates {
    public static native TemplateInstance productsTemplate(List<ProductAPI> products);
}
