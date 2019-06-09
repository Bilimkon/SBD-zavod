package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import sample.Main;
import sample.dao.ProductDao;
import sample.dao.SystemUtilsDao;
import sample.dao.database;
import sample.model.Product;
import sample.model.User;
import sample.utils.BarCodeService;
import sample.utils.Barcode_pdf;
import sample.utils.Workbookcontroller;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import static sample.utils.BarCodeService.numbGen;

public class main implements Initializable {


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
    private TextField textWeight;
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
    private TextField textHeight;
    @FXML
    private TextField textWidth;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeProductTab();
        setOzgaartirishMaxsulot();
        ComboBoxUnit.getItems().addAll("Dona", "Kg", "Litr", "Rulon");
        User u = Login.currentUser;
        textFirstName.setText(u.getFirstName());
        textLastName.setText(u.getLastname());

        try {
            AddTypeComboboxAction();
            AddSuplierComboboxAction();
            productTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeProductTab() {
        TableColumn<Product, String> id = new TableColumn<>("Id");
        TableColumn<Product, String> barcode = new TableColumn<>("Barcode");
        TableColumn<Product, String> name = new TableColumn<>("Nomi");
        TableColumn<Product, String> type = new TableColumn<>("Turi");
        TableColumn<Product, String> cost = new TableColumn<>("Narxi");
        TableColumn<Product, String> quantity = new TableColumn<>("Miqdori");
        TableColumn<Product, String> weight = new TableColumn<>("Og'irligi");
        TableColumn<Product, String> user = new TableColumn<>("Ishchi");
        TableColumn<Product, String> date = new TableColumn<>("Sana");
        TableColumn<Product, String> suplier = new TableColumn<>("Taminotchi");
        TableColumn<Product, String> unit = new TableColumn<>("Birlik");
        TableColumn<Product, String> description = new TableColumn<>("Ma'lumot");
        TableColumn<Product, String> width = new TableColumn<>("Eni");
        TableColumn<Product, String> height = new TableColumn<>("Bo'yi");
        TableColumn<Product, String> color = new TableColumn<>("Rangi");
        AdminTable.getColumns().addAll(id, unit, barcode, name, type, weight, cost, quantity, suplier, date, user, description, width, height, color);
        id.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
        barcode.setCellValueFactory(new PropertyValueFactory<Product, String>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        type.setCellValueFactory(new PropertyValueFactory<Product, String>("type"));
        cost.setCellValueFactory(new PropertyValueFactory<Product, String>("cost"));
        quantity.setCellValueFactory(new PropertyValueFactory<Product, String>("quantity"));
        weight.setCellValueFactory(new PropertyValueFactory<Product, String>("weight"));
        user.setCellValueFactory(new PropertyValueFactory<Product, String>("cr_by"));
        date.setCellValueFactory(new PropertyValueFactory<Product, String>("date_cr"));
        suplier.setCellValueFactory(new PropertyValueFactory<Product, String>("suplier_id"));
        unit.setCellValueFactory(new PropertyValueFactory<Product, String>("unit"));
        description.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        width.setCellValueFactory(new PropertyValueFactory<Product, String>("width"));
        height.setCellValueFactory(new PropertyValueFactory<Product, String>("height"));
        color.setCellValueFactory(new PropertyValueFactory<Product, String>("color"));

    }

    public main() {
        try {
            myConn = database.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void productTable() throws SQLException {
        productDao.initializeTable(AdminTable);
    }

    @FXML
    public void btnAddProductAction() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Maxsulot qo'shish");
        alert.setHeaderText(null);
        alert.setContentText("Yangi maxsulot qo'shasizmi?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent())
            if (result.get() == ButtonType.OK) {
                try {
                    String type = ComboTypeList.getSelectionModel().getSelectedItem();
                    String Suplier = comboBoxSuplier.getSelectionModel().getSelectedItem();
                    String barcode = textBarcode.getText();
                    String name = textName.getText();
                    String unit = ComboBoxUnit.getSelectionModel().getSelectedItem();
                    String quantity = textQuantity.getText();
                    String cost = textCost.getText();
                    String weight = textWeight.getText();
                    String color = textColor.getText();
                    String height = textHeight.getText();
                    String width = textWidth.getText();
                    String description = textDescription.getText();

                    productDao.addProduct(barcode, name, type, cost, quantity, unit, weight, description, Suplier, color, height, width);
                    textBarcode.setText("");
                    textName.setText("");
                    textQuantity.setText("");
                    textColor.setText("");
                    textCost.setText("");
                    textWidth.setText("");
                    textWeight.setText("");
                    textHeight.setText("");
                    textDescription.setText("");
                    productTable();
                } catch (Exception exc) {
                    exc.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Hamma ma'lumotlarni kiritish shart!");
                }
            }
    }


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
                        textColor.setText(product.getColor());
                        textHeight.setText(product.getHeight());
                        textWidth.setText(product.getWidth());
                        textWeight.setText(product.getWeight());
                        textDescription.setText(product.getDescription());
                        textId.setText(String.valueOf(product.getId()));
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
                    String name = textName.getText();
                    String quantity = textQuantity.getText();
                    String cost = textCost.getText();
                    String color = textColor.getText();
                    String height = textHeight.getText();
                    String weight = textWeight.getText();
                    String width = textWidth.getText();
                    String description = textDescription.getText();
                    productDao.updateProduct(id, barcode, name, quantity, cost, color, height, weight, width, description);
                    JOptionPane.showMessageDialog(null, "Maxsulot malumotlari o'zgartirildi!");
                    productTable();
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
    }

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


    @FXML
    private void AddTypeComboboxAction() throws SQLException {
       addTypeComboboxAction1();
    }

    public void addTypeComboboxAction1(){
        try{
            productDao.addTypeCombobox(ComboTypeList);
            String name = productDao.getUnitType(ComboTypeList.getValue());
            ComboBoxUnit.setValue(name);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void AddSuplierComboboxAction() throws SQLException {
        productDao.addSuplierCombobox(comboBoxSuplier);
    }
    @FXML
    private void AddSuplierAction(){
        try {
            AddSuplierComboboxAction();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void AddTypeAction() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../components/views/AddType.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Tur qo'shish");
            stage.setScene(new Scene(root, 700, 400));
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
            root = FXMLLoader.load(getClass().getResource("../components/views/Suplier.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Taminotchi qo'shish");
            stage.setScene(new Scene(root, 700, 400));
            stage.setResizable(false);
            stage.isAlwaysOnTop();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            workbookcontroller.datebaseToExcel("product_v");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnCloseAction() {
        closeAction(btnClose);
    }

    public void closeAction(Button btn) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Chiqish");
        alert.setHeaderText(null);
        alert.setContentText(" Dasturdan chiqmoqchimisiz ?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent())
            if (result.get() == ButtonType.OK) {

                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
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


