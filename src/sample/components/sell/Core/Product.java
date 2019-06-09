package sample.components.sell.Core;

/*
 Humoyun Qo'rg'onov  SBD(Software Business Development)
  */

public class Product {

    private int id;
    private String barcode;
    private String name;
    private String type;
    private int type_id;
    private String cost;
    private int quantity;
    private String cost_o;
    private String date_c;
    private String date_o;
    private int cr_by;
    private String date_cr;
    private int up_by;
    private String date_up;
    public Product() {

    }

    public static Product getInstance() {
        return new Product();
    }

    public Product(String barcode, String name, String type, int type_id, String cost, int quantity, String cost_o, String date_c, String date_o, int cr_by, String date_cr, int up_by, String date_up) {
        this(0, barcode, name, type, type_id, cost, quantity, cost_o, date_c, date_o,cr_by,date_cr,up_by,date_up);
    }

    public Product(int id, String barcode, String name, String type, int type_id, String cost, int quantity, String cost_o, String date_c, String date_o, int cr_by, String date_cr, int up_by, String date_up) {
        super();
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.type = type;
        this.type_id = type_id;
        this.cost = cost;
        this.quantity = quantity;
        this.cost_o = cost_o;
        this.date_c = date_c;
        this.date_o = date_o;
        this.cr_by = cr_by;
        this.date_cr = date_cr;
        this.up_by = up_by;
        this.date_up = date_up;
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

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCost_o() {
        return cost_o;
    }

    public void setCost_o(String cost_o) {
        this.cost_o = cost_o;
    }

    public String getDate_c() {
        return date_c;
    }

    public void setDate_c(String date_c) {
        this.date_c = date_c;
    }

    public String getDate_o() {
        return date_o;
    }

    public void setDate_o(String date_o) {
        this.date_o = date_o;
    }

    public int getCr_by() {
        return cr_by;
    }

    public void setCr_by(int cr_by) {
        this.cr_by = cr_by;
    }

    public String getDate_cr() {
        return date_cr;
    }

    public void setDate_cr(String date_cr) {
        this.date_cr = date_cr;
    }

    public int getUp_by() {
        return up_by;
    }

    public void setUp_by(int up_by) {
        this.up_by = up_by;
    }

    public String getDate_up() {
        return date_up;
    }

    public void setDate_up(String date_up) {
        this.date_up = date_up;
    }

    @Override
    public String toString() {
        return String
                .format("Product [id=%s,barcode=%s ,name=%s, type=%s,type_id=%s, cost=%s, date_c=%s,date_o=%s,cr_by=%s,date_cr=%s,up_by=%s,date_up=%s]",
                        id, barcode, name, type, type_id, cost, date_c, date_o, cr_by, date_cr,up_by,date_up);
    }


}
