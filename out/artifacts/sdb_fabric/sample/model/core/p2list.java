package sample.model.core;

public class p2list {

    String id;
    String name;
    String barcode;
    String type;
    String description;

    public p2list(String id, String name, String barcode, String type, String description) {
        this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.type = type;
        this.description = description;
    }

    public p2list(String id, String name, String barcode, String type) {
    }

    public p2list() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
