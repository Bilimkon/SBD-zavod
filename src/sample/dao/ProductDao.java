package sample.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import sample.Login;
import sample.components.dao.TypeDao;
import sample.components.sell.Utils.Utils;
import sample.model.Dsp2;
import sample.model.Product;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class ProductDao {

    String user_id = String.valueOf(Login.currentUser.getId());
    private Connection myConn;
    String apple = Utils.convertDateToStandardFormat(Utils.getCurrentDate());
    TypeDao typeDao = new TypeDao();

    public ProductDao() {
        myConn = database.getConnection();
    }

    public void initializeTable(TableView tableView, String type, String name, Label quantity, Label cost) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSet myRs = null;
        PreparedStatement pr = null;
        ObservableList<Product> products = FXCollections.observableArrayList();
        try {

            if(!type.isEmpty() && !name.isEmpty()){
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM product_v  where type='"+type+"' and name='"+name+"' order by quantity desc");

                pr = myConn.prepareStatement("select sum(quantity) as total_quantity, sum(cost) as total_cost from product_v where type='"+type+"' and name='"+name+"' order by quantity desc");
                myRs =pr.executeQuery();
            } else if(!type.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM product_v  where type='"+type+"' order by quantity desc");

                pr = myConn.prepareStatement("select sum(quantity) as total_quantity, sum(cost*quantity) as total_cost FROM product_v  where type='"+type+"' order by quantity desc");
                myRs =pr.executeQuery();
            } else if(!name.isEmpty() && type.equals("")){
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM product_v  where  name='"+name+"' order by quantity desc");

                pr = myConn.prepareStatement("select sum(quantity) as total_quantity, sum(cost*quantity) as total_cost FROM product_v  where  name='"+name+"' order by quantity desc");
                myRs =pr.executeQuery();
            }
            else {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM product_v ORDER BY id");

                pr = myConn.prepareStatement("select sum(quantity) as total_quantity, sum(cost*quantity) as total_cost FROM product_v ORDER BY id");
                myRs =pr.executeQuery();
            }

                while (resultSet.next()) {

                    Product product = new Product();
                    product.setId(resultSet.getString("id"));
                    product.setInvoice(resultSet.getString("invoice"));
                    product.setUnit(resultSet.getString("unit"));
                    product.setBarcode(resultSet.getString("barcode"));
                    product.setName(resultSet.getString("name"));
                    product.setType(resultSet.getString("type"));
                    product.setCost(resultSet.getString("cost"));
                    product.setQuantity(resultSet.getString("quantity"));
                    product.setSuplier(resultSet.getString("suplier"));
                    product.setDate_cr(resultSet.getString("date_cr"));
                    product.setCr_by(resultSet.getString("user"));
                    product.setDescription(resultSet.getString("description"));
                    product.setColor(resultSet.getString("color"));

                    products.addAll(product);
                }
            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
            symbols.setGroupingSeparator(' ');
            DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);

                while (myRs.next()){
                    quantity.setText(formatter.format(myRs.getDouble("total_quantity")));
                    cost.setText(formatter.format(myRs.getDouble("total_cost")));
                }

            tableView.setItems(products);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(statement, resultSet);
            if (myRs != null) {
                myRs.close();
            }
            if (pr != null) {
                pr.close();
            }
        }
    }

    /**
     * Adding product
     */
    public void addProduct(String invoice, String barcode, String name, String type, String cost, String quantity, String unit, String description, String suplier, String color) throws SQLException {

        String type_id = getComboBoxId("Type", "name", type);
        String suplier_id = getComboBoxId("person", "companyName", suplier);
        String invoice_id = getComboBoxId("invoice", "name", invoice);
        String color_id = getComboBoxId("color", "name", color);

        PreparedStatement pr = null;
        try {
            if (checkDuplicate("product", barcode) > 0) {
               pr = myConn.prepareStatement("update product set quantity=(quantity+?) where barcode="+barcode);
               pr.setString(1, quantity);
               pr.executeUpdate();

               addProductHistory("Yangilash",invoice_id, barcode, name, type, type_id, cost, quantity, typeDao.typeMaker(unit), description, suplier_id, color_id);
            } else {
                pr = myConn.prepareStatement("INSERT INTO product(invoice_id, barcode, name, " +
                        "type, type_id, cost,quantity, cr_by, date_cr," +
                        " unit, description, suplier_id, color) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pr.setString(1, invoice_id);
                pr.setString(2, barcode);
                pr.setString(3, name);
                pr.setString(4, type);
                pr.setString(5, type_id);
                pr.setString(6, cost);
                pr.setString(7, quantity);
                pr.setString(8, user_id);
                pr.setString(9, apple);
                pr.setString(10, typeDao.typeMaker(unit));
                pr.setString(11, description);
                pr.setString(12, suplier_id);
                pr.setString(13, color_id);
                pr.executeUpdate();

                addProductHistory("Yangi maxsulot qo'shish",invoice_id, barcode, name, type, type_id, cost, quantity, typeDao.typeMaker(unit), description, suplier_id, color_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                pr.close();
            }
        }
    }

    /**
     * Inserting into product_history
     * */
      private void addProductHistory( String operType, String invoice, String barcode, String name, String type, String type_id, String cost, String quantity, String unit, String description, String suplier_id, String color_id) throws SQLException {

          try (PreparedStatement pr = myConn.prepareStatement("INSERT INTO product_h(oper_type, invoice_id, barcode, name, " +
                  "type, type_id, cost,quantity, cr_by, date_cr," +
                  " unit, description, suplier_id, color) " +
                  "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)")) {
              pr.setString(1, operType);
              pr.setString(2, invoice);
              pr.setString(3, barcode);
              pr.setString(4, name);
              pr.setString(5, type);
              pr.setString(6, type_id);
              pr.setString(7, cost);
              pr.setString(8, quantity);
              pr.setString(9, user_id);
              pr.setString(10, apple);
              pr.setString(11, typeDao.typeMaker(unit));
              pr.setString(12, description);
              pr.setString(13, suplier_id);
              pr.setString(14, color_id);
              pr.executeUpdate();
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
    /**
     * Updating product
     */
    public void updateProduct(String id, String barcode, String name, String quantity,
                              String cost, String color, String description) throws SQLException {
        String color_id = getComboBoxId("color", "name", color);
        try (PreparedStatement pr = myConn.prepareStatement("update product set barcode=?, name=?, quantity=?, cost=?, color=?," +
                " description=? " +
                "where id=?")) {
            pr.setString(1, barcode);
            pr.setString(2, name);
            pr.setString(3, quantity);
            pr.setString(4, cost);
            pr.setString(5, color_id);
            pr.setString(6, description);
            pr.setString(7, id);
            pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deleting product
     */
    public void deleteProduct(String id) throws SQLException {

        try (PreparedStatement pr = myConn.prepareStatement("DELETE  FROM product where  id=" + id)) {
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Selectring Combobox
     */
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

    public void colorComboBox(ComboBox<String> comboBox) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT Name FROM Color");
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


    public void addSuplierCombobox(ComboBox<String> comboBox) throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT companyName FROM person order by companyName");
            while (resultSet.next()) {  // loop
                // Now add the comboBox addAll statement
                comboBox.getItems().addAll(resultSet.getString("companyName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(statement, resultSet);
        }
    }

    public void addWhoCombobox(ComboBox<String> comboBox) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT companyName FROM person order by companyName");
            while (resultSet.next()) {  // loop
                // Now add the comboBox addAll statement
                comboBox.getItems().addAll(resultSet.getString("companyName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(statement, resultSet);
        }
    }


    public void addInvoceCombobox(ComboBox<String> comboBox) {

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM invoice order by id desc limit 100");
            while (resultSet.next()) {  // loop
                // Now add the comboBox addAll statement
                comboBox.getItems().addAll(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DaoUtils.close(statement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void addProductName(ComboBox<String> comboBox) {

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT name FROM product order by id desc");
            while (resultSet.next()) {  // loop
                // Now add the comboBox addAll statement
                comboBox.getItems().addAll(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DaoUtils.close(statement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * End
     */

    public String getComboBoxId(String tableName, String columnName, String name) throws SQLException {
        Statement st = null;
        ResultSet rs = null;
        try {
            String q = "SELECT Id FROM " + tableName + " WHERE " + columnName.trim() + "= '" + name.trim() + "'";
             st = myConn.createStatement();
             rs = st.executeQuery(q);
            if (rs.next()) {
                return rs.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DaoUtils.close(st,rs);
        }
        return null;

    }

    public String getUnitType(String name) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String s = "SELECT unit FROM type_v WHERE name='" + name + "'";
            statement = myConn.createStatement();
            resultSet = statement.executeQuery(s);
            if (resultSet.next()) {
                return resultSet.getString("unit");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(statement, resultSet);
        }
        return null;
    }

    public String getCompanyName(String name) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String s = "SELECT company FROM invoice WHERE name='" + name + "'";
            statement = myConn.createStatement();
            resultSet = statement.executeQuery(s);
            if (resultSet.next()) {
                return resultSet.getString("company");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(statement, resultSet);
        }
        return null;
    }
    public String getSelectName(String name) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            String s = "SELECT name FROM product WHERE name='" + name + "'";
            statement = myConn.createStatement();
            resultSet = statement.executeQuery(s);
            if (resultSet.next()) {
                return resultSet.getString("company");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(statement, resultSet);
        }
        return null;
    }

    /**
     * Getting dsp2 from ombor
     */
    public Dsp2 getDSP2Barcode(String barcode) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Dsp2 product = null;
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product where barcode='" + barcode + "'");
            while (resultSet.next()) {
                product = new Dsp2(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("type_id"),
                        resultSet.getString("barcode"),
                        resultSet.getString("quantity"),
                        resultSet.getString("cost"),
                        resultSet.getString("unit"),
                        resultSet.getString("color")
                );
            }
            return product;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(statement, resultSet);
        }
        return null;
    }

    /**
     * adding product to sale from ombor
     */
    public void insertSellTableDsp2(String barcode, String type_id, String name1, String quantity, String cost, String unit, String description) throws SQLException {

        PreparedStatement pr = null;
        PreparedStatement pr1 = null;
        try {
            if (checkDuplicate("sell", barcode) > 0) {
                pr = myConn.prepareStatement("Update sell set quantity=(quantity+?) where barcode=?");
                pr.setString(1, quantity);
                pr.setString(2, barcode);
                pr.executeUpdate();
                pr1 = myConn.prepareStatement("Update product set quantity=(quantity-?) where barcode=?");
                pr1.setString(1, quantity);
                pr1.setString(2, barcode);
                pr1.execute();

                exchange_log(name1, barcode, type_id, quantity, "O-S", apple, user_id);
            } else {
                pr = myConn.prepareStatement("insert into sell(barcode, type_id, name, quantity, cost, unit, date, cr_by, description) values(?,?,?,?,?,?,?,?,?)");
                pr.setString(1, barcode);
                pr.setString(2, type_id);
                pr.setString(3, name1);
                pr.setString(4, quantity);
                pr.setString(5, cost);
                pr.setString(6, unit);
                pr.setString(7, apple);
                pr.setString(8, user_id);
                pr.setString(9, description);
                pr.execute();

                pr1 = myConn.prepareStatement("Update product set quantity=(quantity-?) where barcode=?");
                pr1.setString(1, quantity);
                pr1.setString(2, barcode);
                pr1.execute();

                exchange_log(name1, barcode, type_id, quantity, "O-S", apple, user_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                pr.close();
            }
            if (pr1 != null) {
                pr1.close();
            }
        }
    }

    /**
     * End
     */

    //Inserting into database to with excell sheet
    public void InsertRowInDB(String barcode, String name, String type, String type_id, String cost, String quantity, String unit, String suplier_id, String invoice_id, String color, String desctiprion) throws SQLException {
        PreparedStatement pr = null;
        try {
            String sql = "INSERT INTO product (barcode, name, type, type_id, cost, quantity, unit, suplier_id, invoice_id, color, description, date_cr, cr_by) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pr = myConn.prepareStatement(sql);
            pr.setString(1, barcode);
            pr.setString(2, name);
            pr.setString(3, type);
            pr.setString(4, type_id);
            pr.setString(5, cost);
            pr.setString(6, quantity);
            pr.setString(7, unit);
            pr.setString(8, suplier_id);
            pr.setString(9, invoice_id);
            pr.setString(10, color);
            pr.setString(11, desctiprion);
            pr.setString(12, apple);
            pr.setString(13, user_id);
            pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                pr.close();
            }
        }
    }

    private int checkDuplicate(String table, String barcode) throws SQLException {
        int resultId = 0;
        Statement st = null;
        ResultSet rs = null;
        try {
                 st = myConn.createStatement();
                 rs = st.executeQuery("SELECT  EXISTS(SELECT * FROM " + table + " WHERE barcode = '" + barcode + "') id;");
            {
                while (rs.next()) {
                    resultId = rs.getInt("id");
                }
                return resultId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            DaoUtils.close(st,rs);
        }
        return -1;
    }

    private void exchange_log(String name, String barcode, String type, String quantity, String comment, String cr_on, String cr_by) throws SQLException {

        try (PreparedStatement pr = myConn.prepareStatement("insert into exchange_log ( name, barcode, type, quantity, comment, cr_on, cr_by) values (?,?,?,?,?,?,?)")) {
            pr.setString(1, name);
            pr.setString(2, barcode);
            pr.setString(3, type);
            pr.setString(4, quantity);
            pr.setString(5, comment);
            pr.setString(6, cr_on);
            pr.setString(7, cr_by);
            pr.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
