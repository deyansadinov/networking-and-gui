package com.clouway.calculatortdd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class CalculatorUi implements Display, ActionListener {
  private CalculatorListener calculatorListener;
  private String[] logicalSymbols = {"+", "-", "*", "/", "=", ".", "CE", "C"};
  private JTextField textField;
  private StringBuilder textToAppend = new StringBuilder();

  public void onDigitClicked(String digit) {
    calculatorListener.onDigitClicked(digit);
  }

  public void showUi(CalculatorListener calculatorListener) {
    this.calculatorListener = calculatorListener;
    JFrame frame = new JFrame("Super Calculator");
    frame.setPreferredSize(new Dimension(600, 600));

    textField = new JTextField();
    textField.setEditable(false);
    frame.add(textField);

    addNumberButtonsToPane(frame.getContentPane());
    addLogicalButtonsToPane(frame.getContentPane());

    frame.setVisible(true);
    frame.pack();
  }

  private void addNumberButtonsToPane(Container pane) {
    pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

    for (Integer index = 0; index <= 9; index++) {
      JButton button = new JButton(index.toString());
      button.addActionListener(this);
      pane.add(button);
    }
  }

  private void addLogicalButtonsToPane(Container pane) {
    for (int i = 0; i < logicalSymbols.length; i++) {
      JButton button = new JButton(logicalSymbols[i]);
      button.addActionListener(this);
      pane.add(button);
    }
  }

  @Override
  public void showText(String text) {
    text.concat(text);
    textField.setText(text);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String buttonContent = e.getActionCommand();
    if (isDigit(buttonContent)) {
      textToAppend.append(buttonContent);
      calculatorListener.onDigitClicked(buttonContent);
    }
    if (buttonContent.equals("=")) {
      calculatorListener.calculate();
      return;
    }
    if (!isDigit(buttonContent)) {
      textToAppend.append(buttonContent);
      calculatorListener.onOperationClicked(buttonContent);
    }
  }

  private boolean isDigit(String text) {
    try {
      Double.parseDouble(text);
      return true;
    } catch (NumberFormatException be) {
      return false;
    }
  }
}
