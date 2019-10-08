package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class AdminLogTable {
    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty module = new SimpleStringProperty();
    SimpleStringProperty type = new SimpleStringProperty();
    SimpleStringProperty ksum = new SimpleStringProperty();
    SimpleStringProperty kdollar = new SimpleStringProperty();
    SimpleStringProperty khr = new SimpleStringProperty();
    SimpleStringProperty csum = new SimpleStringProperty();
    SimpleStringProperty cdollar = new SimpleStringProperty();
    SimpleStringProperty chr = new SimpleStringProperty();
    SimpleStringProperty cr_by = new SimpleStringProperty();
    SimpleStringProperty date = new SimpleStringProperty();
    SimpleStringProperty comment = new SimpleStringProperty();

    public AdminLogTable(SimpleStringProperty id, SimpleStringProperty module, SimpleStringProperty type, SimpleStringProperty ksum, SimpleStringProperty kdollar, SimpleStringProperty khr, SimpleStringProperty csum, SimpleStringProperty cdollar, SimpleStringProperty chr, SimpleStringProperty cr_by, SimpleStringProperty date, SimpleStringProperty comment) {
        this.id = id;
        this.module = module;
        this.type = type;
        this.ksum = ksum;
        this.kdollar = kdollar;
        this.khr = khr;
        this.csum = csum;
        this.cdollar = cdollar;
        this.chr = chr;
        this.cr_by = cr_by;
        this.date = date;
        this.comment = comment;
    }

    public AdminLogTable() {

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

    public String getKsum() {
        return ksum.get();
    }

    public SimpleStringProperty ksumProperty() {
        return ksum;
    }

    public void setKsum(String ksum) {
        this.ksum.set(ksum);
    }

    public String getKdollar() {
        return kdollar.get();
    }

    public SimpleStringProperty kdollarProperty() {
        return kdollar;
    }

    public void setKdollar(String kdollar) {
        this.kdollar.set(kdollar);
    }

    public String getKhr() {
        return khr.get();
    }

    public SimpleStringProperty khrProperty() {
        return khr;
    }

    public void setKhr(String khr) {
        this.khr.set(khr);
    }

    public String getCsum() {
        return csum.get();
    }

    public SimpleStringProperty csumProperty() {
        return csum;
    }

    public void setCsum(String csum) {
        this.csum.set(csum);
    }

    public String getCdollar() {
        return cdollar.get();
    }

    public SimpleStringProperty cdollarProperty() {
        return cdollar;
    }

    public void setCdollar(String cdollar) {
        this.cdollar.set(cdollar);
    }

    public String getChr() {
        return chr.get();
    }

    public SimpleStringProperty chrProperty() {
        return chr;
    }

    public void setChr(String chr) {
        this.chr.set(chr);
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
