package domain.models;

import java.time.LocalDate;
import java.util.Random;

public class Expense {

    private int id;
    private double sum;
    private LocalDate date;

    public Expense(int id, double sum, LocalDate date) {
        this.id = id;
        this.sum = sum;
        this.date = date;
    }

    public Expense(double sum, LocalDate date) {
        this(new Random().nextInt(1000000) + 1000000, sum, date);
    }

    public int getId() {
        return id;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", sum=" + sum +
                ", date=" + date;
    }
}
