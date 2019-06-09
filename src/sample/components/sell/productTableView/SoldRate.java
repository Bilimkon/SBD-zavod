package sample.components.sell.productTableView;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SoldRate {

    SimpleStringProperty nameSR = new SimpleStringProperty();
    SimpleStringProperty barcodeSR = new SimpleStringProperty();
    SimpleStringProperty  totalSR = new SimpleStringProperty();
    SimpleIntegerProperty soldQuantitySR = new SimpleIntegerProperty();

    public String getNameSR() {
        return nameSR.get();
    }

    public SimpleStringProperty nameSRProperty() {
        return nameSR;
    }

    public void setNameSR(String nameSR) {
        this.nameSR.set(nameSR);
    }

    public String getBarcodeSR() {
        return barcodeSR.get();
    }

    public SimpleStringProperty barcodeSRProperty() {
        return barcodeSR;
    }

    public void setBarcodeSR(String barcodeSR) {
        this.barcodeSR.set(barcodeSR);
    }

    public String getTotalSR() {
        return totalSR.get();
    }

    public SimpleStringProperty totalSRProperty() {
        return totalSR;
    }

    public void setTotalSR(String totalSR) {
        this.totalSR.set(totalSR);
    }

    public int getSoldQuantitySR() {
        return soldQuantitySR.get();
    }

    public SimpleIntegerProperty soldQuantitySRProperty() {
        return soldQuantitySR;
    }

    public void setSoldQuantitySR(int soldQuantitySR) {
        this.soldQuantitySR.set(soldQuantitySR);
    }
}
