package sample.components.models;

import javafx.beans.property.SimpleStringProperty;

public class Exchange {

    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty name = new SimpleStringProperty();
    SimpleStringProperty barcode = new SimpleStringProperty();
    SimpleStringProperty type = new SimpleStringProperty();
    SimpleStringProperty quantity = new SimpleStringProperty();
    SimpleStringProperty comment = new SimpleStringProperty();
    SimpleStringProperty cr_on = new SimpleStringProperty();
    SimpleStringProperty cr_by = new SimpleStringProperty();

    public Exchange(SimpleStringProperty id, SimpleStringProperty name, SimpleStringProperty barcode, SimpleStringProperty type, SimpleStringProperty quantity, SimpleStringProperty comment, SimpleStringProperty cr_on, SimpleStringProperty cr_by) {
        this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.type = type;
        this.quantity = quantity;
        this.comment = comment;
        this.cr_on = cr_on;
        this.cr_by = cr_by;
    }

    public Exchange() {

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

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public String getComment() {
        return comment.get();
    }

    public SimpleStringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
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
