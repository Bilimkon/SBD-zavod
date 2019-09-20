package sample;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sample.components.sell.Core.History;
import sample.components.sell.DAO.UtilsDao;
import sample.components.sell.Utils.Utils;
import sample.components.sell.productTableView.OperTable;
import sample.dao.AdminDao;
import sample.dao.DaoUtils;
import sample.dao.ProductDao;
import sample.dao.database;
import sample.model.Marketing;
import sample.model.Product;
import sample.model.UserTable;
import sample.utils.Workbookcontroller;
import sample.utils.utils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;

public class Admin1 implements Initializable {

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
    @FXML
    private ComboBox<String> CompanyNameM;
    @FXML
    private TextField NameM;
    @FXML
    private TextField Barcode_OM;
    @FXML
    private TextField BarcodeM;
    @FXML
    private TextField ColorM;
    @FXML
    private TextField CostM;
    @FXML
    private TableView MarketingTableM;
    @FXML
    private JFXTextField idM;
    @FXML
    private ComboBox<String> ColorFilter;
    @FXML
    private ComboBox<String> NameFilter;
    @FXML
    private ComboBox<String> CompanyFilter;
    @FXML
    private Label TotalDollarSum;
    @FXML
    private Label TotalSum;
    @FXML
    private Label LabelSumTotal;
    @FXML
    private Label LabelDollarTotal;
    @FXML
    private Label LabelAccountTotal;
    @FXML
    private Label LabelVHR_Total;
    @FXML
    private ComboBox<String> ExchangeCombobox;
    @FXML
    private TextField ExchangeSumma;
    @FXML
    private TextField DollarRate;
    @FXML
    private Label LabelExchangeMoney;

    // tab ombor
    @FXML
    private TableView tableA_ombor;
    @FXML
    private JFXDatePicker danOmborLog;
    @FXML
    private JFXDatePicker gachaOmborLog;
    @FXML
    private TableView tableOmborLog;
    // end of tab ombor

    // tab 1-ish/ch
    @FXML
    private JFXDatePicker danMain1;
    @FXML
    private JFXDatePicker gachaMain1;
    @FXML
    TableView tableMain1;
    // tab 2-ish/ch
    @FXML
    private JFXDatePicker danMain2;
    @FXML
    private JFXDatePicker gachaMain2;
    @FXML
    private TableView tableMain2;
    // tab admin
    @FXML
    private JFXDatePicker danAdmin;
    @FXML
    private JFXDatePicker gachaAdmin;
    @FXML
    private TableView tableAdminLog;

    // chart
    @FXML
    private JFXDatePicker danChart;
    @FXML
    private JFXDatePicker gachaChart;
    @FXML
    private AreaChart chartAdminSale;

    // Savdo
    @FXML
    private JFXDatePicker danOper;
    @FXML
    private JFXDatePicker gachaOper;
    @FXML
    private TableView tableOperAdmin;

    @FXML
    private JFXDatePicker danSaleHistory;
    @FXML
    private JFXDatePicker gachaSaleHistoty;
    @FXML
    private TableView tableSaleHistory;

    String user_id = String.valueOf(Login.currentUser.getId());
    String apple = Utils.convertDateToStandardFormat(Utils.getCurrentDate());
    Workbookcontroller workbookcontroller = new Workbookcontroller();

    AdminDao adminDao = new AdminDao();
    DaoUtils daoUtils = new DaoUtils();
    sample.utils.utils utils = new utils();

    private Connection myConn = null;

    public Admin1() {
        try {
            myConn = database.getConnection();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Xatolik" + exc, "Xatolik", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Role.getItems().addAll("Ombor", "1-ish/ch", "2-ish/ch", "Savdo", "Admin1","Accounting");
        ExchangeCombobox.getItems().addAll("Sum-Dollar", "HR-Dollar", "Dollar-Sum", "Sum-Hr", "Hr-Sum");
        initializeTable();
        initializeMarketingTable();
        initializeProductTab();
        initializeOmborLogTable();
        initialize1ishchLogTable();
        initialize2ischLogTable();
        intitializeAdminlogTable();
        initializeHistoryTable();
        initializeOperTable();
        userTable();
        marketingTable();
        omborATable();
        chart();
        LogTable();
        main1Table();
        main2Table();
        AdminLogTable();
        SaleOperTable();
        SaleTarixTable();
        setUpdate();
        setUpdateMarketing();
        AddCompanyCombobox();
        colorCombobox();
        total_Sum();
        utils.thousandDivider(ExchangeSumma);
        utils.thousandDivider(DollarRate);

        /**
         *   Timer()
         */
        Timeline ficeSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                userTable();
                marketingTable();
                omborATable();
                LogTable();
                main1Table();
                main2Table();
                AdminLogTable();
                SaleOperTable();
                SaleTarixTable();
                total_Sum();
            }
        }));
        ficeSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        ficeSecondsWonder.play();

        /**
         *  End of timer()
         *
         */
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

    private void initializeMarketingTable() {
        TableColumn id = new TableColumn("Tartib raqami");
        TableColumn company = new TableColumn("Kompaniya nomi");
        TableColumn name = new TableColumn("Maxsulot nomi");
        TableColumn barcode_o = new TableColumn("Kompaniya barcodi");
        TableColumn barcode = new TableColumn("Bizning barcode");
        TableColumn color = new TableColumn("Rangi");
        TableColumn cost = new TableColumn("Narxi");
        MarketingTableM.getColumns().addAll(id, company, name, barcode_o, barcode, color, cost);

        id.setCellValueFactory(new PropertyValueFactory<Marketing, String>("id"));
        company.setCellValueFactory(new PropertyValueFactory<Marketing, String>("company"));
        name.setCellValueFactory(new PropertyValueFactory<Marketing, String>("name"));
        barcode_o.setCellValueFactory(new PropertyValueFactory<Marketing, String>("barcode_o"));
        barcode.setCellValueFactory(new PropertyValueFactory<Marketing, String>("barcode"));
        color.setCellValueFactory(new PropertyValueFactory<Marketing, String>("color"));
        cost.setCellValueFactory(new PropertyValueFactory<Marketing, String>("cost"));

    }

    private void initializeOmborLogTable() {
        TableColumn id = new TableColumn("Id");
        TableColumn module = new TableColumn("Modul");
        TableColumn type = new TableColumn("Turi");
        TableColumn cost = new TableColumn("Narxi");
        TableColumn cr_by = new TableColumn("Ishchi");
        TableColumn date = new TableColumn("Sana");
        TableColumn comment = new TableColumn("Ma'lumot");
        tableOmborLog.getColumns().addAll(id, module, type, cost, cr_by, date, comment);

        id.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("id"));
        module.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("module"));
        type.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("type"));
        cost.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("cost"));
        cr_by.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("cr_by"));
        date.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("date"));
        comment.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("comment"));
    }

    // initialize ombor table

    private void initializeProductTab() {
        TableColumn<Product, String> id = new TableColumn<>("Id");
        TableColumn<Product, String> invoice = new TableColumn<>("invoice");
        TableColumn<Product, String> barcode = new TableColumn<>("Barcode");
        TableColumn<Product, String> name = new TableColumn<>("Nomi");
        TableColumn<Product, String> type = new TableColumn<>("Turi");
        TableColumn<Product, String> cost = new TableColumn<>("Narxi");
        TableColumn<Product, String> quantity = new TableColumn<>("Miqdori");
        TableColumn<Product, String> user = new TableColumn<>("Ishchi");
        TableColumn<Product, String> date = new TableColumn<>("Sana");
        TableColumn<Product, String> suplier = new TableColumn<>("Taminotchi");
        TableColumn<Product, String> unit = new TableColumn<>("Birlik");
        TableColumn<Product, String> description = new TableColumn<>("Ma'lumot");
        TableColumn<Product, String> color = new TableColumn<>("Rangi");
        tableA_ombor.getColumns().addAll(id, invoice, unit, barcode, name, type, cost, quantity, suplier, date, user,color, description);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        invoice.setCellValueFactory(new PropertyValueFactory<>("invoice"));
        barcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        user.setCellValueFactory(new PropertyValueFactory<>("cr_by"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_cr"));
        suplier.setCellValueFactory(new PropertyValueFactory<>("suplier_id"));
        unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));

    }
    // end of initializing ombor table

    //initializing table for 1-ish/ch
    private void initialize1ishchLogTable() {
        TableColumn id = new TableColumn("Id");
        TableColumn module = new TableColumn("Modul");
        TableColumn type = new TableColumn("Turi");
        TableColumn cost = new TableColumn("Narxi");
        TableColumn cr_by = new TableColumn("Ishchi");
        TableColumn date = new TableColumn("Sana");
        TableColumn comment = new TableColumn("Ma'lumot");
        tableMain1.getColumns().addAll(id, module, type, cost, cr_by, date, comment);

        id.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("id"));
        module.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("module"));
        type.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("type"));
        cost.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("cost"));
        cr_by.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("cr_by"));
        date.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("date"));
        comment.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("comment"));
    }

    // Initializing table for 2-ish/ch
    private void initialize2ischLogTable() {
        TableColumn id = new TableColumn("Id");
        TableColumn module = new TableColumn("Modul");
        TableColumn type = new TableColumn("Turi");
        TableColumn cost = new TableColumn("Narxi");
        TableColumn cr_by = new TableColumn("Ishchi");
        TableColumn date = new TableColumn("Sana");
        TableColumn comment = new TableColumn("Ma'lumot");
        tableMain2.getColumns().addAll(id, module, type, cost, cr_by, date, comment);

        id.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("id"));
        module.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("module"));
        type.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("type"));
        cost.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("cost"));
        cr_by.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("cr_by"));
        date.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("date"));
        comment.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("comment"));
    }

    // initializing table AdminLog
    private void intitializeAdminlogTable() {
        TableColumn id = new TableColumn("Id");
        TableColumn module = new TableColumn("Modul");
        TableColumn type = new TableColumn("Turi");
        TableColumn cost = new TableColumn("Narxi");
        TableColumn cr_by = new TableColumn("Ishchi");
        TableColumn date = new TableColumn("Sana");
        TableColumn comment = new TableColumn("Ma'lumot");
        tableAdminLog.getColumns().addAll(id, module, type, cost, cr_by, date, comment);

        id.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("id"));
        module.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("module"));
        type.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("type"));
        cost.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("cost"));
        cr_by.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("cr_by"));
        date.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("date"));
        comment.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("comment"));
    }

    // InitializeSaleOperTable
    private void initializeOperTable() {
        TableColumn<OperTable, String> id = new TableColumn<>("Tartib raqami");
        TableColumn<OperTable, String> type = new TableColumn<>("To'lov turi");
        TableColumn<OperTable, String> who = new TableColumn<>("Kimga/Kimdan");
        TableColumn<OperTable, String> sum = new TableColumn<>("Summa");
        TableColumn<OperTable, String> sum_eqv = new TableColumn<>("Ekvivalent summa");
        TableColumn<OperTable, String> description = new TableColumn<>("Ma'lumot");
        TableColumn<OperTable, String> cr_by = new TableColumn<>("Sotuvchi");
        TableColumn<OperTable, String> date = new TableColumn<>("Sana");
        TableColumn<OperTable, String> currency = new TableColumn<>("Valyuta");

        tableOperAdmin.getColumns().addAll(id, type, who, sum, sum_eqv, cr_by, date, currency, description);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        who.setCellValueFactory(new PropertyValueFactory<>("who"));
        sum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        sum_eqv.setCellValueFactory(new PropertyValueFactory<>("sum_eqv"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        cr_by.setCellValueFactory(new PropertyValueFactory<>("cr_by"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        currency.setCellValueFactory(new PropertyValueFactory<>("currency"));
    }

    //initialize admin history table
    private void initializeHistoryTable() {
        TableColumn<History, String> id = new TableColumn<>("id");
        TableColumn<History, String> barcode = new TableColumn<>("Barcode");
        TableColumn<History, String> p_id = new TableColumn<>("Maxsulot idsi");
        TableColumn<History, String> name = new TableColumn<>("Nomi");
        TableColumn<History, String> type = new TableColumn<>("Turi");
        TableColumn<History, String> quantity = new TableColumn<>("Miqdori");
        TableColumn<History, String> seller_id = new TableColumn<>("Sotuvchi");
        TableColumn<History, String> cost = new TableColumn<>("Narxi");
        TableColumn<History, String> date_cr = new TableColumn<>("Sana");
        TableColumn<History, String> cr_by = new TableColumn<>("Sotuvchi");
        TableColumn<History, String> customer_id = new TableColumn<>("Xaridor");
        TableColumn<History, String> sellAction_id = new TableColumn<>("SellActionId");


        tableSaleHistory.getColumns().addAll(id, barcode, p_id, name, type, quantity, seller_id, cost, date_cr, cr_by, customer_id, sellAction_id);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        barcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        p_id.setCellValueFactory(new PropertyValueFactory<>("p_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        seller_id.setCellValueFactory(new PropertyValueFactory<>("seller_id"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        date_cr.setCellValueFactory(new PropertyValueFactory<>("date_cr"));
        cr_by.setCellValueFactory(new PropertyValueFactory<>("cr_by"));
        customer_id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        sellAction_id.setCellValueFactory(new PropertyValueFactory<>("sellAction_id"));
    }


    // This function updates all the controls
    private void functionUpdate() {
        omborATable();
        AdminLogTable();
        main2Table();
        marketingTable();
        main1Table();
        LogTable();
        userTable();
        marketingTable();
        LogTable();
        main1Table();
        main2Table();
        AdminLogTable();
        SaleOperTable();
        SaleTarixTable();
        AddCompanyCombobox();
        colorCombobox();
    }

    @FXML
    private void btnChartSaralashAction() {
        chart();
    }

    /**
     * Getting chart
     */
    private void chart() {

        try {
            if (danChart.getValue() != null && gachaChart.getValue() != null) {
                LocalDate date1 = danChart.getValue();
                LocalDate date2 = gachaChart.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                adminDao.chart(sdate1, sdate2, chartAdminSale);
            } else {
                adminDao.chart("1", "1", chartAdminSale);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void omborATable() {
        try {
            adminDao.omborATable(tableA_ombor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void userTable() {
        try {
            adminDao.userTable(userTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void marketingTable() {
        try {

            String color = ColorFilter.getSelectionModel().getSelectedItem();
            if (color != null) {
                adminDao.marketingTable(MarketingTableM, color);
            } else {
                adminDao.marketingTable(MarketingTableM, "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LogTable() {

        try {
            if (danOmborLog.getValue() != null && gachaOmborLog.getValue() != null) {
                LocalDate date1 = danOmborLog.getValue();
                LocalDate date2 = gachaOmborLog.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                adminDao.productHistoryTable(tableOmborLog, sdate1, sdate2);
            } else {
                adminDao.productHistoryTable(tableOmborLog, "1", "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void main1Table() {

        try {
            if (danMain1.getValue() != null && gachaMain1.getValue() != null) {
                LocalDate date1 = danMain1.getValue();
                LocalDate date2 = gachaMain1.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                adminDao.main1Table(tableMain1, sdate1, sdate2);
            } else {
                adminDao.main1Table(tableMain1, "1", "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void main2Table() {
        try {
            if (danMain2.getValue() != null && gachaMain2.getValue() != null) {
                LocalDate date1 = danMain2.getValue();
                LocalDate date2 = gachaMain2.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                adminDao.main2Table(tableMain2, sdate1, sdate2);
            } else {
                adminDao.main2Table(tableMain2, "1", "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void AdminLogTable() {
        try {
            if (danAdmin.getValue() != null && gachaAdmin.getValue() != null) {
                LocalDate date1 = danAdmin.getValue();
                LocalDate date2 = gachaAdmin.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                adminDao.adminLogTable(tableAdminLog, sdate1, sdate2);
            } else {
                adminDao.adminLogTable(tableAdminLog, "1", "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SaleOperTable(){
        try {
            if (danOper.getValue() != null && gachaOper.getValue() != null) {
                LocalDate date1 = danOper.getValue();
                LocalDate date2 = gachaOper.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                adminDao.operTableFilter(tableOperAdmin, sdate1, sdate2);
            } else {
                adminDao.operTableFilter(tableOperAdmin, "1", "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SaleTarixTable() {
        try {
            if (danSaleHistory.getValue() != null && gachaSaleHistoty.getValue() != null) {
                LocalDate date1 = danSaleHistory.getValue();
                LocalDate date2 = gachaSaleHistoty.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                adminDao.HistoryTableFilter(tableSaleHistory, sdate1, sdate2);
            } else {
                adminDao.HistoryTableFilter(tableSaleHistory, "1", "1");
            }
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

    private void closeAction(Button btn) {
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

    private void AddCompanyCombobox() {
        try {
            ProductDao productDao = new ProductDao();
            productDao.addSuplierCombobox(CompanyNameM);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpdateMarketing() {
        try {
            MarketingTableM.setOnMouseClicked(event -> {
                Marketing marketing = (Marketing) MarketingTableM.getSelectionModel().getSelectedItem();
                try {
                    NameM.setText(marketing.getName());
                    Barcode_OM.setText(marketing.getBarcode_o());
                    BarcodeM.setText(marketing.getBarcode());
                    ColorM.setText(marketing.getColor());
                    CostM.setText(marketing.getCost());
                    idM.setText(marketing.getId());
                } catch (Exception exc) {
                }
            });
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Error" + exc, "Xatolik", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void btnSaveActionM() {
        try {
            String company = CompanyNameM.getSelectionModel().getSelectedItem();
            String name = NameM.getText();
            String barcode_o = Barcode_OM.getText();
            String barcode = BarcodeM.getText();
            String color = ColorM.getText();
            String cost = CostM.getText();
            adminDao.addToMarketing(company, name, barcode_o, barcode, color, cost);
            marketingTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnUpdateActionM() {
        try {
            String name = NameM.getText();
            String barcode_o = Barcode_OM.getText();
            String barcode = BarcodeM.getText();
            String color = ColorM.getText();
            String cost = CostM.getText();
            String id = idM.getText();
            adminDao.updateMarketing(name, barcode_o, barcode, color, cost, id);
            marketingTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnDeleteActionM() {
        try {
            String id = idM.getText();
            adminDao.deleteMarketing(id);
            marketingTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void colorCombobox() {
        try {
            adminDao.addColorCombobox(ColorFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnFilterAction() {
        marketingTable();

    }

    @FXML
    private void btnExcellFilter() {
        try {
            Workbookcontroller workbookcontroller = new Workbookcontroller();
            workbookcontroller.datebaseToExcel("Marketing_v", "Marketing.xls");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void total_Sum() {
        adminDao.total_balance(LabelSumTotal, LabelDollarTotal, LabelAccountTotal, LabelVHR_Total);
    }

    public void btnExchangeAction() throws SQLException {

        PreparedStatement pr = null;
        PreparedStatement pr1 = null;
        try {
            String oper = ExchangeCombobox.getSelectionModel().getSelectedItem();
            Double sum = Double.valueOf(ExchangeSumma.getText().trim().replaceAll("\\s+", ""));
            Double dollar = Double.valueOf(DollarRate.getText().trim().replaceAll("\\s+", ""));

            switch (oper) {
                case "Sum-Dollar": {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Dollardagi qiymati: " +sum / dollar);
                    alert.setHeaderText(null);
                    alert.setContentText("Valyuta almashtirasizmi ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent())
                        if (result.get() == ButtonType.OK) {

                            pr = myConn.prepareStatement("update total_balance set dollar=(dollar+?) where id =1");
                            pr.setString(1, String.valueOf((sum / dollar)));
                            pr.executeUpdate();
                            pr1 = myConn.prepareStatement("update total_balance set sum=(sum-?) where id =1 ");
                            pr1.setString(1, String.valueOf(sum));
                            pr1.executeUpdate();
                            total_Sum();
                            daoUtils.log("Admin1", "Valyuta almashtirish", "", user_id, "Sum-dollar: " + sum / dollar);

                        }
                    break;
                }
                case "HR-Dollar": {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Dollardagi qiymati: " + String.valueOf(sum / dollar));
                    alert.setHeaderText(null);
                    alert.setContentText("Valyuta almashtirasizmi ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent())
                        if (result.get() == ButtonType.OK) {

                            pr = myConn.prepareStatement("update total_balance set dollar=(dollar+?) where id =1 ");
                            pr.setDouble(1, (sum / dollar));
                            pr.executeUpdate();
                            pr1 = myConn.prepareStatement("update total_balance set hr=(hr-?) where id =1 ");
                            pr1.setString(1, String.valueOf(sum));
                            pr1.executeUpdate();
                            total_Sum();
                            daoUtils.log("Admin1", "Valyuta almashtirish", "", user_id, "HR-dollar: " + sum / dollar);


                        }
                    break;
                }
                case "Dollar-Sum": {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("So'mdagi qiymati: " + String.valueOf(dollar * sum));
                    alert.setHeaderText(null);
                    alert.setContentText("Valyuta almashtirasizmi ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent())
                        if (result.get() == ButtonType.OK) {

                            pr = myConn.prepareStatement("update total_balance set sum=(sum+?) where id =1 ");
                            pr.setString(1, String.valueOf((dollar * sum)));
                            pr.executeUpdate();
                            pr1 = myConn.prepareStatement("update total_balance set dollar=(dollar-?) where id =1 ");
                            pr1.setString(1, String.valueOf(sum));
                            pr1.executeUpdate();
                            total_Sum();
                            daoUtils.log("Admin1", "Valyuta almashtirish", "", user_id, "Dollar-Sum: " + dollar * sum);


                        }
                    break;
                }case "Sum-Hr": {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("So'mdagi qiymati: " + String.valueOf(dollar * sum));
                    alert.setHeaderText(null);
                    alert.setContentText("Valyuta almashtirasizmi ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent())
                        if (result.get() == ButtonType.OK) {

                            pr = myConn.prepareStatement("update total_balance set sum=(sum-?) where id =1 ");
                            pr.setString(1, String.valueOf(sum));
                            pr.executeUpdate();
                            pr1 = myConn.prepareStatement("update total_balance set hr=(hr+?) where id =1 ");
                            pr1.setString(1, String.valueOf(sum));
                            pr1.executeUpdate();
                            total_Sum();
                            daoUtils.log("Admin1", "Valyuta almashtirish", "", user_id, "Sum-Hr: " + dollar * sum);


                        }
                    break;
                }case "Hr-Sum": {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("So'mdagi qiymati: " + String.valueOf(dollar * sum));
                    alert.setHeaderText(null);
                    alert.setContentText("Valyuta almashtirasizmi ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent())
                        if (result.get() == ButtonType.OK) {

                            pr = myConn.prepareStatement("update total_balance set sum=(sum+?) where id =1 ");
                            pr.setString(1, String.valueOf( sum));
                            pr.executeUpdate();
                            pr1 = myConn.prepareStatement("update total_balance set hr=(hr-?) where id =1 ");
                            pr1.setString(1, String.valueOf(sum));
                            pr1.executeUpdate();
                            total_Sum();
                            daoUtils.log("Admin1", "Valyuta almashtirish", "", user_id, "Hr-Sum: " + dollar * sum);


                        }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                pr.close();
            }
            if (pr1 != null) {
                pr1.close();
            }
        }
    }

    @FXML
    private void btnA_ombor_excellAction() {
        try {
            workbookcontroller.datebaseToExcel("product_v", "Ombor.xls");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnSaralashOmborLog() {
        LogTable();
    }

    @FXML
    private void btnMain1SaralashAction() {
        main1Table();
    }

    @FXML
    private void btnMain2Saralash() {
        main2Table();
    }

    @FXML
    private void btnAdminSaralashAction() {
        AdminLogTable();
    }

    @FXML
    private void btnOperAdminSaralashAction(){
       SaleOperTable();
    }

    @FXML private void btnSaleHistoryFilterAction(){
        SaleTarixTable();
    }

    @FXML
    private void btnPrinterNameAction(){
        try {

            UtilsDao utilsDao = new UtilsDao();
            TextInputDialog dialog = new TextInputDialog("Printer");
            dialog.setTitle("Printer nomini qo'shish");
            dialog.setHeaderText("Printer nomi");
            dialog.setContentText("Iltimos printer nomini qo'shing");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> {
                try {
                    utilsDao.setPrinterName(name);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void btnExcellBalanceAction() {
        try {
            FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
            dialog.setMode(FileDialog.LOAD);
            dialog.setVisible(true);
            String file1 = dialog.getFile();
            FileInputStream file = new FileInputStream(new File(dialog.getDirectory() + file1));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through each columns
                Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    //This will change all Cell Types to String
                    cell.setCellType(CellType.STRING);
                    switch (cell.getCellType()) {
                        case BOOLEAN:
                            System.out.println("boolean===>>>" + cell.getBooleanCellValue() + "\t");
                            break;
                        case NUMERIC:
                            break;
                        case STRING:
                            cell.getStringCellValue();
                            break;
                    }
                }
                String who = row.getCell(0).getStringCellValue();
                String sum_in = row.getCell(1).getStringCellValue();
                String sum_out = row.getCell(2).getStringCellValue();
                String dollar_in = row.getCell(3).getStringCellValue();
                String dollar_out = row.getCell(4).getStringCellValue();
                String hr_in = row.getCell(5).getStringCellValue();
                String hr_out = row.getCell(6).getStringCellValue();
                adminDao.InsertRowInDB(who, sum_in, sum_out, dollar_in, dollar_out, hr_in, hr_out);
            }
            file.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void AdminOperationAction() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/AdminOper.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Admin operatsiyalari");
            stage.setScene(new Scene(root, 1000, 700));
            stage.setResizable(false);
            stage.isAlwaysOnTop();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnExchangeActionHistory() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/Exchange.fxml"));
            Stage stage = new Stage();
            stage.setTitle("O'tkazmalar");
            stage.setScene(new Scene(root, 1000, 700));
            stage.setResizable(false);
            stage.isAlwaysOnTop();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnPaperActionHistory() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/p2Tarix.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Qog'oz sex ishlab chiqarish tarixi");
            stage.setScene(new Scene(root, 1000, 700));
            stage.setResizable(false);
            stage.isAlwaysOnTop();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnLaminantActionHistory() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/p3Tarix.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Laminant sex ishlab chiqarish tarixi");
            stage.setScene(new Scene(root, 1000, 700));
            stage.setResizable(false);
            stage.isAlwaysOnTop();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnTotalBalance() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/balance.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Umumiy balance oynasi");
            stage.setScene(new Scene(root, 700, 500));
            stage.setResizable(false);
            stage.isAlwaysOnTop();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
