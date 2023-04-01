import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame {

    private JPanel mainPanel;
    private JButton manageReportsButton;
    private JButton manageCommissionRateButton;
    private JButton createSaleButton;
    private JButton backupButton;
    private JButton manageCustomerAccountsButton;
    private JButton manageBlanksButton;
    private JButton refundButton;
    private JButton restoreButton;
    private JFrame mainFrame;

    public MainPage() {
        createMainPage();
    }

    public void createMainPage() {

        mainFrame = new JFrame("Innovotype");
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(500,500));

        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        manageReportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportPage reportPage = new ReportPage();
                reportPage.setVisible(true);
                mainFrame.dispose();
            }
        });
        manageCommissionRateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        createSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        backupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("backup Complete!");

            }
        });
        manageBlanksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        manageCustomerAccountsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        refundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
        restoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Restoring latest backup..");
            }
        });
    }
}
