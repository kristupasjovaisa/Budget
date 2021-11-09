package services;

import domain.models.Income;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IncomeService {

    public void add(double sum, LocalDate date) {
        try {
            Connection connection = BudgetDriverManager.getInstance().getConnection();
            String query = "INSERT INTO incomes(sum, date) VALUES (?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setDouble(1, sum);
            pstmt.setObject(2, date);
            pstmt.execute();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Income> get() {
        List<Income> incomes = new ArrayList<>();
        try {
            Connection connection = BudgetDriverManager.getInstance().getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, sum, date FROM incomes");
            while (rs.next()) {
                int id = rs.getInt("id");
                double sum = rs.getDouble("sum");
                LocalDate date = rs.getObject("date", LocalDate.class);
                incomes.add(new Income(id, sum, date));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return incomes;
    }
}
