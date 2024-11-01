package org.fk.framework.jooq;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public String getJavaMemberName(Definition definition, Mode mode) {
        return definition.getOutputName();
    }
}