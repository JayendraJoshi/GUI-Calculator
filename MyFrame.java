import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame() {

        setSize(1000,1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel calculatorPanel = getCalculatorPanel();
        add(calculatorPanel);



    setVisible(true);


    }
    private JPanel getNumberPanel(){

        JPanel numberPanel = new JPanel();
        numberPanel.setLayout(new GridBagLayout());

        JButton buttonDot = new JButton(",");
        JButton button0 = new JButton("0");
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,0,0);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; // Make buttons expand horizontally
        gbc.weighty = 1.0; // Make buttons expand vertically
        gbc.gridx = 0;
        gbc.gridy = 0;
        numberPanel.add(button9, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        numberPanel.add(button8, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        numberPanel.add(button7, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        numberPanel.add(button6, gbc);

        gbc.gridx = 1;
        gbc.gridy=1;
        numberPanel.add(button5, gbc);

        gbc.gridx = 2;
        gbc.gridy=1;
        numberPanel.add(button4, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        numberPanel.add(button3, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        numberPanel.add(button2, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        numberPanel.add(button1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // This will make the button0 span two columns
        numberPanel.add(button0, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1; // Reset to default
        numberPanel.add(buttonDot, gbc);

        buttonDot.setPreferredSize(new Dimension(100, 100));
        button0.setPreferredSize(new Dimension(100, 100));
        button1.setPreferredSize(new Dimension(100, 100));
        button2.setPreferredSize(new Dimension(100, 100));
        button3.setPreferredSize(new Dimension(100, 100));
        button4.setPreferredSize(new Dimension(100, 100));
        button5.setPreferredSize(new Dimension(100, 100));
        button6.setPreferredSize(new Dimension(100, 100));
        button7.setPreferredSize(new Dimension(100, 100));
        button8.setPreferredSize(new Dimension(100, 100));
        button9.setPreferredSize(new Dimension(100, 100));

        return numberPanel;
    }

    private JPanel getDisplayPanel(){

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets= new Insets(0,0,0,0);

        JTextField showNumbers = new JTextField();
        showNumbers.setPreferredSize(new Dimension(400,100));

        displayPanel.add(showNumbers);

        return displayPanel;

    }

    private JPanel getOperatorPanel(){

        JPanel operatorPanel = new JPanel();
        operatorPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        JButton addition = new JButton("+");
        JButton subtraction = new JButton("-");
        JButton multiplication = new JButton("*");
        JButton division = new JButton("/");
        JButton equals = new JButton("=");


        addition.setPreferredSize(new Dimension(100,100));
        subtraction.setPreferredSize(new Dimension(100,100));
        multiplication.setPreferredSize(new Dimension(100,100));
        division.setPreferredSize(new Dimension(100,100));
        equals.setPreferredSize(new Dimension(100,100));


        gbc.gridx = 0;
        gbc.gridy = 0;
        operatorPanel.add(division, gbc);

        gbc.gridy = 1;  // Incremented for the next button
        operatorPanel.add(multiplication, gbc);

        gbc.gridy = 2;
        operatorPanel.add(subtraction, gbc);

        gbc.gridy = 3;
        operatorPanel.add(addition, gbc);

        gbc.gridy = 4;
        operatorPanel.add(equals, gbc);

        return operatorPanel;



    }
private JPanel getAdvancedOperatorsPanel(){
        JPanel advancedOperatorPanel = new JPanel();
        advancedOperatorPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        JButton buttonAC = new JButton("AC");
        JButton buttonPlusAndMinus = new JButton("+/-");
        JButton buttonPercentage = new JButton("%");

        buttonAC.setPreferredSize(new Dimension(100,100));
        buttonPlusAndMinus.setPreferredSize(new Dimension(100,100));
        buttonPercentage.setPreferredSize(new Dimension(100,100));
        gbc.gridx=0;
        gbc.gridy=0;
        advancedOperatorPanel.add(buttonAC);

        gbc.gridx=1;
        advancedOperatorPanel.add(buttonPlusAndMinus);

        gbc.gridx=2;
        advancedOperatorPanel.add(buttonPercentage);

        return advancedOperatorPanel;


}

    private JPanel getCalculatorPanel(){
        JPanel calculatorPanel = new JPanel();
        calculatorPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        calculatorPanel.add(getDisplayPanel(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        calculatorPanel.add(getAdvancedOperatorsPanel(), gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 4;
        calculatorPanel.add(getOperatorPanel(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.gridheight = 4;
        calculatorPanel.add(getNumberPanel(), gbc);

        return calculatorPanel;
    }
}