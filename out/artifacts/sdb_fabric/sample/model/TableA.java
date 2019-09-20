package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class TableA {

    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty barcode = new SimpleStringProperty();
    SimpleStringProperty name = new SimpleStringProperty();
    SimpleStringProperty type = new SimpleStringProperty();
    SimpleStringProperty quantity = new SimpleStringProperty();

    public TableA(SimpleStringProperty id, SimpleStringProperty barcode, SimpleStringProperty name, SimpleStringProperty type, SimpleStringProperty quantity) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
    }

    public TableA() {

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

    public String getQuantity() {
        return quantity.get();
    }

    public SimpleStringProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity.set(quantity);
    }
}
