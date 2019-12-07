package sample.dao;
import sample.Login;

import java.sql.*;

public class SystemUtilsDao {

    private Connection myConn = null;
    private String user_id = String.valueOf(Login.currentUser.getId());

    public SystemUtilsDao() {
        try {
            myConn = database.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excellFolder(String path) throws SQLException {
        try (PreparedStatement pt = myConn.prepareStatement("UPDATE user SET path=? where id=?")) {
            pt.setString(1, path);
            pt.setString(2, user_id);
            pt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String ExcelFilePath() throws SQLException {

        Statement statement1 = null;
        ResultSet resultSet1 = null;

        try {
            statement1 = myConn.createStatement();
            resultSet1 = statement1.executeQuery("SELECT * FROM user where id="+user_id+"");
            if (resultSet1.next()) {
                return String.valueOf(resultSet1.getString("path"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(statement1, resultSet1);
        }
        return null;

    }
}
