import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewBlanksPage extends JFrame {
    private JPanel viewBlankPanel;
    private JTable table1;
    private JButton backButton;
    private JButton mainMenuButton;
    private JFrame viewBlankFrame;

    public ViewBlanksPage() {
        createPage();
    }

    public void createPage() {
        viewBlankFrame = new JFrame("Innovotype");
        viewBlankFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        viewBlankFrame.setPreferredSize(new Dimension(500,500));

        viewBlankFrame.add(viewBlankPanel);
        viewBlankFrame.pack();
        viewBlankFrame.setLocationRelativeTo(null);
        viewBlankFrame.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlankPage blankPage = new BlankPage();
                blankPage.setVisible(false);
                viewBlankFrame.dispose();
            }
        });
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.setVisible(false);
                viewBlankFrame.dispose();
            }
        });
    }
}
