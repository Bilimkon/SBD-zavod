package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class ShartnomaTable {

    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty company = new SimpleStringProperty();
    SimpleStringProperty name = new SimpleStringProperty();
    SimpleStringProperty date = new SimpleStringProperty();

    public ShartnomaTable(SimpleStringProperty id, SimpleStringProperty company, SimpleStringProperty name, SimpleStringProperty date) {
        this.id = id;
        this.company = company;
        this.name = name;
        this.date = date;
    }

    public ShartnomaTable() {

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

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}
