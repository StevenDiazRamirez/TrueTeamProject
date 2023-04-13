import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TravelAgentPage extends JFrame {
    private JPanel travelAgentPanel;
    private JTable travelAgent;
    private JButton addButton;
    private JButton backButton;
    private JTextField address;
    private JTextField name;
    private JFrame travelAgentFrame;

    public TravelAgentPage() {
        createPage();
    }

    private void createPage() {
        travelAgentFrame = new JFrame("Innovotype");
        travelAgentFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        travelAgentFrame.setPreferredSize(new Dimension(500,500));

        travelAgentFrame.add(travelAgentPanel);
        travelAgentFrame.pack();
        travelAgentFrame.setLocationRelativeTo(null);
        travelAgentFrame.setVisible(true);

        getTravelAgentInfo();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!name.getText().isEmpty() || !address.getText().isEmpty()) {
                    Agent.addAgent(name.getText(), address.getText());
                    JOptionPane.showMessageDialog(null, "Travel Agent Added");
                } else JOptionPane.showMessageDialog(null, "Missing details, Please fill out all the required fields");
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.createMainPage(MainPage.getProfile());
                mainPage.setVisible(false);
                travelAgentFrame.dispose();
            }
        });
    }

    /**
     * Generates a table with all the travel agent info
     */
    private void getTravelAgentInfo() {
        try {
            Connection con = DBSConnection.getConnection();

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT name, address FROM travelagent");
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) travelAgent.getModel();

            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(colName);

            String name, address;

            while (rs.next()) {
                name = rs.getString(1);
                address = rs.getString(2);
                String[] row = {name, address};
                model.addRow(row);

                Agent agent = new Agent(rs.getString(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
