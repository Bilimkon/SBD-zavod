package sample.components.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import sample.components.models.ManList;
import sample.dao.DaoUtils;
import sample.dao.ProductDao;
import sample.dao.database;

import java.sql.*;

public class ListDao {

    Connection myConn = null;
    ProductDao productDao = new ProductDao();

    public ListDao(){
        try {
            myConn = database.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void listTable(TableView tableView){
        try {
            Statement statement = null;
            ResultSet resultSet = null;

            //List to add items
            ObservableList<ManList> mainlists = FXCollections.observableArrayList();
            try {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM ManList_v ORDER BY id desc");
                while (resultSet.next()) {
                    ManList manList = new ManList();
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

    public void addList(String name, String barcode, String type, String description) throws SQLException {
        PreparedStatement pr = null;
        try{
            String type_id = productDao.getComboBoxId("type", "name", type);
            pr = myConn.prepareStatement("INSERT INTO ManList(name, barcode, type, description) values (?,?,?,?)");
            pr.setString(1, name);
            pr.setString(2, barcode);
            pr.setString(3, type_id);
            pr.setString(4, description);
            pr.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (pr != null) {
                pr.close();
            }
        }
    }

    public void deleteList(String id) throws SQLException {
        try (PreparedStatement pr = myConn.prepareStatement("delete from manList where id=?")) {

            pr.setString(1, id);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateList(String id, String name, String barcode, String description) throws SQLException {
        try (PreparedStatement pr = myConn.prepareStatement("update ManList set name=?, barcode=?, description=? where id=? ")) {
            pr.setString(1, name);
            pr.setString(2, barcode);
            pr.setString(3, description);
            pr.setString(4, id);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
