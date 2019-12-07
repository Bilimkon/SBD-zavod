package sample.components.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.components.dao.p2TarixDao;
import sample.model.TableB;
import sample.utils.ComboBoxAutoComplete;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class p2Tarix implements Initializable {


    @FXML
    private DatePicker dan;
    @FXML
    private DatePicker gacha;
    @FXML
    private TableView P3TarixTable;
    @FXML
    private Label in;
    @FXML
    private Label out;
    @FXML
    private ComboBox<String> p2SelectName;


    sample.components.dao.p2TarixDao p2TarixDao = new p2TarixDao();

    private void initializeTableB() {
        TableColumn id = new TableColumn("ID");
        TableColumn date = new TableColumn("Sana");
        TableColumn barcode = new TableColumn("Barcode");
        TableColumn name = new TableColumn("Nomi");
        TableColumn quantity = new TableColumn("Miqdori");
        TableColumn color = new TableColumn("Rangi");
        TableColumn p_quantity = new TableColumn("Tayyor miqdori");
        TableColumn p_name = new TableColumn("Xarajat nomi");
        TableColumn p_barcode = new TableColumn("Xarajat barcodi");
        TableColumn user = new TableColumn("Hodim");

        P3TarixTable.getColumns().addAll(id, date, barcode, name, color, p_quantity, p_name, p_barcode, quantity, user);

        id.setCellValueFactory(new PropertyValueFactory<TableB, String>("id"));
        date.setCellValueFactory(new PropertyValueFactory<TableB, String>("date"));
        barcode.setCellValueFactory(new PropertyValueFactory<TableB, String>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<TableB, String>("name"));
        color.setCellValueFactory(new PropertyValueFactory<TableB, String>("color"));
        quantity.setCellValueFactory(new PropertyValueFactory<TableB, String>("quantity"));
        p_quantity.setCellValueFactory(new PropertyValueFactory<TableB, String>("p_quantity"));
        p_name.setCellValueFactory(new PropertyValueFactory<TableB, String>("p_name"));
        p_barcode.setCellValueFactory(new PropertyValueFactory<TableB, String>("p_barcode"));
        user.setCellValueFactory(new PropertyValueFactory<TableB, String>("cr_by"));
    }

    public void table() throws SQLException {
        p2TarixDao.tableB(P3TarixTable, "1", "1", "1", in, out);
    }

    public void btnFilter(ActionEvent actionEvent) throws SQLException {

        try {
            if (dan.getValue() != null && gacha.getValue() != null && p2SelectName.getSelectionModel().getSelectedItem() != null) {

                String name = p2SelectName.getSelectionModel().getSelectedItem();
                LocalDate date1 = dan.getValue();
                LocalDate date2 = gacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                p2TarixDao.tableB(P3TarixTable, name, sdate1, sdate2, in, out);

            } else if (dan.getValue() != null && gacha.getValue() != null && p2SelectName.getSelectionModel().getSelectedItem() == null) {

                LocalDate date1 = dan.getValue();
                LocalDate date2 = gacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                p2TarixDao.tableB(P3TarixTable, "", sdate1, sdate2,  in, out);
            } else if (p2SelectName.getSelectionModel().getSelectedItem() != null && dan.getValue() == null && gacha.getValue() == null) {

                p2TarixDao.tableB(P3TarixTable, p2SelectName.getSelectionModel().getSelectedItem(), "", "",  in, out);

            } else if (p2SelectName.getSelectionModel().getSelectedItem() == null && dan.getValue() == null && gacha.getValue() == null) {
                p2TarixDao.tableB(P3TarixTable, "1", "1", "1",  in, out);
            } else {
                p2TarixDao.tableB(P3TarixTable, "1", "1", "1", in, out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            getSelectName();
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableB();
        p2SelectName.getItems().addAll("");
        p2SelectName.getSelectionModel().selectFirst();
        getSelectName();

        p2SelectName.setTooltip(new Tooltip());
        new ComboBoxAutoComplete<String>(p2SelectName);
        try {
            table();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getSelectName() {
        try {
            p2TarixDao.getP2TarixSelectName(p2SelectName);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnExcellAction(ActionEvent actionEvent) {
    }
}
