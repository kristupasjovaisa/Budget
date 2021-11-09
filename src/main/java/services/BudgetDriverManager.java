package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class BudgetDriverManager {

    private Connection connection;
    private static BudgetDriverManager instance;

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

    public static BudgetDriverManager getInstance() throws SQLException {
        if (instance == null) {
            instance = new BudgetDriverManager();
        } else if (instance.getConnection().isClosed()) {
            instance = new BudgetDriverManager();
        }
        return instance;
    }
}
