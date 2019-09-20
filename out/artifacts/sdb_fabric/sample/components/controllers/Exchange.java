package sample.components.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.components.dao.ExchangeDao;
import sample.utils.ComboBoxAutoComplete;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class Exchange implements Initializable {

    @FXML
    DatePicker dan;
    @FXML
    DatePicker gacha;
    @FXML
    TableView exchangeTable;
    @FXML
    private Label LabelQuantity;
    @FXML private ComboBox<String> p2ExchangeSelectName;

    ExchangeDao exchangeDao = new ExchangeDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
        try {
            p2ExchangeSelectName.getItems().addAll("");
            p2ExchangeSelectName.getSelectionModel().selectFirst();
            table();
            getSelectName();
            p2ExchangeSelectName.setTooltip(new Tooltip());
            new ComboBoxAutoComplete<String>(p2ExchangeSelectName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializeTable() {
        TableColumn id = new TableColumn("Id");
        TableColumn name = new TableColumn("Nomi");
        TableColumn barcode = new TableColumn("Barcode");
        TableColumn type = new TableColumn("Turi");
        TableColumn quantity = new TableColumn("Miqdori");
        TableColumn comment = new TableColumn("Izox");
        TableColumn cr_on = new TableColumn("Sana");
        TableColumn cr_by = new TableColumn("Ishchi");
        exchangeTable.getColumns().addAll(id, cr_on, name, barcode, type, quantity, comment, cr_by);

        id.setCellValueFactory(new PropertyValueFactory<Exchange, String>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Exchange, String>("name"));
        barcode.setCellValueFactory(new PropertyValueFactory<Exchange, String>("barcode"));
        type.setCellValueFactory(new PropertyValueFactory<Exchange, String>("type"));
        quantity.setCellValueFactory(new PropertyValueFactory<Exchange, String>("quantity"));
        comment.setCellValueFactory(new PropertyValueFactory<Exchange, String>("comment"));
        cr_on.setCellValueFactory(new PropertyValueFactory<Exchange, String>("cr_on"));
        cr_by.setCellValueFactory(new PropertyValueFactory<Exchange, String>("cr_by"));
    }

    @FXML
    private void btnExchangeAction() {
        btnFilter();
    }

    public void table() throws SQLException {
        exchangeDao.exchangeTableDao(exchangeTable, "1", "1","1", LabelQuantity);
    }

    private void btnFilter() {


        try {
            if (dan.getValue() != null && gacha.getValue() != null && p2ExchangeSelectName.getSelectionModel().getSelectedItem() != null) {

                String name = p2ExchangeSelectName.getSelectionModel().getSelectedItem();
                LocalDate date1 = dan.getValue();
                LocalDate date2 = gacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                exchangeDao.exchangeTableDao(exchangeTable, name, sdate1, sdate2, LabelQuantity);

            } else if (dan.getValue() != null && gacha.getValue() != null && p2ExchangeSelectName.getSelectionModel().getSelectedItem() == null) {

                LocalDate date1 = dan.getValue();
                LocalDate date2 = gacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                exchangeDao.exchangeTableDao(exchangeTable, "", sdate1, sdate2,  LabelQuantity);
            } else if (p2ExchangeSelectName.getSelectionModel().getSelectedItem() != null && dan.getValue() == null && gacha.getValue() == null) {

                exchangeDao.exchangeTableDao(exchangeTable, p2ExchangeSelectName.getSelectionModel().getSelectedItem(), "", "", LabelQuantity);

            } else if (p2ExchangeSelectName.getSelectionModel().getSelectedItem() == null && dan.getValue() == null && gacha.getValue() == null) {
                exchangeDao.exchangeTableDao(exchangeTable, "1", "1", "1",  LabelQuantity);
            } else {
                exchangeDao.exchangeTableDao(exchangeTable, "1", "1", "1", LabelQuantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getSelectName() {
        try {
            exchangeDao.getP2ExchangeSelectName(p2ExchangeSelectName);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
