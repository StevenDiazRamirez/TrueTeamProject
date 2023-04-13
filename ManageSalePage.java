import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ManageSalePage extends JFrame {
    private JPanel refundPanel;
    private JButton refundButton;
    private JButton backButton;
    private JTable refundTable;
    private JButton voidButton;
    private JButton updateButton;
    private JTextField latePayStatus;
    private JFrame refundFrame;

    public ManageSalePage() {
        createRefundPage();
    }

    public void createRefundPage() {
        refundFrame = new JFrame("Innovotype");
        refundFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        refundFrame.setPreferredSize(new Dimension(500,500));

        refundFrame.add(refundPanel);
        refundFrame.pack();
        refundFrame.setLocationRelativeTo(null);
        refundFrame.setVisible(true);

        getRefundInfo();

        voidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = refundTable.getSelectedRow();
                String blankID = refundTable.getValueAt(i, 7).toString();
                String blankType = refundTable.getValueAt(i, 0).toString();

                try {
                    Connection con = DBSConnection.getConnection();
                    PreparedStatement stm = con.prepareStatement("UPDATE blanks SET status = 'Void'" + " WHERE " +
                            "blankID = " + blankID + " AND blankType = " + blankType);
                    stm.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Sale has been Voided");
                } catch (SQLException throwables) {
                    JOptionPane.showMessageDialog(null, throwables);
                }

            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = refundTable.getSelectedRow();
                String saleID = refundTable.getValueAt(i, 0).toString();
                if (!latePayStatus.getText().isEmpty()) {
                    if (!refundTable.getValueAt(i, 5).toString().isEmpty())
                        Sale.addLatePayStatus(latePayStatus.getText(), Integer.parseInt(saleID));
                }
            }
        });
        refundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = refundTable.getSelectedRow();
                String blankID = refundTable.getValueAt(i, 8).toString();
                String blankType = refundTable.getValueAt(i, 1).toString();

                try {
                    Connection con = DBSConnection.getConnection();
                    PreparedStatement stm = con.prepareStatement("UPDATE blanks SET status = 'Refunded'" + " WHERE " +
                            "blankID = " + blankID + " AND blankType = " + blankType);
                    stm.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Refunded!");
                } catch (SQLException throwables) {
                    JOptionPane.showMessageDialog(null, throwables);
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.createMainPage(MainPage.getProfile());
                mainPage.setVisible(false);
                refundFrame.dispose();
            }
        });
    }

    public void getRefundInfo() {
        try {
            Connection con = DBSConnection.getConnection();

            Statement stm = con.createStatement();
            String query = "SELECT saleID, blankTypeSale, PaymentType, PaymentAmount, saleDate, latePaymentDate, latePaymentStatus," +
                    " cardDetails, b.blankID FROM sale s, blanks b WHERE s.blankIDSale = b.blankID AND s.blankTypeSale = b.blankType AND b.status = 'Sold'";
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) refundTable.getModel();

            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);

            String saleID, blankType, PaymentType, PaymentAmount, saleDate, latePaymentDate, latePaymentStatus, cardDetails, blankID;

            while (rs.next()) {
                saleID = rs.getString(1);
                blankType = rs.getString(2);
                PaymentType = rs.getString(3);
                PaymentAmount = rs.getString(4);
                saleDate = rs.getString(5);
                latePaymentDate = rs.getString(6);
                latePaymentStatus = rs.getString(7);
                cardDetails = rs.getString(8);
                blankID = rs.getString(9);

                String[] row = {saleID, blankType, PaymentType, PaymentAmount, saleDate, latePaymentDate, latePaymentStatus, cardDetails, blankID};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}