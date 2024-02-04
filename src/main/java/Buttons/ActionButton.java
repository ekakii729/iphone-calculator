package Buttons;
import CalculatorApp.Operator;

/**
 * This button class is the default for all non number buttons in the app
 * @author Abhay Manoj
 * @since 1.0
 */

public abstract class ActionButton extends CalculatorButton {

    private final Operator operator;

    public ActionButton(Operator operator) {
        this.operator = operator;
        setText(getOperatorSymbol(operator));
    }

    /**
     * Gets the symbol for the action of the button
     * @param operator the action of the button
     * @author Abhay Manoj
     * @since 1.0
     */

    private String getOperatorSymbol(Operator operator) {
        return switch (operator) {
            case ADD -> "+";
            case SUBTRACT -> "−";
            case MULTIPLY -> "×";
            case DIVIDE -> "÷";
            case CLEAR -> "C";
            case NEGATE -> "±";
            case PERCENT -> "%";
            case EQUALS -> "=";
        };
    }

    public Operator getOperator() { return operator; }
}
