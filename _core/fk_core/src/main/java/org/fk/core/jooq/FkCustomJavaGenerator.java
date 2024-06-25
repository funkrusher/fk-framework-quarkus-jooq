package org.fk.core.jooq;

import org.jooq.Field;
import org.jooq.UniqueKey;
import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.codegen.JavaWriter;
import org.jooq.meta.*;
import org.jooq.codegen.JavaGenerator;
import org.jooq.codegen.JavaWriter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jooq.codegen.GeneratorStrategy.Mode;

public final class FkCustomJavaGenerator extends JavaGenerator {

    @Override
    protected void generatePojoClassFooter(TableDefinition table, JavaWriter out) {
        super.generatePojoClassFooter(table, out);

//        List<String> identityFields = table.getColumns().stream()
//            .filter(column -> column.isIdentity())
//            .map(column -> getStrategy().getJavaMemberName(column, Mode.POJO))
//            .collect(Collectors.toList());
//
//        UniqueKeyDefinition pk = table.getPrimaryKey();
//        if (!pk.getKeyColumns().isEmpty()) {
//            out.println();
//            out.tab(1).println("public String getIdentity() {");
//            out.tab(2).println("return %s;", pk.getKeyColumns().size() == 1
//                ? pk.getKeyColumns().get(0)
//                : pk.getKeyColumns().get(0) + "-" + pk.getKeyColumns().get(1));
//            out.tab(1).println("}");
//        }
    }

}