package sample.dao;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Humoyun Qo'rg'onov  SBD(Software Business Development)
 */
public class database {

    static Connection myConn;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            myConn = DriverManager.getConnection("jdbc:mysql://192.168.1.177:3306/" + DaoUtils.tableName, "Humoyun", "Bilimkon@1540937");
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Serverga ulanishda xatolik");
            exc.printStackTrace();
        }
        return myConn;
    }
}
