import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommissionPage extends JFrame {
    private JPanel commissionPanel;
    private JTable table1;
    private JButton addButton;
    private JButton deleteButton;
    private JButton editButton;
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
                mainPage.setVisible(false);
                commissionFrame.dispose();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
