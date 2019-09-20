package sample.components.sell.Core.Models;

public class ReceiptCheck {

    private Integer id ;
    private String name;
    private Integer quantity;
    private Double price;
    private String barcode;


    public ReceiptCheck(Integer id, String name, Integer quantity, Double price, String barcode) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.barcode = barcode;
    }

    public ReceiptCheck() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
