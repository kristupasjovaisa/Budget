package presentation;

import domain.controllers.BudgetController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BudgetGUI implements ActionListener {

    private enum Button {
        INCOME,
        EXPENSE,
        BALANCE
    }

    public BudgetController delegate;

    public BudgetGUI() {
        JFrame frame = new JFrame();
        JPanel incomePanel = new JPanel();
        JButton incomeButton = new JButton("Pajamos");
        JButton expenseButton = new JButton("Islaidos");
        JButton balanceButton = new JButton("Balansas");

        incomePanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        incomePanel.setLayout(new GridLayout(3, 1));
        incomePanel.add(incomeButton);
        incomePanel.add(expenseButton);
        incomePanel.add(balanceButton);

        incomeButton.addActionListener(this);
        incomeButton.setActionCommand(Button.INCOME.name());

        expenseButton.addActionListener(this);
        expenseButton.setActionCommand(Button.EXPENSE.name());

        balanceButton.addActionListener(this);
        balanceButton.setActionCommand(Button.BALANCE.name());

        frame.add(incomePanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Biudzetas");
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Button.valueOf(e.getActionCommand())) {
            case INCOME:
                delegate.incomeButtonTapped();
                break;
            case EXPENSE:
                delegate.expenseButtonTapped();
                break;

            case BALANCE:
                delegate.balanceButtonTapped();
                break;
        }
    }
}
