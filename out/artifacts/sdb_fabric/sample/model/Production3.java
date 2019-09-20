package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class Production3 {
    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty barcode = new SimpleStringProperty();
    SimpleStringProperty name = new SimpleStringProperty();
    SimpleStringProperty type = new SimpleStringProperty();
    SimpleStringProperty color = new SimpleStringProperty();
    SimpleStringProperty quantity = new SimpleStringProperty();
    SimpleStringProperty pBarcode = new SimpleStringProperty();
    SimpleStringProperty pName = new SimpleStringProperty();
    SimpleStringProperty pCost = new SimpleStringProperty();
    SimpleStringProperty pQuantity = new SimpleStringProperty();
    SimpleStringProperty pColor = new SimpleStringProperty();
    SimpleStringProperty dBarcode = new SimpleStringProperty();
    SimpleStringProperty dName = new SimpleStringProperty();
    SimpleStringProperty dQuantity = new SimpleStringProperty();
    SimpleStringProperty cr_on = new SimpleStringProperty();
    SimpleStringProperty cr_by = new SimpleStringProperty();

    public Production3(SimpleStringProperty id, SimpleStringProperty barcode, SimpleStringProperty name, SimpleStringProperty type, SimpleStringProperty color, SimpleStringProperty quantity, SimpleStringProperty pBarcode, SimpleStringProperty pName, SimpleStringProperty pCost, SimpleStringProperty pQuantity, SimpleStringProperty pColor, SimpleStringProperty dBarcode, SimpleStringProperty dName, SimpleStringProperty dQuantity, SimpleStringProperty cr_on, SimpleStringProperty cr_by) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.type = type;
        this.color = color;
        this.quantity = quantity;
        this.pBarcode = pBarcode;
        this.pName = pName;
        this.pCost = pCost;
        this.pQuantity = pQuantity;
        this.pColor = pColor;
        this.dBarcode = dBarcode;
        this.dName = dName;
        this.dQuantity = dQuantity;
        this.cr_on = cr_on;
        this.cr_by = cr_by;
    }

    public Production3() {

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

    public String getColor() {
        return color.get();
    }

    public SimpleStringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
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

    public String getpBarcode() {
        return pBarcode.get();
    }

    public SimpleStringProperty pBarcodeProperty() {
        return pBarcode;
    }

    public void setpBarcode(String pBarcode) {
        this.pBarcode.set(pBarcode);
    }

    public String getpName() {
        return pName.get();
    }

    public SimpleStringProperty pNameProperty() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName.set(pName);
    }

    public String getpCost() {
        return pCost.get();
    }

    public SimpleStringProperty pCostProperty() {
        return pCost;
    }

    public void setpCost(String pCost) {
        this.pCost.set(pCost);
    }

    public String getpQuantity() {
        return pQuantity.get();
    }

    public SimpleStringProperty pQuantityProperty() {
        return pQuantity;
    }

    public void setpQuantity(String pQuantity) {
        this.pQuantity.set(pQuantity);
    }

    public String getpColor() {
        return pColor.get();
    }

    public SimpleStringProperty pColorProperty() {
        return pColor;
    }

    public void setpColor(String pColor) {
        this.pColor.set(pColor);
    }

    public String getdBarcode() {
        return dBarcode.get();
    }

    public SimpleStringProperty dBarcodeProperty() {
        return dBarcode;
    }

    public void setdBarcode(String dBarcode) {
        this.dBarcode.set(dBarcode);
    }

    public String getdName() {
        return dName.get();
    }

    public SimpleStringProperty dNameProperty() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName.set(dName);
    }

    public String getdQuantity() {
        return dQuantity.get();
    }

    public SimpleStringProperty dQuantityProperty() {
        return dQuantity;
    }

    public void setdQuantity(String dQuantity) {
        this.dQuantity.set(dQuantity);
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
}
