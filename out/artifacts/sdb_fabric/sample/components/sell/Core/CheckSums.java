package sample.components.sell.Core;

public class CheckSums {

     String sum;
     String dollar;
     String hr;
     String psum;
     String pdollar;
     String phr;

    public CheckSums(String sum, String dollar, String hr, String psum, String pdollar, String phr) {
        this.sum = sum;
        this.dollar = dollar;
        this.hr = hr;
        this.psum = psum;
        this.pdollar = pdollar;
        this.phr = phr;
    }

    public CheckSums() {

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
}
