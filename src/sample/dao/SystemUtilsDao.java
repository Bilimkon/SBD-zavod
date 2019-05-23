package sample.dao;

import java.sql.*;

public class SystemUtilsDao {

    Connection myConn = null;


    public SystemUtilsDao(){
        try {
            myConn = database.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void excellFolder(String path) throws SQLException {
        PreparedStatement pt =null;
        try {
            pt = myConn.prepareStatement("UPDATE utils SET filePath=?");
            pt.setString(1, path);
            pt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (pt != null) {
                pt.close();
            }
        }
    }
    public String ExcelFilePath() throws SQLException {

        Statement statement1 = null;
        ResultSet resultSet1 = null;

        try {
            statement1 = myConn.createStatement();

            resultSet1 = statement1.executeQuery("SELECT * FROM utils");

            if (resultSet1.next()) {
                return String.valueOf(resultSet1.getString("filePath"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(statement1,resultSet1);
        }
        return null;

    }
}
