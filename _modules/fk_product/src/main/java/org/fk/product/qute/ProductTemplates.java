package org.fk.product.qute;

import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.CheckedTemplate;
import org.fk.product.dto.ProductDTO;

import java.util.List;

@CheckedTemplate
public class ProductTemplates {
    public static native TemplateInstance productsTemplate(List<ProductDTO> products);
}
