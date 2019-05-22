package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class Product {

    private SimpleStringProperty id = new SimpleStringProperty();
    private SimpleStringProperty barcode = new SimpleStringProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty type = new SimpleStringProperty();
    private SimpleStringProperty type_id = new SimpleStringProperty();
    private SimpleStringProperty cost = new SimpleStringProperty();
    private SimpleStringProperty quantity = new SimpleStringProperty();
    private SimpleStringProperty weight = new SimpleStringProperty();
    private SimpleStringProperty cr_by = new SimpleStringProperty();
    private SimpleStringProperty date_cr = new SimpleStringProperty();
    private SimpleStringProperty suplier = new SimpleStringProperty();
    private SimpleStringProperty unit = new SimpleStringProperty();
    private SimpleStringProperty description = new SimpleStringProperty();
    private SimpleStringProperty width = new SimpleStringProperty();
    private SimpleStringProperty height = new SimpleStringProperty();
    private SimpleStringProperty color = new SimpleStringProperty();

    public Product(SimpleStringProperty id, SimpleStringProperty barcode, SimpleStringProperty name, SimpleStringProperty type, SimpleStringProperty type_id, SimpleStringProperty cost, SimpleStringProperty quantity, SimpleStringProperty weight, SimpleStringProperty cr_by, SimpleStringProperty date_cr,  SimpleStringProperty suplier, SimpleStringProperty unit, SimpleStringProperty description,
                   SimpleStringProperty width, SimpleStringProperty height, SimpleStringProperty color) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.type = type;
        this.type_id = type_id;
        this.cost = cost;
        this.quantity = quantity;
        this.weight = weight;
        this.cr_by = cr_by;
        this.date_cr = date_cr;
        this.suplier = suplier;
        this.unit = unit;
        this.description =description;
        this.width=width;
        this.height=height;
        this.color=color;
    }

    public Product() {

    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getBarcode() {
        return barcode.get();
    }

    public SimpleStringProperty barcodeProperty() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode.set(barcode);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getType_id() {
        return type_id.get();
    }

    public SimpleStringProperty type_idProperty() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id.set(type_id);
    }

    public String getCost() {
        return cost.get();
    }

    public SimpleStringProperty costProperty() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost.set(cost);
    }

    public String getQuantity() {
        return quantity.get();
    }

    public SimpleStringProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity.set(quantity);
    }

    public String getWeight() {
        return weight.get();
    }

    public SimpleStringProperty weightProperty() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight.set(weight);
    }

    public String getCr_by() {
        return cr_by.get();
    }

    public SimpleStringProperty cr_byProperty() {
        return cr_by;
    }

    public void setCr_by(String cr_by) {
        this.cr_by.set(cr_by);
    }

    public String getDate_cr() {
        return date_cr.get();
    }

    public SimpleStringProperty date_crProperty() {
        return date_cr;
    }

    public void setDate_cr(String date_cr) {
        this.date_cr.set(date_cr);
    }


    public String getSuplier() {
        return suplier.get();
    }

    public SimpleStringProperty suplier_idProperty() {
        return suplier;
    }

    public void setSuplier(String suplier) {
        this.suplier.set(suplier);
    }

    public String getUnit() {
        return unit.get();
    }

    public SimpleStringProperty unitProperty() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit.set(unit);
    }

    public SimpleStringProperty suplierProperty() {
        return suplier;
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getWidth() {
        return width.get();
    }

    public SimpleStringProperty widthProperty() {
        return width;
    }

    public void setWidth(String width) {
        this.width.set(width);
    }

    public String getHeight() {
        return height.get();
    }

    public SimpleStringProperty heightProperty() {
        return height;
    }

    public void setHeight(String height) {
        this.height.set(height);
    }

    public String getColor() {
        return color.get();
    }

    public SimpleStringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
    }
}
