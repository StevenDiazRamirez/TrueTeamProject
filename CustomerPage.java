import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerPage extends JFrame {
    private JPanel customerPanel;
    private JButton createAccountButton;
    private JButton editAccountButton;
    private JButton backButton;
    private JFrame customerFrame;

    public CustomerPage() {
        createCustomerPage();
    }

    public void createCustomerPage() {
        customerFrame = new JFrame("Innovotype");
        customerFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        customerFrame.setPreferredSize(new Dimension(500,500));

        customerFrame.add(customerPanel);
        customerFrame.pack();
        customerFrame.setLocationRelativeTo(null);
        customerFrame.setVisible(true);

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCustomerPage addCustomerPage = new AddCustomerPage();
                addCustomerPage.setVisible(false);
                customerFrame.dispose();
            }
        });
        editAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCustomerPage editCustomerPage = new EditCustomerPage();
                editCustomerPage.setVisible(false);
                customerFrame.dispose();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.createMainPage(MainPage.getProfile());
                mainPage.setVisible(false);
                customerFrame.dispose();
            }
        });

    }

    public JPanel getCustomerPanel() {
        return customerPanel;
    }
}
