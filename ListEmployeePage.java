import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ListEmployeePage extends JFrame {
    private JPanel listEmployeePanel;
    private JTable employeeTable;
    private JButton deleteAccountButton;
    private JButton backButton;
    private JButton mainMenuButton;
    private JFrame listEmployeeFrame;

    public ListEmployeePage() {
        createPage();
    }

    public void createPage() {
        listEmployeeFrame = new JFrame("Innovotype");
        listEmployeeFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        listEmployeeFrame.setPreferredSize(new Dimension(500,500));

        listEmployeeFrame.add(listEmployeePanel);
        listEmployeeFrame.pack();
        listEmployeeFrame.setLocationRelativeTo(null);
        listEmployeeFrame.setVisible(true);

        getListOfEmployees();

        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = employeeTable.getSelectedRow();
                String employeeID = employeeTable.getValueAt(i,0).toString();

                try {
                    Connection con = DBSConnection.getConnection();
                    PreparedStatement stm = con.prepareStatement("DELETE FROM employeeaccount WHERE EmployeeID = " + employeeID);
                    stm.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Deleted!");
                } catch (SQLException throwables) {
                    JOptionPane.showMessageDialog(null, throwables);
                }

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageEmployeesPage manageEmployeesPage = new ManageEmployeesPage();
                manageEmployeesPage.setVisible(false);
                listEmployeeFrame.dispose();
            }
        });
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.createMainPage(MainPage.getProfile());
                mainPage.setVisible(false);
                listEmployeeFrame.dispose();
            }
        });
    }

    public void getListOfEmployees() {
        try {

            Connection con = DBSConnection.getConnection();

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM employeeaccount");
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();

            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);

            String employeeID, firstName, lastName, username, password, accountType ;

            while (rs.next()) {
                employeeID = rs.getString(1);
                firstName = rs.getString(2);
                lastName = rs.getString(3);
                username = rs.getString(4);
                password = rs.getString(5);
                accountType = rs.getString(6);
                String[] row = {employeeID, firstName, lastName, username, password, accountType};
                model.addRow(row);


                Employee employee = new Employee(rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6));
                employee.setEmployeeID(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
