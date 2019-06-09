package sample.components.sell.productTableView;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class KunlikReyting {

    SimpleDoubleProperty total_cost_reyting = new SimpleDoubleProperty();
    SimpleIntegerProperty total_quantity_reyting = new SimpleIntegerProperty();
    SimpleStringProperty current_date_reyting = new SimpleStringProperty();

    public double getTotal_cost_reyting() {
        return total_cost_reyting.get();
    }

    public SimpleDoubleProperty total_cost_reytingProperty() {
        return total_cost_reyting;
    }

    public void setTotal_cost_reyting(double total_cost_reyting) {
        this.total_cost_reyting.set(total_cost_reyting);
    }

    public int getTotal_quantity_reyting() {
        return total_quantity_reyting.get();
    }

    public SimpleIntegerProperty total_quantity_reytingProperty() {
        return total_quantity_reyting;
    }

    public void setTotal_quantity_reyting(int total_quantity_reyting) {
        this.total_quantity_reyting.set(total_quantity_reyting);
    }

    public String getCurrent_date_reyting() {
        return current_date_reyting.get();
    }

    public SimpleStringProperty current_date_reytingProperty() {
        return current_date_reyting;
    }

    public void setCurrent_date_reyting(String current_date_reyting) {
        this.current_date_reyting.set(current_date_reyting);
    }
}
