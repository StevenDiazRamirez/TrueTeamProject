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

    /**
     * Creates an employee object of the logged in account, this employee object is linked to main page
     * which allows for other functions to work
     * @param username
     * @param password
     * @return
     */
    public static Employee loggedInProfile(String username, String password) {
        try {
            Connection con = DBSConnection.getConnection();

            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM EmployeeAccount WHERE Password = " + '"' + password + '"' + "AND Username = " + '"' + username + '"');
            while (rs.next()) {
                Employee employee = new Employee(rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("AccountType"));
                employee.setEmployeeID(rs.getInt("EmployeeID"));
                return employee;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public void setUsernameTextField(JTextField usernameTextField) {
        this.usernameTextField = usernameTextField;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public void setPasswordField1(JPasswordField passwordField1) {
        this.passwordField1 = passwordField1;
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
