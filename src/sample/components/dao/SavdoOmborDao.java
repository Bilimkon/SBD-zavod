package sample.components.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import sample.Login;
import sample.components.models.SavdoOmbori;
import sample.components.sell.Utils.Utils;
import sample.dao.DaoUtils;
import sample.dao.database;
import sample.utils.Workbookcontroller;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class SavdoOmborDao {

    private Connection myConn;
    String apple = Utils.convertDateToStandardFormat(Utils.getCurrentDate());
    String user_id = String.valueOf(Login.currentUser.getId());
    Workbookcontroller workbookcontroller = new Workbookcontroller();

    public SavdoOmborDao(){
        try {
            myConn = database.getConnection();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void tableDao(TableView sellTable) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pr = null;
        ResultSet myRs = null;

        //List to add items
        ObservableList<SavdoOmbori> products = FXCollections.observableArrayList();
        statement = myConn.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM sell_v ORDER BY name");

        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    SavdoOmbori product = new SavdoOmbori();
                    product.setId(resultSet.getString("id"));
                    product.setBarcode(resultSet.getString("barcode"));
                    product.setName(resultSet.getString("name"));
                    product.setQuantity(resultSet.getString("quantity"));
                    product.setType(resultSet.getString("type"));

                    products.addAll(product);
                }
            }

            sellTable.setItems(products);


        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            try {
                sample.dao.DaoUtils.close(statement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void SellTableDao(TableView tableView, String who, String name, Label total_quantity, Label total_cost) {

        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pr = null;
        ResultSet myRs = null;
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
        try {
            //List to add items
            ObservableList<SavdoOmbori> userTables = FXCollections.observableArrayList();

            if (!who.isEmpty() && !name.isEmpty() ) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM sell_v where type='" + who + "' and name='" + name + "' ");

                pr = myConn.prepareStatement("SELECT sum(quantity) as total_quantity, sum(cost*quantity) total_cost  FROM sell_v where type='" + who + "' and name='" + name + "' ");
                myRs = pr.executeQuery();
            } else if (who.isEmpty() && !name.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM sell_v where name='"+name+"'");

                pr = myConn.prepareStatement("SELECT sum(quantity) as total_quantity, sum(cost*quantity)  total_cost  FROM sell_v where name='"+name+"'");
                myRs = pr.executeQuery();
            } else if (!who.isEmpty() && name.equals("")) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM sell_v where type='" + who + "' ");

                pr = myConn.prepareStatement("SELECT sum(quantity) as total_quantity, sum(cost*quantity)  total_cost FROM sell_v where type='" + who + "' ");
                myRs = pr.executeQuery();
            } else {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM sell_v order by name ");

                pr = myConn.prepareStatement("SELECT sum(quantity) as total_quantity, sum(cost*quantity)  total_cost FROM sell_v order by name ");
                myRs = pr.executeQuery();
            }

            if (resultSet != null) {
                while (resultSet.next()) {
                    SavdoOmbori history = new SavdoOmbori();
                    history.setId(resultSet.getString("id"));
                    history.setBarcode(resultSet.getString("barcode"));
                    history.setName(resultSet.getString("name"));
                    history.setType(resultSet.getString("type"));
                    history.setQuantity(resultSet.getString("quantity"));
                    history.setCost(formatter.format(resultSet.getDouble("cost")));
                    userTables.add(history);
                }

                if (myRs != null && myRs.next()) {
                    total_quantity.setText(formatter.format(myRs.getDouble("total_quantity")));
                    total_cost.setText(formatter.format(myRs.getDouble("total_cost")));

                }
            }
            tableView.setItems(userTables);

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            try {
                DaoUtils.close(statement, resultSet);
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
    public void getTypeList(ComboBox<String> comboBox) throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM type order by Name");
            while (resultSet.next()) {
                comboBox.getItems().addAll(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(statement, resultSet);
        }
    }

    public void getNameList(ComboBox<String> comboBox) throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Sell_v order by Name");
            while (resultSet.next()) {
                comboBox.getItems().addAll(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(statement, resultSet);
        }
    }

    public void SellExcellTableDao( String who, String name) {

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //List to add items
            ObservableList<SavdoOmbori> userTables = FXCollections.observableArrayList();

            if (!who.isEmpty() && !name.isEmpty() ) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM sell_v where type='" + who + "' and name='" + name + "' ");


            } else if (who.isEmpty() && !name.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM sell_v where name='"+name+"'");

            } else if (!who.isEmpty() && name.equals("")) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM sell_v where type='" + who + "' ");


            } else {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM sell_v order by name ");


            }

            if (resultSet != null) {
                while (resultSet.next()) {
                    Workbookcontroller workbookcontroller = new Workbookcontroller();
                    workbookcontroller.datebaseToExcelResultset("","Savdo_ombori.xls", resultSet);
                }
            }


        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            try {
                DaoUtils.close(statement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void UpdateProduct(String id, String text, String text1, String text2, Label label) {
        try(PreparedStatement preparedStatement =myConn.prepareStatement("Update sell set name=?, quantity=?, cost=? where id=?")) {
            preparedStatement.setString(1,text);
            preparedStatement.setString(2,text1);
            preparedStatement.setString(3,text2);
            preparedStatement.setString(4,id);
           int i =  preparedStatement.executeUpdate();
           if (i>0){
               label.setStyle("-fx-background-color:Blue");
               label.setText("Yangilandi");
               try(PreparedStatement preparedStatement1 =myConn.prepareStatement("INSERT INTO SELLUPDATELOG (name ,quantity, cost, p_id, cr_on, cr_by) values(?,?,?,?,?,?)")) {
                   preparedStatement1.setString(1, text);
                   preparedStatement1.setString(2, text1);
                   preparedStatement1.setString(3, text2);
                   preparedStatement1.setString(4, id);
                   preparedStatement1.setString(5, apple);
                   preparedStatement1.setString(6, user_id);
                   preparedStatement1.execute();
               }
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
