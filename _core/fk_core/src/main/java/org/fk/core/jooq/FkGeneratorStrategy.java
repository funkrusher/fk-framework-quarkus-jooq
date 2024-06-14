package org.fk.core.jooq;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class FkGeneratorStrategy extends DefaultGeneratorStrategy {

    private static Pattern FK_PATTERN = Pattern.compile("^(.*)_(.*?)(Id)?$");

    @Override
    public String getJavaSetterName(Definition definition, Mode mode) {
        return "set" + definition.getOutputName().substring(0, 1).toUpperCase() + definition.getOutputName().substring(1);
    }

    @Override
    public String getJavaGetterName(Definition definition, Mode mode) {
        return "get" + definition.getOutputName().substring(0, 1).toUpperCase() + definition.getOutputName().substring(1);
    }

    @Override
    public String getJavaMethodName(Definition definition, Mode mode) {
        if (definition instanceof ForeignKeyDefinition) {
            ForeignKeyDefinition def = (ForeignKeyDefinition) definition;

            Matcher matcher = FK_PATTERN.matcher(definition.getOutputName());
            if (matcher.matches()) {
                return matcher.group(2);
            } else {
                return definition.getOutputName();
            }
        } else {
            if (definition instanceof InverseForeignKeyDefinition) {
                InverseForeignKeyDefinition def = (InverseForeignKeyDefinition) definition;
                TableDefinition def3 = def.getReferencingTable();
                return def3.getName();
            } else if (definition instanceof ManyToManyKeyDefinition) {
                ManyToManyKeyDefinition def = (ManyToManyKeyDefinition) definition;
                TableDefinition def3 = def.getTable();
                return def3.getName();
            }
            return definition.getOutputName();
        }
    }

    @Override
    public String getJavaMemberName(Definition definition, Mode mode) {
        return definition.getOutputName();
    }
}