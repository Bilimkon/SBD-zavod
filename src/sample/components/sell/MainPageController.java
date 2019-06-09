//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sample.components.sell;

import com.sun.istack.internal.Nullable;
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
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;
import sample.components.sell.Core.Models.BasketItem;
import sample.components.sell.Core.Models.CreditModel;
import sample.components.sell.Core.Models.ReceiptCheck;
import sample.components.sell.Core.User;
import sample.components.sell.DAO.*;
import sample.Main;
import sample.components.sell.Utils.PrinterService;
import sample.components.sell.Utils.Utils;
import sample.components.sell.productTableView.CustomerCreditTable;
import sample.components.sell.productTableView.ProductTable;
import sample.components.sell.views.CustomItems.CustomBasketItem.ShopItemListItem;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

/**
 * Humoyun Qo'rg'onov  SBD(Software Business Development)
 */
public class MainPageController extends Parent implements Initializable {
    private ProductDao productDao;
    private UtilsDao utilsDao;
    @FXML private TextField textSampleIzlash;
    private Connection myConn;
    @FXML private TableView<ProductTable> tableSampleManual;
    @FXML private VBox addedItemsList;
    @FXML private Label totalCost;
    @FXML private TextField scanCodeField;
    @FXML private Label idUserName;
    @FXML private Label idStartDate;
    @FXML private Label ClockText;
    @FXML private Button BtnSell;
    @FXML private Button btnClose;
    @FXML private Button btnLogOut;
    @FXML private ListView<String> typeList;
    private ObservableList<ProductTable> productTables;
    public static List<BasketItem> basket = new ArrayList<>();
    private CreditModel credit = null;
    Double dollar = 8500.0;
    Task<Void> longTaskRun;
    private HistoryDao historyDao;

    {
        try {
            historyDao = new HistoryDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeTable() {
        TableColumn<ProductTable, String> barcode = new TableColumn<>("Barcode");
        TableColumn<ProductTable, String> name = new TableColumn<>("Nomi");
        TableColumn<ProductTable, Integer> quantity = new TableColumn<>("Miqdori");
        TableColumn<ProductTable, Double> cost = new TableColumn<>("Narxi");

        tableSampleManual.getColumns().addAll(barcode, name, quantity, cost);

        barcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        addedItemsList.getChildren().addListener((ListChangeListener<Node>) c -> {
            totalCost.setText(Utils.ThousandDivider(calculateCurrentTotalSum()) + " sum");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

        initializeTable();
        User u = LoginController.currentUser;
        if (u == null) {
            u = new User();
            u.setFirstName("Muhammadjon");
            u.setLastName("Toxirov");
            u.setId(1);
            LoginController.currentUser = u;
            u.setDate(Calendar.getInstance().getTime());
        }
        setUserData(u);
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
    }

    private void setUserData(User u) {
        idUserName.setText(u.getFirstName() + " " + u.getLastName());
        idStartDate.setText(Utils.getCurrnetDateInStandardFormat());
    }

    private int counter = 0;

    private void addProductTableToList(ProductTable productTable) {
        FXMLLoader loader = createCustomItemLoader("AddedItemListItem", "CustomBasketItem/");
        assert loader != null;
        try {
            BasketItem basketItem = BasketItem.getInstance();
            basketItem.setAll(productTable.getBarcode(), productTable.getName(), Float.parseFloat(productTable.getCost()), 1, true);
            Pane p = loader.load();
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
            field.textProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    Label l = (Label) p.lookup("#itemPrice");
                    BasketItem i = (BasketItem) p.getUserData();
                    int quantity = productDao.getQuantity(i.getBarcode());

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

                    int number = Utils.isNumberInRange(Integer.valueOf(newValue), 0, quantity);
                    if (number != Integer.valueOf(newValue)) {
                        field.setText(number + "");
                        field.selectAll();
                    } else {
                        int finalNewValue = Integer.parseInt(newValue);
                        Platform.runLater(() -> {
                            try {
                                changeBasketItemAmount(i.getBarcode(), finalNewValue);
                                DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
                                symbols.setGroupingSeparator(' ');
                                DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
                                String app = String.valueOf(formatter.format(calculateCurrentTotalSum()));
                                totalCost.setText(app + " sum");
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }
                        });
                    }
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

    public MainPageController() {
        try {
            myConn = Database.getConnection();
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
            return new FXMLLoader(getClass().getResource("views/CustomItems/" + path));
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
            TableView tableView = (TableView) vBox.lookup("#table_credit");
            TextField textFirstName = (TextField) vBox.lookup("#textFirstName");
            TextField textLastName = (TextField) vBox.lookup("#textLastName");
            TextField textSaleSumm = (TextField) vBox.lookup("#textSaleSumm");
            TextField textSalePercent = (TextField) vBox.lookup("#textSalePercent");
            TextField textPlastik = (TextField) vBox.lookup("#textPlastik");
            TextField textCreditSumm = (TextField) vBox.lookup("#textCreditSumm");
            Label LabelTotalSumm = (Label) vBox.lookup("#LabelTotalSumm");
            Button btnAddCustomer = (Button) vBox.lookup("#btnAddCustomer");
            ToggleButton btnTogglePlastik = (ToggleButton) vBox.lookup("#btnTogglePlastik");
            Label labelCustomerName = (Label) vBox.lookup("#labelCustomerName");
            Label labelCustomerId = (Label) vBox.lookup("#labelCustomerId");
            ToggleButton btnToggleQarz = (ToggleButton) vBox.lookup("#btnToggleQarz");
            TextField textComment = (TextField) vBox.lookup("#textComment");
            TextField textSearch = (TextField) vBox.lookup("#textSearch");
            Label labelDollar = (Label) vBox.lookup("#labelDollar");

            /**
             * Customer credit table
             */
            TableColumn<CustomerCreditTable, Integer> id = new TableColumn<>("Raqami");
            TableColumn<CustomerCreditTable, String> firstName = new TableColumn<>("Ism");
            TableColumn<CustomerCreditTable, String> lastName = new TableColumn<>("Familiya");

            tableView.getColumns().addAll(id, firstName, lastName);

            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

            /**
             *  Connecting table to the database
             */
            Statement statement;
            ResultSet resultSet;
            ObservableList<CustomerCreditTable> customerCreditTables = FXCollections.observableArrayList();

            //Searching customers from customer table
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM main.customer ORDER BY id");
            while (resultSet.next()) {
                CustomerCreditTable customerCreditTable = new CustomerCreditTable();
                customerCreditTable.setId(resultSet.getInt("id"));
                customerCreditTable.setFirstName(resultSet.getString("firstname"));
                customerCreditTable.setLastName(resultSet.getString("lastname"));
                customerCreditTables.addAll(customerCreditTable);
            }
            tableView.setItems(customerCreditTables);
            resultSet.close();
            statement.close();
            /**
             * End of connection to database
             * */

            /**
             * Opreations
             * */

            LabelTotalSumm.setText(Utils.ThousandDivider(calculateCurrentTotalSum()));
            labelDollar.setText("$ " + String.valueOf(calculateCurrentTotalSum() / dollar).substring(0, 3));
            btnTogglePlastik.setOnMouseClicked(event -> {
                if (btnTogglePlastik.isSelected()) {
                    textPlastik.setText(LabelTotalSumm.getText());
                    textSaleSumm.setEditable(false);
                } else {
                    textPlastik.setText("");
                    textSaleSumm.setEditable(true);
                }
            });

            btnToggleQarz.setOnMouseClicked(event -> {
                btnToggleQarz.setSelected(false);
                textSaleSumm.setEditable(true);
                textCreditSumm.setText("");
                labelCustomerId.setText("");
                labelCustomerName.setText("");
            });

            textSaleSumm.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!Utils.isNumberValid(newValue, Utils.Number.DOUBLE) || newValue.equals("")) {
                    textSaleSumm.setText("0");
                    return;
                }

                longTaskRun = new Task<Void>() {

                    @Override
                    protected Void call() {
                        Platform.runLater(() -> {
                            if (Double.valueOf(newValue) >= calculateCurrentTotalSum()) {
                                textSaleSumm.setText(String.valueOf(calculateCurrentTotalSum()));
                            }

                            Double salesum = Double.valueOf(newValue);
                            Double saleQuantitySumm = (Double.valueOf(textSaleSumm.getText()));
                            double number = (Utils.isNumberInRange(Double.valueOf(newValue), 0.00, saleQuantitySumm) * 100);
                            textSalePercent.setText((String.valueOf(number / calculateCurrentTotalSum())).substring(0, 3) + " %");
                            LabelTotalSumm.setText(String.valueOf(calculateCurrentTotalSum() - salesum));
                            labelDollar.setText("$ " + String.valueOf((calculateCurrentTotalSum() - salesum) / dollar).substring(0, 3));
                        });
                        return null;
                    }
                };

                new Thread(longTaskRun).start();
            });

            /**
             * Sellecting customer from table.
             * */
            tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, (EventHandler<Event>) event -> {
                CustomerCreditTable customerCreditTable = (CustomerCreditTable) tableView.getSelectionModel().getSelectedItem();
                labelCustomerId.setText(String.valueOf(customerCreditTable.getId()));
                labelCustomerName.setText(customerCreditTable.getLastName());
                textCreditSumm.setText(LabelTotalSumm.getText());
                btnToggleQarz.setSelected(true);
                textSaleSumm.setEditable(false);
            });

            /**
             * End  of selecting table item.
             */

            /**
             * btnAddCustomer button to add new customers
             * */
            btnAddCustomer.setOnAction(event -> {
                try {
                    if (textFirstName.getText().length() > 0 && textLastName.getText().length() > 0) {
                        String firstname = textFirstName.getText();
                        String lastname = textLastName.getText();
                        btnAddCustomerAction(firstname, lastname);
                        textFirstName.setText("");
                        textLastName.setText("");
                    } else {
                        textFirstName.setPromptText(" Ismni yozing!");
                        textLastName.setPromptText(" Familiyani yozing!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            /**
             * Sell action ok button pressed
             * */
            btnOK.setOnMouseClicked(event -> {

                HistoryDao historyDao = new HistoryDao(myConn);
                try {
                    String saleSum = "0.0";
                    if (textSaleSumm.getText() != null && textSaleSumm.getText().length() > 0) {
                        saleSum = textSaleSumm.getText().replaceAll("\\s+", "");
                    }
                    String cash = "0";
                    String plastikSum = "0";
                    if (textPlastik.getText() != null && textPlastik.getText().length() > 0) {
                        plastikSum = textPlastik.getText().replaceAll("\\s+", "");
                    }
                    //Defining credit model
                    double creditSum = 0.0;
                    int customerId = 0;
                    if (textCreditSumm.getText() != null && textCreditSumm.getText().length() > 0) {
                        creditSum = (Double.valueOf(textCreditSumm.getText().replaceAll("\\s+", "")));
                        customerId = Integer.parseInt(labelCustomerId.getText().trim());
                        credit = new CreditModel(creditSum, customerId);
                    }
                    //Defining variable sum_paid
                    Double sum_paid = null;
                    sum_paid = calculateCurrentTotalSum() - (creditSum + Double.valueOf(saleSum));
                    cash = String.valueOf(Double.valueOf(LabelTotalSumm.getText().replaceAll("\\s+", "")) - Double.valueOf(plastikSum));
                    String total = String.valueOf(calculateCurrentTotalSum());
                    String commnet = "Empty";
                    if (textComment.getText() == null && textComment.getText().length() > 0) {
                        commnet = "Empty";
                    } else {
                        commnet = textComment.getText();
                    }

                    String user_id = String.valueOf(LoginController.currentUser.getId());
                    historyDao.insertBasketToHistory(basket, LoginController.currentUser, credit, total, cash, plastikSum, saleSum, user_id, String.valueOf(sum_paid), commnet);
                    btnActionIzlash();
                    //  printAction();
                    scanCodeField.requestFocus();
                    popOver.hide();
                    reset();
                } catch (Exception e) {
                    Utils.ErrorAlert("Xatolik", "Savdo amalga oshmadi", e.getMessage());
                    e.printStackTrace();
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
    private void btnAddCustomerAction(String firstname, String lastname) {
        try {
            historyDao.addCustomer(firstname, lastname);
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
        Thread clock = new Thread(() -> {
            while (sample.components.sell.Main.is_clock_alive) {
                Calendar cal = Calendar.getInstance();
                int second = cal.get(Calendar.SECOND);
                int minute = cal.get(Calendar.MINUTE);
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
//
//        CloseableHttpClient client =  HttpClientBuilder.create().build();
//        HttpPost postRequest = new HttpPost("http://localhost:6001/products/add");
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("barcode","1");
//        jsonObject.put("name","2");
//        jsonObject.put("type","3");
//        StringEntity se = new StringEntity(jsonObject.toString());
//        se.setContentType(new BasicHeader("Content-Type","application/json"));
//        postRequest.setEntity(se);
//
//        HttpResponse response = (HttpResponse) client.execute(postRequest);

        btnActionIzlash();
    }

    private void printAction() throws SQLException {
        String apple = Utils.convertDateToStandardFormat(Utils.getCurrentDate());
        PrinterService printerService = new PrinterService();
        System.out.println(printerService.getPrinters());
        ArrayList<ReceiptCheck> receiptChecks = null;
        receiptChecks = utilsDao.PerProduct();
        StringBuilder storage = new StringBuilder();
        for (ReceiptCheck item : receiptChecks) {
            storage.append(item.getName()).append("    Miqdori: ").append(item.getQuantity()).append("   Umumiy narxi: ").append(item.getPrice()).append("\n").append("----------------------------------------------\n");
        }
        System.out.println(storage);
        System.out.println(utilsDao.TotalSum());
        printer printer = new printer();
        //print some stuff
        printerService.printString(printer.printerName(), "\n" +
                "*********Software business development**********\n\n\n" +
                "*********    Egamberdi ota do'koni    **********\n\n" +
                storage + "\n" +
                "Umumiy summa              " + utilsDao.TotalSum() + " sum\n\n\n" +
                "***************" + apple + "***************\n" +
                "***********Xaridingiz uchun raxmat!***********\n\n\n\n\n\n\n\n");
        // cut that paper!
        byte[] cutP = new byte[]{0x1d, 'V', 1};
        printerService.printBytes(printer.printerName(), cutP);
    }

    public void btnLogOutAction() {
        Parent root = null;
        Stage stage = new Stage();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Chiqish");
        alert.setHeaderText(null);
        alert.setContentText("Dasturdan chiqasizmi?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent())
            if (result.get() == ButtonType.OK) try {
                root = FXMLLoader.load(getClass().getResource("views/login.fxml"));
                stage.setTitle("SBD boshqaruv tizimi");
                stage.setResizable(true);
                stage.setOnCloseRequest(event -> sample.components.sell.Main.is_clock_alive = false);
                Screen screen = Screen.getPrimary();
                Rectangle2D bounds = screen.getVisualBounds();
                stage.setScene(new Scene(root));
                stage.show();
                stage.getIcons().add(new Image(Main.class.getResourceAsStream("style/Images/SBD-logo.png")));
                myConn.close();
                // Hide this current window (if this is what you want)
                this.btnLogOut.getScene().getWindow().hide();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
    }

}
