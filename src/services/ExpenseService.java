package services;

import domain.models.Expense;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseService {

    public void add(double sum, LocalDate date) {
        Connection connection = BudgetDriverManager.shared.getConnection();
        String query = "INSERT INTO expenses(sum, date) VALUES (?, ?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setDouble(1, sum);
            pstmt.setObject(2, date);
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Expense> get() {
        List<Expense> expense = new ArrayList<>();
        Connection connection = BudgetDriverManager.shared.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, sum, date FROM expenses");
            while (rs.next()) {
                int id = rs.getInt("id");
                System.out.println(id);
                double sum = rs.getDouble("sum");
                System.out.println(sum);
                LocalDate date = rs.getObject("date", LocalDate.class);
                System.out.println(date);
                expense.add(new Expense(id, sum, date));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return expense;
    }
}
