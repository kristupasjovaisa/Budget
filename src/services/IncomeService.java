package services;

import domain.models.Income;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IncomeService {

    public void add(double sum, LocalDate date) {
        Connection connection = BudgetDriverManager.shared.getConnection();
        String query = "INSERT INTO incomes(sum, date) VALUES (?, ?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setDouble(1, sum);
            pstmt.setObject(2, date);
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Income> get() {
        List<Income> incomes = new ArrayList<>();
        Connection connection = BudgetDriverManager.shared.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, sum, date FROM incomes");
            while (rs.next()) {
                int id = rs.getInt("id");
                System.out.println(id);
                double sum = rs.getDouble("sum");
                System.out.println(sum);
                LocalDate date = rs.getObject("date", LocalDate.class);
                System.out.println(date);
                incomes.add(new Income(id, sum, date));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return incomes;
    }
}
