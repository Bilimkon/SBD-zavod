package sample.components.sell.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import sample.Login;
import sample.components.sell.Utils.Utils;
import sample.components.sell.productTableView.OperTable;
import sample.components.sell.productTableView.balance;
import sample.dao.DaoUtils;
import sample.dao.ProductDao;
import sample.dao.database;
import sample.model.Debt;
import sample.model.History;
import sample.model.User;
import sample.utils.Workbookcontroller;

import javax.swing.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class OperDao {

    User u = Login.currentUser;
    String user_id = String.valueOf(Login.currentUser.getId());
    private Connection myConn = null;
    sample.dao.ProductDao productDao = new ProductDao();

    Workbookcontroller workbookcontroller = new Workbookcontroller();

    public OperDao() {
        myConn = database.getConnection();
    }

    public void excellOperTable(Button button, String who, String dan, String gacha) {
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pr = null;
        try {
            if (!who.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
                pr = myConn.prepareStatement("SELECT * FROM sale_balance_v WHERE who=? and substr(date,7,10) BETWEEN ? AND ? order by id desc");
                pr.setString(1, who);
                pr.setString(2, dan);
                pr.setString(3, gacha);
                resultSet = pr.executeQuery();
            } else if (who.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM sale_balance_v where substr(date,7,10) BETWEEN '" + dan + "' and '" + gacha + "' order by id desc ");
            } else if (who.isEmpty() && dan.isEmpty() && gacha.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM sale_balance_v order by id desc limit 500 ");
            }
            workbookcontroller.datebaseToExcelResultset("Sale_balance_v", "Savdo_hisoboti.xls", resultSet);

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            try {
                DaoUtils.close(statement, resultSet);
                if (pr != null) {
                    pr.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void operTableFilter(TableView tableView, String who, String dan, String gacha, Label sum, Label dollar, Label hr) {

        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pr = null;
        ResultSet myRs = null;
        try {
            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
            symbols.setGroupingSeparator(' ');
            DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
            //List to add items
            ObservableList<OperTable> userTables = FXCollections.observableArrayList();
            if (!who.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {

                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM sale_balance_v where who='" + who + "'and substr(date,7,10) BETWEEN '" + dan + "' and '" + gacha + "' order by id desc ");


                pr = myConn.prepareStatement("SELECT sum(sum) total_sum, sum(dollar) total_dollar, sum(hr) total_hr FROM sale_balance_v WHERE who=? and substr(date,7,10) BETWEEN ? AND ? order by id desc");
                pr.setString(1, who);
                pr.setString(2, dan);
                pr.setString(3, gacha);
                myRs = pr.executeQuery();

            } else if (who.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM sale_balance_v where substr(date,7,10) BETWEEN '" + dan + "' and '" + gacha + "' order by id desc ");


                pr = myConn.prepareStatement("SELECT sum(sum) total_sum, sum(dollar) total_dollar, sum(hr) total_hr FROM sale_balance_v WHERE  substr(date,7,10) BETWEEN ? AND ? order by id desc");
                pr.setString(1, dan);
                pr.setString(2, gacha);
                myRs = pr.executeQuery();
            } else if (who.isEmpty() && dan.isEmpty() && gacha.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM sale_balance_v order by id desc limit 500 ");


                pr = myConn.prepareStatement("SELECT sum(sum) total_sum, sum(dollar) total_dollar, sum(hr) total_hr FROM sale_balance_v order by id desc limit 500");
                myRs = pr.executeQuery();
            }
            if (resultSet != null) {
                while (resultSet.next()) {
                    OperTable operTable = new OperTable();
                    operTable.setId(resultSet.getString("id"));
                    operTable.setType(resultSet.getString("type"));
                    operTable.setWho(resultSet.getString("who"));
                    operTable.setSum(formatter.format(resultSet.getDouble("sum")));
                    operTable.setDollar(formatter.format(resultSet.getDouble("dollar")));
                    operTable.setHr(formatter.format(resultSet.getDouble("hr")));
                    operTable.setDescription(resultSet.getString("description"));
                    operTable.setCr_by(resultSet.getString("cr_by"));
                    operTable.setDate(resultSet.getString("date"));
                    operTable.setCurrency(formatter.format(resultSet.getDouble("currency")));
                    operTable.setPercentage(resultSet.getString("percentage"));
                    operTable.setSubtotal(formatter.format(resultSet.getDouble("sub_total")));

                    userTables.add(operTable);
                }
            }

            if (myRs != null && myRs.next()) {
                sum.setText(formatter.format(myRs.getDouble("total_sum")));
                dollar.setText(formatter.format(myRs.getDouble("total_dollar")));
                hr.setText(formatter.format(myRs.getDouble("total_hr")));
            }
            tableView.setItems(userTables);

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            try {
                DaoUtils.close(statement, resultSet);
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

    public void excellHistoryTable(Button button, String who, String name, String dan, String gacha) {
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            if (!who.isEmpty() && !name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM history_v where customer_id='" + who + "' and name='" + name + "' and substr(date_cr,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");

            } else if (who.isEmpty() && name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM history_v where substr(date_cr,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");

            } else if (!who.isEmpty() && !name.isEmpty() && dan.isEmpty() && gacha.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM history_v where customer_id='" + who + "' and name='" + name + "'");

            } else if (!who.isEmpty() && name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM history_v where customer_id='" + who + "' and  substr(date_cr,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");

            } else if (who.isEmpty() && !name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM history_v where name='" + name + "' and  substr(date_cr,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");

            } else if (who.isEmpty() && name.isEmpty() && dan.isEmpty() && gacha.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * total_cost FROM history_v order by id desc limit 500 ");
            }

            Workbookcontroller workbookcontroller = new Workbookcontroller();
            workbookcontroller.datebaseToExcelResultset("history_v", "Savdo_tarixi.xls", resultSet);

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

    public void HistoryTableFilter(TableView tableView, String who, String name, String dan, String gacha, Label total_quantity, Label total_cost) {

        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pr = null;
        ResultSet myRs = null;
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
        try {
            //List to add items
            ObservableList<History> userTables = FXCollections.observableArrayList();

            if (!who.isEmpty() && !name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM history_v where customer_id='" + who + "' and name='" + name + "' and substr(date_cr,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");

                pr = myConn.prepareStatement("SELECT sum(quantity) as total_quantity, sum(cost) total_cost  FROM history_v where customer_id='" + who + "' and name='" + name + "' and substr(date_cr,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");
                myRs = pr.executeQuery();
            } else if (who.isEmpty() && name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM history_v where substr(date_cr,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");

                pr = myConn.prepareStatement("SELECT sum(quantity) as total_quantity, sum(cost) total_cost  FROM history_v where substr(date_cr,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");
                myRs = pr.executeQuery();
            } else if (!who.isEmpty() && !name.isEmpty() && dan.isEmpty() && gacha.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM history_v where customer_id='" + who + "' and name='" + name + "'");

                pr = myConn.prepareStatement("SELECT sum(quantity) as total_quantity, sum(cost) total_cost FROM history_v where customer_id='" + who + "' and name='" + name + "'");
                myRs = pr.executeQuery();
            } else if (!who.isEmpty() && name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM history_v where customer_id='" + who + "' and  substr(date_cr,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");

                pr = myConn.prepareStatement("SELECT sum(quantity) as total_quantity, sum(cost) total_cost FROM history_v  where customer_id='" + who + "' and  substr(date_cr,7,10) BETWEEN '" + dan + "' AND '" + gacha + "'  ");
                myRs = pr.executeQuery();
            } else if (who.isEmpty() && !name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM history_v where name='" + name + "' and  substr(date_cr,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");

                pr = myConn.prepareStatement("SELECT sum(quantity) as total_quantity, sum(cost) total_cost FROM history_v  where name='" + name + "' and  substr(date_cr,7,10) BETWEEN '" + dan + "' AND '" + gacha + "'  ");
                myRs = pr.executeQuery();
            } else if (who.isEmpty() && name.isEmpty() && dan.isEmpty() && gacha.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM history_v order by id desc limit 500 ");

                pr = myConn.prepareStatement("SELECT sum(quantity) as total_quantity, sum(cost) total_cost FROM history_v order by id desc limit 500 ");
                myRs = pr.executeQuery();
            }

            if (resultSet != null) {
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

                if (myRs != null && myRs.next()) {
                    total_quantity.setText(formatter.format(myRs.getDouble("total_quantity")));
                    total_cost.setText(formatter.format(myRs.getDouble("total_cost")));

                }
            }
            tableView.setItems(userTables);

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            try {
                DaoUtils.close(statement, resultSet);
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

    public void QarzTableFilter(TableView tableView, String name, String dan, String gacha, Label sellsumT, Label selldollarT, Label sellhrT, Label sellsumP, Label selldollarP, Label sellhrP) {

        Statement statement = null;
        ResultSet resultSet = null;
        ResultSet myRs = null;
        PreparedStatement pr = null;

        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
        try {
            //List to add items
            ObservableList<Debt> userTables = FXCollections.observableArrayList();

            if (!name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM sellaction_v where companyName='" + name + "' and substr(date_cr, 7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");

                pr = myConn.prepareStatement("SELECT sum(sum) total_sum, sum(dollar) total_dollar, sum(hr) total_hr, sum(psum) total_psum, sum(pdollar) total_pdollar, sum(phr) total_phr FROM sellaction_v where companyName='" + name + "' and substr(date_cr, 7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");
                myRs = pr.executeQuery();

            } else if (name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM sellaction_v where substr(date_cr, 7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");

                pr = myConn.prepareStatement("SELECT sum(sum) total_sum, sum(dollar) total_dollar, sum(hr) total_hr, sum(psum) total_psum, sum(pdollar) total_pdollar, sum(phr) total_phr FROM sellaction_v where substr(date_cr, 7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");
                myRs = pr.executeQuery();
            } else if (!name.isEmpty() && dan.isEmpty() && gacha.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM sellaction_v where companyName='" + name + "'");

                pr = myConn.prepareStatement("SELECT sum(sum) total_sum, sum(dollar) total_dollar, sum(hr) total_hr, sum(psum) total_psum, sum(pdollar) total_pdollar, sum(phr) total_phr FROM sellaction_v where companyName='" + name + "'");
                myRs = pr.executeQuery();
            } else if (name.equals("1") || dan.equals("1") || gacha.equals("1")) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM sellaction_v ORDER BY id desc limit 300");

                pr = myConn.prepareStatement("SELECT sum(sum) total_sum, sum(dollar) total_dollar, sum(hr) total_hr, sum(psum) total_psum, sum(pdollar) total_pdollar, sum(phr) total_phr FROM sellaction_v ORDER BY id desc limit 300");
                myRs = pr.executeQuery();
            }
            if (resultSet != null) {
                while (resultSet.next()) {
                    Debt operTable = new Debt();
                    operTable.setId(resultSet.getString("id"));
                    operTable.setSum(formatter.format(resultSet.getDouble("sum")));
                    operTable.setDollar(formatter.format(resultSet.getDouble("dollar")));
                    operTable.setHr(formatter.format(resultSet.getDouble("hr")));
                    operTable.setPsum(formatter.format(resultSet.getDouble("psum")));
                    operTable.setPdollar(formatter.format(resultSet.getDouble("pdollar")));
                    operTable.setPhr(formatter.format(resultSet.getDouble("phr")));
                    operTable.setSale(formatter.format(resultSet.getDouble("sale")));
                    operTable.setCustomer_id(resultSet.getString("companyName"));
                    operTable.setCr_by(resultSet.getString("username"));
                    operTable.setDate_cr(resultSet.getString("date_cr"));
                    operTable.setComment(resultSet.getString("comment"));
                    userTables.add(operTable);
                }
            }

            if (myRs != null && myRs.next()) {
                sellsumT.setText(formatter.format(myRs.getDouble("total_sum")));
                selldollarT.setText(formatter.format(myRs.getDouble("total_dollar")));
                sellhrT.setText(formatter.format(myRs.getDouble("total_hr")));
                sellsumP.setText(formatter.format(myRs.getDouble("total_psum")));
                selldollarP.setText(formatter.format(myRs.getDouble("total_pdollar")));
                sellhrP.setText(formatter.format(myRs.getDouble("total_phr")));
            }
            tableView.setItems(userTables);

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

    public void getCheckExcellSheet(Button button, String name, String dan, String gacha) {
        Statement statement = null;
        ResultSet resultSet = null;

        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
        try {
            //List to add items
            ObservableList<Debt> userTables = FXCollections.observableArrayList();

            if (!name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM sellaction_v where companyName='" + name + "' and substr(date_cr, 7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");

            } else if (name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM sellaction_v where substr(date_cr, 7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");
            } else if (!name.isEmpty() && dan.isEmpty() && gacha.isEmpty()) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM sellaction_v where companyName='" + name + "'");
            } else if (name.equals("1") || dan.equals("1") || gacha.equals("1")) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM sellaction_v ORDER BY id desc limit 300");
            }

            workbookcontroller.datebaseToExcelResultset("sellaction_v", "savdo.xls", resultSet);

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


    public void addOperTable(String type, String who, String sum, String dollar, String hr, String description, String percentage, String subtotal) throws SQLException {
        String dollar_in = "0";
        String dollar_out = "0";
        String sum_in = "0";
        String sum_out = "0";
        String hr_in = "0";
        String hr_out = "0";
        int i = 0;
        if (type.equals("Kirim")) {
            sum_in = sum;
            dollar_in = dollar;
            hr_in = hr;
        } else if (type.equals("Chiqim")) {
            sum_out = sum;
            dollar_out = dollar;
            hr_out = hr;
        }

        try (PreparedStatement preparedStatement = myConn.prepareStatement("INSERT INTO sale_balance(type, who, sum, dollar, hr, description, cr_by, date, currency, percentage, sub_total) VALUES (?,?,?,?,?,?,?,?,?,?,?)")) {
            //inserting into sale_balance table
            preparedStatement.setString(1, type);
            preparedStatement.setString(2, who);
            preparedStatement.setString(3, sum);
            preparedStatement.setString(4, dollar);
            preparedStatement.setString(5, hr);
            preparedStatement.setString(6, description);
            preparedStatement.setString(7, String.valueOf(u.getId()));
            preparedStatement.setString(8, Utils.getCurrnetDateInStandardFormat());
            preparedStatement.setString(9, "1");
            preparedStatement.setString(10, percentage);
            preparedStatement.setString(11, subtotal);
            i = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(i>0) {
            try (PreparedStatement preparedStatement = myConn.prepareStatement("update balance set  sum_in=(sum_in+?), sum_out=(sum_out+?), " +
                    "dollar_in=(dollar_in+?), dollar_out=(dollar_out+?), hr_in=(hr_in+?), hr_out=(hr_out+?) where who=99999")) {
                //inserting into company balance where company who=99999
                preparedStatement.setString(1, sum_in);
                preparedStatement.setString(2, sum_out);
                preparedStatement.setString(3, dollar_in);
                preparedStatement.setString(4, dollar_out);
                preparedStatement.setString(5, hr_in);
                preparedStatement.setString(6, hr_out);
                preparedStatement.executeUpdate();
            } catch (Exception a) {
                a.printStackTrace();
            }

            if (type.equals("Chiqim")) {
                try (PreparedStatement preparedStatement = myConn.prepareStatement("update balance set sum_in=(sum_in-?), dollar_in=(dollar_in-?), hr_in=(hr_in-?) where who=99999")) {
                    preparedStatement.setString(1, sum_out);
                    preparedStatement.setString(2, dollar_out);
                    preparedStatement.setString(3, hr_out);
                    preparedStatement.executeUpdate();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public void balanceTable(TableView tableView, String name) {
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pr = null;
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
        //List to add items
        ObservableList<balance> balances = FXCollections.observableArrayList();
        try {
            //List to add items
            if (!name.equals("*")) {
                pr = myConn.prepareStatement("SELECT * FROM balance_v WHERE who=?");
                pr.setString(1, name);
                resultSet = pr.executeQuery();
            } else {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM balance_v ORDER BY id");
            }
            while (resultSet.next()) {
                balance balance = new balance();
                balance.setWho(resultSet.getString("who"));
                balance.setSum_in(formatter.format(resultSet.getDouble("sum_in")));
                balance.setSum_out(formatter.format(resultSet.getDouble("sum_out")));
                balance.setSum_balance(formatter.format(resultSet.getDouble("sum_balance")));
                balance.setDollar_in(formatter.format(resultSet.getDouble("dollar_in")));
                balance.setDollar_out(formatter.format(resultSet.getDouble("dollar_out")));
                balance.setDollar_balance(formatter.format(resultSet.getDouble("dollar_balance")));
                balance.setHr_in(formatter.format(resultSet.getDouble("hr_in")));
                balance.setHr_out(formatter.format(resultSet.getDouble("hr_out")));
                balance.setHr_balance(formatter.format(resultSet.getDouble("hr_balance")));

                balances.add(balance);
            }
            tableView.setItems(balances);

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

    public void tableDebtorsTable(TableView tableView) {
        Statement statement = null;
        ResultSet resultSet = null;
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
        //List to add items
        ObservableList<balance> balances = FXCollections.observableArrayList();
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM debtors_v ORDER BY id");
            while (resultSet.next()) {
                balance balance = new balance();
                balance.setWho(resultSet.getString("who"));
                balance.setSum_in(formatter.format(resultSet.getDouble("sum_in")));
                balance.setSum_out(formatter.format(resultSet.getDouble("sum_out")));
                balance.setSum_balance(formatter.format(resultSet.getDouble("sum_balance")));
                balance.setDollar_in(formatter.format(resultSet.getDouble("dollar_in")));
                balance.setDollar_out(formatter.format(resultSet.getDouble("dollar_out")));
                balance.setDollar_balance(formatter.format(resultSet.getDouble("dollar_balance")));
                balance.setHr_in(formatter.format(resultSet.getDouble("hr_in")));
                balance.setHr_out(formatter.format(resultSet.getDouble("hr_out")));
                balance.setHr_balance(formatter.format(resultSet.getDouble("hr_balance")));

                balances.add(balance);
            }
            tableView.setItems(balances);

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

    public void addCurrencyRate(String rate) {
        PreparedStatement pr = null;
        try {
            pr = myConn.prepareStatement("update utils set dollar=?");
            pr.setString(1, rate);
            pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pr != null) {
                    pr.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public int getCurrencyRate() {
        Statement statement = null;
        ResultSet resultSet = null;
        int rate = 0;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("Select dollar from utils");
            while (resultSet.next()) {
                rate = resultSet.getInt("dollar");
            }
            return rate;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Integer.parseInt(null);
    }

    public void getCurrentIncomeSum(Label sumLabel, Label dollarLabel, Label hrLabel) {
        Statement statement = null;
        ResultSet resultSet = null;
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
        Double s = 0.0;
        Double d = 0.0;
        Double h = 0.0;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("Select * from balance where who=99999");
            while (resultSet.next()) {
                s = resultSet.getDouble("sum_in");
                d = resultSet.getDouble("dollar_in");
                h = resultSet.getDouble("hr_in");
            }
            sumLabel.setText(formatter.format(s));
            dollarLabel.setText(formatter.format(d));
            hrLabel.setText(formatter.format(h));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addToTotalBalance(String SumIn, String DollarIn, String hrIn, String sumOut, String dollarOut, String hrOut) {

        String apple = Utils.convertDateToStandardFormat(Utils.getCurrentDate());
        try (PreparedStatement pr = myConn.prepareStatement("insert into admin_log_table ( module, type, ksum, kdollar, khr, csum, cdollar, chr, cr_by, date) values (?,?,?,?,?,?,?,?,?,?)")) {
            pr.setString(1, "Admin");
            pr.setString(2, "Kun yopish");
            pr.setString(3, SumIn);
            pr.setString(4, DollarIn);
            pr.setString(5, hrIn);
            pr.setString(6, sumOut);
            pr.setString(7, dollarOut);
            pr.setString(8, hrOut);
            pr.setString(9, user_id);
            pr.setString(10, apple);
        int i =    pr.executeUpdate();
        if(i>0){
            JOptionPane.showMessageDialog(null, "Kun yopildi");
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void perPersonBalance(Label hr, Label sum, Label dollar, String who) {
        Statement statement = null;
        ResultSet resultSet = null;
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
        Double s = 0.0;
        Double d = 0.0;
        Double h = 0.0;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("Select * from balance_v where who ='" + who + "'");
            while (resultSet.next()) {
                h = resultSet.getDouble("hr_balance");
                s = resultSet.getDouble("sum_balance");
                d = resultSet.getDouble("dollar_balance");
            }
            hr.setText(formatter.format(h));
            sum.setText(formatter.format(s));
            dollar.setText(formatter.format(d));

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

    public void selectOperwhoId(Label id, String who) {
        try {
            Statement statement = null;
            ResultSet resultSet = null;
            String idd = null;
            try {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("Select * from person where companyName ='" + who + "'");
                while (resultSet.next()) {
                  idd =   resultSet.getString("id");
                }
                id.setText(idd);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    DaoUtils.close(statement, resultSet);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exchange(String who, String type, String sum) {
        try {
           // String who1 = productDao.getComboBoxId("person", "companyName", who);
            PreparedStatement pr = null;

            try {
                switch (type) {
                    case "sum-dollar":
                        pr = myConn.prepareStatement("update balance set  sum_out=(sum_out-?), dollar_out=(dollar_out+?) where who=? ");
                        pr.setString(1, sum);
                        pr.setDouble(2, Double.valueOf(sum) / getCurrencyRate());
                        pr.setString(3, who);
                        pr.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Valyuta ayrboshlandi");
                        break;
                    case "hr-dollar":
                        pr = myConn.prepareStatement("update balance set hr_out=(hr_out-?), dollar_out=(dollar_out+?) where who=? ");
                        pr.setString(1, sum);
                        pr.setDouble(2, Double.valueOf(sum) / getCurrencyRate());
                        pr.setString(3, who);
                        pr.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Valyuta ayrboshlandi");
                        break;
                    case "dollar-sum":
                        pr = myConn.prepareStatement("update balance set dollar_out=(dollar_out-?), sum_out=(sum_out+?) where who=? ");
                        pr.setString(1, sum);
                        pr.setDouble(2, Double.valueOf(sum) * getCurrencyRate());
                        pr.setString(3, who);
                        pr.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Valyuta ayrboshlandi");
                        break;
                    case "dollar-hr":
                        pr = myConn.prepareStatement("update balance set dollar_out=(dollar_out-?), hr_out=(hr_out+?) where who=? ");
                        pr.setString(1, sum);
                        pr.setDouble(2, Double.valueOf(sum) * getCurrencyRate());
                        pr.setString(3, who);
                        pr.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Valyuta ayrboshlandi");
                        break;
                    case "hr-sum":
                        pr = myConn.prepareStatement("update balance set hr_out=(hr_out-?), sum_out=(sum_out+?) where who=? ");
                        pr.setString(1, sum);
                        pr.setString(2, sum);
                        pr.setString(3, who);
                        pr.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Valyuta ayrboshlandi");
                        break;
                    case "sum-hr":
                        pr = myConn.prepareStatement("update balance set sum_out=(sum_out-?), hr_out=(hr_out+?) where who=? ");
                        pr.setString(1, sum);
                        pr.setString(2, sum);
                        pr.setString(3, who);
                        pr.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Valyuta ayrboshlandi");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (pr != null) {
                        pr.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getCurrentOutcome(Label expenceSum, Label expenceDollar, Label expenceHR) {

        Statement statement = null;
        ResultSet resultSet = null;
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
        Double s = 0.0;
        Double d = 0.0;
        Double h = 0.0;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("Select * from balance where who=99999");
            while (resultSet.next()) {
                s = resultSet.getDouble("sum_out");
                d = resultSet.getDouble("dollar_out");
                h = resultSet.getDouble("hr_out");
            }
            expenceSum.setText(formatter.format(s));
            expenceDollar.setText(formatter.format(d));
            expenceHR.setText(formatter.format(h));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void revertOper(String id, String operType, String sum, String dollar, String hr, String who) {

        try {
           String name= getComboBoxId("sale_balance", "id",id);
             int i = 0;
             int ii = 0;
             int iii = 0;
             int b = 0;
             int bb = 0;
             int bbb = 0;
             int bbbb = 0;
            if (operType.equals("Kirim")) {
                try (PreparedStatement preparedStatement = myConn.prepareStatement("UPDATE balance set sum_in=(sum_in-?), dollar_in=(dollar_in-?), hr_in=(hr_in-?) where who=99999")) {
                    preparedStatement.setString(1, sum.trim().replaceAll("\\s+", ""));
                    preparedStatement.setString(2,dollar.trim().replaceAll("\\s+", ""));
                    preparedStatement.setString(3, hr.trim().replaceAll("\\s+", ""));
                  i=   preparedStatement.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(i>0) {
                    try (PreparedStatement preparedStatement = myConn.prepareStatement("UPDATE balance set sum_out=(sum_out-?), dollar_out=(dollar_out-?), hr_out=(hr_out-?) where who=" + name + "")) {
                        preparedStatement.setString(1, sum.trim().replaceAll("\\s+", ""));
                        preparedStatement.setString(2, dollar.trim().replaceAll("\\s+", ""));
                        preparedStatement.setString(3, hr.trim().replaceAll("\\s+", ""));
                      ii=  preparedStatement.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(ii>0) {
                        try (PreparedStatement preparedStatement = myConn.prepareStatement("delete from sale_balance  where id='" + id + "'")) {
                         iii=   preparedStatement.executeUpdate();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if(iii>0) {
                            try (PreparedStatement preparedStatement = myConn.prepareStatement("delete from report1  where sb_id=" + id + "")) {
                                preparedStatement.executeUpdate();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            } else if (operType.equals("Chiqim")) {
                try (PreparedStatement preparedStatement = myConn.prepareStatement("UPDATE balance set sum_in=(sum_in+?), dollar_in=(dollar_in+?), hr_in=(hr_in+?) where who=99999")) {
                    preparedStatement.setString(1, sum.trim().replaceAll("\\s+", ""));
                    preparedStatement.setString(2, dollar.trim().replaceAll("\\s+", ""));
                    preparedStatement.setString(3, hr.trim().replaceAll("\\s+", ""));
                    b = preparedStatement.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (b > 0) {
                    try (PreparedStatement preparedStatement = myConn.prepareStatement("UPDATE balance set sum_in=(sum_in-?), dollar_in=(dollar_in-?), hr_in=(hr_in-?) where who='" + name + "'")) {
                        preparedStatement.setString(1, sum.trim().replaceAll("\\s+", ""));
                        preparedStatement.setString(2, dollar.trim().replaceAll("\\s+", ""));
                        preparedStatement.setString(3, hr.trim().replaceAll("\\s+", ""));
                      bb=  preparedStatement.executeUpdate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(bb>0) {
                        try (PreparedStatement preparedStatement = myConn.prepareStatement("delete from sale_balance  where id='" + id + "'")) {
                         bbb=   preparedStatement.executeUpdate();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                            if(bbb>0) {
                                try (PreparedStatement preparedStatement = myConn.prepareStatement("delete from report1  where sb_id=" + id + "")) {
                                 bbbb=   preparedStatement.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if(bbbb>0){
                                    try (PreparedStatement preparedStatement = myConn.prepareStatement("UPDATE balance set sum_out=(sum_out-?), dollar_out=(dollar_out-?), hr_out=(hr_out-?) where who=99999")) {
                                        preparedStatement.setString(1, sum.trim().replaceAll("\\s+", ""));
                                        preparedStatement.setString(2, dollar.trim().replaceAll("\\s+", ""));
                                        preparedStatement.setString(3, hr.trim().replaceAll("\\s+", ""));
                                        preparedStatement.executeUpdate();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getComboBoxId(String tableName, String columnName, String name) throws SQLException {
        Statement st = null;
        ResultSet rs = null;
        try {
            String q = "SELECT who FROM " + tableName + " WHERE " + columnName.trim() + "= '" + name.trim() + "'";
            st = myConn.createStatement();
            rs = st.executeQuery(q);
            if (rs.next()) {
                return rs.getString("who");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DaoUtils.close(st,rs);
        }
        return null;

    }

}
