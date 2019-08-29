package sample.components.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.components.dao.ColorDao;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Color implements Initializable {


    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField textColorName;
    @FXML
    private TextArea textColorDescription;
    @FXML
    private TableView ColorTable;
    @FXML
    private Label labelId;

    ColorDao colorDao = new ColorDao();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn id = new TableColumn("ID");
        TableColumn name = new TableColumn("Nomi");
        TableColumn description = new TableColumn("Ma'lumot");
        ColorTable.getColumns().addAll(id, name, description);

        id.setCellValueFactory(new PropertyValueFactory<Color, String>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Color, String>("name"));
        description.setCellValueFactory(new PropertyValueFactory<Color, String>("description"));
        colorTable();
        setUpdate();
    }

    private void colorTable() {
        try {
            colorDao.ColorTable(ColorTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnColorAddAction() {
        addColor();
        colorTable();
    }

    private void addColor() {
        try {
            String name = textColorName.getText().trim().replaceAll("\\s+", "");
            String description = textColorDescription.getText().trim().replaceAll("\\s+", "");
            colorDao.addColor(name, description);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnColorTableUpdate() {

        try {
            String id = labelId.getText().trim().replaceAll("\\s+", "");
            String name = textColorName.getText().trim().replaceAll("\\s+", "");
            String description = textColorDescription.getText().trim().replaceAll("\\s+", "");
            colorDao.colorUpdate(name, description, id);
            colorTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpdate() {
        try {
            ColorTable.setOnMouseClicked(event -> {
                sample.components.models.Color color = (sample.components.models.Color) ColorTable.getSelectionModel().getSelectedItem();
                try {
                    textColorName.setText(color.getName());
                    textColorDescription.setText(color.getDescription());
                    labelId.setText(color.getId());
                } catch (Exception exc) {
                }
            });
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Error" + exc, "Xatolik", JOptionPane.ERROR_MESSAGE);
        }
    }

}
