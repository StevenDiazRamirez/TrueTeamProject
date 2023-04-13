import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class IndividualInterlineReportPage extends JFrame {
    private JTextField textField1;
    private JPanel reportPanel;
    private JTextField textField2;
    private JButton generateButton;
    private JButton backButton;
    private JButton mainMenuButton;
    private JComboBox employeeIDBox;
    private JTable docInfo;
    private JTable table1;
    private JTable table2;
    private JTable commissionInfo;
    private JTable totalCommission;
    private JTable totalPaid;
    private JFrame reportFrame;

    private java.util.Date startDate;
    private java.util.Date endDate;

    private Connection con = DBSConnection.getConnection();

    public IndividualInterlineReportPage() {
        createPage();
    }

    public void createPage() {
        reportFrame = new JFrame("Innovotype");
        reportFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        reportFrame.add(reportPanel);
        reportFrame.pack();
        reportFrame.setLocationRelativeTo(null);
        reportFrame.setVisible(true);

        getEmployees();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportPage reportPage = new ReportPage();
                reportPage.setVisible(false);
                reportFrame.dispose();
            }
        });
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.createMainPage(MainPage.getProfile());
                mainPage.setVisible(false);
                reportFrame.dispose();
            }
        });
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    startDate = dateFormat.parse(textField1.getText());
                    endDate = dateFormat.parse(textField2.getText());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                if (MainPage.getProfile().getRole().equals("Manager")) {
                    //creates report on employee id selected
                    getDocumentInformation(Integer.parseInt(employeeIDBox.getSelectedItem().toString()), startDate, endDate);
                    getCashInfo(Integer.parseInt(employeeIDBox.getSelectedItem().toString()), startDate, endDate);
                    getCardInfo(Integer.parseInt(employeeIDBox.getSelectedItem().toString()), startDate, endDate);
                    getTotalPaid(Integer.parseInt(employeeIDBox.getSelectedItem().toString()), startDate, endDate);
                    getCommissionInfo(Integer.parseInt(employeeIDBox.getSelectedItem().toString()), startDate, endDate);
                    getTotalCommission(Integer.parseInt(employeeIDBox.getSelectedItem().toString()), startDate, endDate);
                } else if (MainPage.getProfile().getRole().equals("Travel Advisor")) {
                    //creates report on the employee logged in
                    getDocumentInformation(MainPage.getProfile().getEmployeeID(), startDate, endDate);
                    getCashInfo(MainPage.getProfile().getEmployeeID(), startDate, endDate);
                    getCardInfo(MainPage.getProfile().getEmployeeID(), startDate, endDate);
                    getTotalPaid(MainPage.getProfile().getEmployeeID(), startDate, endDate);
                    getCommissionInfo(MainPage.getProfile().getEmployeeID(), startDate, endDate);
                    getTotalCommission(MainPage.getProfile().getEmployeeID(), startDate, endDate);
                }
            }
        });
    }

    public void getEmployees() {
        try {
            Connection con = DBSConnection.getConnection();
            Statement stm = con.createStatement();
            String query = "SELECT EmployeeID FROM employeeaccount";
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                int employee = rs.getInt(1);
                employeeIDBox.addItem(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getDocumentInformation(int employeeID, java.util.Date startDate, java.util.Date endDate) {
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT b.blankType, b.blankID, (s.paymentAmount*s.exchangeRate), s.exchangeRate, s.PaymentAmount, s.taxes, (s.paymentAmount+s.taxes)\n" +
                    "FROM ats.sale s, ats.blanks b\n" +
                    "WHERE b.blankID = s.blankIDSale\n" +
                    "AND (b.Status = 'Sold')\n" +
                    "AND s.blankIDSale = b.blankID\n" +
                    "AND saleDate >= '" + sqlStartDate + "'\n" +
                    "AND saleDate <= '" + sqlEndDate + "'\n" +
                    "AND b.blankType = s.blankTypeSale\n" +
                    "AND s.employeeIDSale = " + employeeID +
                    " AND (s.latePaymentStatus != 'Failed To Pay' or s.latePaymentStatus is null)";

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) docInfo.getModel();

            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);

            String blankType, blankID, USDPrice, exchangeRate, LocalPrice, taxes, priceAndTaxes;

            while (rs.next()) {
                blankType = rs.getString(1);
                blankID = rs.getString(2);
                USDPrice = rs.getString(3);
                exchangeRate = rs.getString(4);
                LocalPrice = rs.getString(5);
                taxes = rs.getString(6);
                priceAndTaxes = rs.getString(7);

                String[] row = {blankType, blankID, USDPrice, exchangeRate, LocalPrice, taxes, priceAndTaxes};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getCashInfo(int employeeID, java.util.Date startDate, java.util.Date endDate) {
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT (s.PaymentAmount+s.taxes) FROM ats.sale s, ats.blanks b WHERE s.PaymentType = 'CASH' AND !(exchangeRate IS NULL)\n" +
                    "AND b.blankID = s.blankIDSale\n" +
                    "AND (b.status = 'Sold')\n" +
                    "AND saleDate >= '" + sqlStartDate + "'\n" +
                    "AND saleDate <= '" + sqlEndDate + "'\n" +
                    "AND s.employeeIDSale = " + employeeID +
                    " AND b.blankType = s.blankTypeSale\n" +
                    "AND (s.latePaymentStatus != 'Failed To Pay' or s.latePaymentStatus is null)";

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) table1.getModel();

            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);

            String priceAndTaxes;

            while (rs.next()) {
                priceAndTaxes= rs.getString(1);
                String[] row = {priceAndTaxes};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getCardInfo(int employeeID, java.util.Date startDate, java.util.Date endDate) {
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT s.cardDetails, (s.paymentAmount*s.exchangeRate), (s.paymentAmount+s.taxes) from ats.sale s, ats.blanks b\n" +
                    "WHERE s.paymentType = 'CARD'\n" +
                    "AND saleDate >= '" + sqlStartDate + "'\n" +
                    "AND saleDate <= '" + sqlEndDate + "'\n" +
                    "AND b.blankID = s.blankIDSale\n" +
                    "AND (b.status = 'Sold')\n" +
                    "AND s.employeeIDSale = " + employeeID +
                    " AND b.blankType = s.blankTypeSale\n" +
                    "AND (s.latePaymentStatus != 'Failed To Pay' or s.latePaymentStatus is null)";

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) table2.getModel();

            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);

            String cardDetails, USDPrice, priceAndTaxes;

            while (rs.next()) {
                cardDetails = rs.getString(1);
                USDPrice = rs.getString(2);
                priceAndTaxes = rs.getString(3);
                String[] row = {cardDetails, USDPrice, priceAndTaxes};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getTotalPaid(int employeeID, java.util.Date startDate, java.util.Date endDate) {
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT (s.PaymentAmount+s.taxes) FROM ats.sale s, ats.blanks b\n" +
                    "WHERE !(s.exchangeRate IS NULL)\n" +
                    "AND b.blankID = s.blankIDSale\n" +
                    "AND (b.status = 'Sold')\n" +
                    "AND saleDate >= '" + sqlStartDate + "'\n" +
                    "AND saleDate <= '" + sqlEndDate + "'\n" +
                    "AND s.employeeIDSale = " + employeeID +
                    " AND b.blankType = s.blankTypeSale\n" +
                    "AND (s.latePaymentStatus != 'Failed To Pay' or s.latePaymentStatus is null)";

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) totalPaid.getModel();

            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);

            String priceAndTaxes;

            while (rs.next()) {
                priceAndTaxes = rs.getString(1);
                String[] row = {priceAndTaxes};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getCommissionInfo(int employeeID, java.util.Date startDate, java.util.Date endDate) {
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT s.PaymentAmount, c.amount, s.taxes FROM ats.sale s, ats.commission c, ats.blanks b\n" +
                    "WHERE !(s.exchangeRate IS NULL)\n" +
                    "AND s.commissionIDSale = c.CommissionID\n" +
                    "AND saleDate >= '" + sqlStartDate + "'\n" +
                    "AND saleDate <= '" + sqlEndDate + "'\n" +
                    "AND b.blankID = s.blankIDSale\n" +
                    "AND (b.status = 'Sold')\n" +
                    "AND s.employeeIDSale = " + employeeID +
                    " AND b.blankType = s.blankTypeSale\n" +
                    "AND (s.latePaymentStatus != 'Failed To Pay' or s.latePaymentStatus is null)";

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) commissionInfo.getModel();

            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);

            String price, CommissionAmount, taxes;

            while (rs.next()) {
                price = rs.getString(1);
                CommissionAmount = rs.getString(2);
                taxes = rs.getString(3);
                String[] row = {price, CommissionAmount, taxes};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getTotalCommission(int employeeID, java.util.Date startDate, java.util.Date endDate) {
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT SUM(s.paymentAmount*(c.amount/100)) FROM ats.sale s, ats.commission c, ats.blanks b\n" +
                    "WHERE !(s.exchangeRate IS NULL)\n" +
                    "AND s.commissionIDSale = c.CommissionID\n" +
                    "AND s.blankIDSale = b.blankID\n" +
                    "AND (b.status = 'Sold')\n" +
                    "AND saleDate >= '" + sqlStartDate + "'\n" +
                    "AND saleDate <= '" + sqlEndDate + "'\n" +
                    "AND s.employeeIDSale = " + employeeID +
                    " AND b.blankType = s.blankTypeSale\n" +
                    "AND (s.latePaymentStatus != 'Failed To Pay' or s.latePaymentStatus is null)";

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) totalCommission.getModel();

            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);

            String totalCommission;

            while (rs.next()) {
                totalCommission = rs.getString(1);
                String[] row = {totalCommission};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
