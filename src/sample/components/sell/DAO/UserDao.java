package sample.components.sell.DAO;

import sample.components.sell.Core.User;

import java.sql.*;

/**
 * Humoyun Qo'rg'onov  SBD(Software Business Development)
 */

public class UserDao {
    Connection myConn;

    public UserDao() {
        myConn = Database.getConnection();
    }

    public User getUser(String name, String password) throws SQLException {
        Statement statement=null;
        ResultSet  res = null;
        try {
            statement  = myConn.createStatement();
             res = statement.executeQuery("select * from main.seller where main.seller.username = '" + name + "' and main.seller.password = '" + password + "';");
            while (res.next()) {
                User u = new User(
                        res.getInt("id"),
                        res.getString("firstname"),
                        res.getString("lastname"),
                        res.getString("username"), null, res.getFloat("salary"), null, res.getString("admin"));
                u.setAdmin(false);
                if (res.getString("admin").equals("owner")) {
                    u.setAdmin(true);
                }
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (statement != null) {
                statement.close();
            }
            if (res != null) {
                res.close();
            }
        }
        return null;
    }

    public void closeConnection() throws SQLException {
        myConn.close();
    }
}
