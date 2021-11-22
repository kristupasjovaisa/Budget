package presentation;

import domain.controllers.BudgetController;
import domain.models.Income;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class IncomeGUI implements ActionListener {

    private JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JButton button = new JButton("+");
    JList<Income> list = new JList<>();
    DefaultListModel<Income> listModel = new DefaultListModel<>();

    public BudgetController delegate;

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
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    jListValueChanged();
                }
            }
        });
    }

    private void jListValueChanged() {
        delegate.onRowTapped(list.getSelectedValue());
    }
}