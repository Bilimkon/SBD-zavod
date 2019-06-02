package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Main;
import sample.dao.Main2Dao;
import sample.dao.Main3Dao;
import sample.model.SimpleProduct;
import sample.model.TableA;
import sample.model.TableB;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class main2 implements Initializable {

    @FXML
    private Button btnClose;
    @FXML
    private Button btnBarcode;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnRevert;
    @FXML
    private TextField textUpdate;
    @FXML
    private TableView tableA;
    @FXML
    private TableView tableB;
    @FXML
    private TextField textBarcode;
    @FXML
    private Label textBarcode2;
    @FXML
    private Label textQuantity;
    @FXML
    private Label textId;

    Main2Dao main2Dao = new Main2Dao();
    Main3Dao main3Dao = new Main3Dao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableA();
        initializeTableB();
        tableA();
        tableB();
        setUpdate();

    }

    private void initializeTableA() {

        TableColumn id = new TableColumn("Tartib raqami");
        TableColumn barcode = new TableColumn("Barcode");
        TableColumn name = new TableColumn("Nomi");
        TableColumn type = new TableColumn("Turi");
        TableColumn quantity = new TableColumn("Miqdori");

        tableA.getColumns().addAll(id, barcode, name, type, quantity);

        id.setCellValueFactory(new PropertyValueFactory<TableA, String>("id"));
        barcode.setCellValueFactory(new PropertyValueFactory<TableA, String>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<TableA, String>("name"));
        type.setCellValueFactory(new PropertyValueFactory<TableA, String>("type"));
        quantity.setCellValueFactory(new PropertyValueFactory<TableA, String>("quantity"));
    }

    private void initializeTableB() {
        TableColumn id = new TableColumn("Tartib raqami");
        TableColumn barcode = new TableColumn("Barcode");
        TableColumn name = new TableColumn("Nomi");
        TableColumn type = new TableColumn("Turi");
        TableColumn quantity = new TableColumn("Miqdori");
        TableColumn p_quantity = new TableColumn("Tayyorlangan miqdori");

        tableB.getColumns().addAll(id, barcode, name, type, quantity, p_quantity);

        id.setCellValueFactory(new PropertyValueFactory<TableB, String>("id"));
        barcode.setCellValueFactory(new PropertyValueFactory<TableB, String>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<TableB, String>("name"));
        type.setCellValueFactory(new PropertyValueFactory<TableB, String>("type"));
        quantity.setCellValueFactory(new PropertyValueFactory<TableB, String>("quantity"));
        p_quantity.setCellValueFactory(new PropertyValueFactory<TableB, String>("p_quantity"));
    }

    private void tableA() {
        try {
            main2Dao.tableA(tableA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tableB() {
        try {
            main2Dao.tableB(tableB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpdate() {
        try {
            tableB.setOnMouseClicked(event -> {
                TableB tableB1 = (TableB) tableB.getSelectionModel().getSelectedItem();
                try {
                    textUpdate.setText(tableB1.getP_quantity());
                    textBarcode2.setText(tableB1.getBarcode());
                    textId.setText(tableB1.getId());
                    textQuantity.setText(tableB1.getQuantity());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Error" + exc, "Xatolik", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void btnUpdateAction() {
        setUpdateAction();
        tableB();
    }

    private void setUpdateAction() {
        try {

            String id = textId.getText();
            String barcode = textBarcode2.getText();
            String quantity = textUpdate.getText();

            main2Dao.setUpdateAction(quantity, barcode, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnCloseAction() {
        closeAction(btnClose);
    }

    private void closeAction(Button btn) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Chiqish");
        alert.setHeaderText(null);
        alert.setContentText(" Dasturdan chiqmoqchimisiz ?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent())
            if (result.get() == ButtonType.OK) {

                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("SBD boshqaruv tizimi");
                    stage.setScene(new Scene(root, 1080, 720));
                    stage.setResizable(true);
                    stage.getIcons().add(
                            new Image(
                                    Main.class.getResourceAsStream("bar-chart.png")));
                    stage.show();
                    this.btnClose.getScene().getWindow().hide();

                    // Hide this current window (if this is what you want)

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
    }

    private void customDialog(String name1, String barcode, String type, String quantity) {
        try {
            TextInputDialog dialog = new TextInputDialog("1");
            dialog.setTitle("Maxsulot");
            dialog.setHeaderText("Nomi:" + name1 + "\nBarcode: " + barcode + "\nTuri: " + type + "\nMiqdori: " + quantity);
            dialog.setContentText("Miqdorni kiriting");

            Optional<String> result = dialog.showAndWait();

            // The Java 8 way to get the response value (with lambda expression).
            result.ifPresent(name ->

                    {
                        try {
                            main2Dao.addToSaleTable(barcode, name1, type, name);
                            tableB();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnOkAction() {
        try {
            String barcodePaper = textBarcode.getText();
            SimpleProduct simpleProduct = main3Dao.getDspBarcode(barcodePaper);
            customDialog(simpleProduct.getName(), simpleProduct.getBarcode(), simpleProduct.getType(), simpleProduct.getQuantity());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Barcodeni kiriting");
        } finally {
            textBarcode.setText("");
        }
    }

    @FXML
    private void btnRevertAction() {
        try {
            String id = textId.getText();
            String barcode = textBarcode2.getText();
            String quantity = textQuantity.getText();
            main2Dao.revertAction(id, barcode, quantity);
            tableA();
            tableB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
