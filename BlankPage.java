import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlankPage extends JFrame {
    private JPanel blankPanel;
    private JButton viewBlanksButton;
    private JButton orderBlanksButton;
    private JButton allocateButton;
    private JButton backButton;
    private JFrame blankFrame;

    public BlankPage()  {
        createBlankPage();
    }

    public void createBlankPage() {

        blankFrame = new JFrame("Innovotype");
        blankFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        blankFrame.setPreferredSize(new Dimension(500,500));

        blankFrame.add(blankPanel);
        blankFrame.pack();
        blankFrame.setLocationRelativeTo(null);
        blankFrame.setVisible(true);

        viewBlanksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewBlanksPage viewBlanksPage = new ViewBlanksPage();
                viewBlanksPage.setVisible(false);
                blankFrame.dispose();
            }
        });
        orderBlanksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MainPage.getProfile().getRole().equals("Admin")) {
                    OrderBlanksPage orderBlanksPage = new OrderBlanksPage();
                    orderBlanksPage.setVisible(false);
                    blankFrame.dispose();
                } else JOptionPane.showMessageDialog(null, "No access,You don't have access to this function");
            }
        });
        allocateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MainPage.getProfile().getRole().equals("Manager")) {
                    AllocateBlanksPage allocateBlanksPage = new AllocateBlanksPage();
                    allocateBlanksPage.setVisible(false);
                    blankFrame.dispose();
                } else JOptionPane.showMessageDialog(null, "No access,You don't have access to this function");
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.createMainPage(MainPage.getProfile());
                mainPage.setVisible(false);
                blankFrame.dispose();
            }
        });
    }

    public JPanel getBlankPanel() {
        return blankPanel;
    }
}
