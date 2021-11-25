package presentation;

import domain.controllers.BudgetController;
import domain.models.Income;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditIncomeGUI implements ActionListener {
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JTextField sumTextField = new JTextField(16);
    private JTextField dateTextField = new JTextField(16);
    private JButton saveButton = new JButton("SAVE");
    private JButton deleteButton = new JButton("DELETE");
    private JLabel sumLabel = new JLabel("Sum ");
    private JLabel dateLabel = new JLabel("Date");
    private JLabel errorSumLabel = new JLabel("Enter a number");
    private JLabel errorDateLabel = new JLabel("Enter date by format YYYY-MM-DD");

    public BudgetController delegate;
    private Income income;

    public EditIncomeGUI(Income income) {
        this.income = income;
        setupPanel();
        setupSumRow();
        setupDateRow();
        setupButtons();
        setupFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(saveButton.getActionCommand())) {
            delegate.incomeEditSaveButtonTapped(income.getId(), sumTextField.getText(), dateTextField.getText());
        } else {
            delegate.incomeDeleteButtonTapped(income.getId());
        }
    }

    public void setSumErrorVisibility(boolean visivility) {
        errorSumLabel.setVisible(visivility);
        frame.pack();
    }

    public void setDateErrorVisibility(boolean visibility) {
        errorDateLabel.setVisible(visibility);
        frame.pack();
    }

    public void destroy() {
        frame.dispose();
    }

    private void setupFrame() {
        frame.setTitle("Edit incomes");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setupPanel() {
        frame.add(panel, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setLayout(new GridLayout(4, 1));
    }

    private void setupButtons() {
        panel.add(saveButton);
        saveButton.addActionListener(this);
        deleteButton();
    }

    private void deleteButton() {
        panel.add(deleteButton);
        deleteButton.addActionListener(this);
    }

    private void setupSumRow() {
        sumTextField.setText(Double.toString(income.getSum()));
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rowPanel.add(sumLabel);
        rowPanel.add(sumTextField);
        rowPanel.add(errorSumLabel);
        panel.add(rowPanel);
        errorSumLabel.setVisible(false);
    }

    private void setupDateRow() {
        dateTextField.setText(income.getDate().toString());
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rowPanel.add(dateLabel);
        rowPanel.add(dateTextField);
        rowPanel.add(errorDateLabel);
        panel.add(rowPanel);
        errorDateLabel.setVisible(false);
    }
}