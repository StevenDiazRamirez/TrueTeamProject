

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Date;

public class ViewBlanksPage extends JFrame {
    private JPanel viewBlankPanel;
    private JButton backButton;
    private JTable blankTable;
    private JButton mainMenuButton;
    private JTextField employeeField;
    private JButton reallocateButton;
    private JFrame viewBlankFrame;

    public ViewBlanksPage() {
        createPage();
    }

    public void createPage() {
        viewBlankFrame = new JFrame("Innovotype");
        viewBlankFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        viewBlankFrame.setPreferredSize(new Dimension(500,500));

        viewBlankFrame.add(viewBlankPanel);
        viewBlankFrame.pack();
        viewBlankFrame.setLocationRelativeTo(null);
        viewBlankFrame.setVisible(true);


        if (MainPage.getProfile().getRole().equals("Travel Advisor")) {
            getBlankInfoSingle();
        } else {
            getBlankInfoAll();
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlankPage blankPage = new BlankPage();
                blankPage.setVisible(false);
                viewBlankFrame.dispose();
            }
        });
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.createMainPage(MainPage.getProfile());
                mainPage.setVisible(false);
                viewBlankFrame.dispose();
            }
        });
        blankTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = blankTable.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) blankTable.getModel();
                employeeField.setText(model.getValueAt(selectedRow,7).toString());
            }
        });
        reallocateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = blankTable.getSelectedRow();
                String blankID = blankTable.getValueAt(i, 0).toString();
                DefaultTableModel model = (DefaultTableModel) blankTable.getModel();
                if (i >= 0)
                    model.setValueAt(employeeField.getText(), i, 7);

                int newID = Integer.parseInt(employeeField.getText());

                try {
                    Connection con = DBSConnection.getConnection();
                    PreparedStatement stm = con.prepareStatement("UPDATE blanks SET employeeID = " + newID + " WHERE blankID = " + Integer.parseInt(blankID));
                    stm.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    public void getBlankInfoSingle() {
        try {

            Connection con = DBSConnection.getConnection();

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM blanks WHERE employeeID =" + MainPage.getProfile().getEmployeeID());
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) blankTable.getModel();

            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);


            String blankID, blankType, status, destFrom, destTo, dateReceived, dateAssigned, employeeID;

            while (rs.next()) {
                blankID = rs.getString(1);
                blankType = rs.getString(2);
                status = rs.getString(3);
                destFrom = rs.getString(4);
                destTo = rs.getString(5);
                dateReceived = rs.getString(6);
                dateAssigned = rs.getString(7);
                employeeID = rs.getString(8);
                String[] row = {blankID, blankType, status, destFrom, destTo, dateReceived, dateAssigned, employeeID};
                model.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getBlankInfoAll() {
        try {

            Connection con = DBSConnection.getConnection();

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM blanks");
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) blankTable.getModel();

            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);


            String blankID, blankType, status, destFrom, destTo, dateReceived, dateAssigned, employeeID;

            while (rs.next()) {
                blankID = rs.getString(1);
                blankType = rs.getString(2);
                status = rs.getString(3);
                destFrom = rs.getString(4);
                destTo = rs.getString(5);
                dateReceived = rs.getString(6);
                dateAssigned = rs.getString(7);
                employeeID = rs.getString(8);
                String[] row = {blankID, blankType, status, destFrom, destTo, dateReceived, dateAssigned, employeeID};
                model.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
