package sample.components.sell.productTableView;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class ProductTable {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty  barcode = new SimpleStringProperty();
    private SimpleStringProperty  name = new SimpleStringProperty();
    private SimpleStringProperty  type = new SimpleStringProperty();
    private SimpleIntegerProperty  type_id = new SimpleIntegerProperty();
    private SimpleStringProperty   cost = new SimpleStringProperty();
    private SimpleIntegerProperty  quantity = new SimpleIntegerProperty();
    private SimpleStringProperty  cost_o = new SimpleStringProperty();
    private SimpleStringProperty  date_c = new SimpleStringProperty();
    private SimpleStringProperty  date_o = new SimpleStringProperty();
    private SimpleIntegerProperty  cr_by = new SimpleIntegerProperty();
    private SimpleIntegerProperty  up_by = new SimpleIntegerProperty();
    private SimpleStringProperty  date_cr = new SimpleStringProperty();
    private SimpleStringProperty  date_up = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
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

    public int getType_id() {
        return type_id.get();
    }

    public SimpleIntegerProperty type_idProperty() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id.set(type_id);
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

    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public String getCost_o() {
        return cost_o.get();
    }

    public SimpleStringProperty cost_oProperty() {
        return cost_o;
    }

    public void setCost_o(String cost_o) {
        this.cost_o.set(cost_o);
    }

    public String getDate_c() {
        return date_c.get();
    }

    public SimpleStringProperty date_cProperty() {
        return date_c;
    }

    public void setDate_c(String date_c) {
        this.date_c.set(date_c);
    }

    public String getDate_o() {
        return date_o.get();
    }

    public SimpleStringProperty date_oProperty() {
        return date_o;
    }

    public void setDate_o(String date_o) {
        this.date_o.set(date_o);
    }

    public int getCr_by() {
        return cr_by.get();
    }

    public SimpleIntegerProperty cr_byProperty() {
        return cr_by;
    }

    public void setCr_by(int cr_by) {
        this.cr_by.set(cr_by);
    }

    public int getUp_by() {
        return up_by.get();
    }

    public SimpleIntegerProperty up_byProperty() {
        return up_by;
    }

    public void setUp_by(int up_by) {
        this.up_by.set(up_by);
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

    public String getDate_up() {
        return date_up.get();
    }

    public SimpleStringProperty date_upProperty() {
        return date_up;
    }

    public void setDate_up(String date_up) {
        this.date_up.set(date_up);
    }
}
