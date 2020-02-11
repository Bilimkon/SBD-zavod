package sample.components.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import sample.components.dao.PaperDao;
import sample.model.tablePaper;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class Paper implements Initializable {

    private @FXML
    TableView table_paper;
    private @FXML
    Label paper_id;
    private @FXML
    Label paper_barcode;
    private @FXML
    Label paper_name;
    private @FXML
    Label paper_quantity;
    private @FXML
    Label paper_type;
    private @FXML
    Button btn_paper_sell;
    private @FXML
    Button btn_paper_production3;
    PaperDao paperDao = new PaperDao();


    private void initializeTablePaper() {
        TableColumn id = new TableColumn("id");
        TableColumn barcode = new TableColumn("Barcode");
        TableColumn name = new TableColumn("Qog'oz Nomi");
        TableColumn type = new TableColumn("Turi");
        TableColumn quantity = new TableColumn("Miqdori");

        table_paper.getColumns().addAll(id, barcode, name, quantity);

        id.setCellValueFactory(new PropertyValueFactory<tablePaper, String>("id"));
        barcode.setCellValueFactory(new PropertyValueFactory<sample.model.tablePaper, String>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<sample.model.tablePaper, String>("name"));
        type.setCellValueFactory(new PropertyValueFactory<sample.model.tablePaper, String>("type"));
        quantity.setCellValueFactory(new PropertyValueFactory<sample.model.tablePaper, String>("quantity"));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_paper_sell.setDisable(true);
        btn_paper_production3.setDisable(true);
        initializeTablePaper();
        TablePaper();
        setOzgaartirishMaxsulot();
        /**
         *   Timer()
         */
        Timeline ficeSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                TablePaper();
            }
        }));
        ficeSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        ficeSecondsWonder.play();
        /**
         *  End of timer()
         *
         */


    }

    private void setOzgaartirishMaxsulot() {
        try {
            table_paper.setOnMouseClicked(event -> {
                if (table_paper.getSelectionModel().getSelectedItem() != null) {
                    tablePaper tablePaper = (tablePaper) table_paper.getSelectionModel().getSelectedItem();
                    try {
                        paper_id.setText(tablePaper.getId());
                        paper_barcode.setText(tablePaper.getBarcode());
                        paper_name.setText(tablePaper.getName());
                        paper_quantity.setText(tablePaper.getQuantity());
                        paper_type.setText(tablePaper.getType());
                        btn_paper_sell.setDisable(false);
                        btn_paper_production3.setDisable(false);
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                }
            });
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private void TablePaper() {
        try {
            paperDao.AddTablePaper(table_paper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void transferDialogSell(String name1, String barcode, String quantity, String type) {
        try {
            TextInputDialog dialog = new TextInputDialog("" + quantity);
            dialog.setTitle("Maxsulot");
            dialog.setHeaderText("Nomi: " + name1 + "\nBarcode: " + barcode +  "\nMiqdori: " + quantity);
            dialog.setContentText("Miqdorni kiriting");
            Optional<String> result = dialog.showAndWait();
            // The Java 8 way to get the response value (with lambda expression).
            result.ifPresent(name ->
                    {
                        try {
                            paperDao.transferProduction3(name1, barcode, type, name);
                            paper_id.setText("");
                            paper_name.setText("");
                            paper_barcode.setText("");
                            paper_quantity.setText("");
                            paper_type.setText("");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void transferDialogProduction3(String name1, String barcode, String quantity, String type) {
        try {
            TextInputDialog dialog = new TextInputDialog("" + quantity);
            dialog.setTitle("Maxsulot");
            dialog.setHeaderText("Nomi: " + name1 + "\nBarcode: " + barcode +  "\nMiqdori: " + quantity);
            dialog.setContentText("Miqdorni kiriting");
            Optional<String> result = dialog.showAndWait();
            // The Java 8 way to get the response value (with lambda expression).
            result.ifPresent(name ->
                    {
                        try {
                            paperDao.transferSell(name1, barcode, type, name);
                            paper_id.setText("");
                            paper_name.setText("");
                            paper_barcode.setText("");
                            paper_quantity.setText("");
                            paper_type.setText("");
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
    private void btnPaperSell() {
        try {
            if(!paper_name.getText().isEmpty()) {
                String name = paper_name.getText();
                String barcode = paper_barcode.getText();
                String quantity = paper_quantity.getText();
                String type = paper_type.getText();
                transferDialogSell(name, barcode, quantity, type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnPaperProduction3() {
        try {
            if(!paper_name.getText().isEmpty()) {
                String name = paper_name.getText();
                String barcode = paper_barcode.getText();
                String quantity = paper_quantity.getText();
                String type = paper_type.getText();
                transferDialogProduction3(name, barcode, quantity, type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
