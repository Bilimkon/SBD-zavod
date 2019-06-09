package sample.components.sell.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static sample.components.sell.DAO.Database.myConn;

public class printer {


    public String printerName() throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = myConn.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM utils");

            if (resultSet.next()) {
                return String.valueOf(resultSet.getString("printerName"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return null;
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
            if (statement1 != null) {
                statement1.close();
            }
            if (resultSet1 != null) {
                resultSet1.close();
            }
        }
        return null;

    }
}
