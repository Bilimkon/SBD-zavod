package sample.model.core;

public class revert {
    String id;
    String barcode;
    String quantity;
    String cost;
    String customer_id;

    public revert(String id, String barcode, String quantity, String cost, String customer_id) {
        this.id = id;
        this.barcode = barcode;
        this.quantity = quantity;
        this.cost = cost;
        this.customer_id = customer_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
}
