package sample.components.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.components.dao.SuplierDao;
import sample.controller.main;
import sample.dao.ProductDao;


import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Suplier implements Initializable {

    @FXML
    TextField id;
    @FXML
    TextField name;
    @FXML
    TextField person;
    @FXML
    TextArea info;
    @FXML
    TableView SuplierTable;
    SuplierDao suplierDao = new SuplierDao();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        intializeTable();
        SuplierTable();
        setUpdate();

    }

    public void intializeTable() {
        TableColumn id = new TableColumn("Tartib raqami");
        TableColumn companyName = new TableColumn("Kompaniya nomi");
        TableColumn person = new TableColumn("Masul shaxs");
        TableColumn info = new TableColumn("Ma'lumot");
        SuplierTable.getColumns().addAll(id, companyName, person, info);

        id.setCellValueFactory(new PropertyValueFactory<sample.components.models.Suplier, Integer>("id"));
        companyName.setCellValueFactory(new PropertyValueFactory<sample.components.models.Suplier, Integer>("companyName"));
        person.setCellValueFactory(new PropertyValueFactory<sample.components.models.Suplier, Integer>("person"));
        info.setCellValueFactory(new PropertyValueFactory<sample.components.models.Suplier, Integer>("info"));
    }

    public void SuplierTable() {
        try {
            suplierDao.initializeTable(SuplierTable);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void btnSaveAction()  {
        try {
            String name1 = name.getText();
            String person1 = person.getText();
            String info1 = info.getText();
            suplierDao.addSuplier(name1, person1, info1);
            SuplierTable();
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error" , "Hamma ma'lumotlarni to'ldiring !", JOptionPane.ERROR_MESSAGE);

        }

    }
    private void setUpdate() {
        try {
            SuplierTable.setOnMouseClicked(event -> {
               sample.components.models.Suplier suplier = (sample.components.models.Suplier) SuplierTable.getSelectionModel().getSelectedItem();
                try {
                    name.setText(suplier.getCompanyName());
                    person.setText(suplier.getPerson());
                    info.setText(suplier.getInfo());
                    id.setText(suplier.getId());
                } catch (Exception exc) {
                }
            });
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Error" + exc, "Xatolik", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void btnUpdateAction(){
        try {
            String name1 = name.getText();
            String person1 = person.getText();
            String info1 = info.getText();
            String id1 = id.getText();
            suplierDao.updateSuplier(name1, person1, info1, id1);
            SuplierTable();
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error" , "Tanimotchi tanlanmagan!", JOptionPane.ERROR_MESSAGE);

        }
    }

    @FXML
    private void btnDeleteAction(){
        try {
            String id1 = id.getText();
            suplierDao.deleteSuplier(id1);
            SuplierTable();
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error" , "Tanimotchi tanlanmagan!", JOptionPane.ERROR_MESSAGE);
        }


    }


}
