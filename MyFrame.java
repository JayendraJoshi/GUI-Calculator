import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MyFrame extends JFrame {

    private String number1 = "";
    private String number2 = "";
    private String operator = "";
    private String result = "";
    JButton clickedbutton;
    JTextField showNumbers = new JTextField();

    Calculate calculate = new Calculate();

    public MyFrame() {

        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel calculatorPanel = getCalculatorPanel();
        add(calculatorPanel);


        setVisible(true);


    }

    private JPanel getNumberPanel() {

        JPanel numberPanel = new JPanel();
        numberPanel.setLayout(new GridBagLayout());

        JButton buttonDot = new JButton(".");
        JButton button0 = new JButton("0");
        JButton button1 = new JButton("3");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("1");
        JButton button4 = new JButton("6");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("4");
        JButton button7 = new JButton("9");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("7");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);

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
        gbc.gridy = 1;
        numberPanel.add(button5, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
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

        buttonDot.setPreferredSize(getPreferredButtonSize());
        button0.setPreferredSize(getPreferredButtonSize());
        button1.setPreferredSize(getPreferredButtonSize());
        button2.setPreferredSize(getPreferredButtonSize());
        button3.setPreferredSize(getPreferredButtonSize());
        button4.setPreferredSize(getPreferredButtonSize());
        button5.setPreferredSize(getPreferredButtonSize());
        button6.setPreferredSize(getPreferredButtonSize());
        button7.setPreferredSize(getPreferredButtonSize());
        button8.setPreferredSize(getPreferredButtonSize());
        button9.setPreferredSize(getPreferredButtonSize());

        buttonDot.addActionListener(numberButtonListener);
        button0.addActionListener(numberButtonListener);
        button1.addActionListener(numberButtonListener);
        button2.addActionListener(numberButtonListener);
        button3.addActionListener(numberButtonListener);
        button4.addActionListener(numberButtonListener);
        button5.addActionListener(numberButtonListener);
        button6.addActionListener(numberButtonListener);
        button7.addActionListener(numberButtonListener);
        button8.addActionListener(numberButtonListener);
        button9.addActionListener(numberButtonListener);


        return numberPanel;
    }

    private JPanel getDisplayPanel() {

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);

        showNumbers.setPreferredSize(new Dimension(200, 75));

        displayPanel.add(showNumbers);

        return displayPanel;

    }

    private JPanel getSideOperatorPanel() {

        JPanel SideOperatorPanel = new JPanel();
        SideOperatorPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);

        JButton ButtonAddition = new JButton("+");
        JButton ButtonSubtraction = new JButton("-");
        JButton ButtonMultiplication = new JButton("*");
        JButton ButtonEquals = new JButton("=");

        ButtonAddition.addActionListener(operatorListener);
        ButtonSubtraction.addActionListener(operatorListener);
        ButtonMultiplication.addActionListener(operatorListener);
        ButtonEquals.addActionListener(equalsButtonListener);


        ButtonAddition.setPreferredSize(getPreferredButtonSize());
        ButtonSubtraction.setPreferredSize(getPreferredButtonSize());
        ButtonMultiplication.setPreferredSize(getPreferredButtonSize());

        ButtonEquals.setPreferredSize(getPreferredButtonSize());


        gbc.gridy = 1;  // Incremented for the next button
        SideOperatorPanel.add(ButtonMultiplication, gbc);

        gbc.gridy = 2;
        SideOperatorPanel.add(ButtonSubtraction, gbc);

        gbc.gridy = 3;
        SideOperatorPanel.add(ButtonAddition, gbc);

        gbc.gridy = 4;
        SideOperatorPanel.add(ButtonEquals, gbc);

        return SideOperatorPanel;

    }

    private JPanel getUpperOperatorPanel() {
        JPanel upperOperatorPanel = new JPanel();
        upperOperatorPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);

        JButton buttonSquare = new JButton("²");
        JButton buttonSquareRoot = new JButton("√");
        JButton buttonDelete = new JButton("⌫");
        JButton buttonPlaceholder = new JButton("PlaceHolder");
        JButton buttonCE = new JButton("CE");
        JButton buttonPlusAndMinus = new JButton("+/-");
        JButton buttonPercentage = new JButton("%");
        JButton buttonDivision = new JButton("/");



        buttonDivision.addActionListener(operatorListener);

        buttonPlaceholder.setPreferredSize(getPreferredButtonSize());
        buttonSquare.setPreferredSize(getPreferredButtonSize());
        buttonSquareRoot.setPreferredSize(getPreferredButtonSize());
        buttonDelete.setPreferredSize(getPreferredButtonSize());
        buttonCE.setPreferredSize(getPreferredButtonSize());
        buttonPlusAndMinus.setPreferredSize(getPreferredButtonSize());
        buttonPercentage.setPreferredSize(getPreferredButtonSize());
        buttonDivision.setPreferredSize(getPreferredButtonSize());

        buttonDelete.addActionListener(deleteSingleDigitButtonListener);
        buttonPlusAndMinus.addActionListener(upperOperatorListener);
        buttonCE.addActionListener(upperOperatorListener);

        gbc.gridx = 0;
        gbc.gridy = 0;
        upperOperatorPanel.add(buttonSquare, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        upperOperatorPanel.add(buttonSquareRoot, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        upperOperatorPanel.add(buttonPlaceholder, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        upperOperatorPanel.add(buttonDelete, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        upperOperatorPanel.add(buttonCE, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        upperOperatorPanel.add(buttonPlusAndMinus, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        upperOperatorPanel.add(buttonPercentage, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        upperOperatorPanel.add(buttonDivision, gbc);


        return upperOperatorPanel;


    }

    private JPanel getCalculatorPanel() {
        JPanel calculatorPanel = new JPanel();
        calculatorPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        calculatorPanel.add(getDisplayPanel(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.gridheight = 2;
        calculatorPanel.add(getUpperOperatorPanel(), gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 4;
        calculatorPanel.add(getSideOperatorPanel(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.gridheight = 4;
        calculatorPanel.add(getNumberPanel(), gbc);

        return calculatorPanel;
    }

    public static Dimension getPreferredButtonSize() {

        int preferredWidth = 50;
        int preferredHeight = 50;

        return new Dimension(preferredWidth, preferredHeight);

    }

    ActionListener numberButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (number2 == "" && operator == "") {
                clickedbutton = (JButton) e.getSource();
                number1 += clickedbutton.getText();
               showNumbers.setText(number1);

            } else if (number1 != "" && operator != "") {
                clickedbutton = (JButton) e.getSource();
                number2 += clickedbutton.getText();
                showNumbers.setText(number1 + " "+operator +" "+ number2);
            }

        }
    };

    ActionListener upperOperatorListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            clickedbutton= (JButton) e.getSource();

            switch (clickedbutton.getText()){
                //Due to - being assigned to number2, number 2 is not recognized as being empty which is why no input can be added to it
                //The same issue is true for number1

                case "+/-":
                    if(calculate.hasResult(showNumbers.getText())){

                    }
                    else if(number1!="" && number2==""){
                        number1= "-" + number1;
                        showNumbers.setText(number1);
                    }
                    else if(number1!="" && operator!=""){
                        number2= "-" + number2;
                        showNumbers.setText(number1 + " "+operator +" "+ number2);
                    }
                    break;
                    case "CE":
                        reset();
                        calculate.reset();
                        showNumbers.setText("");
                        break;
            }



        }
    };
    ActionListener operatorListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if ((calculate.hasResult(showNumbers.getText()))){
                clickedbutton = (JButton) e.getSource();
                operator = clickedbutton.getText();

                String[] updatedArray = calculate.extractNumbersAndOperatorToArray(deleteLastCharacter());

                number1=updatedArray[0];
                updatedArray[1]=operator;
                number2=updatedArray[2];
                result=updatedArray[3];

                setShowNumbers(updatedArray);
            }
            else if(number2 == "" && number1 !="") {
                clickedbutton = (JButton) e.getSource();
                operator = clickedbutton.getText();
                showNumbers.setText(number1+" " + operator);

            }

        }


    };

        ActionListener deleteSingleDigitButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String[] updatedArray = calculate.extractNumbersAndOperatorToArray(deleteLastCharacter());
                //  calculate.extractValuesFromArray(updatedArray);

                operator = updatedArray[1];
                number2 = updatedArray[2];
                result = updatedArray[3];
                number1 = updatedArray[0];

                setShowNumbers(updatedArray);

                calculate.reset();

            }


        };
        ActionListener equalsButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showNumbers.getText().contains("=")) {

                    String newResult= calculate.returnResultEquation(deleteLastCharacter());
                    showNumbers.setText(newResult);
                    calculate.reset();

                } else if ((!number1.isEmpty() && !operator.isEmpty() && !number2.isEmpty())) {
                    String equation = showNumbers.getText();
                    System.out.println(equation + " equation");
                    calculate.reset();
                    String showResult = calculate.returnResultEquation(equation);
                    showNumbers.setText(showResult);
                    calculate.reset();
                }
            }
        };

        private void reset() {

            number1 = "";
            number2 = "";
            operator = "";
            result="";
        }

        private String deleteLastCharacter() {
            String currentText = showNumbers.getText();
            Pattern pattern = Pattern.compile("=\\s*(-?[0-9]+(\\.[0-9]+)?)");
            Matcher matcher = pattern.matcher(currentText);

            if (!currentText.isEmpty()) {
                if (matcher.find()) {
                    currentText = matcher.group(1);
                } else if (currentText.charAt(currentText.length() - 1) == ' ') {
                    currentText = currentText.substring(0, currentText.length() - 2);
                } else {
                    currentText = currentText.substring(0, currentText.length() - 1);
                }
            }
            else{
                reset();
                calculate.reset();
            }
            System.out.println(currentText + " currentText");
            return currentText;
        }

        private void setShowNumbers(String[] updatedArray) {
            if (!updatedArray[3].isEmpty()) {
                showNumbers.setText(number1 + " " + operator + " " + number2 + " = " + result);
            }
            if (!updatedArray[2].isEmpty()) {
                showNumbers.setText(number1 + " " + operator + " " + number2);
            } else if (!updatedArray[1].isEmpty()) {
                showNumbers.setText((number1 + " " + operator));
            } else if (!updatedArray[0].isEmpty()) {
                showNumbers.setText((number1));
            } else {
                showNumbers.setText("");
            }

        }

}