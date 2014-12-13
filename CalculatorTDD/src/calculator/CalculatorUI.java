package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CalculatorUI extends JFrame implements Display{

  class NuberHandler implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {

      JButton src = (JButton) e.getSource();

      calculator.numberPressed(src.getText());
      //calculatorListener.operatorPressed(src.getText());
      //System.out.println(src.getText());

      /*for (JButton button : buttons) {
        if (e.getSource() == button) {
          calculatorListener.numberPressed(button.getText());
        }
      }*/
    }
  }
  class OperatorHandler implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {

      JButton src = (JButton) e.getSource();
      calculator.operatorPressed(src.getText());
    }
  }
  class CleanHandler implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      calculator.cleanPressed();
    }
  }
  class DecimalHandler implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      calculator.decimalPressed();
    }
  }
  class EaqualHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      calculator.eaqualsPressed();
    }
  }

  private final JTextField textField = new JTextField(15);
  private final JButton decimalButton = new JButton(".");
  private final JButton eaqualsButton = new JButton("=");
  private final JButton cleanButton = new JButton("C");

  private final JButton[] buttons = { new JButton("1"), new JButton("2"), new JButton("3"), new JButton("4"),
                                      new JButton("5"), new JButton("6"), new JButton("7"), new JButton("8"),
                                      new JButton("9"), new JButton("0")};

  private final JButton[] operators = {new JButton("+"), new JButton("-"), new JButton("*"), new JButton("/")};

  private Calculator calculator;



  public CalculatorUI() {

    super("CALCULATOR");

    initFrame();
    setButtonBounds();
    addButtonsInFrame();
    addHandlers();


  }

  private void  addHandlers() {

    NuberHandler nuberHandler = new NuberHandler();
    OperatorHandler operatorHandler =  new OperatorHandler();
    CleanHandler cleanHandler = new CleanHandler();
    DecimalHandler decimalHandler = new DecimalHandler();
    EaqualHandler eaqualHandler = new EaqualHandler();

    for (JButton button : buttons){
      button.addActionListener(nuberHandler);
    }
    for (JButton button : operators){
      button.addActionListener(operatorHandler);
    }
    cleanButton.addActionListener(cleanHandler);
    decimalButton.addActionListener(decimalHandler);
    eaqualsButton.addActionListener(eaqualHandler);
  }

  public void   initFrame() {

    setLayout(null);
    setBounds(800, 300, 240, 270);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

  }

  private void  addButtonsInFrame() {
    for (JButton button : buttons) {
      add(button);
    }
    for (JButton button : operators) {
      add(button);
    }
    add(eaqualsButton);
    add(decimalButton);
    add(cleanButton);
    add(textField);
    textField.setEditable(false);
    textField.setText("");

  }

  private void  setButtonBounds() {
    buttons[0].setBounds(12, 112, 42, 25);
    buttons[1].setBounds(66, 112, 42, 25);
    buttons[2].setBounds(120, 112, 42, 25);
    buttons[3].setBounds(12, 146, 42, 25);
    buttons[4].setBounds(66, 146, 42, 25);
    buttons[5].setBounds(120, 146, 42, 25);
    buttons[6].setBounds(12, 183, 42, 25);
    buttons[7].setBounds(66, 183, 42, 25);
    buttons[8].setBounds(120, 183, 42, 25);
    buttons[9].setBounds(174,183,53,25);
    operators[0].setBounds(174, 110, 53, 60);
    operators[1].setBounds(174, 222, 53, 23);
    operators[2].setBounds(66, 220, 42, 25);
    operators[3].setBounds(120, 220, 42, 25);
    decimalButton.setBounds(12, 220, 42, 25);
    eaqualsButton.setBounds(83, 75, 144, 25);
    cleanButton.setBounds(12, 75, 59, 25);
    textField.setBounds(12, 12, 215, 50);
  }

  public void   setVisibility(Calculator calculator) {

    this.calculator = calculator;
    //this.calculatorListener = calculatorListener;

  }

  @Override
  public void   displayText(String text) {

    textField.setText(text);

  }
}