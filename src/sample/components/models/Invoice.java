package sample.components.models;

import javafx.beans.property.SimpleStringProperty;

public class Invoice {
    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty name = new SimpleStringProperty();
    SimpleStringProperty company = new SimpleStringProperty();
    SimpleStringProperty date = new SimpleStringProperty();
    SimpleStringProperty currency = new SimpleStringProperty();
    SimpleStringProperty total_price = new SimpleStringProperty();

    public Invoice(SimpleStringProperty id, SimpleStringProperty name, SimpleStringProperty company, SimpleStringProperty date, SimpleStringProperty currency, SimpleStringProperty total_price) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.date = date;
        this.currency = currency;
        this.total_price = total_price;
    }

    public Invoice() {

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

    public String getCompany() {
        return company.get();
    }

    public SimpleStringProperty companyProperty() {
        return company;
    }

    public void setCompany(String company) {
        this.company.set(company);
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

    public String getCurrency() {
        return currency.get();
    }

    public SimpleStringProperty currencyProperty() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency.set(currency);
    }

    public String getTotal_price() {
        return total_price.get();
    }

    public SimpleStringProperty total_priceProperty() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price.set(total_price);
    }
}
