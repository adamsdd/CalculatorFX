package adamsdd.calculator.model;

public enum CalculationOperationType {

    MUL("*"),
    DIV("/"),
    SQRT("√"),
    POW("^"),
    ADD("+"),
    SUB("-"),
    PERCENT("%");

    public final String label;

    CalculationOperationType(String label) {
        this.label = label;
    }
}
