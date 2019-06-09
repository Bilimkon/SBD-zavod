package sample.components.sell.Core;

public class History {

    private int id;
    private String product_barcode;
    private int product_id;
    private String product_name;
    private int product_type;
    private int product_quantity;
    private int seller_id;
    private String cost;
    private String date_cr;
    private int cr_by;
    private String date_up;
    private int up_by;
    private int customer_id;
    private int sell_action_id;

    public History(int id, String product_barcode, int product_id, String product_name, int product_type, int product_quantity, int seller_id, String cost, String date_cr, int cr_by, String date_up, int up_by, int customer_id, int sell_action_id) {
        super();
        this.id = id;
        this.product_barcode = product_barcode;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_type = product_type;
        this.product_quantity = product_quantity;
        this.seller_id = seller_id;
        this.cost = cost;
        this.date_cr = date_cr;
        this.cr_by = cr_by;
        this.date_up = date_up;
        this.up_by = up_by;
        this.customer_id = customer_id;
        this.sell_action_id = sell_action_id;
    }

    public History(int id, String barcode, int id1, String name, String type, int type_id, int amount, int id2, String cost, String date_c, int id3, String date_cr, int id4, int id5) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_barcode() {
        return product_barcode;
    }

    public void setProduct_barcode(String product_barcode) {
        this.product_barcode = product_barcode;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_type() {
        return product_type;
    }

    public void setProduct_type(int product_type) {
        this.product_type = product_type;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDate_cr() {
        return date_cr;
    }

    public void setDate_cr(String date_cr) {
        this.date_cr = date_cr;
    }

    public int getCr_by() {
        return cr_by;
    }

    public void setCr_by(int cr_by) {
        this.cr_by = cr_by;
    }

    public String getDate_up() {
        return date_up;
    }

    public void setDate_up(String date_up) {
        this.date_up = date_up;
    }

    public int getUp_by() {
        return up_by;
    }

    public void setUp_by(int up_by) {
        this.up_by = up_by;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getSell_action_id() {
        return sell_action_id;
    }

    public void setSell_action_id(int sell_action_id) {
        this.sell_action_id = sell_action_id;
    }
}
