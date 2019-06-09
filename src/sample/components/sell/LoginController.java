package sample.components.sell;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import sample.components.sell.Core.User;
import sample.components.sell.DAO.UserDao;
import sample.Main;
import sample.components.sell.Utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


/**
 * Humoyun Qo'rg'onov  SBD(Software Business Development)
 */
public class LoginController implements Initializable {

    public Button btnKirish;
    public TextField textIsm;
    public PasswordField textPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnActionKirish() {

        String ismText = textIsm.getText();
        String passText = textPassword.getText();
        Parent root = null;
        Stage stage = new Stage();

        if (isUserExists(ismText, passText)) {

            try {
                root = FXMLLoader.load(getClass().getResource("views/MainPage.fxml"));
                stage.setTitle("SBD boshqaruv tizimi");
                stage.setOnCloseRequest(event -> sample.components.sell.Main.is_clock_alive = false);
                Screen screen = Screen.getPrimary();
                Rectangle2D bounds = screen.getVisualBounds();
                stage.setScene(new Scene(root));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setFullScreen(true);
                stage.show();
                stage.setOnCloseRequest(e -> Platform.exit());
                stage.setOnCloseRequest(e -> System.exit(0));
                stage.getIcons().add(new Image(Main.class.getResourceAsStream("style/Images/SBD-logo.png")));

                // Hide this current window (if this is what you want)
                this.btnKirish.getScene().getWindow().hide();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Utils.ErrorAlert("Login", "Xatolik bor", "E-mail yoki parol xato");
        }
    }

    public static User currentUser;

    private boolean isUserExists(String name, String password) {
        try {
            UserDao userDao = new UserDao();
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
