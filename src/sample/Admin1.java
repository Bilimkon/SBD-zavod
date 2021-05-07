package sample;

import com.jfoenix.controls.JFXDatePicker;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sample.components.sell.DAO.UtilsDao;
import sample.dao.AdminDao;
import sample.dao.DaoUtils;
import sample.dao.database;
import sample.model.UserTable;
import sample.utils.Workbookcontroller;
import sample.utils.utils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;

public class Admin1 implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField username;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField phone;
    @FXML
    private TextField password;
    @FXML
    private Button btnClose;
    @FXML
    private ComboBox Role;
    @FXML
    private TableView userTable;
    @FXML
    private Label LabelSumTotal;
    @FXML
    private Label LabelDollarTotal;
    @FXML
    private Label LabelAccountTotal;
    @FXML
    private Label LabelVHR_Total;
    @FXML
    private ComboBox<String> ExchangeCombobox;
    @FXML
    private TextField ExchangeSumma;
    @FXML
    private TextField DollarRate;

    // tab admin
    @FXML
    private JFXDatePicker danAdmin;
    @FXML
    private JFXDatePicker gachaAdmin;
    @FXML
    private TableView tableAdminLog;

    // tab admin
    @FXML
    private JFXDatePicker danAdmin1;
    @FXML
    private JFXDatePicker gachaAdmin1;
    // chart
    @FXML
    private JFXDatePicker danChart;
    @FXML
    private JFXDatePicker gachaChart;
    @FXML
    private AreaChart chartAdminSale;
    @FXML
    private TableView table_log;
    @FXML Button btnExcell;
    @FXML Label textExchange;

    String user_id = String.valueOf(Login.currentUser.getId());
    Workbookcontroller workbookcontroller = new Workbookcontroller();

    AdminDao adminDao = new AdminDao();
    DaoUtils daoUtils = new DaoUtils();
    sample.utils.utils utils = new utils();

    private Connection myConn = null;

    public Admin1() {
        try {
            myConn = database.getConnection();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Xatolik" + exc, "Xatolik", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Role.getItems().addAll("Ombor", "1-ish/ch", "2-ish/ch", "Savdo", "Admin1", "Accounting");
        ExchangeCombobox.getItems().addAll("Sum-Dollar", "HR-Dollar", "Dollar-Sum", "Sum-Hr", "Hr-Sum");
        initializeTable();
        intitializeAdminlogTable();
        userTable();
        chart();
        AdminLogTable();
        setUpdate();
        InitializeLogTable();
        total_Sum();
        AdminLog();
        utils.thousandDivider(ExchangeSumma);
        utils.thousandDivider(DollarRate);

        /**
         *   Timer()
         */
        Timeline ficeSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                userTable();
                AdminLogTable();
                total_Sum();
                AdminLog();
            }
        }));
        ficeSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        ficeSecondsWonder.play();

        /**
         *  End of timer()
         *
         */
    }

    private void initializeTable() {
        TableColumn id = new TableColumn("Tartib raqami");
        TableColumn username = new TableColumn("Username");
        TableColumn firstname = new TableColumn("Ism");
        TableColumn lastname = new TableColumn("Familiya");
        TableColumn password = new TableColumn("Password");
        TableColumn userType = new TableColumn("userType");
        TableColumn phone = new TableColumn("Telefon");
        userTable.getColumns().addAll(id, username, firstname, lastname, phone, password, userType);

        id.setCellValueFactory(new PropertyValueFactory<UserTable, String>("id"));
        username.setCellValueFactory(new PropertyValueFactory<UserTable, String>("username"));
        firstname.setCellValueFactory(new PropertyValueFactory<UserTable, String>("firstname"));
        lastname.setCellValueFactory(new PropertyValueFactory<UserTable, String>("lastname"));
        phone.setCellValueFactory(new PropertyValueFactory<UserTable, String>("phone"));
        userType.setCellValueFactory(new PropertyValueFactory<UserTable, String>("userType"));
        password.setCellValueFactory(new PropertyValueFactory<UserTable, String>("password"));
    }



    // initializing table AdminLog
    private void intitializeAdminlogTable() {
        TableColumn id = new TableColumn("Id");
        TableColumn module = new TableColumn("Modul");
        TableColumn type = new TableColumn("Turi");
        TableColumn ksum = new TableColumn("Kirim so'm");
        TableColumn kdollar = new TableColumn("Kirim dollar");
        TableColumn khr = new TableColumn("Kirim hr");
        TableColumn csum = new TableColumn("Chiqim so'm");
        TableColumn cdollar = new TableColumn("Chiqim dollar");
        TableColumn chr = new TableColumn("Chiqim hr");
        TableColumn cr_by = new TableColumn("Ishchi");
        TableColumn date = new TableColumn("Sana");
        TableColumn comment = new TableColumn("Ma'lumot");
        tableAdminLog.getColumns().addAll(date, module, type, ksum, kdollar, khr, csum, cdollar, chr, cr_by);

        id.setCellValueFactory(new PropertyValueFactory<sample.model.AdminLogTable, String>("id"));
        module.setCellValueFactory(new PropertyValueFactory<sample.model.AdminLogTable, String>("module"));
        type.setCellValueFactory(new PropertyValueFactory<sample.model.AdminLogTable, String>("type"));
        ksum.setCellValueFactory(new PropertyValueFactory<sample.model.AdminLogTable, String>("ksum"));
        kdollar.setCellValueFactory(new PropertyValueFactory<sample.model.AdminLogTable, String>("kdollar"));
        khr.setCellValueFactory(new PropertyValueFactory<sample.model.AdminLogTable, String>("khr"));
        csum.setCellValueFactory(new PropertyValueFactory<sample.model.AdminLogTable, String>("csum"));
        cdollar.setCellValueFactory(new PropertyValueFactory<sample.model.AdminLogTable, String>("cdollar"));
        chr.setCellValueFactory(new PropertyValueFactory<sample.model.AdminLogTable, String>("chr"));
        cr_by.setCellValueFactory(new PropertyValueFactory<sample.model.AdminLogTable, String>("cr_by"));
        date.setCellValueFactory(new PropertyValueFactory<sample.model.AdminLogTable, String>("date"));
        comment.setCellValueFactory(new PropertyValueFactory<sample.model.AdminLogTable, String>("comment"));
    }

    private void InitializeLogTable() {
        TableColumn id = new TableColumn("Id");
        TableColumn module = new TableColumn("Modul");
        TableColumn type = new TableColumn("Turi");
        TableColumn cost = new TableColumn("Narxi");
        TableColumn cr_by = new TableColumn("Ishchi");
        TableColumn date = new TableColumn("Sana");
        TableColumn comment = new TableColumn("Ma'lumot");
        TableColumn summa = new TableColumn("Summa");

        table_log.getColumns().addAll(date, module, type, cr_by, comment, cost, summa);

        id.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("id"));
        module.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("module"));
        type.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("type"));
        cost.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("cost"));
        cr_by.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("cr_by"));
        date.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("date"));
        comment.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("comment"));
        summa.setCellValueFactory(new PropertyValueFactory<sample.model.LogTable, String>("summa"));
    }


    // This function updates all the controls
    private void functionUpdate() {
        AdminLogTable();
        userTable();
        AdminLogTable();
    }

    @FXML
    private void btnChartSaralashAction() {
        chart();
    }

    /**
     * Getting chart
     */
    private void chart() {

        try {
            if (danChart.getValue() != null && gachaChart.getValue() != null) {
                LocalDate date1 = danChart.getValue();
                LocalDate date2 = gachaChart.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                adminDao.chart(sdate1, sdate2, chartAdminSale);
            } else {
                adminDao.chart("1", "1", chartAdminSale);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void userTable() {
        try {
            adminDao.userTable(userTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void AdminLogTable() {
        try {
            if (danAdmin.getValue() != null && gachaAdmin.getValue() != null) {
                LocalDate date1 = danAdmin.getValue();
                LocalDate date2 = gachaAdmin.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                adminDao.adminLogTable(tableAdminLog, sdate1, sdate2);
            } else {
                adminDao.adminLogTable(tableAdminLog, "1", "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void AdminLog() {
        try {
            if (danAdmin1.getValue() != null && gachaAdmin1.getValue() != null) {
                LocalDate date1 = danAdmin1.getValue();
                LocalDate date2 = gachaAdmin1.getValue();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String sdate1 = df.format(Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                String sdate2 = df.format(Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                adminDao.adminLog(table_log, sdate1, sdate2);
            } else {
                adminDao.adminLog(table_log, "1", "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void btnSaveAction() {

        try {
            String username1 = username.getText();
            String firstname1 = firstname.getText();
            String lastname1 = lastname.getText();
            String password1 = password.getText();
            String phone1 = phone.getText();
            String userType = Role.getValue().toString();
            adminDao.addUser(username1, firstname1, lastname1, phone1, password1, userType);
            userTable();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Hamma ma'lumotlarni kiritishingiz shart !");
        }

    }

    private void setUpdate() {
        try {
            userTable.setOnMouseClicked(event -> {
                UserTable userTable1 = (UserTable) userTable.getSelectionModel().getSelectedItem();
                try {
                    username.setText(userTable1.getUsername());
                    firstname.setText(userTable1.getFirstname());
                    lastname.setText(userTable1.getLastname());
                    password.setText(userTable1.getPassword());
                    Role.setValue(userTable1.getUserType());
                    phone.setText(userTable1.getPhone());
                    id.setText(userTable1.getId());
                } catch (Exception exc) {
                }
            });
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Error" + exc, "Xatolik", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void btnUpdateAction() {
        try {
            String username1 = username.getText();
            String firstname1 = firstname.getText();
            String lastname1 = lastname.getText();
            String password1 = password.getText();
            String phone1 = phone.getText();
            String userType = Role.getValue().toString();
            String id1 = id.getText();
            adminDao.updateUser(id1, username1, firstname1, lastname1, phone1, password1, userType);
            userTable();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error", "Xatolik", JOptionPane.ERROR_MESSAGE);

        }
    }

    @FXML
    private void btnDeleteAction() {
        try {
            String id1 = id.getText();
            adminDao.deleteUser(id1);
            userTable();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error", "Xatolik", JOptionPane.ERROR_MESSAGE);
        }
    }

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

    @FXML
    private void btnExcellFilter() {
        try {
            Workbookcontroller workbookcontroller = new Workbookcontroller();
            workbookcontroller.datebaseToExcel("Marketing_v", "Marketing.xls");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void total_Sum() {
        adminDao.total_balance(LabelSumTotal, LabelDollarTotal, LabelAccountTotal, LabelVHR_Total);
    }

    public void btnExchangeAction() throws SQLException {

        PreparedStatement pr = null;
        PreparedStatement pr1 = null;
        try {
            String oper = ExchangeCombobox.getSelectionModel().getSelectedItem();
            Double sum = Double.valueOf(ExchangeSumma.getText().trim().replaceAll("\\s+", ""));
            Double dollar = Double.valueOf(DollarRate.getText().trim().replaceAll("\\s+", ""));

            switch (oper) {
                case "Sum-Dollar": {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Dollardagi qiymati: " + sum / dollar);
                    alert.setHeaderText(null);
                    alert.setContentText("Valyuta almashtirasizmi ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent())
                        if (result.get() == ButtonType.OK) {

                            pr = myConn.prepareStatement("update total_balance set dollar=(dollar+?) where id =1");
                            pr.setString(1, String.valueOf((sum / dollar)));
                           int i = pr.executeUpdate();
                           if(i>0) {
                               pr1 = myConn.prepareStatement("update total_balance set sum=(sum-?) where id =1 ");
                               pr1.setString(1, String.valueOf(sum));
                               pr1.executeUpdate();
                               total_Sum();
                               daoUtils.log("Admin1", "Valyuta almashtirish", dollar+"", user_id, "Sum-dollar: " + sum +"/"+dollar, sum+"");
                           }

                        }
                    break;
                }
                case "HR-Dollar": {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Dollardagi qiymati: " + String.valueOf(sum / dollar));
                    alert.setHeaderText(null);
                    alert.setContentText("Valyuta almashtirasizmi ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent())
                        if (result.get() == ButtonType.OK) {

                            pr = myConn.prepareStatement("update total_balance set dollar=(dollar+?) where id =1 ");
                            pr.setDouble(1, (sum / dollar));
                          int i=  pr.executeUpdate();
                          if(i>0) {
                              pr1 = myConn.prepareStatement("update total_balance set hr=(hr-?) where id =1 ");
                              pr1.setString(1, String.valueOf(sum));
                              pr1.executeUpdate();
                              total_Sum();
                              daoUtils.log("Admin1", "Valyuta almashtirish", dollar+"", user_id, "HR-dollar: " + sum +"/"+ dollar, ""+sum);
                          }

                        }
                    break;
                }
                case "Dollar-Sum": {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("So'mdagi qiymati: " + String.valueOf(dollar * sum));
                    alert.setHeaderText(null);
                    alert.setContentText("Valyuta almashtirasizmi ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent())
                        if (result.get() == ButtonType.OK) {

                            pr = myConn.prepareStatement("update total_balance set sum=(sum+?) where id =1 ");
                            pr.setString(1, String.valueOf((dollar * sum)));
                         int i =    pr.executeUpdate();
                         if(i>0) {
                             pr1 = myConn.prepareStatement("update total_balance set dollar=(dollar-?) where id =1 ");
                             pr1.setString(1, String.valueOf(sum));
                             pr1.executeUpdate();
                             total_Sum();
                             daoUtils.log("Admin1", "Valyuta almashtirish", ""+dollar, user_id, "Dollar-Sum: " + dollar +"*"+ sum,""+sum);

                         }
                        }
                    break;
                }
                case "Sum-Hr": {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("So'mdagi qiymati: " + String.valueOf(dollar * sum));
                    alert.setHeaderText(null);
                    alert.setContentText("Valyuta almashtirasizmi ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent())
                        if (result.get() == ButtonType.OK) {

                            pr = myConn.prepareStatement("update total_balance set sum=(sum-?) where id =1 ");
                            pr.setString(1, String.valueOf(sum));
                          int i=  pr.executeUpdate();
                          if(i>0) {
                              pr1 = myConn.prepareStatement("update total_balance set hr=(hr+?) where id =1 ");
                              pr1.setString(1, String.valueOf(sum));
                              pr1.executeUpdate();
                              total_Sum();
                              daoUtils.log("Admin1", "Valyuta almashtirish", ""+sum, user_id, "Sum-Hr: " + sum +" : "+ sum, ""+sum);
                          }
                        }
                    break;
                }
                case "Hr-Sum": {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("So'mdagi qiymati: " + String.valueOf(dollar * sum));
                    alert.setHeaderText(null);
                    alert.setContentText("Valyuta almashtirasizmi ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent())
                        if (result.get() == ButtonType.OK) {

                            pr = myConn.prepareStatement("update total_balance set sum=(sum+?) where id =1 ");
                            pr.setString(1, String.valueOf(sum));
                        int i=    pr.executeUpdate();
                        if(i>0) {
                            pr1 = myConn.prepareStatement("update total_balance set hr=(hr-?) where id =1 ");
                            pr1.setString(1, String.valueOf(sum));
                            pr1.executeUpdate();
                            total_Sum();
                            daoUtils.log("Admin1", "Valyuta almashtirish", ""+sum, user_id, "Hr-Sum: " + sum +" : "+ sum, ""+sum);
                        }
                        }
                    break;
                }
            }
                ExchangeSumma.setText("");
                DollarRate.setText("");
                textExchange.setText("Valyuta ayrboshlandi");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                pr.close();
            }
            if (pr1 != null) {
                pr1.close();
            }
        }
    }

    @FXML
    private void btnAdminSaralashAction() {
        AdminLogTable();
    }

    @FXML private void btnAdminSaralashActionLog() {
        try {
            AdminLog();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    private void btnPrinterNameAction() {
        try {

            UtilsDao utilsDao = new UtilsDao();
            TextInputDialog dialog = new TextInputDialog("Printer");
            dialog.setTitle("Printer nomini qo'shish");
            dialog.setHeaderText("Printer nomi");
            dialog.setContentText("Iltimos printer nomini qo'shing");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> {
                try {
                    utilsDao.setPrinterName(name);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnExcellBalanceAction() {
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
                Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();

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
                String who = row.getCell(0).getStringCellValue();
                String sum_in = row.getCell(1).getStringCellValue();
                String sum_out = row.getCell(2).getStringCellValue();
                String dollar_in = row.getCell(3).getStringCellValue();
                String dollar_out = row.getCell(4).getStringCellValue();
                String hr_in = row.getCell(5).getStringCellValue();
                String hr_out = row.getCell(6).getStringCellValue();
                adminDao.InsertRowInDB(who, sum_in, sum_out, dollar_in, dollar_out, hr_in, hr_out);
            }
            file.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void AdminOperationAction() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/AdminOper.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Admin operatsiyalari");
            stage.setScene(new Scene(root, 1000, 700));
            stage.setResizable(false);
            stage.isAlwaysOnTop();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnExchangeActionHistory() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/Exchange.fxml"));
            Stage stage = new Stage();
            stage.setTitle("O'tkazmalar");
            stage.setScene(new Scene(root, 1000, 700));
            stage.setResizable(false);
            stage.isAlwaysOnTop();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnPaperActionHistory() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/p2Tarix.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Qog'oz sex ishlab chiqarish tarixi");
            stage.setScene(new Scene(root, 1000, 700));
            stage.setResizable(false);
            stage.isAlwaysOnTop();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnLaminantishChiqrish() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/P3Tarix.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Laminant sex ishlab chiqarish tarixi");
            stage.setScene(new Scene(root, 1000, 600));
            stage.setResizable(false);
            stage.isAlwaysOnTop();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnTotalBalance() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("view/balance.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Umumiy balance oynasi");
            stage.setScene(new Scene(root, 1000, 700));
            stage.setResizable(false);
            stage.isAlwaysOnTop();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
