package sample.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import sample.Login;
import sample.components.sell.Utils.Utils;
import sample.model.Production3;
import sample.model.TableDsp;
import sample.model.core.manList;
import sample.model.core.product;
import sample.model.core.production2;
import sample.model.core.production3;
import sample.model.tablePaper;

import javax.swing.*;
import java.sql.*;


public class Main3Dao {

    String user_id = String.valueOf(Login.currentUser.getId());
    String apple = Utils.convertDateToStandardFormat(Utils.getCurrentDate());

    Connection myConn = null;


    public Main3Dao() {
        try {
            myConn = database.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tablePaper(TableView tableView) {
        Statement statement = null;
        ResultSet resultSet = null;
        //List to add items
        ObservableList<tablePaper> tablePapers = FXCollections.observableArrayList();
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM production2_ready ORDER BY id");
            while (resultSet.next()) {
                tablePaper tablePaper = new tablePaper();
                tablePaper.setId(resultSet.getString("id"));
                tablePaper.setBarcode(resultSet.getString("barcode"));
                tablePaper.setName(resultSet.getString("name"));
                tablePaper.setColor(resultSet.getString("color"));
                tablePaper.setType(resultSet.getString("type"));
                tablePaper.setQuantity(resultSet.getString("quantity"));
                tablePapers.add(tablePaper);
            }
            tableView.setItems(tablePapers);

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

    public void tableDsp(TableView tableView) {
        Statement statement = null;
        ResultSet resultSet = null;
        //List to add items
        ObservableList<TableDsp> tableDsps = FXCollections.observableArrayList();
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product_v ORDER BY id");
            while (resultSet.next()) {
                TableDsp tableDsp = new TableDsp();
                tableDsp.setId(resultSet.getString("id"));
                tableDsp.setBarcode(resultSet.getString("barcode"));
                tableDsp.setName(resultSet.getString("name"));
                tableDsp.setType(resultSet.getString("type"));
                tableDsp.setQuantity(resultSet.getString("quantity"));
                tableDsps.add(tableDsp);
            }
            tableView.setItems(tableDsps);
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

    public void tableProduction(TableView tableView) {
        Statement statement = null;
        ResultSet resultSet = null;
        //List to add items
        ObservableList<Production3> tablePapers = FXCollections.observableArrayList();
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM production3_v ORDER BY id desc");
            while (resultSet.next()) {
                Production3 production3 = new Production3();
                production3.setId(resultSet.getString("id"));
                production3.setBarcode(resultSet.getString("barcode"));
                production3.setName(resultSet.getString("name"));
                production3.setType(resultSet.getString("type"));
                production3.setColor(resultSet.getString("color"));
                production3.setQuantity(resultSet.getString("quantity"));
                production3.setpBarcode(resultSet.getString("pBarcode"));
                production3.setpName(resultSet.getString("pName"));
                production3.setpCost(resultSet.getString("pCost"));
                production3.setpQuantity(resultSet.getString("pQuantity"));
                production3.setpColor(resultSet.getString("pColor"));
                production3.setdBarcode(resultSet.getString("dBarcode"));
                production3.setdName(resultSet.getString("dName"));
                production3.setdQuantity(resultSet.getString("dQuantity"));
                production3.setCr_on(resultSet.getString("cr_on"));
                production3.setCr_by(resultSet.getString("cr_by"));
                tablePapers.add(production3);
            }
            tableView.setItems(tablePapers);
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

    /**
     * Getting list of products in the database
     */
    public product getDspBarcode(String barcode) throws SQLException {
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


    public production2 getPaperBarcode(String barcode) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            production2 product = null;
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM production2_ready where barcode=" + barcode);
            while (resultSet.next()) {
                product = new production2(
                        resultSet.getString("id"),
                        resultSet.getString("barcode"),
                        resultSet.getString("name"),
                        resultSet.getString("type"),
                        resultSet.getString("quantity"),
                        resultSet.getString("quantity"),
                        resultSet.getString("color"),
                        resultSet.getString("color"),
                        resultSet.getString("color"),
                        resultSet.getString("date"),
                        resultSet.getString("user_id"),
                        resultSet.getString("user_id"),
                        resultSet.getString("user_id")
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
     * Inserting selected dsps to the production3 table
     */
    public void addToSaleTable(String barcode, String name, String quantity) throws SQLException {
        PreparedStatement pr = null;
        try {
            pr = myConn.prepareStatement("INSERT INTO production3 (dbarcode, dname, dquantity) values (?,?,?)");
            pr.setString(1, barcode);
            pr.setString(2, name);
            pr.setString(3, quantity);
            pr.executeUpdate();
            //Ombordagi dsp maxsulotlar sonidan ayirish
            pr = myConn.prepareStatement("update product set quantity=(quantity-?) where barcode=?");
            pr.setString(1, quantity);
            pr.setString(2, barcode);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                pr.close();
            }
        }
    }

    /**
     * Inserting into production3 table (Paper)
     */

    public void addPaperProduction3Table(String barcode, String name, String type, String color, String quantity, String cost, String date) throws SQLException {
        try (PreparedStatement pr = myConn.prepareStatement("update production3 set  pbarcode=?, pname=?, pcolor=?, pquantity=?, pCost=?, cr_on=?, cr_by=?, type=? where id =" + getMaxId())) {
            pr.setString(1, barcode);
            pr.setString(2, name);
            pr.setString(3, color);
            pr.setString(4, quantity);
            pr.setString(5, cost);
            pr.setString(6, date);
            pr.setString(7, user_id);
            pr.setString(8, type);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePaperAmount(String barcode, String quantity) {
        try (PreparedStatement pr = myConn.prepareStatement("update production2_ready set quantity=(quantity-?) where barcode=" + barcode)) {
            //Ombordagi dsp maxsulotlar sonidan ayirish
            pr.setString(1, quantity);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // getting maxid from producton3 table
    private int getMaxId() {
        int resultId = 0;

        try (Statement st = myConn.createStatement(); ResultSet rs = st.executeQuery("SELECT id FROM production3 WHERE id =(SELECT MAX(id) FROM production3)")) {
            while (rs.next()) {
                resultId = rs.getInt("id");
            }
            return resultId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * update the ready laminant product to production3 table
     */
    public void setUpdateAction(String quantity, String name, String barcode, String type, String color, String id) throws SQLException {
        try (PreparedStatement ps = myConn.prepareStatement("UPDATE production3 set quantity=?, name=?, barcode=?, type=?, color=? where id=?")) {
            ps.setString(1, quantity);
            ps.setString(2, name);
            ps.setString(3, barcode);
            ps.setString(4, type);
            ps.setString(5, color);
            ps.setString(6, id);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public production3 getProductMain3Barcode(String id) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            production3 product = null;
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM production3 where id='" + id + "'");
            while (resultSet.next()) {
                product = new production3(
                        resultSet.getInt("id"),
                        resultSet.getString("barcode"),
                        resultSet.getString("name"),
                        resultSet.getString("type"),
                        resultSet.getString("color"),
                        resultSet.getString("quantity"),
                        resultSet.getString("pBarcode"),
                        resultSet.getString("pName"),
                        resultSet.getString("pCost"),
                        resultSet.getString("pColor"),
                        resultSet.getString("pQuantity"),
                        resultSet.getString("dBarcode"),
                        resultSet.getString("dName"),
                        resultSet.getString("dQuantity"),
                        resultSet.getString("cr_on"),
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

    public void insertSellTableMain3(String id, String barcode, String type_id, String name1, String quantity, String cost, String unit, String description) throws SQLException {

        PreparedStatement pr = null;
        PreparedStatement pr1 = null;
        try {
            if (checkDuplicate("sell", barcode) > 0) {
                pr = myConn.prepareStatement("Update sell set quantity=(quantity+?) where barcode=?");
                pr.setString(1, quantity);
                pr.setString(2, barcode);
                pr.executeUpdate();
                pr1 = myConn.prepareStatement("update  production3 set ready=? where id= ?");
                pr1.setString(1, "1");
                pr1.setString(2, id);
                pr1.execute();

                exchange_log(name1, barcode, type_id, quantity, "2-S", apple, user_id);
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
                pr1 = myConn.prepareStatement("update  production3 set ready=? where id= ?");
                pr1.setString(1, "1");
                pr1.setString(2, id);
                pr1.execute();

                exchange_log(name1, barcode, type_id, quantity, "2-S", apple, user_id);

            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Bunday barcodeli tavar savdoda mavjud barcodini o'zgartirib keyin savdoga o'tqazing!");
        } finally {
            if (pr != null) {
                pr.close();
            }
            if (pr1 != null) {
                pr1.close();
            }
        }
    }

    public void addListNameCombobox(ComboBox<String> comboBox) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT Name FROM manList");
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

    public manList selectListItem(String  name) {
        try {
            Statement statement = null;
            ResultSet resultSet = null;

            try {
                manList product = null;
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM manList where name='" + name + "'");
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

    public void revertAction1(String pbarcode, String pquantity) throws SQLException {

        try (PreparedStatement pr = myConn.prepareStatement("Update production2_ready set quantity=(quantity+?) where barcode=?")) {
            pr.setString(1, pquantity);
            pr.setString(2, pbarcode);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void revertAction2(String dbarcode, String dquantity) throws SQLException {
        try (PreparedStatement pr = myConn.prepareStatement("update product set quantity=(quantity+?) where barcode=" + dbarcode)) {
            pr.setString(1, dquantity);
            pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void revertAction3(String id) throws SQLException {
        try (PreparedStatement pr = myConn.prepareStatement("delete from  production3 where id=?")) {
            pr.setString(1, id);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertSellTableMain2( String barcode, String name1, String quantity, String type_id, String color) throws SQLException {

        PreparedStatement pr = null;
        try {
            if (checkDuplicate("sell", barcode) > 0) {
                pr = myConn.prepareStatement("update sell set quantity=(quantity+?) where barcode=?");
                pr.setString(1, quantity);
                pr.setString(2, barcode);
                pr.executeUpdate();
                //Updating product quantity in production2 table
                pr = myConn.prepareStatement("update production2_ready set quantity=(quantity-?) where barcode=?");
                pr.setString(1, quantity);
                pr.setString(2, barcode);
                pr.executeUpdate();

                exchange_log(name1, barcode, type_id, quantity, "2-S", apple, user_id);
            } else {
                pr = myConn.prepareStatement("insert into sell(barcode, type_id, name, quantity, cost, unit, date, cr_by, description) values(?,?,?,?,?,?,?,?,?)");
                pr.setString(1, barcode);
                pr.setString(2, type_id);
                pr.setString(3, name1);
                pr.setString(4, quantity);
                pr.setString(5, "23");
                pr.setString(6, "1");
                pr.setString(7, apple);
                pr.setString(8, user_id);
                pr.setString(9, color);
                pr.executeUpdate();

                //Updating product quantity in production2 table
                pr = myConn.prepareStatement("update production2_ready set quantity=(quantity-?) where barcode=?");
                pr.setString(1, quantity);
                pr.setString(2, barcode);
                pr.executeUpdate();

                exchange_log(name1, barcode, type_id, quantity, "2-S", apple, user_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                pr.close();
            }
        }
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

    public int getDSPAmount() throws SQLException {
        int resultId = 0;
        try (Statement st = myConn.createStatement(); ResultSet rs = st.executeQuery("select dquantity from production3  where id = (select max(id) max_id from production3 where  ready = 0)")) {
            while (rs.next()) {
                resultId = rs.getInt("dquantity");
            }
            return resultId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}
