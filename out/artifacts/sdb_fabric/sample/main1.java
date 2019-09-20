package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sample.dao.ProductDao;
import sample.dao.SystemUtilsDao;
import sample.dao.database;
import sample.model.Dsp2;
import sample.model.Product;
import sample.model.User;
import sample.utils.BarCodeService;
import sample.utils.Barcode_pdf;
import sample.utils.ComboBoxAutoComplete;
import sample.utils.Workbookcontroller;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;

import static sample.utils.BarCodeService.numbGen;

public class main1 implements Initializable {


    private Connection myConn = null;
    ProductDao productDao = new ProductDao();

    @FXML
    private TextField AdminTextSearch;
    @FXML
    private TableView<Product> AdminTable;
    @FXML
    private TextField textBarcode;
    @FXML
    private TextField textName;
    @FXML
    private ComboBox<String> ComboTypeList;
    @FXML
    private ComboBox<String> ComboBoxUnit;
    @FXML
    private TextField textQuantity;
    @FXML
    private TextField textCost;
    @FXML
    private TextField textColor;
    @FXML
    private TextArea textDescription;
    @FXML
    private TextField textId;
    @FXML
    private Button btnClose;
    @FXML
    Label textFirstName;
    @FXML
    Label textLastName;
    @FXML
    private ComboBox<String> comboBoxSuplier;
    @FXML
    private ComboBox<String> comboInvoice;
    @FXML
    private TextField textSentBarcode;
    @FXML
    private Button btnSentBarcode;
    @FXML
    private ComboBox<String> ComboboxColor;
    @FXML
    private Label omborPrice;
    @FXML
    private Label omborQuantity;
    @FXML
    private ComboBox<String> omborType;
    @FXML
    private ComboBox<String> omborName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeProductTab();
        setOzgaartirishMaxsulot();
        ComboBoxUnit.getItems().addAll("Dona", "Kg", "Litr", "Rulon", "m2");
        omborType.getItems().addAll("");
        omborName.getItems().addAll("");
        User u = Login.currentUser;
        textFirstName.setText(u.getFirstName());
        textLastName.setText(u.getLastname());

        try {
            AddTypeComboboxAction();
            AddSuplierComboboxAction();
            getTypeList();
            selectProductName();
            addInvoiceCombobox();
            productTable();
            colorComboBox();
            comboBoxSuplier.setTooltip(new Tooltip());
            ComboTypeList.setTooltip(new Tooltip());
            comboInvoice.setTooltip(new Tooltip());
            ComboboxColor.setTooltip(new Tooltip());
            omborType.setTooltip(new Tooltip());
            omborName.setTooltip(new Tooltip());
            new ComboBoxAutoComplete<String>(comboBoxSuplier);
            new ComboBoxAutoComplete<String>(ComboTypeList);
            new ComboBoxAutoComplete<String>(comboInvoice);
            new ComboBoxAutoComplete<String>(ComboboxColor);
            new ComboBoxAutoComplete<String>(omborType);
            new ComboBoxAutoComplete<String>(omborName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         *   Timer()
         */

        Timeline ficeSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                productTable();
                colorComboBox();
                getTypeList();
                selectProductName();
            }
        }));
        ficeSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        ficeSecondsWonder.play();

        /**
         *  End of timer()
         *
         */
    }


    private void initializeProductTab() {
        TableColumn<Product, String> id = new TableColumn<>("Id");
        TableColumn<Product, String> invoice = new TableColumn<>("invoice");
        TableColumn<Product, String> barcode = new TableColumn<>("Barcode");
        TableColumn<Product, String> name = new TableColumn<>("Nomi");
        TableColumn<Product, String> type = new TableColumn<>("Turi");
        TableColumn<Product, String> cost = new TableColumn<>("Narxi");
        TableColumn<Product, String> quantity = new TableColumn<>("Miqdori");
        TableColumn<Product, String> user = new TableColumn<>("Ishchi");
        TableColumn<Product, String> date = new TableColumn<>("Sana");
        TableColumn<Product, String> suplier = new TableColumn<>("Taminotchi");
        TableColumn<Product, String> unit = new TableColumn<>("Birlik");
        TableColumn<Product, String> description = new TableColumn<>("Ma'lumot");
        TableColumn<Product, String> color = new TableColumn<>("Rangi");

        AdminTable.getColumns().addAll(barcode, type, name, quantity, cost, invoice, suplier, unit, date, user, color, description, id);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        invoice.setCellValueFactory(new PropertyValueFactory<>("invoice"));
        barcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        user.setCellValueFactory(new PropertyValueFactory<>("cr_by"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_cr"));
        suplier.setCellValueFactory(new PropertyValueFactory<>("suplier"));
        unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));

    }

    public main1() {
        try {
            myConn = database.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void productTable() {
        try {
            if (omborName.getSelectionModel().getSelectedItem() != null && omborType.getSelectionModel().getSelectedItem() != null) {
                String name = omborName.getSelectionModel().getSelectedItem();
                String type = omborType.getSelectionModel().getSelectedItem();
                productDao.initializeTable(AdminTable, type, name, omborQuantity, omborPrice);
            } else if (omborType.getSelectionModel().getSelectedItem() != null && omborName.getSelectionModel().getSelectedItem() == null) {

                String type = omborType.getSelectionModel().getSelectedItem();
                productDao.initializeTable(AdminTable, type, "", omborQuantity, omborPrice);

            } else if (omborType.getSelectionModel().getSelectedItem() == null && omborName.getSelectionModel().getSelectedItem() != null) {
                String name = omborName.getSelectionModel().getSelectedItem();

                productDao.initializeTable(AdminTable, "", name, omborQuantity, omborPrice);
            } else {
                productDao.initializeTable(AdminTable, "", "", omborQuantity, omborPrice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void omborBtnAction() {
        try {
            productTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adding product
     */
    @FXML
    public void btnAddProductAction() {

        try {
            String type = ComboTypeList.getSelectionModel().getSelectedItem();
            String Suplier = comboBoxSuplier.getSelectionModel().getSelectedItem();
            String invoice = comboInvoice.getSelectionModel().getSelectedItem();
            String barcode = textBarcode.getText();
            String name = textName.getText().trim().replaceAll("\\s+", "");
            String unit = ComboBoxUnit.getSelectionModel().getSelectedItem();
            String quantity = textQuantity.getText();
            String cost = textCost.getText();
            String color = ComboboxColor.getSelectionModel().getSelectedItem();
            String description = textDescription.getText();

            productDao.addProduct(invoice, barcode, name, type, cost, quantity, unit, description, Suplier, color);
            textBarcode.setText("");
            textName.setText("");
            textQuantity.setText("");
            textCost.setText("");
            textDescription.setText("");
            productTable();
        } catch (Exception exc) {
            exc.printStackTrace();
            JOptionPane.showMessageDialog(null, "Hamma ma'lumotlarni kiritish shart!");
        }
    }
    /**
     * End
     * */


    /**
     * Setupdate action
     */
    private void setOzgaartirishMaxsulot() {

        try {
            AdminTable.setOnMouseClicked(event -> {
                if (AdminTable.getSelectionModel().getSelectedItem() != null) {
                    Product product = AdminTable.getSelectionModel().getSelectedItem();
                    try {
                        textBarcode.setText(product.getBarcode());
                        textName.setText(product.getName());
                        textCost.setText(product.getCost());
                        textQuantity.setText(product.getQuantity());
                        textDescription.setText(product.getDescription());
                        textId.setText(String.valueOf(product.getId()));
                        ComboboxColor.setValue(product.getColor());
                        omborType.setValue(product.getType());
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                }
            });
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
    /**
     * End
     * */


    /**
     * Product update
     */
    public void btnUpdateAction() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("O'zgartirish");
        alert.setHeaderText(null);
        alert.setContentText("Maxsulot malumotlarini o'zgartirasizmi ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent())
            if (result.get() == ButtonType.OK) {
                try {
                    String id = textId.getText();
                    String barcode = textBarcode.getText();
                    String name = textName.getText().trim().replaceAll("\\s+", "");
                    String quantity = textQuantity.getText();
                    String cost = textCost.getText();
                    String color = ComboboxColor.getSelectionModel().getSelectedItem();
                    String description = textDescription.getText();
                    productDao.updateProduct(id, barcode, name, quantity, cost, color, description);
                    productTable();
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
    }
    /**
     * End
     * */

    /**
     * Product deletion
     */
    public void btnDeleteAction() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("O'zgartirish");
        alert.setHeaderText(null);
        alert.setContentText("Maxsulotni rostdan ham o'chirasizmi ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent())
            if (result.get() == ButtonType.OK) {
                String id = textId.getText();
                try {
                    productDao.deleteProduct(id);
                    JOptionPane.showMessageDialog(null, "Maxsulot o'chirildi!");
                    productTable();
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
    }
    /**
     * End
     * */


    /**
     * Comboboxdagi amallar
     */
    @FXML
    private void AddTypeComboboxAction() {
        addTypeComboboxAction1();
    }

    private void addTypeComboboxAction1() {
        try {
            productDao.addTypeCombobox(ComboTypeList);
            String name = productDao.getUnitType(ComboTypeList.getValue());
            ComboBoxUnit.setValue(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void selectProductName() {
        try {
            productDao.addProductName(omborName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void colorComboBoxAction() {
        colorComboBox();
    }

    private void colorComboBox() {
        try {
            productDao.colorComboBox(ComboboxColor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void AddBtnInvoiceAction() {
        addInvoiceCombobox();
    }

    @FXML
    private void AddSuplierBtnAction() throws SQLException {
        AddSuplierComboboxAction();
    }

    private void AddSuplierComboboxAction() {
        try {
            productDao.addSuplierCombobox(comboBoxSuplier);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getTypeList() {
        try {
            productDao.addTypeCombobox(omborType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addInvoiceCombobox() {
        try {
            productDao.addInvoceCombobox(comboInvoice);
            String name = productDao.getCompanyName(comboInvoice.getValue());
            comboBoxSuplier.setValue(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * End
     * */


    /**
     * Ombor amallari
     */
    @FXML
    private void GenerateBarcode() {
        if (textBarcode.getText().isEmpty()) {
            String testCode = String.valueOf(numbGen());
            BarCodeService serv = new BarCodeService();
            String parsedString = serv.parseInput(testCode);
            textBarcode.setText(parsedString);
            String barCodeString = serv.generateCode(parsedString);
        } else {
            String barcode = textBarcode.getText();
            int lenght = barcode.length();
            if (lenght == 13) {
                BarCodeService serv = new BarCodeService();
                String parsedString = serv.parseInput(barcode);
                textBarcode.setText(parsedString);
                String barCodeString = serv.generateCode(parsedString);
            } else {
                JOptionPane.showMessageDialog(null, "Barcodega 13 honali son kiritilishi shart!");
            }
        }
    }

    @FXML
    private void btnPdfAction() {
        String barcode = textBarcode.getText();
        String name = textName.getText();
        if (!barcode.equals("") && !name.equals("")) {
            Barcode_pdf.createImage(name + ".png", barcode);
            JOptionPane.showMessageDialog(null, "Barcode yaratildi");
        } else {
            JOptionPane.showMessageDialog(null, "Maxsulot tanlanmagan");
        }
    }

    @FXML
    private void btnPathImage() {
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

    @FXML
    public void btnExcelAction() {
        Workbookcontroller workbookcontroller = new Workbookcontroller();
        try {
            workbookcontroller.datebaseToExcel("product_v", "Ombor.xls");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * End
     * */

    /**
     * Ombordan savdoga maxsulot o'tkazish
     */
    private void customDialog(String barcode, String type_id, String name1, String quantity, String cost, String unit, String description) {
        try {
            TextInputDialog dialog = new TextInputDialog("1");
            dialog.setTitle("Maxsulot");
            dialog.setHeaderText("Nomi:" + name1 + "\nBarcode: " + barcode + "\nMiqdori: " + quantity);
            dialog.setContentText("Miqdorni kiriting");
            Optional<String> result = dialog.showAndWait();

            result.ifPresent(name ->
                    {
                        try {
                            productDao.insertSellTableDsp2(barcode, type_id, name1, name, cost, unit, description);
                            productTable();
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
    private void btnSentBarcodeAction() {
        try {
            String barcode = textSentBarcode.getText();
            Dsp2 dsp2 = productDao.getDSP2Barcode(barcode);
            customDialog(dsp2.getBarcode(), dsp2.getType(), dsp2.getName(), dsp2.getQuantity(), dsp2.getCost(), dsp2.getUnit(), dsp2.getDescription());
        } catch (Exception e) {
            e.fillInStackTrace();
            JOptionPane.showMessageDialog(null, "Barcodni kiriting !");
        } finally {
            textSentBarcode.setText("");
            textSentBarcode.setPromptText("Savdoga o'tkazildi");
        }
    }
    /**
     * End
     * */

    /**
     * Inserting products with excell file
     */
    @FXML
    private void btnExcellUploadAction() {
        excellUpload();
    }

    public void excellUpload() {
        try {
            FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
            dialog.setMode(FileDialog.LOAD);
            dialog.setVisible(true);
            String file1 = dialog.getFile();
            FileInputStream file = new FileInputStream(new File(dialog.getDirectory() + file1));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    //This will change all Cell Types to String
                    cell.setCellType(CellType.STRING);
                    switch (cell.getCellType()) {
                        case BOOLEAN:
                            System.out.println("boolean===>>>" + cell.getBooleanCellValue() + "\t");
                            break;
                        case NUMERIC:
                            break;
                        case STRING:
                            cell.getStringCellValue();
                            break;
                    }
                }
                String barcode = row.getCell(0).getStringCellValue();
                String name = row.getCell(1).getStringCellValue();
                String type = row.getCell(2).getStringCellValue();
                String type_id = row.getCell(3).getStringCellValue();
                String cost = row.getCell(4).getStringCellValue();
                String quantity = row.getCell(5).getStringCellValue();
                String unit = row.getCell(6).getStringCellValue();
                String suplier_id = row.getCell(7).getStringCellValue();
                String invoice_id = row.getCell(8).getStringCellValue();
                String color = row.getCell(9).getStringCellValue();
                String description = row.getCell(10).getStringCellValue();
                productDao.InsertRowInDB(barcode, name, type, type_id, cost, quantity, unit, suplier_id, invoice_id, color, description);
                productTable();
            }
            file.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * End of Inserting from excell
     */

    @FXML
    private void btnColorAction() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/color.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Rang qo'shish");
            stage.setScene(new Scene(root, 900, 500));
            stage.setResizable(false);
            stage.isAlwaysOnTop();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void AddTypeAction() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/AddType.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Tur qo'shish");
            stage.setScene(new Scene(root, 900, 500));
            stage.setResizable(false);
            stage.isAlwaysOnTop();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnSuplierAction() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/Suplier.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Taminotchi qo'shish");
            stage.setScene(new Scene(root, 900, 500));
            stage.setResizable(false);
            stage.isAlwaysOnTop();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnInvoiceAction() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/Invoice.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Invoice qo'shish");
            stage.setScene(new Scene(root, 1000, 700));
            stage.setResizable(false);
            stage.isAlwaysOnTop();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ombordan chiqish
     */
    @FXML
    private void btnCloseAction() {
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
     * */
}
