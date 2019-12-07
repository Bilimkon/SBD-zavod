package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class LogTable {

    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty module = new SimpleStringProperty();
    SimpleStringProperty type = new SimpleStringProperty();
    SimpleStringProperty cost = new SimpleStringProperty();
    SimpleStringProperty cr_by = new SimpleStringProperty();
    SimpleStringProperty date = new SimpleStringProperty();
    SimpleStringProperty comment = new SimpleStringProperty();
    SimpleStringProperty summa = new SimpleStringProperty();

    public LogTable(SimpleStringProperty id, SimpleStringProperty module, SimpleStringProperty type, SimpleStringProperty cost, SimpleStringProperty cr_by, SimpleStringProperty date, SimpleStringProperty comment, SimpleStringProperty summa) {
        this.id = id;
        this.module = module;
        this.type = type;
        this.cost = cost;
        this.cr_by = cr_by;
        this.date = date;
        this.comment = comment;
        this.summa = summa;
    }

    public LogTable() {

    }

    public String getSumma() {
        return summa.get();
    }

    public SimpleStringProperty summaProperty() {
        return summa;
    }

    public void setSumma(String summa) {
        this.summa.set(summa);
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

    public String getModule() {
        return module.get();
    }

    public SimpleStringProperty moduleProperty() {
        return module;
    }

    public void setModule(String module) {
        this.module.set(module);
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

    public String getCost() {
        return cost.get();
    }

    public SimpleStringProperty costProperty() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost.set(cost);
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

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
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
}
