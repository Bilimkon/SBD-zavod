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
import sample.dao.Main3Dao;
import sample.model.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class main3 implements Initializable {

    @FXML
    private TableView tableMain3;
    @FXML
    private TableView tablePaper;
    @FXML
    private TableView tableDsp;
    @FXML
    private Button btnClose;
    @FXML
    private Button btnPaper;
    @FXML
    private TextField textPaper;
    @FXML
    private TextField textDsp;
    @FXML
    private Button btnDsp;
    @FXML
    private Label labelId;
    @FXML
    private Label labelBarcode;
    @FXML
    private Label labelQuantity;
    @FXML
    private TextField textUpdate;
    Main3Dao main3Dao = new Main3Dao();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initializeTablePaper();
        initializeTableDsp();
        initializeProduction3();
        tablePaper();
        tableDsp();
        tableProduction3();
        setUpdate();
    }

    private void initializeTablePaper() {
        TableColumn id = new TableColumn("Tartib raqami");
        TableColumn barcode = new TableColumn("Barcode");
        TableColumn name = new TableColumn("Qog'oz Nomi");
        TableColumn type = new TableColumn("Turi");
        TableColumn quantity = new TableColumn("Miqdori");

        tablePaper.getColumns().addAll(id, barcode, name, type, quantity);

        id.setCellValueFactory(new PropertyValueFactory<sample.model.tablePaper, String>("id"));
        barcode.setCellValueFactory(new PropertyValueFactory<sample.model.tablePaper, String>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<sample.model.tablePaper, String>("name"));
        type.setCellValueFactory(new PropertyValueFactory<sample.model.tablePaper, String>("type"));
        quantity.setCellValueFactory(new PropertyValueFactory<sample.model.tablePaper, String>("quantity"));

    }

    private void initializeTableDsp() {
        TableColumn id = new TableColumn("Tartib raqami");
        TableColumn barcode = new TableColumn("Barcode");
        TableColumn name = new TableColumn("Material Nomi");
        TableColumn type = new TableColumn("Turi");
        TableColumn quantity = new TableColumn("Miqdori");

        tableDsp.getColumns().addAll(id, barcode, name, type, quantity);

        id.setCellValueFactory(new PropertyValueFactory<TableDsp, String>("id"));
        barcode.setCellValueFactory(new PropertyValueFactory<TableDsp, String>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<TableDsp, String>("name"));
        type.setCellValueFactory(new PropertyValueFactory<TableDsp, String>("type"));
        quantity.setCellValueFactory(new PropertyValueFactory<TableDsp, String>("quantity"));

    }

    private void initializeProduction3() {
        TableColumn id = new TableColumn("Tarrib raqami");
        TableColumn barcode = new TableColumn("Barcode");
        TableColumn name = new TableColumn("Nomi");
        TableColumn type = new TableColumn("Turi");
        TableColumn quantity = new TableColumn("Miqdori");
        TableColumn p_quantity = new TableColumn("Tayyorlangan miqdori");
        tableMain3.getColumns().addAll(id, barcode, name, type, quantity, p_quantity);

        id.setCellValueFactory(new PropertyValueFactory<Production3, String>("id"));
        barcode.setCellValueFactory(new PropertyValueFactory<Production3, String>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<Production3, String>("name"));
        type.setCellValueFactory(new PropertyValueFactory<Production3, String>("type"));
        quantity.setCellValueFactory(new PropertyValueFactory<Production3, String>("quantity"));
        p_quantity.setCellValueFactory(new PropertyValueFactory<Production3, String>("p_quantity"));

    }

    private void tablePaper() {
        try {
            main3Dao.tablePaper(tablePaper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tableDsp() {
        try {
            main3Dao.tableDsp(tableDsp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tableProduction3() {
        try {
            main3Dao.tableProduction(tableMain3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpdate() {
        try {
            tableMain3.setOnMouseClicked(event -> {
                Production3 production3 = (Production3) tableMain3.getSelectionModel().getSelectedItem();
                try {
                    textUpdate.setText(production3.getP_quantity());
                    labelId.setText(production3.getId());
                    labelBarcode.setText(production3.getBarcode());
                    labelQuantity.setText(production3.getQuantity());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Error" + exc, "Xatolik", JOptionPane.ERROR_MESSAGE);
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
                            main3Dao.addToSaleTable(barcode, name1, type, name);
                            tableProduction3();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Custom dialog for getting Dsp product
     * */
    @FXML
    private void btnDspAction() {
        try {
            String barcodeDsp = textDsp.getText();
            SimpleProduct simpleProduct = main3Dao.getDspBarcode(barcodeDsp);
            customDialog(simpleProduct.getName(), simpleProduct.getBarcode(), simpleProduct.getType(), simpleProduct.getQuantity());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Barcodeni kiriting");
        } finally {
            textDsp.setText("");
        }
    }

    /**
     * Custom dialog for getting paper product
     * */
    @FXML
    private void btnPaperAction() {
        try {
            String barcodePaper = textPaper.getText();
            SimplePaperProduct simplePaperProduct = main3Dao.getPaperBarcode(barcodePaper);
            customDialog(simplePaperProduct.getName(), simplePaperProduct.getBarcode(), simplePaperProduct.getType(), simplePaperProduct.getQuantity());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            textPaper.setText("");
        }
    }

    @FXML
    private void btnRevertAction() {
        revertAction();
    }

    private void revertAction() {

        try {
            String barcode_R = labelBarcode.getText();
            String quantity_R = labelQuantity.getText();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnUpdateAction() {
        updateAction();
    }

    private void updateAction() {
        try {
            String id_u = labelId.getText();
            String quantity_u = textUpdate.getText();
            main3Dao.setUpdateAction(quantity_u, id_u);
            tableProduction3();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
