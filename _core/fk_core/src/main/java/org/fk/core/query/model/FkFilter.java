package org.fk.core.query.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FkFilter {
    private final String field;
    private final FkFilterOperator operator;
    private final List<String> values;

    public FkFilter(String field, FkFilterOperator operator, List<String> values) {
        this.field = field;
        this.operator = operator;
        this.values = values;
    }

    public String getField() {
        return field;
    }

    public FkFilterOperator getOperator() {
        return operator;
    }

    public List<String> getValues() {
        return values;
    }

    public static List<FkFilter> parseFilterString(String filterString) {
        final List<FkFilter> filters = new ArrayList<>();
        if (filterString == null || filterString.isEmpty()) {
            return filters;
        }
        String[] filterParams = filterString.split(",");
        Arrays.stream(filterParams)
                .map(param -> param.split(":"))
                .forEach(parts -> {
                    if (parts.length >= 2) {
                        String field = parts[0];
                        FkFilterOperator operator = FkFilterOperator.fromString(parts[1]);
                        if (operator != null) {
                            List<String> values = parts.length > 2 ? Arrays.asList(parts[2].split("\\|")) : new ArrayList<>();
                            filters.add(new FkFilter(field, operator, values));
                        }
                    }
                });
        return filters;
    }
}