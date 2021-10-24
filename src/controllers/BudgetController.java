package controllers;

import domain.models.Expense;
import domain.models.Income;
import presentation.*;
import presentation.delegates.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


public class BudgetController implements BudgetGUIDelegate, IncomeGUIDelegate, ExpenseGUIDelegate, AddIncomeGUIDelegate, AddExpenseGUIDelegate {

    List<Income> incomeList = new ArrayList<>();
    List<Expense> expenseList = new ArrayList<>();


    BudgetGUI budgetGUI = new BudgetGUI();
    IncomeGUI incomeGUI;
    AddIncomeGUI addIncomeGUI;
    ExpenseGUI expenseGUI;
    AddExpenseGUI addExpenseGUI;

    public BudgetController() {
        budgetGUI.delegate = this;
    }

    @Override
    public void incomeButtonTapped() {
        incomeGUI = new IncomeGUI();
        incomeGUI.delegate = this;
    }

    @Override
    public void expenseButtonTapped() {
        expenseGUI = new ExpenseGUI();
        expenseGUI.delegate = this;
    }

    @Override
    public void addIncome() {
        addIncomeGUI = new AddIncomeGUI();
        addIncomeGUI.delegate = this;
    }

    @Override
    public void addExpense() {
        addExpenseGUI = new AddExpenseGUI();
        addExpenseGUI.delegate = this;
    }

    @Override
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

            incomeList.add(new Income(incomeSum, incomeDate));
            incomeGUI.seedIncomes(incomeList);

        } catch (NumberFormatException e) {
            addIncomeGUI.setSumErrorVisibility(true);
        } catch (DateTimeParseException d) {
            addIncomeGUI.setDateVisibility(true);
        }
    }

    @Override
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

        expenseList.add(new Expense(expenseSum,expenseDate));
        expenseGUI.seedExpense(expenseList);
        } catch (NumberFormatException e) {
        addExpenseGUI.setSumErrorVisibility(true);
        } catch (DateTimeParseException d) {
         addExpenseGUI.setDateVisibility(true);
        }

    }
}
