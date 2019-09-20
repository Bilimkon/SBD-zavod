package sample.components.controllers;

import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.components.dao.InvoiceDao;
import sample.dao.ProductDao;
import sample.model.Product;
import sample.utils.ComboBoxAutoComplete;
import sample.utils.utils;

import javax.swing.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class Invoice implements Initializable {

    @FXML
    public Label CompanyId;
    @FXML
    private TableView Invoice_table;
    @FXML
    private TextField Invoice_name;
    @FXML
    private TextField Invoice_Price;
    @FXML
    private JFXDatePicker Invoice_date;
    @FXML
    private ComboBox<String> InvoiceCompanyList;
    @FXML
    private ComboBox<String> invoiceValyutaList;
    @FXML
    private TableView tableProductHistory;
    @FXML
    private ComboBox<String> invoiceSelectName;
    @FXML
    private DatePicker invoiceDan;
    @FXML
    private DatePicker invoiceGacha;
    @FXML
    private Label invoiceItemQuantity;
    @FXML
    private Label invoiceItemPrice;
    @FXML
    private Label invoiceItem$Price;
    @FXML private DatePicker mainDate;

    InvoiceDao invoiceDao = new InvoiceDao();
    sample.dao.ProductDao productDao = new ProductDao();
    sample.utils.utils utils = new utils();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InvoiceTable();
        invoiceValyutaList.getItems().addAll( "Dollar", "Sum");
        invoiceSelectName.getItems().addAll("");

        utils.thousandDivider(Invoice_Price);

        getCompanyList();
        getCompanyList1();

        InvoiceCompanyList.setTooltip(new Tooltip());
        new ComboBoxAutoComplete<String>(InvoiceCompanyList);
        invoiceSelectName.setTooltip(new Tooltip());
        new ComboBoxAutoComplete<String>(invoiceSelectName);
        initializeInvoiceTable();
        initializeProductHistory();
        ProductHistoryTable();
        setOzgaartirishMaxsulot();
        invoiceBtnAction();
    }

    private void initializeInvoiceTable() {

        TableColumn id = new TableColumn("Tartib raqami");
        TableColumn name = new TableColumn("Nomi");
        TableColumn company = new TableColumn("Kompaniya nomi");
        TableColumn currency = new TableColumn("Valyuta");
        TableColumn total_price = new TableColumn("Summa");
        TableColumn date = new TableColumn("Sana");

        Invoice_table.getColumns().addAll(id, name, company, currency, total_price, date);

        id.setCellValueFactory(new PropertyValueFactory<Invoice, String>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Invoice, String>("name"));
        company.setCellValueFactory(new PropertyValueFactory<Invoice, String>("company"));
        currency.setCellValueFactory(new PropertyValueFactory<Invoice, String>("currency"));
        total_price.setCellValueFactory(new PropertyValueFactory<Invoice, String>("total_price"));
        date.setCellValueFactory(new PropertyValueFactory<Invoice, String>("date"));

    }

    private void initializeProductHistory() {
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

        tableProductHistory.getColumns().addAll(id, operType, invoice, unit, barcode, name, type, cost, quantity, suplier, date, user, color, description);

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

    private void setOzgaartirishMaxsulot() {
        try {
            Invoice_table.setOnMouseClicked(event -> {
                if (Invoice_table.getSelectionModel().getSelectedItem() != null) {
                    sample.components.models.Invoice invoice = (sample.components.models.Invoice) Invoice_table.getSelectionModel().getSelectedItem();
                    try {
                        invoiceDao.ProductHistory(tableProductHistory, invoiceItemQuantity, invoiceItemPrice, invoice.getId());
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                }
            });
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @FXML
    private void invoiceBtnAction() {
        try {
            if (invoiceDan.getValue() != null && invoiceGacha.getValue() != null && invoiceSelectName.getSelectionModel().getSelectedItem() != null) {
                String name = invoiceSelectName.getSelectionModel().getSelectedItem();
                LocalDate date1 = invoiceDan.getValue();
                LocalDate date2 = invoiceGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                invoiceDao.InvoiceTable(Invoice_table, name, sdate1, sdate2);
            } else if (invoiceDan.getValue() != null && invoiceGacha.getValue() != null && invoiceSelectName.getSelectionModel().getSelectedItem() == null) {
                LocalDate date1 = invoiceDan.getValue();
                LocalDate date2 = invoiceGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                invoiceDao.InvoiceTable(Invoice_table, "", sdate1, sdate2);
            } else if (invoiceSelectName.getSelectionModel().getSelectedItem() != null && invoiceDan.getValue() == null && invoiceGacha.getValue() == null) {
                invoiceDao.InvoiceTable(Invoice_table, invoiceSelectName.getSelectionModel().getSelectedItem(), "", "");
            } else if (invoiceSelectName.getSelectionModel().getSelectedItem() == null && invoiceDan.getValue() == null && invoiceGacha.getValue() == null) {
                invoiceDao.InvoiceTable(Invoice_table, "1", "1", "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void InvoiceTable() {
        invoiceDao.InvoiceTable(Invoice_table, "1", "1", "1");
    }

    private void ProductHistoryTable() {
        try {
            invoiceDao.ProductHistory(tableProductHistory, invoiceItemQuantity, invoiceItemPrice, "*");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Invoice_btnSaveAction() {
        try {

            if(!Invoice_name.getText().isEmpty() && InvoiceCompanyList.getSelectionModel().getSelectedItem()!= null && InvoiceCompanyList.getSelectionModel().getSelectedItem()!=null
            && !Invoice_Price.getText().isEmpty() && Invoice_date.getValue()!=null) {
                String name = Invoice_name.getText().trim().replaceAll("\\s+", "");
                String company = InvoiceCompanyList.getSelectionModel().getSelectedItem();
                String currency = invoiceValyutaList.getSelectionModel().getSelectedItem();
                String price = Invoice_Price.getText().trim().replaceAll("\\s+", "");
                LocalDate date1 = Invoice_date.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                invoiceDao.addInvoice(name, company, currency, price, sdate1, CompanyId.getText());
                InvoiceTable();
                Invoice_name.setText("");
                Invoice_Price.setText("");
                Invoice_name.setPromptText("Saqlandi");
            } else {
                JOptionPane.showMessageDialog(null, "Hamma ma'lumotlarni kiritishingiz shart!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void getCompanyList() {
        try {
            String CompanyName = InvoiceCompanyList.getSelectionModel().getSelectedItem();
            productDao.addSuplierCombobox(InvoiceCompanyList);
            invoiceDao.selectCompanyId(CompanyId, CompanyName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getCompanyList1() {
        try {
            productDao.addSuplierCombobox(invoiceSelectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
