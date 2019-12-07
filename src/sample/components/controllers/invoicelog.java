package sample.components.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.components.dao.invoicelogDao;
import sample.components.models.omborReport;
import sample.model.Product;
import sample.utils.ComboBoxAutoComplete;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class invoicelog implements Initializable {

    @FXML public Button btnExcell12;
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
    @FXML
    private Label totalCost;
    @FXML
    private ComboBox<String> companyName;
    @FXML
    private ComboBox<String> reportCompanyName;
    @FXML
    private DatePicker reportDan;
    @FXML
    private DatePicker reportGacha;
    @FXML private TableView reportTable;
    @FXML private Label ReportTotalDollar;
    @FXML private Label ReportTotalSum;
    @FXML private Label ReportTotalHr;
    @FXML private Button btnExcell;

    invoicelogDao invoicelogDao = new invoicelogDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     initializeTable();
     invoiceLogTable();
     invoiceLogSelectType.getItems().addAll("");
     companyName.getItems().addAll("");
     reportCompanyName.getItems().addAll("");
     getInvoiceProductType();
     getCompanyName();
        getReportCompanyName();
        invoiceLogSelectType.setTooltip(new Tooltip());
        companyName.setTooltip(new Tooltip());
        new ComboBoxAutoComplete<String>(invoiceLogSelectType);
        new ComboBoxAutoComplete<String>(companyName);
        reportCompanyName.setTooltip(new Tooltip());
        new ComboBoxAutoComplete<String>(reportCompanyName);
        initializeReportTable();
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

    private void initializeReportTable(){
        TableColumn<omborReport, String> id = new TableColumn<>("Id");
        TableColumn<omborReport, String> type = new TableColumn<>("Turi");
        TableColumn<omborReport, String> invoiceName = new TableColumn<>("Invoice");
        TableColumn<omborReport, String> company = new TableColumn<>("Firma");
        TableColumn<omborReport, String> account = new TableColumn<>("Hisob raqam");
        TableColumn<omborReport, String> contract = new TableColumn<>("Shartnoma");
        TableColumn<omborReport, String> dollar = new TableColumn<>("Dollar");
        TableColumn<omborReport, String> sum = new TableColumn<>("So'm");
        TableColumn<omborReport, String> hr = new TableColumn<>("Hr");
        TableColumn<omborReport, String> maxsulot = new TableColumn<>("Maxsulot");
        TableColumn<omborReport, String> izoh = new TableColumn<>("Izoh");
        TableColumn<omborReport, String> cr_on = new TableColumn<>("Sana");
        TableColumn<omborReport, String> cr_by = new TableColumn<>("Ishchi");
        TableColumn<omborReport, String> i_id = new TableColumn<>("Invoice Id");


        reportTable.getColumns().addAll(type, invoiceName, company, account, contract, sum, dollar, hr, maxsulot, izoh, cr_on);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        invoiceName.setCellValueFactory(new PropertyValueFactory<>("invoiceName"));
        company.setCellValueFactory(new PropertyValueFactory<>("company"));
        account.setCellValueFactory(new PropertyValueFactory<>("account"));
        contract.setCellValueFactory(new PropertyValueFactory<>("contract"));
        dollar.setCellValueFactory(new PropertyValueFactory<>("dollar"));
        sum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        hr.setCellValueFactory(new PropertyValueFactory<>("hr"));
        maxsulot.setCellValueFactory(new PropertyValueFactory<>("maxsulot"));
        izoh.setCellValueFactory(new PropertyValueFactory<>("izoh"));
        cr_on.setCellValueFactory(new PropertyValueFactory<>("cr_on"));
        cr_by.setCellValueFactory(new PropertyValueFactory<>("cr_by"));
        i_id.setCellValueFactory(new PropertyValueFactory<>("i_id"));
    }

    private void invoiceLogTable(){
        try {
            if (invoiceLogDan.getValue() != null && invoiceLogGacha.getValue() != null && invoiceLogSelectType.getSelectionModel().getSelectedItem() != null && companyName.getSelectionModel().getSelectedItem() != null) {

                String name = invoiceLogSelectType.getSelectionModel().getSelectedItem();
                String company = companyName.getSelectionModel().getSelectedItem();
                LocalDate date1 = invoiceLogDan.getValue();
                LocalDate date2 = invoiceLogGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                invoicelogDao.ReportTableDao(tableInvoiceLog, company, name, sdate1, sdate2, invoiceLogQuantity, totalCost);

            } else if (invoiceLogDan.getValue() != null && invoiceLogGacha.getValue() != null && companyName.getSelectionModel().getSelectedItem() ==null && invoiceLogSelectType.getSelectionModel().getSelectedItem() == null) {

                LocalDate date1 = invoiceLogDan.getValue();
                LocalDate date2 = invoiceLogGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                invoicelogDao.ReportTableDao(tableInvoiceLog, "", "", sdate1, sdate2, invoiceLogQuantity, totalCost);
            } else if (invoiceLogSelectType.getSelectionModel().getSelectedItem() != null && companyName.getSelectionModel().getSelectedItem() == null && invoiceLogDan.getValue() == null && invoiceLogGacha.getValue() == null) {

                invoicelogDao.ReportTableDao(tableInvoiceLog,"", invoiceLogSelectType.getSelectionModel().getSelectedItem(), "", "", invoiceLogQuantity, totalCost);

            } else if (invoiceLogSelectType.getSelectionModel().getSelectedItem() == null && companyName.getSelectionModel().getSelectedItem() != null && invoiceLogDan.getValue() == null && invoiceLogGacha.getValue() == null) {

                invoicelogDao.ReportTableDao(tableInvoiceLog, companyName.getSelectionModel().getSelectedItem(), "", "", "", invoiceLogQuantity, totalCost);

            }
             else {
                invoicelogDao.ReportTableDao(tableInvoiceLog, "1", "1", "1", "1", invoiceLogQuantity, totalCost);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void btnExcellAction(){
        try {
            if (invoiceLogDan.getValue() != null && invoiceLogGacha.getValue() != null && invoiceLogSelectType.getSelectionModel().getSelectedItem() != null && companyName.getSelectionModel().getSelectedItem() != null) {

                String name = invoiceLogSelectType.getSelectionModel().getSelectedItem();
                String company = companyName.getSelectionModel().getSelectedItem();
                LocalDate date1 = invoiceLogDan.getValue();
                LocalDate date2 = invoiceLogGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                invoicelogDao.ReportExcellTableDao( btnExcell, company, name, sdate1, sdate2);
            } else if (invoiceLogDan.getValue() != null && invoiceLogGacha.getValue() != null && companyName.getSelectionModel().getSelectedItem() ==null && invoiceLogSelectType.getSelectionModel().getSelectedItem() == null) {
                LocalDate date1 = invoiceLogDan.getValue();
                LocalDate date2 = invoiceLogGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                invoicelogDao.ReportExcellTableDao( btnExcell,"", "", sdate1, sdate2);
            } else if (invoiceLogSelectType.getSelectionModel().getSelectedItem() != null && companyName.getSelectionModel().getSelectedItem() == null && invoiceLogDan.getValue() == null && invoiceLogGacha.getValue() == null) {
                invoicelogDao.ReportExcellTableDao(btnExcell,"", invoiceLogSelectType.getSelectionModel().getSelectedItem(), "", "");
            } else if (invoiceLogSelectType.getSelectionModel().getSelectedItem() == null && companyName.getSelectionModel().getSelectedItem() != null && invoiceLogDan.getValue() == null && invoiceLogGacha.getValue() == null) {
                invoicelogDao.ReportExcellTableDao( btnExcell,companyName.getSelectionModel().getSelectedItem(), "", "", "");
            }
            else {
                invoicelogDao.ReportExcellTableDao( btnExcell,"1", "1", "1", "1");
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
    private void getCompanyName() {
        try {
            invoicelogDao.getCompanyNameDao(companyName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void getReportCompanyName() {
        try {
            invoicelogDao.getCompanyNameDao(reportCompanyName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML private void reportSaralash(){
        try {
            if (reportDan.getValue() != null && reportGacha.getValue() != null && reportCompanyName.getSelectionModel().getSelectedItem() != null) {
                String name = reportCompanyName.getSelectionModel().getSelectedItem();
                LocalDate date1 = reportDan.getValue();
                LocalDate date2 = reportGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                invoicelogDao.invertoryReport(reportTable, name, sdate1, sdate2, ReportTotalDollar, ReportTotalSum, ReportTotalHr);
            } else if (reportDan.getValue() != null && reportGacha.getValue() != null && reportCompanyName.getSelectionModel().getSelectedItem() == null) {
                LocalDate date1 = reportDan.getValue();
                LocalDate date2 = reportGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                invoicelogDao.invertoryReport(reportTable, "", sdate1, sdate2, ReportTotalDollar, ReportTotalSum, ReportTotalHr);
            } else if (reportCompanyName.getSelectionModel().getSelectedItem() != null && reportDan.getValue() == null && reportGacha.getValue() == null) {
                invoicelogDao.invertoryReport(reportTable, reportCompanyName.getSelectionModel().getSelectedItem(), "", "", ReportTotalDollar, ReportTotalSum, ReportTotalHr);
            } else if (reportCompanyName.getSelectionModel().getSelectedItem() == null && reportDan.getValue() == null && reportGacha.getValue() == null) {
                invoicelogDao.invertoryReport(reportTable, "1", "1", "1", ReportTotalDollar, ReportTotalSum, ReportTotalHr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void reportExcellAction(){
        try {
            if (reportDan.getValue() != null && reportGacha.getValue() != null && reportCompanyName.getSelectionModel().getSelectedItem() != null) {
                String name = reportCompanyName.getSelectionModel().getSelectedItem();
                LocalDate date1 = reportDan.getValue();
                LocalDate date2 = reportGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                invoicelogDao.invertoryExcellReport(btnExcell, name, sdate1, sdate2);
            } else if (reportDan.getValue() != null && reportGacha.getValue() != null && reportCompanyName.getSelectionModel().getSelectedItem() == null) {
                LocalDate date1 = reportDan.getValue();
                LocalDate date2 = reportGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                invoicelogDao.invertoryExcellReport( btnExcell,"", sdate1, sdate2);
            } else if (reportCompanyName.getSelectionModel().getSelectedItem() != null && reportDan.getValue() == null && reportGacha.getValue() == null) {
                invoicelogDao.invertoryExcellReport(btnExcell, reportCompanyName.getSelectionModel().getSelectedItem(), "", "");
            } else if (reportCompanyName.getSelectionModel().getSelectedItem() == null && reportDan.getValue() == null && reportGacha.getValue() == null) {
                invoicelogDao.invertoryExcellReport( btnExcell,"1", "1", "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
