package sample.components.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import sample.dao.DaoUtils;
import sample.dao.database;
import sample.model.TableB;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class p2TarixDao {

    private Connection myConn = null;

    public p2TarixDao() {
        try {
            myConn = database.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void tableB(TableView tableView,String name, String dan, String gacha, Label kirim, Label chiqim) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pr = null;
        ResultSet myRs = null;

        //List to add items
        ObservableList<TableB> tableBS = FXCollections.observableArrayList();
        if(!name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty())
        {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM production2_tarix where name='"+name+"' and date BETWEEN '"+dan+"' AND '"+gacha+"' ");

            pr = myConn.prepareStatement("SELECT sum(quantity) as outt, sum(p_quantity) as inn FROM production2_tarix where name='"+name+"' and date BETWEEN '"+dan+"' AND '"+gacha+"' ");
            myRs = pr.executeQuery();
        } else if(name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()){
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM production2_tarix where date BETWEEN '"+dan+"' AND '"+gacha+"' ");

            pr = myConn.prepareStatement("SELECT sum(quantity) as outt, sum(p_quantity) as inn FROM production2_tarix where date BETWEEN '"+dan+"' AND '"+gacha+"' ");
            myRs = pr.executeQuery();
        }
        else if(!name.isEmpty() && dan.isEmpty() && gacha.isEmpty())
        {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM production2_tarix where name='"+name+"'");

            pr = myConn.prepareStatement("SELECT sum(quantity) as outt, sum(p_quantity) as inn FROM production2_tarix where name='"+name+"'");
            myRs = pr.executeQuery();
        }
        else if(name.equals("1") || dan.equals("1") || gacha.equals("1")) {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM production2_tarix ORDER BY id desc limit 300");

            pr = myConn.prepareStatement("SELECT sum(quantity) as outt, sum(p_quantity) as inn FROM production2_tarix ORDER BY id desc limit 300");
            myRs = pr.executeQuery();
        }

        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    TableB tableB = new TableB();
                    tableB.setId(resultSet.getString("id"));
                    tableB.setDate(resultSet.getString("date"));
                    tableB.setBarcode(resultSet.getString("barcode"));
                    tableB.setName(resultSet.getString("name"));
                    tableB.setQuantity(resultSet.getString("quantity"));
                    tableB.setP_quantity(resultSet.getString("p_quantity"));
                    tableB.setP_name(resultSet.getString("p_name"));
                    tableB.setP_barcode(resultSet.getString("p_barcode"));
                    tableB.setCr_by(resultSet.getString("user_id"));
                    tableB.setColor(resultSet.getString("color"));
                    tableB.setType(resultSet.getString("type"));
                    tableBS.add(tableB);
                }
            }
            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
            symbols.setGroupingSeparator(' ');
            DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);

            if (myRs != null && myRs.next()) {
                kirim.setText(formatter.format(myRs.getDouble("inn")));
                chiqim.setText(formatter.format(myRs.getDouble("outt")));
            }

            tableView.setItems(tableBS);


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

    public void getP2TarixSelectName(ComboBox<String> comboBox) throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT name FROM production2_tarix order by id desc");
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
