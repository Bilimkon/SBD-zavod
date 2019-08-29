package sample.model.core;

public class production2 {
    String id;
    String barcode;
    String name;
    String type;
    String quantity;
    String cost;
    String unit;
    String color;
    String p_quantity;
    String  date;
    String user_id;
    String p_name;
    String p_barcode;

    public production2(String id, String barcode, String name, String type, String quantity, String cost, String unit, String color, String p_quantity, String date, String user_id, String p_name, String p_barcode) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.cost = cost;
        this.unit = unit;
        this.color = color;
        this.p_quantity = p_quantity;
        this.date = date;
        this.user_id = user_id;
        this.p_name = p_name;
        this.p_barcode = p_barcode;
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

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getP_quantity() {
        return p_quantity;
    }

    public void setP_quantity(String p_quantity) {
        this.p_quantity = p_quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_barcode() {
        return p_barcode;
    }

    public void setP_barcode(String p_barcode) {
        this.p_barcode = p_barcode;
    }
}
