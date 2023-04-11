import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IndividualInterlineReportPage extends JFrame {
    private JTextField textField1;
    private JPanel reportPanel;
    private JTextField textField2;
    private JButton generateButton;
    private JButton backButton;
    private JButton mainMenuButton;
    private JComboBox employeeID;
    private JFrame reportFrame;

    public IndividualInterlineReportPage() {
        createPage();
    }

    public void createPage() {
        reportFrame = new JFrame("Innovotype");
        reportFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        reportFrame.setPreferredSize(new Dimension(500,500));

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
                employeeID.addItem(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
