import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlankPage extends JFrame {
    private JPanel blankPanel;
    private JButton viewBlanksButton;
    private JButton orderBlanksButton;
    private JButton allocateReallocateButton;
    private JButton reportBlanksButton;
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

            }
        });
        orderBlanksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        allocateReallocateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        reportBlanksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.setVisible(false);
                blankFrame.dispose();
            }
        });
    }

    public JPanel getBlankPanel() {
        return blankPanel;
    }
}
