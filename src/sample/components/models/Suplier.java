package sample.components.models;

import javafx.beans.property.SimpleStringProperty;

public class Suplier {

    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty companyName = new SimpleStringProperty();
    SimpleStringProperty account = new SimpleStringProperty();
    SimpleStringProperty phone = new SimpleStringProperty();
    SimpleStringProperty info = new SimpleStringProperty();
    SimpleStringProperty date_cr = new SimpleStringProperty();
    SimpleStringProperty cr_by = new SimpleStringProperty();

    public Suplier(SimpleStringProperty id, SimpleStringProperty companyName, SimpleStringProperty account, SimpleStringProperty phone, SimpleStringProperty info, SimpleStringProperty date_cr, SimpleStringProperty cr_by) {
        this.id = id;
        this.companyName = companyName;
        this.account = account;
        this.phone = phone;
        this.info = info;
        this.date_cr = date_cr;
        this.cr_by = cr_by;
    }

    public Suplier() {

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

    public String getCompanyName() {
        return companyName.get();
    }

    public SimpleStringProperty companyNameProperty() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }

    public String getAccount() {
        return account.get();
    }

    public SimpleStringProperty accountProperty() {
        return account;
    }

    public void setAccount(String account) {
        this.account.set(account);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
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

    public String getDate_cr() {
        return date_cr.get();
    }

    public SimpleStringProperty date_crProperty() {
        return date_cr;
    }

    public void setDate_cr(String date_cr) {
        this.date_cr.set(date_cr);
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
}


