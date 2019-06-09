package sample.components.sell.Core;

public class Xisoblar {

    private  String  name;
    private  String date;
    private  float total_cost;
    private int  quantity;
    private String firstName;
    private String lastName;

    public Xisoblar(String name, String date, float total_cost, int quantity, String firstName, String lastName) {
        this.name = name;
        this.date = date;
        this.total_cost = total_cost;
        this.quantity = quantity;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(float total_cost) {
        this.total_cost = total_cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
