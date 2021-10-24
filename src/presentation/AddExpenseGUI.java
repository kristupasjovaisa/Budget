package presentation;
import presentation.delegates.AddExpenseGUIDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddExpenseGUI implements ActionListener {

    private JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    private JTextField sumTextField = new JTextField(16);
    private JTextField dateTextField = new JTextField(16);
    private JButton saveButton = new JButton("SAVE");
    private JLabel sumLabel = new JLabel("Sum ");
    private JLabel dateLabel = new JLabel("Date");

    public AddExpenseGUIDelegate delegate;

    public AddExpenseGUI(){
        setUpPanel();
        setupSumRow();
        setupDateRow();
        setUpButtons();
        setUpFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        delegate.expenseSaveButtonTapt(sumTextField.getText(), dateTextField.getText());
    }

    private void setUpFrame() {
        frame.setTitle("Add expense");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setUpPanel() {
        frame.add(panel, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setLayout(new GridLayout(3, 1));
    }

    private void setUpButtons() {
        panel.add(saveButton);
        saveButton.addActionListener(this);
    }

    private void setupSumRow(){
        JPanel rowPanel = new JPanel();
        rowPanel.add(sumLabel);
        rowPanel.add(sumTextField);
        panel.add(rowPanel);
    }

    private void setupDateRow(){
        JPanel rowPanel = new JPanel();
        rowPanel.add(dateLabel);
        rowPanel.add(dateTextField);
        panel.add(rowPanel);
    }
}
