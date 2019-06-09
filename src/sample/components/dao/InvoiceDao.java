package sample.components.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.components.models.Invoice;
import sample.dao.DaoUtils;
import sample.dao.database;

import java.sql.*;

public class InvoiceDao {

    Connection myConn = null;

    public InvoiceDao(){
        try {
            myConn = database.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void InvoiceTable(TableView tableView){
        try {
            Statement statement = null;
            ResultSet resultSet = null;

            //List to add items
            ObservableList<Invoice> invoices = FXCollections.observableArrayList();
            try {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM invoice ORDER BY id desc limit 100");
                while (resultSet.next()) {
                    Invoice invoice = new Invoice();
                    invoice.setId(resultSet.getString("id"));
                    invoice.setName(resultSet.getString("name"));
                    invoice.setCompany(resultSet.getString("company"));
                    invoice.setTotal_price(resultSet.getString("total_price"));
                    invoice.setDate(resultSet.getString("date"));

                    invoices.add(invoice);
                }
                tableView.setItems(invoices);

            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                DaoUtils.close(statement, resultSet);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addInvoice(String name, String company, String price, String date) throws SQLException {
        PreparedStatement pr = null;
        try{

        pr = myConn.prepareStatement("INSERT INTO invoice(name, company, total_price, date) values (?,?,?,?)");
        pr.setString(1, name);
        pr.setString(2, company);
        pr.setString(3, price);
        pr.setString(4, date);
        pr.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (pr != null) {
                pr.close();
            }
        }
    }
}
