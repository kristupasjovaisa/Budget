package presentation;

import domain.models.Expense;
import presentation.delegates.ExpenseGUIDelegate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ExpenseGUI implements ActionListener {

    private JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JButton button = new JButton("+");
    DefaultListModel<Expense> listModel = new DefaultListModel<>();
    JList<Expense> list = new JList<>();
    public ExpenseGUIDelegate delegate;

    public ExpenseGUI() {
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setLayout(new GridLayout(2, 1));
        panel.add(button);
        panel.add(list);
        setUpList();
        button.addActionListener(this);

        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("Expense");
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        delegate.addExpense();
    }

    public void seedExpense(List<Expense> expenses) {
        listModel.clear();
        for (Expense expense : expenses) {
            listModel.addElement(expense);
        }
        frame.pack();
    }

    private void setUpList() {
        list.setModel(listModel);
    }
}

