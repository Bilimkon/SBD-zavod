package sample.components.sell.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.components.sell.Core.Product;
import sample.components.sell.productTableView.ProductTable;

import java.sql.*;

/**
 * Humoyun Qo'rg'onov  SBD(Software Business Development)
 */
public class ProductDao {

    private Connection myConn;


    public ProductDao(Connection conn) {
        this.myConn = conn;
    }

    public ProductDao() {
        myConn = Database.getConnection();
    }

    public int getQuantity(String barcode) {
        try {
            Product p = getProduct(barcode);
            if (p != null) {
                return p.getQuantity();
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Search product with barcode
     *
     * @param barcode unique id of the product
     */
    public Product getProduct(String barcode) throws Exception {
        Product product;

        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("select * from main.product where quantity > 0 and main.product.barcode = '" + barcode + "' order by name ");
            if (myRs.next()) {
                product = convertRowToProduct(myRs);
                return product;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(myStmt, myRs);
        }
        return null;

    }

    public static void productTableGenerator(ResultSet myRs, ObservableList<ProductTable> productTables) throws SQLException {
        while (myRs.next()) {
            ProductTable app = new ProductTable();
            app.setBarcode(myRs.getString("barcode"));
            app.setName(myRs.getString("name"));
            app.setType(myRs.getString("type"));
            app.setQuantity(myRs.getInt("quantity"));
            app.setCost(myRs.getString("cost"));
            productTables.add(app);
        }
    }

    public static ResultSet getResultSet(String name, TextField textSampleIzlash, Connection myConn) throws SQLException {
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            PreparedStatement myStmt1;
            if (textSampleIzlash.getText().trim().isEmpty()) {
                myStmt = myConn.createStatement();
                myRs = myStmt.executeQuery("SELECT * FROM main.product WHERE quantity > 0 ORDER BY name ");
            } else {
                name += "%";
                myStmt1 = myConn.prepareStatement("SELECT * FROM main.product WHERE quantity > 0 AND main.product.name LIKE ?  ORDER BY main.product.name");
                myStmt1.setString(1, name);
                myRs = myStmt1.executeQuery();
            }

            return myRs;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public ResultSet searchProductType(String Name) throws Exception {
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            Name += "%";
            myStmt = myConn.prepareStatement("SELECT * FROM main.product WHERE quantity > 0 AND type LIKE ?  ORDER BY name");
            myStmt.setString(1, Name);
            myRs = myStmt.executeQuery();
            return myRs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ListView<String> typeList(ListView<String> list) throws SQLException {
        ObservableList<String> items = FXCollections.observableArrayList();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = myConn.createStatement();
            rs = st.executeQuery("SELECT name FROM main.type");
            while (rs.next()) {
                items.add(rs.getString("name"));
            }
            list.setItems(items);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(st, rs);
        }
        return list;
    }

    public ProductTable convertProductToProductTable(Product product) {
        ProductTable pt = new ProductTable();
        pt.setId(product.getId());
        pt.setName(product.getName());
        pt.setBarcode(product.getBarcode());
        pt.setType(product.getType());
        pt.setType_id(product.getType_id());
        pt.setCost(product.getCost());
        pt.setCost_o(product.getCost_o());
        pt.setQuantity(product.getQuantity());
        pt.setDate_c(product.getDate_c());
        pt.setDate_o(product.getDate_o());
        pt.setCr_by(product.getCr_by());
        pt.setDate_cr(product.getDate_cr());
        pt.setUp_by(product.getUp_by());
        pt.setDate_up(product.getDate_up());
        return pt;
    }

    private Product convertRowToProduct(ResultSet myRs) throws SQLException {

        int id = myRs.getInt("id");
        String barcode = myRs.getString("barcode");
        String name = myRs.getString("name");
        String type = myRs.getString("type");
        int type_id = myRs.getInt("type_id");
        String cost = myRs.getString("cost");
        int quantity = myRs.getInt("quantity");
        String cost_o = myRs.getString("cost_o");
        String date_c = myRs.getString("date_c");
        String date_o = myRs.getString("date_o");
        int cr_by = myRs.getInt("cr_by");
        String date_cr = myRs.getString("date_cr");
        int up_by = myRs.getInt("up_by");
        String date_up = myRs.getString("date_up");

        Product product = new Product(id, barcode, name, type, type_id, cost, quantity, cost_o, date_c, date_o, cr_by, date_cr, up_by, date_up);

        return product;
    }


}

