package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.dao.accountingDao;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class accounting implements Initializable {

    @FXML
    Label accHR_label;
    @FXML
    Label accVAL_label;
    @FXML
    ComboBox<String> accOper_select;
    @FXML
    TextField accSum_text;
    @FXML
    TextField accDollarRate;
    @FXML
    Button btnClose;

    // Imports
    sample.dao.accountingDao accountingDao = new accountingDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setSumms();
        accOper_select.getItems().addAll("hr-vhr");
        accOper_select.getSelectionModel().selectFirst();
    }

    private void setSumms() {
        try {
            accountingDao.setLabels(accHR_label, accVAL_label);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnAccExchangeAction() {
        exchange();
    }

    private void exchange() {
        try {
            String operType = accOper_select.getSelectionModel().getSelectedItem();
            String sum = accSum_text.getText();
            String dollarRate = accDollarRate.getText();
            accountingDao.exchangeHr(operType, sum, dollarRate);
            setSumms();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            accSum_text.setText("");
            accDollarRate.setText("");
        }
    }

    @FXML
    private void btnAccQuitAction() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Chiqish");
        alert.setHeaderText(null);
        alert.setContentText(" Dasturdan chiqmoqchimisiz ?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent())
            if (result.get() == ButtonType.OK) {

                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
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
