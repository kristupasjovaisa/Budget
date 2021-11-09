package presentation;

import domain.models.Income;
import presentation.delegates.IncomeGUIDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class IncomeGUI implements ActionListener {

    private JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JButton button = new JButton("+");
    JList<Income> list = new JList<>();
    DefaultListModel<Income> listModel = new DefaultListModel<>();
    public IncomeGUIDelegate delegate;

    public IncomeGUI() {
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setLayout(new GridLayout(2, 1));
        panel.add(button);
        panel.add(list);
        setUpList();

        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("Incomes");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        delegate.addIncome();
    }

    public void seedIncomes(List<Income> incomes) {
        listModel.clear();
        for (Income income : incomes) {
            listModel.addElement(income);
        }
        frame.pack();
    }

    private void setUpList() {
        list.setModel(listModel);
    }
}

