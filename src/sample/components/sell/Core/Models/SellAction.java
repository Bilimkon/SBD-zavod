package sample.components.sell.Core.Models;

public class SellAction {

    int id;
    String sum;
    String dollar;
    String hr;
    String psum;
    String pdollar;
    String phr;
    String sale;
    int customer_id;
    int cr_by;
    String date_cr;

    public SellAction(int id, String sum, String dollar, String hr, String psum, String pdollar, String phr, String sale, int customer_id, int cr_by, String date_cr) {
        this.id = id;
        this.sum = sum;
        this.dollar = dollar;
        this.hr = hr;
        this.psum = psum;
        this.pdollar = pdollar;
        this.phr = phr;
        this.sale = sale;
        this.customer_id = customer_id;
        this.cr_by = cr_by;
        this.date_cr = date_cr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getDollar() {
        return dollar;
    }

    public void setDollar(String dollar) {
        this.dollar = dollar;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getPsum() {
        return psum;
    }

    public void setPsum(String psum) {
        this.psum = psum;
    }

    public String getPdollar() {
        return pdollar;
    }

    public void setPdollar(String pdollar) {
        this.pdollar = pdollar;
    }

    public String getPhr() {
        return phr;
    }

    public void setPhr(String phr) {
        this.phr = phr;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
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
}
