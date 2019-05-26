package sample.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.model.UserTable;

import java.sql.*;

public class AdminDao {
    private Connection myConn = null;

    public AdminDao(){
        try{
            myConn = database.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void  userTable(TableView tableView){

        Statement statement = null;
        ResultSet resultSet = null;

        //List to add items
        ObservableList<UserTable> userTables = FXCollections.observableArrayList();
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user_v ORDER BY id");
            while (resultSet.next()) {
                UserTable userTable = new UserTable();
                userTable.setId(resultSet.getString("id"));
                userTable.setUsername(resultSet.getString("username"));
                userTable.setFisrtname(resultSet.getString("firstname"));
                userTable.setLastname(resultSet.getString("lastname"));
                userTable.setPhone(resultSet.getString("phone"));
                userTable.setPassword(resultSet.getString("password"));
                userTable.setUserType(resultSet.getString("userType"));

                userTables.add(userTable);
            }
            tableView.setItems(userTables);

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
    public void addUser( String username,String firstname, String lastname, String phone, String password, String userType){
        PreparedStatement pr = null;

        String unit_id = "1";
        if (userType.equals("Ombor")) {
            unit_id = "1";
        } else if (userType.equals("1-ish/ch")) {
            unit_id = "2";
        } else if (userType.equals("2-ish/ch")) {
            unit_id = "3";
        } else if (userType.equals("Savdo")) {
            unit_id = "4";
        } else if (userType.equals("Admin")) {
            unit_id = "5";
        }
        try {
            pr = myConn.prepareStatement("INSERT INTO user_v(username, firstname, lastname, password, userType, phone) VALUES (?,?,?,?,?,?)");
            pr.setString(1, username);
            pr.setString(2, firstname);
            pr.setString(3, lastname);
            pr.setString(4, password);
            pr.setString(5, unit_id);
            pr.setString(6, phone);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                try {
                    pr.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateUser(String id, String username,String firstname, String lastname, String phone, String password, String userType){
        PreparedStatement pr = null;

        String unit_id = "1";
        if (userType.equals("Ombor")) {
            unit_id = "1";
        } else if (userType.equals("1-ish/ch")) {
            unit_id = "2";
        } else if (userType.equals("2-ish/ch")) {
            unit_id = "3";
        } else if (userType.equals("Savdo")) {
            unit_id = "4";
        }else if (userType.equals("Admin")) {
            unit_id = "5";
        }
        try {
            pr = myConn.prepareStatement("update  user set username=?, firstname=?, lastname=?, password=?, userType=?, phone=? where id="+id);
            pr.setString(1, username);
            pr.setString(2, firstname);
            pr.setString(3, lastname);
            pr.setString(4, password);
            pr.setString(5, unit_id);
            pr.setString(6, phone);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                try {
                    pr.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteUser(String id){
        PreparedStatement pr = null;
        try{
            pr = myConn.prepareStatement("DELETE FROM user_v WHERE id=?");
            pr.setString(1,id);
            pr.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (pr != null) {
                try {
                    pr.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
