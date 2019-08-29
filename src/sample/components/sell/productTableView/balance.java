package sample.components.sell.productTableView;

import javafx.beans.property.SimpleStringProperty;

public class balance {

    SimpleStringProperty who = new SimpleStringProperty();
    SimpleStringProperty income = new SimpleStringProperty();
    SimpleStringProperty outcome = new SimpleStringProperty();
    SimpleStringProperty balance = new SimpleStringProperty();
    SimpleStringProperty sum_in  = new SimpleStringProperty();
    SimpleStringProperty sum_out  = new SimpleStringProperty();
    SimpleStringProperty sum_balance  = new SimpleStringProperty();
    SimpleStringProperty dollar_in  = new SimpleStringProperty();
    SimpleStringProperty dollar_out  = new SimpleStringProperty();
    SimpleStringProperty dollar_balance  = new SimpleStringProperty();
    SimpleStringProperty hr_in  = new SimpleStringProperty();
    SimpleStringProperty hr_out  = new SimpleStringProperty();
    SimpleStringProperty hr_balance  = new SimpleStringProperty();

    public balance() {

    }

    public balance(SimpleStringProperty who, SimpleStringProperty income, SimpleStringProperty outcome, SimpleStringProperty balance, SimpleStringProperty sum_in, SimpleStringProperty sum_out, SimpleStringProperty sum_balance, SimpleStringProperty dollar_in, SimpleStringProperty dollar_out, SimpleStringProperty dollar_balance, SimpleStringProperty hr_in, SimpleStringProperty hr_out, SimpleStringProperty hr_balance) {
        this.who = who;
        this.income = income;
        this.outcome = outcome;
        this.balance = balance;
        this.sum_in = sum_in;
        this.sum_out = sum_out;
        this.sum_balance = sum_balance;
        this.dollar_in = dollar_in;
        this.dollar_out = dollar_out;
        this.dollar_balance = dollar_balance;
        this.hr_in = hr_in;
        this.hr_out = hr_out;
        this.hr_balance = hr_balance;
    }

    public String getWho() {
        return who.get();
    }

    public SimpleStringProperty whoProperty() {
        return who;
    }

    public void setWho(String who) {
        this.who.set(who);
    }

    public String getIncome() {
        return income.get();
    }

    public SimpleStringProperty incomeProperty() {
        return income;
    }

    public void setIncome(String income) {
        this.income.set(income);
    }

    public String getOutcome() {
        return outcome.get();
    }

    public SimpleStringProperty outcomeProperty() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome.set(outcome);
    }

    public String getBalance() {
        return balance.get();
    }

    public SimpleStringProperty balanceProperty() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance.set(balance);
    }

    public String getSum_in() {
        return sum_in.get();
    }

    public SimpleStringProperty sum_inProperty() {
        return sum_in;
    }

    public void setSum_in(String sum_in) {
        this.sum_in.set(sum_in);
    }

    public String getSum_out() {
        return sum_out.get();
    }

    public SimpleStringProperty sum_outProperty() {
        return sum_out;
    }

    public void setSum_out(String sum_out) {
        this.sum_out.set(sum_out);
    }

    public String getSum_balance() {
        return sum_balance.get();
    }

    public SimpleStringProperty sum_balanceProperty() {
        return sum_balance;
    }

    public void setSum_balance(String sum_balance) {
        this.sum_balance.set(sum_balance);
    }

    public String getDollar_in() {
        return dollar_in.get();
    }

    public SimpleStringProperty dollar_inProperty() {
        return dollar_in;
    }

    public void setDollar_in(String dollar_in) {
        this.dollar_in.set(dollar_in);
    }

    public String getDollar_out() {
        return dollar_out.get();
    }

    public SimpleStringProperty dollar_outProperty() {
        return dollar_out;
    }

    public void setDollar_out(String dollar_out) {
        this.dollar_out.set(dollar_out);
    }

    public String getDollar_balance() {
        return dollar_balance.get();
    }

    public SimpleStringProperty dollar_balanceProperty() {
        return dollar_balance;
    }

    public void setDollar_balance(String dollar_balance) {
        this.dollar_balance.set(dollar_balance);
    }

    public String getHr_in() {
        return hr_in.get();
    }

    public SimpleStringProperty hr_inProperty() {
        return hr_in;
    }

    public void setHr_in(String hr_in) {
        this.hr_in.set(hr_in);
    }

    public String getHr_out() {
        return hr_out.get();
    }

    public SimpleStringProperty hr_outProperty() {
        return hr_out;
    }

    public void setHr_out(String hr_out) {
        this.hr_out.set(hr_out);
    }

    public String getHr_balance() {
        return hr_balance.get();
    }

    public SimpleStringProperty hr_balanceProperty() {
        return hr_balance;
    }

    public void setHr_balance(String hr_balance) {
        this.hr_balance.set(hr_balance);
    }
}
