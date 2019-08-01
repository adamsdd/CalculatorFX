package adamsdd.calculator.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Matcher;

public class Calculations {

    private Map<String, BiFunction<BigDecimal, BigDecimal, BigDecimal>> calculationOperations = new HashMap<>();
    private Map<FunctionOperationType, Function<StringBuilder, StringBuilder>> functionsOperations = new HashMap<>();

    public Calculations() {
        initCalculationOperations();
        initFunctionalOperations();
    }

    private void initCalculationOperations() {
        calculationOperations.put(CalculationOperationType.ADD.label, BigDecimal::add);
        calculationOperations.put(CalculationOperationType.SUB.label, BigDecimal::subtract);
        calculationOperations.put(CalculationOperationType.MUL.label, BigDecimal::multiply);
        calculationOperations.put(CalculationOperationType.DIV.label, BigDecimal::divide);
        calculationOperations.put(CalculationOperationType.POW.label, (x, y) -> x.pow(y.intValue()));
        calculationOperations.put(CalculationOperationType.SQRT.label, (x, y) -> BigDecimal.valueOf(Math.sqrt(y.doubleValue())));
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

    public BigDecimal calculateExpression(String expression) {
        System.out.println("expression to calculate = " + expression);

        try {
            for (Map.Entry<Integer, Operation> op : CalculationPattern.operationsPriority.entrySet()) {
                System.out.println("Actual op = " + op);
                if (expression.contains(op.getValue().symbol)) {
                    System.out.println("Expression contains = " + op.getValue().symbol);
                    Matcher matcher = op.getValue().pattern.matcher(expression);
                    expression = executePattern(expression, matcher, calculationOperations.get(op.getValue().symbol));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error = " + e);
        }

        System.out.println("expression to return = " + expression);
        return new BigDecimal(expression);
    }

    private String executePattern(String expression, Matcher matcher, BiFunction operation) {

        String result = null;
        while (matcher.find()) {
            String reg = matcher.group(0);
            System.out.println("matcher.group(0) = " + reg);
            System.out.println("matcher.group(1) = " + matcher.group(1));
            System.out.println("matcher.group(2) = " + matcher.group(2));
            BigDecimal x = new BigDecimal(matcher.group(1));
            BigDecimal y = new BigDecimal(matcher.group(2));
            result = String.valueOf(operation.apply(x, y));
            expression = expression.replace(reg, result);
            matcher.reset(expression);
        }

        System.out.println("RESULT = " + result);
        System.out.println("Expression = " + expression);
        return expression;
    }
}
