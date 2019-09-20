package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class TableExchangeDollar {

    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty hr = new SimpleStringProperty();
    SimpleStringProperty dollar = new SimpleStringProperty();
    SimpleStringProperty rate = new SimpleStringProperty();
    SimpleStringProperty who = new SimpleStringProperty();
    SimpleStringProperty description = new SimpleStringProperty();
    SimpleStringProperty sana = new SimpleStringProperty();

    public TableExchangeDollar(SimpleStringProperty id, SimpleStringProperty hr, SimpleStringProperty dollar, SimpleStringProperty rate, SimpleStringProperty who, SimpleStringProperty description, SimpleStringProperty sana) {
        this.id = id;
        this.hr = hr;
        this.dollar = dollar;
        this.rate = rate;
        this.who = who;
        this.description = description;
        this.sana = sana;
    }

    public TableExchangeDollar() {

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

    public String getHr() {
        return hr.get();
    }

    public SimpleStringProperty hrProperty() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr.set(hr);
    }

    public String getDollar() {
        return dollar.get();
    }

    public SimpleStringProperty dollarProperty() {
        return dollar;
    }

    public void setDollar(String dollar) {
        this.dollar.set(dollar);
    }

    public String getRate() {
        return rate.get();
    }

    public SimpleStringProperty rateProperty() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate.set(rate);
    }

    public String getWho() {
        return who.get();
    }

    public SimpleStringProperty whoProperty() {
        return who;
    }

    public void setWho(String who) {
        this.who.set(who);
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

    public String getSana() {
        return sana.get();
    }

    public SimpleStringProperty sanaProperty() {
        return sana;
    }

    public void setSana(String sana) {
        this.sana.set(sana);
    }
}
