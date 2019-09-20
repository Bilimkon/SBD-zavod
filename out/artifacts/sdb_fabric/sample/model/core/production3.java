package sample.model.core;

public class production3 {

    private int id;
    private String barcode;
    private String name;
    private String type;
    private String color;
    private String quantity;
    private String pBarcode;
    private String pName;
    private String pCost;
    private String pColor;
    private String pQuantity;
    private String dBarcode;
    private String dName;
    private String dQuantity;
    private String cr_on;
    private String cr_by;


    public production3(int id, String barcode, String name, String type, String color, String quantity, String pBarcode, String pName, String pCost, String pColor, String pQuantity, String dBarcode, String dName, String dQuantity, String cr_on, String cr_by) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.type = type;
        this.color = color;
        this.quantity = quantity;
        this.pBarcode = pBarcode;
        this.pName = pName;
        this.pCost = pCost;
        this.pColor = pColor;
        this.pQuantity = pQuantity;
        this.dBarcode = dBarcode;
        this.dName = dName;
        this.dQuantity = dQuantity;
        this.cr_on = cr_on;
        this.cr_by = cr_by;
    }

    public production3() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getpBarcode() {
        return pBarcode;
    }

    public void setpBarcode(String pBarcode) {
        this.pBarcode = pBarcode;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpCost() {
        return pCost;
    }

    public void setpCost(String pCost) {
        this.pCost = pCost;
    }

    public String getpColor() {
        return pColor;
    }

    public void setpColor(String pColor) {
        this.pColor = pColor;
    }

    public String getpQuantity() {
        return pQuantity;
    }

    public void setpQuantity(String pQuantity) {
        this.pQuantity = pQuantity;
    }

    public String getdBarcode() {
        return dBarcode;
    }

    public void setdBarcode(String dBarcode) {
        this.dBarcode = dBarcode;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getdQuantity() {
        return dQuantity;
    }

    public void setdQuantity(String dQuantity) {
        this.dQuantity = dQuantity;
    }

    public String getCr_on() {
        return cr_on;
    }

    public void setCr_on(String cr_on) {
        this.cr_on = cr_on;
    }

    public String getCr_by() {
        return cr_by;
    }

    public void setCr_by(String cr_by) {
        this.cr_by = cr_by;
    }
}
