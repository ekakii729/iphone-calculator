package Buttons;
import javax.swing.*;
import java.awt.*;

/**
 * This button class is the default for all buttons in the app
 * @author Abhay Manoj
 * @since 1.0
 */

public abstract class CalculatorButton extends JButton {

    private static final Font font = new Font("Helvetica", Font.PLAIN, 55);

    public CalculatorButton() {
        setFocusable(false);
        setPreferredSize(new Dimension(100,100));
        setFont(font);
        setForeground(Color.WHITE);
    }
}
