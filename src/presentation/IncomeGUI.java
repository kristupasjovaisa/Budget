package presentation;

import javax.swing.*;
import java.awt.*;

public class IncomeGUI {

    private JFrame frame = new JFrame();

    IncomeGUI() {
        JPanel panel = new JPanel();
        JButton button = new JButton("+");
        String[] array = {"1", "2", "3", "4", "5"};
        JList<String> list = new JList<>(array);


        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setLayout(new GridLayout(2, 1));
        panel.add(button);
        panel.add(list);

        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("Incomes");
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    void setVisible() {
        frame.setVisible(true);
    }
}
