package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class Report {

    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty type = new SimpleStringProperty();
    SimpleStringProperty who = new SimpleStringProperty();
    SimpleStringProperty sum = new SimpleStringProperty();
    SimpleStringProperty dollar = new SimpleStringProperty();
    SimpleStringProperty hr = new SimpleStringProperty();
    SimpleStringProperty psum = new SimpleStringProperty();
    SimpleStringProperty pdollar = new SimpleStringProperty();
    SimpleStringProperty phr = new SimpleStringProperty();
    SimpleStringProperty sale = new SimpleStringProperty();
    SimpleStringProperty product = new SimpleStringProperty();
    SimpleStringProperty comment = new SimpleStringProperty();
    SimpleStringProperty cr_on = new SimpleStringProperty();
    SimpleStringProperty cr_by = new SimpleStringProperty();
    SimpleStringProperty s_id = new SimpleStringProperty();

    public Report(SimpleStringProperty id, SimpleStringProperty type, SimpleStringProperty who, SimpleStringProperty sum, SimpleStringProperty dollar, SimpleStringProperty hr, SimpleStringProperty psum, SimpleStringProperty pdollar, SimpleStringProperty phr, SimpleStringProperty sale, SimpleStringProperty product, SimpleStringProperty comment, SimpleStringProperty cr_on, SimpleStringProperty cr_by, SimpleStringProperty s_id) {
        this.id = id;
        this.type = type;
        this.who = who;
        this.sum = sum;
        this.dollar = dollar;
        this.hr = hr;
        this.psum = psum;
        this.pdollar = pdollar;
        this.phr = phr;
        this.sale = sale;
        this.product = product;
        this.comment = comment;
        this.cr_on = cr_on;
        this.cr_by = cr_by;
        this.s_id = s_id;
    }

    public Report() {

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

    public String getProduct() {
        return product.get();
    }

    public SimpleStringProperty productProperty() {
        return product;
    }

    public void setProduct(String product) {
        this.product.set(product);
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

    public String getCr_on() {
        return cr_on.get();
    }

    public SimpleStringProperty cr_onProperty() {
        return cr_on;
    }

    public void setCr_on(String cr_on) {
        this.cr_on.set(cr_on);
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

    public String getS_id() {
        return s_id.get();
    }

    public SimpleStringProperty s_idProperty() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id.set(s_id);
    }
}
