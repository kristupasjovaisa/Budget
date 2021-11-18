package presentation;

import javax.swing.*;
import java.awt.*;

public class BalanceGUI {

    private JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel balanceLabel = new JLabel();

    public BalanceGUI() {
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setLayout(new GridLayout(3, 1));

        setupLabels();
        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("Balance");
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    private void setupLabels() {
        panel.add(balanceLabel);
    }

    public void setupBalance(double balance) {
        balanceLabel.setText("Balansas: " + balance);
        frame.pack();
    }
}
