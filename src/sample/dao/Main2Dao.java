package sample.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.model.TableA;
import sample.model.TableB;

import java.sql.*;

public class Main2Dao {

    private Connection myConn = null;

    public Main2Dao() {
        try {
            myConn = database.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tableA(TableView tableView) {
        Statement statement = null;
        ResultSet resultSet = null;

        //List to add items
        ObservableList<TableA> tableAS = FXCollections.observableArrayList();
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product_v ORDER BY id");
            while (resultSet.next()) {
                TableA tableA = new TableA();
                tableA.setId(resultSet.getString("id"));
                tableA.setBarcode(resultSet.getString("barcode"));
                tableA.setName(resultSet.getString("name"));
                tableA.setType(resultSet.getString("type"));
                tableA.setQuantity(resultSet.getString("quantity"));

                tableAS.add(tableA);
            }
            tableView.setItems(tableAS);

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

    public void tableB(TableView tableView) {
        Statement statement = null;
        ResultSet resultSet = null;

        //List to add items
        ObservableList<TableB> tableBS = FXCollections.observableArrayList();
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM production2 ORDER BY id");
            while (resultSet.next()) {
                TableB tableB = new TableB();
                tableB.setId(resultSet.getString("id"));
                tableB.setBarcode(resultSet.getString("barcode"));
                tableB.setName(resultSet.getString("name"));
                tableB.setType(resultSet.getString("type"));
                tableB.setQuantity(resultSet.getString("quantity"));
                tableB.setP_quantity(resultSet.getString("p_quantity"));

                tableBS.add(tableB);
            }
            tableView.setItems(tableBS);

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

    public void addToSaleTable(String barcode, String name, String type_id, String quantity) throws SQLException {
        PreparedStatement pr = null;
        try {
            pr = myConn.prepareStatement("INSERT INTO production2 (barcode, name, type, quantity, p_quantity) values (?,?,?,?,?)");
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

    public void setUpdateAction(String quantity, String barcode, String id) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = myConn.prepareStatement("UPDATE production2 set p_quantity=? where id=?");
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

    public void revertAction(String id, String barcode, String quantity) throws SQLException {
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        try {
            ps = myConn.prepareStatement("update  product set quantity=(quantity+?) where barcode=?");
            ps.setString(1, quantity);
            ps.setString(2, barcode);
            ps.executeUpdate();

            ps1 = myConn.prepareStatement("DELETE from production2 where id=?");
            ps1.setString(1, id);
            ps1.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (ps1 != null) {
                ps1.close();
            }
        }
    }
}
