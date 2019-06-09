package sample.components.sell.Core;

public class type {
    private int id;
    private String name;
    private int unit;
    private System description;
    private System date_cr;
    private int cr_by;
    private int up_by;
    private String date_up;

    public type(int id, String name, int unit, System description, System date_cr, int cr_by, int up_by, String date_up) {
        super();
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.description = description;
        this.date_cr = date_cr;
        this.cr_by = cr_by;
        this.up_by = up_by;
        this.date_up = date_up;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public System getDescription() {
        return description;
    }

    public void setDescription(System description) {
        this.description = description;
    }

    public System getDate_cr() {
        return date_cr;
    }

    public void setDate_cr(System date_cr) {
        this.date_cr = date_cr;
    }

    public int getCr_by() {
        return cr_by;
    }

    public void setCr_by(int cr_by) {
        this.cr_by = cr_by;
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
}
