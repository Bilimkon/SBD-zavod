package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class Debt {

    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty sum = new SimpleStringProperty();
    SimpleStringProperty dollar = new SimpleStringProperty();
    SimpleStringProperty hr = new SimpleStringProperty();
    SimpleStringProperty psum = new SimpleStringProperty();
    SimpleStringProperty pdollar = new SimpleStringProperty();
    SimpleStringProperty phr = new SimpleStringProperty();
    SimpleStringProperty sale = new SimpleStringProperty();
    SimpleStringProperty currency = new SimpleStringProperty();
    SimpleStringProperty customer_id = new SimpleStringProperty();
    SimpleStringProperty cr_by = new SimpleStringProperty();
    SimpleStringProperty date_cr = new SimpleStringProperty();
    SimpleStringProperty comment = new SimpleStringProperty();

    public Debt(SimpleStringProperty id, SimpleStringProperty sum, SimpleStringProperty dollar, SimpleStringProperty hr, SimpleStringProperty psum, SimpleStringProperty pdollar, SimpleStringProperty phr, SimpleStringProperty sale, SimpleStringProperty currency, SimpleStringProperty customer_id, SimpleStringProperty cr_by, SimpleStringProperty date_cr, SimpleStringProperty comment) {
        this.id = id;
        this.sum = sum;
        this.dollar = dollar;
        this.hr = hr;
        this.psum = psum;
        this.pdollar = pdollar;
        this.phr = phr;
        this.sale = sale;
        this.currency = currency;
        this.customer_id = customer_id;
        this.cr_by = cr_by;
        this.date_cr = date_cr;
        this.comment = comment;
    }

    public Debt() {

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

    public String getPsum() {
        return psum.get();
    }

    public SimpleStringProperty psumProperty() {
        return psum;
    }

    public void setPsum(String psum) {
        this.psum.set(psum);
    }

    public String getPdollar() {
        return pdollar.get();
    }

    public SimpleStringProperty pdollarProperty() {
        return pdollar;
    }

    public void setPdollar(String pdollar) {
        this.pdollar.set(pdollar);
    }

    public String getPhr() {
        return phr.get();
    }

    public SimpleStringProperty phrProperty() {
        return phr;
    }

    public void setPhr(String phr) {
        this.phr.set(phr);
    }

    public String getSale() {
        return sale.get();
    }

    public SimpleStringProperty saleProperty() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale.set(sale);
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

    public String getCustomer_id() {
        return customer_id.get();
    }

    public SimpleStringProperty customer_idProperty() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id.set(customer_id);
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

    public String getDate_cr() {
        return date_cr.get();
    }

    public SimpleStringProperty date_crProperty() {
        return date_cr;
    }

    public void setDate_cr(String date_cr) {
        this.date_cr.set(date_cr);
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
