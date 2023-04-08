import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageEmployeesPage extends JFrame {
    private JPanel manageEmployeePanel;
    private JButton listOfEmployeesButton;
    private JButton addAccountButton;
    private JButton backButton;
    private JFrame manageEmployeeFrame;

    ManageEmployeesPage() {
        createPage();
    }

    public void createPage() {
        manageEmployeeFrame = new JFrame("Innovotype");
        manageEmployeeFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        manageEmployeeFrame.setPreferredSize(new Dimension(500,500));

        manageEmployeeFrame.add(manageEmployeePanel);
        manageEmployeeFrame.pack();
        manageEmployeeFrame.setLocationRelativeTo(null);
        manageEmployeeFrame.setVisible(true);

        listOfEmployeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListEmployeePage listEmployeePage = new ListEmployeePage();
                listEmployeePage.setVisible(false);
                manageEmployeeFrame.dispose();
            }
        });
        addAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEmployeePage addEmployeePage = new AddEmployeePage();
                addEmployeePage.setVisible(false);
                manageEmployeeFrame.dispose();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.createMainPage(MainPage.getProfile());
                mainPage.setVisible(false);
                manageEmployeeFrame.dispose();
            }
        });
    }
}
