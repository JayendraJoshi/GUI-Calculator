import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame() {

        setSize(500,500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel numberPanel = getNumberPanel();
        add(numberPanel, BorderLayout.CENTER);


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

        gbc.fill = GridBagConstraints.BOTH;
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

        numberPanel.setBackground(Color.black);

        return numberPanel;
    }
}