package adamsdd.calculator.model;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class CalculationPattern {

    private static final Pattern DECIMAL = Pattern.compile("(-?\\d+\\.-?\\d+)");
    private static final Pattern POW = Pattern.compile("(-?\\d+)\\^(-?\\d+)");
    private static final Pattern SQRT = Pattern.compile("√(-?\\d+)");
    private static final Pattern MUL = Pattern.compile("(-?\\d+)\\*(-?\\d+)");
    private static final Pattern DIV = Pattern.compile("(-?\\d+)/(-?\\d+)");
    private static final Pattern ADD = Pattern.compile("(-?\\d+)\\+(-?\\d+)");
    private static final Pattern SUB = Pattern.compile("(-?\\d+)-(-?\\d+)");

    public final static Map<Integer, Operation> operationsPriority = new TreeMap<>();

    static {
        operationsPriority.put(0, new Operation(".", DECIMAL));
        operationsPriority.put(1, new Operation("^", POW));
        operationsPriority.put(2, new Operation("√", SQRT));
        operationsPriority.put(3, new Operation("*", MUL));
        operationsPriority.put(4, new Operation("/", DIV));
        operationsPriority.put(5, new Operation("+", ADD));
        operationsPriority.put(6, new Operation("-", SUB));
    }
}
