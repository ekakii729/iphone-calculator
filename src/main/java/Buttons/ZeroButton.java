package Buttons;
import java.awt.*;

/**
 * This button class is used for the zero button, which is longer than the other number buttons
 * @author Abhay Manoj
 * @since 1.0
 */

public class ZeroButton extends NumberButton {

    public ZeroButton() {
        this("0");
    }

    public ZeroButton(String value) {
        super(value);
        setPreferredSize(new Dimension(215, 100));
    }
}
