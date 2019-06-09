package sample.components.sell.productTableView;

import javafx.beans.property.SimpleStringProperty;

public class TableHistory {


    private SimpleStringProperty owener = new SimpleStringProperty();
    private SimpleStringProperty description = new SimpleStringProperty();
    private SimpleStringProperty   changeTime = new SimpleStringProperty();

    public String getOwener() {
        return owener.get();
    }

    public SimpleStringProperty owenerProperty() {
        return owener;
    }

    public void setOwener(String owener) {
        this.owener.set(owener);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getChangeTime() {
        return changeTime.get();
    }

    public SimpleStringProperty changeTimeProperty() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime.set(changeTime);
    }
}
