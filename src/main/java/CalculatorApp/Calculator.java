package CalculatorApp;
import Buttons.*;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;

/**
 * This is the main class for the calculator
 * @author Abhay Manoj
 * @since 1.0
 */

public class Calculator {

    private JFrame window;
    private JPanel display;
    private JLabel resultField;
    private static final CalculationsQueue queue = new CalculationsQueue();

    /**
     * Makes the calculator visible
     * @author Abhay Manoj
     * @since 1.0
     */

    public Calculator() {
        init();
        window.setVisible(true);
    }

    /**
     * Initializes the display of the calculator
     * @author Abhay Manoj
     * @since 1.0
     */

    private void init() {
        initWindow();
        initResultField();
        initLayout();
    }

    /**
     * Initializes the window, setting the size, color of title bar and more
     * @author Abhay Manoj
     * @since 1.0
     */

    private void initWindow() {
        FlatMacDarkLaf.setup(); // look and feel for the window
        UIManager.put("Button.arc", 999); // for circular buttons
        window = new JFrame("Calculator");
        window.setSize(500, 825);
        window.setResizable(false);
        window.setIconImage(new ImageIcon("src/main/resources/calculator.png").getImage());
        window.getRootPane().putClientProperty("JRootPane.titleBarBackground", Color.BLACK); // setting title bar to black
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        display = (JPanel) window.getContentPane();
        display.setBackground(Color.BLACK);
        display.setLayout(new MigLayout("gap 15, insets 15")); // gap of 15 between buttons and from edges of the screen
    }

    /**
     * Initializes the result field, loads the font and the position
     * @author Abhay Manoj
     * @since 1.0
     */

    private void initResultField() {
        resultField = new JLabel("0");
        resultField.setFont(new Font("Helvetica", Font.PLAIN, 100));
        resultField.setHorizontalTextPosition(SwingConstants.RIGHT);
        resultField.setHorizontalAlignment(SwingConstants.RIGHT);
        display.add(resultField, "gaptop 50, span, right, wrap"); // result field is on its own row
    }

    /**
     * Initializes the number button layout, just adds it to the screen
     * @author Abhay Manoj
     * @since 1.0
     */

    private void initLayout() {
        addRow(new CalculatorButton[] { new ValueButton(Operator.CLEAR), new ValueButton(Operator.NEGATE), new ValueButton(Operator.PERCENT)}, Operator.DIVIDE); // C, +-, %, /
        addRow(getButtonList(7), Operator.MULTIPLY); // 7, 8, 9, x
        addRow(getButtonList(4), Operator.SUBTRACT); // 4, 5, 6, -
        addRow(getButtonList(1), Operator.ADD); // 1, 2, 3, +
        addRow(new CalculatorButton[] { new ZeroButton(), new NumberButton(".")}, Operator.EQUALS); // 0, ., =
    }

    /**
     * Adds a single row of buttons to the screen
     * @param buttons The list of number buttons to be added
     * @param operator The operator of the arithmetic button at the end of the row
     * @author Abhay Manoj
     * @since 1.0
     */

    private void addRow(CalculatorButton[] buttons, Operator operator) {
        for (CalculatorButton button : buttons) {
            setButtonAction(button);
            display.add(button, button instanceof ZeroButton ? "span 2" : ""); // if it is the zero button (which is long), it will take up 2 columns
        } ArithmeticButton arithmeticButton = new ArithmeticButton(operator);
        setButtonAction(arithmeticButton);
        display.add(arithmeticButton, "wrap"); // since arithmetic button is the last one in row, it will wrap to next row after
    }

    /**
     * Gets an array of 3 number buttons, starting from the one on the left
     * @param min The value of the left-most number button
     * @author Abhay Manoj
     * @since 1.0
     */

    private NumberButton[] getButtonList(int min) {
        NumberButton[] buttons = new NumberButton[3];
        for (int i = 0; i < buttons.length; i++) buttons[i] = new NumberButton(String.valueOf(i + min)); // creates number button with its proper number, e.g. numberButton(8)
        return buttons;
    }

    /**
     * Adds an action listener to the button, depending on which it is
     * @param button The button to add the action listener to
     * @author Abhay Manoj
     * @since 1.0
     */

    private void setButtonAction(CalculatorButton button) {
        if (button instanceof NumberButton) button.addActionListener(e -> drawNumber(((NumberButton) button).getValue())); // if number button is pressed, draws number to screen
        else button.addActionListener(e -> doOperation(((ActionButton)button).getOperator())); // if any other button is pressed, it does the action
    }

    /**
     * Draws the number of the button pressed to the screen
     * @param value The value of the button pressed
     * @author Abhay Manoj
     * @since 1.0
     */

    private void drawNumber(String value) {
        String currentNumber = resultField.getText();
        resultField.setText(currentNumber.equals("0") ? value : currentNumber + value); // if the current number displayed is 0, then set the number to the button pressed, if not, then just add the number pressed to the end of the current number
    }

    /**
     * Performs the action of the button that was pressed
     * @param operator The action of the button
     * @author Abhay Manoj
     * @since 1.0
     */

    private void doOperation(Operator operator) {
        double currentNumber = Double.parseDouble(resultField.getText());
        switch (operator) {
            case ADD, SUBTRACT, MULTIPLY, DIVIDE -> {
                queue.add(currentNumber, operator);
                resultField.setText("0");
            } case CLEAR -> resultField.setText("0");
            case EQUALS -> resultField.setText(doubleToIntString(queue.calculate(currentNumber)));
            case NEGATE -> resultField.setText(doubleToIntString(-currentNumber));
            case PERCENT -> resultField.setText(doubleToIntString(currentNumber / 100));
        }
    }

    /**
     * Returns a double into a string, but if it can be an integer, will remove the decimals
     * @param number The number to be converted
     * @author Abhay Manoj
     * @since 1.0
     */

    private String doubleToIntString(double number) {
        return (number % 1 == 0) ? String.format("%.0f", number) : String.valueOf(number);
    }

    public static void main(String[] args) { SwingUtilities.invokeLater(Calculator::new); }
}
