package test2;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class SwingCalculatorView extends JFrame implements CalculatorView  {

  private Calculator calculator;
  private JTextField display;
  private JPanel contentPanel;
  private Character[] buttons = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'C', '<', '.', '+', '-', '*', '/', '='};

  public SwingCalculatorView() throws HeadlessException {
    super("Calculator");
    this.calculator = new Calculator(this);
    createFrame();
  }

  private void createFrame() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    contentPanel = new JPanel();
    contentPanel.setLayout(new BorderLayout());
    this.add(contentPanel);

    display = new JTextField(null, 20);
    contentPanel.add(display, BorderLayout.NORTH);


    JPanel buttonPanel = new JPanel();
    contentPanel.add(buttonPanel);
    for (int i = 0; i < buttons.length; i++) {
      JButton button = new JButton("" + buttons[i]);
      buttonPanel.add(button);
      buttonPanel.setBackground(Color.ORANGE);
      button.addActionListener(listener(buttons[i]));
    }


    this.setVisible(true);
    this.setResizable(false);
    this.setSize(300, 180);
    display.setEditable(false);


  }

  private ActionListener listener(final Character text) {
    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        if (Character.isDigit(text)) {
          calculator.numberPressed(text);
        }else if (Character.isLetter(text)){
          calculator.deletePressed();
        }else if ( text == '<' ){
          calculator.backspacePressed();
        }else if (text == '='){
          calculator.calculateExpression();
        }else if (text == '.'){
          calculator.pointPressed();
        }else {
          calculator.operationPressed(text);
        }
      }
    };
    return actionListener;
  }


  public String getExpression() {
     return display.getText();
  }


  public String setExpression(String expression) {
     display.setText(expression);

    return expression;
  }

}
