import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class AllocateBlanksPage extends JFrame {
    private JPanel allocateBlankPanel;
    private JComboBox comboBox1;
    private JTextField amountField;
    private JTextField employeeIDFIeld;
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
                mainPage.createMainPage(MainPage.getProfile());
                mainPage.setVisible(false);
                allocateBlankFrame.dispose();
            }
        });
        allocateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Blank.allocateBlank(Integer.parseInt(comboBox1.getSelectedItem().toString()), Integer.parseInt(amountField.getText()), Integer.parseInt(employeeIDFIeld.getText()), 0);
                System.out.println("DONE!");
            }
        });
    }
}
