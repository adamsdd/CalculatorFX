package adamsdd.calculator.model;

public enum CalculationOperationType {

    MUL("*"),
    DIV("/"),
    SQRT("sqrt"),
    POW("pow"),
    ADD("+"),
    SUB("-"),
    PERCENT("%");

    public final String label;

    CalculationOperationType(String label) {
        this.label = label;
    }
}
