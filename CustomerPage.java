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
    private JComboBox discountType;
    private JComboBox type;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextField discountAmount;
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
                //Checks if any of the fields are empty
                if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || email.getText().isEmpty() || String.valueOf(type.getSelectedItem()).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Missing fields, Please fill out all the required fields");
                    return;
                } else {
                    //Fields are not empty and now cheks if customer is valued or not
                    if (!type.getSelectedItem().equals("Valued")) {
                        //Customer is not valued so calls addCustomerAccount
                        Customer customer = new Customer(firstName.getText(), lastName.getText(),
                                String.valueOf(type.getSelectedItem()), email.getText());
                        customer.setCustomerID(Customer.getLatestCustomerID() + 1);
                        Customer.addCustomerAccount(customer);
                    } else {
                        //customer is valued so adds a discount and valued account to database
                        Discount discount = new Discount(discountType.getSelectedItem().toString());
                        discount.setDiscountID(Discount.getLatestDiscountID() + 1);
                        Customer customer = new Customer(firstName.getText(), lastName.getText(),
                                String.valueOf(type.getSelectedItem()), email.getText(), discount.getDiscountID());
                        customer.setCustomerID(Customer.getLatestCustomerID() + 1);
                        Customer.addCustomerAccountValued(customer);
                        Discount.addDiscount(discount);
                        if (discountType.getSelectedItem().equals("Fixed")) {
                            //Adds a fixed discount
                            FixedDiscount fixedDiscount = new FixedDiscount(discount.getDiscountID(), Float.parseFloat(discountAmount.getText()));
                            fixedDiscount.setFixedID(FixedDiscount.getLatestFixedDiscountID() + 1);
                            FixedDiscount.addFixedDiscount(fixedDiscount);
                        }
                        if (discountType.getSelectedItem().equals("Flexible")) {
                            //Adds a flexible discount
                            FlexibleDiscount flexibleDiscount = new FlexibleDiscount(0, 15, Float.parseFloat(discountAmount.getText()), discount.getDiscountID());
                            flexibleDiscount.setFlexibleID(FlexibleDiscount.getLatestFlexibleDiscountID() + 1);
                            FlexibleDiscount.addFlexibleDiscount(flexibleDiscount);
                        }
                    }
                }
            }
        });
        /**
         * Updates customer account type
         */
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Checks if fields are empty
                if(firstName.getText().isEmpty()||lastName.getText().isEmpty()||email.getText().isEmpty()||String.valueOf(type.getSelectedItem()).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Missing fields, Please fill out all the required fields");
                    return;
                }
                Customer newCustomer = new Customer(firstName.getText(), lastName.getText(),
                        String.valueOf(type.getSelectedItem()), email.getText());
                newCustomer.setCustomerID(Customer.getThisCustomerID(newCustomer));
                Customer.updateCustomerAccount(newCustomer);
                JOptionPane.showMessageDialog(null, "Updated");
            }
        });
        /**
         * Deletes Customer from database
         */
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Checks if fields are empty
                if(firstName.getText().isEmpty()||lastName.getText().isEmpty()||email.getText().isEmpty()||String.valueOf(type.getSelectedItem()).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Missing fields, Please fill out all the required fields");
                    return;
                }
                Customer deleteCustomer = new Customer(firstName.getText(), lastName.getText(),
                        String.valueOf(type.getSelectedItem()), email.getText());
                deleteCustomer.setCustomerID(Customer.getThisCustomerID(deleteCustomer));
                Customer.deleteCustomerAccount(deleteCustomer);
                deleteCustomer = null;
                JOptionPane.showMessageDialog(null, "Deleted");
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
