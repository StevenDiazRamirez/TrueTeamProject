import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployeePage extends JFrame {
    private JPanel addEmployeePanel;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField username;
    private JTextField password;
    private JComboBox comboBox1;
    private JButton addButton;
    private JButton backButton;
    private JButton mainMenuButton;
    private JLabel lastNameField;
    private JLabel usernameField;
    private JFrame addEmployeeFrame;

    public AddEmployeePage() {
        createPage();
    }

    public void createPage() {
        addEmployeeFrame = new JFrame("Innovotype");
        addEmployeeFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        addEmployeeFrame.setPreferredSize(new Dimension(500,500));

        addEmployeeFrame.add(addEmployeePanel);
        addEmployeeFrame.pack();
        addEmployeeFrame.setLocationRelativeTo(null);
        addEmployeeFrame.setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Checks if any of the fields are empty
                if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || username.getText().isEmpty()
                || password.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Missing fields, Please fill out all the required fields" );
                    return;
                }
                //Not empty so creates an employee abject and calls addEmployeeAccount
                Employee employee = new Employee(firstName.getText(), lastName.getText(), username.getText(),
                        password.getText(), String.valueOf(comboBox1.getSelectedItem()));
                employee.setEmployeeID(Employee.getLatestEmployeeID() + 1);
                Employee.addEmployeeAccount(employee);

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageEmployeesPage manageEmployeesPage = new ManageEmployeesPage();
                manageEmployeesPage.setVisible(false);
                addEmployeeFrame.dispose();
            }
        });
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.createMainPage(MainPage.getProfile());
                mainPage.setVisible(false);
                addEmployeeFrame.dispose();
            }
        });
    }
}
