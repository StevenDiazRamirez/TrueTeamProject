import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AllocateBlanksPage extends JFrame {
    private JPanel allocateBlankPanel;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton allocateButton;
    private JButton backButton;
    private JButton mainMenuButton;
    private JFrame allocateBlankFrame;

    public AllocateBlanksPage() {
        createPage();
    }

    public void createPage() {
        allocateBlankFrame = new JFrame("Innovotype");
        allocateBlankFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        allocateBlankFrame.setPreferredSize(new Dimension(500,500));

        allocateBlankFrame.add(allocateBlankPanel);
        allocateBlankFrame.pack();
        allocateBlankFrame.setLocationRelativeTo(null);
        allocateBlankFrame.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlankPage blankPage = new BlankPage();
                blankPage.setVisible(false);
                allocateBlankFrame.dispose();
            }
        });
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.setVisible(false);
                allocateBlankFrame.dispose();
            }
        });
        allocateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
