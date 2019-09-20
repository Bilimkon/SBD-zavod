package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class AccountXarajat {

    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty schot = new SimpleStringProperty();
    SimpleStringProperty  sDate = new SimpleStringProperty();
    SimpleStringProperty firma = new SimpleStringProperty();
    SimpleStringProperty shartnoma = new SimpleStringProperty();
    SimpleStringProperty hr = new SimpleStringProperty();
    SimpleStringProperty dollar = new SimpleStringProperty();
    SimpleStringProperty description = new SimpleStringProperty();

    public AccountXarajat(SimpleStringProperty id, SimpleStringProperty schot, SimpleStringProperty sDate, SimpleStringProperty firma, SimpleStringProperty shartnoma, SimpleStringProperty hr, SimpleStringProperty dollar, SimpleStringProperty description) {
        this.id = id;
        this.schot = schot;
        this.sDate = sDate;
        this.firma = firma;
        this.shartnoma = shartnoma;
        this.hr = hr;
        this.dollar = dollar;
        this.description = description;
    }

    public AccountXarajat() {

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

    public String getSchot() {
        return schot.get();
    }

    public SimpleStringProperty schotProperty() {
        return schot;
    }

    public void setSchot(String schot) {
        this.schot.set(schot);
    }

    public String getsDate() {
        return sDate.get();
    }

    public SimpleStringProperty sDateProperty() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate.set(sDate);
    }

    public String getFirma() {
        return firma.get();
    }

    public SimpleStringProperty firmaProperty() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma.set(firma);
    }

    public String getShartnoma() {
        return shartnoma.get();
    }

    public SimpleStringProperty shartnomaProperty() {
        return shartnoma;
    }

    public void setShartnoma(String shartnoma) {
        this.shartnoma.set(shartnoma);
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
