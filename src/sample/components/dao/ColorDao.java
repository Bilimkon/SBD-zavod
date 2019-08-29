package sample.components.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.components.models.Color;
import sample.dao.DaoUtils;
import sample.dao.database;

import java.sql.*;

public class ColorDao {

    private Connection myConn = null;

    public ColorDao(){
        try {
            myConn = database.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ColorTable(TableView tableView){
        try {
            Statement statement = null;
            ResultSet resultSet = null;

            //List to add items
            ObservableList<Color> colors = FXCollections.observableArrayList();
            try {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM color ORDER BY id ");
                while (resultSet.next()) {
                    Color color = new Color();
                    color.setId(resultSet.getString("id"));
                    color.setName(resultSet.getString("name"));
                    color.setDescription(resultSet.getString("description"));
                    colors.add(color);
                }
                tableView.setItems(colors);

            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                DaoUtils.close(statement, resultSet);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addColor(String name, String descripton) throws SQLException {
        try (PreparedStatement pr = myConn.prepareStatement("INSERT INTO color(name, description) values (?,?)")) {
            pr.setString(1, name);
            pr.setString(2, descripton);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void  colorUpdate(String  name, String  description, String id) throws SQLException {
        try (PreparedStatement pr = myConn.prepareStatement("Update color set name=?, description=? where id=?")) {
            pr.setString(1, name);
            pr.setString(2, description);
            pr.setString(3, id);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
