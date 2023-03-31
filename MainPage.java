import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage {

    BlankPage blankPage = new BlankPage();
    ReportPage reportPage = new ReportPage();
    CustomerPage customerPage = new CustomerPage();

    private JPanel mainPanel;
    private JButton manageReportsButton;
    private JButton manageCommissionRateButton;
    private JButton createSaleButton;
    private JButton backupButton;
    private JButton manageCustomerAccountsButton;
    private JButton manageBlanksButton;
    private JButton refundButton;
    private JButton restoreButton;

    public MainPage() {}

    public void createMainPage() {
        JFrame frame = new JFrame("Innovotype");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(mainPanel);
        manageReportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(mainPanel);
                frame.add(reportPage.getReportPanel());
                frame.validate();
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

            }
        });
        manageBlanksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(mainPanel);
                frame.add(blankPage.getBlankPanel());
                frame.validate();
            }
        });
        manageCustomerAccountsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(mainPanel);
                frame.add(customerPage.getCustomerPanel());
                frame.validate();
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

            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        MainPage mp = new MainPage();
        mp.createMainPage();
    }
}
