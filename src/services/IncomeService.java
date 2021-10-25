package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

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
}
