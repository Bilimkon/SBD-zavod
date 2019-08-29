package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class History {

    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty barcode = new SimpleStringProperty();
    SimpleStringProperty p_id = new SimpleStringProperty();
    SimpleStringProperty name = new SimpleStringProperty();
    SimpleStringProperty type = new SimpleStringProperty();
    SimpleStringProperty quantity = new SimpleStringProperty();
    SimpleStringProperty seller_id = new SimpleStringProperty();
    SimpleStringProperty cost = new SimpleStringProperty();
    SimpleStringProperty perCost = new SimpleStringProperty();
    SimpleStringProperty date_cr = new SimpleStringProperty();
    SimpleStringProperty cr_by = new SimpleStringProperty();
    SimpleStringProperty customer_id = new SimpleStringProperty();
    SimpleStringProperty sellAction_id = new SimpleStringProperty();

    public History(SimpleStringProperty id, SimpleStringProperty barcode, SimpleStringProperty p_id, SimpleStringProperty name, SimpleStringProperty type, SimpleStringProperty quantity, SimpleStringProperty seller_id, SimpleStringProperty cost, SimpleStringProperty perCost, SimpleStringProperty date_cr, SimpleStringProperty cr_by, SimpleStringProperty customer_id, SimpleStringProperty sellAction_id) {
        this.id = id;
        this.barcode = barcode;
        this.p_id = p_id;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.seller_id = seller_id;
        this.cost = cost;
        this.perCost = perCost;
        this.date_cr = date_cr;
        this.cr_by = cr_by;
        this.customer_id = customer_id;
        this.sellAction_id = sellAction_id;
    }

    public History() {

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

    public String getBarcode() {
        return barcode.get();
    }

    public SimpleStringProperty barcodeProperty() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode.set(barcode);
    }

    public String getP_id() {
        return p_id.get();
    }

    public SimpleStringProperty p_idProperty() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id.set(p_id);
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

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getQuantity() {
        return quantity.get();
    }

    public SimpleStringProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity.set(quantity);
    }

    public String getSeller_id() {
        return seller_id.get();
    }

    public SimpleStringProperty seller_idProperty() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id.set(seller_id);
    }

    public String getCost() {
        return cost.get();
    }

    public SimpleStringProperty costProperty() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost.set(cost);
    }

    public String getPerCost() {
        return perCost.get();
    }

    public SimpleStringProperty perCostProperty() {
        return perCost;
    }

    public void setPerCost(String perCost) {
        this.perCost.set(perCost);
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

    public String getCustomer_id() {
        return customer_id.get();
    }

    public SimpleStringProperty customer_idProperty() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id.set(customer_id);
    }

    public String getSellAction_id() {
        return sellAction_id.get();
    }

    public SimpleStringProperty sellAction_idProperty() {
        return sellAction_id;
    }

    public void setSellAction_id(String sellAction_id) {
        this.sellAction_id.set(sellAction_id);
    }
}
