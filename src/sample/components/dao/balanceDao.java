package sample.components.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import sample.components.models.BalanceTotal;
import sample.components.sell.DAO.OperDao;
import sample.components.sell.Utils.Utils;
import sample.components.sell.productTableView.balance;
import sample.dao.DaoUtils;
import sample.dao.database;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class balanceDao {

    Connection myConn = null;
    String apple = Utils.convertDateToStandardFormat(Utils.getCurrentDate());
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
              resultSet = statement.executeQuery("Select * from total_balance_v");
                if (resultSet != null) {
                    while (resultSet.next()) {
                        BalanceTotal invoice = new BalanceTotal();
                        invoice.setProduct(formatter.format(resultSet.getDouble("total_product")));
                        invoice.setP3(formatter.format(resultSet.getDouble("total_product_walpaper")));
                        invoice.setSell(formatter.format(resultSet.getDouble("total_sell")));
                        invoice.setC_sum(formatter.format(resultSet.getDouble("c_sum")));
                        invoice.setC_dollar(formatter.format(resultSet.getDouble("c_dollar")));
                        invoice.setC_hr(formatter.format(resultSet.getDouble("c_hr")));
                        invoice.setSum(formatter.format(resultSet.getDouble("total_sum")));
                        invoice.setDollar(formatter.format(resultSet.getDouble("total_dollar")));
                        invoice.setHr(formatter.format(resultSet.getDouble("total_hr")));
                        invoice.setVhr(formatter.format(resultSet.getDouble("total_vhr")));

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

    public void tableBalance(TableView tableView, Label sum, Label dollar, Label hr) {
        Statement statement = null;
        ResultSet resultSet = null;
        Statement statement1 = null;
        ResultSet resultSet1 = null;
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
        //List to add items
        ObservableList<balance> balances = FXCollections.observableArrayList();
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM balance_v ORDER BY who");

            statement1 = myConn.createStatement();
            resultSet1 = statement1.executeQuery("SELECT  sum(sum_balance) as sum," +
                    "sum(dollar_balance) as dollar, sum(hr_balance) as hr FROM balance_v ");

            while (resultSet.next()) {
                balance balance = new balance();
                balance.setWho(resultSet.getString("who"));
                balance.setSum_balance(formatter.format(resultSet.getDouble("sum_balance")));
                balance.setDollar_balance(formatter.format(resultSet.getDouble("dollar_balance")));
                balance.setHr_balance(formatter.format(resultSet.getDouble("hr_balance")));

                balances.add(balance);
            }

            while (resultSet1.next()){
                sum.setText(formatter.format(resultSet1.getDouble("sum")));
                dollar.setText(formatter.format(resultSet1.getDouble("dollar")));
                hr.setText(formatter.format(resultSet1.getDouble("hr")));
            }

            tableView.setItems(balances);

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            try {
                DaoUtils.close(statement, resultSet);
                DaoUtils.close(statement1, resultSet1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void sumAll(Label total, Label sum1, Label dollar1, Label hr) {
        try{
            OperDao operDao =new OperDao();
            Statement statement;
            ResultSet resultSet;
            double dollar=0;
            double sum =0;
            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
            symbols.setGroupingSeparator(' ');
            DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
            statement=myConn.createStatement();
            resultSet=statement.executeQuery("  SELECT ((select C_Sum from total_balance_v)+ (select c_hr from total_balance_v) + (select total_sum from total_balance_v)+\n" +
                    "                   (select  total_hr from total_balance_v) +(-1*((select sum(sum_balance) from balance_v)+(select sum(hr_balance) from balance_v)))) as sum,\n" +
                    "                   +  ((select total_product from total_balance_v)+ (select total_product_walpaper from total_balance_v)\n" +
                    "                   +  (select C_dollar from total_balance_v) + (select total_sell from total_balance_v) + (select total_dollar from total_balance_v)\n" +
                    "                 +   (select total_vhr from total_balance_v)+(-1*(select sum(dollar_balance) from balance_v))) as dollar");
            while (resultSet.next()){
                dollar= resultSet.getDouble("dollar");
                sum= resultSet.getDouble("sum");
            }
            total.setText(formatter.format(dollar+(sum/operDao.getCurrencyRate())));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void chart( StackedBarChart areaChart) throws SQLException {
        /*
         *   Getting data from database to XYChart
         */

        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pr = null;
        try {

                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM t_balance_rate ORDER BY id limit 10 ");

            XYChart.Series<String, Double> series = new XYChart.Series<>();

            while (resultSet.next()) {
                series.getData().add(new XYChart.Data<>(resultSet.getString("cr_on"), resultSet.getDouble("cost")));
            }
            areaChart.getData().add(series);

            /*
             * End of XYChart
             *
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertTotalDayily(String total1) throws SQLException {

        PreparedStatement preparedStatement = null;
        try{
            preparedStatement =myConn.prepareStatement("Insert into t_balance_rate (cr_on, cost) values(?,?)");
            preparedStatement.setString(1,apple);
            preparedStatement.setString(2,total1);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
}
