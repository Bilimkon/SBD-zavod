package sample.components.models;

import javafx.beans.property.SimpleStringProperty;

public class BalanceTotal {
    SimpleStringProperty product = new SimpleStringProperty();
    SimpleStringProperty p2 = new SimpleStringProperty();
    SimpleStringProperty p3 = new SimpleStringProperty();
    SimpleStringProperty sell = new SimpleStringProperty();
    SimpleStringProperty sum = new SimpleStringProperty();
    SimpleStringProperty dollar = new SimpleStringProperty();
    SimpleStringProperty hr = new SimpleStringProperty();
    SimpleStringProperty vhr = new SimpleStringProperty();
    SimpleStringProperty total_all = new SimpleStringProperty();

    public BalanceTotal(SimpleStringProperty product, SimpleStringProperty p2, SimpleStringProperty p3, SimpleStringProperty sell, SimpleStringProperty sum, SimpleStringProperty dollar, SimpleStringProperty hr, SimpleStringProperty vhr, SimpleStringProperty total_all) {
        this.product = product;
        this.p2 = p2;
        this.p3 = p3;
        this.sell = sell;
        this.sum = sum;
        this.dollar = dollar;
        this.hr = hr;
        this.vhr = vhr;
        this.total_all = total_all;
    }

    public BalanceTotal() {

    }

    public String getProduct() {
        return product.get();
    }

    public SimpleStringProperty productProperty() {
        return product;
    }

    public void setProduct(String product) {
        this.product.set(product);
    }

    public String getP2() {
        return p2.get();
    }

    public SimpleStringProperty p2Property() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2.set(p2);
    }

    public String getP3() {
        return p3.get();
    }

    public SimpleStringProperty p3Property() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3.set(p3);
    }

    public String getSell() {
        return sell.get();
    }

    public SimpleStringProperty sellProperty() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell.set(sell);
    }

    public String getSum() {
        return sum.get();
    }

    public SimpleStringProperty sumProperty() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum.set(sum);
    }

    public String getDollar() {
        return dollar.get();
    }

    public SimpleStringProperty dollarProperty() {
        return dollar;
    }

    public void setDollar(String dollar) {
        this.dollar.set(dollar);
    }

    public String getHr() {
        return hr.get();
    }

    public SimpleStringProperty hrProperty() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr.set(hr);
    }

    public String getVhr() {
        return vhr.get();
    }

    public SimpleStringProperty vhrProperty() {
        return vhr;
    }

    public void setVhr(String vhr) {
        this.vhr.set(vhr);
    }

    public String getTotal_all() {
        return total_all.get();
    }

    public SimpleStringProperty total_allProperty() {
        return total_all;
    }

    public void setTotal_all(String total_all) {
        this.total_all.set(total_all);
    }
}
