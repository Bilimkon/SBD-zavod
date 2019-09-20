package sample.model;

public class Dsp2 {
    String id;
    String name;
    String type;
    String barcode;
    String quantity;
    String cost;
    String unit;
    String description;

    public Dsp2(String id, String name, String type, String barcode, String quantity, String cost, String unit, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.barcode = barcode;
        this.quantity = quantity;
        this.cost = cost;
        this.unit = unit;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
