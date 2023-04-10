import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
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
    private JFrame saleFrame;
    private JPanel panel;
    private JTextField latePayDate;
    private Date lateDate;

    private int blankID;

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
                blankID = getSelectedBlankID();
                if (customerEmail.getText().isEmpty() && paymentType.getSelectedItem().equals("CASH") && flightType.getSelectedItem().equals("DOMESTIC")) {
                    Sale.addNewCashSaleDomestic(Integer.parseInt(blankType.getSelectedItem().toString()), blankID, Integer.parseInt(amount.getText()));
                }
                if (customerEmail.getText().isEmpty() && paymentType.getSelectedItem().equals("CARD") && flightType.getSelectedItem().equals("DOMESTIC")) {

                }
                if (customerEmail.getText().isEmpty() && paymentType.getSelectedItem().equals("CASH") && flightType.getSelectedItem().equals("GLOBAL")) {

                }
                if (customerEmail.getText().isEmpty() && paymentType.getSelectedItem().equals("CARD") && flightType.getSelectedItem().equals("GLOBAL")) {

                }

            }
        });
        voidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //below will be used to get latePayDate for database
                //java.sql.Date endDate2 = new java.sql.Date(lateDate.getTime());
               // System.out.println(endDate2);
                getCommissions();

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
        ArrayList<JTextField> textFields = new ArrayList<JTextField>();
        for (int i = 0; i < numFields; i++) {
            JTextField textField = new JTextField(5);
            panel.add(textField);
            textFields.add(textField);
        }

        JOptionPane.showConfirmDialog(null, panel, "Coupons", JOptionPane.OK_CANCEL_OPTION);
        for (JTextField textField : textFields) {
            String text = textField.getText();
            System.out.println(text);
        }
    }

    public void createLatePayPanel() {
        if (Sale.getCustomerType(customerEmail.getText()).equals("Regular") || Sale.getCustomerType(customerEmail.getText()).equals("Valued")) {
            panel = new JPanel();
            latePayDate = new JTextField(5);
            panel.add(latePayDate);

            int option = JOptionPane.showConfirmDialog(null, panel, "LatePayment", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    lateDate = dateFormat.parse(latePayDate.getText());
                    System.out.println(lateDate);
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

}
