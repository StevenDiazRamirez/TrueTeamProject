import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerPage extends JFrame {
    private JPanel customerPanel;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField email;
    private JButton addButton;
    private JButton backButton;
    private JComboBox discount;
    private JComboBox type;
    private JButton updateButton;
    private JButton deleteButton;
    private JFrame customerFrame;

    public CustomerPage() {
        createPage();
    }

    public void createPage() {
        customerFrame = new JFrame("Innovotype");
        customerFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        customerFrame.setPreferredSize(new Dimension(500,500));

        customerFrame.add(customerPanel);
        customerFrame.pack();
        customerFrame.setLocationRelativeTo(null);
        customerFrame.setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(firstName.getText().isEmpty()||lastName.getText().isEmpty()||email.getText().isEmpty()||String.valueOf(type.getSelectedItem()).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Missing fields, Please fill out all the required fields");
                    return;
                }
                Customer customer = new Customer(firstName.getText(), lastName.getText(),
                        String.valueOf(type.getSelectedItem()), email.getText());
                customer.setCustomerID(Customer.getLatestCustomerID() + 1);
                Customer.addCustomerAccount(customer);
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(firstName.getText().isEmpty()||lastName.getText().isEmpty()||email.getText().isEmpty()||String.valueOf(type.getSelectedItem()).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Missing fields, Please fill out all the required fields");
                    return;
                }
                Customer newCustomer = new Customer(firstName.getText(), lastName.getText(),
                        String.valueOf(type.getSelectedItem()), email.getText());
                newCustomer.setCustomerID(Customer.getThisCustomerID(newCustomer));
                Customer.updateCustomerAccount(newCustomer);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(firstName.getText().isEmpty()||lastName.getText().isEmpty()||email.getText().isEmpty()||String.valueOf(type.getSelectedItem()).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Missing fields, Please fill out all the required fields");
                    return;
                }
                Customer deleteCustomer = new Customer(firstName.getText(), lastName.getText(),
                        String.valueOf(type.getSelectedItem()), email.getText());
                deleteCustomer.setCustomerID(Customer.getThisCustomerID(deleteCustomer));
                System.out.println(deleteCustomer.getCustomerID());
                Customer.deleteCustomerAccount(deleteCustomer);
                deleteCustomer = null;
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
}
