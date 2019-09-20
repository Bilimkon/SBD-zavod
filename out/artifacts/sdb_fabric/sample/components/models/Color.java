package sample.components.models;

import javafx.beans.property.SimpleStringProperty;

public class Color {

    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty name = new SimpleStringProperty();
    SimpleStringProperty description = new SimpleStringProperty();

    public Color(SimpleStringProperty id, SimpleStringProperty name, SimpleStringProperty description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Color() {

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

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}
