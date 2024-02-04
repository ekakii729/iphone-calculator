package Buttons;
import java.awt.*;

/**
 * This button class is used for all buttons that represent numbers in the app
 * @author Abhay Manoj
 * @since 1.0
 */

public class NumberButton extends CalculatorButton {

    private final String value;

    public NumberButton(String value) {
        this.value = value;
        setText(value);
        setBackground(new Color(51, 51, 51));
    }

    public String getValue() { return value; }
}
