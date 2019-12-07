package sample.components.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.components.dao.p2_xarajatDao;
import sample.model.TableA;
import sample.utils.ComboBoxAutoComplete;
import sample.utils.Workbookcontroller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class p2_xarajat implements Initializable {
    @FXML
    public Button btnExcell;
    @FXML private TableView p2_xTable;
    @FXML private ComboBox<String> p2XarajatSelectName;
    @FXML private DatePicker dan;
    @FXML private DatePicker gacha;
    @FXML private Label LabelTotal_quantity;

    p2_xarajatDao p2_xarajatDao = new p2_xarajatDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableA();
        p2XarajatSelectName.getItems().addAll("");
        p2XarajatSelectName.getSelectionModel().selectFirst();
        getXarajatSelectName();

        p2XarajatSelectName.setTooltip(new Tooltip());
        new ComboBoxAutoComplete<String>(p2XarajatSelectName);

        TableColumn id = new TableColumn("ID");
        TableColumn barcode = new TableColumn("Barcode");
        TableColumn name = new TableColumn("Nomi");
        TableColumn type = new TableColumn("Turi");
        TableColumn quantity = new TableColumn("Miqdori");
        TableColumn cr_on = new TableColumn("Sana");
        TableColumn cr_by = new TableColumn("Ishchi");

        p2_xTable.getColumns().addAll(id, cr_on, barcode, name, type, quantity, cr_by);

        id.setCellValueFactory(new PropertyValueFactory<TableA, String>("id"));
        barcode.setCellValueFactory(new PropertyValueFactory<TableA, String>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<TableA, String>("name"));
        type.setCellValueFactory(new PropertyValueFactory<TableA, String>("type"));
        quantity.setCellValueFactory(new PropertyValueFactory<TableA, String>("quantity"));
        cr_on.setCellValueFactory(new PropertyValueFactory<TableA, String>("cr_on"));
        cr_by.setCellValueFactory(new PropertyValueFactory<TableA, String>("cr_by"));
    }

    private void tableA() {
        try {
            p2_xarajatDao.tableA(p2_xTable, "1", "1", "1", LabelTotal_quantity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnExcellp2X(){
        try{
            Workbookcontroller workbookcontroller = new Workbookcontroller();
            workbookcontroller.datebaseToExcel("production2_xarajat", "Qog'oz_xarajatlari.xls");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML private void btnXarajatAction() {
        try {
            if (dan.getValue() != null && gacha.getValue() != null && p2XarajatSelectName.getSelectionModel().getSelectedItem() != null) {

                String name = p2XarajatSelectName.getSelectionModel().getSelectedItem();
                LocalDate date1 = dan.getValue();
                LocalDate date2 = gacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                p2_xarajatDao.tableA(p2_xTable, name, sdate1, sdate2, LabelTotal_quantity);

            } else if (dan.getValue() != null && gacha.getValue() != null && p2XarajatSelectName.getSelectionModel().getSelectedItem() == null) {

                LocalDate date1 = dan.getValue();
                LocalDate date2 = gacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                p2_xarajatDao.tableA(p2_xTable, "", sdate1, sdate2,  LabelTotal_quantity);
            } else if (p2XarajatSelectName.getSelectionModel().getSelectedItem() != null && dan.getValue() == null && gacha.getValue() == null) {

                p2_xarajatDao.tableA(p2_xTable, p2XarajatSelectName.getSelectionModel().getSelectedItem(), "", "", LabelTotal_quantity);

            } else if (p2XarajatSelectName.getSelectionModel().getSelectedItem() == null && dan.getValue() == null && gacha.getValue() == null) {
                p2_xarajatDao.tableA(p2_xTable, "1", "1", "1", LabelTotal_quantity);
            } else {
                p2_xarajatDao.tableA(p2_xTable, "1", "1", "1", LabelTotal_quantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getXarajatSelectName() {
        try {
            p2_xarajatDao.getP2XarajatSelectName(p2XarajatSelectName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
