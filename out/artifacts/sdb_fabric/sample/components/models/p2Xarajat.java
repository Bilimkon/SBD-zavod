package sample.components.models;

import javafx.beans.property.SimpleStringProperty;

public class p2Xarajat {

    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty barcode = new SimpleStringProperty();
    SimpleStringProperty name = new SimpleStringProperty();
    SimpleStringProperty type = new SimpleStringProperty();
    SimpleStringProperty  quantity = new SimpleStringProperty();
    SimpleStringProperty cr_on   = new SimpleStringProperty();
    SimpleStringProperty cr_by = new SimpleStringProperty();

    public p2Xarajat() {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.cr_on = cr_on;
        this.cr_by = cr_by;
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

    public String getCr_on() {
        return cr_on.get();
    }

    public SimpleStringProperty cr_onProperty() {
        return cr_on;
    }

    public void setCr_on(String cr_on) {
        this.cr_on.set(cr_on);
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
}
