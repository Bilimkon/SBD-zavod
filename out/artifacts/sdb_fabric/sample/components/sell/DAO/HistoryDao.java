package sample.components.sell.DAO;

import com.sun.istack.internal.Nullable;
import sample.Login;
import sample.components.models.AllCurrencyValues;
import sample.components.sell.Core.Models.BasketItem;
import sample.components.sell.Core.Models.CreditModel;
import sample.components.sell.Core.Product;
import sample.components.sell.Utils.Utils;
import sample.dao.database;
import sample.model.User;

import java.sql.*;
import java.util.List;

/**
 * Humoyun Qo'rg'onov  SBD(Software Business Development)
 */

public class HistoryDao {
    private Connection myConn;
    private PreparedStatement pt;

    public HistoryDao() {
        myConn = database.getConnection();
    }

    public HistoryDao(Connection c) {
        myConn = c;
    }
    String apple = Utils.convertDateToStandardFormat(Utils.getCurrentDate());

    public void insertBasketToHistory(List<BasketItem> basket, User user, @Nullable CreditModel credit, AllCurrencyValues allCurrencyValues, String sale, String commnet, String sum, String dollar, String hr ) throws Exception {
        try {
            ProductDao productDao = new ProductDao(myConn);
            if (credit == null) {
                credit = new CreditModel(0, 0);
            }
            pt = myConn.prepareStatement("insert  into sellaction(sum, dollar, hr, sale, customer_id, cr_by, date_cr, comment, psum, pdollar, phr) values (?,?,?,?,?,?,?,?,?,?,?)");
            pt.setString(1, String.valueOf(allCurrencyValues.sum));
            pt.setString(2, String.valueOf(allCurrencyValues.dollar));
            pt.setString(3, String.valueOf(allCurrencyValues.hr));
            pt.setString(4, sale);
            pt.setString(5, String.valueOf(credit.getId()));
            pt.setString(6, String.valueOf(Login.currentUser.getId()));
            pt.setString(7, apple);
            pt.setString(8, commnet);
            pt.setString(9, sum);
            pt.setString(10, dollar);
            pt.setString(11, hr);
            pt.executeUpdate();

        int actionId = createAction();
        for (BasketItem item : basket) {
            Product p = productDao.getProduct(item.getBarcode());
            float totalCost = item.getAmount() * item.getCost();
            pt=myConn.prepareStatement("INSERT INTO history(barcode," +
                    " p_id, name, type, quantity, seller_id," +
                    " cost, date_cr, cr_by, customer_id, sellAction_id)" +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            pt.setString(1, item.getBarcode());
            pt.setInt(2,p.getId());
            pt.setString(3,item.getTitle());
            pt.setString(4,p.getType());
            pt.setInt(5,item.getAmount());
            pt.setString(6, String.valueOf(Login.currentUser.getId()));
            pt.setFloat(7,totalCost);
            pt.setString(8,apple);
            pt.setString(9,String.valueOf(Login.currentUser.getId()));
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
            String q = "select max(id) as 'last_item_id' from sellaction";
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

    public void addCustomer(String firstname) throws SQLException {
        try {
            pt = myConn.prepareStatement("INSERT  INTO Person(type,companyName, cr_by, date_cr) values (?,?,?,?)");
            pt.setString(1,"2");
            pt.setString(2,firstname);
            pt.setString(3,String.valueOf(Login.currentUser.getId()));
            pt.setString(4,apple);
            pt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            pt.close();
        }
    }


    public void addSalePaidSum2( String sum, String dollar, String hr) {
        try {
            //inserting into company balance where company who=99999
            try (PreparedStatement preparedStatement = myConn.prepareStatement("update balance set  sum_in=(sum_in+?), " +
                    "dollar_in=(dollar_in+?), hr_in=(hr_in+?) where who=99999")) {
                preparedStatement.setString(1, sum);
                preparedStatement.setString(2, dollar);
                preparedStatement.setString(3, hr);
                preparedStatement.executeUpdate();
            } catch (Exception e5) {
                e5.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
