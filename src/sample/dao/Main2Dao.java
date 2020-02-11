package sample.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import sample.Login;
import sample.components.sell.Utils.Utils;
import sample.model.TableA;
import sample.model.TableB;
import sample.model.core.manList;
import sample.model.core.product;
import sample.model.core.production2;

import java.math.BigDecimal;
import java.sql.*;

public class Main2Dao {
    private Connection myConn = null;
    String user_id = String.valueOf(Login.currentUser.getId());
    String apple = Utils.convertDateToStandardFormat(Utils.getCurrentDate());

    public Main2Dao() {
        try {
            myConn = database.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tableA(TableView tableView) {
        Statement statement = null;
        ResultSet resultSet = null;

        //List to add items
        ObservableList<TableA> tableAS = FXCollections.observableArrayList();
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product_v ORDER BY id");
            while (resultSet.next()) {
                TableA tableA = new TableA();
                tableA.setId(resultSet.getString("id"));
                tableA.setBarcode(resultSet.getString("barcode"));
                tableA.setName(resultSet.getString("name"));
                tableA.setType(resultSet.getString("type"));
                tableA.setQuantity(resultSet.getString("quantity"));

                tableAS.add(tableA);
            }
            tableView.setItems(tableAS);

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            try {
                DaoUtils.close(statement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void tableB(TableView tableView, String name, Label quantity) throws SQLException {
       try {


           ResultSet resultSet = null;
           PreparedStatement pr = null;
           Statement statement = null;
           ResultSet myRs = null;


           //List to add items
           ObservableList<TableB> tableBS = FXCollections.observableArrayList();

           if (!name.isEmpty()) {

               statement = myConn.createStatement();
               resultSet = statement.executeQuery("SELECT  * FROM production2_v  where name='" + name + "' order by id desc ");

               pr = myConn.prepareStatement("Select sum(p_quantity) total_quantity from production2_v where name='" + name + "' order by id desc");
               myRs = pr.executeQuery();

           } else if (name.equals("")) {

               statement = myConn.createStatement();
               resultSet = statement.executeQuery("SELECT  * FROM production2_v  ORDER BY id desc ");

               pr = myConn.prepareStatement("Select sum(p_quantity) total_quantity from production2_v  order by id desc");
               myRs = pr.executeQuery();
           }

           try {
               if (resultSet != null) {
                   while (resultSet.next()) {
                       TableB tableB = new TableB();
                       tableB.setId(resultSet.getString("id"));
                       tableB.setDate(resultSet.getString("date"));
                       tableB.setBarcode(resultSet.getString("barcode"));
                       tableB.setName(resultSet.getString("name"));
                       tableB.setQuantity(resultSet.getString("quantity"));
                       tableB.setP_quantity(resultSet.getString("p_quantity"));
                       tableB.setP_name(resultSet.getString("p_name"));
                       tableB.setP_barcode(resultSet.getString("p_barcode"));
                       tableB.setCr_by(resultSet.getString("user_id"));
                       tableB.setColor(resultSet.getString("color"));
                       tableB.setType(resultSet.getString("type"));
                       tableBS.add(tableB);
                   }
               }

               if (myRs != null && myRs.next()) {
                   quantity.setText(new BigDecimal(myRs.getDouble("total_quantity")).toPlainString() + " Dona");
               }

               tableView.setItems(tableBS);

           } catch (Exception exc) {
               exc.printStackTrace();
           } finally {
               try {
                   DaoUtils.close(null, resultSet);
                   if (pr != null) {
                       pr.close();
                   }
                   if (statement != null) {
                       statement.close();
                   }

                   if (myRs != null) {
                       myRs.close();
                   }
                   if (resultSet != null) {
                       resultSet.close();
                   }
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
       }catch (Exception e) {
           e.printStackTrace();
       }
    }

    /**
     * Getting list of products in the database
     */
    public product getPaperBarcode(String barcode) throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            product product = null;
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product where barcode=" + barcode);
            while (resultSet.next()) {
                product = new product(
                        resultSet.getInt("id"),
                        resultSet.getString("barcode"),
                        resultSet.getString("name"),
                        resultSet.getString("type"),
                        resultSet.getString("type_id"),
                        resultSet.getString("cost"),
                        resultSet.getString("quantity"),
                        resultSet.getString("unit"),
                        resultSet.getString("suplier_id"),
                        resultSet.getString("invoice_id"),
                        resultSet.getString("color"),
                        resultSet.getString("description"),
                        resultSet.getString("date_cr"),
                        resultSet.getString("cr_by")
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

    private int checkDuplicate(String table, String barcode) {
        int resultId = 0;
        try (Statement st = myConn.createStatement(); ResultSet rs = st.executeQuery("SELECT  EXISTS(SELECT * FROM " + table + " WHERE barcode = '" + barcode + "') id;")) {
            while (rs.next()) {
                resultId = rs.getInt("id");
            }
            return resultId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int checkDuplicateId(String table, String barcode) {
        int resultId = 0;
        try (Statement st = myConn.createStatement(); ResultSet rs = st.executeQuery("SELECT  EXISTS(SELECT * FROM " + table + " WHERE id = '" + barcode + "') idd;")) {
            while (rs.next()) {
                resultId = rs.getInt("idd");
            }
            return resultId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


    public void addToProduction2Table(String barcode, String name, String type, String quantity, String cost, String unit, String color, String p_name, String p_barcode, String date) throws SQLException {

        try (PreparedStatement pr = myConn.prepareStatement("insert into production2 (barcode, name, type, quantity, p_quantity, date, user_id, color, p_name, p_barcode) values(?,?,?,?,?,?,?,?,?,?)")) {
            pr.setString(1, barcode);
            pr.setString(2, name);
            pr.setString(3, type);
            pr.setString(4, quantity);
            pr.setString(5, "1");
            pr.setString(6, date);
            pr.setString(7, user_id);
            pr.setString(8, color);
            pr.setString(9, p_name);
            pr.setString(10, p_barcode);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUpdateAction(String quantity, String name, String barcode, String id) throws SQLException {
        try (PreparedStatement ps = myConn.prepareStatement("UPDATE production2 set p_quantity=? where id=?")) {
            ps.setString(1, quantity);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void subtractAction(String barcode, String quantity) throws SQLException {
        try (PreparedStatement ps = myConn.prepareStatement("update product set quantity=(quantity-?)  where barcode=" + barcode)) {
            ps.setString(1, quantity);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reverting products from production2
     * */
    public void revertAction(String barcode, String quantity) {
        try (PreparedStatement ps = myConn.prepareStatement("update product set quantity=(quantity+?)  where barcode='" + barcode+"' ")) {
            ps.setString(1, quantity);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord(String id) {
        try (PreparedStatement ps = myConn.prepareStatement("DELETE from production2 where id=?")) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public production2 getProductMain2Barcode(String id) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            production2 production2 = null;
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM production2 where id='" + id + "'");
            while (resultSet.next()) {
                production2 = new production2(
                        resultSet.getString("id"),
                        resultSet.getString("barcode"),
                        resultSet.getString("name"),
                        resultSet.getString("type"),
                        resultSet.getString("quantity"),
                        resultSet.getString("p_name"),
                        resultSet.getString("p_barcode"),
                        resultSet.getString("color"),
                        resultSet.getString("p_quantity"),
                        resultSet.getString("date"),
                        resultSet.getString("user_id"),
                        resultSet.getString("p_name"),
                        resultSet.getString("p_barcode")
                );
            }
            return production2;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(statement, resultSet);
        }
        return null;
    }

    public void addToPaper(String id, String barcode, String type_id, String name1, String quantity, String cost, String unit, String description) throws SQLException {

        PreparedStatement pr = null;
        try {
            if (checkDuplicate("paper", barcode) > 0) {
                pr = myConn.prepareStatement("update paper set quantity=(quantity+?) where barcode=?");
                pr.setString(1, quantity);
                pr.setString(2, barcode);
                int i = pr.executeUpdate();
                if( i > 0 ) {
                    //Updating product quantity in production2 table
                    pr = myConn.prepareStatement("update production2 set p_quantity=(p_quantity-?) where id=?");
                    pr.setString(1, quantity);
                    pr.setString(2, id);
                    pr.executeUpdate();
                }

                // exchange_log(name1, barcode, type_id, quantity, "1-2", apple, user_id);
            } else {
                pr = myConn.prepareStatement("insert into paper (barcode, name, type, quantity, cr_on) values(?,?,?,?,?)");
                pr.setString(1, barcode);
                pr.setString(2, name1);
                pr.setString(3, type_id);
                pr.setString(4, quantity);
                pr.setString(5, apple);
                int i = pr.executeUpdate();
                if( i > 0) {
                    //Updating product quantity in production2 table
                    pr = myConn.prepareStatement("update production2 set p_quantity=(p_quantity-?) where id=?");
                    pr.setString(1, quantity);
                    pr.setString(2, id);
                    pr.executeUpdate();
                }
                // exchange_log(name1, barcode, type_id, quantity, "1-2", apple, user_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                pr.close();
            }
        }
    }

    public void insertSellTableMain2(String id, String barcode, String type_id, String name1, String quantity, String cost, String unit, String description) throws SQLException {

        PreparedStatement pr = null;
        try {
            if (checkDuplicate("sell", barcode) > 0) {
                pr = myConn.prepareStatement("update sell set quantity=(quantity+?) where barcode=?");
                pr.setString(1, quantity);
                pr.setString(2, barcode);
                pr.executeUpdate();
                //Updating product quantity in production2 table
                pr = myConn.prepareStatement("update production2 set p_quantity=(p_quantity-?) where id=?");
                pr.setString(1, quantity);
                pr.setString(2, id);
                pr.executeUpdate();
                exchange_log(name1, barcode, type_id, quantity, "1-S", apple, user_id);
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
                pr.executeUpdate();

                //Updating product quantity in production2 table
                pr = myConn.prepareStatement("update production2 set p_quantity=(p_quantity-?) where id=?");
                pr.setString(1, quantity);
                pr.setString(2, id);
                pr.executeUpdate();
                exchange_log(name1, barcode, type_id, quantity, "1-S", apple, user_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                pr.close();
            }
        }
    }

    public void insertToPHarajat(String barcode, String type_id, String name1, String quantity, String cost, String unit, String description) throws SQLException {

        PreparedStatement pr = null;
        try {
                pr = myConn.prepareStatement("update product set quantity=(quantity-?) where barcode=?");
                pr.setString(1, quantity);
                pr.setString(2, barcode);
                pr.executeUpdate();
                //Updating product quantity in production2 table
                pr = myConn.prepareStatement("insert into  production2_xarajat(barcode, name, type, quantity, cr_on, cr_by) values(?,?,?,?,?,?)");
                pr.setString(1, barcode);
                pr.setString(2, name1);
                pr.setString(3, type_id);
                pr.setString(4, quantity);
                pr.setString(5, apple);
                pr.setString(6, user_id);
                pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                pr.close();
            }
        }
    }
    public void addListNameCombobox(ComboBox<String> comboBox) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT Name FROM p2list");
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

    public manList selectListItem(String name) {
        ProductDao productDao = new ProductDao();
        try {
            Statement statement = null;
            ResultSet resultSet = null;
            String type_id = productDao.getComboBoxId("p2list", "name", name);

            try {
                manList product = null;
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM p2list where id="+type_id);
                while (resultSet.next()) {
                    product = new manList(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getString("barcode"),
                            resultSet.getString("type")
                    );
                    return  product;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DaoUtils.close(statement, resultSet);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public void insertToHistory(String id, String barcode, String name, String type, String quantity, String p_name, String p_barcode, String color, String p_quantity, String date, String user_id) throws SQLException {

        if (checkDuplicateId("production2_tarix", id) > 0) {
            try (
                    PreparedStatement pr = myConn.prepareStatement("update  production2_tarix set p_quantity=? where id=?")
            ) {
                pr.setString(1, p_quantity);
                pr.setString(2, id);
                pr.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            try (
                    PreparedStatement pr = myConn.prepareStatement("insert into production2_tarix (id, barcode, name, type, quantity, p_name, p_barcode, color, p_quantity, date, user_id) values(?,?,?,?,?,?,?,?,?,?,?)")
            ) {
                pr.setString(1, id);
                pr.setString(2, barcode);
                pr.setString(3, name);
                pr.setString(4, type);
                pr.setString(5, quantity);
                pr.setString(6, p_name);
                pr.setString(7, p_barcode);
                pr.setString(8, color);
                pr.setString(9, p_quantity);
                pr.setString(10, date);
                pr.setString(11, user_id);
                pr.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private   void exchange_log(String name, String barcode, String type, String quantity, String comment, String cr_on, String cr_by) throws SQLException {

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

    public void getP2OmborSelectName(ComboBox<String> comboBox) throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT name FROM production2_v order by id desc");
            while (resultSet.next()) {
                comboBox.getItems().addAll(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(statement, resultSet);
        }
    }
}
