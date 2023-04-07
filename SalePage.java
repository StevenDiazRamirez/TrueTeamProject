import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SalePage extends JFrame  {
    private JPanel salePanel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JButton confirmButton;
    private JButton voidButton;
    private JTextField amountField;
    private JButton backButton;
    private JFormattedTextField dateField;
    private JFrame saleFrame;
    private DateFormat dateFormat;

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
