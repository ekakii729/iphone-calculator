package Buttons;
import CalculatorApp.Operator;
import java.awt.*;

/**
 * This button class is used for all buttons that do an arithmetic calculation, basically just the orange / right most
 * buttons
 * @author Abhay Manoj
 * @since 1.0
 */

public class ArithmeticButton extends ActionButton {

    public ArithmeticButton(Operator operator) {
        super(operator);
        setBackground(new Color(253, 149, 0));
    }
}
