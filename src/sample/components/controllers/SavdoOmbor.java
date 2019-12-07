package sample.components.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.components.dao.SavdoOmborDao;
import sample.components.models.SavdoOmbori;
import sample.utils.ComboBoxAutoComplete;

import java.net.URL;
import java.util.ResourceBundle;

public class SavdoOmbor implements Initializable {

    @FXML private TableView SellTable;
    @FXML private ComboBox<String> name;
    @FXML private ComboBox<String> type;
    @FXML private Label totalQuantity;
    @FXML private Label totalCost;
    @FXML private Label IdS;
    @FXML private TextField BarcodeS;
    @FXML private TextField NameS;
    @FXML private TextField QuantityS;
    @FXML private TextField CostS;
    SavdoOmborDao savdoOmborDao = new SavdoOmborDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type.getItems().addAll("");
        name.getItems().addAll("");
        type.setTooltip(new Tooltip());
        new ComboBoxAutoComplete<String>(type);
        name.setTooltip(new Tooltip());
        new ComboBoxAutoComplete<String>(name);

       initializeTable();
       getTypeList();
       getNameList();
    }

    private void initializeTable(){
        TableColumn<SavdoOmbori, String> id = new TableColumn<>("Id");
        TableColumn<SavdoOmbori, String> barcode = new TableColumn<>("Barcode");
        TableColumn<SavdoOmbori, String> name = new TableColumn<>("Nomi");
        TableColumn<SavdoOmbori, String> quantity = new TableColumn<>("Miqdori");
        TableColumn<SavdoOmbori, String> type = new TableColumn<>("Turi");
        TableColumn<SavdoOmbori, String> cost = new TableColumn<>("Narxi");

        SellTable.getColumns().addAll(barcode, type, name, quantity, cost);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        barcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        SellTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 1) {
                SavdoOmbori history = (SavdoOmbori) SellTable.getSelectionModel().getSelectedItem();
                try {
                       IdS.setText(history.getId());
                       BarcodeS.setText(history.getBarcode());
                       NameS.setText(history.getName());
                       QuantityS.setText(history.getQuantity());
                       CostS.setText(history.getCost());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }



    private void table(){
        try{

            try {
                if ( type.getSelectionModel().getSelectedItem() != null && name.getSelectionModel().getSelectedItem() != null) {
                    String customer = type.getSelectionModel().getSelectedItem();
                    String name1 = name.getSelectionModel().getSelectedItem();

                    savdoOmborDao.SellTableDao(SellTable, customer, name1, totalQuantity, totalCost);
                } else if ( type.getSelectionModel().getSelectedItem() == null && name.getSelectionModel().getSelectedItem() != null) {

                    savdoOmborDao.SellTableDao(SellTable, "", name.getSelectionModel().getSelectedItem(), totalQuantity, totalCost);
                } else if (type.getSelectionModel().getSelectedItem() != null && name.getSelectionModel().getSelectedItem() == null ) {
                    savdoOmborDao.SellTableDao(SellTable, type.getSelectionModel().getSelectedItem(), "", totalQuantity, totalCost);

                }
                 else if ( type.getSelectionModel().getSelectedItem() == null && name.getSelectionModel().getSelectedItem() == null) {
                    savdoOmborDao.SellTableDao(SellTable, "", "", totalQuantity, totalCost);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }catch (Exception e){
            e.fillInStackTrace();
        }
    }

    @FXML
    private void getTypeList(){
        try{
            savdoOmborDao.getTypeList(type);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void getNameList(){
        try{
            savdoOmborDao.getNameList(name);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML private void btnFilterAction(){
        table();
    }

    @FXML private void btnExcellAction(){

        try {
            if ( type.getSelectionModel().getSelectedItem() != null && name.getSelectionModel().getSelectedItem() != null) {
                String customer = type.getSelectionModel().getSelectedItem();
                String name1 = name.getSelectionModel().getSelectedItem();

                savdoOmborDao.SellExcellTableDao( customer, name1);
            } else if ( type.getSelectionModel().getSelectedItem() == null && name.getSelectionModel().getSelectedItem() != null) {

                savdoOmborDao.SellExcellTableDao( "", name.getSelectionModel().getSelectedItem());
            } else if (type.getSelectionModel().getSelectedItem() != null && name.getSelectionModel().getSelectedItem() == null ) {
                savdoOmborDao.SellExcellTableDao(type.getSelectionModel().getSelectedItem(), "");

            }
            else if ( type.getSelectionModel().getSelectedItem() == null && name.getSelectionModel().getSelectedItem() == null) {
                savdoOmborDao.SellExcellTableDao( "", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML private void saveAction(){
       try {
           savdoOmborDao.UpdateProduct(IdS.getText().trim().replaceAll("\\s+", ""), NameS.getText().trim().replaceAll("\\s+", ""), QuantityS.getText().trim().replaceAll("\\s+", ""), CostS.getText().trim().replaceAll("\\s+", ""), IdS);
           table();
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}
