package org.fk.core.jooq;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;

public final class FkGeneratorStrategy extends DefaultGeneratorStrategy {

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
        System.out.println("TEST: " + definition.getOutputName());
        return definition.getOutputName();
    }

    @Override
    public String getJavaMemberName(Definition definition, Mode mode) {
        return definition.getOutputName();
    }

}
