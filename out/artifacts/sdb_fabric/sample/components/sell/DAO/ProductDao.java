package sample.components.sell.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import sample.components.models.Exchange;
import sample.components.sell.Core.Models.CustomerName;
import sample.components.sell.Core.Models.ReceiptCheck;
import sample.components.sell.Core.Models.SellAction;
import sample.components.sell.Core.Product;
import sample.components.sell.productTableView.ProductTable;
import sample.dao.database;
import sample.model.History;

import javax.swing.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

/**
 * Humoyun Qo'rg'onov  SBD(Software Business Development)
 */
public class ProductDao {

    private Connection myConn;


    public ProductDao(Connection conn) {
        this.myConn = conn;
    }

    public ProductDao() {
        myConn = database.getConnection();
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
            myRs = myStmt.executeQuery("select * from sell_v where  sell_v.barcode = '" + barcode + "' order by name ");
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
            app.setDescription(myRs.getString("description"));
            productTables.add(app);
        }
    }

    public static ResultSet getResultSet(String name, TextField textSampleIzlash, Connection myConn) {
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            PreparedStatement myStmt1;
            if (textSampleIzlash.getText().trim().isEmpty()) {
                myStmt = myConn.createStatement();
                myRs = myStmt.executeQuery("SELECT * FROM sell_v");
            } else {
                name += "%";
                myStmt1 = myConn.prepareStatement("SELECT * FROM sell_v WHERE  sell_v.name LIKE ?  ORDER BY sell_v.name");
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
            myStmt = myConn.prepareStatement("SELECT * FROM sell_v WHERE  type LIKE ?  ORDER BY name");
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
            rs = st.executeQuery("SELECT name FROM type");
            while (rs.next()) {
                items.add(rs.getString("name"));
            }
            list.setItems(items);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // DaoUtils.close(st, rs);
        }
        return list;
    }

    public ProductTable convertProductToProductTable(Product product) {
        ProductTable pt = new ProductTable();
        pt.setId(product.getId());
        pt.setBarcode(product.getBarcode());
        pt.setType(product.getType());
        pt.setName(product.getName());
        pt.setQuantity(product.getQuantity());
        pt.setCost(product.getCost());
        pt.setUnit(product.getUnit());
        pt.setDate(product.getDate());
        pt.setUsername(product.getUsername());
        pt.setDescription(product.getDescription());

        return pt;

    }

    private Product convertRowToProduct(ResultSet myRs) throws SQLException {

        int id = myRs.getInt("id");
        String barcode = myRs.getString("barcode");
        String type = myRs.getString("type");
        String name = myRs.getString("name");
        int quantity = myRs.getInt("quantity");
        String cost = myRs.getString("cost");
        String unit = myRs.getString("unit");
        String date = myRs.getString("date");
        String username = myRs.getString("username");
        String description = myRs.getString("description");

        Product product = new Product(id, barcode, type, name, quantity, cost, unit, date, username, description);

        return product;
    }

    public void checkHistory(TableView tableView, String id) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pr = null;
        ObservableList<History> userTables = FXCollections.observableArrayList();
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
        try {

            if (!id.equals("*")) {
                pr = myConn.prepareStatement("SELECT * FROM  history_v WHERE sellAction_id=" + id);
                resultSet = pr.executeQuery();

            } else {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM history_v ORDER BY id limit 100");
            }
            while (resultSet.next()) {

                History history = new History();
                history.setId(resultSet.getString("id"));
                history.setBarcode(resultSet.getString("barcode"));
                history.setP_id(resultSet.getString("p_id"));
                history.setName(resultSet.getString("name"));
                history.setType(resultSet.getString("type"));
                history.setQuantity(resultSet.getString("quantity"));
                history.setSeller_id(resultSet.getString("seller_id"));
                history.setCost(formatter.format(resultSet.getDouble("cost")));
                history.setPerCost(formatter.format(resultSet.getDouble("per_cost")));
                history.setDate_cr(resultSet.getString("date_cr"));
                history.setCustomer_id(resultSet.getString("customer_id"));
                history.setSellAction_id(resultSet.getString("sellAction_id"));
                userTables.add(history);
            }

            tableView.setItems(userTables);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sample.dao.DaoUtils.close(statement, resultSet);
            if (pr != null) {
                pr.close();
            }
        }
    }

    public void exchangeTaleDao(TableView tableView, String name, String dan, String gacha, Label quantity) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pr = null;
        ResultSet myRs = null;

        //List to add items
        ObservableList<Exchange> tableBS = FXCollections.observableArrayList();

        if (!name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM exchange_v_s where name='" + name + "' and substr(cr_on,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");

            pr = myConn.prepareStatement("SELECT sum(quantity) as outt FROM exchange_v_s where name='" + name + "' and substr(cr_on,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");
            myRs = pr.executeQuery();
        } else if (name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM exchange_v_s where substr(cr_on,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");

            pr = myConn.prepareStatement("SELECT sum(quantity) as outt FROM exchange_v_s where substr(cr_on,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");
            myRs = pr.executeQuery();
        } else if (!name.isEmpty() && dan.isEmpty() && gacha.isEmpty()) {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM exchange_v_s where name='" + name + "'");

            pr = myConn.prepareStatement("SELECT sum(quantity) as outt FROM exchange_v_s where name='" + name + "'");
            myRs = pr.executeQuery();
        } else if (name.equals("1") || dan.equals("1") || gacha.equals("1")) {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM exchange_v_s ORDER BY id desc limit 500");

            pr = myConn.prepareStatement("SELECT sum(quantity) as outt FROM exchange_v_s");
            myRs = pr.executeQuery();
        }

        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    Exchange tableB = new Exchange();
                    tableB.setId(resultSet.getString("id"));
                    tableB.setBarcode(resultSet.getString("barcode"));
                    tableB.setName(resultSet.getString("name"));
                    tableB.setQuantity(resultSet.getString("quantity"));
                    tableB.setComment(resultSet.getString("comment"));
                    tableB.setCr_on(resultSet.getString("cr_on"));
                    tableB.setCr_by(resultSet.getString("cr_by"));
                    tableB.setType(resultSet.getString("type"));
                    tableBS.add(tableB);
                }
            }


            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
            symbols.setGroupingSeparator(' ');
            DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);

            if (myRs != null && myRs.next()) {
                quantity.setText(formatter.format(myRs.getDouble("outt")));

            }

            tableView.setItems(tableBS);


        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            try {
                sample.dao.DaoUtils.close(statement, resultSet);
                if (pr != null) {
                    pr.close();
                }
                if (myRs != null) {
                    myRs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void customerNameDao(TableView tableView, String keyword) {
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pr = null;

        //List to add items
        ObservableList<CustomerName> tableAS = FXCollections.observableArrayList();
        try {
            if (keyword.equals("")) {
                pr = myConn.prepareStatement("Select * from person order by companyName");
                resultSet = pr.executeQuery();
            } else {
                pr = myConn.prepareStatement("SELECT * FROM person where companyName like '%" + keyword + "%' order by companyName");
                resultSet = pr.executeQuery();
            }
            while (resultSet.next()) {
                CustomerName tableA = new CustomerName();
                tableA.setId(resultSet.getString("id"));
                tableA.setName(resultSet.getString("companyName"));
                tableAS.add(tableA);
            }
            tableView.setItems(tableAS);

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            try {
                sample.dao.DaoUtils.close(statement, resultSet);
                if (pr != null) {
                    pr.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getSellActionNameCombobox(ComboBox<String> comboBox) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT companyName from sellAction_v order by id desc");
            while (resultSet.next()) {  // loop
                // Now add the comboBox addAll statement
                comboBox.getItems().addAll(resultSet.getString("companyName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sample.dao.DaoUtils.close(statement, resultSet);
        }
    }

    public void getSellActionExchangeName(ComboBox<String> comboBox) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT name from exchange_v_s order by id desc");
            while (resultSet.next()) {  // loop
                // Now add the comboBox addAll statement
                comboBox.getItems().addAll(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sample.dao.DaoUtils.close(statement, resultSet);
        }
    }

    public void getTarixSelectName(ComboBox<String> comboBox) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT name from history_v order by id desc");
            while (resultSet.next()) {  // loop
                // Now add the comboBox addAll statement
                comboBox.getItems().addAll(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sample.dao.DaoUtils.close(statement, resultSet);
        }
    }

    public SellAction revertProduct(String id) throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            SellAction sellAction = null;
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM sellaction where id='" + id + "'");
            while (resultSet.next()) {
                sellAction = new SellAction(
                        resultSet.getInt("id"),
                        resultSet.getString("sum"),
                        resultSet.getString("dollar"),
                        resultSet.getString("hr"),
                        resultSet.getString("psum"),
                        resultSet.getString("pdollar"),
                        resultSet.getString("phr"),
                        resultSet.getString("sale"),
                        resultSet.getInt("customer_id"),
                        resultSet.getInt("cr_by"),
                        resultSet.getString("date_cr")
                );
            }
            return sellAction;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sample.dao.DaoUtils.close(statement, resultSet);
        }
        return null;

    }


    public void revertAll(int id, String sum, String dollar, String hr, String psum, String pdollar, String phr, String sale, int customer_id, int cr_by, String date_cr) {
        try (PreparedStatement preparedStatement = myConn.prepareStatement("Insert into sellaction_r (sum, dollar, hr, psum, pdollar, phr, customer_id, cr_by, date_cr) values(?,?,?,?,?,?,?,?,?)")) {
            preparedStatement.setString(1, sum);
            preparedStatement.setString(2, dollar);
            preparedStatement.setString(3, hr);
            preparedStatement.setString(4, psum);
            preparedStatement.setString(5, pdollar);
            preparedStatement.setString(6, phr);
            preparedStatement.setInt(7, customer_id);
            preparedStatement.setInt(8, cr_by);
            preparedStatement.setString(9, date_cr);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PreparedStatement preparedStatement = myConn.prepareStatement("Update balance set sum_in =(sum_in-?), dollar_in=(dollar_in-?), hr_in=(hr_in-?) where who=99999")) {
            preparedStatement.setString(1, psum);
            preparedStatement.setString(2, pdollar);
            preparedStatement.setString(3, phr);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PreparedStatement preparedStatement = myConn.prepareStatement("delete from sellaction where id =?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void revertPeoducrAll(ArrayList<ReceiptCheck> receiptChecks) {
        try (PreparedStatement preparedStatement = myConn.prepareStatement("Insert into history_r (barcode, name, quantity, sellaction_id) values(?,?,?,?)")) {
            for (ReceiptCheck item : receiptChecks) {
                preparedStatement.setString(1, item.getBarcode());
                preparedStatement.setString(2, item.getName());
                preparedStatement.setInt(3, item.getQuantity());
                preparedStatement.setInt(4, item.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteHistory(String id) {

        try(PreparedStatement preparedStatement =myConn.prepareStatement("Delete from history where sellaction_id = '"+id+"'")){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Operatsiya yakunlandi");
    }
}

