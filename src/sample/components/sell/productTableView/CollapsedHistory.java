package sample.components.sell.productTableView;

import javafx.beans.property.SimpleStringProperty;

public class CollapsedHistory {

    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty seller = new SimpleStringProperty();
    SimpleStringProperty customer = new SimpleStringProperty();
    SimpleStringProperty cost_paid = new SimpleStringProperty();
    SimpleStringProperty total_cost = new SimpleStringProperty();
    SimpleStringProperty sale = new SimpleStringProperty();
    SimpleStringProperty credit = new SimpleStringProperty();
    SimpleStringProperty card = new SimpleStringProperty();
    SimpleStringProperty cash = new SimpleStringProperty();
    SimpleStringProperty comment = new SimpleStringProperty();
    SimpleStringProperty date = new SimpleStringProperty();

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getSeller() {
        return seller.get();
    }

    public SimpleStringProperty sellerProperty() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller.set(seller);
    }

    public String getCustomer() {
        return customer.get();
    }

    public SimpleStringProperty customerProperty() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer.set(customer);
    }

    public String getCost_paid() {
        return cost_paid.get();
    }

    public SimpleStringProperty cost_paidProperty() {
        return cost_paid;
    }

    public void setCost_paid(String cost_paid) {
        this.cost_paid.set(cost_paid);
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

    public String getSale() {
        return sale.get();
    }

    public SimpleStringProperty saleProperty() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale.set(sale);
    }

    public String getCredit() {
        return credit.get();
    }

    public SimpleStringProperty creditProperty() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit.set(credit);
    }

    public String getCard() {
        return card.get();
    }

    public SimpleStringProperty cardProperty() {
        return card;
    }

    public void setCard(String card) {
        this.card.set(card);
    }

    public String getCash() {
        return cash.get();
    }

    public SimpleStringProperty cashProperty() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash.set(cash);
    }

    public String getComment() {
        return comment.get();
    }

    public SimpleStringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
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
}
