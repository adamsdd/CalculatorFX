package adamsdd.calculator.model;

import java.util.regex.Pattern;

class Operation {

    public final String symbol;
    public final Pattern pattern;

    public Operation(String symbol, Pattern pattern) {
        this.symbol = symbol;
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "symbol='" + symbol + '\'' +
                ", pattern=" + pattern +
                '}';
    }
}
