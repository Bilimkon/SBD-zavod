package sample.utils;

import com.gembox.spreadsheet.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.shaded.apache.poi.hssf.usermodel.HSSFSheet;
import org.shaded.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.shaded.apache.poi.ss.usermodel.Cell;
import org.shaded.apache.poi.ss.usermodel.Row;
import org.shaded.apache.poi.ss.usermodel.Sheet;
import org.shaded.apache.poi.ss.usermodel.Workbook;
import sample.dao.SystemUtilsDao;
import sample.dao.database;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class Workbookcontroller {
    Connection myConn = null;

    public Workbookcontroller(){
        try {
            myConn = database.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    static {
        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
    }

    public void datebaseToExcel(String tableName) throws SQLException {


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Excel fayl yaratish ");
        alert.setHeaderText(null);
        alert.setContentText("Ombordagi maxsulotlarni excell faylga ko'chirasizmi?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent())
            if (result.get() == ButtonType.OK) {
                SystemUtilsDao systemUtilsDao = new SystemUtilsDao();
                String filename = systemUtilsDao.ExcelFilePath() + "OmborXisobi.xls";
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("Ombor Xisobi");
                FileOutputStream fileOut1 = null;
                try {
                    fileOut1 = new FileOutputStream(filename);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    workbook.write(fileOut1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (fileOut1 != null) {
                        fileOut1.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Workbook writeWorkbook = new HSSFWorkbook();
                Sheet desSheet = writeWorkbook.createSheet("new sheet");
                Statement stmt = null;
                ResultSet rs = null;
                try {
                    String query = "SELECT * FROM "+tableName;
                    stmt = myConn.createStatement();
                    rs = stmt.executeQuery(query);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnsNumber = rsmd.getColumnCount();
                    Row desRow1 = desSheet.createRow(0);
                    for (int col = 0; col < columnsNumber; col++) {
                        Cell newpath = desRow1.createCell(col);
                        newpath.setCellValue(rsmd.getColumnLabel(col + 1));
                    }
                    while (rs.next()) {
                        Row desRow = desSheet.createRow(rs.getRow());
                        for (int col = 0; col < columnsNumber; col++) {
                            Cell newpath = desRow.createCell(col);
                            newpath.setCellValue(rs.getString(col + 1));
                        }
                        FileOutputStream fileOut = new FileOutputStream(systemUtilsDao.ExcelFilePath() + "Ombor.xls");
                        writeWorkbook.write(fileOut);
                        fileOut.close();
                    }
                    JOptionPane.showMessageDialog(null, "Excel file yaratildi !");
                } catch (SQLException e) {
                    System.out.println("Failed to get data from database");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                }
            }
    }
}
