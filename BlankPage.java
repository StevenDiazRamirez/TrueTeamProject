import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlankPage {
    private JPanel blankPanel;
    private JButton viewBlanksButton;
    private JButton orderBlanksButton;
    private JButton allocateReallocateButton;
    private JButton reportBlanksButton;

    public BlankPage() {}

    public void createBlankPage() {
        viewBlanksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        orderBlanksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        allocateReallocateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        reportBlanksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getBlankPanel() {
        return blankPanel;
    }
}
