package sample.components.sell.DAO;

import sample.components.sell.Core.CheckSums;
import sample.components.sell.Core.Models.ReceiptCheck;
import sample.dao.database;

import java.sql.*;
import java.util.ArrayList;

/**
 * Humoyun Qo'rg'onov  SBD(Software Business Development)
 */
public class UtilsDao {
    private Connection myConn;

    public UtilsDao(){
        myConn= database.getConnection();
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

    public String getSellerName() throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT cr_by FROM sale_balance_v WHERE id = (SELECT max(id) FROM sale_balance_v)");
            if (resultSet.next()) {
                return resultSet.getString("cr_by");
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


    public ArrayList<ReceiptCheck> PerProduct() throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<ReceiptCheck> receiptChecksList = new ArrayList<>();
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM history_v WHERE sellAction_id = (SELECT max(id)  FROM sellaction)");
            while (resultSet.next()) {
                ReceiptCheck receiptCheck = new ReceiptCheck();
                receiptCheck.setId(resultSet.getInt("id"));
                receiptCheck.setBarcode(resultSet.getString("barcode"));
                receiptCheck.setName(resultSet.getString("name"));
                receiptCheck.setQuantity(resultSet.getInt("quantity"));
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

    public ArrayList<ReceiptCheck> PerProductRevert(String id) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<ReceiptCheck> receiptChecksList = new ArrayList<>();
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM history_v WHERE sellAction_id = '"+id+"' ");
            while (resultSet.next()) {
                ReceiptCheck receiptCheck = new ReceiptCheck();
                receiptCheck.setId(resultSet.getInt("id"));
                receiptCheck.setBarcode(resultSet.getString("barcode"));
                receiptCheck.setName(resultSet.getString("name"));
                receiptCheck.setQuantity(resultSet.getInt("quantity"));
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


    public CheckSums getCheckSums() throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            CheckSums checkSums = null;
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM sellAction_v WHERE id = (SELECT max(id) FROM sellAction_v)");
            if (resultSet.next()) {
                checkSums = new CheckSums(
                        resultSet.getString("sum"),
                        resultSet.getString("dollar"),
                        resultSet.getString("hr"),
                        resultSet.getString("psum"),
                        resultSet.getString("pdollar"),
                        resultSet.getString("phr"));
            }
            return checkSums;
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

    public int getCheckQuantity() throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            int quantity=0;
            CheckSums checkSums = null;
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT sum(quantity) total_quantity FROM history_v WHERE sellAction_id = (SELECT max(id) FROM sellAction_v)");
            if (resultSet.next()) {
               quantity = resultSet.getInt("total_quantity");
            }
            return quantity;
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
        return 0;
    }

    public CheckSums getCheckSumsWithId(String id) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            CheckSums checkSums = null;
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM sellAction_v WHERE id ='"+id+"'");
            if (resultSet.next()) {
                checkSums = new CheckSums(
                        resultSet.getString("sum"),
                        resultSet.getString("dollar"),
                        resultSet.getString("hr"),
                        resultSet.getString("psum"),
                        resultSet.getString("pdollar"),
                        resultSet.getString("phr"));
            }
            return checkSums;
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

}
