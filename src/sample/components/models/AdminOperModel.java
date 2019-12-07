package sample.components.models;

import javafx.beans.property.SimpleStringProperty;

public class AdminOperModel {
    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty type = new SimpleStringProperty();
    SimpleStringProperty who = new SimpleStringProperty();
    SimpleStringProperty sum = new SimpleStringProperty();
    SimpleStringProperty dollar = new SimpleStringProperty();
    SimpleStringProperty hr = new SimpleStringProperty();
    SimpleStringProperty vhr = new SimpleStringProperty();
    SimpleStringProperty comment = new SimpleStringProperty();
    SimpleStringProperty cr_on = new SimpleStringProperty();

    public AdminOperModel(SimpleStringProperty id, SimpleStringProperty type, SimpleStringProperty who, SimpleStringProperty sum, SimpleStringProperty dollar, SimpleStringProperty hr, SimpleStringProperty vhr, SimpleStringProperty comment, SimpleStringProperty cr_on) {
        this.id = id;
        this.type = type;
        this.who = who;
        this.sum = sum;
        this.dollar = dollar;
        this.hr = hr;
        this.vhr = vhr;
        this.comment = comment;
        this.cr_on = cr_on;
    }

    public AdminOperModel() {

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

    public String getVhr() {
        return vhr.get();
    }

    public SimpleStringProperty vhrProperty() {
        return vhr;
    }

    public void setVhr(String vhr) {
        this.vhr.set(vhr);
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
}
