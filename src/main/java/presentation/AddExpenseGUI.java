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
    private JLabel errorSumLabel = new JLabel("Enter a number");
    private JLabel errorDateLabel = new JLabel("Enter date by format YYYY-MM-DD");

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

    public void setSumErrorVisibility(boolean visivility) {
        errorSumLabel.setVisible(visivility);
        frame.pack();
    }

    public void setDateVisibility(boolean visibility) {
        errorDateLabel.setVisible(visibility);
        frame.pack();
    }

    public void destroy(){
        frame.dispose();
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
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rowPanel.add(sumLabel);
        rowPanel.add(sumTextField);
        rowPanel.add(errorSumLabel);
        panel.add(rowPanel);
        errorSumLabel.setVisible(false);
    }

    private void setupDateRow(){
        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rowPanel.add(dateLabel);
        rowPanel.add(dateTextField);
        rowPanel.add(errorDateLabel);
        panel.add(rowPanel);
        errorDateLabel.setVisible(false);
    }
}
