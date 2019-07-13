package adamsdd.calculator.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Calculations {

    private BigDecimal x;
    private BigDecimal y;
    private String operation;
    private Map<OperationType, BiFunction<BigDecimal, BigDecimal, BigDecimal>> operations = new HashMap<>();

    public Calculations() {
        operations.put(OperationType.ADD, BigDecimal::add);
        operations.put(OperationType.SUB, BigDecimal::subtract);
        operations.put(OperationType.MUL, BigDecimal::multiply);
        operations.put(OperationType.DIV, BigDecimal::divide);
        operations.put(OperationType.SQRT, (x, y) -> BigDecimal.valueOf(Math.sqrt(y.doubleValue())));
    }

    public BigDecimal calculate(BigDecimal x, BigDecimal y, String operation) {
        return operations.get(OperationType.ADD).apply(x, y);
    }

    public boolean isOperation(String text) {
        for (OperationType op : OperationType.values()) {
            if (op.label.contains(text)) {
                return true;
            }
        }

        return false;
    }
}
