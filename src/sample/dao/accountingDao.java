package sample.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import sample.components.sell.Utils.Utils;
import sample.components.sell.productTableView.balance;
import sample.model.AccountXarajat;
import sample.model.ShartnomaTable;
import sample.model.TableExchangeDollar;
import sample.model.core.manList;
import sample.utils.Workbookcontroller;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class accountingDao {

    private Connection myConn = null;
    String apple = Utils.convertDateToStandardFormat(Utils.getCurrentDate());

    public accountingDao() {
        try {
            myConn = database.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLabels(Label accHR_label, Label accVAL_label) throws SQLException {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
        try (PreparedStatement pr = myConn.prepareStatement("select * from total_balance"); ResultSet rs = pr.executeQuery()) {
            while (rs.next()) {
                accHR_label.setText(formatter.format(rs.getDouble("hr")));
                accVAL_label.setText(formatter.format(rs.getDouble("vhr")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exchangeHr(String operType, String sum, String dollarRate) {

        try (PreparedStatement pr = myConn.prepareStatement("update total_balance set hr=(hr-?), vhr=(vhr+?) where id=1 ")) {
            pr.setString(1, sum);
            pr.setDouble(2, Double.valueOf(sum) / Double.valueOf(dollarRate));
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PreparedStatement preparedStatement = myConn.prepareStatement("INSERT INTO dollarExchange (hr, dollar, rate, who, description, sana) VALUES(?,?,?,?,?,?)")) {
            preparedStatement.setString(1, sum);
            preparedStatement.setString(2, "1");
            preparedStatement.setString(3, dollarRate);
            preparedStatement.setString(4, "1");
            preparedStatement.setString(5, "1");
            preparedStatement.setString(6, apple);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addHarajat(String schot, String schotSana, String firma, String shartnoma, String hr, String dollar, String izoh) {
        int i =0;
        try {
            try (PreparedStatement preparedStatement = myConn.prepareStatement("Insert into accountExpense (schot, schotDate, firmaName, shartnoma, hr, dollar, izoh, shartnomaDate) values(?,?,?,?,?,?,?,?)")) {
                preparedStatement.setString(1, schot);
                preparedStatement.setString(2, schotSana);
                preparedStatement.setString(3, firma);
                preparedStatement.setString(4, shartnoma);
                preparedStatement.setString(5, hr);
                preparedStatement.setString(6, dollar);
                preparedStatement.setString(7, izoh);
                preparedStatement.setString(8, apple);
                i =preparedStatement.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addShartnoma(String company, String name, String date) {
        try (PreparedStatement preparedStatement = myConn.prepareStatement("INSERT INTO shartnoma (company, name, date) VALUES(?,?,?)")) {
            preparedStatement.setString(1, company);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, date);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCompanyCombobox(ComboBox<String> comboBox) throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT companyName FROM person order by companyName");
            while (resultSet.next()) {  // loop
                // Now add the comboBox addAll statement
                comboBox.getItems().addAll(resultSet.getString("companyName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(statement, resultSet);
        }
    }

    public void addCompanyList(ComboBox<String> comboBox) throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT name FROM shartnoma order by name");
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

    public void FiltlerCompanyName(ComboBox<String> comboBox) throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT companyName FROM person  ");
            while (resultSet.next()) {  // loop
                // Now add the comboBox addAll statement
                comboBox.getItems().addAll(resultSet.getString("companyName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DaoUtils.close(statement, resultSet);
        }
    }

    public void shartnomaTableDao(TableView tableView) {

        try {
            Statement statement = null;
            ResultSet resultSet = null;

            //List to add items
            ObservableList<ShartnomaTable> colors = FXCollections.observableArrayList();
            try {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM shartnoma ORDER BY id ");
                while (resultSet.next()) {
                    ShartnomaTable color = new ShartnomaTable();
                    color.setId(resultSet.getString("id"));
                    color.setCompany(resultSet.getString("company"));
                    color.setName(resultSet.getString("name"));
                    color.setDate(resultSet.getString("date"));
                    colors.add(color);
                }
                tableView.setItems(colors);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DaoUtils.close(statement, resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteShartnoma(String id) {
        try (PreparedStatement preparedStatement = myConn.prepareStatement(" Delete from shartnoma where id='" + id + "'")) {
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void deleteXarajat(String id, String firma, String hr, String dollar) {
        int i = 0;
        try (PreparedStatement preparedStatement = myConn.prepareStatement(" UPDATE total_balance set hr=(hr+?),  vhr=(vhr+?) where id=1")) {
            preparedStatement.setString(1, hr);
            preparedStatement.setString(2, dollar);
            i =  preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if(i>0) {
            int ii =0;
            try (PreparedStatement preparedStatement = myConn.prepareStatement(" Delete from accountExpense where id=" + id + " ")) {
              ii=  preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            if(ii>0) {
                try (PreparedStatement preparedStatement = myConn.prepareStatement("Update balance set dollar_in=(dollar_in-?), hr_in=(hr_in-?) where who=(select firmaname from accountexpense where id = "+id+")")) {
                    preparedStatement.setString(1, dollar);
                    preparedStatement.setString(2, hr);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public void xarajatTableDao(TableView tablleAccountHarajat, String company, String date1, String date2, Label accountingTotalHr, Label accountingTotalDollar) {

        try {
            Statement statement = null;
            ResultSet resultSet = null;
            PreparedStatement pr = null;
            ResultSet myRs = null;

            //List to add items
            ObservableList<AccountXarajat> colors = FXCollections.observableArrayList();
            try {
                DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
                symbols.setGroupingSeparator(' ');
                DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);

                if (!company.isEmpty() && !date1.isEmpty() && !date2.isEmpty()) {
                    statement = myConn.createStatement();
                    resultSet = statement.executeQuery("Select * from accountexpense_v where firmaName ='" + company + "' and substr(shartnomaDate,7,10) between '" + date1 + "' and '" + date2 + "'  ");
                    pr = myConn.prepareStatement("Select sum(hr) total_hr, sum(dollar) total_dollar from accountexpense_v where firmaName ='" + company + "' and substr(shartnomaDate,7,10) between '" + date1 + "' and '" + date2 + "' ");
                    myRs = pr.executeQuery();
                } else if (company.isEmpty() && !date1.isEmpty() && !date2.isEmpty()) {
                    statement = myConn.createStatement();
                    resultSet = statement.executeQuery("Select * from accountexpense_v where substr(shartnomaDate,7,10) between '" + date1 + "' and '" + date2 + "' ");

                    pr = myConn.prepareStatement("Select sum(hr) total_hr, sum(dollar) total_dollar from accountexpense_v where substr(shartnomaDate,7,10) between '" + date1 + "' and '" + date2 + "' ");
                    myRs = pr.executeQuery();
                } else {
                    statement = myConn.createStatement();
                    resultSet = statement.executeQuery("SELECT * FROM accountexpense_v ORDER BY id limit 300 ");

                    pr = myConn.prepareStatement("Select sum(hr) total_hr, sum(dollar) total_dollar from accountexpense_v ORDER BY id limit 300  ");
                    myRs = pr.executeQuery();
                }

                if (resultSet != null) {
                    while (resultSet.next()) {
                        AccountXarajat color = new AccountXarajat();
                        color.setId(resultSet.getString("id"));
                        color.setSchot(resultSet.getString("schot"));
                        color.setsDate(resultSet.getString("schotDate"));
                        color.setFirma(resultSet.getString("firmaName"));
                        color.setShartnoma(resultSet.getString("shartnoma"));
                        color.setHr(formatter.format(resultSet.getDouble("hr")));
                        color.setDollar(formatter.format(resultSet.getDouble("dollar")));
                        color.setDescription(resultSet.getString("izoh"));
                        colors.add(color);
                    }
                }
                tablleAccountHarajat.setItems(colors);

                if (myRs != null && myRs.next()) {
                    accountingTotalHr.setText(formatter.format(myRs.getDouble("total_hr")));
                    accountingTotalDollar.setText(formatter.format(myRs.getDouble("total_dollar")));
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DaoUtils.close(statement, resultSet);
                if (pr != null) {
                    pr.close();
                }
                if (myRs != null) {
                    myRs.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tableExchangeDao(TableView tableExchange) {
        try {
            Statement statement = null;
            ResultSet resultSet = null;
            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
            symbols.setGroupingSeparator(' ');
            DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
            //List to add items
            ObservableList<TableExchangeDollar> colors = FXCollections.observableArrayList();
            try {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM dollarExchange ORDER BY id limit 200 ");
                while (resultSet.next()) {
                    TableExchangeDollar color = new TableExchangeDollar();
                    color.setId(resultSet.getString("id"));
                    color.setHr(formatter.format(resultSet.getDouble("hr")));
                    color.setDollar(resultSet.getString("dollar"));
                    color.setRate(resultSet.getString("rate"));
                    color.setDescription(resultSet.getString("description"));
                    color.setSana(resultSet.getString("sana"));
                    colors.add(color);
                }
                tableExchange.setItems(colors);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DaoUtils.close(statement, resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public manList selectListItem(String name) {
        ProductDao productDao = new ProductDao();
        try {
            Statement statement = null;
            ResultSet resultSet = null;
            String type_id = productDao.getComboBoxId("person", "companyName", name);

            try {
                manList product = null;
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM person where id=" + type_id);
                while (resultSet.next()) {
                    product = new manList(
                            resultSet.getString("id"),
                            resultSet.getString("id"),
                            resultSet.getString("id"),
                            resultSet.getString("id")
                    );
                    return product;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DaoUtils.close(statement, resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void aBalanceTable(TableView tableAccountBalance) {
        try {
            Statement statement = null;
            ResultSet resultSet = null;
            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
            symbols.setGroupingSeparator(' ');
            DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
            //List to add items
            ObservableList<balance> colors = FXCollections.observableArrayList();
            try {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM balance_v ORDER BY who ");

                while (resultSet.next()) {
                    balance color = new balance();
                    color.setWho(resultSet.getString("who"));
                    color.setSum_balance(formatter.format(resultSet.getDouble("sum_balance")));
                    color.setDollar_balance(formatter.format(resultSet.getDouble("dollar_balance")));
                    color.setHr_balance(formatter.format(resultSet.getDouble("hr_balance")));

                    colors.add(color);
                }
                tableAccountBalance.setItems(colors);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DaoUtils.close(statement, resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xarajatExcellTableDao(Button button, String company, String date1, String date2) {

        try {
            Statement statement = null;
            ResultSet resultSet = null;
            //List to add items
            try {
                if (!company.isEmpty() && !date1.isEmpty() && !date2.isEmpty()) {
                    statement = myConn.createStatement();
                    resultSet = statement.executeQuery("Select * from accountexpense_v where firmaName ='" + company + "' and substr(shartnomaDate,7,10) between '" + date1 + "' and '" + date2 + "'  ");
                    if(resultSet.next()){
                        Workbookcontroller workbookcontroller = new Workbookcontroller();
                        workbookcontroller.datebaseToExcelResultset("accountexpense_v","Buhgalteriya.xls", resultSet);
                    }
                } else if (company.equals("") && !date1.isEmpty() && !date2.isEmpty()) {
                    statement = myConn.createStatement();
                    resultSet = statement.executeQuery("Select * from accountexpense_v where substr(shartnomaDate,7,10) between '" + date1 + "' and '" + date2 + "' ");
                    if(resultSet.next()){
                        Workbookcontroller workbookcontroller = new Workbookcontroller();
                        workbookcontroller.datebaseToExcelResultset("accountexpense_v","Buhgalteriya.xls", resultSet);
                    }
                } else {
                    statement = myConn.createStatement();
                    resultSet = statement.executeQuery("SELECT * FROM accountexpense_v ORDER BY id limit 300 ");
                    if(resultSet.next()){
                        Workbookcontroller workbookcontroller = new Workbookcontroller();
                        workbookcontroller.datebaseToExcelResultset("accountexpense_v","Buhgalteriya.xls", resultSet);
                    }
                }

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
