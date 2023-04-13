import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StockTurnoverReportPage extends JFrame {
    private JTextField textField1;
    private JPanel reportPanel;
    private JTextField textField2;
    private JButton generateButton;
    private JButton backButton;
    private JButton mainMenuButton;
    private JTable receivedAgentStock;
    private JTable assignedSubAgentsStock;
    private JTable newlyAssignedBlanks;
    private JTable usedBlanks;
    private JTable finalAgentAmounts;
    private JTable finalSubAgentAmounts;
    private JFrame reportFrame;

    private java.util.Date startDate;
    private java.util.Date endDate;

    private Connection con = DBSConnection.getConnection();

    public StockTurnoverReportPage() {
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
                getReceivedAgentStock(startDate, endDate);
                getNewlyAssignedBlanks(startDate, endDate);
                getAssignedSubAgentsStock(startDate, endDate);
                getUsedBlanks(startDate, endDate);
                getFinalAgentAmounts(endDate);
                getFinalSubAgentAmounts(endDate);
            }
        });
    }

    private void getReceivedAgentStock(Date startDate, Date endDate) {
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT blankType, MIN(blankID), MAX(blankID), COUNT(blankID)\n" +
                    "FROM ats.blanks \n" +
                    "WHERE dateRecieved<= '" + sqlEndDate + "'\n" +
                    "AND dateRecieved>= '" + sqlStartDate + "'\n" +
                    "GROUP BY dateRecieved, blankType";

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) receivedAgentStock.getModel();

            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);

            String blankType, minBlankID, maxBlankID, blankCount;

            while (rs.next()) {
                blankType = rs.getString(1);
                minBlankID = rs.getString(2);
                maxBlankID = rs.getString(3);
                blankCount = rs.getString(4);

                String[] row = {blankType, minBlankID, maxBlankID, blankCount};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void getAssignedSubAgentsStock(Date startDate, Date endDate) {
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT e.EmployeeID, MIN(blankID), MAX(blankID), COUNT(blankID)\n" +
                    "FROM ats.employeeaccount e, ats.blanks b\n" +
                    "WHERE e.EmployeeID = b.employeeIDBlank \n" +
                    "AND dateAssigned <= '" + sqlEndDate + "'\n" +
                    "AND dateAssigned >= '" + sqlStartDate + "'\n" +
                    "AND b.status is not null\n" +
                    "GROUP BY b.dateAssigned, e.EmployeeID";

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) assignedSubAgentsStock.getModel();

            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);

            String employeeID, minBlankID, maxBlankID, blankCount;

            while (rs.next()) {
                employeeID = rs.getString(1);
                minBlankID = rs.getString(2);
                maxBlankID = rs.getString(3);
                blankCount = rs.getString(4);

                String[] row = {employeeID, minBlankID, maxBlankID, blankCount};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getNewlyAssignedBlanks(Date startDate, Date endDate) {
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT e.EmployeeID, b.blankType, MIN(b.blankID), MAX(b.blankID), COUNT(b.blankID)\n" +
                    "FROM ats.employeeaccount e, ats.blanks b\n" +
                    "WHERE e.EmployeeID = b.employeeIDBlank\n" +
                    "AND b.dateAssigned> '" + sqlStartDate + "'\n" +
                    "AND b.dateAssigned< '" + sqlEndDate + "'\n" +
                    "AND b.status is not null\n" +
                    "GROUP BY e.EmployeeID, b.blankType, b.dateAssigned";

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) newlyAssignedBlanks.getModel();

            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);

            String employeeID, blankType, minBlankID, maxBlankID, blankCount;

            while (rs.next()) {
                employeeID = rs.getString(1);
                blankType = rs.getString(2);
                minBlankID = rs.getString(3);
                maxBlankID = rs.getString(4);
                blankCount = rs.getString(5);

                String[] row = {employeeID, blankType, minBlankID, maxBlankID, blankCount};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void getUsedBlanks(Date startDate, Date endDate) {
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT b.blankType, MIN(blankID),MAX(blankID), COUNT(blankID) \n" +
                    "FROM ats.employeeaccount e, ats.blanks b, ats.sale s\n" +
                    "WHERE e.EmployeeID = b.employeeIDBlank\n" +
                    "AND s.blankIDSale = b.blankID\n" +
                    "AND b.status != 'Assigned' \n" +
                    "AND b.status is not null\n" +
                    "AND s.saleDate >= '" + sqlStartDate + "'\n" +
                    "AND s.saleDate<= '" + sqlEndDate + "'\n" +
                    "GROUP BY e.EmployeeID, b.blankType, b.dateAssigned";

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) usedBlanks.getModel();

            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);

            String blankType, minBlankID, maxBlankID, blankCount;

            while (rs.next()) {
                blankType = rs.getString(1);
                minBlankID = rs.getString(2);
                maxBlankID = rs.getString(3);
                blankCount = rs.getString(4);

                String[] row = {blankType, minBlankID, maxBlankID, blankCount};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void getFinalAgentAmounts(Date endDate) {
        try {
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT b.blankType, MIN(blankID), MAX(blankID), COUNT(blankID) \n" +
                    "FROM ats.blanks b\n" +
                    "WHERE b.dateRecieved <= '" + sqlEndDate + "'\n" +
                    "AND (b.status = 'Assigned' OR b.status = 'Received')\n" +
                    "GROUP BY b.blankType, b.status, b.dateRecieved";

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) finalAgentAmounts.getModel();

            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);

            String blankType, minBlankID, maxBlankID, blankCount;

            while (rs.next()) {
                blankType = rs.getString(1);
                minBlankID = rs.getString(2);
                maxBlankID = rs.getString(3);
                blankCount = rs.getString(4);

                String[] row = {blankType, minBlankID, maxBlankID, blankCount};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getFinalSubAgentAmounts(Date endDate) {
        try {
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            String query = "SELECT e.EmployeeID, b.blankType, MIN(blankID), MAX(blankID), COUNT(blankID) \n" +
                    "FROM ats.blanks b, ats.employeeaccount e\n" +
                    "WHERE b.dateRecieved <= '" + sqlEndDate + "'\n" +
                    "AND b.employeeIDBlank = e.EmployeeID\n" +
                    "AND b.status = 'Assigned'\n" +
                    "GROUP BY e.EmployeeID, b.blankType, b.status, b.dateRecieved";

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) finalSubAgentAmounts.getModel();

            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);

            String employeeID, blankType, minBlankID, maxBlankID, blankCount;

            while (rs.next()) {
                employeeID = rs.getString(1);
                blankType = rs.getString(2);
                minBlankID = rs.getString(3);
                maxBlankID = rs.getString(4);
                blankCount = rs.getString(5);

                String[] row = {employeeID, blankType, minBlankID, maxBlankID, blankCount};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
