package sample.components.sell.DAO;

import java.sql.Connection;


/**
 * Humoyun Qo'rg'onov  SBD(Software Business Development)
 */
public class SellerDao {
    private Connection myConn;

    public SellerDao(){
        try {
            myConn =Database.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
