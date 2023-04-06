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
    private JButton backButton;
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
                StockTurnoverReportPage stockTurnoverReportPage = new StockTurnoverReportPage();
                stockTurnoverReportPage.setVisible(false);
                reportFrame.dispose();
            }
        });
        individualInterlineReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IndividualInterlineReportPage individualInterlineReportPage = new IndividualInterlineReportPage();
                individualInterlineReportPage.setVisible(false);
                reportFrame.dispose();
            }
        });
        interlineReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterlineReportPage interlineReportPage = new InterlineReportPage();
                interlineReportPage.setVisible(false);
                reportFrame.dispose();
            }
        });
        individualDomesticReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IndividualDomesticReportPage individualDomesticReportPage = new IndividualDomesticReportPage();
                individualDomesticReportPage.setVisible(false);
                reportFrame.dispose();
            }
        });
        domesticReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DomesticReportPage domesticReportPage = new DomesticReportPage();
                domesticReportPage.setVisible(false);
                reportFrame.dispose();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage mainPage = new MainPage();
                mainPage.createMainPage(MainPage.getProfile());
                mainPage.setVisible(false);
                reportFrame.dispose();
            }
        });
    }

    public JPanel getReportPanel() {
        return reportPanel;
    }
}
