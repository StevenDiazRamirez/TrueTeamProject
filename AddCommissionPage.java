import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddCommissionPage extends JFrame {
    private JPanel addCommissionPanel;
    private JTextField amountField;
    private JComboBox comboBox1;
    private JButton addButton;
    private JButton backButton;
    private JButton mainMenuButton;
    private JTextField endDateField;
    private JFrame addCommissionFrame;
    private Date endDate;

    public AddCommissionPage() {
        createPage();
    }

    public void createPage() {
        addCommissionFrame = new JFrame("Innovotype");
        addCommissionFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        addCommissionFrame.setPreferredSize(new Dimension(500,500));

        addCommissionFrame.add(addCommissionPanel);
        addCommissionFrame.pack();
        addCommissionFrame.setLocationRelativeTo(null);
        addCommissionFrame.setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Checks if any of the fields are empty
                if (!amountField.getText().isEmpty() || !String.valueOf(comboBox1.getSelectedItem()).isEmpty() || !endDateField.getText().isEmpty()) {
                    //Not empty so gets the date and creates a commission
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        endDate = dateFormat.parse(endDateField.getText());
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    Commission.addCommission(Float.parseFloat(amountField.getText()), Integer.parseInt(comboBox1.getSelectedItem().toString()), endDate);
                    JOptionPane.showMessageDialog(null, "Commission added!");
                } else JOptionPane.showMessageDialog(null, "Missing details, Please fill out all the required fields");
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommissionPage commissionPage = new CommissionPage();
                commissionPage.setVisible(false);
                addCommissionFrame.dispose();
            }
        });
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.createMainPage(MainPage.getProfile());
                mainPage.setVisible(false);
                addCommissionFrame.dispose();
            }
        });
    }
}
