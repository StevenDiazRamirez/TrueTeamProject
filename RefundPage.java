import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefundPage extends JFrame {
    private JPanel refundPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton confirmButton;
    private JButton backButton;
    private JFrame refundFrame;

    public RefundPage() {
        createRefundPage();
    }

    public void createRefundPage() {
        refundFrame = new JFrame("Innovotype");
        refundFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        refundFrame.setPreferredSize(new Dimension(500,500));

        refundFrame.add(refundPanel);
        refundFrame.pack();
        refundFrame.setLocationRelativeTo(null);
        refundFrame.setVisible(true);

        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        textField2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.createMainPage(MainPage.getProfile());
                mainPage.setVisible(false);
                refundFrame.dispose();
            }
        });
    }

}
