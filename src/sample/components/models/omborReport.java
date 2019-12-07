package sample.components.models;

import javafx.beans.property.SimpleStringProperty;

public class omborReport {
    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty type = new SimpleStringProperty();
    SimpleStringProperty invoiceName = new SimpleStringProperty();
    SimpleStringProperty company = new SimpleStringProperty();
    SimpleStringProperty account = new SimpleStringProperty();
    SimpleStringProperty contract = new SimpleStringProperty();
    SimpleStringProperty dollar = new SimpleStringProperty();
    SimpleStringProperty sum = new SimpleStringProperty();
    SimpleStringProperty hr = new SimpleStringProperty();
    SimpleStringProperty maxsulot = new SimpleStringProperty();
    SimpleStringProperty izox = new SimpleStringProperty();
    SimpleStringProperty cr_on = new SimpleStringProperty();
    SimpleStringProperty cr_by = new SimpleStringProperty();
    SimpleStringProperty i_id = new SimpleStringProperty();

    public omborReport(SimpleStringProperty id, SimpleStringProperty type, SimpleStringProperty invoiceName, SimpleStringProperty company, SimpleStringProperty account, SimpleStringProperty contract, SimpleStringProperty dollar, SimpleStringProperty sum, SimpleStringProperty hr, SimpleStringProperty maxsulot, SimpleStringProperty izox, SimpleStringProperty cr_on, SimpleStringProperty cr_by, SimpleStringProperty i_id) {
        this.id = id;
        this.type = type;
        this.invoiceName = invoiceName;
        this.company = company;
        this.account = account;
        this.contract = contract;
        this.dollar = dollar;
        this.sum = sum;
        this.hr = hr;
        this.maxsulot = maxsulot;
        this.izox = izox;
        this.cr_on = cr_on;
        this.cr_by = cr_by;
        this.i_id = i_id;
    }

    public omborReport() {

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

    public String getInvoiceName() {
        return invoiceName.get();
    }

    public SimpleStringProperty invoiceNameProperty() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName.set(invoiceName);
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

    public String getAccount() {
        return account.get();
    }

    public SimpleStringProperty accountProperty() {
        return account;
    }

    public void setAccount(String account) {
        this.account.set(account);
    }

    public String getContract() {
        return contract.get();
    }

    public SimpleStringProperty contractProperty() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract.set(contract);
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

    public String getSum() {
        return sum.get();
    }

    public SimpleStringProperty sumProperty() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum.set(sum);
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

    public String getMaxsulot() {
        return maxsulot.get();
    }

    public SimpleStringProperty maxsulotProperty() {
        return maxsulot;
    }

    public void setMaxsulot(String maxsulot) {
        this.maxsulot.set(maxsulot);
    }

    public String getIzox() {
        return izox.get();
    }

    public SimpleStringProperty izoxProperty() {
        return izox;
    }

    public void setIzox(String izox) {
        this.izox.set(izox);
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

    public String getI_id() {
        return i_id.get();
    }

    public SimpleStringProperty i_idProperty() {
        return i_id;
    }

    public void setI_id(String i_id) {
        this.i_id.set(i_id);
    }
}
