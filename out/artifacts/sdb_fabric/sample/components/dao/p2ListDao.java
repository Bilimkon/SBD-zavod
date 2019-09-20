package sample.components.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import sample.components.models.P2List;
import sample.dao.DaoUtils;
import sample.dao.database;

import java.sql.*;

public class p2ListDao {

    private Connection myConn = null;

    public p2ListDao() {
        try {
            myConn = database.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addList(String name, String barcode, String type, String description) throws SQLException {

        PreparedStatement pr = null;

        try {
                String type_id = getComboBoxId("type", "name", type);
                pr = myConn.prepareStatement("insert into p2list (name, barcode, type, description) values(?,?,?,?)");
                pr.setString(1, name);
                pr.setString(2,  barcode);
                pr.setString(3, type_id);
                pr.setString(4, description);
                pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (pr != null) {
                pr.close();
            }
        }
    }

    public void addTypeCombobox(ComboBox<String> comboBox) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT Name FROM type");
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

    public void listTable(TableView tableView){
        try {
            Statement statement = null;
            ResultSet resultSet = null;

            //List to add items
            ObservableList<P2List> mainlists = FXCollections.observableArrayList();
            try {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM p2list ORDER BY id desc");
                while (resultSet.next()) {
                    P2List manList = new P2List();
                    manList.setId(resultSet.getString("id"));
                    manList.setName(resultSet.getString("name"));
                    manList.setBarcode(resultSet.getString("barcode"));
                    manList.setType(resultSet.getString("type"));
                    manList.setDescription(resultSet.getString("description"));

                    mainlists.add(manList);
                }
                tableView.setItems(mainlists);

            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                DaoUtils.close(statement, resultSet);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void delete(String id) throws SQLException {
        try (PreparedStatement pr = myConn.prepareStatement("delete from p2list where id=?")) {
            pr.setString(1, id);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateList(String id, String name, String barcode, String description) throws SQLException {
        try (PreparedStatement pr = myConn.prepareStatement("update p2list set name=?, barcode=?,  description=? where id=? ")) {

            pr.setString(1, name);
            pr.setString(2, barcode);
            pr.setString(3, description);
            pr.setString(4, id);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getComboBoxId(String tableName, String columnName, String name) throws SQLException {
        Statement st = null;
        ResultSet rs = null;
        try {
            String q = "SELECT Id FROM " + tableName + " WHERE " + columnName.trim() + "= '" + name.trim() + "'";
            st = myConn.createStatement();
            rs = st.executeQuery(q);
            if (rs.next()) {
                return rs.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DaoUtils.close(st,rs);
        }
        return null;

    }
}
