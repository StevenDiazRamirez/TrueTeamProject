import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame {

    private JPanel mainPanel;
    private JButton manageReportsButton;
    private JButton manageCommissionRateButton;
    private JButton createSaleButton;
    private JButton backupRestoreButton;
    private JButton manageCustomerAccountsButton;
    private JButton manageBlanksButton;
    private JButton refundButton;
    private JButton logoutButton;
    private JButton manageEmployeesButton;
    private JButton travelAgentButton;
    private JFrame mainFrame;

    public static Employee profile;

    public MainPage() {
    }

    public void createMainPage(Employee profile) {
        MainPage.profile = profile;

        mainFrame = new JFrame("Innovotype");
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(500,500));

        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        travelAgentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getProfile().getRole().equals("Admin")) {
                    TravelAgentPage travelAgentPage = new TravelAgentPage();
                    travelAgentPage.setVisible(false);
                    mainFrame.dispose();
                } else JOptionPane.showMessageDialog(null, "No access,You don't have access to this function");
            }
        });
        manageEmployeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getProfile().getRole().equals("Admin")) {
                    ManageEmployeesPage manageEmployeesPage = new ManageEmployeesPage();
                    manageEmployeesPage.setVisible(false);
                    mainFrame.dispose();
                } else JOptionPane.showMessageDialog(null, "No access,You don't have access to this function");
            }
        });
        manageReportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportPage reportPage = new ReportPage();
                reportPage.setVisible(false);
                mainFrame.dispose();
            }
        });
        manageCommissionRateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    CommissionPage commissionPage = new CommissionPage();
                    commissionPage.setVisible(false);
                    mainFrame.dispose();
            }
        });
        createSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    SalePage salePage = new SalePage();
                    salePage.setVisible(false);
                    mainFrame.dispose();
                    System.out.println(MainPage.getProfile().getRole());
            }
        });
        backupRestoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getProfile().getRole().equals("Admin")) {
                    BackupRestorePage backupRestorePage = new BackupRestorePage();
                    backupRestorePage.setVisible(false);
                    mainFrame.dispose();
                } else JOptionPane.showMessageDialog(null, "No access,You don't have access to this function");
            }
        });
        manageBlanksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlankPage blankPage = new BlankPage();
                blankPage.setVisible(false);
                mainFrame.dispose();
            }
        });
        manageCustomerAccountsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    CustomerPage customerPage = new CustomerPage();
                    customerPage.setVisible(false);
                    mainFrame.dispose();
            }
        });
        refundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    RefundPage refundPage = new RefundPage();
                    refundPage.setVisible(false);
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage loginPage = new LoginPage();
                loginPage.setVisible(false);
                mainFrame.dispose();
            }
        });
    }

    public static Employee getProfile() {
        return profile;
    }
}
