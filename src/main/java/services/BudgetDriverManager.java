package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class BudgetDriverManager implements IBudgetDriverManager {

    private static BudgetDriverManager instance;

    private BudgetDriverManager(){}

    public Connection getConnection() {
        try {
             return DriverManager.getConnection("jdbc:mysql://localhost:3306/budget", "kristupas", "Slaptazodis1/");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BudgetDriverManager getInstance() {
        try {
            if (instance == null) {
                instance = new BudgetDriverManager();
            } else if (instance.getConnection().isClosed()) {
                instance = new BudgetDriverManager();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return instance;
    }
}

