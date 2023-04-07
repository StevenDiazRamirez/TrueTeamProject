import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackupRestorePage extends JFrame {
    private JPanel backupRestorePanel;
    private JTextField filepathField;
    private JButton browseButton;
    private JButton backupButton;
    private JButton browseButton2;
    private JTextField filepathField2;
    private JButton restoreButton;
    private JButton backButton;
    private JFrame backupFrame;

    private String location1;
    private String location2;
    private String filename1;
    private String filename2;
    private String start;
    private String date;

    public BackupRestorePage() {
        createPage();
    }

    public void createPage() {
        backupFrame = new JFrame("Innovotype");
        backupFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        backupFrame.setPreferredSize(new Dimension(500,500));

        backupFrame.add(backupRestorePanel);
        backupFrame.pack();
        backupFrame.setLocationRelativeTo(null);
        backupFrame.setVisible(true);

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start = System.getProperty("user.dir");
                JFileChooser path = new JFileChooser(start);
                path.showOpenDialog(BackupRestorePage.this);
                date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

                try {
                    File f = path.getSelectedFile();
                    location1 = f.getAbsolutePath();
                    location1 = location1.replace('\\', '/');
                    filename1 = location1 + "_" + date + ".sql";
                    filepathField.setText(filename1);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception);
                }
            }
        });
        backupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Process p = null;
                try {
                    Runtime runtime = Runtime.getRuntime();
                    p = runtime.exec("C:/Program Files/MySQL/MySQL Server 8.0/bin/mysqldump.exe -uroot -p8W3hMJoZ --add-drop-database -B ats -r" + filename1);
                    int processComplete = p.waitFor();
                    if (processComplete == 0) {
                        JOptionPane.showMessageDialog(null, "Backup Created!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error");
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception);
                }
            }
        });
        browseButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start = System.getProperty("user.dir");
                JFileChooser path = new JFileChooser(start);
                path.showOpenDialog(BackupRestorePage.this);

                try {
                    File f = path.getSelectedFile();
                    location2 = f.getAbsolutePath();
                    location2 = location2.replace('\\', '/');
                    filename2 = location2;
                    filepathField2.setText(filename2);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception);
                }
            }
        });
        restoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] restoreCmd = new String[]{"C:/Program Files/MySQL/MySQL Server 8.0/bin/mysql.exe","-uroot","-p8W3hMJoZ","-e","source " + filename2};
                Process process;
                try {
                    process = Runtime.getRuntime().exec(restoreCmd);
                    int processComplete = process.waitFor();
                    if (processComplete == 0) {
                        JOptionPane.showMessageDialog(null, "Restored!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error");
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception);
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.createMainPage(MainPage.getProfile());
                mainPage.setVisible(false);
                backupFrame.dispose();
            }
        });
    }

}
