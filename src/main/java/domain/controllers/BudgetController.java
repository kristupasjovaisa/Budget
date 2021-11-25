package domain.controllers;

import domain.models.Expense;
import domain.models.Income;
import presentation.*;
import services.BudgetDriverManager;
import services.ExpenseService;
import services.IncomeService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BudgetController {

    BudgetGUI budgetGUI = new BudgetGUI();
    IncomeGUI incomeGUI;
    AddIncomeGUI addIncomeGUI;
    ExpenseGUI expenseGUI;
    AddExpenseGUI addExpenseGUI;
    BalanceGUI balanceGUI;
    EditIncomeGUI editIncomeGUI;
    EditExpenseGUI editExpenseGUI;
    IncomeService incomeService = new IncomeService(BudgetDriverManager.getInstance());
    ExpenseService expenseService = new ExpenseService(BudgetDriverManager.getInstance());

    public BudgetController() {
        budgetGUI.delegate = this;
    }

    public void incomeButtonTapped() {
        incomeGUI = new IncomeGUI();
        incomeGUI.delegate = this;
        incomeGUI.seedIncomes(incomeService.get());
    }

    public void expenseButtonTapped() {
        expenseGUI = new ExpenseGUI();
        expenseGUI.delegate = this;
        expenseGUI.seedExpense(expenseService.get());
    }

    public void balanceButtonTapped() {
        balanceGUI = new BalanceGUI();
        double balance = 0;
        for (Income income : incomeService.get()) {
            balance += income.getSum();
        }

        for (Expense expense : expenseService.get()) {
            balance -= expense.getSum();
        }
        balanceGUI.setupBalance(balance);
    }

    public void addIncome() {
        addIncomeGUI = new AddIncomeGUI();
        addIncomeGUI.delegate = this;
    }

    public void addExpense() {
        addExpenseGUI = new AddExpenseGUI();
        addExpenseGUI.delegate = this;
    }

    public void incomeSaveButtonTapped(String sum, String date) {
        if (sum.isEmpty() || date.isEmpty()) {
            return;
        }
        try {
            Double incomeSum = Double.parseDouble(sum);
            addIncomeGUI.setSumErrorVisibility(false);
            LocalDate incomeDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            addIncomeGUI.setDateVisibility(false);
            addIncomeGUI.destroy();
            incomeService.add(incomeSum, incomeDate);

            incomeGUI.seedIncomes(incomeService.get());

        } catch (NumberFormatException e) {
            addIncomeGUI.setSumErrorVisibility(true);
        } catch (DateTimeParseException d) {
            addIncomeGUI.setDateVisibility(true);
        }
    }

    public void incomeEditSaveButtonTapped(int id, String sum, String date) {
        if (sum.isEmpty() || date.isEmpty()) {
            return;
        }
        try {
            Double incomeSum = Double.parseDouble(sum);
            LocalDate incomeDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            editIncomeGUI.setSumErrorVisibility(false);
            editIncomeGUI.setDateErrorVisibility(false);
            editIncomeGUI.destroy();
            incomeService.update(id, incomeSum, incomeDate);

            incomeGUI.seedIncomes(incomeService.get());

        } catch (NumberFormatException e) {
            editIncomeGUI.setSumErrorVisibility(true);
        } catch (DateTimeParseException d) {
            editIncomeGUI.setDateErrorVisibility(true);
        }
    }

    public void incomeDeleteButtonTapped(int id) {
        editIncomeGUI.destroy();
        incomeService.delete(id);

        incomeGUI.seedIncomes(incomeService.get());
    }

    public void expenseSaveButtonTapt(String sum, String date) {
        if (sum.isEmpty() || date.isEmpty()) {
            return;
        }
        try {
            Double expenseSum = Double.parseDouble(sum);
            addExpenseGUI.setSumErrorVisibility(false);
            LocalDate expenseDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            addExpenseGUI.setDateVisibility(false);
            addExpenseGUI.destroy();
            expenseService.add(expenseSum, expenseDate);

            expenseGUI.seedExpense(expenseService.get());
        } catch (NumberFormatException e) {
            addExpenseGUI.setSumErrorVisibility(true);
        } catch (DateTimeParseException d) {
            addExpenseGUI.setDateVisibility(true);
        }
    }

    public void expenseEditSaveButtonTapped(int id, String sum, String date) {
        if (sum.isEmpty() || date.isEmpty()) {
            return;
        }
        try {
            Double expenseSum = Double.parseDouble(sum);
            LocalDate expenseDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            editExpenseGUI.setSumErrorVisibility(false);
            editExpenseGUI.setDateErrorVisibility(false);
            editExpenseGUI.destroy();
            expenseService.update(id, expenseSum, expenseDate);

            expenseGUI.seedExpense(expenseService.get());

        } catch (NumberFormatException e) {
            editExpenseGUI.setSumErrorVisibility(true);
        } catch (DateTimeParseException d) {
            editExpenseGUI.setDateErrorVisibility(true);
        }
    }

    public void expenseDeleteButtonTapped(int id) {
        editExpenseGUI.destroy();
        expenseService.delete(id);

        expenseGUI.seedExpense(expenseService.get());
    }

    public void incomesOnRowTapped(Income selectedValue) {
        editIncomeGUI = new EditIncomeGUI(selectedValue);
        editIncomeGUI.delegate = this;
    }

    public void expenseOnRowTapped(Expense selectedValue){
        editExpenseGUI = new EditExpenseGUI(selectedValue);
        editExpenseGUI.delegate = this;
    }
}