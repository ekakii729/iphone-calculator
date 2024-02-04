package CalculatorApp;
import java.util.LinkedList;

/**
 * This class stores numbers / operations of the calculator and also does the math for it
 * @author Abhay Manoj
 * @since 1.0
 */

public class CalculationsQueue {

    private final LinkedList<Double> numbers;
    private final LinkedList<Operator> operations;

    public CalculationsQueue() {
        numbers = new LinkedList<>();
        operations = new LinkedList<>();
    }

    /**
     * Adds a number and an action to the lists
     * @param number The number to be added
     * @param operator The action to be added
     * @author Abhay Manoj
     * @since 1.0
     */

    public void add(double number, Operator operator ) {
        numbers.push(number);
        operations.push(operator);
    }

    /**
     * Calculates what the answer should be, based off numbers in the lists
     * @param currentNumber The current number that was entered in the result field
     * @author Abhay Manoj
     * @since 1.0
     */

    public double calculate(double currentNumber) {
        double firstNumber = numbers.remove();
        Operator operator = operations.remove();
        return switch (operator) {
            case ADD -> firstNumber + currentNumber;
            case SUBTRACT -> firstNumber - currentNumber;
            case MULTIPLY -> firstNumber * currentNumber;
            case DIVIDE -> firstNumber / currentNumber;
            default -> throw new IllegalStateException("Unexpected value: " + operator);
        };
    }
}
