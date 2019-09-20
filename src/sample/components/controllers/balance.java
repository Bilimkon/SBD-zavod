package sample.components.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.components.dao.balanceDao;
import sample.components.models.BalanceTotal;

import java.net.URL;
import java.util.ResourceBundle;

public class balance implements Initializable {
    @FXML
    private TableView balanceTable1;
    sample.components.dao.balanceDao balanceDao = new balanceDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        TableColumn product = new TableColumn("Ombor");
        TableColumn ish1 = new TableColumn("1-Ish");
        TableColumn ish2 = new TableColumn("2-Ish");
        TableColumn sell = new TableColumn("Savdo");
        TableColumn kassasum = new TableColumn("Kassa-so'm");
        TableColumn kassadollar = new TableColumn("Kassa-dollar");
        TableColumn kassahr = new TableColumn("Kassa-hr");
        TableColumn kassavhr = new TableColumn("Kassa-vhr");
        TableColumn total_all = new TableColumn("Umumiy");

        balanceTable1.getColumns().addAll(product, ish1, ish2, sell, kassasum, kassadollar, kassahr, kassavhr, total_all);

        product.setCellValueFactory(new PropertyValueFactory<BalanceTotal, String>("product"));
        ish1.setCellValueFactory(new PropertyValueFactory<BalanceTotal, String>("p2"));
        ish2.setCellValueFactory(new PropertyValueFactory<BalanceTotal, String>("p3"));
        sell.setCellValueFactory(new PropertyValueFactory<BalanceTotal, String>("sell"));
        kassasum.setCellValueFactory(new PropertyValueFactory<BalanceTotal, String>("sum"));
        kassadollar.setCellValueFactory(new PropertyValueFactory<BalanceTotal, String>("dollar"));
        kassahr.setCellValueFactory(new PropertyValueFactory<BalanceTotal, String>("hr"));
        kassavhr.setCellValueFactory(new PropertyValueFactory<BalanceTotal, String>("vhr"));
        total_all.setCellValueFactory(new PropertyValueFactory<BalanceTotal, String>("total_all"));

        balanceTable();

    }

    private void balanceTable() {
        try {
             balanceDao.balanceTableDao(balanceTable1);
        } catch (Exception e) {
            e.printStackTrace();

        }


    }
}
