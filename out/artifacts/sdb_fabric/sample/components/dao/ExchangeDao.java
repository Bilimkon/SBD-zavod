package sample.components.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import sample.components.models.Exchange;
import sample.dao.DaoUtils;
import sample.dao.database;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class ExchangeDao {

    private Connection myConn = null;

    public ExchangeDao() {
        try {
            myConn = database.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void exchangeTableDao(TableView tableView, String name, String dan, String gacha, Label total_quantity) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pr = null;
        ResultSet myRs = null;

        //List to add items
        ObservableList<Exchange> tableBS = FXCollections.observableArrayList();
        if(!name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty())
        {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM exchange_log where name='"+name+"' and substr(cr_on,7,10) BETWEEN '"+dan+"' AND '"+gacha+"' ");

            pr = myConn.prepareStatement("SELECT sum(quantity) as outt FROM exchange_log where name='"+name+"' and substr(cr_on,7,10) BETWEEN '"+dan+"' AND '"+gacha+"' ");
            myRs = pr.executeQuery();
        } else if(name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()){
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM exchange_log where substr(cr_on,7,10) BETWEEN '"+dan+"' AND '"+gacha+"' ");

            pr = myConn.prepareStatement("SELECT sum(quantity) as outt FROM exchange_log where substr(cr_on,7,10) BETWEEN '"+dan+"' AND '"+gacha+"' ");
            myRs = pr.executeQuery();
        }
        else if(!name.isEmpty() && dan.isEmpty() && gacha.isEmpty())
        {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM exchange_log where name='"+name+"'");

            pr = myConn.prepareStatement("SELECT sum(quantity) as outt FROM exchange_log where name='"+name+"'");
            myRs = pr.executeQuery();
        }
        else if(name.equals("1") || dan.equals("1") || gacha.equals("1")) {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM exchange_log ORDER BY id desc limit 300");

            pr = myConn.prepareStatement("SELECT sum(quantity) as outt FROM exchange_log ORDER BY id desc limit 300");
            myRs = pr.executeQuery();
        }

        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    Exchange tableB = new Exchange();
                    tableB.setId(resultSet.getString("id"));
                    tableB.setBarcode(resultSet.getString("barcode"));
                    tableB.setName(resultSet.getString("name"));
                    tableB.setQuantity(resultSet.getString("quantity"));
                    tableB.setComment(resultSet.getString("comment"));
                    tableB.setCr_on(resultSet.getString("cr_on"));
                    tableB.setCr_by(resultSet.getString("cr_by"));
                    tableB.setType(resultSet.getString("type"));
                    tableBS.add(tableB);
                }
            }
            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
            symbols.setGroupingSeparator(' ');
            DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);

            if (myRs != null && myRs.next()) {
                total_quantity.setText(formatter.format(myRs.getDouble("outt")));

            }


            tableView.setItems(tableBS);


        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            try {
                DaoUtils.close(statement, resultSet);
                if (pr != null) {
                    pr.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getP2ExchangeSelectName(ComboBox<String> comboBox) throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT name FROM exchange_log order by id desc");
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
