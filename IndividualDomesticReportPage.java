import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class IndividualDomesticReportPage extends JFrame {
    private JPanel reportPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton generateButton;
    private JButton backButton;
    private JButton mainMenuButton;
    private JComboBox employeeIDBox;
    private JTable docInfo;
    private JTable cashInfo;
    private JTable totalPaid;
    private JTable cardInfo;
    private JTable commissionInfo;
    private JTable totalCommission;
    private JFrame reportFrame;

    private java.util.Date startDate;
    private java.util.Date endDate;

    private Connection con = DBSConnection.getConnection();

    public IndividualDomesticReportPage() {
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
                //createReport();

                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    startDate = dateFormat.parse(textField1.getText());
                    endDate = dateFormat.parse(textField2.getText());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                getDocumentInformation(Integer.parseInt(employeeIDBox.getSelectedItem().toString()), startDate, endDate);
                getCashInfo(Integer.parseInt(employeeIDBox.getSelectedItem().toString()), startDate, endDate);
                getCardInfo(Integer.parseInt(employeeIDBox.getSelectedItem().toString()), startDate, endDate);
                getTotalPaid(Integer.parseInt(employeeIDBox.getSelectedItem().toString()), startDate, endDate);
                getCommissionInfo(Integer.parseInt(employeeIDBox.getSelectedItem().toString()), startDate, endDate);
                getTotalCommission(Integer.parseInt(employeeIDBox.getSelectedItem().toString()), startDate, endDate);
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

    public static void createReport(int employeeID, Date startDate, Date endDate) {
        JFrame report = new JFrame("Individual Domestic Report");
        report.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void getDocumentInformation(int employeeID, java.util.Date startDate, java.util.Date endDate) {
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT b.blankType, b.blankID, s.PaymentAmount, s.taxes, (s.PaymentAmount+s.taxes)\n" +
                    "FROM ats.sale s, ats.blanks b\n" +
                    "WHERE b.blankID = s.blankIDSale\n" +
                    "AND saleDate >= '" + sqlStartDate + "'\n" +
                    "AND saleDate <= '" + sqlEndDate + "'\n" +
                    "AND s.exchangeRate IS NULL\n" +
                    "AND (b.Status = 'Sold')\n" +
                    "AND b.blankType = s.blankTypeSale\n" +
                    "AND s.employeeIDSale = " + employeeID;

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

            String blankType, blankID, price, taxes, priceAndTaxes;

            while (rs.next()) {
                blankType = rs.getString(1);
                blankID = rs.getString(2);
                price = rs.getString(3);
                taxes = rs.getString(4);
                priceAndTaxes= rs.getString(5);
                String[] row = {blankType, blankID, price, taxes, priceAndTaxes};
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

            String query = "SELECT(s.paymentAmount+s.taxes)\n" +
                    "FROM ats.sale s, ats.blanks b\n" +
                    "WHERE b.blankID = s.blankIDSale\n" +
                    "AND saleDate >= '" + sqlStartDate + "'\n" +
                    "AND saleDate <= '" + sqlEndDate + "'\n" +
                    "AND s.exchangeRate IS NULL\n" +
                    "AND (b.Status = 'Sold')\n" +
                    "AND b.blankType = s.blankTypeSale\n" +
                    "AND s.PaymentType = 'CASH'\n" +
                    "AND s.employeeIDSale = " + employeeID;

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) cashInfo.getModel();

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

            String query = "SELECT s.cardDetails, (s.paymentAmount+s.taxes)\n" +
                    "FROM ats.sale s, ats.blanks b\n" +
                    "WHERE b.blankID = s.blankIDSale\n" +
                    "AND saleDate >= '" + sqlStartDate + "'\n" +
                    "AND saleDate <= '" + sqlEndDate + "'\n" +
                    "AND s.exchangeRate IS NULL\n" +
                    "AND (b.Status = 'Sold')\n" +
                    "AND b.blankType = s.blankTypeSale\n" +
                    "AND s.PaymentType = 'CARD'\n" +
                    "AND s.employeeIDSale = " + employeeID;

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) cardInfo.getModel();

            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);

            String cardDetails, priceAndTaxes;

            while (rs.next()) {
                cardDetails = rs.getString(1);
                priceAndTaxes = rs.getString(2);
                String[] row = {cardDetails, priceAndTaxes};
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

            String query = "SELECT(s.PaymentAmount+s.taxes)\n" +
                    "FROM ats.sale s, ats.blanks b\n" +
                    "WHERE b.blankID = s.blankIDSale\n" +
                    "AND saleDate >= '" + sqlStartDate + "'\n" +
                    "AND saleDate <= '" + sqlEndDate + "'\n" +
                    "AND s.exchangeRate IS NULL\n" +
                    "AND (b.Status = 'Sold')\n" +
                    "AND b.blankType = s.blankTypeSale\n" +
                    "AND s.employeeIDSale = " + employeeID;

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
                    "WHERE s.exchangeRate is NULL \n" +
                    "AND s.commissionIDSale = c.CommissionID\n" +
                    "AND saleDate >= '" + sqlStartDate + "'\n" +
                    "AND saleDate <= '" + sqlEndDate + "'\n" +
                    "AND b.blankID = s.blankIDSale\n" +
                    "AND (b.Status = 'Sold')\n" +
                    "AND b.blankType = s.blankTypeSale\n" +
                    "AND s.employeeIDSale = " + employeeID;

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

            String priceAndTaxes, CommissionAmount, taxes;

            while (rs.next()) {
                priceAndTaxes = rs.getString(1);
                CommissionAmount = rs.getString(2);
                taxes = rs.getString(3);
                String[] row = {priceAndTaxes, CommissionAmount, taxes};
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
                    "WHERE s.exchangeRate is NULL \n" +
                    "AND s.commissionIDSale = c.CommissionID\n" +
                    "AND saleDate >= '" + sqlStartDate + "'\n" +
                    "AND saleDate <= '" + sqlEndDate + "'\n" +
                    "AND b.blankID = s.blankIDSale\n" +
                    "AND (b.Status = 'Sold')\n" +
                    "AND b.blankType = s.blankTypeSale\n" +
                    "AND s.employeeIDSale = " + employeeID;

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
