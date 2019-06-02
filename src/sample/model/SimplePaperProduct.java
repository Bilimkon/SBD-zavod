package sample.model;

public class SimplePaperProduct {
    String id;
    String barcode;
    String name;
    String type;
    String quantity;
    String p_quantity;

    public SimplePaperProduct(String id, String barcode, String name, String type, String quantity, String p_quantity) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.p_quantity = p_quantity;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getP_quantity() {
        return p_quantity;
    }

    public void setP_quantity(String p_quantity) {
        this.p_quantity = p_quantity;
    }
}
