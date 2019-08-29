package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class TableB {


    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty date = new SimpleStringProperty();
    SimpleStringProperty barcode = new SimpleStringProperty();
    SimpleStringProperty name = new SimpleStringProperty();
    SimpleStringProperty type = new SimpleStringProperty();
    SimpleStringProperty color = new SimpleStringProperty();
    SimpleStringProperty quantity = new SimpleStringProperty();
    SimpleStringProperty p_quantity = new SimpleStringProperty();
    SimpleStringProperty p_name = new SimpleStringProperty();
    SimpleStringProperty p_barcode = new SimpleStringProperty();
    SimpleStringProperty cr_by = new SimpleStringProperty();





    public TableB() {

    }

    public TableB(SimpleStringProperty id, SimpleStringProperty date, SimpleStringProperty barcode, SimpleStringProperty name, SimpleStringProperty type, SimpleStringProperty color, SimpleStringProperty quantity, SimpleStringProperty p_quantity, SimpleStringProperty p_name, SimpleStringProperty p_barcode, SimpleStringProperty cr_by) {
        this.id = id;
        this.date = date;
        this.barcode = barcode;
        this.name = name;
        this.type = type;
        this.color = color;
        this.quantity = quantity;
        this.p_quantity = p_quantity;
        this.p_name = p_name;
        this.p_barcode = p_barcode;
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

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
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

    public String getColor() {
        return color.get();
    }

    public SimpleStringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
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

    public String getP_quantity() {
        return p_quantity.get();
    }

    public SimpleStringProperty p_quantityProperty() {
        return p_quantity;
    }

    public void setP_quantity(String p_quantity) {
        this.p_quantity.set(p_quantity);
    }

    public String getP_name() {
        return p_name.get();
    }

    public SimpleStringProperty p_nameProperty() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name.set(p_name);
    }

    public String getP_barcode() {
        return p_barcode.get();
    }

    public SimpleStringProperty p_barcodeProperty() {
        return p_barcode;
    }

    public void setP_barcode(String p_barcode) {
        this.p_barcode.set(p_barcode);
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
