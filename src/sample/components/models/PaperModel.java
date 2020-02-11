package sample.components.models;

public class PaperModel {
    int id;
    String name;
    String barcode;
    int type;
    int quantity;
    String cr_on;
    int user_id;

    public PaperModel(int id, String name, String barcode, int type, int quantity, String cr_on, int user_id) {
        this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.type = type;
        this.quantity = quantity;
        this.cr_on = cr_on;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCr_on() {
        return cr_on;
    }

    public void setCr_on(String cr_on) {
        this.cr_on = cr_on;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
