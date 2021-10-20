package presentation;

public class BudgetPresenter implements BudgetGUIDelegate{

    IncomeGUI incomeGUI = new IncomeGUI();
    ExpenseGUI expenseGUI = new ExpenseGUI();

    public BudgetPresenter() {
        BudgetGUI budgetGUI = new BudgetGUI();
        budgetGUI.delegate = this;
    }

    @Override
    public void incomeButtonTapped() {
        incomeGUI.setVisible();
    }

    @Override
    public void expenseButtonTapped() {
        expenseGUI.setVisible();
    }
}
