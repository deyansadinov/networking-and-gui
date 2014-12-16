package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class SwingCalculatorView extends JFrame implements CalculatorView {

  private final int BACKSPACE_UNI_CODE = 60;
  private final int POINT_UNI_CODE = 46;
  private final int EQUALS_UNI_CODE = 61;
  private Calculator calculator;
  private JTextField display;
  private JPanel mainPanel;
  private Character[] buttons = {'6', '7', '8', '9', 'C', '<', '2', '3', '4', '5', '*', '/', '1', '.', '0', '=', '-', '+'};

  public SwingCalculatorView() {
    this.calculator = new Calculator(this);
    createFrame();
  }

  private void createFrame() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    add(mainPanel);
    display = new JTextField("");
    display.setHorizontalAlignment(JTextField.RIGHT);
    mainPanel.add(display, BorderLayout.NORTH);

    JPanel panelButton = new JPanel(new GridLayout(3, 6));
    mainPanel.add(panelButton, BorderLayout.CENTER);
    for (int i = 0; i < buttons.length; i++) {
      JButton button = new JButton("" + buttons[i]);
      panelButton.add(button);
      button.addActionListener(listener(buttons[i]));
    }

    setVisible(true);
    setSize(600, 400);
    pack();
  }

  private ActionListener listener(final Character text) {
    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (Character.isDigit(text)) {
          calculator.onNumberPressed(text);
        }else if (Character.isLetter(text)) {
          calculator.onDeletedPressed();
        }else if (((int) text) == BACKSPACE_UNI_CODE) { // "<"
          calculator.onBackspacePressed();
        }else if (((int) text) == POINT_UNI_CODE) { //"."
          calculator.onPointPressed();
        }else if (((int) text) == EQUALS_UNI_CODE) { //"="
          calculator.onCalculateExpression();
        }else {
          calculator.onOperationPressed(text);
        }
      }
    };

    return actionListener;
  }


  @Override
  public String getExpression() {
    return display.getText();
  }

  @Override
  public String setExpression(String expression) {
    display.setText(expression);
    return expression;
  }
}
