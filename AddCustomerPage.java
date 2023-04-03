import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomerPage extends JFrame {
    private JPanel addCustomerPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton confirmButton;
    private JButton mainMenuButton;
    private JButton backButton;
    private JFrame addCustomerFrame;

    public AddCustomerPage() {
        createPage();
    }

    public void createPage() {
        addCustomerFrame = new JFrame("Innovotype");
        addCustomerFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        addCustomerFrame.setPreferredSize(new Dimension(500,500));

        addCustomerFrame.add(addCustomerPanel);
        addCustomerFrame.pack();
        addCustomerFrame.setLocationRelativeTo(null);
        addCustomerFrame.setVisible(true);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.setVisible(false);
                addCustomerFrame.dispose();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerPage customerPage = new CustomerPage();
                customerPage.setVisible(false);
                addCustomerFrame.dispose();
            }
        });
    }
}
