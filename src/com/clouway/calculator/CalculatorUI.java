package com.clouway.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Tihomir Kehayov <kehayov89@gmail.com>
 */
public class CalculatorUI implements ActionListener {
  private JTextField textField;
  private StringBuilder content = new StringBuilder();
  private boolean isSign = false;
  private String[] logicalSymbols = {"+", "-", "*", "/", "=", ".", "CE", "C"};


  public void showUi() {

    JFrame frame = new JFrame("Super Calculator");
    frame.setPreferredSize(new Dimension(600, 600));

    textField = new JTextField();
    textField.setEditable(false);
    frame.add(textField);

    addNumberButtonsToPane(frame.getContentPane());
    addLogicalButtonsToPane(frame.getContentPane());
    frame.pack();
    frame.setVisible(true);
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
  public void actionPerformed(ActionEvent e) {
    String buttonContent = e.getActionCommand();

    if (!isSign) {
      content.append(getContent(buttonContent));
      textField.setText(content.toString());
    }
    if (!isDigit(buttonContent)) {
      isSign = true;

    } else {
      isSign = false;

    }
    if (canCalculate(buttonContent)) {

      Calculator calculator = new Calculator(content.toString());

      Double calculated = null;
      try {
        calculated = calculator.calculate();
      } catch (Exception e1) {
        JOptionPane.showMessageDialog(null, "error");
        return;
      }
      content.setLength(0);
      content.append(calculated.toString());

      textField.setText(content.toString());
    }
    if (buttonContent.equals("CE")) {
      content.setLength(0);
      textField.setText(content.toString());
    }
    if (buttonContent.equals("C")) {
      content.deleteCharAt(content.length() - 1);
      textField.setText(content.toString());
    }
  }

  private boolean canCalculate(String buttonContent) {
    return buttonContent.equals("=") && !isEmptyContent();
  }

  private boolean isEmptyContent() {
    return content.toString().isEmpty();
  }

  private boolean isDigit(String content) {
    try {
      Double.parseDouble(content);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  private String getContent(String content) {
    StringBuilder buttonContent = new StringBuilder();
    if (!content.contains("=")) {
      buttonContent.append(content);
    }
    return buttonContent.toString();
  }
}
