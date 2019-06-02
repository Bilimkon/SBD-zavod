package sample.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.model.*;

import java.sql.*;


public class Main3Dao {

    Connection myConn = null;

    public Main3Dao() {
        try {
            myConn = database.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tablePaper(TableView tableView) {

        Statement statement = null;
        ResultSet resultSet = null;

        //List to add items
        ObservableList<tablePaper> tablePapers = FXCollections.observableArrayList();
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM production2 ORDER BY id");
            while (resultSet.next()) {
                tablePaper tablePaper = new tablePaper();
                tablePaper.setId(resultSet.getString("id"));
                tablePaper.setBarcode(resultSet.getString("barcode"));
                tablePaper.setName(resultSet.getString("name"));
                tablePaper.setType(resultSet.getString("type"));
                tablePaper.setQuantity(resultSet.getString("quantity"));

                tablePapers.add(tablePaper);
            }
            tableView.setItems(tablePapers);

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


    public void tableDsp(TableView tableView) {

        Statement statement = null;
        ResultSet resultSet = null;

        //List to add items
        ObservableList<TableDsp> tableDsps = FXCollections.observableArrayList();
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product_v ORDER BY id");
            while (resultSet.next()) {
                TableDsp tableDsp = new TableDsp();
                tableDsp.setId(resultSet.getString("id"));
                tableDsp.setBarcode(resultSet.getString("barcode"));
                tableDsp.setName(resultSet.getString("name"));
                tableDsp.setType(resultSet.getString("type"));
                tableDsp.setQuantity(resultSet.getString("quantity"));

                tableDsps.add(tableDsp);
            }
            tableView.setItems(tableDsps);

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


    public void tableProduction(TableView tableView) {

        Statement statement = null;
        ResultSet resultSet = null;

        //List to add items
        ObservableList<Production3> tablePapers = FXCollections.observableArrayList();
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM production3 ORDER BY id");
            while (resultSet.next()) {
                Production3 production3 = new Production3();
                production3.setId(resultSet.getString("id"));
                production3.setBarcode(resultSet.getString("barcode"));
                production3.setName(resultSet.getString("name"));
                production3.setType(resultSet.getString("type_id"));
                production3.setQuantity(resultSet.getString("quantity"));
                production3.setP_quantity(resultSet.getString("p_quantity"));
                tablePapers.add(production3);
            }
            tableView.setItems(tablePapers);

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
/**
 * Getting list of products in the database
* */
    public SimpleProduct getDspBarcode(String barcode) throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            SimpleProduct product = null;
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product where barcode=" + barcode);
            while (resultSet.next()) {
                product = new SimpleProduct(
                        resultSet.getString("id"),
                        resultSet.getString("barcode"),
                        resultSet.getString("name"),
                        resultSet.getString("type_id"),
                        resultSet.getString(7),
                        resultSet.getString("quantity"),
                        resultSet.getString(9),
                        resultSet.getString(16),
                        resultSet.getString(14),
                        resultSet.getString(15),
                        resultSet.getString(2)
                );
            }
            return product;
        } catch (Exception e) {
            DaoUtils.close(statement, resultSet);

        } finally {
            DaoUtils.close(statement, resultSet);
        }
        return null;
    }


    public SimplePaperProduct getPaperBarcode(String barcode) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            SimplePaperProduct product = null;
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM production2 where barcode=" + barcode);
            while (resultSet.next()) {
                product = new SimplePaperProduct(
                        resultSet.getString("id"),
                        resultSet.getString("barcode"),
                        resultSet.getString("name"),
                        resultSet.getString("type"),
                        resultSet.getString("quantity"),
                        resultSet.getString("p_quantity")
                );
            }
            return product;
        } catch (Exception e) {
            DaoUtils.close(statement, resultSet);
        } finally {
            DaoUtils.close(statement, resultSet);
        }
        return null;
    }

    /**
     * Inserting selected products to the sale table
     */
    public void addToSaleTable(String barcode, String name, String type_id, String quantity) throws SQLException {
        PreparedStatement pr = null;
        try {
            pr = myConn.prepareStatement("INSERT INTO production3 (barcode, name, type_id, quantity, p_quantity) values (?,?,?,?,?)");
            pr.setString(1, barcode);
            pr.setString(2, name);
            pr.setString(3, type_id);
            pr.setString(4, quantity);
            pr.setString(5, "0");
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                pr.close();
            }

        }

    }

    public void setUpdateAction(String quantity, String id) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = myConn.prepareStatement("UPDATE production3 set p_quantity=? where id=?");
            ps.setString(1, quantity);
            ps.setString(2, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

}
