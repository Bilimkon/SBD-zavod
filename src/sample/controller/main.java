package sample.controller;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.jcp.xml.dsig.internal.dom.Utils;
import sample.dao.DaoUtils;
import sample.dao.database;
import sample.model.Product;
import sample.utils.BarCodeService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import static sample.utils.BarCodeService.numbGen;

public class main implements Initializable {


    private Connection myConn;


        // String name12  =  LoginController.currentUser.getFirstName();
        @FXML
        private TextField AdminTextSearch;
        @FXML
        private TableView<Product> AdminTable;
        //O'zgarmalar Maydoni textFiledlari
        @FXML
        private TextField AdminTextBarcode;
        @FXML
        private TextField AdminTextNomi;
        @FXML
        private TextField AdminTextId;
        @FXML
        private ComboBox<String> ComboTypeList;
        @FXML
        private ComboBox<String> ComboBoxUnit;
        @FXML
        private TextField AdminTextMiqdori;
        @FXML
        private TextField AdminTextNarxi;
        @FXML
        private TextField AdminTextSale;
        @FXML
        private DatePicker AdminTextDan;
        @FXML
        private DatePicker AdminTextGacha;
        @FXML
        private ComboBox<String> comboBoxSuplier;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            initializeProductTab();
            setOzgaartirishMaxsulot();
            ComboBoxUnit.getItems().addAll("Dona", "Kg");
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
            AdminTable.getColumns().addAll(id, unit, barcode, name, type, weight, cost, quantity, suplier,  date ,user, description,width, height, color);
            id.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
            barcode.setCellValueFactory( new PropertyValueFactory<Product ,String>("barcode"));
            name.setCellValueFactory( new PropertyValueFactory<Product ,String>("name"));
            type.setCellValueFactory( new PropertyValueFactory<Product ,String>("type"));
            cost.setCellValueFactory( new PropertyValueFactory<Product ,String>("cost"));
            quantity.setCellValueFactory( new PropertyValueFactory<Product ,String>("quantity"));
            weight.setCellValueFactory( new PropertyValueFactory<Product ,String>("weight"));
            user.setCellValueFactory( new PropertyValueFactory<Product ,String>("cr_by"));
            date.setCellValueFactory( new PropertyValueFactory<Product ,String>("date_cr"));
            suplier.setCellValueFactory( new PropertyValueFactory<Product ,String>("suplier_id"));
            unit.setCellValueFactory( new PropertyValueFactory<Product ,String>("unit"));
            description.setCellValueFactory( new PropertyValueFactory<Product ,String>("description"));
            width.setCellValueFactory(new PropertyValueFactory<Product, String>("width"));
            height.setCellValueFactory(new PropertyValueFactory<Product, String>("height"));
            color.setCellValueFactory(new PropertyValueFactory<Product, String>("color"));

        }

        public main()  {
            try {
                myConn = database.getConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        private void productTable() throws SQLException {
            Statement statement = null;
            ResultSet resultSet = null;
            ObservableList<Product> products = FXCollections.observableArrayList();
            try {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM product_v ORDER BY id");
                while (resultSet.next()) {

                    Product product = new Product();
                    product.setId(resultSet.getString("id"));
                    product.setUnit(resultSet.getString("unit"));
                    product.setBarcode(resultSet.getString("barcode"));
                    product.setName(resultSet.getString("name"));
                    product.setType(resultSet.getString("type"));
                    product.setCost(resultSet.getString("cost"));
                    product.setQuantity(resultSet.getString("quantity"));
                    product.setWeight(resultSet.getString("weight"));
                    product.setSuplier(resultSet.getString("suplier"));
                    product.setDate_cr(resultSet.getString("date_cr"));
                    product.setCr_by(resultSet.getString("user"));
                    product.setDescription(resultSet.getString("description"));
                    product.setWidth(resultSet.getString("width"));
                    product.setHeight(resultSet.getString("height"));
                    product.setColor(resultSet.getString("color"));

                    products.addAll(product);
                }
                AdminTable.setItems(products);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                   DaoUtils.close(statement,resultSet);
            }
        }

        @FXML
        public void maxsulotQoshish() {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("O'zgartirish");
            alert.setHeaderText(null);
            alert.setContentText("Yangi maxsulot qo'shasizmi?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent())
                if (result.get() == ButtonType.OK) {
                    try {
                        String type = ComboTypeList.getSelectionModel().getSelectedItem();
                        String Suplier = comboBoxSuplier.getSelectionModel().getSelectedItem();
                        String barcode1 = AdminTextBarcode.getText();
                        String nomi1 = AdminTextNomi.getText();
                        String unit = ComboBoxUnit.getSelectionModel().getSelectedItem();
                        String Miqdori1 = AdminTextMiqdori.getText();
                        String narxi1 = AdminTextNarxi.getText();
                        String saleNarxi = AdminTextSale.getText();
                        String dan1 = AdminTextDan.getValue().toString();
                        String gacha1 = AdminTextGacha.getValue().toString();
                       // productDao.addProduct(barcode1, nomi1, type, saleNarxi, narxi1, unit, Miqdori1, dan1, gacha1, Suplier);
                        AdminTextBarcode.setText("");
                        AdminTextNomi.setText("");
                        AdminTextMiqdori.setText("");
                        AdminTextNarxi.setText("");
                        AdminTextSale.setText("");
                        productTable();
                    } catch (Exception exc) {
                        exc.printStackTrace();
                        JOptionPane.showMessageDialog(null,"Barcodega 13 honali son kiritilishi shart!");

                    }
                }
        }


        private void setOzgaartirishMaxsulot() {
            try {
                AdminTable.setOnMouseClicked(event -> {
                    Product product = AdminTable.getItems().get(AdminTable.getSelectionModel().getSelectedIndex());
                    try {
                        AdminTextBarcode.setText(product.getBarcode());
                        AdminTextNomi.setText(product.getName());
                        AdminTextNarxi.setText(product.getCost());
                        AdminTextMiqdori.setText(product.getQuantity());
                        AdminTextId.setText(String.valueOf(product.getId()));
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                });
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }


        /**
         * Product update
         */
        public void btnOzgartirishAction() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("O'zgartirish");
            alert.setHeaderText(null);
            alert.setContentText("Maxsulot malumotlarini o'zgartirasizmi ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent())
                if (result.get() == ButtonType.OK) {
                    try {
                        String id = AdminTextId.getText();
                        String barcode1 = AdminTextBarcode.getText();
                        String nomi1 = AdminTextNomi.getText();
                        String Miqdori1 = AdminTextMiqdori.getText();
                        String narxi1 = AdminTextNarxi.getText();
                        String saleNarxi1 = AdminTextSale.getText();
                        String dan1 = AdminTextDan.getValue().toString();
                        String gacha1 = AdminTextDan.getValue().toString();
                      //  productDao.updateProduct(barcode1, nomi1, saleNarxi1, narxi1, Miqdori1, dan1, gacha1, id);
                        JOptionPane.showMessageDialog(null,"Maxsulot malumotlari o'zgartirildi!");
                        productTable();
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                }
        }

        public void btnOchirishAction() throws Exception {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("O'zgartirish");
            alert.setHeaderText(null);
            alert.setContentText("Maxsulotni rostdan ham o'chirasizmi ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent())
                if (result.get() == ButtonType.OK) {
                    String id = AdminTextId.getText();
                    try {
                        //productDao.deleteProduct(id);
                        JOptionPane.showMessageDialog(null,"Maxsulot o'chirildi!");
                        productTable();
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                }
        }


        @FXML
        private void AddTypeComboboxAction() throws SQLException {
            Statement statement = null;
            ResultSet resultSet = null;
            try {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT Name FROM type");
                while (resultSet.next()) {  // loop
                    // Now add the comboBox addAll statement
                    ComboTypeList.getItems().addAll(resultSet.getString("name"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DaoUtils.close(statement, resultSet);
            }
        }

        private void AddSuplierComboboxAction() throws SQLException {
            Statement statement = null;
            ResultSet resultSet = null;
            try {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT firstName FROM suplier");
                while (resultSet.next()) {  // loop
                    // Now add the comboBox addAll statement
                    comboBoxSuplier.getItems().addAll(resultSet.getString("firstname"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DaoUtils.close(statement, resultSet);
            }
        }

        @FXML
        private void AddTypeAction() {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("Components/AddType.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Tur qo'shish");
                stage.setScene(new Scene(root, 600, 400));
                stage.setResizable(false);
                stage.isAlwaysOnTop();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        @FXML
        private void GenerateBarcode() {
            if (AdminTextBarcode.getText().isEmpty()) {
                String testCode = String.valueOf(numbGen());
                BarCodeService serv = new BarCodeService();
                String parsedString = serv.parseInput(testCode);
                AdminTextBarcode.setText(parsedString);
                String barCodeString = serv.generateCode(parsedString);
            } else {
                String barcode = AdminTextBarcode.getText();
                int lenght = barcode.length();
                if (lenght == 13) {
                    BarCodeService serv = new BarCodeService();
                    String parsedString = serv.parseInput(barcode);
                    AdminTextBarcode.setText(parsedString);
                    String barCodeString = serv.generateCode(parsedString);
                } else {
                    JOptionPane.showMessageDialog(null,"Barcodega 13 honali son kiritilishi shart!");
                }
            }
        }
    }


