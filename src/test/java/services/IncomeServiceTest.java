package services;

import domain.models.Income;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IncomeServiceTest {

    @Test
    void get() throws Exception {
        IncomeService incomeService = new IncomeService(new MockBudgetDriveManager());
        List<Income> incomes = incomeService.get();
        assertEquals(10, incomes.get(0).getId());
        assertEquals(11.4, incomes.get(0).getSum());
    }
}

class MockBudgetDriveManager implements IBudgetDriverManager {

    @Override
    public Connection getConnection() {
        try {
            ResultSet resultSet = Mockito.mock(ResultSet.class);
            Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
            Mockito.when(resultSet.getInt("id")).thenReturn(10);
            Mockito.when(resultSet.getDouble("sum")).thenReturn(11.4);

            Statement statement = Mockito.mock(Statement.class);
            Mockito.when(statement.executeQuery("SELECT id, sum, date FROM incomes")).thenReturn(resultSet);

            Connection jdbcConnection = Mockito.mock(Connection.class);
            Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);

            return jdbcConnection;
        } catch (SQLException e) {
            assert (false);
        }

        return null;
    }
}