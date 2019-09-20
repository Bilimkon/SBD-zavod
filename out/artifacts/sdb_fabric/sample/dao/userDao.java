package sample.dao;

import sample.model.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class userDao {

    private Connection myConn;

    public userDao() {
        myConn = database.getConnection();
    }

    public User getUser(String name, String password) throws SQLException {
        Statement statement = null;
        ResultSet res = null;
        try {
            statement = myConn.createStatement();
            res = statement.executeQuery("select * from sbd_factory.user where user.username = '" + name + "' and user.password = '" + password + "';");
            while (res.next()) {
                User u = new User(
                        res.getInt("id"),
                        res.getString("username"),
                        res.getString("firstname"),
                        res.getString("lastname"),
                        res.getString("password"),
                        res.getInt("userType"),
                        res.getString("phone"));
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(statement, res);
        }
        return null;
    }

    public void closeConnection() throws SQLException {
        myConn.close();
    }
}
