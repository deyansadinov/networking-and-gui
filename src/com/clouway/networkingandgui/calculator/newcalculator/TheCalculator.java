package com.clouway.networkingandgui.calculator.newcalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

/**
 * @ author Ivan Genchev <ivan.genchev1989@gmail.com> 10:49 Nov 14-11-20
 */
public class TheCalculator extends JFrame {
  private String display = "";
  private final JTextField jTextField=new JTextField("", 30);

  public TheCalculator() throws HeadlessException {
    this.setSize(380, 130);
    jTextField.setEditable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Calculator");
    JPanel panel = new JPanel();
    JPanel panel2 = new JPanel();

    final JButton[] numberButtons = new JButton[10];
    ActionListener numbersListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (JButton bn : numberButtons) {
          if (e.getSource() == bn) {
            display += bn.getText();
            jTextField.setText(display);
          }
        }
      }
    };
    for (int i = 0; i < numberButtons.length; i++) {
      numberButtons[i] = new JButton(String.valueOf(i));
      numberButtons[i].addActionListener(numbersListener);
    }
    final JButton[] opperatorButtons = new JButton[8];
    ActionListener opperationsListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (JButton bn : opperatorButtons) {
          if (e.getSource() == bn) {
            if (display.length() == 0) {
              return;
            }
            if (display.endsWith("*") || display.endsWith("/") || display.endsWith("+") || display.endsWith("-") || display.endsWith(".")) {
              return;
            }
            if(doubleDot(display)){
              return;
            }
            display += bn.getText();
            jTextField.setText(display);
          }
        }
      }

      private boolean doubleDot(String display) {

          int cout = 0;
          if (display.endsWith(".")) {
            return true;
          }
          String[] dotArray = display.split("[0-9]");
          for (String x : dotArray) {
            if (x.equals(".")) {
              cout++;
            } else if (x.equals("+") || x.equals("-") || x.equals("*") || x.equals("/")) {
              cout--;
            }
          }
          if (cout >= 1) {
            return true;
          }
          return false;

      }
    };
    ActionListener removeClrearListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (JButton bn : opperatorButtons) {
          if (e.getSource() == bn && bn.getText() == "C") {
            display = "";
            jTextField.setText(display);
            return;
          } else if (e.getSource() == bn && bn.getText() == "<-") {
            String[] allDisplay = display.split("");
            allDisplay[allDisplay.length - 1] = "";
            display = "";
            for (String x : allDisplay) {
              display += x;
            }
            jTextField.setText(display);
          }
        }
      }
    };
    String[] opperators = new String[]{"+", "-", "*", "/", ".", "=", "C", "<-"};
    for (int i = 0; i < opperators.length; i++) {
      opperatorButtons[i] = new JButton(opperators[i]);
      if (!(opperatorButtons[i].getText() == "C" || opperatorButtons[i].getText() == "<-" || opperatorButtons[i].getText() == "=")) {
        opperatorButtons[i].addActionListener(opperationsListener);
      }
      if (opperatorButtons[i].getText() == "C" || opperatorButtons[i].getText() == "<-") {
        opperatorButtons[i].addActionListener(removeClrearListener);
      }
      if (opperatorButtons[i].getText() == "=") {
        ButtonEqClicked buttonEqClicked = new ButtonEqClicked();
        opperatorButtons[i].addActionListener(buttonEqClicked);
      }
    }

    panel.add(jTextField);
    for (JButton b : numberButtons) {
      panel.add(b);
    }
    for (JButton b : opperatorButtons) {
      panel2.add(b);
    }
    panel.add(panel2);
    this.add(panel);
    this.setVisible(true);
  }

  public class ButtonEqClicked implements ActionListener {
    private Stack<String> stack = new Stack<>();
    private ArrayList<String> list2 = new ArrayList<>();

    @Override
    public void actionPerformed(ActionEvent e) {
      display = eval(display);
      jTextField.setText(display);
    }

    private String eval(String display) {
      if(display.equals("") ){
        return "";
      }

      ArrayList list = getListfromString(display);
      return RPN(list);
    }

    private String RPN(ArrayList<String> list) {

      for (String x : list) {
        if ((x.equals("+") || x.equals("-") || x.equals("*") || x.equals("/"))) {
          use(stack, x);
        } else {
          list2.add(x);
        }
      }
      while (stack.size() != 0) {
        list2.add(stack.pop());
      }
      while (list2.size() != 1) {
        for (int i = 0; i < list2.size(); i++) {

          if (list2.get(i).equals("+")) {
            Double x = Double.parseDouble(list2.get(i - 2)) + Double.parseDouble(list2.get(i - 1));
            i = performMathOpperation(i, x);
          }
          if (list2.get(i).equals("-")) {
            Double x = Double.parseDouble(list2.get(i - 2)) - Double.parseDouble(list2.get(i - 1));
            i = performMathOpperation(i, x);
          }
          if (list2.get(i).equals("/")) {
            Double x = Double.parseDouble(list2.get(i - 2)) / Double.parseDouble(list2.get(i - 1));
            i = performMathOpperation(i, x);
          }
          if (list2.get(i).equals("*")) {
            Double x = Double.parseDouble(list2.get(i - 2)) * Double.parseDouble(list2.get(i - 1));
            i = performMathOpperation(i, x);
          }
        }
      }
      return list2.get(0);
    }

    private int performMathOpperation(int i, Double x) {
      list2.set(i, x.toString());
      list2.set(i - 1, "");
      list2.set(i - 2, "");
      removeEmptyElements(list2);
      i = 0;
      return i;
    }

    private void removeEmptyElements(java.util.List<String> list) {
      while (list.contains("")) {
        for (int i = 0; i < list.size(); i++) {
          if (list.get(i).equals("")) {
            list.remove(i);
          }
        }
      }
    }

    private void use(Stack<String> stack, String x) {
      if (stack.size() == 0) {
        stack.push(x);
      } else {
        if (stack.lastElement() == x) {
          list2.add(x);
          stack.pop();
          stack.push(x);
        }
        if ((stack.peek().equals("+") || stack.peek().equals("-")) && (x.equals("*") || x.equals("/"))) {
          stack.push(x);
          return;
        }

        if (((stack.peek().equals("+") || stack.peek().equals("-")) && (x.equals("+") || x.equals("-")))
                || ((stack.peek().equals("*") || stack.peek().equals("/")) && (x.equals("+") || x.equals("-")))
                || ((stack.peek().equals("*") || stack.peek().equals("/")) && (x.equals("*") || x.equals("/")))) {
          list2.add(stack.pop());
          stack.push(x);
          return;
        }

      }
    }

    private ArrayList<String> getListfromString(String display) {
      String[] opperators = display.split("[0-9]\\.[0-9]|[0-9]");
      String[] onlyNummbers = display.split("[/*\\-+]");
      ArrayList<String> result = new ArrayList<>();
      ArrayList<String> onlyOperators = new ArrayList<>();
      for (int i = 0; i < opperators.length; i++) {
        if (!opperators[i].equals("")) {
          onlyOperators.add(opperators[i]);
        }
      }
      for (int i = 0; i < onlyNummbers.length; i++) {
        result.add(onlyNummbers[i]);
        for (int j = 0; j < onlyOperators.size(); j++) {
          result.add(onlyOperators.get(j));
          onlyOperators.remove(j);
          j++;
          break;
        }
      }
      return result;
    }
  }
}
