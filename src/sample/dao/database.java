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

            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DaoUtils.tableName, "java", "Bilimkon");

        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Databasega ulanishda hatolik");
        }
        return myConn;
    }
}
