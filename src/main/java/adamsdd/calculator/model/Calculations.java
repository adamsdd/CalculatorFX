package adamsdd.calculator.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;

public class Calculations {

    private BigDecimal x;
    private BigDecimal y;
    private String operation;
    private Map<CalculationOperationType, BiFunction<BigDecimal, BigDecimal, BigDecimal>> calculationOperations = new HashMap<>();
    private Map<FunctionOperationType, Function<StringBuilder, StringBuilder>> functionsOperations = new HashMap<>();

    public Calculations() {
        initCalculationOperations();
        initFunctionalOperations();
    }

    public BigDecimal calculate(BigDecimal x, BigDecimal y, String operation) {
        return calculationOperations.get(CalculationOperationType.ADD).apply(x, y);
    }

    public boolean isOperation(String text) {
        for (CalculationOperationType op : CalculationOperationType.values()) {
            if (op.label.contains(text)) {
                return true;
            }
        }

        return false;
    }

    private void initCalculationOperations() {
        calculationOperations.put(CalculationOperationType.ADD, BigDecimal::add);
        calculationOperations.put(CalculationOperationType.SUB, BigDecimal::subtract);
        calculationOperations.put(CalculationOperationType.MUL, BigDecimal::multiply);
        calculationOperations.put(CalculationOperationType.DIV, BigDecimal::divide);
        calculationOperations.put(CalculationOperationType.POW, (x, y) -> x.pow(y.intValue()));
        calculationOperations.put(CalculationOperationType.SQRT, (x, y) -> BigDecimal.valueOf(Math.sqrt(y.doubleValue())));
    }

    private void initFunctionalOperations() {
        functionsOperations.put(FunctionOperationType.C, (currentResult) -> {
            if (currentResult.length() > 0) {
                currentResult.setLength(0);
            }

            return currentResult;
        });
    }

    public StringBuilder doFunctionOperation(FunctionOperationType functionOperationType, StringBuilder result) {
        return functionsOperations.get(functionOperationType).apply(result);
    }
}
