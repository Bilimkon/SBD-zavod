package sample.components.sell.DAO;

import sample.components.sell.Core.Models.ReceiptCheck;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Humoyun Qo'rg'onov  SBD(Software Business Development)
 */
public class UtilsDao {
    private Connection myConn;

    public UtilsDao(){
        myConn= Database.getConnection();
    }

    public void clearAll() throws SQLException {
        PreparedStatement pt =null;
        try {
            pt = myConn.prepareStatement("DELETE FROM sellaction  ");
            pt.executeUpdate();
            pt = myConn.prepareStatement("DELETE FROM history");
            pt.executeUpdate();
            pt = myConn.prepareStatement("DELETE FROM product");
            pt.executeUpdate();
            pt = myConn.prepareStatement("DELETE FROM seller");
            pt.executeUpdate();
            pt = myConn.prepareStatement("DELETE FROM customer");
            pt.executeUpdate();
            pt = myConn.prepareStatement("DELETE FROM suplier");
            pt.executeUpdate();
            pt = myConn.prepareStatement("DELETE FROM type");
            pt.executeUpdate();
            JOptionPane.showMessageDialog(null, " Barcha malumotlar tozalandi", "Barcha malumotlar o'chirib tashlandi", JOptionPane.INFORMATION_MESSAGE);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (pt != null) {
                pt.close();
            }
        }
    }

    public void setPrinterName(String name) throws SQLException {
        PreparedStatement pt =null;
        try {
            pt = myConn.prepareStatement("UPDATE utils SET printerName=?");
            pt.setString(1, name);
            pt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (pt != null) {
                pt.close();
            }
        }
    }
    public int TotalSum() {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT total_cost FROM sellaction WHERE id = (SELECT max(id) AS 'last_item_id' FROM main.sellaction)");
            if (resultSet.next()) {
                return resultSet.getInt("total_cost");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public ArrayList<ReceiptCheck> PerProduct() throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<ReceiptCheck> receiptChecksList = new ArrayList<>();
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM history WHERE sell_action_id = (SELECT max(id) AS 'last_item_id' FROM main.sellaction)");
            while (resultSet.next()) {
                ReceiptCheck receiptCheck = new ReceiptCheck();
                receiptCheck.setId(resultSet.getInt("id"));
                receiptCheck.setName(resultSet.getString("product_name"));
                receiptCheck.setQuantity(resultSet.getInt("product_quantity"));
                receiptCheck.setPrice(resultSet.getDouble("cost"));
                receiptChecksList.add(receiptCheck);
            }
            return receiptChecksList;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return receiptChecksList;
    }

}
