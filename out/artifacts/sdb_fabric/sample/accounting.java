package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.components.sell.productTableView.balance;
import sample.dao.accountingDao;
import sample.model.AccountXarajat;
import sample.model.ShartnomaTable;
import sample.model.TableExchangeDollar;
import sample.model.core.manList;
import sample.utils.ComboBoxAutoComplete;
import sample.utils.utils;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
    @FXML
    TextField textSchotNumber;
    @FXML
    TextField hrSumma;
    @FXML
    TextField dollarSumma;
    @FXML
    TextArea textIzoh;
    @FXML
    DatePicker dateSchot;
    @FXML
    ComboBox<String> firmaSelectName;
    @FXML
    ComboBox<String> shartnomaSelectName;
    @FXML
    TableView tableAccountBalance;
    @FXML
    TableView tablleAccountHarajat;
    @FXML ComboBox<String> ShartnomaCompanyName;
    @FXML ComboBox<String> accountingSelectCompanyName;
    @FXML TextField ShartnomaName;
    @FXML DatePicker ShartnomaDate;
    @FXML DatePicker accountingDate1;
    @FXML DatePicker accountingDate2;
    @FXML TableView tableShartnoma;
    @FXML TableView tableExchange;
    @FXML Label firma_id;
    @FXML Label accountingTotalHr;
    @FXML Label accountingTotalDollar;




    // Imports
    sample.dao.accountingDao accountingDao = new accountingDao();
    sample.utils.utils utils = new utils();


    private void initializeShartnomaTable() {
        TableColumn company = new TableColumn("Kompaniya");
        TableColumn name = new TableColumn("Nomi");
        TableColumn date = new TableColumn("Sana");
        tableShartnoma.getColumns().addAll(company, name, date);

        company.setCellValueFactory(new PropertyValueFactory<ShartnomaTable, String>("company"));
        name.setCellValueFactory(new PropertyValueFactory<ShartnomaTable, String>("name"));
        date.setCellValueFactory(new PropertyValueFactory<ShartnomaTable, String>("date"));
    }

    private void initializeXarajatTable() {

        TableColumn schot = new TableColumn("Hisob raqam");
        TableColumn schotDate = new TableColumn("Sana");
        TableColumn firma = new TableColumn("Kompaniya");
        TableColumn shartnoma = new TableColumn("Shartnoma");
        TableColumn hr = new TableColumn("Hr sum");
        TableColumn dollar = new TableColumn("Dollar");
        TableColumn description = new TableColumn("Izox");

        tablleAccountHarajat.getColumns().addAll(schot, schotDate, firma, shartnoma, hr, dollar, description);

        schot.setCellValueFactory(new PropertyValueFactory<AccountXarajat, String>("schot"));
        schotDate.setCellValueFactory(new PropertyValueFactory<AccountXarajat, String>("sDate"));
        firma.setCellValueFactory(new PropertyValueFactory<AccountXarajat, String>("firma"));
        shartnoma.setCellValueFactory(new PropertyValueFactory<AccountXarajat, String>("shartnoma"));
        hr.setCellValueFactory(new PropertyValueFactory<AccountXarajat, String>("hr"));
        dollar.setCellValueFactory(new PropertyValueFactory<AccountXarajat, String>("dollar"));
        description.setCellValueFactory(new PropertyValueFactory<AccountXarajat, String>("description"));

    }

    private void initializeEchangeTable() {

        TableColumn hr = new TableColumn("Hisob raqam");
        TableColumn rate = new TableColumn(" Dollar kursi");
        TableColumn sana = new TableColumn("Sana");

        tableExchange.getColumns().addAll(hr, rate, sana);

        hr.setCellValueFactory(new PropertyValueFactory<TableExchangeDollar, String>("hr"));
        rate.setCellValueFactory(new PropertyValueFactory<TableExchangeDollar, String>("rate"));
        sana.setCellValueFactory(new PropertyValueFactory<TableExchangeDollar, String>("sana"));

    }

    private void initializeAccountingBalance() {

        TableColumn who = new TableColumn("Kopmaniya");
        TableColumn sum = new TableColumn("Sum");
        TableColumn dollar = new TableColumn("Dollar");
        TableColumn hr = new TableColumn("Hr");

        tableAccountBalance.getColumns().addAll(who, sum, dollar, hr);

        who.setCellValueFactory(new PropertyValueFactory<balance, String>("who"));
        sum.setCellValueFactory(new PropertyValueFactory<balance, String>("sum_balance"));
        dollar.setCellValueFactory(new PropertyValueFactory<balance, String>("dollar_balance"));
        hr.setCellValueFactory(new PropertyValueFactory<balance, String>("Hr_balance"));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountingSelectCompanyName.getItems().addAll("");
        accountingSelectCompanyName.getSelectionModel().selectFirst();
        setSumms();
        accOper_select.getItems().addAll("hr-vhr");
        accOper_select.getSelectionModel().selectFirst();
        getCompanylist();
        getCompanyXarajat();
        getCompanyName();
        FiltlerCompanyName();
        initializeShartnomaTable();
        initializeXarajatTable();
        initializeEchangeTable();
        initializeAccountingBalance();
        tableShartnoma();
        tableHarajat();
        tableExchange();
        accountingBalance();
        ShartnomaCompanyName.setTooltip(new Tooltip());
        new ComboBoxAutoComplete<String>(ShartnomaCompanyName);

        firmaSelectName.setTooltip(new Tooltip());
        new ComboBoxAutoComplete<String>(firmaSelectName);

        shartnomaSelectName.setTooltip(new Tooltip());
        new ComboBoxAutoComplete<String>(shartnomaSelectName);

        accountingSelectCompanyName.setTooltip(new Tooltip());
        new ComboBoxAutoComplete<String>(accountingSelectCompanyName);

        utils.thousandDivider(hrSumma);
        utils.thousandDivider(dollarSumma);


        tableShartnoma.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                ShartnomaTable shartnomaTable = (ShartnomaTable) tableShartnoma.getSelectionModel().getSelectedItem();
                try {
                    dialogPrint(shartnomaTable.getId());
                    tableShartnoma();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        tablleAccountHarajat.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                AccountXarajat shartnomaTable = (AccountXarajat) tablleAccountHarajat.getSelectionModel().getSelectedItem();
                try {
                    dialogPrintXarajat(shartnomaTable.getId(), shartnomaTable.getHr(), shartnomaTable.getDollar());
                    tableHarajat();

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
                getCompanylist();
                tableShartnoma();
                getCompanylist();
                getCompanyXarajat();
                getCompanyName();
                tableExchange();
                setSumms();
                accountingBalance();
                accountingbtnSaralash();

            }
        }));
        ficeSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        ficeSecondsWonder.play();

        /**
         *  End of timer()
         *
         */
    }

    private void accountingBalance() {
        try{
            accountingDao.aBalanceTable(tableAccountBalance);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void dialogPrint(String id) {
        try {
            TextInputDialog dialog = new TextInputDialog(id);
            dialog.setTitle("O'chirish");
            dialog.setHeaderText("Shartnomani o'chirish");
            dialog.setContentText("O'chirasizmi ?");
            Optional<String> result = dialog.showAndWait();
            // The Java 8 way to get the response value (with lambda expression).
            result.ifPresent(name ->
                    {
                        accountingDao.deleteShartnoma(id);
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dialogPrintXarajat(String id,String hr, String dollar) {
        try {
            TextInputDialog dialog = new TextInputDialog(id);
            dialog.setTitle("O'chirish");
            dialog.setHeaderText("Xarajatni o'chirish");
            dialog.setContentText("O'chirasizmi ?");
            Optional<String> result = dialog.showAndWait();
            // The Java 8 way to get the response value (with lambda expression).
            result.ifPresent(name ->
                    {
                        accountingDao.deleteXarajat(id, hr.trim().replaceAll("\\s+", ""), dollar.trim().replaceAll("\\s+", ""));
                        setSumms();
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            String sum = accSum_text.getText().trim().replaceAll("\\s+", "");
            String dollarRate = accDollarRate.getText().trim().replaceAll("\\s+", "");
            if(accOper_select.getSelectionModel().getSelectedItem() != null && !sum.isEmpty() &&  !dollarRate.isEmpty())
            {
                accountingDao.exchangeHr(operType, sum, dollarRate);
            }else {
                JOptionPane.showMessageDialog(null, "Hamma ma'lumotlarni kiriting!");
            }

            setSumms();
            tableExchange();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            accSum_text.setText("0");
            accDollarRate.setText("0");
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

    @FXML private void btnAddShartnoma() {
        try {
            if(ShartnomaCompanyName.getSelectionModel().getSelectedItem()  != null && !ShartnomaName.getText().isEmpty() && ShartnomaDate.getValue() != null){
                String company = ShartnomaCompanyName.getSelectionModel().getSelectedItem();
                String name = ShartnomaName.getText().trim().replaceAll("\\s+", "");
                LocalDate date1 = ShartnomaDate.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String date = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                accountingDao.addShartnoma(company, name, date);
                ShartnomaCompanyName.setValue("");
                ShartnomaName.setText("");
                ShartnomaDate.setValue(LocalDate.now());
                tableShartnoma();
            } else {
                JOptionPane.showMessageDialog(null, "Hamma ma'lumotlarni kiriting!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tableShartnoma(){
        try {
            accountingDao.shartnomaTableDao(tableShartnoma);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void tableHarajat(){
        try {
                accountingDao.xarajatTableDao(tablleAccountHarajat, "", "", "", accountingTotalHr, accountingTotalDollar);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void tableExchange(){
        try {
            accountingDao.tableExchangeDao(tableExchange);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Harajat onasi
     * */

    @FXML private void btnAddAction() {
        try {
            if(!textSchotNumber.getText().isEmpty() && dateSchot.getValue() != null && firmaSelectName.getSelectionModel().getSelectedItem() != null && shartnomaSelectName.getSelectionModel().getSelectedItem() != null &&
                    !hrSumma.getText().isEmpty() && !dollarSumma.getText().isEmpty() && !textIzoh.getText().isEmpty()) {
                String schotNumber = textSchotNumber.getText().trim().replaceAll("\\s+", "");
                LocalDate date1 = dateSchot.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String schotDate = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String firma = firmaSelectName.getSelectionModel().getSelectedItem();
                String firmaId  = firma_id.getText();
                String shartnome = shartnomaSelectName.getSelectionModel().getSelectedItem();
                String sum = hrSumma.getText().trim().replaceAll("\\s+", "");
                String dollar = dollarSumma.getText().trim().replaceAll("\\s+", "");
                String izoh = textIzoh.getText();
                accountingDao.addHarajat(schotNumber, schotDate, firmaId, shartnome, sum, dollar, izoh);
                tableHarajat();
                setSumms();
                textSchotNumber.setText("");
                hrSumma.setText("0");
                dollarSumma.setText("0");
                textIzoh.setText("0");


            } else {
                JOptionPane.showMessageDialog(null, "Hamma ma'lumotlarni kiriting! ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getCompanylist(){
        try {
            accountingDao.addCompanyCombobox(ShartnomaCompanyName);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void getCompanyName(){
        try {
            accountingDao.addCompanyCombobox(firmaSelectName);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void getCompanyXarajat(){
        try {
            accountingDao.addCompanyList(shartnomaSelectName);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void FiltlerCompanyName(){
        try {
            accountingDao.FiltlerCompanyName(accountingSelectCompanyName);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void getSelectFirmaName() {
        try{
        String  name =    firmaSelectName.getSelectionModel().getSelectedItem();
            manList manList = accountingDao.selectListItem(name);
            firma_id.setText(manList.getId());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void AddSuplier() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/Suplier.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Taminotchi qo'shish");
            stage.setScene(new Scene(root, 900, 500));
            stage.setResizable(false);
            stage.isAlwaysOnTop();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void accountingbtnSaralash() {
        try {
            if(accountingSelectCompanyName.getSelectionModel().getSelectedItem() != null && accountingDate1.getValue() != null && accountingDate2.getValue() != null){

                String company = accountingSelectCompanyName.getSelectionModel().getSelectedItem();
                LocalDate date1 = accountingDate1.getValue();
                LocalDate date2 = accountingDate2.getValue();

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));

                accountingDao.xarajatTableDao(tablleAccountHarajat, company, sdate1, sdate2, accountingTotalHr, accountingTotalDollar);
            } else if(accountingSelectCompanyName.getSelectionModel().getSelectedItem().equals("") && accountingDate1.getValue() != null && accountingDate2.getValue() != null){
                LocalDate date1 = accountingDate1.getValue();
                LocalDate date2 = accountingDate2.getValue();

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));

                accountingDao.xarajatTableDao(tablleAccountHarajat, "", sdate1, sdate2, accountingTotalHr, accountingTotalDollar);
            } else{

                accountingDao.xarajatTableDao(tablleAccountHarajat, "", "", "", accountingTotalHr, accountingTotalDollar);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void btnExcellAction() {
        try{
            if(accountingSelectCompanyName.getSelectionModel().getSelectedItem() != null && accountingDate1.getValue() != null && accountingDate2.getValue() != null){

                String company = accountingSelectCompanyName.getSelectionModel().getSelectedItem();
                LocalDate date1 = accountingDate1.getValue();
                LocalDate date2 = accountingDate2.getValue();

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));

                accountingDao.xarajatExcellTableDao( company, sdate1, sdate2);
            } else if(accountingSelectCompanyName.getSelectionModel().getSelectedItem().equals("") && accountingDate1.getValue() != null && accountingDate2.getValue() != null){
                LocalDate date1 = accountingDate1.getValue();
                LocalDate date2 = accountingDate2.getValue();

                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));

                accountingDao.xarajatExcellTableDao( "", sdate1, sdate2);
            } else{
                accountingDao.xarajatExcellTableDao("", "", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
