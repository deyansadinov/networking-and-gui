package com.clouway.gui.calculator;

import com.clouway.gui.calculator.calculation.CalculationAlgorithm;
import com.clouway.gui.calculator.calculation.RPN;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by clouway on 14-11-19.
 */
public class CalculatorUI extends JFrame {

  private JPanel calculatorPanel;
  private JTextField equationField,resultField;
  private JButton btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero;
  private JButton btnMinus, btnPlus, btnMultiply, btnDivide, btnPowerOf, btnDot;
  private JButton btnEquals, btnClear;

  private final CalculationAlgorithm calcAlgorithm;
  private final EquationBuilder equationBuilder;

  public CalculatorUI(CalculationAlgorithm calcAlgorithm, EquationBuilder equationBuilder) {
    super("Calculator");
    this.calcAlgorithm = calcAlgorithm;
    this.equationBuilder = equationBuilder;

    setVisible(true);
    setContentPane(calculatorPanel);
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    pack();
    setActionListenerToButtons();
  }

  private void setActionListenerToButtons() {

    JButton[] equationButtons = {btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine,
            btnDot, btnPlus, btnMinus, btnMultiply, btnDivide, btnPowerOf};

    ActionListener equationListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JButton pressed = (JButton) e.getSource();
        String symbol = pressed.getText();
        equationBuilder.append(symbol);
        equationField.setText(equationBuilder.content());
      }
    };

    ActionListener calculateListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String equation = equationBuilder.build();
        if ("".equals(equation)) {
          return;
        }
        double result = calcAlgorithm.calculate(equation);
        resultField.setText(Double.toString(result));
      }
    };

    ActionListener clearListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        equationField.setText("");
        resultField.setText("");
        equationBuilder.clear();
      }
    };

    for (JButton each : equationButtons) {
      each.addActionListener(equationListener);
    }
    btnClear.addActionListener(clearListener);
    btnEquals.addActionListener(calculateListener);
  }

  public static void main(String[] args) {
    CalculationAlgorithm rpn = new RPN();
    EquationBuilder eBuilder = new EquationBuilder();
    new CalculatorUI(rpn, eBuilder);
  }

}
