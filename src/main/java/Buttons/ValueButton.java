package Buttons;
import CalculatorApp.Operator;
import java.awt.*;

/**
 * This button class is used for all buttons that are used for non-arithmetic actions, such as clearing and negating,
 * basically just the light gray buttons at the top
 * @author Abhay Manoj
 * @since 1.0
 */

public class ValueButton extends ActionButton {

    public ValueButton(Operator operator) {
        super(operator);
        setBackground(new Color(165, 165, 165));
        setForeground(Color.BLACK);
    }
}
