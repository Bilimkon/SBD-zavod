package sample.components.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import sample.dao.DaoUtils;
import sample.dao.database;
import sample.model.Production3;

import java.math.BigDecimal;
import java.sql.*;

public class P3HistoryDao {

    Connection myConn = null;

    public P3HistoryDao() {
        try {
            myConn = database.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tableProduction(TableView tableView,String name, String dan, String gacha, Label kirim, Label chiqim, Label outt2) throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pr = null;
        ResultSet myRs = null;

        ObservableList<Production3> tablePapers = FXCollections.observableArrayList();
        if(!name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty())
        {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM production3 where name='"+name+"' and cr_on BETWEEN '"+dan+"' AND '"+gacha+"' ");

            pr = myConn.prepareStatement("SELECT sum(quantity) as inn, sum(pquantity) as outt, sum(dquantity) as outt2 FROM production3 where name='"+name+"' and cr_on BETWEEN '"+dan+"' AND '"+gacha+"' ");
            myRs = pr.executeQuery();
        } else if(name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()){
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM production3 where cr_on BETWEEN '"+dan+"' AND '"+gacha+"' ");

            pr = myConn.prepareStatement("SELECT sum(quantity) as inn, sum(pquantity) as outt, sum(dquantity) as outt2 FROM production3 where cr_on BETWEEN '"+dan+"' AND '"+gacha+"' ");
            myRs = pr.executeQuery();
        }
        else if(!name.isEmpty() && dan.isEmpty() && gacha.isEmpty())
        {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM production3 where name='"+name+"'");

            pr = myConn.prepareStatement("SELECT sum(quantity) as inn, sum(pquantity) as outt, sum(dquantity) as outt2 FROM production3 where name='"+name+"'");
            myRs = pr.executeQuery();
        }
        else if(name.equals("1") || dan.equals("1") || gacha.equals("1")) {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM production3 ORDER BY id desc limit 300");

            pr = myConn.prepareStatement("SELECT sum(quantity) as inn, sum(pquantity) as outt, sum(dquantity) as outt2 FROM production3 ORDER BY id desc limit 300");
            myRs = pr.executeQuery();
        }

        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    Production3 production3 = new Production3();
                    production3.setId(resultSet.getString("id"));
                    production3.setBarcode(resultSet.getString("barcode"));
                    production3.setName(resultSet.getString("name"));
                    production3.setType(resultSet.getString("type"));
                    production3.setColor(resultSet.getString("color"));
                    production3.setQuantity(resultSet.getString("quantity"));
                    production3.setpBarcode(resultSet.getString("pBarcode"));
                    production3.setpName(resultSet.getString("pName"));
                    production3.setpCost(resultSet.getString("pCost"));
                    production3.setpQuantity(resultSet.getString("pQuantity"));
                    production3.setpColor(resultSet.getString("pColor"));
                    production3.setdBarcode(resultSet.getString("dBarcode"));
                    production3.setdName(resultSet.getString("dName"));
                    production3.setdQuantity(resultSet.getString("dQuantity"));
                    production3.setCr_on(resultSet.getString("cr_on"));
                    production3.setCr_by(resultSet.getString("cr_by"));
                    tablePapers.add(production3);
                }
            }
            if (myRs != null && myRs.next()) {
                kirim.setText(new BigDecimal(myRs.getDouble("inn")).toPlainString() + " Dona");
                chiqim.setText(new BigDecimal(myRs.getDouble("outt")).toPlainString() + " PDona");
                outt2.setText(new BigDecimal(myRs.getDouble("outt2")).toPlainString() + " DDona");
            }

            tableView.setItems(tablePapers);
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

    public void getP3TarixSelectName(ComboBox<String> comboBox) throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT name FROM production3 order by id desc");
            while (resultSet.next()) {
                comboBox.getItems().addAll(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(statement, resultSet);
        }
    }
}
