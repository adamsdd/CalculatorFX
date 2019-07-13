package adamsdd.calculator.model;

public enum OperationType {

    MUL("x"),
    DIV("/"),
    SQRT("sqrt"),
    POW("pow"),
    ADD("+"),
    SUB("-"),
    PERCENT("%");

    public final String label;

    OperationType(String label) {
        this.label = label;
    }
}
