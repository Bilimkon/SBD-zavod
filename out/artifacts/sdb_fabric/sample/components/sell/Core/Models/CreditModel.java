package sample.components.sell.Core.Models;

public class CreditModel {
    private double summa;
    private int id;

    public CreditModel(double summa, int id) {
        this.summa = summa;
        this.id = id;
    }

    public CreditModel(Float credit_sum, String trim) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSumma() {
        return summa;
    }

    public void setSumma(float summa) {
        this.summa = summa;
    }
}
