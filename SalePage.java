import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SalePage extends JFrame  {
    private JPanel salePanel;
    private JComboBox blankType;
    private JComboBox paymentType;
    private JComboBox flightType;
    private JButton confirmButton;
    private JButton voidButton;
    private JTextField amount;
    private JButton backButton;
    private JTextField customerEmail;
    private JButton validateButton;
    private JTextField exchangeRate;
    private JButton payLaterButton;
    private JTextField commissionField;
    private JButton getCommissionButton;
    private JTextField taxes;
    private JTextField cardDetails;
    private JButton getDiscountButton;
    private JFrame saleFrame;
    private JPanel panel;

    private Date lateDate;
    private Float discountAmount;
    private float discountPrice;
    private boolean allFieldsFilled;

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

        getBlanks();

        getDiscountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCustomerDiscount();
                discountPrice = Float.parseFloat(amount.getText()) * (1-discountAmount/100);
                amount.setText(String.valueOf(discountPrice));
            }
        });
        getCommissionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCommissions();
            }
        });
        payLaterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createLatePayPanel();
            }
        });
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createValid();

            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int blankID = getSelectedBlankID();
                int employeeID = MainPage.getProfile().getEmployeeID();
                int customerID = getSelectedCustomerID();
                int commissionID = getSelectedCommissionID();

               System.out.println(allFieldsFilled);

                if (allFieldsFilled) {

                    Blank.changeStatusSold(blankID, Integer.parseInt(blankType.getSelectedItem().toString()));

                    if (paymentType.getSelectedItem().equals("CASH") && flightType.getSelectedItem().equals("DOMESTIC")) {
                        Sale.addNewCashSaleDomestic(Integer.parseInt(blankType.getSelectedItem().toString()), Float.parseFloat(amount.getText()),
                                Float.parseFloat(taxes.getText()), lateDate, blankID, employeeID, customerID, commissionID);
                    }
                    if (paymentType.getSelectedItem().equals("CARD") && flightType.getSelectedItem().equals("DOMESTIC")) {
                        Sale.addNewCardSaleDomestic(Integer.parseInt(blankType.getSelectedItem().toString()), Float.parseFloat(amount.getText()),
                                Float.parseFloat(taxes.getText()), lateDate, blankID, employeeID, customerID, commissionID, Integer.parseInt(cardDetails.getText()));
                    }
                    if (paymentType.getSelectedItem().equals("CASH") && flightType.getSelectedItem().equals("GLOBAL")) {
                        Sale.addNewCashSaleGlobal(Integer.parseInt(blankType.getSelectedItem().toString()), Float.parseFloat(amount.getText()),
                                Float.parseFloat(exchangeRate.getText()), Float.parseFloat(taxes.getText()), lateDate, blankID, employeeID, customerID, commissionID);
                    }
                    if (paymentType.getSelectedItem().equals("CARD") && flightType.getSelectedItem().equals("GLOBAL")) {
                        Sale.addNewCardSaleGlobal(Integer.parseInt(blankType.getSelectedItem().toString()), Float.parseFloat(amount.getText()),
                                Float.parseFloat(exchangeRate.getText()), Float.parseFloat(taxes.getText()), lateDate, blankID, employeeID, customerID, commissionID,
                                Integer.parseInt(cardDetails.getText()));
                    }
                } else JOptionPane.showMessageDialog(null, "Blank is not Valid");
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

    public void createValid() {
        int numFields = 0;

        switch (Integer.parseInt(blankType.getSelectedItem().toString())) {
            case 444:
                numFields = 4;
                break;
            case 440:
                numFields = 4;
                break;
            case 420:
                numFields = 2;
                break;
            case 201:
                numFields = 2;
                break;
            case 101:
                numFields = 1;
                break;
        }

        panel = new JPanel();
        ArrayList<JTextField> coupons = new ArrayList<JTextField>();
        for (int i = 0; i < numFields; i++) {
            JTextField textField = new JTextField(5);
            panel.add(textField);
            coupons.add(textField);
        }

        int option = JOptionPane.showConfirmDialog(null, panel, "Coupons", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            if (blankType.getSelectedItem().equals(444) || blankType.getSelectedItem().equals(440)) {
                Coupon coupon = new Coupon("Flight", Integer.parseInt(blankType.getSelectedItem().toString()), getSelectedBlankID());
                coupon.setDestFrom1(coupons.get(0).getText());
                coupon.setDestTo1(coupons.get(1).getText());
                coupon.setDestFrom2(coupons.get(2).getText());
                coupon.setDestTo2(coupons.get(3).getText());
                coupon.setCouponID(Coupon.getLatestCustomerID() + 1);
                Coupon.addCoupons(coupon);
            } else if (blankType.getSelectedItem().equals(420) || blankType.getSelectedItem().equals(201)) {
                Coupon coupon = new Coupon("Flight", Integer.parseInt(blankType.getSelectedItem().toString()), getSelectedBlankID());
                coupon.setDestFrom1(coupons.get(0).getText());
                coupon.setDestTo1(coupons.get(1).getText());
                coupon.setCouponID(Coupon.getLatestCustomerID() + 1);
                Coupon.addCoupons(coupon);

            } else if (blankType.getSelectedItem().equals(101)) {
                Coupon coupon = new Coupon("Flight", Integer.parseInt(blankType.getSelectedItem().toString()), getSelectedBlankID());
                coupon.setDestTo1(coupons.get(0).getText());
                coupon.setCouponID(Coupon.getLatestCustomerID() + 1);
                Coupon.addCoupons(coupon);
            }

            allFieldsFilled = true;
            for (JTextField textField : coupons) {
                if (textField.getText().isEmpty()) {
                    allFieldsFilled = false;
                    break;
                }
            }
        }
    }

    public void getCustomerDiscount() {
        if (Sale.getCustomerType(customerEmail.getText()).equals("Valued")) {
            String query = null;
            if (getDiscountType().equals("Fixed")) {
                query = "SELECT amount from fixeddiscount f, discount d WHERE d.DiscountID = f.fixedDiscountID AND d.DiscountID = " + getDiscountID();
            } else if (getDiscountType().equals("Flexible")) {
                query = "SELECT discountAmount from flexiblediscount f, discount d WHERE d.DiscountID = f.flexibleDiscountID AND d.DiscountID = " + getDiscountID();
            }
            try {
                Connection con = DBSConnection.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(query);
                while (rs.next()) {
                    discountAmount = rs.getFloat(1);
                }
                JOptionPane.showMessageDialog(null, "Discount Added!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getDiscountType() {
        String type = "None";
        try {
            Connection con = DBSConnection.getConnection();
            Statement stm = con.createStatement();
            String query = "SELECT DiscountType from discount d, customeraccount c WHERE d.DiscountID = c.DiscountIDCustomer AND c.CustomerEmail = " + "'" + customerEmail.getText() + "'";
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                type = rs.getString(1);
            }
            return type;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return type;
    }

    public int getDiscountID() {
        try {
            Connection con = DBSConnection.getConnection();
            Statement stm = con.createStatement();
            String query = "SELECT DiscountIDCustomer from discount d, customeraccount c WHERE d.DiscountID = c.DiscountIDCustomer AND c.CustomerEmail = " + "'" + customerEmail.getText() + "'";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void createLatePayPanel() {
        if (Sale.getCustomerType(customerEmail.getText()).equals("Regular") || Sale.getCustomerType(customerEmail.getText()).equals("Valued")) {
            panel = new JPanel();
            JTextField latePayDate = new JTextField(5);
            panel.add(latePayDate);

            int option = JOptionPane.showConfirmDialog(null, panel, "LatePayment", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    lateDate = dateFormat.parse(latePayDate.getText());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } else JOptionPane.showMessageDialog(null, "Customer not allowed");
    }

    public void getBlanks() {
        try {
            Connection con = DBSConnection.getConnection();
            Statement stm = con.createStatement();
            String query = "SELECT blankType FROM ats.blanks WHERE employeeID = " + MainPage.getProfile().getEmployeeID() +
                    " AND status = 'Assigned'";
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                int blank = rs.getInt(1);
                blankType.addItem(blank);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getCommissions() {
        try {
            Connection con = DBSConnection.getConnection();
            Statement stm = con.createStatement();
            String query = "SELECT amount FROM commission WHERE blankType = " + blankType.getSelectedItem();
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                int commission = rs.getInt(1);
                commissionField.setText(String.valueOf(commission));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getSelectedCommissionID() {
        try {
            Connection con = DBSConnection.getConnection();
            Statement stm = con.createStatement();
            String query = "SELECT CommissionID FROM ats.commission WHERE amount = " +
                    Integer.parseInt(commissionField.getText()) + " AND blankType = " + blankType.getSelectedItem();
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getSelectedBlankID() {
        try {
            Connection con = DBSConnection.getConnection();
            Statement stm = con.createStatement();
            String query = "SELECT MIN(blankID) FROM ats.blanks WHERE employeeID = " + MainPage.getProfile().getEmployeeID() +
                    " AND blankType = " + blankType.getSelectedItem() + " AND status = 'Assigned'";
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getSelectedCustomerID() {
        try {
            Connection con = DBSConnection.getConnection();
            String query = "SELECT CustomerID from customeraccount WHERE CustomerEmail = " + "'" + customerEmail.getText() + "'";
            PreparedStatement stm = con.prepareStatement(query);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  -1;
    }
}
