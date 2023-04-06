import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalePage extends JFrame {
    private JPanel salePanel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JButton confirmButton;
    private JButton voidButton;
    private JTextField textField1;
    private JTextField textField2;
    private JButton backButton;
    private JFrame saleFrame;

    public SalePage() {
        createSalePage();
    }

    public void createSalePage() {
        saleFrame = new JFrame("Innovotype");
        saleFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        saleFrame.setPreferredSize(new Dimension(500,500));

        saleFrame.add(salePanel);
        saleFrame.pack();
        saleFrame.setLocationRelativeTo(null);
        saleFrame.setVisible(true);

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        comboBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        comboBox4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        voidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.createMainPage(MainPage.getProfile());
                mainPage.setVisible(false);
                saleFrame.dispose();
            }
        });
    }

}
