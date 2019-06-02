package sample.controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;
import sample.dao.userDao;
import sample.model.User;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class Login {

    public Button btnKirish;
    public TextField textIsm;
    public PasswordField textPassword;

    public void btnActionKirish() {

        String ismText = textIsm.getText();
        String passText = textPassword.getText();
        Stage stage = new Stage();

        if (isUserExists(ismText, passText)) {
            try {
                String nameOfModule = null;
                if (currentUser.getUserType() == 1) {
                    nameOfModule = "main.fxml";
                } else if (currentUser.getUserType() == 5) {
                    nameOfModule = "Admin.fxml";
                } else if (currentUser.getUserType() == 3) {
                    nameOfModule = "main3.fxml";
                } else if (currentUser.getUserType() == 2) {
                    nameOfModule = "main2.fxml";
                } else if (currentUser.getUserType() == 4) {
                    nameOfModule = "sale.fxml";
                }
                Parent root = FXMLLoader.load(getClass().getResource("../view/" + nameOfModule));
                stage.setTitle("SBD");
                Screen screen = Screen.getPrimary();
                Rectangle2D bounds = screen.getVisualBounds();
                stage.setScene(new Scene(root));
                //stage.initStyle(StageStyle.UNDECORATED);
                stage.setMinHeight(720);
                stage.setMinWidth(1080);
                stage.setFullScreen(false);
                stage.getIcons().add(
                        new Image(
                                Main.class.getResourceAsStream("bar-chart.png")));

                stage.show();
                stage.setOnCloseRequest(e -> Platform.exit());
                stage.setOnCloseRequest(e -> System.exit(0));
                // Hide this current window (if this is what you want)
                this.btnKirish.getScene().getWindow().hide();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Xatolik");
        }
    }

    public static User currentUser;

    private boolean isUserExists(String name, String password) {
        try {
            userDao userDao = new userDao();
            currentUser = userDao.getUser(name, password);
            if (currentUser == null) return false;
            userDao.closeConnection();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
