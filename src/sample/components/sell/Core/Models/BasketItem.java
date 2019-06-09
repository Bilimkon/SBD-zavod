package sample.components.sell.Core.Models;

public class BasketItem {
    /**
     * @param barcode
     */
    private String barcode;
    private String title;
    private float cost = 0;
    private int amount = 1;
    private boolean isAccepted = true;

    public static BasketItem getInstance() {
        return new BasketItem();
    }

    private BasketItem() {

    }

    public void setAll(String barcode, String title, float cost, int amount, boolean isAccepted) {
        this.barcode = barcode;
        this.title = title;
        this.cost = cost;
        this.amount = amount;
        this.isAccepted = isAccepted;
    }

    public BasketItem(String barcode, String title, float cost, int amount, boolean isAccepted) {
        this.barcode = barcode;
        this.title = title;
        this.cost = cost;
        this.amount = amount;
        this.isAccepted = isAccepted;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public boolean isEqual(String barcode) {
        return getBarcode().equals(barcode);
    }
}
