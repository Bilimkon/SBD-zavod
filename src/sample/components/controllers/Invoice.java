package sample.components.controllers;

import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.components.dao.InvoiceDao;

import java.net.URL;
import java.util.ResourceBundle;

public class Invoice implements Initializable {

    @FXML
    private TableView Invoice_table;
    @FXML
    private TextField Invoice_name;
    @FXML
    private TextField Invoice_Company;
    @FXML
    private TextField Invoice_Price;
    @FXML
    private JFXDatePicker Invoice_date;
    InvoiceDao invoiceDao = new InvoiceDao();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InvoiceTable();

        TableColumn id = new TableColumn("Tartib raqami");
        TableColumn name = new TableColumn("Nomi");
        TableColumn company = new TableColumn("Kompaniya nomi");
        TableColumn total_price = new TableColumn("Summa");
        TableColumn date = new TableColumn("Sana");

        Invoice_table.getColumns().addAll(id, name, company, total_price, date);

        id.setCellValueFactory(new PropertyValueFactory<Invoice, String>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Invoice, String>("name"));
        company.setCellValueFactory(new PropertyValueFactory<Invoice, String>("company"));
        total_price.setCellValueFactory(new PropertyValueFactory<Invoice, String>("total_price"));
        date.setCellValueFactory(new PropertyValueFactory<Invoice, String>("date"));
    }
    private void InvoiceTable(){
        invoiceDao.InvoiceTable(Invoice_table);
    }

    @FXML
    private void Invoice_btnSaveAction(){
        try {

            String name = Invoice_name.getText();
            String company = Invoice_Company.getText();
            String price = Invoice_Price.getText();
            String date = Invoice_date.getValue().toString();
            invoiceDao.addInvoice(name, company, price, date);
            InvoiceTable();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
