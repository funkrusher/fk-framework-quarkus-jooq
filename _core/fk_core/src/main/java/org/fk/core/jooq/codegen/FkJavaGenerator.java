package org.fk.core.jooq.codegen;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.core.dto.AbstractDTO;
import org.jooq.codegen.GeneratorStrategy;
import org.jooq.codegen.JavaGenerator;
import org.jooq.codegen.JavaWriter;
import org.jooq.meta.Definition;
import org.jooq.meta.TableDefinition;
import org.jooq.meta.TypedElementDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FkJavaGenerator extends JavaGenerator {

    @Override
    protected void printClassAnnotations(JavaWriter out, Definition definition, GeneratorStrategy.Mode mode) {
        super.printClassAnnotations(out, definition, mode);
        // we may need this sometimes, if we need to validate the whole dto in one validate call.
        // out.println("@%s", out.ref(Valid.class));
    }


    @Override
    protected void generatePojoClassFooter(TableDefinition table, JavaWriter out) {
        super.generatePojoClassFooter(table, out);

        // add imports to the class.
        out.ref(AbstractDTO.class);
    }
}
