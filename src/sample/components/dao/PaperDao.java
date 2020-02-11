package sample.components.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.Login;
import sample.components.sell.Utils.Utils;
import sample.dao.DaoUtils;
import sample.dao.database;
import sample.model.tablePaper;

import java.sql.*;

public class PaperDao {

    String user_id = String.valueOf(Login.currentUser.getId());
    String apple = Utils.convertDateToStandardFormat(Utils.getCurrentDate());

    Connection myConn = null;


    public PaperDao() {
        try {
            myConn = database.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void AddTablePaper(TableView tableView) {
        Statement statement = null;
        ResultSet resultSet = null;
        //List to add items
        ObservableList<tablePaper> tablePapers = FXCollections.observableArrayList();
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Paper ORDER BY name");
            while (resultSet.next()) {
                tablePaper tablePaper = new tablePaper();
                tablePaper.setId(resultSet.getString("id"));
                tablePaper.setBarcode(resultSet.getString("barcode"));
                tablePaper.setName(resultSet.getString("name"));
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

    public void transferProduction3(String name1, String barcode, String type, String quantity) throws SQLException {
        PreparedStatement pr = null;
        try {
            if (checkDuplicate("production2_ready", barcode) > 0) {
                pr = myConn.prepareStatement("update production2_ready set quantity=(quantity+?) where barcode=?");
                pr.setString(1, quantity);
                pr.setString(2, barcode);
                int i = pr.executeUpdate();
                if( i > 0 ) {
                    //Updating product quantity in production2 table
                    pr = myConn.prepareStatement("update paper set quantity=(quantity-?) where barcode=?");
                    pr.setString(1, quantity);
                    pr.setString(2, barcode);
                    int ii = pr.executeUpdate();
                    if( ii > 0) {
                        exchange_log(name1, barcode, type, quantity, "1-2", apple, user_id);
                    }
                }

            } else {
                pr = myConn.prepareStatement("insert into production2_ready (barcode, name, type, quantity, color, date, user_id) values(?,?,?,?,?,?,?)");
                pr.setString(1, barcode);
                pr.setString(2, name1);
                pr.setString(3, type);
                pr.setString(4, quantity);
                pr.setString(5, "1");
                pr.setString(6, apple);
                pr.setString(7, user_id);
                int i = pr.executeUpdate();
                if( i > 0) {
                    //Updating product quantity in production2 table
                    pr = myConn.prepareStatement("update paper set quantity=(quantity-?) where barcode=?");
                    pr.setString(1, quantity);
                    pr.setString(2, barcode);
                    int ii = pr.executeUpdate();
                    if( ii > 0) {
                        exchange_log(name1, barcode, type, quantity, "1-2", apple, user_id);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                pr.close();
            }
        }
    }

    public void transferSell(String name1, String barcode, String type, String quantity) throws SQLException {
        PreparedStatement pr = null;
        try {
            if (checkDuplicate("Sell", barcode) > 0) {
                pr = myConn.prepareStatement("update sell set quantity=(quantity+?) where barcode=?");
                pr.setString(1, quantity);
                pr.setString(2, barcode);
                int i = pr.executeUpdate();
                if( i > 0 ) {
                    //Updating product quantity in production2 table
                    pr = myConn.prepareStatement("update paper set quantity=(quantity-?) where barcode=?");
                    pr.setString(1, quantity);
                    pr.setString(2, barcode);
                    int ii = pr.executeUpdate();
                    if( ii > 0) {
                        exchange_log(name1, barcode, type, quantity, "1-S", apple, user_id);
                    }
                }

            } else {
                pr = myConn.prepareStatement("insert into sell (barcode, name, type_id, quantity, cost, unit, description, date, cr_by) values(?,?,?,?,?,?,?,?,?)");
                pr.setString(1, barcode);
                pr.setString(2, name1);
                pr.setString(3, type);
                pr.setString(4, quantity);
                pr.setString(5, "1");
                pr.setString(6, "1");
                pr.setString(7, "1");
                pr.setString(8, apple);
                pr.setString(9, user_id);
                int i = pr.executeUpdate();
                if( i > 0) {
                    //Updating product quantity in production2 table
                    pr = myConn.prepareStatement("update paper set quantity=(quantity-?) where barcode=?");
                    pr.setString(1, quantity);
                    pr.setString(2, barcode);
                    int ii = pr.executeUpdate();
                    if( ii > 0) {
                        exchange_log(name1, barcode, type, quantity, "1-S", apple, user_id);
                    }
                }
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
}
