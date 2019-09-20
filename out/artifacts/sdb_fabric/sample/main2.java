package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.dao.Main2Dao;
import sample.model.TableA;
import sample.model.TableB;
import sample.model.core.manList;
import sample.model.core.product;
import sample.model.core.production2;
import sample.utils.ComboBoxAutoComplete;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class main2 implements Initializable {

    @FXML
    private Button btnClose;
    @FXML
    private Button btnBarcode;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnRevert;
    @FXML
    private TextField textUpdate;
    @FXML
    private TableView tableA;
    @FXML
    private TableView tableB;
    @FXML
    private TextField textBarcode;
    @FXML
    private TextField textBarcode2;
    @FXML
    private Label textQuantity;
    @FXML
    private Label textId;
    @FXML
    private TextField textName2;
    @FXML
    private Label textFirstName;
    @FXML
    private Label textLastName;
    @FXML
    private TextField sname;
    @FXML
    private TextField p2_xarajat;
    @FXML
    private ComboBox<String> p2ComboType;
    @FXML
    private TextField p2Type;
    @FXML
    private DatePicker main2Date;
    @FXML
    private ComboBox<String> main2selectNameOmbor;
    @FXML
    private Label LabelNameSelectQuantityMain2;

    Main2Dao main2Dao = new Main2Dao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableA();
        initializeTableB();
        main2selectNameOmbor.getItems().addAll("");
        main2selectNameOmbor.getSelectionModel().selectFirst();
        tableA();
        tableB();
        setUpdate();
        selectListName();
        getOmborSelectName();
        main2Date.setValue(LocalDate.now());

        p2ComboType.setTooltip(new Tooltip());
        new ComboBoxAutoComplete<String>(p2ComboType);


        /**
         *   Timer()
         */

        Timeline ficeSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                tableA();
                tableB();
                selectListName();
                getOmborSelectName();
            }
        }));
        ficeSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        ficeSecondsWonder.play();
        /**
         *  End of timer()
         *
         */

        /**
         * Hot keys
         * */

        textBarcode.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    btnOkAction();
                }
            }
        });

        textUpdate.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        btnUpdateAction();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        p2_xarajat.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    btnP2OK();
                }
            }
        });
    }

    private void initializeTableA() {

        TableColumn id = new TableColumn("ID");
        TableColumn barcode = new TableColumn("Barcode");
        TableColumn name = new TableColumn("Nomi");
        TableColumn type = new TableColumn("Turi");
        TableColumn quantity = new TableColumn("Miqdori");

        tableA.getColumns().addAll(id, barcode, name, type, quantity);

        id.setCellValueFactory(new PropertyValueFactory<TableA, String>("id"));
        barcode.setCellValueFactory(new PropertyValueFactory<TableA, String>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<TableA, String>("name"));
        type.setCellValueFactory(new PropertyValueFactory<TableA, String>("type"));
        quantity.setCellValueFactory(new PropertyValueFactory<TableA, String>("quantity"));
    }

    private void initializeTableB() {
        TableColumn id = new TableColumn("ID");
        TableColumn date = new TableColumn("Sana");
        TableColumn barcode = new TableColumn("Barcode");
        TableColumn name = new TableColumn("Nomi");
        TableColumn quantity = new TableColumn("Miqdori");
        TableColumn color = new TableColumn("Rangi");
        TableColumn p_quantity = new TableColumn("Tayyor miqdori");
        TableColumn p_name = new TableColumn("Xarajat nomi");
        TableColumn p_barcode = new TableColumn("Xarajat barcodi");
        TableColumn user = new TableColumn("Hodim");

        tableB.getColumns().addAll(id, date, barcode, name, color, p_quantity, p_name, p_barcode, quantity, user);

        id.setCellValueFactory(new PropertyValueFactory<TableB, String>("id"));
        date.setCellValueFactory(new PropertyValueFactory<TableB, String>("date"));
        barcode.setCellValueFactory(new PropertyValueFactory<TableB, String>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<TableB, String>("name"));
        color.setCellValueFactory(new PropertyValueFactory<TableB, String>("color"));
        quantity.setCellValueFactory(new PropertyValueFactory<TableB, String>("quantity"));
        p_quantity.setCellValueFactory(new PropertyValueFactory<TableB, String>("p_quantity"));
        p_name.setCellValueFactory(new PropertyValueFactory<TableB, String>("p_name"));
        p_barcode.setCellValueFactory(new PropertyValueFactory<TableB, String>("p_barcode"));
        user.setCellValueFactory(new PropertyValueFactory<TableB, String>("p_barcode"));
    }

    private void tableA() {
        try {
            main2Dao.tableA(tableA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tableB() {
        try {
            if (main2selectNameOmbor.getSelectionModel().getSelectedItem() != null) {
                main2Dao.tableB(tableB, main2selectNameOmbor.getSelectionModel().getSelectedItem(), LabelNameSelectQuantityMain2);
            } else if (main2selectNameOmbor.getSelectionModel().getSelectedItem() == null) {
                main2Dao.tableB(tableB, "", LabelNameSelectQuantityMain2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpdate() {
        try {
            tableB.setOnMouseClicked(event -> {
                if (tableB.getSelectionModel().getSelectedItem() != null) {
                    TableB tableB1 = (TableB) tableB.getSelectionModel().getSelectedItem();
                    try {
                        textUpdate.setText(tableB1.getP_quantity());
                        textName2.setText(tableB1.getName());
                        textBarcode2.setText(tableB1.getBarcode());
                        p2Type.setText(tableB1.getType());
                        textId.setText(tableB1.getId());
                        textQuantity.setText(tableB1.getQuantity());
                        textBarcode.setText(tableB1.getP_barcode());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Error" + exc, "Xatolik", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void btnUpdateAction() throws SQLException {

        try {
            if (!textId.getText().isEmpty()) {
                String id = textId.getText();
                setUpdateAction();
                tableB();
                production2 dsp2 = main2Dao.getProductMain2Barcode(id);
                main2Dao.insertToHistory(dsp2.getId(), dsp2.getBarcode(), dsp2.getName(), dsp2.getType(), dsp2.getQuantity(), dsp2.getP_name(), dsp2.getP_barcode(), dsp2.getColor(), dsp2.getP_quantity(), dsp2.getDate(), dsp2.getUser_id());
            } else {
                JOptionPane.showMessageDialog(null, "Ishlab chiqarish uchun  jadvaldan maxsulot tanlang!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            textBarcode.setText("");
            textName2.setText("");
            textBarcode2.setText("");
            p2Type.setText("");
            textId.setText("");
            textQuantity.setText("");
            textUpdate.setText("");
        }
    }

    private void setUpdateAction() {
        try {

            String id = textId.getText().trim().replaceAll("\\s+", "");
            String name = textName2.getText().trim().replaceAll("\\s+", "");
            String barcode = textBarcode2.getText().trim().replaceAll("\\s+", "");
            String quantity = textUpdate.getText().trim().replaceAll("\\s+", "");
            main2Dao.setUpdateAction(quantity, name, barcode, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Adding paper to production2
     */
    private void customDialogAddToProduction2(String name1, String barcode, String type, String quantity, String cost, String unit, String color, String date) {
        try {
            TextInputDialog dialog = new TextInputDialog("1");
            dialog.setTitle("Maxsulot");
            dialog.setHeaderText("Nomi: " + name1 + "\nBarcode: " + barcode + "\nTuri: " + type + "\nMiqdori: " + quantity);
            dialog.setContentText("Miqdorni kiriting");
            Optional<String> result = dialog.showAndWait();
            // The Java 8 way to get the response value (with lambda expression).
            result.ifPresent(name ->
                    {
                        try {
                            main2Dao.addToProduction2Table(textBarcode2.getText(), textName2.getText(), p2Type.getText(), name, cost, unit, color, name1, barcode, date);
                            main2Dao.subtractAction(barcode, name);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnOkAction() {
        try {
            if (!textName2.getText().isEmpty() && textId.getText().isEmpty()) {
                String barcodePaper = textBarcode.getText();
                LocalDate date1 = main2Date.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                product product = main2Dao.getPaperBarcode(barcodePaper);
                customDialogAddToProduction2(product.getName(), product.getBarcode(), product.getType_id(), product.getQuantity(), product.getCost(), product.getUnit(), product.getColor(), sdate1);
            } else {
                JOptionPane.showMessageDialog(null, "Ishlab chiqariladigan maxsulotni tanlang!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Barcodeni kiriting");
        } finally {
            textBarcode.setText("");
            textBarcode2.setText("");
            textName2.setText("");
            p2Type.setText("");
            tableA();
            tableB();
        }
    }
    /**
     * End
     * */

    /**
     * Reverting products from production2
     */
    @FXML
    private void btnRevertAction() {
        try {
            if (!textId.getText().isEmpty() && !textBarcode.getText().isEmpty() && !textQuantity.getText().isEmpty()) {

                String id = textId.getText();
                String barcode = textBarcode.getText();
                String quantity = textQuantity.getText();
                main2Dao.revertAction(barcode, quantity);
                main2Dao.deleteRecord(id);
                tableA();
                tableB();
            } else {
                JOptionPane.showMessageDialog(null, "Malumotlar kiritilmagan!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Production2 to sell
     */
    private void customDialog(String id, String barcode, String type_id, String name1, String quantity, String cost, String unit, String description) {
        try {
            TextInputDialog dialog = new TextInputDialog("1");
            dialog.setTitle("Maxsulot");
            dialog.setHeaderText("Nomi:" + name1 + "\nBarcode: " + barcode + "\nMiqdori: " + quantity);
            dialog.setContentText("Miqdorni kiriting");

            Optional<String> result = dialog.showAndWait();

            // The Java 8 way to get the response value (with lambda expression).
            result.ifPresent(name ->
                    {
                        try {
                            main2Dao.insertSellTableMain2(id, barcode, type_id, name1, name, cost, unit, description);
                            tableB();
                            tableA();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnMain2SaleAction() {
        try {
            if (!textId.getText().isEmpty()) {
                String id = textId.getText().trim();
                production2 dsp2 = main2Dao.getProductMain2Barcode(id);
                customDialog(id, dsp2.getBarcode(), dsp2.getType(), dsp2.getName(), dsp2.getP_quantity(), "1", "1", dsp2.getColor());
            } else {
                JOptionPane.showMessageDialog(null, "Savdoga o'tkazish uchun maxsulot tanlang !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * End
     * */


    /**
     * Production2 to production3
     */

    public void customDialogP2ToP3(String id, String barcode, String type_id, String name1, String quantity, String cost, String unit, String description) {
        try {
            TextInputDialog dialog = new TextInputDialog("1");
            dialog.setTitle("Maxsulot");
            dialog.setHeaderText("Nomi:" + name1 + "\nBarcode: " + barcode + "\nMiqdori: " + quantity);
            dialog.setContentText("Miqdorni kiriting");
            Optional<String> result = dialog.showAndWait();
            // The Java 8 way to get the response value (with lambda expression).
            result.ifPresent(name ->
                    {
                        try {
                            main2Dao.insertintoProduction2_ready(id, barcode, type_id, name1, name, cost, unit, description);
                            tableB();
                            tableA();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnMain2Production3() {
        try {
            String id = textId.getText().trim();
            production2 dsp2 = main2Dao.getProductMain2Barcode(id);
            customDialogP2ToP3(id, dsp2.getBarcode(), dsp2.getType(), dsp2.getName(), dsp2.getP_quantity(), "1", "1", dsp2.getColor());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * End
     * */


    /**
     * Ikkinchi ishlab chiqarish liniyasida  ishlab chiqarish uchun
     * hom ashiyolarni  ishlatish
     */
    @FXML
    private void btnP2OK() {
        try {
            String barcode = p2_xarajat.getText().trim();
            product dsp2 = main2Dao.getPaperBarcode(barcode);
            customDialogOmborToP2Harajat(dsp2.getBarcode(), dsp2.getType(), dsp2.getName(), dsp2.getQuantity(), dsp2.getCost(), dsp2.getUnit(), dsp2.getColor());
            p2_xarajat.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Barcodeni kiriting!");

        }
    }

    private void customDialogOmborToP2Harajat(String barcode, String type_id, String name1, String quantity, String cost, String unit, String description) {
        try {
            TextInputDialog dialog = new TextInputDialog("1");
            dialog.setTitle("Maxsulot");
            dialog.setHeaderText("Nomi:" + name1 + "\nBarcode: " + barcode + "\nMiqdori: " + quantity);
            dialog.setContentText("Miqdorni kiriting");
            Optional<String> result = dialog.showAndWait();
            // The Java 8 way to get the response value (with lambda expression).
            result.ifPresent(name ->
                    {
                        try {
                            main2Dao.insertToPHarajat(barcode, type_id, name1, name, cost, unit, description);
                            tableB();
                            tableA();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Getting list of product templates
     */
    @FXML
    public void btnP2ListAction() {
        selectListName();
        String name = p2ComboType.getSelectionModel().getSelectedItem();
        manList manList = main2Dao.selectListItem(name);
        textBarcode2.setText(manList.getBarcode());
        textName2.setText(manList.getName());
        p2Type.setText(manList.getType());
        textBarcode.setText("");
        textId.setText("");
        textQuantity.setText("");
        textUpdate.setText("");
    }

    private void selectListName() {
        try {
            main2Dao.addListNameCombobox(p2ComboType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Sub modules
     */
    @FXML
    private void btnP2List() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/p2List.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ishlab chiqariladigan maxsulotlar ro'yhati");
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.isAlwaysOnTop();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void p2Histoty() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/p2Tarix.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ishlab chiqariladigan maxsulotlar  tarixi");
            stage.setScene(new Scene(root, 1000, 600));
            stage.setResizable(false);
            stage.isAlwaysOnTop();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btn2X() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/p2_xarajat.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Xarajatlar");
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.isAlwaysOnTop();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Chiqish
     */
    @FXML
    public void btnCloseAction() {
        closeAction(btnClose);
    }

    private void closeAction(Button btn) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Chiqish");
        alert.setHeaderText(null);
        alert.setContentText(" Dasturdan chiqmoqchimisiz ?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent())
            if (result.get() == ButtonType.OK) {

                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("SBD boshqaruv tizimi");
                    stage.setScene(new Scene(root, 1080, 720));
                    stage.setResizable(true);
                    stage.getIcons().add(
                            new Image(
                                    Main.class.getResourceAsStream("bar-chart.png")));
                    stage.show();
                    this.btnClose.getScene().getWindow().hide();

                    // Hide this current window (if this is what you want)

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
    }

    /**
     * End
     */
    @FXML
    private void btnExchangeAction() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/Exchange.fxml"));
            Stage stage = new Stage();
            stage.setTitle("O'tkazmalar tarixi");
            stage.setScene(new Scene(root, 900, 600));
            stage.setResizable(false);
            stage.isAlwaysOnTop();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnMain2OmborFilter() {
        try {
            tableB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getOmborSelectName() {
        try {
            main2Dao.getP2OmborSelectName(main2selectNameOmbor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
