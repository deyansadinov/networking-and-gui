package test3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Deyan Sadinov <sadinov88@gmail.com>
 */
public class TheCalculator extends JFrame {

  private String display = "";

  public TheCalculator() throws HeadlessException {
    this.setSize(380, 130);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Calculator");
    JPanel panel = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    final JTextField jTextField = new JTextField("", 30);

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
            display += bn.getText();
            jTextField.setText(display);
          }
        }
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
    ActionListener eqListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        display = "??????????????????????";
        jTextField.setText(display);
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

        opperatorButtons[i].addActionListener(eqListener);
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

}
