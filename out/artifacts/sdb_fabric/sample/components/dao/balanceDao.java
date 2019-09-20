package sample.components.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.components.models.BalanceTotal;
import sample.dao.DaoUtils;
import sample.dao.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class balanceDao {

    Connection myConn = null;
    public balanceDao() {
        myConn = database.getConnection();
    }


    public void balanceTableDao(TableView tableView) {
        try {
            Statement statement = null;
            ResultSet resultSet = null;
            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
            symbols.setGroupingSeparator(' ');
            DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);

            //List to add items
            ObservableList<BalanceTotal> invoices = FXCollections.observableArrayList();
            try {
              statement =myConn.createStatement();
              resultSet = statement.executeQuery("Select * from balancetotal_v");
                if (resultSet != null) {
                    while (resultSet.next()) {
                        BalanceTotal invoice = new BalanceTotal();
                        invoice.setProduct(formatter.format(resultSet.getDouble("product")));
                        invoice.setP2(formatter.format(resultSet.getDouble("p2")));
                        invoice.setP3(formatter.format(resultSet.getDouble("p3")));
                        invoice.setSell(formatter.format(resultSet.getDouble("sell")));
                        invoice.setSum(formatter.format(resultSet.getDouble("sum")));
                        invoice.setDollar(formatter.format(resultSet.getDouble("dollar")));
                        invoice.setHr(formatter.format(resultSet.getDouble("hr")));
                        invoice.setVhr(formatter.format(resultSet.getDouble("vhr")));
                        invoice.setTotal_all(formatter.format(resultSet.getDouble("total_all")));

                        invoices.add(invoice);
                    }
                }
                tableView.setItems(invoices);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DaoUtils.close(statement, resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
