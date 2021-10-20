package presentation;

import javax.swing.*;
import java.awt.*;

public class ExpenseGUI {

    private JFrame frame = new JFrame();

    ExpenseGUI() {
        JPanel panel = new JPanel();
        JButton button = new JButton("+");
        String[] array = {"a", "b", "c", "d", "e"};
        JList<String> list = new JList<>(array);

        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setLayout(new GridLayout(2, 1));
        panel.add(button);
        panel.add(list);

        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("Expense");
        frame.pack();
    }

    void setVisible() {
        frame.setVisible(true);
    }
}

