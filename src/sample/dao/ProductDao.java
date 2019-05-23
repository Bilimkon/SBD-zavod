package sample.dao;

import javafx.scene.control.ComboBox;
import sample.utils.utils;

import java.sql.*;

public class ProductDao {

    private Connection myConn;
    private String apple = String.valueOf(utils.getCurrentDate());

    public ProductDao() throws Exception {
        myConn = database.getConnection();
    }

    public void addProduct(String barcode, String name, String type, String cost, String quantity, String unit, String weight, String description, String suplier, String color, String height, String width) throws SQLException {

        String type_id = getComboBoxId("Type", "name", type);
        String suplier_id = getComboBoxId("suplier", "firstname", suplier);
        String unit_id = "1";
        if (unit.equals("Kg")) {
            unit_id = "2";
        } else if (unit.equals("Dona")) {
            unit_id = "1";
        } else if (unit.equals("Rulon")) {
            unit_id = "3";
        } else if (unit.equals("Litr")) {
            unit_id = "4";
        }
        PreparedStatement pr = null;

        try {
            pr = myConn.prepareStatement("INSERT INTO product(barcode, name, " +
                    "type, type_id, cost,quantity, weight, cr_by, date_cr," +
                    " unit, description, suplier_id, color, height, width) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pr.setString(1, barcode);
            pr.setString(2, name);
            pr.setString(3, type);
            pr.setString(4, type_id);
            pr.setString(5, cost);
            pr.setString(6, quantity);
            pr.setString(7, weight);
            pr.setString(8, "1");
            pr.setString(9, apple);
            pr.setString(10, unit_id);
            pr.setString(11, description);
            pr.setString(12, suplier_id);
            pr.setString(13, color);
            pr.setString(14, height);
            pr.setString(15, width);

            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                pr.close();
            }
        }
    }

    public void updateProduct(String id, String barcode, String name, String quantity,
                              String cost, String color, String height,
                              String weight, String width, String description) throws SQLException {
        PreparedStatement pr = null;
        try {
            pr = myConn.prepareStatement("update product set barcode=?, name=?, quantity=?, cost=?, color=?," +
                    " height=?, weight=?,  width=?, description=? " +
                    "where id=" + id);
            pr.setString(1, barcode);
            pr.setString(2, name);
            pr.setString(3, quantity);
            pr.setString(4, cost);
            pr.setString(5, color);
            pr.setString(6, height);
            pr.setString(7, weight);
            pr.setString(8, width);
            pr.setString(9, description);
            pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                pr.close();
            }
        }
    }

    public void deleteProduct(String id) throws SQLException {
        PreparedStatement pr = null;

        try{
            pr= myConn.prepareStatement("DELETE  FROM product where  id="+id);
            pr.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (pr != null) {
                pr.close();
            }
        }

    }

    public void addTypeCombobox(ComboBox<String> comboBox) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT Name FROM type");
            while (resultSet.next()) {  // loop
                // Now add the comboBox addAll statement
                comboBox.getItems().addAll(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(statement, resultSet);
        }
    }

    public  void addSuplierCombobox(ComboBox<String> comboBox) throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT firstName FROM suplier");
            while (resultSet.next()) {  // loop
                // Now add the comboBox addAll statement
                comboBox.getItems().addAll(resultSet.getString("firstname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(statement, resultSet);
        }
    }

    private String getComboBoxId(String tableName, String columnName, String name) {
        try {
            String q = "SELECT Id FROM " + tableName + " WHERE " + columnName.trim() + "= '" + name.trim() + "'";
            Statement st = myConn.createStatement();
            ResultSet rs = st.executeQuery(q);
            if (rs.next()) {
                return rs.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
