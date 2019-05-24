package sample.components.models;

import javafx.beans.property.SimpleStringProperty;

public class Type {

    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty name = new SimpleStringProperty();
    SimpleStringProperty unit = new SimpleStringProperty();
    SimpleStringProperty info = new SimpleStringProperty();
    SimpleStringProperty date = new SimpleStringProperty();
    SimpleStringProperty cr_by = new SimpleStringProperty();

    public Type(SimpleStringProperty id, SimpleStringProperty name, SimpleStringProperty unit, SimpleStringProperty info,
                SimpleStringProperty date,SimpleStringProperty cr_by) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.info = info;
        this.date = date;
        this.cr_by = cr_by;
    }

    public Type() {

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

    public String getCr_by() {
        return cr_by.get();
    }

    public SimpleStringProperty cr_byProperty() {
        return cr_by;
    }

    public void setCr_by(String cr_by) {
        this.cr_by.set(cr_by);
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

    public String getUnit() {
        return unit.get();
    }

    public SimpleStringProperty unitProperty() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit.set(unit);
    }

    public String getInfo() {
        return info.get();
    }

    public SimpleStringProperty infoProperty() {
        return info;
    }

    public void setInfo(String info) {
        this.info.set(info);
    }
}
