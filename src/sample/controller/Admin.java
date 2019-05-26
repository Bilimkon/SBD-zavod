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
import sample.dao.AdminDao;
import sample.dao.database;
import sample.model.UserTable;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Optional;
import java.util.ResourceBundle;

public class Admin implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField username;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField phone;
    @FXML
    private TextField password;
    @FXML
    private Button btnClose;
    @FXML
    private ComboBox Role;
    @FXML
    private TableView userTable;
    AdminDao adminDao = new AdminDao();


    private Connection myConn = null;

    public Admin() {
        try {
            myConn = database.getConnection();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Xatolik" + exc, "Xatolik", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Role.getItems().addAll("Ombor", "1-ish/ch", "2-ish/ch", "Savdo", "Admin");
        initializeTable();
        userTable();
        setUpdate();
    }

    private void initializeTable() {
        TableColumn id = new TableColumn("Tartib raqami");
        TableColumn username = new TableColumn("Username");
        TableColumn firstname = new TableColumn("Ism");
        TableColumn lastname = new TableColumn("Familiya");
        TableColumn password = new TableColumn("Password");
        TableColumn userType = new TableColumn("userType");
        TableColumn phone = new TableColumn("Telefon");
        userTable.getColumns().addAll(id, username, firstname, lastname, phone, password, userType);

        id.setCellValueFactory(new PropertyValueFactory<UserTable, String>("id"));
        username.setCellValueFactory(new PropertyValueFactory<UserTable, String>("username"));
        firstname.setCellValueFactory(new PropertyValueFactory<UserTable, String>("firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<UserTable, String>("lastname"));
        phone.setCellValueFactory(new PropertyValueFactory<UserTable, String>("phone"));
        userType.setCellValueFactory(new PropertyValueFactory<UserTable, String>("userType"));
        password.setCellValueFactory(new PropertyValueFactory<UserTable, String>("password"));
    }

    private void userTable() {
        try {
            adminDao.userTable(userTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnSaveAction() {

        try {
            String username1 = username.getText();
            String firstname1 = firstname.getText();
            String lastname1 = lastname.getText();
            String password1 = password.getText();
            String phone1 = phone.getText();
            String userType = Role.getValue().toString();
            adminDao.addUser(username1, firstname1, lastname1, phone1, password1, userType);
            userTable();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Hamma ma'lumotlarni kiritishingiz shart !");
        }

    }

    private void setUpdate() {
        try {
            userTable.setOnMouseClicked(event -> {
                UserTable userTable1 = (UserTable) userTable.getSelectionModel().getSelectedItem();
                try {
                    username.setText(userTable1.getUsername());
                    firstname.setText(userTable1.getFisrtname());
                    lastname.setText(userTable1.getLastname());
                    password.setText(userTable1.getPassword());
                    Role.setValue(userTable1.getUserType());
                    phone.setText(userTable1.getPhone());
                    id.setText(userTable1.getId());
                } catch (Exception exc) {
                }
            });
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Error" + exc, "Xatolik", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void btnUpdateAction() {
        try {
            String username1 = username.getText();
            String firstname1 = firstname.getText();
            String lastname1 = lastname.getText();
            String password1 = password.getText();
            String phone1 = phone.getText();
            String userType = Role.getValue().toString();
            String id1 = id.getText();
            adminDao.updateUser(id1, username1, firstname1, lastname1, phone1, password1, userType);
            userTable();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error", "Xatolik", JOptionPane.ERROR_MESSAGE);

        }
    }

    @FXML
    private void btnDeleteAction() {
        try {
            String id1 = id.getText();
            adminDao.deleteUser(id1);
            userTable();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error", "Xatolik", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void btnCloseAction() {
       closeAction(btnClose);
    }

    public void closeAction(Button btn){
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

}
