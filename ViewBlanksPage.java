
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class ViewBlanksPage extends JFrame {
    private JPanel viewBlankPanel;
    private JButton backButton;
    private JTable blankTable;
    private JButton mainMenuButton;
    private JTextField employeeField;
    private JButton reallocateButton;
    private JButton reportButton;
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
                employeeField.setText(model.getValueAt(selectedRow,5).toString());
            }
        });
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = blankTable.getSelectedRow();
                String blankID = blankTable.getValueAt(i, 0).toString();
                String blankType = blankTable.getValueAt(i, 1).toString();
                Blank.changeStatus(Integer.parseInt(blankID), Integer.parseInt(blankType), "Lost/Stolen");
                JOptionPane.showMessageDialog(null, "Blank has been reported");
            }
        });
        reallocateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    int i = blankTable.getSelectedRow();
                    if (blankTable.getValueAt(i, 2).toString().equals("Assigned")) {
                        if (!MainPage.getProfile().getRole().equals("Manager")) {
                            JOptionPane.showMessageDialog(null, "Only Manager allowed");
                        } else {
                            String blankID = blankTable.getValueAt(i, 0).toString();
                            DefaultTableModel model = (DefaultTableModel) blankTable.getModel();
                            if (i >= 0)
                                model.setValueAt(employeeField.getText(), i, 5);

                            int newID = Integer.parseInt(employeeField.getText());

                            try {
                                Connection con = DBSConnection.getConnection();
                                PreparedStatement stm = con.prepareStatement("UPDATE blanks SET employeeID = " + newID + " WHERE blankID = " + Integer.parseInt(blankID));
                                stm.executeUpdate();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                    } else JOptionPane.showMessageDialog(null, "Wrong Blank status");
            }
        });
    }

    private void getBlankInfoSingle() {
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

            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel> (model);
            blankTable.setRowSorter(sorter);

            String blankID, blankType, status, dateReceived, dateAssigned, employeeID;

            while (rs.next()) {
                blankID = rs.getString(1);
                blankType = rs.getString(2);
                status = rs.getString(3);
                dateReceived = rs.getString(4);
                dateAssigned = rs.getString(5);
                employeeID = rs.getString(6);
                String[] row = {blankID, blankType, status, dateReceived, dateAssigned, employeeID};
                model.addRow(row);

                if (Blank.checkBlankStatus(rs.getInt(1)).equals("Assigned") || Blank.checkBlankStatus(rs.getInt(1)).equals("Sold")
                        || Blank.checkBlankStatus(rs.getInt(1)).equals("Refunded")) {
                    Blank blank = new Blank(rs.getInt(2),
                            rs.getString(3),
                            rs.getDate(4),
                            rs.getDate(5),
                            rs.getInt(6)
                    );
                    blank.setBlankID(rs.getInt(1));
                } else {
                    Blank blank = new Blank(rs.getInt(2),
                            rs.getString(3),
                            rs.getDate(4)
                    );
                    blank.setBlankID(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getBlankInfoAll() {
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

            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel> (model);
            blankTable.setRowSorter(sorter);

            String blankID, blankType, status, dateReceived, dateAssigned, employeeID;

            while (rs.next()) {
                blankID = rs.getString(1);
                blankType = rs.getString(2);
                status = rs.getString(3);
                dateReceived = rs.getString(4);
                dateAssigned = rs.getString(5);
                employeeID = rs.getString(6);
                String[] row = {blankID, blankType, status, dateReceived, dateAssigned, employeeID};
                model.addRow(row);

                if (Blank.checkBlankStatus(rs.getInt(1)).equals("Assigned") || Blank.checkBlankStatus(rs.getInt(1)).equals("Sold")
                        || Blank.checkBlankStatus(rs.getInt(1)).equals("Refunded")) {
                    Blank blank = new Blank(rs.getInt(2),
                            rs.getString(3),
                            rs.getDate(4),
                            rs.getDate(5),
                            rs.getInt(6)
                    );
                    blank.setBlankID(rs.getInt(1));
                } else {
                    Blank blank = new Blank(rs.getInt(2),
                            rs.getString(3),
                            rs.getDate(4)
                    );
                    blank.setBlankID(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
