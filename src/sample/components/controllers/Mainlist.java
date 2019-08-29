package sample.components.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.components.dao.ListDao;
import sample.components.models.ManList;
import sample.utils.ComboBoxAutoComplete;

import java.net.URL;
import java.util.ResourceBundle;

public class Mainlist implements Initializable {

    @FXML
    private TextField listName;
    @FXML
    private TextField listBarcode;
    @FXML
    private ComboBox<String> comboListType;
    @FXML
    private TextArea listDescription;
    @FXML
    private TableView listTable;
    @FXML
    private Label list_id;
    ListDao listDao = new ListDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listTable();
        listType();
        setOzgaartirishMaxsulot();
        comboListType.setTooltip(new Tooltip());
        new ComboBoxAutoComplete<String>(comboListType);

        TableColumn id = new TableColumn("Tartib raqami");
        TableColumn name = new TableColumn("Nomi");
        TableColumn barcode = new TableColumn("Barcode");
        TableColumn type = new TableColumn("Turi");
        TableColumn description = new TableColumn("Ma'lumot");

        listTable.getColumns().addAll(id, name, barcode, type, description);

        id.setCellValueFactory(new PropertyValueFactory<Invoice, String>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Invoice, String>("name"));
        barcode.setCellValueFactory(new PropertyValueFactory<Invoice, String>("barcode"));
        type.setCellValueFactory(new PropertyValueFactory<Invoice, String>("type"));
        description.setCellValueFactory(new PropertyValueFactory<Invoice, String>("description"));
    }

    private void setOzgaartirishMaxsulot() {

        try {
            listTable.setOnMouseClicked(event -> {
                if (listTable.getSelectionModel().getSelectedItem() != null) {
                    ManList manList = (ManList) listTable.getSelectionModel().getSelectedItem();
                    try {
                        listName.setText(manList.getBarcode());
                        listBarcode.setText(manList.getName());
                        comboListType.setValue(manList.getType());
                        listDescription.setText(manList.getDescription());
                        list_id.setText(manList.getId());
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                }
            });
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }


    private void listTable() {
        try{
            listDao.listTable(listTable);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void listType() {
        try{
            listDao.addTypeCombobox(comboListType);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void btnAddAction() {
        try{
            String name = listName.getText().trim().replaceAll("\\s+", "");
            String barcode = listBarcode.getText().trim().replaceAll("\\s+", "");
            String type = comboListType.getSelectionModel().getSelectedItem();
            String description = listDescription.getText().trim().replaceAll("\\s+", "");
            if(!name.isEmpty() && !barcode.isEmpty() && !type.isEmpty() && !description.isEmpty()) {
                listDao.addList(name, barcode, type, description);
                listTable();
            } else {
                listName.setPromptText("Hamma malumotlarni kiriting");
                listBarcode.setPromptText("Hamma malumotlarni kiriting");
                comboListType.setPromptText("Hamma malumotlarni kiriting");
                listDescription.setPromptText("Hamma malumotlarni kiriting");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void btnDeleteAction() {
        try {
            String id = list_id.getText();
            listDao.deleteList(id);
            listTable();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void btnUpdateAction() {
        try{
            String name = listName.getText().trim().replaceAll("\\s+", "");
            String barcode = listBarcode.getText().trim().replaceAll("\\s+", "");
            String description = listDescription.getText().trim().replaceAll("\\s+", "");
            String id = list_id.getText();
            if(!name.isEmpty() && !barcode.isEmpty() && !description.isEmpty()) {
                listDao.updateList(id, name, barcode, description);
                listTable();
            } else {
                listName.setPromptText("Hamma malumotlarni kiriting");
                listBarcode.setPromptText("Hamma malumotlarni kiriting");
                listDescription.setPromptText("Hamma malumotlarni kiriting");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
