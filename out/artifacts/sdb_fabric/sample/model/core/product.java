package sample.model.core;

public class product {

    private int id;
    private String barcode;
    private String name;
    private String type;
    private String type_id;
    private String cost;
    private String quantity;
    private String unit;
    private String suplier_id;
    private String invoice_id;
    private String color;
    private String description;
    private String date_cr;
    private String cr_by;


    public product(int id, String barcode, String name, String type, String type_id, String cost, String quantity, String unit, String suplier_id, String invoice_id, String color, String description, String date_cr, String cr_by) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.type = type;
        this.type_id = type_id;
        this.cost = cost;
        this.quantity = quantity;
        this.unit = unit;
        this.suplier_id = suplier_id;
        this.invoice_id = invoice_id;
        this.color = color;
        this.description = description;
        this.date_cr = date_cr;
        this.cr_by = cr_by;
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

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSuplier_id() {
        return suplier_id;
    }

    public void setSuplier_id(String suplier_id) {
        this.suplier_id = suplier_id;
    }

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_cr() {
        return date_cr;
    }

    public void setDate_cr(String date_cr) {
        this.date_cr = date_cr;
    }

    public String getCr_by() {
        return cr_by;
    }

    public void setCr_by(String cr_by) {
        this.cr_by = cr_by;
    }
}
