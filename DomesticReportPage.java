import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DomesticReportPage extends JFrame {
    private JTextField textField1;
    private JPanel reportPanel;
    private JTextField textField2;
    private JButton generateButton;
    private JButton backButton;
    private JButton mainMenuButton;
    private JTable docInfo;
    private JTable cashInfo;
    private JTable cardInfo;
    private JTable commissionInfo;
    private JTable totalCommission;
    private JTable totalPaid;
    private JFrame reportFrame;

    private java.util.Date startDate;
    private java.util.Date endDate;

    private Connection con = DBSConnection.getConnection();

    public DomesticReportPage() {
        createPage();
    }

    public void createPage() {
        reportFrame = new JFrame("Innovotype");
        reportFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        reportFrame.add(reportPanel);
        reportFrame.pack();
        reportFrame.setLocationRelativeTo(null);
        reportFrame.setVisible(true);

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
                getDocumentInformation(startDate, endDate);
                getCashInfo(startDate, endDate);
                getCardInfo(startDate, endDate);
                getTotalPaid(startDate, endDate);
                getCommissionInfo(startDate, endDate);
                getTotalCommission(startDate, endDate);
            }
        });
    }

    private void getDocumentInformation(java.util.Date startDate, java.util.Date endDate) {
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT e.employeeID, COUNT(s.saleID), SUM(s.PaymentAmount), SUM(s.taxes), SUM(s.PaymentAmount)+SUM(s.taxes)\n" +
                    "FROM ats.employeeaccount e, ats.sale s, ats.blanks b\n" +
                    "WHERE e.EmployeeID = s.employeeIDSale\n" +
                    "AND b.blankID = s.blankIDSale\n" +
                    "AND saleDate >= '" + sqlStartDate + "'\n" +
                    "AND saleDate <= '" + sqlEndDate + "'\n" +
                    "AND (b.status = 'Sold')\n" +
                    "AND (s.exchangeRate IS NULL)\n" +
                    "AND b.blankType = s.blankTypeSale\n" +
                    "AND (s.latePaymentStatus != 'Failed To Pay' or s.latePaymentStatus is null)\n" +
                    "GROUP BY e.EmployeeID";

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

            String employeeID, sales, totalPrice, totalTaxes, totalAmount;

            while (rs.next()) {
                employeeID = rs.getString(1);
                sales = rs.getString(2);
                totalPrice = rs.getString(3);
                totalTaxes = rs.getString(4);
                totalAmount = rs.getString(5);

                String[] row = {employeeID, sales, totalPrice, totalTaxes, totalAmount};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getCashInfo(java.util.Date startDate, java.util.Date endDate) {
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT e.EmployeeID, SUM(s.PaymentAmount)+SUM(s.taxes)\n" +
                    "FROM ats.employeeaccount e, ats.sale s, ats.blanks b\n" +
                    "WHERE e.EmployeeID = s.employeeIDSale\n" +
                    "AND b.blankID = s.blankIDSale\n" +
                    "AND saleDate >= '" + sqlStartDate + "'\n" +
                    "AND saleDate <= '" + sqlEndDate + "'\n" +
                    "AND (b.status = 'Sold')\n" +
                    "AND (s.exchangeRate IS NULL)\n" +
                    "AND s.PaymentType = 'CASH'\n" +
                    "AND b.blankType = s.blankTypeSale\n" +
                    "AND (s.latePaymentStatus != 'Failed To Pay' or s.latePaymentStatus is null)\n" +
                    "GROUP BY e.EmployeeID";

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

            String employeeID, totalCashAmount;

            while (rs.next()) {
                employeeID = rs.getString(1);
                totalCashAmount = rs.getString(2);
                String[] row = {employeeID, totalCashAmount};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getCardInfo(java.util.Date startDate, java.util.Date endDate) {
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT SUM(s.PaymentAmount*s.exchangeRate), SUM(s.PaymentAmount)+SUM(s.taxes)\n" +
                    "FROM ats.employeeaccount e, ats.sale s, ats.blanks b\n" +
                    "WHERE e.EmployeeID = s.employeeIDSale\n" +
                    "AND b.blankID = s.blankIDSale\n" +
                    "AND saleDate >= '" + sqlStartDate + "'\n" +
                    "AND saleDate <= '" + sqlEndDate + "'\n" +
                    "AND (b.status = 'Sold')\n" +
                    "AND (s.exchangeRate IS NULL)\n" +
                    "AND s.PaymentType = 'CARD'\n" +
                    "AND b.blankType = s.blankTypeSale\n" +
                    "AND (s.latePaymentStatus != 'Failed To Pay' or s.latePaymentStatus is null)\n" +
                    "GROUP BY e.EmployeeID";

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

            String totalUSDAmount, totalAmount;

            while (rs.next()) {
                totalUSDAmount = rs.getString(1);
                totalAmount = rs.getString(2);
                String[] row = {totalUSDAmount, totalAmount};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getTotalPaid(java.util.Date startDate, java.util.Date endDate) {
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT SUM(s.PaymentAmount)+SUM(s.taxes)\n" +
                    "FROM ats.employeeaccount e, ats.sale s, ats.blanks b\n" +
                    "WHERE e.EmployeeID = s.employeeIDSale\n" +
                    "AND b.blankID = s.blankIDSale\n" +
                    "AND saleDate >= '" + sqlStartDate + "'\n" +
                    "AND saleDate <= '" + sqlEndDate + "'\n" +
                    "AND (b.status = 'Sold')\n" +
                    "AND (s.exchangeRate IS NULL)\n" +
                    "AND b.blankType = s.blankTypeSale\n" +
                    "AND (s.latePaymentStatus != 'Failed To Pay' or s.latePaymentStatus is null)\n" +
                    "GROUP BY e.EmployeeID";

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

            String totalAmount;

            while (rs.next()) {
                totalAmount = rs.getString(1);
                String[] row = {totalAmount};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getCommissionInfo(java.util.Date startDate, java.util.Date endDate) {
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT c.amount, SUM(s.PaymentAmount), SUM(s.taxes)\n" +
                    "FROM ats.sale s, ats.employeeaccount e, ats.commission c, ats.blanks b\n" +
                    "WHERE e.EmployeeID = s.employeeIDSale\n" +
                    "AND c.CommissionID = s.commissionIDSale\n" +
                    "AND b.blankID = s.blankIDSale\n" +
                    "AND saleDate >= '" + sqlStartDate + "'\n" +
                    "AND saleDate <= '" + sqlEndDate + "'\n" +
                    "AND (b.status = 'Sold')\n" +
                    "AND (s.exchangeRate IS NULL)\n" +
                    "AND b.blankType = s.blankTypeSale\n" +
                    "AND (s.latePaymentStatus != 'Failed To Pay' or s.latePaymentStatus is null)\n" +
                    "GROUP BY e.EmployeeID, c.commissionID";

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

            String commissionAmount, totalAmount, totalTaxes;

            while (rs.next()) {
                commissionAmount = rs.getString(1);
                totalAmount = rs.getString(2);
                totalTaxes = rs.getString(3);
                String[] row = {commissionAmount, totalAmount, totalTaxes};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getTotalCommission(java.util.Date startDate, java.util.Date endDate) {
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT SUM(s.PaymentAmount*(c.amount/100)) FROM ats.sale s, ats.commission c, ats.blanks b\n" +
                    "WHERE (s.exchangeRate IS NULL)\n" +
                    "AND s.commissionIDSale = c.CommissionID\n" +
                    "AND s.blankIDSale = b.blankID\n" +
                    "AND (b.status = 'Sold')\n" +
                    "AND saleDate >= '" + sqlStartDate + "'\n" +
                    "AND saleDate <= '" + sqlEndDate + "'\n" +
                    "AND (s.latePaymentStatus != 'Failed To Pay' or s.latePaymentStatus is null)\n" +
                    "AND b.blankType = s.blankTypeSale";

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
