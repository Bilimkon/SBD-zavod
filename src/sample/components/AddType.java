package sample.components;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.components.dao.TypeDao;
import sample.components.models.Type;
import sample.dao.database;
import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AddType implements Initializable {

    @FXML
    TextField TypeName;
    @FXML
    TextArea TypeDescription;
    @FXML
    ComboBox Unit;
    @FXML
    Button SaveBtn;
    @FXML
    TextField TypeId;
    @FXML
    TableView TypeTable;

    Connection myConn = null;
    TypeDao typeDao = new TypeDao();

    public AddType() {
        try {
            myConn = database.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Unit.getItems().addAll("Dona", "Kg", "Litr", "Rulon");

        TableColumn id = new TableColumn("Tartib raqami");
        TableColumn name = new TableColumn("Nomi");
        TableColumn measurement = new TableColumn("O'lchov birligi");
        TableColumn description = new TableColumn("Tarif");
        TableColumn date = new TableColumn("Sana");
        TableColumn cr_by = new TableColumn("Ishchi");

        TypeTable.getColumns().addAll(id, name, measurement, description, date, cr_by);

        id.setCellValueFactory(new PropertyValueFactory<Type, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Type, String>("name"));
        measurement.setCellValueFactory(new PropertyValueFactory<Type, String>("unit"));
        description.setCellValueFactory(new PropertyValueFactory<Type, String>("info"));
        date.setCellValueFactory(new PropertyValueFactory<Type, String>("date"));
        cr_by.setCellValueFactory(new PropertyValueFactory<Type, String>("cr_by"));

        try {
            typeTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setUpdate();
    }

    private void typeTable() throws SQLException {
        typeDao.initializeTable(TypeTable);
    }

    private void setUpdate() {
        try {
            TypeTable.setOnMouseClicked(event -> {
                Type type = (Type) TypeTable.getSelectionModel().getSelectedItem();
                try {
                    TypeId.setText(type.getId());
                    TypeName.setText(type.getName());
                    TypeDescription.setText(type.getInfo());
                } catch (Exception exc) {
                }
            });
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Error" + exc, "Xatolik", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void AddTypeSaveAction() throws Exception {

        String name = TypeName.getText();
        String info = TypeDescription.getText();
        String unit = Unit.getValue().toString();
        typeDao.addType(name,unit,info);
        typeTable();
    }

    public void updateType() throws Exception {
        String id = TypeId.getText();
        String name = TypeName.getText();
        String info = TypeDescription.getText();
        typeDao.updateType(name, info, id);
        typeTable();
    }

    public void deleteType() throws Exception {
        String id = TypeId.getText();
        typeDao.deleteType(id);
        typeTable();
    }
}
