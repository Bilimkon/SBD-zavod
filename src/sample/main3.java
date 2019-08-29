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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.dao.Main3Dao;
import sample.model.Production3;
import sample.model.TableDsp;
import sample.model.core.manList;
import sample.model.core.product;
import sample.model.core.production2;
import sample.model.core.production3;
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

public class main3 implements Initializable {

    @FXML
    private TableView tableMain3;
    @FXML
    private TableView tablePaper;
    @FXML
    private TableView tableDsp;
    @FXML
    private Button btnClose;
    @FXML
    private Button btnPaper;
    @FXML
    private TextField textPaper;
    @FXML
    private TextField textDsp;
    @FXML
    private Button btnDsp;
    @FXML
    private Label labelId;
    @FXML
    private TextField labelBarcode;
    @FXML
    private Label labelQuantity;
    @FXML
    private TextField textUpdate;
    @FXML
    private TextField textName3;
    @FXML
    private Label textFirstName;
    @FXML
    private Label textLastName;
    Main3Dao main3Dao = new Main3Dao();
    @FXML
    private ComboBox<String> listName;
    @FXML
    private TextField textBarcode;
    @FXML
    private TextField textType;
    @FXML
    private Label textQuantity;
    @FXML
    private Label textColor;
    @FXML
    private TextField text_id;
    @FXML
    DatePicker main3Date;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initializeTablePaper();
        initializeTableDsp();
        initializeProduction3();
        tablePaper();
        tableDsp();
        tableProduction3();
        setUpdate();
        selectListName();
        listName.setTooltip(new Tooltip());
        new ComboBoxAutoComplete<String>(listName);
        main3Date.setValue(LocalDate.now());

        tablePaper.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                sample.model.tablePaper product = (sample.model.tablePaper) tablePaper.getSelectionModel().getSelectedItem();
                try {
                    dialogRevert1(product.getBarcode(), product.getName(), product.getQuantity(), product.getType(), product.getColor());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });


        /**
         *   Timer()
         */
        Timeline ficeSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                tablePaper();
                tableDsp();
                tableProduction3();
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

        textDsp.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    btnDspAction();
                }
            }
        });

        textPaper.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    btnPaperAction();
                }
            }
        });
    }

    private void initializeTablePaper() {
        TableColumn id = new TableColumn("id");
        TableColumn barcode = new TableColumn("Barcode");
        TableColumn name = new TableColumn("Qog'oz Nomi");
        TableColumn type = new TableColumn("Turi");
        // TableColumn color = new TableColumn("Rangi");
        TableColumn quantity = new TableColumn("Miqdori");

        tablePaper.getColumns().addAll(id, barcode, name, quantity);

        id.setCellValueFactory(new PropertyValueFactory<sample.model.tablePaper, String>("id"));
        barcode.setCellValueFactory(new PropertyValueFactory<sample.model.tablePaper, String>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<sample.model.tablePaper, String>("name"));
        type.setCellValueFactory(new PropertyValueFactory<sample.model.tablePaper, String>("type"));
        // color.setCellValueFactory(new PropertyValueFactory<sample.model.tablePaper, String>("color"));
        quantity.setCellValueFactory(new PropertyValueFactory<sample.model.tablePaper, String>("quantity"));

    }

    private void initializeTableDsp() {
        TableColumn id = new TableColumn("id");
        TableColumn barcode = new TableColumn("Barcode");
        TableColumn name = new TableColumn("Material Nomi");
        // TableColumn type = new TableColumn("Turi");
        TableColumn quantity = new TableColumn("Miqdori");

        tableDsp.getColumns().addAll(id, barcode, name, quantity);

        id.setCellValueFactory(new PropertyValueFactory<TableDsp, String>("id"));
        barcode.setCellValueFactory(new PropertyValueFactory<TableDsp, String>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<TableDsp, String>("name"));
        // type.setCellValueFactory(new PropertyValueFactory<TableDsp, String>("type"));
        quantity.setCellValueFactory(new PropertyValueFactory<TableDsp, String>("quantity"));

    }

    private void initializeProduction3() {
        TableColumn id = new TableColumn("id");
        TableColumn barcode = new TableColumn("Barcode");
        TableColumn name = new TableColumn("Nomi");
        TableColumn type = new TableColumn("Turi");
        TableColumn color = new TableColumn("Rangi");
        TableColumn quantity = new TableColumn("Miqdori");
        TableColumn pBarcode = new TableColumn("Qog'oz barcodi");
        TableColumn pName = new TableColumn("Q nomi");
        TableColumn pCost = new TableColumn("Q Narxi");
        TableColumn pQuantity = new TableColumn("Q miqdori");
        TableColumn pColor = new TableColumn("Q rangi");
        TableColumn dBarcode = new TableColumn("DSP/MDF barcode");
        TableColumn dName = new TableColumn("Nomi");
        TableColumn dQuantity = new TableColumn("Miqdori");
        TableColumn cr_on = new TableColumn("Sana");
        TableColumn cr_by = new TableColumn("Ishchi");

        tableMain3.getColumns().addAll(id, barcode, name, quantity, pBarcode, pName, pCost, pQuantity, pColor, dBarcode, dName, dQuantity, cr_on, cr_by);

        id.setCellValueFactory(new PropertyValueFactory<Production3, String>("id"));
        barcode.setCellValueFactory(new PropertyValueFactory<Production3, String>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<Production3, String>("name"));
        type.setCellValueFactory(new PropertyValueFactory<Production3, String>("type"));
        color.setCellValueFactory(new PropertyValueFactory<Production3, String>("color"));
        quantity.setCellValueFactory(new PropertyValueFactory<Production3, String>("quantity"));
        pBarcode.setCellValueFactory(new PropertyValueFactory<Production3, String>("pBarcode"));
        pName.setCellValueFactory(new PropertyValueFactory<Production3, String>("pName"));
        pCost.setCellValueFactory(new PropertyValueFactory<Production3, String>("pCost"));
        pQuantity.setCellValueFactory(new PropertyValueFactory<Production3, String>("pQuantity"));
        pColor.setCellValueFactory(new PropertyValueFactory<Production3, String>("pColor"));
        dBarcode.setCellValueFactory(new PropertyValueFactory<Production3, String>("dBarcode"));
        dName.setCellValueFactory(new PropertyValueFactory<Production3, String>("dName"));
        dQuantity.setCellValueFactory(new PropertyValueFactory<Production3, String>("dQuantity"));
        cr_on.setCellValueFactory(new PropertyValueFactory<Production3, String>("cr_on"));
        cr_by.setCellValueFactory(new PropertyValueFactory<Production3, String>("cr_by"));

    }

    private void tablePaper() {
        try {
            main3Dao.tablePaper(tablePaper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tableDsp() {
        try {
            main3Dao.tableDsp(tableDsp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tableProduction3() {
        try {
            main3Dao.tableProduction(tableMain3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpdate() {
        try {
            tableMain3.setOnMouseClicked(event -> {
                Production3 production3 = (Production3) tableMain3.getSelectionModel().getSelectedItem();
                try {
                    textQuantity.setText(production3.getdQuantity());
                    textColor.setText(production3.getpColor());
                    text_id.setText(production3.getId());
                    //textBarcode.setText(production3.getBarcode());
                    //textType.setText(production3.getType());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Error" + exc, "Xatolik", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void dialogRevert1(String barcode, String nomi, String quantity, String type_id, String color) {
        try {
            TextInputDialog dialog = new TextInputDialog("1");
            dialog.setTitle("Maxsulot");
            dialog.setHeaderText("Nomi:" + nomi + "\nBarcode: " + barcode + "\nMiqdori: " + quantity);
            dialog.setContentText("Miqdorni kiriting");
            Optional<String> result = dialog.showAndWait();
            // The Java 8 way to get the response value (with lambda expression).
            result.ifPresent(name ->
                    {
                        try {
                            main3Dao.insertSellTableMain2(barcode, nomi, name, type_id, color);
                            tablePaper();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Inserting dsp to production3
    private void customDialog(String name1, String barcode, String type, String quantity) {
        try {
            TextInputDialog dialog = new TextInputDialog("1");
            dialog.setTitle("Maxsulot");
            dialog.setHeaderText("Nomi:" + name1 + "\nBarcode: " + barcode + "\nTuri: " + type + "\nMiqdori: " + quantity);
            dialog.setContentText("Miqdorni kiriting");
            Optional<String> result = dialog.showAndWait();
            // The Java 8 way to get the response value (with lambda expression).
            result.ifPresent(name ->
                    {
                        try {
                            main3Dao.addToSaleTable(barcode, name1, name);
                            tableProduction3();
                            tableDsp();
                            tablePaper();
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
     * Custom dialog for getting Dsp product
     */
    @FXML
    private void btnDspAction() {
        try {
            if (!textBarcode.getText().isEmpty()) {
                String barcodeDsp = textDsp.getText();
                product product = main3Dao.getDspBarcode(barcodeDsp);
                customDialog(product.getName(), product.getBarcode(), product.getType(), product.getQuantity());
            } else {
                JOptionPane.showMessageDialog(null, "Ishlab chiqarish uchun tepadagi listdan maxsulot tanlang!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Barcodeni kiriting");
        } finally {
            textDsp.setText("");
        }
    }

    /**
     * Custom dialog for getting paper product
     */
    private void customDialogPaper(String name1, String barcode, String type, String cost, String unit, String color, String quantity, String type_p) {
        try {
            LocalDate date1 = main3Date.getValue();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            TextInputDialog dialog = new TextInputDialog("1");
            dialog.setTitle("Maxsulot");
            dialog.setHeaderText("Nomi:" + name1 + "\nBarcode: " + barcode + "\nTuri: " + type + "\nMiqdori: " + quantity);
            dialog.setContentText("Miqdorni kiriting");
            Optional<String> result = dialog.showAndWait();
            // The Java 8 way to get the response value (with lambda expression).
            result.ifPresent(name ->
                    {
                        try {
                            main3Dao.addPaperProduction3Table(barcode, name1, type, color, name, cost, sdate1);
                            main3Dao.updatePaperAmount(barcode, name);
                            tableProduction3();
                            tableDsp();
                            tablePaper();
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
    private void btnPaperAction() {
        try {
            String barcodePaper = textPaper.getText();
            production2 production2 = main3Dao.getPaperBarcode(barcodePaper);
            customDialogPaper(production2.getName(), production2.getBarcode(), production2.getType(), "1", "1", production2.getColor(), production2.getQuantity(), "2");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Bunday barcodeli maxsulot mavjud emas!");
        } finally {
            textPaper.setText("");
        }
    }

    @FXML
    private void btnRevertAction() {
        revertAction();
    }

    private void revertAction() {

        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Qaytarish!");
            alert.setHeaderText(null);
            alert.setContentText("Ishlab chiqarilgan maxsulotni orqaga qaytarasizmi");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent())
                if (result.get() == ButtonType.OK) {
                    if(!text_id.getText().isEmpty()) {
                        String id = text_id.getText().trim();
                        production3 dsp2 = main3Dao.getProductMain3Barcode(id);
                        main3Dao.revertAction1(dsp2.getpBarcode(), dsp2.getpQuantity());
                        main3Dao.revertAction2(dsp2.getdBarcode(), dsp2.getdQuantity());
                        main3Dao.revertAction3(id);
                        tableProduction3();
                        tableDsp();
                        tablePaper();
                    } else {
                        JOptionPane.showMessageDialog(null, "Qaytarish uchun maxsulot tanlang!");
                    }
                }

        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Error" + exc, "Xatolik", JOptionPane.ERROR_MESSAGE);
        }

    }


    @FXML
    private void btnUpdateAction() {
        updateAction();
    }

    private void updateAction() {
        try {
            if (!textBarcode.getText().isEmpty() && !text_id.getText().isEmpty()) {
                String id_u = text_id.getText().trim().replaceAll("\\s+", "");
                String name = listName.getSelectionModel().getSelectedItem();
                String quantity_u = textQuantity.getText().trim().replaceAll("\\s+", "");
                String type = textType.getText().trim().replaceAll("\\s+", "");
                String barcode = textBarcode.getText().trim().trim().replaceAll("\\s+", "");
                String color = textColor.getText().trim().trim().replaceAll("\\s+", "");
                main3Dao.setUpdateAction(quantity_u, name, barcode, type, color, id_u);
                tableProduction3();
            } else {
                JOptionPane.showMessageDialog(null, "Ishlab chiqarish uchun jadvaldan maxsulot tanlang!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Tayyor bo'lgan laminantlarni savdo bo'limiga jonatish
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
                            main3Dao.insertSellTableMain3(id, barcode, type_id, name1, quantity, cost, unit, description);
                            tablePaper();
                            tableDsp();
                            tableProduction3();
                            textBarcode.setText("");
                            text_id.setText("");
                            textQuantity.setText("");
                            textType.setText("");
                            textColor.setText("");
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
    private void btnLaminantSaleAction() {
        try {
            if (!text_id.getText().isEmpty()) {
                String id = text_id.getText().trim();
                production3 dsp2 = main3Dao.getProductMain3Barcode(id);
                customDialog(id, dsp2.getBarcode(), dsp2.getType(), dsp2.getName(), dsp2.getQuantity(), dsp2.getpCost(), "2", dsp2.getpColor());
            } else {
                JOptionPane.showMessageDialog(null, "Savdoga o'tkazish uchun maxsulot  tanlang!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnListProdducts() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/Manlist.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Maxsulotlar ro'yhati");
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.isAlwaysOnTop();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnSelectListAction() {
        selectListName();
        String name = listName.getSelectionModel().getSelectedItem();
        manList manList = main3Dao.selectListItem(name);
        textBarcode.setText(manList.getBarcode());
        textType.setText(manList.getType());
        textQuantity.setText("");
        textColor.setText("");
        text_id.setText("");
    }

    private void selectListName() {
        try {
            main3Dao.addListNameCombobox(listName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnTarixAction() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/P3Tarix.fxml"));
            Stage stage = new Stage();
            stage.setTitle("3 ishlab chiqarish liniyasi tarixi");
            stage.setScene(new Scene(root, 1000, 600));
            stage.setResizable(false);
            stage.isAlwaysOnTop();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

}
