package controllers;

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

    BudgetGUI budgetGUI = new BudgetGUI();
    IncomeGUI incomeGUI;
    AddIncomeGUI addIncomeGUI;

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
        new ExpenseGUI().delegate = this;
    }

    @Override
    public void addIncome() {
        addIncomeGUI = new AddIncomeGUI();
        addIncomeGUI.delegate = this;
    }

    @Override
    public void addExpense() {
        new AddExpenseGUI().delegate = this;
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
        try {
            Double expenseSum = Double.parseDouble(sum);
            System.out.println("Expense sum " + expenseSum);
            LocalDate expenseDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.println("Expense date " + expenseDate);
        } catch (NumberFormatException e) {
            System.out.println("Expense sum error" + e);
        } catch (DateTimeParseException d) {
            System.out.println("Expense date error" + d);
        }

    }
}
