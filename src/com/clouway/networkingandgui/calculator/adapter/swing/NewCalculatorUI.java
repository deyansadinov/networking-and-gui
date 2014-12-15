package com.clouway.networkingandgui.calculator.adapter.swing;

import com.clouway.networkingandgui.calculator.core.Display;
import com.clouway.networkingandgui.calculator.core.TheCalculatorListener;

import javax.swing.*;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 15:56 Nov 14-11-19
 */
public class NewCalculatorUI extends JFrame implements Display {
  private JTextField textField1, textField2;
  private JButton button7, button8, button9, button4, button5, button6, button1, button2, button3, button0;
  private JButton buttonDev, buttonMulty, buttonSub, buttonPlus, buttonDot;
  private JButton buttonClear, buttonRemove, buttonEq;
  private JPanel panel;
  private JButton[] numberButtons = new JButton[]{button0, button1, button2, button3, button4, button5, button6, button7, button8, button9,};
  private JButton[] opperatorsButtons = new JButton[]{buttonDev, buttonMulty, buttonPlus, buttonSub};
  private JButton[] clearOrRemoveButtons = new JButton[]{buttonRemove, buttonClear, buttonEq};
  private String display = "";

  private TheCalculatorListener theCalculatorListener;

  public void init(TheCalculatorListener listener) {
    this.theCalculatorListener = listener;
    this.setSize(500, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    textField1.setEnabled(false);
    this.add(panel);
    this.setVisible(true);
//    ActionListener numbersListener = new ActionListener() {
//      @Override
//      public void actionPerformed(ActionEvent e) {
//        for (JButton button : numberButtons) {
//          if (e.getSource() == button) {
//            calculatorListener.onNumberPressed(button.displayText());
//          }
//        }
//      }
//    };
//    for (JButton button : numberButtons) {
//      button.addActionListener(numbersListener);
//    }
//    ActionListener opperatorListener = new ActionListener() {
//      @Override
//      public void actionPerformed(ActionEvent e) {
//        for (JButton opperator : opperatorsButtons) {
//          if (opperator == e.getSource()) {
//            if (display.endsWith("+") || display.endsWith("-") || display.endsWith("*") || display.endsWith("/")) {
//              return;
//            }
//            if (display.length() == 0) {
//              return;
//            }
//            if (display.endsWith(".")) {
//              return;
//            }
//            display += opperator.displayText();
//            textField1.setText(display);
//          }
//        }
//      }
//    };
//    for (JButton opperator : opperatorsButtons) {
//      opperator.addActionListener(opperatorListener);
//    }
//    ActionListener dotClicked = new ActionListener() {
//      @Override
//      public void actionPerformed(ActionEvent e) {
//        if (doubleDot(display)) {
//          return;
//        }
//        display += buttonDot.displayText();
//        textField1.setText(display);
//      }
//
//      private boolean doubleDot(String display) {
//        int cout = 0;
//        if (display.endsWith(".")) {
//          return true;
//        }
//        String[] dotArray = display.split("[0-9]");
//        for (String x : dotArray) {
//          if (x.equals(".")) {
//            cout++;
//          } else if (x.equals("+") || x.equals("-") || x.equals("*") || x.equals("/")) {
//            cout--;
//          }
//        }
//        if (cout >= 1) {
//          return true;
//        }
//        return false;
//      }
//    };
//    buttonDot.addActionListener(dotClicked);
//    ActionListener removeClearEqListener = new ActionListener() {
//      @Override
//      public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == buttonClear) {
//          display = "";
//          textField1.setText(display);
//        } else if (e.getSource() == buttonRemove) {
//          String[] allDisplay = display.split("");
//          allDisplay[allDisplay.length - 1] = "";
//          display = "";
//          for (String x : allDisplay) {
//            display += x;
//          }
//          textField1.setText(display);
//        } else if (e.getSource() == buttonEq) {
//          if (display.equals("")) {
//            return;
//          }
//          ScriptEngineManager scm = new ScriptEngineManager();
//          ScriptEngine jsEngine = scm.getEngineByName("JavaScript");
//          if (display.endsWith("+") || display.endsWith("-") || display.endsWith(".")) {
//            textField1.setText(display += 0);
//          }
//          if (display.endsWith("*") || display.endsWith("/")) {
//            textField1.setText(display += 1);
//          }
//          try {
//            textField2.setText(jsEngine.eval(display).toString());
//          } catch (ScriptException e1) {
//            e1.printStackTrace();
//          }
//        }
//      }
//    };
//    for (JButton opperator : clearOrRemoveButtons) {
//      opperator.addActionListener(removeClearEqListener);
//    }
  }

  @Override
  public void displayText(String text) {
    textField2.setText(text);
  }
}
