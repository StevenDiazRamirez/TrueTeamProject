import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class ViewCommissionPage extends JFrame {
    private JPanel viewCommissionPanel;
    private JTable commissionTable;
    private JButton deleteButton;
    private JButton backButton;
    private JButton mainMenuButton;
    private JFrame viewCommissionFrame;

    public ViewCommissionPage() {
        createPage();
    }

    public void createPage() {
        viewCommissionFrame = new JFrame("Innovotype");
        viewCommissionFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        viewCommissionFrame.setPreferredSize(new Dimension(500,500));

        viewCommissionFrame.add(viewCommissionPanel);
        viewCommissionFrame.pack();
        viewCommissionFrame.setLocationRelativeTo(null);
        viewCommissionFrame.setVisible(true);

        getCommissionInfo();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommissionPage commissionPage = new CommissionPage();
                commissionPage.setVisible(false);
                viewCommissionFrame.dispose();
            }
        });
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.createMainPage(MainPage.getProfile());
                mainPage.setVisible(false);
                viewCommissionFrame.dispose();
            }
        });
       /* commissionTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = commissionTable.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) commissionTable.getModel();
            }
        }); */
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = commissionTable.getSelectedRow();
                String commissionID = commissionTable.getValueAt(i,0).toString();

                try {
                    Connection con = DBSConnection.getConnection();
                    PreparedStatement stm = con.prepareStatement("DELETE FROM commission WHERE CommissionID = " + commissionID);
                    stm.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Deleted!");
                } catch (SQLException throwables) {
                    JOptionPane.showMessageDialog(null, throwables);
                }
            }
        });
    }

    public void getCommissionInfo() {
        try {

            Connection con = DBSConnection.getConnection();

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM commission");
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) commissionTable.getModel();

            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);

            String commissionID, amount, commissionDate, commissionEndDate, blankType;

            while (rs.next()) {
                commissionID = rs.getString(1);
                amount = rs.getString(2);
                commissionDate = rs.getString(3);
                commissionEndDate = rs.getString(4);
                blankType = rs.getString(5);
                String[] row = {commissionID, amount, commissionDate, commissionEndDate, blankType};
                model.addRow(row);


                Commission commission = new Commission(rs.getFloat(2), rs.getDate(3),
                        rs.getDate(4), rs.getInt(5));
                commission.setCommissionID(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
