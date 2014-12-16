package tddcalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class CalculatorUI extends JFrame implements Display {

//  private Calculator calculator;
  private String currentDisplay="";
  private JTextField jTextField;
  private JPanel contentPanel;
  private Character[] buttons = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'C', '<', '.', '+', '-', '*', '/', '='};
  private CalculatorListener calculatorListener;



  public void createFrame(CalculatorListener calculatorListener) {
    this.calculatorListener = calculatorListener;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    contentPanel = new JPanel();
    contentPanel.setLayout(new BorderLayout());
    this.add(contentPanel);

    jTextField = new JTextField(null, 20);
    contentPanel.add(jTextField, BorderLayout.NORTH);


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
    jTextField.setEditable(false);
  }

  private ActionListener listener(final Character text) {

    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        if (Character.isDigit(text)) {
          calculatorListener.onNumberPressed(Integer.valueOf(text));
        }else if (Character.isLetter(text)){
          calculatorListener.onDeletePressed();
        }else if ( text == '<' ){
          calculatorListener.onBackspacePressed();
        }else if (text == '='){
          calculatorListener.onEvaluate();
        }else if (text == '.'){
          calculatorListener.onPointPressed();
        }else {
          calculatorListener.onOperationPressed(String.valueOf(text));
        }
//        if (isDigit(text.toString())) {
//          //Logikata za chislo
//          calculator.onNumberPressed(String.valueOf(text));
//        }
//        if (text.equals("=")) {
//          //Logikata za ravno
//        }
//
//        if (text.equals("<")) {
//          //Logikata za triene
//        }
//        switch (text) {
//          case '-':
////            if (Character.isDigit(text)) {
//              calculator.onNumberPressed(String.valueOf((text)));break;
////            }
//          case '2':
//            if (Character.isLetter(text)){
//              calculator.onDeletePressed();break;
//            }
//          case '3':
//            if (text.equals('<')){
//              calculator.onBackspacePressed();break;
//            }
//          case '4':
////            if (text.equals('=')){
//              calculator.onEvaluate();break;
////            }
//          case '5':
////            if (text.equals('.')){
//              calculator.onPointPressed();break;
////            }
//          case '6':
//            calculator.onOperationPressed(String.valueOf(text));break;
//        }
      }

    };
    return actionListener;
  }



  @Override
  public void displayText(String text) {
    currentDisplay=text;
    jTextField.setText(currentDisplay);
  }

//  @Override
//  public String getText() {
//    return jTextField.getText();
//  }
}
