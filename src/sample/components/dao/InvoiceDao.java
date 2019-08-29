package sample.components.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import sample.Login;
import sample.components.models.Invoice;
import sample.dao.DaoUtils;
import sample.dao.database;
import sample.model.Product;
import sample.model.User;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class InvoiceDao {

    User u = Login.currentUser;
    Connection myConn = null;

    public InvoiceDao() {
        try {
            myConn = database.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void InvoiceTable(TableView tableView,String name, String dan, String gacha) {
        try {
            Statement statement = null;
            ResultSet resultSet = null;

            //List to add items
            ObservableList<Invoice> invoices = FXCollections.observableArrayList();
            try {
                if(!name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty())
                {
                    statement = myConn.createStatement();
                    resultSet = statement.executeQuery("SELECT * FROM invoice where company='"+name+"' and date BETWEEN '"+dan+"' AND '"+gacha+"' ");

                } else if(name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()){
                    statement = myConn.createStatement();
                    resultSet = statement.executeQuery("SELECT * FROM invoice where date BETWEEN '"+dan+"' AND '"+gacha+"' ");
                }
                 else if(!name.isEmpty() && dan.isEmpty() && gacha.isEmpty())
                {
                    statement = myConn.createStatement();
                    resultSet = statement.executeQuery("SELECT * FROM invoice where company='"+name+"'");
                }
                else if(name.equals("1") || dan.equals("1") || gacha.equals("1")) {
                    statement = myConn.createStatement();
                    resultSet = statement.executeQuery("SELECT * FROM invoice ORDER BY id desc limit 300");
                }
                if (resultSet != null) {
                    while (resultSet.next()) {
                        Invoice invoice = new Invoice();
                        invoice.setId(resultSet.getString("id"));
                        invoice.setName(resultSet.getString("name"));
                        invoice.setCompany(resultSet.getString("company"));
                        invoice.setCurrency(resultSet.getString("currency"));
                        invoice.setTotal_price(resultSet.getString("total_price"));
                        invoice.setDate(resultSet.getString("date"));

                        invoices.add(invoice);
                    }
                }
                tableView.setItems(invoices);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DaoUtils.close(statement, resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addInvoice(String name, String company, String currency, String price, String date) throws SQLException {
        try (PreparedStatement pr = myConn.prepareStatement("INSERT INTO invoice(name, company, total_price, date,user_id, currency) values (?,?,?,?,?,?)")) {

            pr.setString(1, name);
            pr.setString(2, company);
            pr.setString(3, price);
            pr.setString(4, date);
            pr.setInt(5, u.getId());
            pr.setString(6, currency);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ProductHistory(TableView tableView, Label quantity, Label price, String id) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        ObservableList<Product> products = FXCollections.observableArrayList();
        PreparedStatement pr = null;
        ResultSet myRs = null;
        try {

            if (!id.equals("*")) {
                pr = myConn.prepareStatement("SELECT * FROM product_h_v WHERE invoice=" + id);
                resultSet = pr.executeQuery();

                pr = myConn.prepareStatement("SELECT sum(quantity*cost) as total_cost, sum(quantity) total_quantity FROM product_h_v WHERE invoice=" + id);
                myRs = pr.executeQuery();

            } else {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM product_h_v ORDER BY id limit 300");
            }
            while (resultSet.next()) {

                Product product = new Product();
                product.setId(resultSet.getString("id"));
                product.setOperType(resultSet.getString("oper_type"));
                product.setInvoice(resultSet.getString("invoice"));
                product.setUnit(resultSet.getString("unit"));
                product.setBarcode(resultSet.getString("barcode"));
                product.setName(resultSet.getString("name"));
                product.setType(resultSet.getString("type"));
                product.setCost(resultSet.getString("cost"));
                product.setQuantity(resultSet.getString("quantity"));
                product.setSuplier(resultSet.getString("suplier"));
                product.setDate_cr(resultSet.getString("date_cr"));
                product.setCr_by(resultSet.getString("user"));
                product.setDescription(resultSet.getString("description"));
                product.setColor(resultSet.getString("color"));

                products.addAll(product);
            }
            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
            symbols.setGroupingSeparator(' ');
            DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
            if (myRs != null && myRs.next()) {
                quantity.setText(formatter.format(myRs.getDouble("total_quantity")));
                price.setText(formatter.format(myRs.getDouble("total_cost")));

            }
            tableView.setItems(products);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(statement, resultSet);
            if (pr != null) {
                pr.close();
            }
            if (myRs != null) {
                myRs.close();
            }
        }
    }
}
