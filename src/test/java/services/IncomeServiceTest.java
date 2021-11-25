package services;

import domain.models.Income;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IncomeServiceTest {
    @Test
    void add() {
        class MockBudgetDriveManager implements IBudgetDriverManager {
            @Override
            public Connection getConnection() {
                try {
                    PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
                    Mockito.when(preparedStatement.execute()).thenReturn(true);
                    Connection jdbcConnection = Mockito.mock(Connection.class);
                    Mockito.when(jdbcConnection.prepareStatement("INSERT INTO incomes(sum, date) VALUES (?, ?)")).thenReturn(preparedStatement);
                    return jdbcConnection;
                } catch (SQLException e) {
                    assert (false);
                }
                return null;
            }
        }

        IncomeService incomeService = new IncomeService(new MockBudgetDriveManager());
        boolean output = incomeService.add(2, LocalDate.now());
        boolean expected = true;
        assertEquals(expected, output);
    }

    @Test
    void get() throws Exception {
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
        IncomeService incomeService = new IncomeService(new MockBudgetDriveManager());
        List<Income> incomes = incomeService.get();
        assertEquals(10, incomes.get(0).getId());
        assertEquals(11.4, incomes.get(0).getSum());
    }

    @Test
    void update() {
        class MockBudgetDriveManager implements IBudgetDriverManager {
            @Override
            public Connection getConnection() {
                try {
                    PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
                    Mockito.when(preparedStatement.execute()).thenReturn(true);
                    Connection jdbcConnection = Mockito.mock(Connection.class);
                    Mockito.when(jdbcConnection.prepareStatement("UPDATE incomes SET sum = ?, date = ? WHERE id = ?")).thenReturn(preparedStatement);
                    return jdbcConnection;
                } catch (SQLException e) {
                    assert (false);
                }
                return null;
            }
        }

        IncomeService incomeService = new IncomeService(new MockBudgetDriveManager());
        boolean output = incomeService.update(2,20,LocalDate.now());
        boolean expected = true;
        assertEquals(expected, output);
    }

    @Test
    void delete() {
        class MockBudgetDriveManager implements IBudgetDriverManager {
            @Override
            public Connection getConnection() {
                try {
                    PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
                    Mockito.when(preparedStatement.execute()).thenReturn(true);
                    Connection jdbcConnection = Mockito.mock(Connection.class);
                    Mockito.when(jdbcConnection.prepareStatement("DELETE FROM incomes WHERE id = ?")).thenReturn(preparedStatement);
                    return jdbcConnection;
                } catch (SQLException e) {
                    assert (false);
                }
                return null;
            }
        }

        IncomeService incomeService = new IncomeService(new MockBudgetDriveManager());
        boolean output = incomeService.delete(3);
        boolean expected = true;
        assertEquals(expected, output);
    }
}

