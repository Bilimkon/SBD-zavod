package sample.components.sell.Core;

/*
 Humoyun Qo'rg'onov  SBD(Software Business Development)
  */

public class Product {

    private int id;
    private String barcode;
    private String type;
    private String name;
    private int quantity;
    private String cost;
    private String unit;
    private String date;
    private String username;
    private String description;

    public Product() {

    }

    public static Product getInstance() {
        return new Product();
    }

    public Product(int id, String barcode, String type, String name, int quantity, String cost, String unit, String date, String username, String description) {
        this.id = id;
        this.barcode = barcode;
        this.type = type;
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
        this.unit = unit;
        this.date = date;
        this.username = username;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String
                .format("Product [id=%s,barcode=%s ,type=%s, name=%s,quantity=%s, cost=%s, unit=%s,date=%s,username=%s,description=%s]",
                        id, barcode, type, name, quantity, cost, unit, date, username, description);
    }
}