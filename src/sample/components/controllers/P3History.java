package sample.components.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.components.dao.P3HistoryDao;
import sample.model.Production3;
import sample.utils.ComboBoxAutoComplete;
import sample.utils.Workbookcontroller;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class P3History implements Initializable {

    @FXML
    private DatePicker dan;
    @FXML
    private DatePicker gacha;
    @FXML
    private TableView P3TarixTable;
    @FXML
    private Label inn;
    @FXML
    private Label outt;
    @FXML
    private Label outt2;
    @FXML private ComboBox<String> p3TarixSelectName;

    private P3HistoryDao p3HistoryDao = new P3HistoryDao();

    private void initializeProduction3() {
        TableColumn id = new TableColumn("id");
        TableColumn barcode = new TableColumn("Barcode");
        TableColumn name = new TableColumn("Nomi");
        TableColumn type = new TableColumn("Turi");
        TableColumn color = new TableColumn("Rangi");
        TableColumn quantity = new TableColumn("Miqdori");
        TableColumn pBarcode = new TableColumn("Qog'oz barcodi");
        TableColumn pName = new TableColumn("Q nomi");
        TableColumn pCost = new TableColumn("Q Narxi");
        TableColumn pQuantity = new TableColumn("Q miqdori");
        TableColumn pColor = new TableColumn("Q rangi");
        TableColumn dBarcode = new TableColumn("DSP/MDF barcode");
        TableColumn dName = new TableColumn("Nomi");
        TableColumn dQuantity = new TableColumn("D Miqdori");
        TableColumn cr_on = new TableColumn("Sana");
        TableColumn cr_by = new TableColumn("Ishchi");

        P3TarixTable.getColumns().addAll(id, barcode, name, quantity, pBarcode, pName, pCost, pQuantity, pColor, dBarcode, dName, dQuantity, cr_on, cr_by);

        id.setCellValueFactory(new PropertyValueFactory<Production3, String>("id"));
        barcode.setCellValueFactory(new PropertyValueFactory<Production3, String>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<Production3, String>("name"));
        type.setCellValueFactory(new PropertyValueFactory<Production3, String>("type"));
        color.setCellValueFactory(new PropertyValueFactory<Production3, String>("color"));
        quantity.setCellValueFactory(new PropertyValueFactory<Production3, String>("quantity"));
        pBarcode.setCellValueFactory(new PropertyValueFactory<Production3, String>("pBarcode"));
        pName.setCellValueFactory(new PropertyValueFactory<Production3, String>("pName"));
        pCost.setCellValueFactory(new PropertyValueFactory<Production3, String>("pCost"));
        pQuantity.setCellValueFactory(new PropertyValueFactory<Production3, String>("pQuantity"));
        pColor.setCellValueFactory(new PropertyValueFactory<Production3, String>("pColor"));
        dBarcode.setCellValueFactory(new PropertyValueFactory<Production3, String>("dBarcode"));
        dName.setCellValueFactory(new PropertyValueFactory<Production3, String>("dName"));
        dQuantity.setCellValueFactory(new PropertyValueFactory<Production3, String>("dQuantity"));
        cr_on.setCellValueFactory(new PropertyValueFactory<Production3, String>("cr_on"));
        cr_by.setCellValueFactory(new PropertyValueFactory<Production3, String>("cr_by"));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    initializeProduction3();
        try {
            tableHistory();
            p3TarixSelectName.getItems().addAll("");
            p3TarixSelectName.getSelectionModel().selectFirst();
            p3TarixSelectName.setTooltip(new Tooltip());
            new ComboBoxAutoComplete<String>(p3TarixSelectName);
            getTarixSelectName();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnExcell(ActionEvent actionEvent) {
        Workbookcontroller workbookcontroller = new Workbookcontroller();
        try {
            workbookcontroller.datebaseToExcel("production3", "2-ishlab-chiqarish.xls");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnFilter(ActionEvent actionEvent) {

        try {
            if (dan.getValue() != null && gacha.getValue() != null && p3TarixSelectName.getSelectionModel().getSelectedItem() != null) {

                String name = p3TarixSelectName.getSelectionModel().getSelectedItem();
                LocalDate date1 = dan.getValue();
                LocalDate date2 = gacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                p3HistoryDao.tableProduction(P3TarixTable, name, sdate1, sdate2, inn, outt, outt2);

            } else if (dan.getValue() != null && gacha.getValue() != null && p3TarixSelectName.getSelectionModel().getSelectedItem() == null) {

                LocalDate date1 = dan.getValue();
                LocalDate date2 = gacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));

                p3HistoryDao.tableProduction(P3TarixTable, "", sdate1, sdate2, inn, outt, outt2);
            } else if (p3TarixSelectName.getSelectionModel().getSelectedItem() != null && dan.getValue() == null && gacha.getValue() == null) {
                p3HistoryDao.tableProduction(P3TarixTable, p3TarixSelectName.getSelectionModel().getSelectedItem(), "", "",inn, outt, outt2);
            } else if (p3TarixSelectName.getSelectionModel().getSelectedItem() == null && dan.getValue() == null && gacha.getValue() == null) {
                p3HistoryDao.tableProduction(P3TarixTable, "1", "1", "1", inn, outt, outt2);
            } else {
                p3HistoryDao.tableProduction(P3TarixTable, "1", "1", "1",inn, outt, outt2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            getTarixSelectName();
        }
    }

    private void tableHistory() throws SQLException {
        p3HistoryDao.tableProduction(P3TarixTable, "1","1", "1", inn, outt, outt2);
    }

    private void getTarixSelectName() {
        try {
            p3HistoryDao.getP3TarixSelectName(p3TarixSelectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
