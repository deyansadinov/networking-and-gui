package com.clouway.networkingandgui.tddcalculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 11:41 Nov 14-11-26
 */
public class TDDCalculatorUI extends JFrame implements Display {
  private String display = "";
  private JTextField textField1, textField2;
  private JButton buttonClear, buttonRemove;
  private JButton buttonMultu, buttonMinus, buttonDev, buttonPlus;
  private JButton buttonEight, buttonOne, buttonZero, buttonThree, buttonSeven, buttonTwo, buttonFive, buttonFour, buttonSix, buttonNine;
  private JButton buttonDot,buttonEQ;
  private JPanel panel1;


  private JButton[] numberButtons = new JButton[]{buttonEight, buttonOne, buttonZero, buttonThree,
          buttonSeven, buttonTwo, buttonFive, buttonFour, buttonSix, buttonNine};
  private JButton[] opperatorsButtons = new JButton[]{buttonMultu, buttonMinus, buttonDev, buttonPlus};

  private CalculatorListener calculatorListener;

  public void init(final CalculatorListener listener) {
    this.calculatorListener = listener;
    this.setSize(500, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    textField1.setEnabled(false);
    this.add(panel1);
    this.setVisible(true);

    //Nummber is pressed(listener)
    ActionListener numberButtonPressed = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (JButton number : numberButtons) {
          if (e.getSource() == number) {
            listener.numberIsPressed(number.getText());
          }
        }
      }
    };
    for (JButton number : numberButtons) {
      number.addActionListener(numberButtonPressed);
    }

    ActionListener opperatorButtonPressed = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (JButton opperator : opperatorsButtons) {
          if (e.getSource() == opperator) {
            listener.operatorIsPressed(opperator.getText());
          }
        }
      }
    };
    for (JButton opperator : opperatorsButtons) {
      opperator.addActionListener(opperatorButtonPressed);
    }

    ActionListener clearButtonPressed = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonClear) {
          listener.clearIsPressed(buttonClear.getText());
        }
      }
    };
    buttonClear.addActionListener(clearButtonPressed);

    ActionListener removeButtonPressed = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonRemove) {
          listener.removeIsPressed(buttonRemove.getText());
        }
      }
    };
    buttonRemove.addActionListener(removeButtonPressed);

    ActionListener dotButtonPressed = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonDot) {
          listener.dotIsPressed(buttonDot.getText());
        }
      }
    };
    buttonDot.addActionListener(dotButtonPressed);

    ActionListener eqButtonPressed = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonEQ) {
          listener.eqIsPressed(buttonEQ.getText());
        }
      }
    };
    buttonEQ.addActionListener(eqButtonPressed);
  }


  @Override
  public void displayText(String text) {
    display = calculatorListener.getText();
    textField1.setText(display);
  }


}
