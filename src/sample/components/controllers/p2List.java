package sample.components.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.components.dao.p2ListDao;
import sample.components.models.P2List;
import sample.utils.ComboBoxAutoComplete;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class p2List implements Initializable {

    @FXML
    private TableView listTable;
    @FXML
    private TextField listName;
    @FXML
    private TextField listBarcode;
    @FXML
    private ComboBox<String> comboListType;
    @FXML
    private TextArea listDescription;
    @FXML
    private Label textId;

    p2ListDao p2ListDao = new p2ListDao();


    public void table() {

        TableColumn id = new TableColumn("Tartib raqami");
        TableColumn name = new TableColumn("Nomi");
        TableColumn barcode = new TableColumn("Barcode");
        TableColumn type = new TableColumn("Turi");
        TableColumn description = new TableColumn("Ma'lumot");

        listTable.getColumns().addAll(id, name, barcode, type, description);

        id.setCellValueFactory(new PropertyValueFactory<P2List, String>("id"));
        name.setCellValueFactory(new PropertyValueFactory<P2List, String>("name"));
        barcode.setCellValueFactory(new PropertyValueFactory<P2List, String>("barcode"));
        type.setCellValueFactory(new PropertyValueFactory<P2List, String>("type"));
        description.setCellValueFactory(new PropertyValueFactory<P2List, String>("description"));
    }

    public void btnAddAction(ActionEvent actionEvent) {
        try{
            String name = listName.getText().trim().replaceAll("\\s+", "");
            String barcode = listBarcode.getText().trim().replaceAll("\\s+", "");
            String type = comboListType.getSelectionModel().getSelectedItem();
            String description = listDescription.getText().trim().replaceAll("\\s+", "");
            if(!name.isEmpty() && !barcode.isEmpty() && !type.isEmpty() && !description.isEmpty())
            {
                p2ListDao.addList(name, barcode, type, description);
                listTable();
            } else {
                listDescription.setPromptText("Hamma malumotlarni kiriting!");
                listName.setPromptText("Hamma malumotlarni kiriting!");
                listBarcode.setPromptText("Hamma malumotlarni kiriting!");
                comboListType.setPromptText("Hamma malumotlarni kiriting!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void btnDeleteAction(ActionEvent actionEvent) {
        String  id = textId.getText();
        try {
            p2ListDao.delete(id);
            listTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateAction(ActionEvent actionEvent) {
        try{
            String name = listName.getText().trim().replaceAll("\\s+", "");
            String barcode = listBarcode.getText().trim().replaceAll("\\s+", "");

            String description = listDescription.getText().trim().replaceAll("\\s+", "");
            String id = textId.getText();
            if(!name.isEmpty() && !barcode.isEmpty() && !description.isEmpty())
            {
                p2ListDao.updateList(id, name, barcode, description);
                listTable();
            } else {
                listDescription.setPromptText("Hamma malumotlarni kiriting!");
                listName.setPromptText("Hamma malumotlarni kiriting!");
                listBarcode.setPromptText("Hamma malumotlarni kiriting!");
                comboListType.setPromptText("Hamma malumotlarni kiriting!");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboListType.setTooltip(new Tooltip());
        new ComboBoxAutoComplete<String>(comboListType);
        setOzgaartirishMaxsulot();
        listType();
        listTable();
        table();
        comboListType.setOnAction( Event->{
            listType();
        });
    }

    private void listTable() {
        try{
            p2ListDao.listTable(listTable);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void listType() {
        try{
            p2ListDao.addTypeCombobox(comboListType);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setOzgaartirishMaxsulot() {

        try {
            listTable.setOnMouseClicked(event -> {
                if (listTable.getSelectionModel().getSelectedItem() != null) {
                    P2List p2List1 = (P2List) listTable.getSelectionModel().getSelectedItem();
                    try {
                        listName.setText(p2List1.getName());
                        listBarcode.setText(p2List1.getBarcode());
                        comboListType.setValue(p2List1.getType());
                        listDescription.setText(p2List1.getDescription());
                        textId.setText(p2List1.getId());
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                }
            });
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

}
