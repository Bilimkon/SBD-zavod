package sample.components.sell.views.CustomItems.CustomBasketItem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import sample.components.sell.MainPageController;
import sample.components.sell.productTableView.ProductTable;

import java.net.URL;
import java.util.ResourceBundle;


public class ShopItemListItem extends AnchorPane implements ShopItemConnector, Initializable {

    @FXML private Label itemTitle;
    @FXML private Label itemType;
    @FXML private Label itemPrice;
    @FXML private TextField amountField;
    @FXML private CheckBox isAccepted;
    private Pane self;
    public ShopItemListItem() {

    }

    public int id = -1;
    private String barcode = "0";
    public void setDetails(ProductTable maxsulot, boolean isAccepted) {
        itemTitle.setText(maxsulot.getName());
        itemType.setText(maxsulot.getType());
        itemPrice.setText(maxsulot.getCost() + " sum");
        amountField.setText("1");
        barcode = maxsulot.getBarcode();
        this.isAccepted.setSelected(isAccepted);

    }

    private Background paneBackground = null;
    private void setPaneBackgroundColor(int red, int green, int blue) {
        Color c = new Color((double) red / 256, (double) green / 256, (double) blue / 256,1);
        BackgroundFill backgroundFill = new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        this.self.backgroundProperty().setValue(background);
    }
    @Override
    public void setPane(Pane p) {
        this.self = p;
        paneBackground = p.getBackground();
        if (id != -1) {
            if (id % 2 == 0) {
                setPaneBackgroundColor(203, 203, 203);
            }
        }
        p.setOnMouseClicked(event -> amountField.requestFocus());



        p.setOnMouseExited(event -> {
            if (id != -1) {
                if (id % 2 == 0) {
                    setPaneBackgroundColor(203, 203, 203);
                } else {
                    //self.setBackground(paneBackground);
                }
            }
        });
    }

    @Override
    public void onDispose() {
        VBox b = (VBox) self.getParent();
        for (int i = 0; i < MainPageController.basket.size(); i++) {
            if (MainPageController.basket.get(i).getBarcode().equals(barcode)) {
                System.out.println("removing item barcode = " + barcode);
                MainPageController.basket.remove(i);
            }
        }
        b.getChildren().remove(self);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
