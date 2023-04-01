import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportPage extends JFrame {
    private JPanel reportPanel;
    private JButton stockTurnoverReportButton;
    private JButton individualInterlineReportButton;
    private JButton interlineReportButton;
    private JButton individualDomesticReportButton;
    private JButton domesticReportButton;
    private JFrame reportFrame;

    public ReportPage() {
        createReportPage();
    }

    public void createReportPage() {

        reportFrame = new JFrame("Innovotype");
        reportFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        reportFrame.setPreferredSize(new Dimension(500,500));

        reportFrame.add(reportPanel);
        reportFrame.pack();
        reportFrame.setLocationRelativeTo(null);
        reportFrame.setVisible(true);

        stockTurnoverReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        individualInterlineReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        interlineReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        individualDomesticReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        domesticReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getReportPanel() {
        return reportPanel;
    }
}
