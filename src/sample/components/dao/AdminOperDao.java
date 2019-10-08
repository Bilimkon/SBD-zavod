package sample.components.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import sample.Login;
import sample.components.models.AdminOperModel;
import sample.components.sell.Utils.Utils;
import sample.dao.DaoUtils;
import sample.dao.database;
import sample.utils.Workbookcontroller;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class AdminOperDao {

    Connection myConn = null;
    private String user_id = String.valueOf(Login.currentUser.getId());
    private String apple = Utils.convertDateToStandardFormat(Utils.getCurrentDate());
    Workbookcontroller workbookcontroller = new Workbookcontroller();

    public AdminOperDao(){
        try {
            myConn = database.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void addExpenceDao(String type, String sum, String dollar, String hr, String vhr, String comment) {
        try(PreparedStatement preparedStatement = myConn.prepareStatement("INSERT INTO AdminOper (type, sum, dollar, hr, vhr, comment, cr_on, cr_by) VALUES(?,?,?,?,?,?,?,?)")){
            preparedStatement.setString(1, type);
            preparedStatement.setString(2, sum);
            preparedStatement.setString(3, dollar);
            preparedStatement.setString(4, hr);
            preparedStatement.setString(5, vhr);
            preparedStatement.setString(6, comment);
            preparedStatement.setString(7, apple);
            preparedStatement.setString(8, user_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(PreparedStatement preparedStatement = myConn.prepareStatement("Update total_balance set sum=(sum+?), dollar=(dollar+?), hr=(hr+?), vhr=(vhr+?) where id =1")) {
            preparedStatement.setString(1, sum);
            preparedStatement.setString(2, dollar);
            preparedStatement.setString(3, hr);
            preparedStatement.setString(4, vhr);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addExpenceDaoChiqim(String type, String sum, String dollar, String hr, String vhr, String comment) {
        try(PreparedStatement preparedStatement = myConn.prepareStatement("INSERT INTO AdminOper (type, sum, dollar, hr, vhr, comment, cr_on, cr_by) VALUES(?,?,?,?,?,?,?,?)")){
            preparedStatement.setString(1, type);
            preparedStatement.setString(2, sum);
            preparedStatement.setString(3, dollar);
            preparedStatement.setString(4, hr);
            preparedStatement.setString(5, vhr);
            preparedStatement.setString(6, comment);
            preparedStatement.setString(7, apple);
            preparedStatement.setString(8, user_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(PreparedStatement preparedStatement = myConn.prepareStatement("Update total_balance set sum=(sum-?), dollar=(dollar-?), hr=(hr-?), vhr=(vhr-?) where id =1")) {
            preparedStatement.setString(1, sum);
            preparedStatement.setString(2, dollar);
            preparedStatement.setString(3, hr);
            preparedStatement.setString(4, vhr);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void total_balance(Label sum, Label dollar, Label account, Label VAL) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
            symbols.setGroupingSeparator(' ');
            DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("Select * from total_balance where id =1");
            while (resultSet.next()) {
                sum.setText(formatter.format(resultSet.getDouble("sum")));
                dollar.setText(formatter.format(resultSet.getDouble("dollar")));
                account.setText(formatter.format(resultSet.getDouble("hr")));
                VAL.setText(formatter.format(resultSet.getDouble("vhr")));
            }
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

    public void adminTable(String date1 , String date2, TableView tableView, Label sum, Label dollar, Label hr, Label vhr) {
        try {
            Statement statement = null;
            ResultSet resultSet = null;
            Statement statement1 = null;
            ResultSet resultSet1 = null;



            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
            symbols.setGroupingSeparator(' ');
            DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);

            //List to add items
            ObservableList<AdminOperModel> invoices = FXCollections.observableArrayList();
            try {
                if(!date1.isEmpty() && !date2.isEmpty()){
                    statement = myConn.createStatement();
                    resultSet =statement.executeQuery("Select * from AdminOper where substr(cr_on,7,10)  BETWEEN '"+date1+"' AND '"+date2+"' ");

                    statement1 = myConn.createStatement();
                    resultSet1 =statement1.executeQuery("Select sum(sum) total_sum, sum(dollar) total_dollar, sum(hr) total_hr, sum(vhr) total_vhr from AdminOper where substr(cr_on,7,10)  BETWEEN '"+date1+"' AND '"+date2+"' ");


                } else{
                    statement = myConn.createStatement();
                    resultSet =statement.executeQuery("Select * from AdminOper order by id limit 300");

                    statement1 = myConn.createStatement();
                    resultSet1 =statement1.executeQuery("Select sum(sum) total_sum, sum(dollar) total_dollar, sum(hr) total_hr, sum(vhr) total_vhr   from AdminOper order by id limit 300");


                }

                if (resultSet != null) {
                    while (resultSet.next()) {
                        AdminOperModel invoice = new AdminOperModel();
                        invoice.setId(resultSet.getString("id"));
                        invoice.setType(resultSet.getString("type"));
                        invoice.setSum(formatter.format(resultSet.getDouble("sum")));
                        invoice.setDollar(formatter.format(resultSet.getDouble("dollar")));
                        invoice.setHr(formatter.format(resultSet.getDouble("hr")));
                        invoice.setVhr(formatter.format(resultSet.getDouble("vhr")));
                        invoice.setComment(resultSet.getString("comment"));
                        invoice.setCr_on(resultSet.getString("cr_on"));

                        invoices.add(invoice);

                    }
                }

                if (resultSet1 != null && resultSet1.next()) {
                    sum.setText(formatter.format(resultSet1.getDouble("total_sum")));
                    dollar.setText(formatter.format(resultSet1.getDouble("total_dollar")));
                    hr.setText(formatter.format(resultSet1.getDouble("total_hr")));
                    vhr.setText(formatter.format(resultSet1.getDouble("total_vhr")));
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

    public void adminTableExcell(String date1 , String date2, TableView tableView) {
        try {
            Statement statement = null;
            ResultSet resultSet = null;



            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
            symbols.setGroupingSeparator(' ');
            DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);

            //List to add items
            ObservableList<AdminOperModel> invoices = FXCollections.observableArrayList();
            try {
                if(!date1.isEmpty() && !date2.isEmpty()){
                    statement = myConn.createStatement();
                    resultSet =statement.executeQuery("Select * from AdminOper where substr(cr_on,7,10)  BETWEEN '"+date1+"' AND '"+date2+"' ");



                } else{
                    statement = myConn.createStatement();
                    resultSet =statement.executeQuery("Select * from AdminOper order by id limit 300");

                }
                if(resultSet.next()){
                    workbookcontroller.datebaseToExcelResultset("AdminOper","Admin_operatsiyalari.xls",resultSet);
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



    public void revertOperKirim(String id, String sum, String dollar, String hr, String vhr) {
        try(PreparedStatement preparedStatement = myConn.prepareStatement("Update total_balance set sum=(sum-?), dollar=(dollar-?), hr=(hr-?), vhr=(vhr-?) where id =1")) {
            preparedStatement.setString(1, sum);
            preparedStatement.setString(2, dollar);
            preparedStatement.setString(3, hr);
            preparedStatement.setString(4, vhr);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(PreparedStatement preparedStatement = myConn.prepareStatement("Delete from AdminOper where id='"+id+"'")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void revertOperChiqim(String id,   String sum, String dollar, String hr, String vhr) {
        try(PreparedStatement preparedStatement = myConn.prepareStatement("Update total_balance set sum=(sum+?), dollar=(dollar+?), hr=(hr+?), vhr=(vhr+?) where id =1")) {
            preparedStatement.setString(1, sum);
            preparedStatement.setString(2, dollar);
            preparedStatement.setString(3, hr);
            preparedStatement.setString(4, vhr);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try(PreparedStatement preparedStatement = myConn.prepareStatement("Delete from AdminOper where id='"+id+"'")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
