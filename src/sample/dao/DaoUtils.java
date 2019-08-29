package sample.dao;


import sample.components.sell.Utils.Utils;

import java.sql.*;

/**
 * Humoyun Qo'rg'onov  SBD(Software Business Development)
 */
public class DaoUtils {

    Connection myConn = null;

    public DaoUtils(){
        myConn = database.getConnection();
    }
    public static String tableName = "sbd_factory?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static void close(Connection myConn, Statement myStmt, ResultSet myRs)
            throws SQLException {
        if (myRs != null) {
            myRs.close();
        }
        if (myStmt != null) {
            myStmt.close();
        }
        if (myConn != null) {
            myConn.close();
        }
    }

    public static void close(Statement myStmt, ResultSet myRs) throws SQLException {
        close(null, myStmt, myRs);
    }

    public  void log(String module, String type, String cost, String cr_by, String comment) throws SQLException {

        String apple = Utils.convertDateToStandardFormat(Utils.getCurrentDate());
        try (PreparedStatement pr = myConn.prepareStatement("insert into log ( module, type, cost, cr_by, date , comment) values (?,?,?,?,?,?)")) {
            pr.setString(1, module);
            pr.setString(2, type);
            pr.setString(3, cost);
            pr.setString(4, cr_by);
            pr.setString(5, apple);
            pr.setString(6, comment);
            pr.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
