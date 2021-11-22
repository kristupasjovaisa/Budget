package domain.models;

import java.time.LocalDate;
import java.util.Random;

public class Income {
    private int id;
    private double sum;
    private LocalDate date;

    public Income(int id, double sum, LocalDate date) {
        this.id = id;
        this.sum = sum;
        this.date = date;
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
