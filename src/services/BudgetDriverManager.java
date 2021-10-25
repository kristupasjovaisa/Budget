package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class BudgetDriverManager {

    public static BudgetDriverManager shared = new BudgetDriverManager();
    private Connection connection;

    private BudgetDriverManager() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/budget", "kristupas", "Slaptazodis1/");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
       return connection;
    }
}
