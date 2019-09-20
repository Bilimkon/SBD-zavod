package sample.components.sell.productTableView;

import javafx.beans.property.SimpleStringProperty;

public class OperTable {

    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty type = new SimpleStringProperty();
    SimpleStringProperty who = new SimpleStringProperty();
    SimpleStringProperty sum = new SimpleStringProperty();
    SimpleStringProperty dollar = new SimpleStringProperty();
    SimpleStringProperty hr = new SimpleStringProperty();
    SimpleStringProperty description = new SimpleStringProperty();
    SimpleStringProperty cr_by = new SimpleStringProperty();
    SimpleStringProperty date = new SimpleStringProperty();
    SimpleStringProperty currency = new SimpleStringProperty();
    SimpleStringProperty percentage = new SimpleStringProperty();
    SimpleStringProperty subtotal = new SimpleStringProperty();

    public OperTable(SimpleStringProperty id, SimpleStringProperty type, SimpleStringProperty who, SimpleStringProperty sum, SimpleStringProperty dollar, SimpleStringProperty hr, SimpleStringProperty description, SimpleStringProperty cr_by, SimpleStringProperty date, SimpleStringProperty currency, SimpleStringProperty percentage, SimpleStringProperty subtotal) {
        this.id = id;
        this.type = type;
        this.who = who;
        this.sum = sum;
        this.dollar = dollar;
        this.hr = hr;
        this.description = description;
        this.cr_by = cr_by;
        this.date = date;
        this.currency = currency;
        this.percentage = percentage;
        this.subtotal = subtotal;
    }

    public OperTable() {

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

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
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

    public String getSum() {
        return sum.get();
    }

    public SimpleStringProperty sumProperty() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum.set(sum);
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

    public String getHr() {
        return hr.get();
    }

    public SimpleStringProperty hrProperty() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr.set(hr);
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

    public String getCurrency() {
        return currency.get();
    }

    public SimpleStringProperty currencyProperty() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency.set(currency);
    }

    public String getPercentage() {
        return percentage.get();
    }

    public SimpleStringProperty percentageProperty() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage.set(percentage);
    }

    public String getSubtotal() {
        return subtotal.get();
    }

    public SimpleStringProperty subtotalProperty() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal.set(subtotal);
    }
}
