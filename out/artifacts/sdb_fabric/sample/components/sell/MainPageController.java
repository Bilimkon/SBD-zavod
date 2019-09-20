//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sample.components.sell;

import com.jfoenix.controls.JFXDatePicker;
import com.sun.istack.internal.Nullable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;
import sample.Login;
import sample.components.models.AllCurrencyValues;
import sample.components.models.Exchange;
import sample.components.sell.Core.CheckSums;
import sample.components.sell.Core.History;
import sample.components.sell.Core.Models.*;
import sample.components.sell.DAO.*;
import sample.components.sell.Utils.PrinterService;
import sample.components.sell.Utils.Utils;
import sample.components.sell.productTableView.OperTable;
import sample.components.sell.productTableView.ProductTable;
import sample.components.sell.productTableView.balance;
import sample.components.sell.views.CustomItems.CustomBasketItem.ShopItemListItem;
import sample.dao.SystemUtilsDao;
import sample.dao.database;
import sample.model.Debt;
import sample.model.User;
import sample.utils.ComboBoxAutoComplete;
import sample.utils.Workbookcontroller;
import sample.utils.utils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Humoyun Qo'rg'onov  SBD(Software Business Development)
 */
public class MainPageController extends Parent implements Initializable {
    private ProductDao productDao;
    UtilsDao utilsDao = new UtilsDao();
    utils utils = new utils();
    sample.dao.DaoUtils daoUtils = new sample.dao.DaoUtils();
    @FXML
    private TextField textSampleIzlash;
    private Connection myConn;
    @FXML
    private TableView<ProductTable> tableSampleManual;
    @FXML
    private VBox addedItemsList;
    @FXML
    private Label totalCost;
    @FXML
    private TextField scanCodeField;
    @FXML
    private Label idUserName;
    @FXML
    private Label idStartDate;
    @FXML
    private Label ClockText;
    @FXML
    private Button BtnSell;
    @FXML
    private Button btnClose;
    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnAccount;
    @FXML
    private TextField textAccount;
    @FXML
    private ListView<String> typeList;
    /**
     * Tab operations
     */
    @FXML
    private ComboBox<String> operType;
    @FXML
    private TextField operSum;
    @FXML
    private ComboBox<String> operWho;
    @FXML
    private ComboBox<String> operCurrency;
    @FXML
    private TextArea operDescription;
    @FXML
    private TableView operTable;
    @FXML
    private ComboBox<String> operCustomerFilter;
    @FXML
    private TableView operBalanceTable;
    @FXML
    private Label OperDollarLabel;
    @FXML
    private Label CurrentIncomeSum;
    @FXML
    private Label CurrentIncomeDollar;
    @FXML
    private Label CurrentIncomeHR;
    @FXML
    private Label PersonHRBalance;
    @FXML
    private Label PersonSumBalance;
    @FXML
    private Label PersonDollarBalance;
    @FXML
    private DatePicker operDanFilter;
    @FXML
    private DatePicker operGachaFilter;
    @FXML
    private TableView HistoryTable;
    @FXML
    private JFXDatePicker HistoryDan;
    @FXML
    private JFXDatePicker HistoryGacha;
    @FXML
    private TableView QarzTable;
    @FXML
    private JFXDatePicker QarzDan;
    @FXML
    private JFXDatePicker QarzGacha;
    @FXML
    private TableView tableDebtorsOper;
    @FXML
    TextField text_per;
    @FXML
    Label labelDollar;
    @FXML
    Label labelHR;
    @FXML
    TextField operDollar;
    @FXML
    TextField operHR;
    @FXML
    ComboBox<String> operHistoryWho;
    @FXML
    ComboBox<String> ComboBoxBalance;
    @FXML
    Label text_balance_sum;
    @FXML
    Label text_balance_dollar;
    @FXML
    Label text_balance_hr;
    @FXML
    Label text_balance_name;
    @FXML
    ComboBox<String> ChangeType;
    @FXML
    TextField ChangeSum;
    @FXML
    TableView tarixCheckTable;
    //Change tab
    @FXML
    private DatePicker ExchangeDan;
    @FXML
    private DatePicker ExchangeGacha;
    @FXML
    private TableView ExchangeTable;
    //Expences
    @FXML
    private Label ExpenceSum;
    @FXML
    private Label ExpenceDollar;
    @FXML
    private Label ExpenceHR;
    @FXML
    private ComboBox<String> sellactionSelectCustomer;
    @FXML
    private Label ExchangeTotalQuantity;
    @FXML
    private ComboBox<String> exchangeSelectName;
    @FXML
    private ComboBox<String> tarixSelectName;
    @FXML
    private Label tarixTotalQuantity;
    @FXML
    private Label tarixTotalCost;
    @FXML
    private Label labelOperSum;
    @FXML
    private Label labelOperDollar;
    @FXML
    private Label labelOperHr;
    //
    @FXML
    private Label sellsumT;
    @FXML
    private Label selldollarT;
    @FXML
    private Label sellhrT;
    @FXML
    private Label sellsumP;
    @FXML
    private Label selldollarP;
    @FXML
    private Label sellhrP;
    @FXML
    private Label revertLabel_id;
    @FXML private Label OperWhoId;
    @FXML private Label LabelBalanceIdWho;




    @FXML
    private Label sell_total_quantity;


    String user_id = String.valueOf(Login.currentUser.getId());
    String apple = Utils.convertDateToStandardFormat(Utils.getCurrentDate());


    /**
     * End of tab operations
     */
    private ObservableList<ProductTable> productTables;
    public static List<BasketItem> basket = new ArrayList<>();
    private CreditModel credit = null;

    Task<Void> longTaskRun;
    private HistoryDao historyDao;
    OperDao operDao = new OperDao();
    sample.dao.ProductDao productDao1 = new sample.dao.ProductDao();
    printer printer = new printer();
    PrinterService printerService = new PrinterService();
    private boolean exit;
    Thread clock;

    {
        try {
            historyDao = new HistoryDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeExchangeTable() {
        TableColumn id = new TableColumn("Id");
        TableColumn name = new TableColumn("Nomi");
        TableColumn barcode = new TableColumn("Barcode");
        TableColumn type = new TableColumn("Turi");
        TableColumn quantity = new TableColumn("Miqdori");
        TableColumn comment = new TableColumn("Izox");
        TableColumn cr_on = new TableColumn("Sana");
        TableColumn cr_by = new TableColumn("Ishchi");

        ExchangeTable.getColumns().addAll(id, cr_on, name, barcode, type, quantity, comment, cr_by);

        id.setCellValueFactory(new PropertyValueFactory<Exchange, String>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Exchange, String>("name"));
        barcode.setCellValueFactory(new PropertyValueFactory<Exchange, String>("barcode"));
        type.setCellValueFactory(new PropertyValueFactory<Exchange, String>("type"));
        quantity.setCellValueFactory(new PropertyValueFactory<Exchange, String>("quantity"));
        comment.setCellValueFactory(new PropertyValueFactory<Exchange, String>("comment"));
        cr_on.setCellValueFactory(new PropertyValueFactory<Exchange, String>("cr_on"));
        cr_by.setCellValueFactory(new PropertyValueFactory<Exchange, String>("cr_by"));
    }

    private void initializeOperTable() {
        TableColumn<OperTable, String> id = new TableColumn<>("Tartib raqami");
        TableColumn<OperTable, String> type = new TableColumn<>("To'lov turi");
        TableColumn<OperTable, String> who = new TableColumn<>("Kimga/Kimdan");
        TableColumn<OperTable, String> sum = new TableColumn<>("So'm");
        TableColumn<OperTable, String> dollar = new TableColumn<>("Dollar");
        TableColumn<OperTable, String> hr = new TableColumn<>("hr");
        TableColumn<OperTable, String> description = new TableColumn<>("Ma'lumot");
        TableColumn<OperTable, String> cr_by = new TableColumn<>("Sotuvchi");
        TableColumn<OperTable, String> date = new TableColumn<>("Sana");
        TableColumn<OperTable, String> currency = new TableColumn<>("Valyuta");
        TableColumn<OperTable, String> percentage = new TableColumn<>("%");
        TableColumn<OperTable, String> subtotal = new TableColumn<>("% bilan");

        // operTable.getColumns().addAll(id, type, who, sum, dollar, hr, cr_by, date, currency, percentage, subtotal, description);
        operTable.getColumns().addAll(id, date, type, sum, dollar, hr, cr_by, who, description, currency, percentage, subtotal);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        who.setCellValueFactory(new PropertyValueFactory<>("who"));
        sum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        dollar.setCellValueFactory(new PropertyValueFactory<>("dollar"));
        hr.setCellValueFactory(new PropertyValueFactory<>("hr"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        cr_by.setCellValueFactory(new PropertyValueFactory<>("cr_by"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        currency.setCellValueFactory(new PropertyValueFactory<>("currency"));
        percentage.setCellValueFactory(new PropertyValueFactory<>("percentage"));
        subtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
    }

    private void intializeBalanceTable() {
        TableColumn<balance, String> who = new TableColumn<>("Kompaniya nomi");
        TableColumn<balance, Integer> sum_in = new TableColumn<>("Kirim So'mda");
        TableColumn<balance, Integer> sum_out = new TableColumn<>("Chiqim So'mda");
        TableColumn<balance, Integer> sum_balance = new TableColumn<>("Balans So'mda");
        TableColumn<balance, Integer> dollar_in = new TableColumn<>("$ da kirim");
        TableColumn<balance, Integer> dollar_out = new TableColumn<>("$ da chiqim");
        TableColumn<balance, Integer> dollar_balance = new TableColumn<>(" $ da balans");
        TableColumn<balance, Integer> hr_in = new TableColumn<>("Kirim hisib raqam");
        TableColumn<balance, Integer> hr_out = new TableColumn<>("Chiqim hisob raqam");
        TableColumn<balance, Integer> hr_balance = new TableColumn<>("Hisob raqam balans");


        operBalanceTable.getColumns().addAll(who, sum_in, sum_out, sum_balance, dollar_in, dollar_out, dollar_balance, hr_in, hr_out, hr_balance);

        who.setCellValueFactory(new PropertyValueFactory<>("who"));
        sum_in.setCellValueFactory(new PropertyValueFactory<>("sum_in"));
        sum_out.setCellValueFactory(new PropertyValueFactory<>("sum_out"));
        sum_balance.setCellValueFactory(new PropertyValueFactory<>("sum_balance"));
        dollar_in.setCellValueFactory(new PropertyValueFactory<>("dollar_in"));
        dollar_out.setCellValueFactory(new PropertyValueFactory<>("dollar_out"));
        dollar_balance.setCellValueFactory(new PropertyValueFactory<>("dollar_balance"));
        hr_in.setCellValueFactory(new PropertyValueFactory<>("hr_in"));
        hr_out.setCellValueFactory(new PropertyValueFactory<>("hr_out"));
        hr_balance.setCellValueFactory(new PropertyValueFactory<>("hr_balance"));
    }

    private void initializeDebtorsTable() {
        TableColumn<balance, String> who = new TableColumn<>("Kompaniya nomi");
        TableColumn<balance, Integer> sum_in = new TableColumn<>("Kirim So'mda");
        TableColumn<balance, Integer> sum_out = new TableColumn<>("Chiqim So'mda");
        TableColumn<balance, Integer> sum_balance = new TableColumn<>("Balans So'mda");
        TableColumn<balance, Integer> dollar_in = new TableColumn<>("$ da kirim");
        TableColumn<balance, Integer> dollar_out = new TableColumn<>("$ da chiqim");
        TableColumn<balance, Integer> dollar_balance = new TableColumn<>(" $ da balans");
        TableColumn<balance, Integer> hr_in = new TableColumn<>("Kirim hisib raqam");
        TableColumn<balance, Integer> hr_out = new TableColumn<>("Chiqim hisob raqam");
        TableColumn<balance, Integer> hr_balance = new TableColumn<>("Hisob raqam balans");


        tableDebtorsOper.getColumns().addAll(who, sum_in, sum_out, sum_balance, dollar_in, dollar_out, dollar_balance, hr_in, hr_out, hr_balance);

        who.setCellValueFactory(new PropertyValueFactory<>("who"));
        sum_in.setCellValueFactory(new PropertyValueFactory<>("sum_in"));
        sum_out.setCellValueFactory(new PropertyValueFactory<>("sum_out"));
        sum_balance.setCellValueFactory(new PropertyValueFactory<>("sum_balance"));
        dollar_in.setCellValueFactory(new PropertyValueFactory<>("dollar_in"));
        dollar_out.setCellValueFactory(new PropertyValueFactory<>("dollar_out"));
        dollar_balance.setCellValueFactory(new PropertyValueFactory<>("dollar_balance"));
        hr_in.setCellValueFactory(new PropertyValueFactory<>("hr_in"));
        hr_out.setCellValueFactory(new PropertyValueFactory<>("hr_out"));
        hr_balance.setCellValueFactory(new PropertyValueFactory<>("hr_balance"));
    }

    private void initializeHistoryTable() {
        TableColumn<History, String> id = new TableColumn<>("id");
        TableColumn<History, String> barcode = new TableColumn<>("Barcode");
        TableColumn<History, String> p_id = new TableColumn<>("Maxsulot idsi");
        TableColumn<History, String> name = new TableColumn<>("Nomi");
        TableColumn<History, String> type = new TableColumn<>("Turi");
        TableColumn<History, String> quantity = new TableColumn<>("Miqdori");
        TableColumn<History, String> seller_id = new TableColumn<>("Sotuvchi");
        TableColumn<History, String> cost = new TableColumn<>("Umumiy narxi");
        TableColumn<History, String> perCost = new TableColumn<>("Narxi");
        TableColumn<History, String> date_cr = new TableColumn<>("Sana");
        TableColumn<History, String> customer_id = new TableColumn<>("Xaridor");
        TableColumn<History, String> sellAction_id = new TableColumn<>("SellActionId");


        HistoryTable.getColumns().addAll(id, date_cr, seller_id, barcode, name, type, quantity, perCost, cost, customer_id, sellAction_id);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        barcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        p_id.setCellValueFactory(new PropertyValueFactory<>("p_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        seller_id.setCellValueFactory(new PropertyValueFactory<>("seller_id"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        perCost.setCellValueFactory(new PropertyValueFactory<>("perCost"));
        date_cr.setCellValueFactory(new PropertyValueFactory<>("date_cr"));
        customer_id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        sellAction_id.setCellValueFactory(new PropertyValueFactory<>("sellAction_id"));
    }

    private void initializeCheckTable() {
        TableColumn<History, String> id = new TableColumn<>("id");
        TableColumn<History, String> barcode = new TableColumn<>("Barcode");
        TableColumn<History, String> p_id = new TableColumn<>("Maxsulot idsi");
        TableColumn<History, String> name = new TableColumn<>("Nomi");
        TableColumn<History, String> type = new TableColumn<>("Turi");
        TableColumn<History, String> quantity = new TableColumn<>("Miqdori");
        TableColumn<History, String> seller_id = new TableColumn<>("Sotuvchi");
        TableColumn<History, String> cost = new TableColumn<>("Umumiy narxi");
        TableColumn<History, String> perCost = new TableColumn<>("Narxi");
        TableColumn<History, String> date_cr = new TableColumn<>("Sana");
        TableColumn<History, String> customer_id = new TableColumn<>("Xaridor");
        TableColumn<History, String> sellAction_id = new TableColumn<>("SellActionId");


        tarixCheckTable.getColumns().addAll(id, date_cr, barcode, name, type, quantity, perCost, cost);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        barcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        p_id.setCellValueFactory(new PropertyValueFactory<>("p_id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        seller_id.setCellValueFactory(new PropertyValueFactory<>("seller_id"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        perCost.setCellValueFactory(new PropertyValueFactory<>("perCost"));
        date_cr.setCellValueFactory(new PropertyValueFactory<>("date_cr"));
        customer_id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        sellAction_id.setCellValueFactory(new PropertyValueFactory<>("sellAction_id"));
    }

    private void initializeSellactionTable() {
        TableColumn<Debt, String> id = new TableColumn<>("id");
        TableColumn<Debt, String> date = new TableColumn<>("Sana");
        TableColumn<Debt, String> sum = new TableColumn<>("Sum");
        TableColumn<Debt, String> dollar = new TableColumn<>("Dollar");
        TableColumn<Debt, String> hr = new TableColumn<>("Hisob raqam");
        TableColumn<Debt, String> psum = new TableColumn<>("To'langan-sum");
        TableColumn<Debt, String> pdollar = new TableColumn<>("To'langan-dollar");
        TableColumn<Debt, String> phr = new TableColumn<>("To'langan-hr");
        TableColumn<Debt, String> sale = new TableColumn<>("Chegirma");
        TableColumn<Debt, String> companyName = new TableColumn<>("Xaridor");
        TableColumn<Debt, String> cr_by = new TableColumn<>("Sotuvchi");
        TableColumn<Debt, String> comment = new TableColumn<>("Izoh");


        QarzTable.getColumns().addAll(id, date, sum, dollar, hr, psum, pdollar, phr, sale, companyName, cr_by, comment);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_cr"));
        sum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        dollar.setCellValueFactory(new PropertyValueFactory<>("dollar"));
        hr.setCellValueFactory(new PropertyValueFactory<>("hr"));
        psum.setCellValueFactory(new PropertyValueFactory<>("psum"));
        pdollar.setCellValueFactory(new PropertyValueFactory<>("pdollar"));
        phr.setCellValueFactory(new PropertyValueFactory<>("phr"));
        sale.setCellValueFactory(new PropertyValueFactory<>("sale"));
        companyName.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        cr_by.setCellValueFactory(new PropertyValueFactory<>("cr_by"));
        comment.setCellValueFactory(new PropertyValueFactory<>("comment"));

    }

    /**
     * Initialise MainPart
     */
    private void initializeTable() {

        //initializing dollar amount
        OperDollarLabel.setText(String.valueOf(operDao.getCurrencyRate()));

        TableColumn<ProductTable, String> barcode = new TableColumn<>("Barcode");
        TableColumn<ProductTable, String> name = new TableColumn<>("Nomi");
        TableColumn<ProductTable, Integer> quantity = new TableColumn<>("Miqdori");
        TableColumn<ProductTable, Double> cost = new TableColumn<>("Narxi");
        TableColumn<ProductTable, Double> color = new TableColumn<>("Rangi");


        tableSampleManual.getColumns().addAll(barcode, name, quantity, cost, color);

        barcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        color.setCellValueFactory(new PropertyValueFactory<>("description"));

        addedItemsList.getChildren().addListener((ListChangeListener<Node>) c -> {

            totalCost.setText(Utils.ThousandDivider(calculateCurrentTotalAll().sum) + " sum");
            labelDollar.setText("$ " + Utils.ThousandDivider(calculateCurrentTotalAll().dollar));
            labelHR.setText(Utils.ThousandDivider(calculateCurrentTotalAll().hr) + " sum");

        });
        tableSampleManual.addEventHandler(MouseEvent.MOUSE_CLICKED, (EventHandler<Event>) event -> {
            try {
                if (tableSampleManual.getSelectionModel().getSelectedItem() != null) {
                    ProductTable productTable = tableSampleManual.getSelectionModel().getSelectedItem();
                    addProductTableToList(productTable);
                    tableSampleManual.getSelectionModel().clearSelection();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

        /**
         * Hot keys
         * */
        scanCodeField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    onCodeScanClicked();
                }
            }
        });
    }

    /**
     * Sell action with Enter
     */
    private float calculateCurrentTotalSum() {
        float sum = 0;
        for (BasketItem aBasket : basket) {
            sum += (aBasket.getCost() * aBasket.getAmount());
        }
        return sum;
    }

    private AllCurrencyValues calculateCurrentTotalAll() {
        float sum = 0;
        float dollar = 0;
        float hr = 0;
        for (BasketItem aBasket : basket) {
            if (aBasket.getCurrency().equalsIgnoreCase("sum"))
                sum += (aBasket.getCost() * aBasket.getAmount());
            if (aBasket.getCurrency().equals("$"))
                dollar += (aBasket.getCost() * aBasket.getAmount());
            if (aBasket.getCurrency().equalsIgnoreCase("hr"))
                hr += (aBasket.getCost() * aBasket.getAmount());
        }
        return new AllCurrencyValues(sum, dollar, hr);
    }

    private boolean updateListItemAmount(BasketItem basketItem) {
        List<Node> items = addedItemsList.getChildren();
        for (Node item : items) {
            BasketItem i = (BasketItem) item.getUserData();
            if (i.isEqual(basketItem.getBarcode())) {
                TextField field = (TextField) item.lookup("#amountField");
                field.setText((Integer.valueOf(field.getText()) + 1) + "");
                changeBasketItemAmount(i.getBarcode(), i.getAmount() + 1);
            }
        }
        return false;
    }

    private void validityCheckinteger() {

              utils.thousandDivider(operHR);
              utils.thousandDivider(operDollar);
              utils.thousandDivider(operSum);
              utils.thousandDivider(ChangeSum);
              utils.selectAll(operHR);
              utils.selectAll(operDollar);
              utils.selectAll(operSum);
              utils.selectAll(ChangeSum);

    }

    private void setSaleCheck() {
        try {
            QarzTable.setOnMouseClicked(event -> {
                if (QarzTable.getSelectionModel().getSelectedItem() != null) {
                    Debt debt = (sample.model.Debt) QarzTable.getSelectionModel().getSelectedItem();
                    try {
                        productDao.checkHistory(tarixCheckTable, debt.getId());
                        revertLabel_id.setText(debt.getId());
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                }
            });
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private void checkHistory() {
        try {
            productDao.checkHistory(tarixCheckTable, "*");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        operType.getItems().addAll("Kirim", "Chiqim");
        ChangeType.getItems().addAll("sum-dollar", "hr-dollar", "dollar-sum", "dollar-hr", "hr-sum", "sum-hr");
        ChangeType.getSelectionModel().selectFirst();
        ComboBoxBalance.getItems().addAll("*");
        ComboBoxBalance.getSelectionModel().selectFirst();
        sellactionSelectCustomer.getItems().addAll("");
        exchangeSelectName.getItems().addAll("");
        tarixSelectName.getItems().addAll("");
        operHistoryWho.getItems().addAll("");
        operCustomerFilter.getItems().addAll("");

        try {
            setSaleCheck();
            productDao1.addWhoCombobox(operWho);
            productDao1.addWhoCombobox(operCustomerFilter);
            productDao1.addWhoCombobox(operHistoryWho);
            productDao1.addWhoCombobox(ComboBoxBalance);
            getSellactionCompanyName();
            getSellactionExchangeName();
            getTarixSelectName();
            setCurrentIncome();
            setCurrentOutcome();
            table();


            tarixCheckTable.setOnMouseClicked((MouseEvent event) -> {
                if (event.getClickCount() == 2) {
                    sample.model.History history = (sample.model.History) tarixCheckTable.getSelectionModel().getSelectedItem();
                    try {
                        dialogPrint(String.valueOf(history.getSellAction_id()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });

            operTable.setOnMouseClicked((MouseEvent event) -> {
                if (event.getClickCount() == 2) {
                    OperTable history = (OperTable) operTable.getSelectionModel().getSelectedItem();
                    try {
                        dialogRevert(String.valueOf(history.getId()), history.getType(), history.getSum(), history.getDollar(), history.getHr(), history.getWho());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });


            //Autocomplate combobox for person table
            ComboBoxBalance.setTooltip(new Tooltip());
            operHistoryWho.setTooltip(new Tooltip());
            operWho.setTooltip(new Tooltip());
            operCustomerFilter.setTooltip(new Tooltip());
            sellactionSelectCustomer.setTooltip(new Tooltip());
            exchangeSelectName.setTooltip(new Tooltip());
            tarixSelectName.setTooltip(new Tooltip());
            new ComboBoxAutoComplete<String>(ComboBoxBalance);
            new ComboBoxAutoComplete<String>(operHistoryWho);
            new ComboBoxAutoComplete<String>(operWho);
            new ComboBoxAutoComplete<String>(operCustomerFilter);
            new ComboBoxAutoComplete<String>(sellactionSelectCustomer);
            new ComboBoxAutoComplete<String>(exchangeSelectName);
            new ComboBoxAutoComplete<String>(tarixSelectName);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        /**
         * Initialising producttype listview
         * */
        try {
            productDao.typeList(typeList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /**
         * Sellecting type from list
         * */
        typeList.addEventHandler(MouseEvent.MOUSE_CLICKED, (EventHandler<Event>) event -> {
            ResultSet rs;
            productTables = FXCollections.observableArrayList();
            String name = typeList.getSelectionModel().getSelectedItems().toString().replace("[", "").replace("]", "");
            System.out.println(name);
            try {
                rs = productDao.searchProductType(name);
                ProductDao.productTableGenerator(rs, productTables);
                tableSampleManual.setItems(productTables);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        validityCheckinteger();
        initializeExchangeTable();
        initializeCheckTable();
        initializeHistoryTable();
        initializeTable();
        initializeOperTable();
        intializeBalanceTable();
        initializeDebtorsTable();
        initializeSellactionTable();
        operTable();
        balanceTable();
        debtorsTable();
        QarzTable();
        checkHistory();
        historyTable();
        sample.model.User u = Login.currentUser;
        if (u == null) {
            u = new sample.model.User();
            u.setFirstName("Someone");
            u.setLastname("Someone");
            u.setId(1);
            Login.currentUser = u;
        }
        setUserData1(u);
        Clock();
        Thread.currentThread();
        scanCodeField.requestFocus();

        btnClose.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Yopish");
            alert.setHeaderText(null);
            alert.setContentText("Dasturni yopasizmi?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent())
                if (result.get() == ButtonType.OK) {
                    Platform.exit();
                    System.exit(0);
                }
        });
        setOzgaartirishMaxsulot();
        setOzgaartirishMaxsulotQarz();


        /**
         *   Timer()
         */
        Timeline ficeSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                operTable();
                balanceTable();
                debtorsTable();
                QarzTable();
                historyTable();
                getSellactionCompanyName();
                getSellactionExchangeName();
                getTarixSelectName();
                setCurrentIncome();
                setCurrentOutcome();
            }
        }));
        ficeSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        ficeSecondsWonder.play();
        /**
         *  End of timer()
         *
         */
    }

    private void setOzgaartirishMaxsulot() {

        try {
            operBalanceTable.setOnMouseClicked(event -> {
                if (operBalanceTable.getSelectionModel().getSelectedItem() != null) {
                    balance balance = (sample.components.sell.productTableView.balance) operBalanceTable.getSelectionModel().getSelectedItem();
                    try {
                        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
                        symbols.setGroupingSeparator(' ');
                        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
                        Double s = 0.0;
                        Double d = 0.0;
                        Double h = 0.0;
                        text_balance_sum.setText(balance.getSum_balance() + " sum");
                        text_balance_dollar.setText("$ " + balance.getDollar_balance());
                        text_balance_hr.setText(balance.getHr_balance() + " hr sum");
                        text_balance_name.setText(balance.getWho());
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                }
            });
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private void dialogRevert(String id, String operType1, String sum, String dollar,String  hr,String who) {
        try {
            TextInputDialog dialog = new TextInputDialog(id);
            dialog.setTitle("Operatsiya");
            dialog.setHeaderText("Operatsiya nomeri: " + id);
            dialog.setContentText("Operatsiyani ortga qaytarasizmi ?");
            Optional<String> result = dialog.showAndWait();
            // The Java 8 way to get the response value (with lambda expression).
            result.ifPresent(name ->
                    {
                        operDao.revertOper(id, operType1, sum.trim().replaceAll("\\s+", ""), dollar.trim().replaceAll("\\s+", ""), hr.trim().replaceAll("\\s+", ""), who);
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dialogPrint(String id) {
        try {
            TextInputDialog dialog = new TextInputDialog(id);
            dialog.setTitle("Check chiqarish");
            dialog.setHeaderText("Check nomeri: " + id);
            dialog.setContentText("Check chiqarasizmi?");
            Optional<String> result = dialog.showAndWait();
            // The Java 8 way to get the response value (with lambda expression).
            result.ifPresent(name ->
                    {
                        try {
                            printActionWithId(id);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setOzgaartirishMaxsulotQarz() {

        try {
            tableDebtorsOper.setOnMouseClicked(event -> {
                if (tableDebtorsOper.getSelectionModel().getSelectedItem() != null) {
                    balance balance = (sample.components.sell.productTableView.balance) tableDebtorsOper.getSelectionModel().getSelectedItem();
                    try {
                        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
                        symbols.setGroupingSeparator(' ');
                        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
                        Double s = 0.0;
                        Double d = 0.0;
                        Double h = 0.0;
                        text_balance_sum.setText(balance.getSum_balance() + " sum");
                        text_balance_dollar.setText("$ " + balance.getDollar_balance());
                        text_balance_hr.setText(balance.getHr_balance() + " hr sum");
                        text_balance_name.setText(balance.getWho());
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                }
            });
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }


    private void setUserData1(User u) {
        idUserName.setText(u.getFirstName() + " " + u.getLastname());
        idStartDate.setText(Utils.getCurrnetDateInStandardFormat());
    }

    private int counter = 0;

    private void addProductTableToList(ProductTable productTable) {
        FXMLLoader loader = createCustomItemLoader("AddedItemListItem", "CustomBasketItem/");
        assert loader != null;
        try {
            Pane p = loader.load();
            BasketItem basketItem = BasketItem.getInstance();
            basketItem.setAll(productTable.getBarcode(), productTable.getName(), Float.parseFloat(productTable.getCost()), 1, true, "$");
            p.setUserData(basketItem);
            ShopItemListItem item = loader.getController();
            item.setDetails(productTable, basketItem.isAccepted());
            for (BasketItem b : basket) {
                if (b.isEqual(basketItem.getBarcode())) {
                    updateListItemAmount(b);
                    return;
                }
            }
            counter++;
            item.id = counter;
            basket.add(basketItem);
            addedItemsList.getChildren().add(p);
            item.setPane(p);
            TextField field = (TextField) p.lookup("#amountField");
            field.requestFocus();
            field.selectAll();
            //
            ComboBox<String> c = (ComboBox<String>) p.lookup("#comboCurrency");
            c.setOnAction(Event -> {
                DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
                symbols.setGroupingSeparator(' ');
                DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
                String sum = String.valueOf(formatter.format(calculateCurrentTotalAll().sum));
                String dollar = String.valueOf(formatter.format(calculateCurrentTotalAll().dollar));
                String hr = String.valueOf(formatter.format(calculateCurrentTotalAll().hr));
                TextField l2 = (TextField) p.lookup("#itemPrice");
                utils.thousandDivider(l2);
                l2.requestFocus();
                totalCost.setText(sum + " sum");
                labelDollar.setText("$ " + dollar);
                labelHR.setText(hr + " sum");
            });

            field.textProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    TextField l = (TextField) p.lookup("#itemPrice");

                    float price = Float.parseFloat(l.getText().trim().replaceAll("\\s+", ""));

                    calculateCurrentTotalAll();


                    String currency = c.getSelectionModel().getSelectedItem();

                    BasketItem i = (BasketItem) p.getUserData();
                    int quantity = productDao.getQuantity(i.getBarcode());
                    changeBasketItemPrice(i.getBarcode(), price);

                    if (!Utils.isNumberValid(newValue, Utils.Number.INT) || newValue.equals("")) {

                        field.setText("0");
                        return;
                    }

                    if (!Utils.isNumberValid(newValue, Utils.Number.INT) || oldValue.equals("")) {
                        field.setText("0");
                        return;
                    }

                    if (newValue.length() > 1 & newValue.charAt(0) == '0') {
                        field.setText(newValue.replaceFirst("0", ""));
                        return;
                    }

//                    int number = Utils.isNumberInRange(Integer.valueOf(newValue), 0, quantity);
//                    if (number != Integer.valueOf(newValue)) {
//                        field.setText(number + "");
//                        field.selectAll();
//                    } else {
                    int finalNewValue = Integer.parseInt(newValue);
                    Platform.runLater(() -> {
                        try {
                            changeBasketItemAmount(i.getBarcode(), finalNewValue);
                            changeBasketItemPrice(i.getBarcode(), price);
                            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
                            changeSellCurrency(i.getBarcode(), currency);
                            symbols.setGroupingSeparator(' ');
                            DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
                            String sum = String.valueOf(formatter.format(calculateCurrentTotalAll().sum));
                            String dollar = String.valueOf(formatter.format(calculateCurrentTotalAll().dollar));
                            String hr = String.valueOf(formatter.format(calculateCurrentTotalAll().hr));
                            totalCost.setText(sum + " sum");
                            labelDollar.setText("$ " + dollar);
                            labelHR.setText(hr + " sum");
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    });
//                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                /**
                 * Basket quantity field, Enter is pressed.
                 * */
                field.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (event.getCode() == KeyCode.ENTER) {
                            scanCodeField.requestFocus();
                        }
                    }
                });
            });

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void changeBasketItemAmount(String barcode, int amount) {
        for (BasketItem item : basket) {
            if (item.getBarcode().equals(barcode)) {
                item.setAmount(amount);
                return;
            }
        }
    }

    private void changeBasketItemPrice(String barcode, float price) {
        for (BasketItem item : basket) {
            if (item.getBarcode().equals(barcode)) {
                item.setCost(price);
                return;
            }
        }
    }

    private void changeSellCurrency(String barcode, String currency) {
        for (BasketItem item : basket) {
            if (item.getBarcode().equals(barcode)) {
                item.setCurrency(currency);
                return;
            }
        }
    }

    public MainPageController() {
        try {
            myConn = database.getConnection();
            productDao = new ProductDao();
        } catch (Exception exc) {
            Utils.ErrorAlert("Error", "Xatolik" + exc, "Xatolik bor shu yerda");
        }
    }

    /**
     * End of custom credit table.
     */

    public void onCodeScanClicked() {
        try {
            ProductDao dao = new ProductDao();
            ProductTable pt = dao.convertProductToProductTable(dao.getProduct(scanCodeField.getText().trim()));
            addProductTableToList(pt);
            scanCodeField.setText("");
        } catch (Exception e) {
            System.out.println("no such item: " + scanCodeField.getText());
        }
    }

    @FXML
    public void btnActionIzlash() {
        String SearchItemName = textSampleIzlash.getText();
        buildData(SearchItemName);
    }

    private void buildData(String name) {
        productTables = FXCollections.observableArrayList();
        ResultSet myRs;
        try {
            myRs = ProductDao.getResultSet(name, textSampleIzlash, myConn);
            if (myRs != null) {
                ProductDao.productTableGenerator(myRs, productTables);
            }
            tableSampleManual.setItems(productTables);
        } catch (Exception exc) {
            Utils.ErrorAlert("Xatolik", "Xatolik yuz berdi", "" + exc);
        }
    }

    /**
     * on cancelShop button clicked
     */
    public void cancelShop() {
        reset();
    }

    private FXMLLoader createCustomItemLoader(String title, @Nullable String folder) {
        try {
            if (folder == null) folder = "";
            String path = folder + title + ".fxml";
            return new FXMLLoader(getClass().getResource("/sample/components/sell/views/CustomItems/" + path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Custom sellaction  dialogbox  . It works when you press sell button.
     */
    private void onBtnDiscountClicked() {
        try {
            FXMLLoader loader = createCustomItemLoader("DiscountWindow", "CustomDiscountWindow/");
            assert loader != null;
            BorderPane vBox = loader.load();
            PopOver popOver = new PopOver(vBox);
            Button btnCancel = (Button) vBox.lookup("#btnCancel");
            Button btnOK = (Button) vBox.lookup("#discountOK");
            //Gird components
            TextField textFirstName = (TextField) vBox.lookup("#textFirstName");
            Button btnAddCustomer = (Button) vBox.lookup("#btnAddCustomer");
            TextField textSaleSumm = (TextField) vBox.lookup("#textSaleSumm");
            TextField textSalePercent = (TextField) vBox.lookup("#textSalePercent");
            ComboBox<String> SellWho = (ComboBox<String>) vBox.lookup("#SellWho");
            TextField SellOperSumma = (TextField) vBox.lookup("#SellOperSumma");
            Button LabelGivenSum = (Button) vBox.lookup("#LabelGivenSum");
            TextField textHr = (TextField) vBox.lookup("#textHR");
            TextField textDollar = (TextField) vBox.lookup("#textDollar");
            Label sumLabel = (Label) vBox.lookup("#sumLabel");
            Label hrLabel = (Label) vBox.lookup("#hrLabel");
            Label labelDollar = (Label) vBox.lookup("#labelDollar");
            Label LabelTotalSumm = (Label) vBox.lookup("#LabelTotalSumm");
            TextField textComment = (TextField) vBox.lookup("#textComment");
            TextField TextSearchCustomer = (TextField) vBox.lookup("#TextSearchCustomer");
            TableView TableCustomer = (TableView) vBox.lookup("#TableCustomer");
            Label LabelCustomerName = (Label) vBox.lookup("#LabelCustomerName");
            Label LabelCustomerId = (Label) vBox.lookup("#LabelCustomerId");
            Label CSum = (Label) vBox.lookup("#CSum");
            Label CDollar = (Label) vBox.lookup("#CDollar");
            Label CHR = (Label) vBox.lookup("#CHR");

            /**
             * Customer table
             * */
            TableColumn id = new TableColumn("Id");
            TableColumn name = new TableColumn("Ism");
            TableCustomer.getColumns().addAll(id, name);
            id.setCellValueFactory(new PropertyValueFactory<CustomerName, String>("id"));
            name.setCellValueFactory(new PropertyValueFactory<CustomerName, String>("name"));

            productDao.customerNameDao(TableCustomer, "");

            TextSearchCustomer.setOnKeyReleased(Event -> {
                productDao.customerNameDao(TableCustomer, TextSearchCustomer.getText());
            });

            TextSearchCustomer.setOnAction(Event -> {
                productDao.customerNameDao(TableCustomer, TextSearchCustomer.getText());
            });

            try {
                TableCustomer.setOnMouseClicked(event -> {
                    if (TableCustomer.getSelectionModel().getSelectedItem() != null) {
                        CustomerName product = (CustomerName) TableCustomer.getSelectionModel().getSelectedItem();
                        try {
                            LabelCustomerName.setText(product.getName());
                            LabelCustomerId.setText(product.getId());
                            operDao.perPersonBalance(CHR, CSum, CDollar, product.getName());
                        } catch (Exception exc) {
                            exc.printStackTrace();
                        }
                    }
                });
            } catch (Exception exc) {
                exc.printStackTrace();
            }

            /**
             * End of customer table
             * */


            /**
             * Opreations
             * */

            sumLabel.setText(Utils.ThousandDivider(calculateCurrentTotalAll().sum));
            labelDollar.setText(Utils.ThousandDivider(calculateCurrentTotalAll().dollar));
            hrLabel.setText(Utils.ThousandDivider(calculateCurrentTotalAll().hr));

            textSaleSumm.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!Utils.isNumberValid(newValue, Utils.Number.DOUBLE) || newValue.equals("")) {
                    textSaleSumm.setText("0");
                    return;
                }

                longTaskRun = new Task<Void>() {

                    @Override
                    protected Void call() {
                        Platform.runLater(() -> {
                            if (Double.valueOf(newValue) >= calculateCurrentTotalAll().sum) {
                                textSaleSumm.setText(String.valueOf(calculateCurrentTotalAll().sum));
                            }

                            Double salesum = Double.valueOf(newValue);
                            Double saleQuantitySumm = (Double.valueOf(textSaleSumm.getText()));
                            double number = (Utils.isNumberInRange(Double.valueOf(newValue), 0.00, saleQuantitySumm) * 100);
                            textSalePercent.setText((String.valueOf(number / calculateCurrentTotalAll().sum)).substring(0, 3) + " %");
                            sumLabel.setText(String.valueOf(calculateCurrentTotalAll().sum - salesum));
                        });
                        return null;
                    }
                };

                new Thread(longTaskRun).start();
            });


            utils.thousandDivider(SellOperSumma);
            utils.thousandDivider(textDollar);
            utils.thousandDivider(textHr);
            utils.selectAll(SellOperSumma);
            utils.selectAll(textDollar);
            utils.selectAll(textHr);

            /**
             * btnAddCustomer button to add new customers
             * */
            btnAddCustomer.setOnAction(event -> {
                try {
                    if (textFirstName.getText().length() > 0) {
                        String firstname = textFirstName.getText().trim().replaceAll("\\s+", "");
                        btnAddCustomerAction(firstname);
                        productDao1.addWhoCombobox(operWho);
                        productDao1.addWhoCombobox(operCustomerFilter);
                        setCurrentIncome();
                        textFirstName.setText("");
                    } else {
                        textFirstName.setPromptText("Ismni yozing!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            /**
             * Sotish oynasiga haridorning qancha pul berayotganini  ko'rsatuvchi labellarga u olgan maxsulotlarning
             * valuta, sum, va hisob raqam summalarini belgilash.
             * */
            LabelGivenSum.setOnMouseClicked(Event -> {
                String sum = sumLabel.getText().trim().replaceAll("\\s+", "");
                String hr = hrLabel.getText().trim().replaceAll("\\s+", "");
                String dollar = labelDollar.getText().trim().replaceAll("\\s+", "");
                textDollar.setText(dollar);
                textHr.setText(hr);
                SellOperSumma.setText(sum);
            });

            /**
             * Sell action ok button pressed
             * */
            btnOK.setOnMouseClicked(event -> {

                String customerName = LabelCustomerId.getText();
                if (!customerName.equals("*")) {
                    HistoryDao historyDao = new HistoryDao(myConn);
                    try {
                        credit = new CreditModel(0, Integer.valueOf(LabelCustomerId.getText()));

                        String commnet = "Empty";
                        if (textComment.getText() == null && textComment.getText().length() > 0) {
                            commnet = "Empty";
                        } else {
                            commnet = textComment.getText();
                        }
                        int Salesum = 0;
                        if (textSaleSumm.getText() != null) {
                            Salesum = Integer.valueOf(textSaleSumm.getText());
                        }
                        /**
                         *
                         * */
                        String sum = SellOperSumma.getText().trim().replaceAll("\\s+", "");
                        String hr1 = textHr.getText().trim().replaceAll("\\s+", "");
                        String dollar1 = textDollar.getText().trim().replaceAll("\\s+", "");
                        // Qarzga berilayotgan maxsulotlar summasi
                        String sum2 = sumLabel.getText().trim().replaceAll("\\s+", "");
                        String hr2 = hrLabel.getText().trim().replaceAll("\\s+", "");
                        String dollar2 = labelDollar.getText().trim().replaceAll("\\s+", "");

                        /**
                         *
                         * */


                        if(!sum.isEmpty() && !hr1.isEmpty() && !dollar1.isEmpty()) {

                            historyDao.insertBasketToHistory(basket, Login.currentUser, credit, calculateCurrentTotalAll(), String.valueOf(Salesum), commnet, sum, dollar1, hr1);
                            //
                            //
                            historyDao.addSalePaidSum2( sum, dollar1, hr1);
                            btnActionIzlash();
                            printAction();
                            scanCodeField.requestFocus();
                            popOver.hide();
                            reset();
                            setCurrentIncome();
                            setCurrentOutcome();
                            callFunctions();
                        } else {
                            SellOperSumma.setStyle("-fx-background-color:red; ");
                            textHr.setStyle("-fx-background-color:red; ");
                            textDollar.setStyle("-fx-background-color:red; ");
                        }

                    } catch (Exception e) {
                        Utils.ErrorAlert("Xatolik", "Savdo amalga oshmadi", e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    LabelCustomerName.setStyle("-fx-background-color:red; ");
                    LabelCustomerId.setStyle("-fx-background-color:red; ");
                }
            });
            popOver.setDetachedTitle("Savdo oynasi");
            popOver.detach();
            btnCancel.setOnMouseClicked(event -> popOver.hide());

            float sum1 = calculateCurrentTotalSum();
            if (sum1 > 0) {
                BtnSell.setOnMouseClicked(event -> {
                    popOver.show(BtnSell);
                    popOver.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
                });
            } else {
                Utils.InfoAlert("Eslatma", "Sotish uchun hech narsa tanlanmagan!", "Eslatma");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * on action sell
     */
    public void actionSell() {
        onBtnDiscountClicked();
    }

    /**
     * btnAddCustomer action
     */
    private void btnAddCustomerAction(String firstname) {
        try {
            historyDao.addCustomer(firstname);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reset() {
        credit = null;
        basket = new ArrayList<>();
        addedItemsList.getChildren().removeAll(addedItemsList.getChildren());
        totalCost.setText("0.0 sum");

        counter = 0;
    }

    private void Clock() {
        clock = new Thread(() -> {
            while (!exit) {
                Calendar cal = Calendar.getInstance();
                int minute = cal.get(Calendar.MINUTE);
                int second = cal.get(Calendar.SECOND);
                int hour = cal.get(Calendar.HOUR);
                try {
                    Platform.runLater(() -> {
                        String second_str = "" + second;
                        String min_str = "" + minute;
                        String hour_str = "" + hour;

                        if (minute < 10) {
                            min_str = "0" + minute;
                        }
                        if (hour == 0) {
                            hour_str = "12";
                        }
                        if (second < 10) {
                            second_str = "0" + second;
                        }
                        ClockText.setText(hour_str + ":"
                                + min_str + ":"
                                + second_str);

                    });
                    Thread.sleep(1000);
                } catch (Exception exe) {
                    JOptionPane.showMessageDialog(null, exe);
                }
            }
        });
        clock.start();
    }

    public void BtnUpdateAction() {
        btnActionIzlash();
    }

    private void printAction() throws SQLException {
        String apple = Utils.convertDateToStandardFormat(Utils.getCurrentDate());
        ArrayList<ReceiptCheck> receiptChecks = null;
        receiptChecks = utilsDao.PerProduct();
        StringBuilder storage = new StringBuilder();
        System.out.println(utilsDao.getCheckQuantity() + " ishladi");
        for (ReceiptCheck item : receiptChecks) {
            storage.append(item.getName()).append("  No: ").append(item.getQuantity()).append("  Summa: ").append(item.getPrice()).append("\n").append("----------------------------------------------\n");
        }
        CheckSums checkSums = utilsDao.getCheckSums();
        //print some stuff
        printerService.printString(printer.printerName(), "\n" +
                "******** Software Business Development *********\n\n\n" +
                 storage + "\n" +
                "Mashina No:__________________________________\n\n\n\n\n" +
                "***************** To'lashi kerak ************\n\n" +
                "Umumiy So'm                  " + checkSums.getSum() + " sum\n\n" +
                "Umumiy Hisob raqam summasi   " + checkSums.getHr() + " sum\n\n" +
                "Umumiy dollar               $" + checkSums.getDollar() + "\n\n" +
                "**************** To'langan ******************\n\n" +
                "So'm                " + checkSums.getPsum() + "\n\n" +
                "Dollar              " + checkSums.getPdollar() + "\n\n" +
                "Hisob raqam         " + checkSums.getPhr() + "\n\n" +
                "Umumiy miqdori      " + utilsDao.getCheckQuantity() + "\n\n" +
                "Sotuvchi            " + utilsDao.getSellerName() + "\n\n\n" +
                "************** " + apple + " ****************\n\n" +
                "********** Xaridingiz uchun raxmat! **********\n\n\n\n\n\n\n\n\n");
        // cut that paper!
        byte[] cutP = new byte[]{0x1d, 'V', 1};
        printerService.printBytes(printer.printerName(), cutP);
    }

    private void printActionWithId(String id) throws SQLException {
        String apple = Utils.convertDateToStandardFormat(Utils.getCurrentDate());
        ArrayList<ReceiptCheck> receiptChecks = null;
        receiptChecks = utilsDao.PerProduct();
        StringBuilder storage = new StringBuilder();
        for (ReceiptCheck item : receiptChecks) {
            storage.append(item.getName()).append("  No: ").append(item.getQuantity()).append("  Summa: ").append(item.getPrice()).append("\n").append("----------------------------------------------\n");
        }
        CheckSums checkSums = utilsDao.getCheckSumsWithId(id);
        //print some stuff
        printerService.printString(printer.printerName(), "\n" +
                "******** Software Business Development *********\n\n\n" +
                 storage + "\n" +
                "Mashina No:__________________________________\n\n\n\n\n" +
                "***************** To'lashi kerak ************\n\n" +
                "Umumiy So'm                  " + checkSums.getSum() + " sum\n\n" +
                "Umumiy Hisob raqam summasi   " + checkSums.getHr() + " sum\n\n" +
                "Umumiy dollar               $" + checkSums.getDollar() + "\n\n" +
                "**************** To'langan ******************\n\n" +
                "So'm                " + checkSums.getPsum() + "\n\n" +
                "Dollar              " + checkSums.getPdollar() + "\n\n" +
                "Hisob raqam         " + checkSums.getPhr() + "\n\n" +
                "Sotuvchi          $" + utilsDao.getSellerName() + "\n\n\n" +
                "************** " + apple + " ****************\n\n" +
                "********** Xaridingiz uchun raxmat! **********\n\n\n\n\n\n\n\n\n");
        // cut that paper!
        byte[] cutP = new byte[]{0x1d, 'V', 1};
        printerService.printBytes(printer.printerName(), cutP);
    }


    private void operTable() {
        try {
            if (operCustomerFilter.getSelectionModel().getSelectedItem() != null && operDanFilter.getValue() != null && operGachaFilter.getValue() != null) {
                String who = operCustomerFilter.getSelectionModel().getSelectedItem();
                LocalDate date1 = operDanFilter.getValue();
                LocalDate date2 = operGachaFilter.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                operDao.operTableFilter(operTable, who, sdate1, sdate2, labelOperSum, labelOperDollar, labelOperHr);
            } else if (operCustomerFilter.getSelectionModel().getSelectedItem() == null && operDanFilter.getValue() != null && operGachaFilter.getValue() != null) {
                LocalDate date1 = operDanFilter.getValue();
                LocalDate date2 = operGachaFilter.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                operDao.operTableFilter(operTable, "", sdate1, sdate2, labelOperSum, labelOperDollar, labelOperHr);
            } else if (operCustomerFilter.getSelectionModel().getSelectedItem() == null && operDanFilter.getValue() == null && operGachaFilter.getValue() == null) {
                operDao.operTableFilter(operTable, "", "", "", labelOperSum, labelOperDollar, labelOperHr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void balanceTable() {
        try {
            String name = ComboBoxBalance.getSelectionModel().getSelectedItem();
            operDao.balanceTable(operBalanceTable, name);
            operDao.selectOperwhoId(LabelBalanceIdWho, name);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void debtorsTable() {
        try {
            operDao.tableDebtorsTable(tableDebtorsOper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void btnOperAction() {

        try {
            if (operType.getSelectionModel().getSelectedItem() != null && !text_per.getText().isEmpty() && operWho.getSelectionModel().getSelectedItem() != null
                    && !operSum.getText().isEmpty() && !operDollar.getText().isEmpty() && !operHR.getText().isEmpty()
                    && !operDescription.getText().isEmpty()) {
                double percentage = 0.0;
                String type = operType.getSelectionModel().getSelectedItem();
                percentage = Double.parseDouble(text_per.getText().trim().replaceAll("\\s", ""));
                String who = OperWhoId.getText();
                String sum = operSum.getText().trim().replaceAll("\\s+", "");
                String dollar = operDollar.getText().trim().replaceAll("\\s+", "");
                String hr = operHR.getText().trim().replaceAll("\\s+", "");
                String description = operDescription.getText();
                operDao.addOperTable(type, who, sum, dollar, hr, description, String.valueOf(percentage), String.valueOf(Double.valueOf(sum) - ((Double.valueOf(sum) * (percentage / 100)))));
                setCurrentIncome();
                setCurrentOutcome();
                operTable();
                balanceTable();
            } else {
                JOptionPane.showMessageDialog(null, "Hamma ma'lumotlarni kiriting!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            operSum.setText("0");
            operDescription.setText("0");
            operDollar.setText("0");
            operHR.setText("0");
            callFunctions();
        }
    }

    @FXML
    private void btnOperFilter() {
        try {
            operTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void operFilterAction() {
        try {
            productDao1.addWhoCombobox(operCustomerFilter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnDollarAction() {
        TextInputDialog dialog = new TextInputDialog("8500");
        dialog.setTitle("Dollar");
        dialog.setHeaderText("Valyuta");
        dialog.setContentText("Iltimos valyuta kursini kiriting");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> operDao.addCurrencyRate(name));
        OperDollarLabel.setText("$ " + operDao.getCurrencyRate());
    }

    private void setCurrentIncome() {
        operDao.getCurrentIncomeSum(CurrentIncomeSum, CurrentIncomeDollar, CurrentIncomeHR);
    }

    private void setCurrentOutcome() {
        operDao.getCurrentOutcome(ExpenceSum, ExpenceDollar, ExpenceHR);
    }

    // Print function
    private void printCheck(String sum, String dollar, String hr, String outSum, String outDollar, String outHr) throws SQLException {

        printerService.printString(printer.printerName(), "\n" +
                "******** Software Business Development *********\n\n\n" +
                "Sum             " + sum + " sum\n\n" +
                "Dollar         $" + dollar + " \n\n" +
                "Hisob raqam     " + hr + " sum\n\n" +
                "*******************************************\n" +
                "---------------- Xarajatlar ---------------\n" +
                "Sum             " + outSum + " sum\n\n" +
                "Dollar         $" + outDollar + " \n\n" +
                "Hisob raqam     " + outHr + " sum\n\n" +
                "************** " + apple + " **************\n" +
                "********** Kun yopish uchun check! **********\n\n\n\n\n\n\n\n");
        System.out.println(sum+"\n");
        System.out.println(dollar+"\n");
        System.out.println(hr+"\n");
        System.out.println("\n\n\n\n\n\n");
        System.out.println(outSum+"\n");
        System.out.println(outDollar+"\n");
        System.out.println(outHr+"\n");

        // cut that paper!
        byte[] cutP = new byte[]{0x1d, 'V', 1};
        printerService.printBytes(printer.printerName(), cutP);
    }

    // Colose the working day with balance
    @FXML
    private void btnEndDayAction() throws SQLException {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Kunni yopish");
        alert.setHeaderText(null);
        alert.setContentText("Bugunlik kundagi hisobni yopasizmi?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent())
            if (result.get() == ButtonType.OK) {
                String incomeSum = CurrentIncomeSum.getText().trim().replaceAll("\\s+", "");
                String incomeDollar = CurrentIncomeDollar.getText().trim().replaceAll("\\s+", "");
                String incomeHR = CurrentIncomeHR.getText().trim().replaceAll("\\s+", "");

                String outcomeSum = ExpenceSum.getText().trim().replaceAll("\\s+", "");
                String outcomeDollar = ExpenceDollar.getText().trim().replaceAll("\\s+", "");
                String outcomeHR = ExpenceHR.getText().trim().replaceAll("\\s+", "");

                try {

                    operDao.addToTotalBalance(incomeSum, incomeDollar, incomeHR, outcomeSum, outcomeDollar, outcomeHR);
                    //
                    printCheck(incomeSum, incomeDollar, incomeHR, outcomeSum, outcomeDollar, outcomeHR);
                    //Print the check for end  of the day
                    setCurrentIncome();
                    setCurrentOutcome();

                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
    }

    @FXML
    private void btnPersonAction() {
        try {
            String who = operWho.getSelectionModel().getSelectedItem();
            operDao.perPersonBalance(PersonHRBalance, PersonSumBalance, PersonDollarBalance, who);
            operDao.selectOperwhoId(OperWhoId, who);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnOperFilterAction() {
        operTable();
    }

    @FXML
    private void BtnExcellActionHistory() {
        try {
            if (HistoryDan.getValue() != null && HistoryGacha.getValue() != null && operHistoryWho.getSelectionModel().getSelectedItem() != null && tarixSelectName.getSelectionModel().getSelectedItem() != null) {
                String customer = operHistoryWho.getSelectionModel().getSelectedItem();
                String name = tarixSelectName.getSelectionModel().getSelectedItem();
                LocalDate date1 = HistoryDan.getValue();
                LocalDate date2 = HistoryGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                operDao.excellHistoryTable(customer, name, sdate1, sdate2);
            } else if (HistoryDan.getValue() != null && HistoryGacha.getValue() != null && operHistoryWho.getSelectionModel().getSelectedItem() == null && tarixSelectName.getSelectionModel().getSelectedItem() == null) {
                LocalDate date1 = HistoryDan.getValue();
                LocalDate date2 = HistoryGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                operDao.excellHistoryTable("", "", sdate1, sdate2);
            } else if (operHistoryWho.getSelectionModel().getSelectedItem() != null && tarixSelectName.getSelectionModel().getSelectedItem() != null && HistoryDan.getValue() == null && HistoryGacha.getValue() == null) {
                operDao.HistoryTableFilter(HistoryTable, operHistoryWho.getSelectionModel().getSelectedItem(), tarixSelectName.getSelectionModel().getSelectedItem(), "", "", tarixTotalQuantity, tarixTotalCost);
            } else if (operHistoryWho.getSelectionModel().getSelectedItem() != null && tarixSelectName.getSelectionModel().getSelectedItem() == null && HistoryDan.getValue() != null && HistoryGacha.getValue() != null) {
                LocalDate date1 = HistoryDan.getValue();
                LocalDate date2 = HistoryGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                operDao.excellHistoryTable(operHistoryWho.getSelectionModel().getSelectedItem(), "", sdate1, sdate2);
            } else if (operHistoryWho.getSelectionModel().getSelectedItem() == null && tarixSelectName.getSelectionModel().getSelectedItem() != null && HistoryDan.getValue() != null && HistoryGacha.getValue() != null) {
                LocalDate date1 = HistoryDan.getValue();
                LocalDate date2 = HistoryGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                operDao.excellHistoryTable("", tarixSelectName.getSelectionModel().getSelectedItem(), sdate1, sdate2);
            } else if (HistoryDan.getValue() == null && HistoryGacha.getValue() == null && operHistoryWho.getSelectionModel().getSelectedItem() == null && tarixSelectName.getSelectionModel().getSelectedItem() == null) {
                operDao.excellHistoryTable("", "", "", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void historyTable() {

        try {
            if (HistoryDan.getValue() != null && HistoryGacha.getValue() != null && operHistoryWho.getSelectionModel().getSelectedItem() != null && tarixSelectName.getSelectionModel().getSelectedItem() != null) {
                String customer = operHistoryWho.getSelectionModel().getSelectedItem();
                String name = tarixSelectName.getSelectionModel().getSelectedItem();
                LocalDate date1 = HistoryDan.getValue();
                LocalDate date2 = HistoryGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                operDao.HistoryTableFilter(HistoryTable, customer, name, sdate1, sdate2, tarixTotalQuantity, tarixTotalCost);
            } else if (HistoryDan.getValue() != null && HistoryGacha.getValue() != null && operHistoryWho.getSelectionModel().getSelectedItem() == null && tarixSelectName.getSelectionModel().getSelectedItem() == null) {
                LocalDate date1 = HistoryDan.getValue();
                LocalDate date2 = HistoryGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                operDao.HistoryTableFilter(HistoryTable, "", "", sdate1, sdate2, tarixTotalQuantity, tarixTotalCost);
            } else if (operHistoryWho.getSelectionModel().getSelectedItem() != null && tarixSelectName.getSelectionModel().getSelectedItem() != null && HistoryDan.getValue() == null && HistoryGacha.getValue() == null) {
                operDao.HistoryTableFilter(HistoryTable, operHistoryWho.getSelectionModel().getSelectedItem(), tarixSelectName.getSelectionModel().getSelectedItem(), "", "", tarixTotalQuantity, tarixTotalCost);
            } else if (operHistoryWho.getSelectionModel().getSelectedItem() != null && tarixSelectName.getSelectionModel().getSelectedItem() == null && HistoryDan.getValue() != null && HistoryGacha.getValue() != null) {
                LocalDate date1 = HistoryDan.getValue();
                LocalDate date2 = HistoryGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                operDao.HistoryTableFilter(HistoryTable, operHistoryWho.getSelectionModel().getSelectedItem(), "", sdate1, sdate2, tarixTotalQuantity, tarixTotalCost);
            } else if (operHistoryWho.getSelectionModel().getSelectedItem() == null && tarixSelectName.getSelectionModel().getSelectedItem() != null && HistoryDan.getValue() != null && HistoryGacha.getValue() != null) {
                LocalDate date1 = HistoryDan.getValue();
                LocalDate date2 = HistoryGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                operDao.HistoryTableFilter(HistoryTable, "", tarixSelectName.getSelectionModel().getSelectedItem(), sdate1, sdate2, tarixTotalQuantity, tarixTotalCost);
            } else if (HistoryDan.getValue() == null && HistoryGacha.getValue() == null && operHistoryWho.getSelectionModel().getSelectedItem() == null && tarixSelectName.getSelectionModel().getSelectedItem() == null) {
                operDao.HistoryTableFilter(HistoryTable, "", "", "", "", tarixTotalQuantity, tarixTotalCost);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void QarzTable() {

        try {
            if (QarzDan.getValue() != null && QarzGacha.getValue() != null && sellactionSelectCustomer.getSelectionModel().getSelectedItem() != null) {
                String name = sellactionSelectCustomer.getSelectionModel().getSelectedItem();
                LocalDate date1 = QarzDan.getValue();
                LocalDate date2 = QarzGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                operDao.QarzTableFilter(QarzTable, name, sdate1, sdate2,  sellsumT,  selldollarT,  sellhrT,  sellsumP,  selldollarP,  sellhrP);
            } else if (QarzDan.getValue() != null && QarzGacha.getValue() != null && sellactionSelectCustomer.getSelectionModel().getSelectedItem() == null) {
                LocalDate date1 = QarzDan.getValue();
                LocalDate date2 = QarzGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                operDao.QarzTableFilter(QarzTable, "", sdate1, sdate2, sellsumT,  selldollarT,  sellhrT,  sellsumP,  selldollarP,  sellhrP);
            } else if (sellactionSelectCustomer.getSelectionModel().getSelectedItem() != null && QarzDan.getValue() == null && QarzGacha.getValue() == null) {
                operDao.QarzTableFilter(QarzTable, sellactionSelectCustomer.getSelectionModel().getSelectedItem(), "", "", sellsumT,  selldollarT,  sellhrT,  sellsumP,  selldollarP,  sellhrP);
            } else if (sellactionSelectCustomer.getSelectionModel().getSelectedItem() == null && QarzDan.getValue() == null && QarzGacha.getValue() == null) {
                operDao.QarzTableFilter(QarzTable, "1", "1", "1", sellsumT,  selldollarT,  sellhrT,  sellsumP,  selldollarP,  sellhrP);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnHistoryFilterAction() {
        historyTable();
    }

    @FXML
    private void btnQarzFilterAction() {
        QarzTable();
    }

    @FXML
    private void CheckExcellBtnAction() {
        try {
            try {
                if (QarzDan.getValue() != null && QarzGacha.getValue() != null && sellactionSelectCustomer.getSelectionModel().getSelectedItem() != null) {
                    String name = sellactionSelectCustomer.getSelectionModel().getSelectedItem();
                    LocalDate date1 = QarzDan.getValue();
                    LocalDate date2 = QarzGacha.getValue();
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    operDao.getCheckExcellSheet(name, sdate1, sdate2);
                } else if (QarzDan.getValue() != null && QarzGacha.getValue() != null && sellactionSelectCustomer.getSelectionModel().getSelectedItem() == null) {
                    LocalDate date1 = QarzDan.getValue();
                    LocalDate date2 = QarzGacha.getValue();
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    operDao.getCheckExcellSheet("", sdate1, sdate2);
                } else if (sellactionSelectCustomer.getSelectionModel().getSelectedItem() != null && QarzDan.getValue() == null && QarzGacha.getValue() == null) {
                    operDao.getCheckExcellSheet(sellactionSelectCustomer.getSelectionModel().getSelectedItem(), "", "");
                } else if (sellactionSelectCustomer.getSelectionModel().getSelectedItem() == null && QarzDan.getValue() == null && QarzGacha.getValue() == null) {
                    operDao.getCheckExcellSheet("1", "1", "1");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnExchangeFilterAction() {
        try {
            btnFilter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void table() throws SQLException {
        productDao.exchangeTaleDao(ExchangeTable, "1", "1", "1", ExchangeTotalQuantity);
    }

    private void btnFilter() {

        try {
            if (ExchangeDan.getValue() != null && ExchangeGacha.getValue() != null && exchangeSelectName.getSelectionModel().getSelectedItem() != null) {

                String name = exchangeSelectName.getSelectionModel().getSelectedItem();
                LocalDate date1 = ExchangeDan.getValue();
                LocalDate date2 = ExchangeGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                productDao.exchangeTaleDao(ExchangeTable, name, sdate1, sdate2, ExchangeTotalQuantity);

            } else if (ExchangeDan.getValue() != null && ExchangeGacha.getValue() != null && exchangeSelectName.getSelectionModel().getSelectedItem() == null) {

                LocalDate date1 = ExchangeDan.getValue();
                LocalDate date2 = ExchangeGacha.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                productDao.exchangeTaleDao(ExchangeTable, "", sdate1, sdate2, ExchangeTotalQuantity);
            } else if (exchangeSelectName.getSelectionModel().getSelectedItem() != null && ExchangeDan.getValue() == null && ExchangeGacha.getValue() == null) {

                productDao.exchangeTaleDao(ExchangeTable, exchangeSelectName.getSelectionModel().getSelectedItem(), "", "", ExchangeTotalQuantity);

            } else if (exchangeSelectName.getSelectionModel().getSelectedItem() == null && ExchangeDan.getValue() == null && ExchangeGacha.getValue() == null) {
                productDao.exchangeTaleDao(ExchangeTable, "1", "1", "1", ExchangeTotalQuantity);
            } else {
                productDao.exchangeTaleDao(ExchangeTable, "1", "1", "1", ExchangeTotalQuantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callFunctions() {
        try {
            historyTable();
            operTable();
            balanceTable();
            debtorsTable();
            QarzTable();
            checkHistory();
            table();

            productDao1.addWhoCombobox(operWho);
            productDao1.addWhoCombobox(operCustomerFilter);
            productDao1.addWhoCombobox(operHistoryWho);
            productDao1.addWhoCombobox(ComboBoxBalance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnSellExcellAction() {
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Excelga ko'chirasizmi");
            alert.setHeaderText(null);
            alert.setContentText("Excelga ko'chirish");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent())
                if (result.get() == ButtonType.OK) {
                    if (operCustomerFilter.getSelectionModel().getSelectedItem() !=null && operDanFilter.getValue() != null && operDanFilter.getValue() != null) {
                        String who = operCustomerFilter.getSelectionModel().getSelectedItem();
                        LocalDate date1 = operDanFilter.getValue();
                        LocalDate date2 = operGachaFilter.getValue();
                        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                        String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                        String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                        operDao.excellOperTable(who, sdate1, sdate2);
                    } else if(operCustomerFilter.getSelectionModel().getSelectedItem() == null && operDanFilter.getValue() != null && operDanFilter.getValue() != null){
                        LocalDate date1 = operDanFilter.getValue();
                        LocalDate date2 = operGachaFilter.getValue();
                        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                        String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                        String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                        operDao.excellOperTable("", sdate1, sdate2);
                    } else if(operCustomerFilter.getSelectionModel().getSelectedItem() ==null && operDanFilter.getValue() == null){
                        operDao.excellOperTable("", "", "");
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnExcellBalanceTable() throws SQLException {
        Workbookcontroller workbookcontroller = new Workbookcontroller();
        workbookcontroller.datebaseToExcel("balance_v", "balance.xls");
    }

    @FXML
    private void btnExcellFilePath() {
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            Stage stage = null;
            File dir = directoryChooser.showDialog(stage);
            String path = dir.getAbsolutePath() + "\\";
            SystemUtilsDao systemUtilsDao = new SystemUtilsDao();
            systemUtilsDao.excellFolder(path);
            JOptionPane.showMessageDialog(null, "Rasm joyi saqlandi!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Ayrboshlash
     */
    @FXML
    public void btnChange() {
        String who = LabelBalanceIdWho.getText();
        String type = ChangeType.getSelectionModel().getSelectedItem();
        String sum = ChangeSum.getText().trim().replaceAll("\\s+", "");
        operDao.exchange(who, type, sum);
        balanceTable();
        ChangeSum.setText("");
        ChangeSum.setPromptText("O'zgartirildi");
    }

    private void getSellactionCompanyName() {
        try {
            productDao.getSellActionNameCombobox(sellactionSelectCustomer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getSellactionExchangeName() {
        try {
            productDao.getSellActionExchangeName(exchangeSelectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getTarixSelectName() {
        try {
            productDao.getTarixSelectName(tarixSelectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void MainRevertAction() {
        try {
            try {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Savdodan qaytarish");
                dialog.setHeaderText("Maxsulot idsi");
                dialog.setContentText("Qaytarasizmi ?");
                Optional<String> result = dialog.showAndWait();
                // The Java 8 way to get the response value (with lambda expression).
                result.ifPresent(name ->
                        {
                            try {
                                SellAction sellAction = productDao.revertProduct(revertLabel_id.getText());
                                productDao.revertAll(sellAction.getId(), sellAction.getSum(), sellAction.getDollar(), sellAction.getHr(), sellAction.getPsum(), sellAction.getPdollar(), sellAction.getPhr(), sellAction.getSale(), sellAction.getCustomer_id(), sellAction.getCr_by(), sellAction.getDate_cr());
                                ArrayList<ReceiptCheck> receiptChecks = null;
                                receiptChecks = utilsDao.PerProductRevert(revertLabel_id.getText());
                                productDao.revertPeoducrAll(receiptChecks);
                                productDao.deleteHistory(revertLabel_id.getText());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}






//