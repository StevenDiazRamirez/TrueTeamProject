import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginPage extends JFrame {
    private JTextField usernameTextField;
    private JPanel loginPanel;
    private JButton loginButton;
    private JPasswordField passwordField1;
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
                if (Employee.login(usernameTextField.getText(), String.valueOf(passwordField1.getPassword()))) {
                    Employee employee = LoginPage.loggedInProfile(usernameTextField.getText(), String.valueOf(passwordField1.getPassword()));
                    MainPage mainPage = new MainPage();
                    mainPage.setVisible(false);
                    mainPage.createMainPage(employee);
                    frame.dispose();
                }
            }
        });
    }

    public static Employee loggedInProfile(String username, String password) {
        try {
            Connection con = DBSConnection.getConnection();

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM EmployeeAccount WHERE employeePassword = " + '"' + password + '"' + "AND Username = " + '"' + username + '"');
            while (rs.next()) {
                Employee employee = new Employee(rs.getString("First Name"),
                        rs.getString("Last Name"),
                        rs.getString("Username"),
                        rs.getString("employeePassword"),
                        rs.getString("AccountType"));
                employee.setEmployeeID(rs.getInt("EmployeeID"));
                return employee;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
