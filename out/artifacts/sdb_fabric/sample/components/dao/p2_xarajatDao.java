package sample.components.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import sample.components.models.p2Xarajat;
import sample.dao.DaoUtils;
import sample.dao.database;
import sample.utils.utils;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class p2_xarajatDao {

    sample.utils.utils utils = new utils();
    private Connection myConn = null;


    public p2_xarajatDao() {
        try {
            myConn = database.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tableA(TableView tableView,  String name, String dan, String gacha, Label total_quantity) {
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pr = null;
        ResultSet myRs = null;

        //List to add items
        ObservableList<p2Xarajat> tableAS = FXCollections.observableArrayList();
        try {


            if(!name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty())
            {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM production2_xarajat where name='"+name+"' and substr(cr_on,7,10) BETWEEN '"+dan+"' AND '"+gacha+"' ");

                pr = myConn.prepareStatement("SELECT sum(quantity) as outt FROM production2_xarajat where name='"+name+"' and substr(cr_on,7,10) BETWEEN '"+dan+"' AND '"+gacha+"' ");
                myRs = pr.executeQuery();
            } else if(name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()){
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM production2_xarajat where substr(cr_on,7,10) BETWEEN '"+dan+"' AND '"+gacha+"' ");

                pr = myConn.prepareStatement("SELECT sum(quantity) as outt FROM production2_xarajat where substr(cr_on,7,10) BETWEEN '"+dan+"' AND '"+gacha+"' ");
                myRs = pr.executeQuery();
            }
            else if(!name.isEmpty() && dan.isEmpty() && gacha.isEmpty())
            {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM production2_xarajat where name='"+name+"'");

                pr = myConn.prepareStatement("SELECT sum(quantity) as outt FROM production2_xarajat where name='"+name+"'");
                myRs = pr.executeQuery();
            }
            else if(name.equals("1") || dan.equals("1") || gacha.equals("1")) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM production2_xarajat ORDER BY id desc limit 500");

                pr = myConn.prepareStatement("SELECT sum(quantity) as outt FROM production2_xarajat");
                myRs = pr.executeQuery();
            }

            if (resultSet != null) {
                while (resultSet.next()) {
                    p2Xarajat tableA = new p2Xarajat();
                    tableA.setId(resultSet.getString("id"));
                    tableA.setBarcode(resultSet.getString("barcode"));
                    tableA.setName(resultSet.getString("name"));
                    tableA.setType(resultSet.getString("type"));
                    tableA.setQuantity(resultSet.getString("quantity"));
                    tableA.setCr_on(resultSet.getString("cr_on"));
                    tableA.setCr_by(resultSet.getString("cr_by"));

                    tableAS.add(tableA);
                }
            }

            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
            symbols.setGroupingSeparator(' ');
            DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);

            if (myRs != null && myRs.next()) {
                total_quantity.setText(formatter.format(myRs.getDouble("outt")));

            }

            tableView.setItems(tableAS);

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            try {
                DaoUtils.close(statement, resultSet);
                if (myRs != null) {
                    myRs.close();
                }
                if (pr != null) {
                    pr.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getP2XarajatSelectName(ComboBox<String> comboBox) throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT name FROM production2_xarajat order by id desc");
            while (resultSet.next()) {  // loop
                // Now add the comboBox addAll statement
                comboBox.getItems().addAll(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(statement, resultSet);
        }
    }
}
