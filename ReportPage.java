import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportPage {
    private JPanel reportPanel;
    private JButton stockTurnoverReportButton;
    private JButton individualInterlineReportButton;
    private JButton interlineReportButton;
    private JButton individualDomesticReportButton;
    private JButton domesticReportButton;

    public ReportPage() {}

    public void createReportPage() {
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
