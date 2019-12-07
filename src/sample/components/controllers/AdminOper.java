package sample.components.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import sample.components.dao.AdminOperDao;
import sample.components.models.AdminOperModel;
import sample.utils.utils;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminOper implements Initializable {

    public Button btnExcell;
    @FXML
    ComboBox<String> AdminOperSelectName;
    @FXML
    TextField AdminOperSum;
    @FXML
    TextField AdminOperDollar;
    @FXML
    TextField AdminOperHr;
    @FXML
    TextArea AdminOperIzoh;
    @FXML
    TableView AdminOperTable;
    @FXML
    Label AdminOperSumLabel;
    @FXML
    Label AdminOperDollarLabal;
    @FXML
    Label AdminOperHrLabel;
    @FXML
    Label AdminOperVhrLabel;
    @FXML DatePicker date1;
    @FXML DatePicker date2;
    @FXML Label total_sum;
    @FXML Label total_dollar;
    @FXML Label total_hr;
    @FXML Label total_vhr;
    @FXML ComboBox<String> ComboWho;
    @FXML Label LabelWho;


    //AdminOperDao
    AdminOperDao adminOperDao = new AdminOperDao();
    sample.utils.utils utils = new utils();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AdminOperSelectName.getItems().addAll("Kirim", "Chiqim");
        setSums();
        initializeAdminOperTable();
        AdminOperTable();
        utils.thousandDivider(AdminOperSum);
        utils.thousandDivider(AdminOperDollar);
        utils.thousandDivider(AdminOperHr);
        utils.selectAll(AdminOperSum);
        utils.selectAll(AdminOperDollar);
        utils.selectAll(AdminOperHr);
        adminOperDao.GetPerson(ComboWho);


        AdminOperTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                AdminOperModel history = (AdminOperModel) AdminOperTable.getSelectionModel().getSelectedItem();
                try {
                    dialogRevert(String.valueOf(history.getId()), history.getType(), history.getWho(), history.getSum(), history.getDollar(), history.getHr());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        /**
         *   Timer()
         */

        Timeline ficeSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setSums();
                AdminFilterBtn();

            }
        }));
        ficeSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        ficeSecondsWonder.play();

        /**
         *  End of timer()
         *
         */
    }

    private void initializeAdminOperTable() {
        TableColumn<AdminOperModel, String> id = new TableColumn<>("Id");
        TableColumn<AdminOperModel, String> type = new TableColumn<>("Turi");
        TableColumn<AdminOperModel, String> who = new TableColumn<>("Kim");
        TableColumn<AdminOperModel, String> sum = new TableColumn<>("Sum");
        TableColumn<AdminOperModel, String> dollar = new TableColumn<>("Dollar");
        TableColumn<AdminOperModel, String> hr = new TableColumn<>("Hisob raqam");
        TableColumn<AdminOperModel, String> vhr = new TableColumn<>("Valyuta hr");
        TableColumn<AdminOperModel, String> comment = new TableColumn<>("Izoh");
        TableColumn<AdminOperModel, String> cr_on = new TableColumn<>("Sana");

        AdminOperTable.getColumns().addAll(type, who, sum, dollar, hr,  comment, cr_on);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        who.setCellValueFactory(new PropertyValueFactory<>("who"));
        sum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        dollar.setCellValueFactory(new PropertyValueFactory<>("dollar"));
        hr.setCellValueFactory(new PropertyValueFactory<>("hr"));
        vhr.setCellValueFactory(new PropertyValueFactory<>("vhr"));
        comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        cr_on.setCellValueFactory(new PropertyValueFactory<>("cr_on"));


    }

    private void setSums(){
        adminOperDao.total_balance(AdminOperSumLabel,AdminOperDollarLabal,AdminOperHrLabel,AdminOperVhrLabel);
    }

    private void AdminOperTable(){
        try{
            if(date1.getValue() != null && date2.getValue() != null){
                LocalDate date11 = date1.getValue();
                LocalDate date22 = date2.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date11.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date22.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                adminOperDao.adminTable(sdate1, sdate2, AdminOperTable, total_sum, total_dollar, total_hr, total_vhr);

            } else {
            adminOperDao.adminTable("", "", AdminOperTable, total_sum, total_dollar, total_hr, total_vhr);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void btnLabelWhoAction(){
        try {
            adminOperDao.getWhoName(ComboWho.getSelectionModel().getSelectedItem(), LabelWho);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void addExpence() {
        if (AdminOperSelectName.getSelectionModel().getSelectedItem() != null && ComboWho.getSelectionModel().getSelectedItem()!= null && !AdminOperSum.getText().isEmpty()
                && !AdminOperDollar.getText().isEmpty() && !AdminOperHr.getText().isEmpty() &&  !AdminOperIzoh.getText().isEmpty()) {

            //Addig Eexpence
                String sum = AdminOperSum.getText().trim().replaceAll("\\s+", "");
                String dollar = AdminOperDollar.getText().trim().replaceAll("\\s+", "");
                String hr = AdminOperHr.getText().trim().replaceAll("\\s+", "");

            adminOperDao.addExpenceDaoChiqim(AdminOperSelectName.getSelectionModel().getSelectedItem(), LabelWho.getText(), sum,
                    dollar, hr,  AdminOperIzoh.getText());

            AdminOperSelectName.setValue("");
            AdminOperSum.setText("");
            AdminOperDollar.setText("");
            AdminOperHr.setText("");
            AdminOperIzoh.setText("");
            setSums();
        } else {
            AdminOperSelectName.setStyle("-fx-background-color:red; ");
            AdminOperSum.setStyle("-fx-background-color:red; ");
            AdminOperDollar.setStyle("-fx-background-color:red; ");
            AdminOperHr.setStyle("-fx-background-color:red; ");
            AdminOperIzoh.setStyle("-fx-background-color:red; ");
        }

    }

    @FXML
    public void AdminFilterBtn() {
        AdminOperTable();
    }

    @FXML
    public void AdminExcellBtn() {
        try{
            if(date1.getValue() != null && date2.getValue() != null){
                LocalDate date11 = date1.getValue();
                LocalDate date22 = date2.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date11.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date22.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                adminOperDao.adminTableExcell(btnExcell,sdate1, sdate2, AdminOperTable);

            } else {
                adminOperDao.adminTableExcell(btnExcell,"", "", AdminOperTable);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void dialogRevert(String id, String operType1, String who, String sum, String dollar,String  hr) {
        try {
            TextInputDialog dialog = new TextInputDialog(id);
            dialog.setTitle("Operatsiya");
            dialog.setHeaderText("Operatsiya nomeri: " + id);
            dialog.setContentText("Operatsiyani ortga qaytarasizmi ?");
            Optional<String> result = dialog.showAndWait();
            // The Java 8 way to get the response value (with lambda expression).
            result.ifPresent(name ->
                    {
                            adminOperDao.revertOperKirim(id, operType1, who, sum.trim().replaceAll("\\s+", ""), dollar.trim().replaceAll("\\s+", ""), hr.trim().replaceAll("\\s+", ""));
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
