package org.fk.core.query.model;


public class FkSorter {
    private final String field;
    private final FkSorterOperator operator;

    public FkSorter(String field, FkSorterOperator operator) {
        this.field = field;
        this.operator = operator;
    }

    public String getField() {
        return field;
    }

    public FkSorterOperator getOperator() {
        return operator;
    }


    public static FkSorter parseSorterString(String filterString) {
        if (filterString == null || filterString.isEmpty()) {
            return null;
        }
        String[] parts = filterString.split(":");
        if (parts.length >= 1) {
            String field = parts[0];
            FkSorterOperator operator = FkSorterOperator.fromString(parts[1]);
            if (operator != null) {
                return new FkSorter(field, operator);
            }
        }
        return null;
    }
}