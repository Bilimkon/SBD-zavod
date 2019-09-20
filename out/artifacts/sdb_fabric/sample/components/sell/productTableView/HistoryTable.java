package sample.components.sell.productTableView;

import javafx.beans.property.SimpleStringProperty;

public class HistoryTable {
    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty username = new SimpleStringProperty();
    SimpleStringProperty customer = new SimpleStringProperty();
    SimpleStringProperty barcode = new SimpleStringProperty();
    SimpleStringProperty product_name = new SimpleStringProperty();
    SimpleStringProperty type_name = new SimpleStringProperty();
    SimpleStringProperty total_cost = new SimpleStringProperty();
    SimpleStringProperty cost = new SimpleStringProperty();
    SimpleStringProperty quantity = new SimpleStringProperty();
    SimpleStringProperty date = new SimpleStringProperty();
    SimpleStringProperty sell_action_id = new SimpleStringProperty();

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getCustomer() {
        return customer.get();
    }

    public SimpleStringProperty customerProperty() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer.set(customer);
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

    public String getProduct_name() {
        return product_name.get();
    }

    public SimpleStringProperty product_nameProperty() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name.set(product_name);
    }

    public String getType_name() {
        return type_name.get();
    }

    public SimpleStringProperty type_nameProperty() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name.set(type_name);
    }

    public String getTotal_cost() {
        return total_cost.get();
    }

    public SimpleStringProperty total_costProperty() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost.set(total_cost);
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

    public String getQuantity() {
        return quantity.get();
    }

    public SimpleStringProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity.set(quantity);
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

    public String getSell_action_id() {
        return sell_action_id.get();
    }

    public SimpleStringProperty sell_action_idProperty() {
        return sell_action_id;
    }

    public void setSell_action_id(String sell_action_id) {
        this.sell_action_id.set(sell_action_id);
    }
}


