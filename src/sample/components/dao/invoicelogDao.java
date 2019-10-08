package sample.components.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import sample.dao.database;
import sample.model.Product;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class invoicelogDao {
    Connection myConn;

    public invoicelogDao(){
        try {
            myConn = database.getConnection();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void ReportTableDao(TableView tableView, String name, String dan, String gacha, Label quantity) throws SQLException {


        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pr = null;
        ResultSet myRs = null;

        //List to add items
        ObservableList<Product> products = FXCollections.observableArrayList();

        if (!name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product_h_v where type='" + name + "' and substr(date_cr,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");

            pr = myConn.prepareStatement("SELECT sum(quantity) as outt FROM product_h_v where type='" + name + "' and substr(date_cr,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");
            myRs = pr.executeQuery();
        } else if (name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product_h_v where substr(date_cr,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");

            pr = myConn.prepareStatement("SELECT sum(quantity) as outt FROM product_h_v where substr(date_cr,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");
            myRs = pr.executeQuery();
        } else if (!name.isEmpty() && dan.isEmpty() && gacha.isEmpty()) {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product_h_v where type='" + name + "'");

            pr = myConn.prepareStatement("SELECT sum(quantity) as outt FROM product_h_v where type='" + name + "'");
            myRs = pr.executeQuery();
        } else if (name.equals("1") || dan.equals("1") || gacha.equals("1")) {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product_h_v ORDER BY id desc limit 500");

            pr = myConn.prepareStatement("SELECT sum(quantity) as outt FROM product_h_v");
            myRs = pr.executeQuery();
        }
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);

        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getString("id"));
                    product.setOperType(resultSet.getString("oper_type"));
                    product.setInvoice(resultSet.getString("invoice"));
                    product.setUnit(resultSet.getString("unit"));
                    product.setBarcode(resultSet.getString("barcode"));
                    product.setName(resultSet.getString("name"));
                    product.setType(resultSet.getString("type"));
                    product.setCost(formatter.format(resultSet.getDouble("cost")));
                    product.setQuantity(formatter.format(resultSet.getDouble("quantity")));
                    product.setSuplier(resultSet.getString("suplier"));
                    product.setDate_cr(resultSet.getString("date_cr"));
                    product.setCr_by(resultSet.getString("user"));
                    product.setDescription(resultSet.getString("description"));
                    product.setColor(resultSet.getString("color"));

                    products.addAll(product);
                }
            }

            if (myRs != null && myRs.next()) {
                quantity.setText(formatter.format(myRs.getDouble("outt")));

            }

            tableView.setItems(products);


        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            try {
                sample.dao.DaoUtils.close(statement, resultSet);
                if (pr != null) {
                    pr.close();
                }
                if (myRs != null) {
                    myRs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void ReportSelectName(ComboBox<String> comboBox) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT name from type order by name ");
            while (resultSet.next()) {  // loop
                // Now add the comboBox addAll statement
                comboBox.getItems().addAll(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sample.dao.DaoUtils.close(statement, resultSet);
        }
    }
}
