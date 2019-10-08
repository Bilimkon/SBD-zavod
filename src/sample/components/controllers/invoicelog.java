package sample.components.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.components.dao.invoicelogDao;
import sample.model.Product;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class invoicelog implements Initializable {

    @FXML
    private ComboBox<String> invoiceLogSelectType;
    @FXML
    private DatePicker invoiceLogDan;
    @FXML
    private DatePicker invoiceLogGacha;
    @FXML
    private TableView tableInvoiceLog;
    @FXML
    private Label invoiceLogQuantity;
    invoicelogDao invoicelogDao = new invoicelogDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     initializeTable();
     invoiceLogTable();
     invoiceLogSelectType.getItems().addAll("");
     getInvoiceProductType();
    }

    private void initializeTable(){
        TableColumn<Product, String> id = new TableColumn<>("Id");
        TableColumn<Product, String> operType = new TableColumn<>("Qabul turi");
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

        tableInvoiceLog.getColumns().addAll(id, operType, invoice, unit, barcode, name, type, cost, quantity, suplier, date, user, color, description);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        operType.setCellValueFactory(new PropertyValueFactory<>("operType"));
        invoice.setCellValueFactory(new PropertyValueFactory<>("invoice"));
        barcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        user.setCellValueFactory(new PropertyValueFactory<>("cr_by"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_cr"));
        suplier.setCellValueFactory(new PropertyValueFactory<>("suplier"));
        unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));
    }

    private void invoiceLogTable(){
        try {
            if (invoiceLogDan.getValue() != null && invoiceLogGacha.getValue() != null && invoiceLogSelectType.getSelectionModel().getSelectedItem() != null) {

                String name = invoiceLogSelectType.getSelectionModel().getSelectedItem();
                LocalDate date1 = invoiceLogDan.getValue();
                LocalDate date2 = invoiceLogGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                invoicelogDao.ReportTableDao(tableInvoiceLog, name, sdate1, sdate2, invoiceLogQuantity);

            } else if (invoiceLogDan.getValue() != null && invoiceLogGacha.getValue() != null && invoiceLogSelectType.getSelectionModel().getSelectedItem() == null) {

                LocalDate date1 = invoiceLogDan.getValue();
                LocalDate date2 = invoiceLogGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                invoicelogDao.ReportTableDao(tableInvoiceLog, "", sdate1, sdate2, invoiceLogQuantity);
            } else if (invoiceLogSelectType.getSelectionModel().getSelectedItem() != null && invoiceLogDan.getValue() == null && invoiceLogGacha.getValue() == null) {

                invoicelogDao.ReportTableDao(tableInvoiceLog, invoiceLogSelectType.getSelectionModel().getSelectedItem(), "", "", invoiceLogQuantity);

            } else if (invoiceLogSelectType.getSelectionModel().getSelectedItem() == null && invoiceLogDan.getValue() == null && invoiceLogGacha.getValue() == null) {
                invoicelogDao.ReportTableDao(tableInvoiceLog, "1", "1", "1", invoiceLogQuantity);
            } else {
                invoicelogDao.ReportTableDao(tableInvoiceLog, "1", "1", "1", invoiceLogQuantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void invoiceLogBtnSaralash(){
        invoiceLogTable();
    }

    private void getInvoiceProductType() {
        try {
            invoicelogDao.ReportSelectName(invoiceLogSelectType);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
