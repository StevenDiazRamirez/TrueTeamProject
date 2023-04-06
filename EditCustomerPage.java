import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditCustomerPage extends JFrame {
    private JComboBox comboBox1;
    private JPanel editCustomerPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton backButton;
    private JButton mainMenuButton;
    private JFrame editCustomerFrame;

    public EditCustomerPage() {
        createPage();
    }

    public void createPage() {
        editCustomerFrame = new JFrame("Innovotype");
        editCustomerFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        editCustomerFrame.setPreferredSize(new Dimension(500,500));

        editCustomerFrame.add(editCustomerPanel);
        editCustomerFrame.pack();
        editCustomerFrame.setLocationRelativeTo(null);
        editCustomerFrame.setVisible(true);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerPage customerPage = new CustomerPage();
                customerPage.setVisible(false);
                editCustomerFrame.dispose();
            }
        });
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.createMainPage(MainPage.getProfile());
                mainPage.setVisible(false);
                editCustomerFrame.dispose();
            }
        });
    }
}
