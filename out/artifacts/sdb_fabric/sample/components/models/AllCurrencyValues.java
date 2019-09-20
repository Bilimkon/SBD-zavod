package sample.components.models;

public class AllCurrencyValues {
    public float sum;
    public float dollar;
    public float hr;

    public AllCurrencyValues(float sum, float dollar, float hr) {
        this.sum = sum;
        this.dollar = dollar;
        this.hr = hr;
    }

    @Override
    public String toString() {
        return "AllCurrencyValues{" +
                "sum=" + sum +
                ", dollar=" + dollar +
                ", hr=" + hr +
                '}';
    }
}
