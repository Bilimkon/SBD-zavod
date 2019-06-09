package sample.components.sell.DAO;

import com.sun.istack.internal.Nullable;
import sample.components.sell.Core.Models.BasketItem;
import sample.components.sell.Core.Models.CreditModel;
import sample.components.sell.Core.Product;
import sample.components.sell.Core.User;
import sample.components.sell.LoginController;
import sample.components.sell.Utils.Utils;
import java.sql.*;
import java.util.List;

/**
 * Humoyun Qo'rg'onov  SBD(Software Business Development)
 */

public class HistoryDao {
    private Connection myConn;
    private PreparedStatement pt;

    public HistoryDao() {
        myConn =Database.getConnection();
    }

    public HistoryDao(Connection c) {
        myConn = c;
    }

    public void insertBasketToHistory(List<BasketItem> basket, User user, @Nullable CreditModel credit, String total_cost, String cash, String  card, String sale, String cr_by, String cost_paid, String commnet ) throws Exception {
        try {
            ProductDao productDao = new ProductDao(myConn);
            if (credit == null) {
                credit = new CreditModel(0, 0);
            }
            String apple = Utils.convertDateToStandardFormat(Utils.getCurrentDate());
            pt = myConn.prepareStatement("insert  into main.sellaction(total_cost, cash, card, sale, credit, date_cr, cr_by, cost_paid, customer_id,comment) values (?,?,?,?,?,?,?,?,?,?)");
            pt.setString(1, total_cost);
            pt.setString(2, cash);
            pt.setString(3, card);
            pt.setString(4, sale);
            pt.setString(5, String.valueOf(credit.getSumma()));
            pt.setString(6, apple);
            pt.setString(7, cr_by);
            pt.setString(8, cost_paid);
            pt.setString(9, String.valueOf(credit.getId()));
            pt.setString(10,commnet);
            pt.executeUpdate();

        int actionId = createAction();
        for (BasketItem item : basket) {
            Product p = productDao.getProduct(item.getBarcode());
            float totalCost = item.getAmount() * item.getCost();
            pt=myConn.prepareStatement("INSERT INTO main.history(product_barcode," +
                    " product_id, product_name, product_type, product_quantity, seller_id," +
                    " cost, date_cr, cr_by, customer_id, sell_action_id)" +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            pt.setString(1, item.getBarcode());
            pt.setInt(2,p.getId());
            pt.setString(3,item.getTitle());
            pt.setInt(4,p.getType_id());
            pt.setInt(5,item.getAmount());
            pt.setString(6, String.valueOf(LoginController.currentUser.getId()));
            pt.setFloat(7,totalCost);
            pt.setString(8,apple);
            pt.setString(9,String.valueOf(LoginController.currentUser.getId()));
            pt.setString(10, String.valueOf(credit.getId()));
            pt.setInt(11,actionId);
            pt.executeUpdate();
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            pt.close();
        }
    }

    private int createAction() {
        try {
            String q = "select max(id) as 'last_item_id' from main.sellaction";
            ResultSet resultSet = generateResultSet(q);
            if (resultSet.next()) {
                return resultSet.getInt("last_item_id");
            } else return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private ResultSet generateResultSet(String query) throws SQLException {
        Statement myStmt = myConn.createStatement();
        return myStmt.executeQuery(query);
    }

    public void addCustomer(String firstname ,String lastname) throws SQLException {
        try {
            pt = myConn.prepareStatement("INSERT  INTO customer(firstName,lastName) values (?,?)");
            pt.setString(1,firstname);
            pt.setString(2,lastname);
            pt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            pt.close();
        }
    }

}
