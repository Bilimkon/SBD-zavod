package sample.components.sell.DAO;

import sample.components.sell.Utils.Utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Humoyun Qo'rg'onov  SBD(Software Business Development)
 */
public class Database {

    static Connection myConn;


    public static Connection getConnection() {
        try {
            myConn = DriverManager.getConnection("jdbc:sqlite:"+DaoUtils.tableName);
        } catch (Exception exc) {
            Utils.ErrorAlert("Xatolik", "Xatolik", "Xatolik" + exc);
        }
        return myConn;
    }
}
