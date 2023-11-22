import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyFrame extends JFrame implements MouseListener {

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

        return numberPanel;
    }

    private JPanel getDisplayPanel(){

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets= new Insets(0,0,0,0);

        JTextField showNumbers = new JTextField();
        showNumbers.setPreferredSize(new Dimension(200,75));

        displayPanel.add(showNumbers);

        return displayPanel;

    }

    private JPanel getSideOperatorPanel(){

        JPanel SideOperatorPanel = new JPanel();
        SideOperatorPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,0,0);

        JButton addition = new JButton("+");
        JButton subtraction = new JButton("-");
        JButton multiplication = new JButton("*");
        JButton equals = new JButton("=");


        addition.setPreferredSize(getPreferredButtonSize());
        subtraction.setPreferredSize(getPreferredButtonSize());
        multiplication.setPreferredSize(getPreferredButtonSize());

        equals.setPreferredSize(getPreferredButtonSize());


        gbc.gridy = 1;  // Incremented for the next button
        SideOperatorPanel.add(multiplication, gbc);

        gbc.gridy = 2;
        SideOperatorPanel.add(subtraction, gbc);

        gbc.gridy = 3;
        SideOperatorPanel.add(addition, gbc);

        gbc.gridy = 4;
        SideOperatorPanel.add(equals, gbc);

        return SideOperatorPanel;

    }
private JPanel getUpperOperatorPanel(){
        JPanel upperOperatorPanel = new JPanel();
        upperOperatorPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(0,0,0,0);

        JButton buttonSquare= new JButton("²");
        JButton buttonSquareRoot = new JButton("√");
        JButton buttonDelete = new JButton("⌫");
        JButton buttonPlaceholder = new JButton("PlaceHolder");
        JButton buttonCE = new JButton("CE");
        JButton buttonPlusAndMinus = new JButton("+/-");
        JButton buttonPercentage = new JButton("%");
        JButton division = new JButton("/");

        buttonPlaceholder.setPreferredSize(getPreferredButtonSize());
        buttonSquare.setPreferredSize(getPreferredButtonSize());
        buttonSquareRoot.setPreferredSize(getPreferredButtonSize());
        buttonDelete.setPreferredSize(getPreferredButtonSize());
        buttonCE.setPreferredSize(getPreferredButtonSize());
        buttonPlusAndMinus.setPreferredSize(getPreferredButtonSize());
        buttonPercentage.setPreferredSize(getPreferredButtonSize());
        division.setPreferredSize(getPreferredButtonSize());

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

    gbc.gridx=3;
    gbc.gridy=1;
    upperOperatorPanel.add(division,gbc);



        return upperOperatorPanel;


}

    private JPanel getCalculatorPanel(){
        JPanel calculatorPanel = new JPanel();
        calculatorPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        calculatorPanel.add(getDisplayPanel(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.gridheight=2;
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
public static Dimension getPreferredButtonSize(){

        int preferredWidth=50;
        int preferredHeight=50;

        return new Dimension(preferredWidth,preferredHeight);

}
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}