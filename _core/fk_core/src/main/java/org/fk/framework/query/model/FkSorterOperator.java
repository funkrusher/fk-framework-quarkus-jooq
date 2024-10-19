package org.fk.framework.query.model;

public enum FkSorterOperator {

    ASC("asc"),
    DESC("desc");

    private final String symbol;

    FkSorterOperator(String symbol)    {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static FkSorterOperator fromString(String symbol) {
        for (FkSorterOperator operator : FkSorterOperator.values()) {
            if (operator.getSymbol().equals(symbol)) {
                return operator;
            }
        }
        return null;
    }
}