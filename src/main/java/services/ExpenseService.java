package services;

import domain.models.Expense;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseService {

    IBudgetDriverManager driverManager;

    public ExpenseService(IBudgetDriverManager driverManager) {
        this.driverManager = driverManager;
    }

    public boolean add(double sum, LocalDate date) {
        try {
            Connection connection = driverManager.getConnection();
            String query = "INSERT INTO expenses(sum, date) VALUES (?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setDouble(1, sum);
            pstmt.setObject(2, date);
            boolean success =  pstmt.execute();
            connection.close();
            return success;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public List<Expense> get() {
        List<Expense> expense = new ArrayList<>();
        try {
            Connection connection = driverManager.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, sum, date FROM expenses");
            while (rs.next()) {
                int id = rs.getInt("id");
                double sum = rs.getDouble("sum");
                LocalDate date = rs.getObject("date", LocalDate.class);
                expense.add(new Expense(id, sum, date));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return expense;
    }

    public boolean update(int id, double sum, LocalDate date) {
        try {
            Connection connection = driverManager.getConnection();
            String query = "UPDATE expenses SET sum = ?, date = ? WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setDouble(1, sum);
            pstmt.setObject(2, date);
            pstmt.setInt(3, id);
            boolean success = pstmt.execute();
            connection.close();
            return success;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
}
