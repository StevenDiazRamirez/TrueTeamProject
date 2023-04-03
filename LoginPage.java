import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JTextField usernameTextField;
    private JPanel loginPanel;
    private JPasswordField passwordPasswordField;
    private JButton loginButton;
    private JFrame frame;

    public LoginPage() {
        start();
    }

    public void start() {

        frame = new JFrame("Innovotype");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500,500));

        frame.add(loginPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //check if details match goes here
                MainPage mainPage = new MainPage();
                mainPage.setVisible(false);
                frame.dispose();

            }
        });

        usernameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        passwordPasswordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginPage();
            }
        });
    }
}
