package sample.components.sell.productTableView;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class QarzTable {

    SimpleIntegerProperty idQ = new SimpleIntegerProperty();
    SimpleIntegerProperty Sotuvchi_id_Q = new SimpleIntegerProperty();
    SimpleStringProperty FirstName_Q = new SimpleStringProperty();
    SimpleStringProperty Lastname_Q = new SimpleStringProperty();
    SimpleStringProperty Card_amount_Q = new SimpleStringProperty();
    SimpleStringProperty Credit_Q = new SimpleStringProperty();
    SimpleStringProperty CreditDescription_Q = new SimpleStringProperty();
    SimpleStringProperty paid_date_Q = new SimpleStringProperty();
    SimpleStringProperty  total_cost_Q = new SimpleStringProperty();
    SimpleStringProperty paid_in_cash = new SimpleStringProperty();

    public int getIdQ() {
        return idQ.get();
    }

    public SimpleIntegerProperty idQProperty() {
        return idQ;
    }

    public void setIdQ(int idQ) {
        this.idQ.set(idQ);
    }

    public int getSotuvchi_id_Q() {
        return Sotuvchi_id_Q.get();
    }

    public SimpleIntegerProperty sotuvchi_id_QProperty() {
        return Sotuvchi_id_Q;
    }

    public void setSotuvchi_id_Q(int sotuvchi_id_Q) {
        this.Sotuvchi_id_Q.set(sotuvchi_id_Q);
    }

    public String getFirstName_Q() {
        return FirstName_Q.get();
    }

    public SimpleStringProperty firstName_QProperty() {
        return FirstName_Q;
    }

    public void setFirstName_Q(String firstName_Q) {
        this.FirstName_Q.set(firstName_Q);
    }

    public String getLastname_Q() {
        return Lastname_Q.get();
    }

    public SimpleStringProperty lastname_QProperty() {
        return Lastname_Q;
    }

    public void setLastname_Q(String lastname_Q) {
        this.Lastname_Q.set(lastname_Q);
    }

    public String getCard_amount_Q() {
        return Card_amount_Q.get();
    }

    public SimpleStringProperty card_amount_QProperty() {
        return Card_amount_Q;
    }

    public void setCard_amount_Q(String card_amount_Q) {
        this.Card_amount_Q.set(card_amount_Q);
    }

    public String getCredit_Q() {
        return Credit_Q.get();
    }

    public SimpleStringProperty credit_QProperty() {
        return Credit_Q;
    }

    public void setCredit_Q(String credit_Q) {
        this.Credit_Q.set(credit_Q);
    }

    public String  getCreditDescription_Q() {
        return CreditDescription_Q.get();
    }

    public SimpleStringProperty creditDescription_QProperty() {
        return CreditDescription_Q;
    }

    public void setCreditDescription_Q(String  creditDescription_Q) {
        this.CreditDescription_Q.set(creditDescription_Q);
    }

    public String getPaid_date_Q() {
        return paid_date_Q.get();
    }

    public SimpleStringProperty paid_date_QProperty() {
        return paid_date_Q;
    }

    public void setPaid_date_Q(String paid_date_Q) {
        this.paid_date_Q.set(paid_date_Q);
    }

    public String getTotal_cost_Q() {
        return total_cost_Q.get();
    }

    public SimpleStringProperty total_cost_QProperty() {
        return total_cost_Q;
    }

    public void setTotal_cost_Q(String total_cost_Q) {
        this.total_cost_Q.set(total_cost_Q);
    }

    public String getPaid_in_cash() {
        return paid_in_cash.get();
    }

    public SimpleStringProperty paid_in_cashProperty() {
        return paid_in_cash;
    }

    public void setPaid_in_cash(String paid_in_cash) {
        this.paid_in_cash.set(paid_in_cash);
    }
}
