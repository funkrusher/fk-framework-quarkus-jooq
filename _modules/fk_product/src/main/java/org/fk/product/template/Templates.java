package org.fk.product.template;

import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.CheckedTemplate;
import org.fk.product.dto.ProductDTO;

import java.util.List;

@CheckedTemplate
public class Templates {
    public static native TemplateInstance productsTemplate(List<ProductDTO> products);
}
