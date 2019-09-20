package sample.components.sell.productTableView;



import javafx.beans.property.SimpleStringProperty;


public class product {
    private SimpleStringProperty id = new SimpleStringProperty();
    private SimpleStringProperty unit = new SimpleStringProperty();
    private SimpleStringProperty barcode = new SimpleStringProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty type = new SimpleStringProperty();
    private SimpleStringProperty cost_o = new SimpleStringProperty();
    private SimpleStringProperty cost = new SimpleStringProperty();
    private SimpleStringProperty quantity = new SimpleStringProperty();
    private SimpleStringProperty date_c = new SimpleStringProperty();
    private SimpleStringProperty date_o = new SimpleStringProperty();
    private SimpleStringProperty suplier = new SimpleStringProperty();
    private SimpleStringProperty date = new SimpleStringProperty();
    private SimpleStringProperty total_cost_o = new SimpleStringProperty();
    private SimpleStringProperty total_cost = new SimpleStringProperty();

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getUnit() {
        return unit.get();
    }

    public SimpleStringProperty unitProperty() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit.set(unit);
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

    public String getCost_o() {
        return cost_o.get();
    }

    public SimpleStringProperty cost_oProperty() {
        return cost_o;
    }

    public void setCost_o(String cost_o) {
        this.cost_o.set(cost_o);
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

    public String getSuplier() {
        return suplier.get();
    }

    public SimpleStringProperty suplierProperty() {
        return suplier;
    }

    public void setSuplier(String suplier) {
        this.suplier.set(suplier);
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

    public String getTotal_cost_o() {
        return total_cost_o.get();
    }

    public SimpleStringProperty total_cost_oProperty() {
        return total_cost_o;
    }

    public void setTotal_cost_o(String total_cost_o) {
        this.total_cost_o.set(total_cost_o);
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
}

