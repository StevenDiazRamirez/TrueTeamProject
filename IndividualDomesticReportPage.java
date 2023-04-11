import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class IndividualDomesticReportPage extends JFrame {
    private JPanel reportPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton generateButton;
    private JButton backButton;
    private JButton mainMenuButton;
    private JComboBox employeeID;
    private JFrame reportFrame;

    public IndividualDomesticReportPage() {
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
                //createReport();
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

    public static void createReport(int employeeID, Date startDate, Date endDate) {
        JFrame report = new JFrame("Individual Domestic Report");
        report.setDefaultCloseOperation(EXIT_ON_CLOSE);

        String [] headers;
        headers = new String[]{"Type","ID","GBP","Taxes","Total Price/GBP", "Cash","CC number","GBP","TotalPaid","Assessable Amount","Commission %","Non-Assessable Amount"};

    }
}
