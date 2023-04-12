import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderBlanksPage extends JFrame {
    private JPanel orderBlankPanel;
    private JComboBox comboBox1;
    private JTextField amountField;
    private JButton addBlanksButton;
    private JButton backButton;
    private JButton mainMenuButton;
    private JFrame orderBlankFrame;

    public OrderBlanksPage() {
        createPage();
    }

    public void createPage() {
        orderBlankFrame = new JFrame("Innovotype");
        orderBlankFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        orderBlankFrame.setPreferredSize(new Dimension(500,500));

        orderBlankFrame.add(orderBlankPanel);
        orderBlankFrame.pack();
        orderBlankFrame.setLocationRelativeTo(null);
        orderBlankFrame.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlankPage blankPage = new BlankPage();
                blankPage.setVisible(false);
                orderBlankFrame.dispose();
            }
        });
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.createMainPage(MainPage.getProfile());
                mainPage.setVisible(false);
                orderBlankFrame.dispose();
            }
        });
        addBlanksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Blank.orderBlanks(Integer.parseInt(amountField.getText()), Integer.parseInt(comboBox1.getSelectedItem().toString()));
                JOptionPane.showMessageDialog(null, "Done!");
            }
        });

    }
}
