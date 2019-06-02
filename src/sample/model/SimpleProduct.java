package sample.model;

public class SimpleProduct {

    String  id;
    String  barcode;
    String  name;
    String  type;
    String  cost;
    String  quantity;
    String  weight;
    String  color;
    String  width;
    String  height;
    String  unit;

    public SimpleProduct(String id, String barcode, String name, String type, String cost, String quantity, String weight, String color, String width, String height, String unit) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.quantity = quantity;
        this.weight = weight;
        this.color = color;
        this.width = width;
        this.height = height;
        this.unit = unit;
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

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
