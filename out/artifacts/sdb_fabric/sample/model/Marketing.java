package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class Marketing {
    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty company = new SimpleStringProperty();
    SimpleStringProperty name = new SimpleStringProperty();
    SimpleStringProperty barcode_o = new SimpleStringProperty();
    SimpleStringProperty barcode = new SimpleStringProperty();
    SimpleStringProperty color = new SimpleStringProperty();
    SimpleStringProperty cost = new SimpleStringProperty();

    public Marketing(SimpleStringProperty id, SimpleStringProperty company, SimpleStringProperty name, SimpleStringProperty barcode_o, SimpleStringProperty barcode, SimpleStringProperty color, SimpleStringProperty cost) {
        this.id = id;
        this.company = company;
        this.name = name;
        this.barcode_o = barcode_o;
        this.barcode = barcode;
        this.color = color;
        this.cost = cost;
    }

    public Marketing() {

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

    public String getCompany() {
        return company.get();
    }

    public SimpleStringProperty companyProperty() {
        return company;
    }

    public void setCompany(String company) {
        this.company.set(company);
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

    public String getBarcode_o() {
        return barcode_o.get();
    }

    public SimpleStringProperty barcode_oProperty() {
        return barcode_o;
    }

    public void setBarcode_o(String barcode_o) {
        this.barcode_o.set(barcode_o);
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

    public String getColor() {
        return color.get();
    }

    public SimpleStringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
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
}
