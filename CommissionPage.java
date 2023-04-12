import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommissionPage extends JFrame {
    private JPanel commissionPanel;
    private JButton addButton;
    private JButton viewButton;
    private JButton backButton;
    private JFrame commissionFrame;

    public CommissionPage() {
        createPage();
    }

    public void createPage() {
        commissionFrame = new JFrame("Innovotype");
        commissionFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        commissionFrame.setPreferredSize(new Dimension(500,500));

        commissionFrame.add(commissionPanel);
        commissionFrame.pack();
        commissionFrame.setLocationRelativeTo(null);
        commissionFrame.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.createMainPage(MainPage.getProfile());
                mainPage.setVisible(false);
                commissionFrame.dispose();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MainPage.getProfile().getRole().equals("Admin") || MainPage.getProfile().getRole().equals("Manager")) {
                    AddCommissionPage addCommissionPage = new AddCommissionPage();
                    addCommissionPage.setVisible(false);
                    commissionFrame.dispose();
                } else JOptionPane.showMessageDialog(null, "No access,You don't have access to this function");
            }
        });
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewCommissionPage viewCommissionPage = new ViewCommissionPage();
                viewCommissionPage.setVisible(false);
                commissionFrame.dispose();
            }
        });
    }
}
