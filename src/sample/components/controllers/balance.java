package sample.components.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.components.dao.balanceDao;
import sample.components.models.BalanceTotal;

import java.net.URL;
import java.util.ResourceBundle;

public class balance implements Initializable {

    @FXML private TableView tableBalance;
    @FXML private TableView tableAll;
    @FXML private Label totalSum;
    @FXML private Label totalDollar;
    @FXML private Label totalHr;
    @FXML private Label total;
    @FXML private StackedBarChart Barchart;
    sample.components.dao.balanceDao balanceDao = new balanceDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        TableColumn product = new TableColumn("Ombor");
        TableColumn ish2 = new TableColumn("Laminant ish/ch");
        TableColumn sell = new TableColumn("Savdo");
        TableColumn kassasum = new TableColumn("Kassa-so'm");
        TableColumn kassadollar = new TableColumn("Kassa-dollar");
        TableColumn kassahr = new TableColumn("Kassa-hr");
        TableColumn adminSum = new TableColumn("Admin so'm");
        TableColumn adminDollar = new TableColumn("Admin do'llar ");
        TableColumn adminHr = new TableColumn("Admin hr");
        TableColumn adminVhr = new TableColumn("Admin vhr");


        tableAll.getColumns().addAll(product,  ish2, sell, kassasum, kassadollar, kassahr, adminSum, adminDollar, adminHr, adminVhr);

        product.setCellValueFactory(new PropertyValueFactory<BalanceTotal, String>("product"));
        ish2.setCellValueFactory(new PropertyValueFactory<BalanceTotal, String>("p3"));
        sell.setCellValueFactory(new PropertyValueFactory<BalanceTotal, String>("sell"));
        kassasum.setCellValueFactory(new PropertyValueFactory<BalanceTotal, String>("c_sum"));
        kassadollar.setCellValueFactory(new PropertyValueFactory<BalanceTotal, String>("c_dollar"));
        kassahr.setCellValueFactory(new PropertyValueFactory<BalanceTotal, String>("c_hr"));
        adminSum.setCellValueFactory(new PropertyValueFactory<BalanceTotal, String>("sum"));
        adminDollar.setCellValueFactory(new PropertyValueFactory<BalanceTotal, String>("dollar"));
        adminHr.setCellValueFactory(new PropertyValueFactory<BalanceTotal, String>("hr"));
        adminVhr.setCellValueFactory(new PropertyValueFactory<BalanceTotal, String>("vhr"));


        balanceTable();
        initializeBalanceTable();
        balanceTableAll();
        sumAll();
        chart();
        getTotalBalnace();

    }

    private void initializeBalanceTable(){
        TableColumn who = new TableColumn("Xaridor");
        TableColumn sum = new TableColumn("So'm");
        TableColumn dollar = new TableColumn("Do'llar");
        TableColumn hr = new TableColumn("Hisob raqam");

        tableBalance.getColumns().addAll(who, sum, dollar, hr);

        who.setCellValueFactory(new PropertyValueFactory<balance, String>("who"));
        sum.setCellValueFactory(new PropertyValueFactory<balance, String>("sum_balance"));
        dollar.setCellValueFactory(new PropertyValueFactory<balance, String>("dollar_balance"));
        hr.setCellValueFactory(new PropertyValueFactory<balance, String>("hr_balance"));
    }


    private void balanceTable() {
        try {
             balanceDao.tableBalance(tableBalance, totalSum, totalDollar, totalHr);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void balanceTableAll() {
        try {
            balanceDao.balanceTableDao(tableAll);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void sumAll(){
        try{
            balanceDao.sumAll(total, totalSum, totalDollar, totalHr);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void chart(){
        try{
            balanceDao.chart(Barchart);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getTotalBalnace(){
        try{
            String total1 = total.getText().trim().trim().replaceAll("\\s+", "");
            balanceDao.insertTotalDayily(total1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
