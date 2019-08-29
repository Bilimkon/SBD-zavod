package sample.components.sell.Core.Models;

import javafx.beans.property.SimpleStringProperty;

public class CustomerName {

    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty name = new SimpleStringProperty();

    public CustomerName(SimpleStringProperty id, SimpleStringProperty name) {
        this.id = id;
        this.name = name;
    }

    public CustomerName() {

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
}
