package sample.components.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import sample.components.models.omborReport;
import sample.dao.DaoUtils;
import sample.dao.database;
import sample.model.Product;
import sample.utils.Workbookcontroller;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class invoicelogDao {
    Connection myConn;
    Workbookcontroller workbookcontroller = new Workbookcontroller();

    public invoicelogDao(){
        try {
            myConn = database.getConnection();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void ReportTableDao(TableView tableView,String company, String name, String dan, String gacha, Label quantity, Label totalCost) throws SQLException {


        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pr = null;
        ResultSet myRs = null;

        //List to add items
        ObservableList<Product> products = FXCollections.observableArrayList();

        if (!company.isEmpty() && !name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product_h_v where suplier='"+company+"' and type='" + name + "' and substr(date_cr,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");

            pr = myConn.prepareStatement("SELECT sum(quantity) as outt, sum(cost*quantity) totalCost FROM product_h_v where suplier='"+company+"' and type='" + name + "' and substr(date_cr,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");
            myRs = pr.executeQuery();
        } else if (company.isEmpty() && name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product_h_v where substr(date_cr,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");

            pr = myConn.prepareStatement("SELECT sum(quantity) as outt, sum(cost*quantity) totalCost FROM product_h_v where substr(date_cr,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");
            myRs = pr.executeQuery();
        } else if (company.isEmpty() && !name.isEmpty() && dan.isEmpty() && gacha.isEmpty()) {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product_h_v where type='" + name + "'");

            pr = myConn.prepareStatement("SELECT sum(quantity) as outt, sum(cost*quantity) totalCost FROM product_h_v where type='" + name + "'");
            myRs = pr.executeQuery();
        } else if (!company.isEmpty() && name.isEmpty() && dan.isEmpty() && gacha.isEmpty()) {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product_h_v where suplier='" + company + "'");

            pr = myConn.prepareStatement("SELECT sum(quantity) as outt, sum(cost*quantity) totalCost FROM product_h_v where suplier='" + company + "'");
            myRs = pr.executeQuery();
        }
        else if (company.equals("1") && name.equals("1") || dan.equals("1") || gacha.equals("1")) {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product_h_v ORDER BY id desc limit 500");

            pr = myConn.prepareStatement("SELECT sum(quantity) as outt, sum(cost*quantity) totalCost FROM product_h_v");
            myRs = pr.executeQuery();
        }
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);

        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getString("id"));
                    product.setOperType(resultSet.getString("oper_type"));
                    product.setInvoice(resultSet.getString("invoice"));
                    product.setUnit(resultSet.getString("unit"));
                    product.setBarcode(resultSet.getString("barcode"));
                    product.setName(resultSet.getString("name"));
                    product.setType(resultSet.getString("type"));
                    product.setCost(formatter.format(resultSet.getDouble("cost")));
                    product.setQuantity(formatter.format(resultSet.getDouble("quantity")));
                    product.setSuplier(resultSet.getString("suplier"));
                    product.setDate_cr(resultSet.getString("date_cr"));
                    product.setCr_by(resultSet.getString("user"));
                    products.addAll(product);
                }
            }

            if (myRs != null && myRs.next()) {
                quantity.setText(formatter.format(myRs.getDouble("outt")));
                totalCost.setText(formatter.format(myRs.getDouble("totalCost")));
            }

            tableView.setItems(products);


        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            try {
                sample.dao.DaoUtils.close(statement, resultSet);
                if (pr != null) {
                    pr.close();
                }
                if (myRs != null) {
                    myRs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void ReportSelectName(ComboBox<String> comboBox) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT name from type order by name ");
            while (resultSet.next()) {  // loop
                // Now add the comboBox addAll statement
                comboBox.getItems().addAll(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sample.dao.DaoUtils.close(statement, resultSet);
        }
    }

    public void  getCompanyNameDao(ComboBox<String> comboBox) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT companyName from person where type=1 order by companyName ");
            while (resultSet.next()) {  // loop
                // Now add the comboBox addAll statement
                comboBox.getItems().addAll(resultSet.getString("companyName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sample.dao.DaoUtils.close(statement, resultSet);
        }
    }

    public void ReportExcellTableDao(Button button, String company, String name, String dan, String gacha) throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pr = null;
        ResultSet myRs = null;

        //List to add items
        ObservableList<Product> products = FXCollections.observableArrayList();

        if (!company.isEmpty() && !name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product_h_v where suplier='"+company+"' and type='" + name + "' and substr(date_cr,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");

        } else if (company.isEmpty() && name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()) {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product_h_v where substr(date_cr,7,10) BETWEEN '" + dan + "' AND '" + gacha + "' ");

        } else if (company.isEmpty() && !name.isEmpty() && dan.isEmpty() && gacha.isEmpty()) {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product_h_v where type='" + name + "'");

        } else if (!company.isEmpty() && name.isEmpty() && dan.isEmpty() && gacha.isEmpty()) {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product_h_v where suplier='" + company + "'");
        }
        else if (company.equals("1") && name.equals("1") || dan.equals("1") || gacha.equals("1")) {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM product_h_v ORDER BY id desc limit 500");
        }
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);

        try {
            if (resultSet != null) {

                workbookcontroller.datebaseToExcelResultset( "product_h_v", "Ombor_kirimi.xls", resultSet);
            }



        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            try {
                sample.dao.DaoUtils.close(statement, resultSet);
                if (pr != null) {
                    pr.close();
                }
                if (myRs != null) {
                    myRs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public void invertoryReport(TableView tableView, String name, String dan, String gacha, Label dollar, Label sum, Label hr) {
        try {
            Statement statement = null;
            ResultSet resultSet = null;
            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
            symbols.setGroupingSeparator(' ');
            DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);

            PreparedStatement pr = null;
            ResultSet myRs = null;
            //List to add items
            ObservableList<omborReport> omborReports = FXCollections.observableArrayList();
            try {
                if(!name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty())
                {
                    statement = myConn.createStatement();
                    resultSet = statement.executeQuery("SELECT * FROM reportOmbor where company='"+name+"' and cr_on BETWEEN '"+dan+"' AND '"+gacha+"' ");

                    pr = myConn.prepareStatement("SELECT sum(dollar) as total_dollar, sum(sum) as total_sum, sum(hr) as total_hr FROM reportOmbor where company='"+name+"' and cr_on BETWEEN '"+dan+"' AND '"+gacha+"' ");
                    myRs = pr.executeQuery();



                } else if(name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()){
                    statement = myConn.createStatement();
                    resultSet = statement.executeQuery("SELECT * FROM reportOmbor where cr_on BETWEEN '"+dan+"' AND '"+gacha+"' ");

                    pr = myConn.prepareStatement("SELECT sum(dollar) as total_dollar, sum(sum) as total_sum, sum(hr) as total_hr FROM reportOmbor where cr_on BETWEEN '"+dan+"' AND '"+gacha+"' ");
                    myRs = pr.executeQuery();

                }
                else if(!name.isEmpty() && dan.isEmpty() && gacha.isEmpty())
                {
                    statement = myConn.createStatement();
                    resultSet = statement.executeQuery("SELECT * FROM reportOmbor where company='"+name+"' order by id desc limit 300");

                    pr = myConn.prepareStatement("SELECT sum(dollar) as total_dollar, sum(sum) as total_sum, sum(hr) as total_hr FROM reportOmbor where company='"+name+"' order by id desc limit 300");
                    myRs = pr.executeQuery();

                }
                else if(name.equals("1") || dan.equals("1") || gacha.equals("1")) {
                    statement = myConn.createStatement();
                    resultSet = statement.executeQuery("SELECT * FROM reportOmbor ORDER BY id desc limit 300");

                    pr = myConn.prepareStatement("SELECT sum(dollar) as total_dollar, sum(sum) as total_sum, sum(hr) as total_hr FROM reportOmbor ORDER BY id desc limit 300");
                    myRs = pr.executeQuery();

                }
                if (resultSet != null) {
                    while (resultSet.next()) {
                        omborReport invoice = new omborReport();
                        invoice.setId(resultSet.getString("id"));
                        invoice.setType(resultSet.getString("type"));
                        invoice.setInvoiceName(resultSet.getString("invoiceName"));
                        invoice.setCompany(resultSet.getString("company"));
                        invoice.setAccount(resultSet.getString("account"));
                        invoice.setContract(resultSet.getString("contract"));
                        invoice.setDollar(formatter.format(resultSet.getDouble("dollar")));
                        invoice.setSum(formatter.format(resultSet.getDouble("sum")));
                        invoice.setHr(formatter.format(resultSet.getDouble("hr")));
                        invoice.setMaxsulot(resultSet.getString("maxsulot"));
                        invoice.setIzox(resultSet.getString("izoh"));
                        invoice.setCr_on(resultSet.getString("cr_on"));
                        invoice.setCr_by(formatter.format(resultSet.getDouble("cr_by")));
                        invoice.setI_id(resultSet.getString("i_id"));

                        omborReports.add(invoice);
                    }
                }

                if (myRs != null && myRs.next()) {
                    dollar.setText(formatter.format(myRs.getDouble("total_dollar")));
                    sum.setText(formatter.format(myRs.getDouble("total_sum")));
                    hr.setText(formatter.format(myRs.getDouble("total_hr")));

                }

                tableView.setItems(omborReports);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DaoUtils.close(statement, resultSet);
                if (myRs != null) {
                    myRs.close();
                }
                if (pr != null) {
                    pr.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void invertoryExcellReport(Button button, String name, String dan, String gacha) {
        try {
            Statement statement = null;
            ResultSet resultSet = null;

            //List to add items
            ObservableList<omborReport> omborReports = FXCollections.observableArrayList();
            try {
                if(!name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty())
                {
                    statement = myConn.createStatement();
                    resultSet = statement.executeQuery("SELECT * FROM reportOmbor where company='"+name+"' and cr_on BETWEEN '"+dan+"' AND '"+gacha+"' ");



                } else if(name.isEmpty() && !dan.isEmpty() && !gacha.isEmpty()){
                    statement = myConn.createStatement();
                    resultSet = statement.executeQuery("SELECT * FROM reportOmbor where cr_on BETWEEN '"+dan+"' AND '"+gacha+"' ");


                }
                else if(!name.isEmpty() && dan.isEmpty() && gacha.isEmpty())
                {
                    statement = myConn.createStatement();
                    resultSet = statement.executeQuery("SELECT * FROM reportOmbor where company='"+name+"' order by id desc limit 300");


                }
                else if(name.equals("1") || dan.equals("1") || gacha.equals("1")) {
                    statement = myConn.createStatement();
                    resultSet = statement.executeQuery("SELECT * FROM reportOmbor ORDER BY id desc limit 300");


                }
                if (resultSet != null) {
                  workbookcontroller.datebaseToExcelResultset("", "Ombor_hisoboti.xls", resultSet);
                }


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DaoUtils.close(statement, resultSet);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
