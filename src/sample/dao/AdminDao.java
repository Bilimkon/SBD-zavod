package sample.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import sample.Login;
import sample.model.*;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class
AdminDao {
    private Connection myConn = null;
    DaoUtils daoUtils = new DaoUtils();
    User u = Login.currentUser;

    public AdminDao() {
        try {
            myConn = database.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void userTable(TableView tableView) {

        Statement statement = null;
        ResultSet resultSet = null;

        //List to add items
        ObservableList<UserTable> userTables = FXCollections.observableArrayList();
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user_v ORDER BY id");
            while (resultSet.next()) {
                UserTable userTable = new UserTable();
                userTable.setId(resultSet.getString("id"));
                userTable.setUsername(resultSet.getString("username"));
                userTable.setFirstname(resultSet.getString("firstname"));
                userTable.setLastname(resultSet.getString("lastname"));
                userTable.setPhone(resultSet.getString("phone"));
                userTable.setPassword(resultSet.getString("password"));
                userTable.setUserType(resultSet.getString("userType"));

                userTables.add(userTable);
            }
            tableView.setItems(userTables);

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            try {
                DaoUtils.close(statement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void marketingTable(TableView tableView, String color) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            ObservableList<Marketing> marketings = FXCollections.observableArrayList();
            if (!color.equals("1")) {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("Select * from marketing_v where color like '%" + color + "%'");
            } else {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM marketing_v ORDER BY id");
            }
            while (resultSet.next()) {
                Marketing marketing = new Marketing();
                marketing.setId(resultSet.getString("id"));
                marketing.setCompany(resultSet.getString("company"));
                marketing.setName(resultSet.getString("name"));
                marketing.setBarcode_o(resultSet.getString("barcode_o"));
                marketing.setBarcode(resultSet.getString("barcode"));
                marketing.setColor(resultSet.getString("color"));
                marketing.setCost(resultSet.getString("cost"));

                marketings.add(marketing);
            }
            tableView.setItems(marketings);

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            try {
                DaoUtils.close(statement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void adminLogTable(TableView tableView, String dan, String gacha) {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pr = null;
        try {
            ObservableList<AdminLogTable> marketings = FXCollections.observableArrayList();

            if (!dan.equals("1") && !gacha.equals("1")) {
                pr = myConn.prepareStatement("SELECT * FROM admin_log_table WHERE substr(date,7,10) BETWEEN ? AND ?");
                pr.setString(1, dan);
                pr.setString(2, gacha);
                resultSet = pr.executeQuery();
            } else {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM admin_log_table ORDER BY id limit 100");
            }

            while (resultSet.next()) {
                AdminLogTable marketing = new AdminLogTable();
                marketing.setId(resultSet.getString("id"));
                marketing.setModule(resultSet.getString("module"));
                marketing.setType(resultSet.getString("type"));
                marketing.setKsum(formatter.format(resultSet.getDouble("ksum")));
                marketing.setKdollar(formatter.format(resultSet.getDouble("kdollar")));
                marketing.setKhr(formatter.format(resultSet.getDouble("khr")));
                marketing.setCsum(formatter.format(resultSet.getDouble("csum")));
                marketing.setCdollar(formatter.format(resultSet.getDouble("cdollar")));
                marketing.setChr(formatter.format(resultSet.getDouble("chr")));
                marketing.setCr_by(resultSet.getString("cr_by"));
                marketing.setDate(resultSet.getString("date"));
                marketings.add(marketing);
            }
            tableView.setItems(marketings);

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            try {
                DaoUtils.close(statement, resultSet);
                if (pr != null) {
                    pr.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void addUser(String username, String firstname, String lastname, String phone, String password, String userType) {
        PreparedStatement pr = null;

        try {
            pr = myConn.prepareStatement("INSERT INTO user(username, firstname, lastname, password, userType, phone) VALUES (?,?,?,?,?,?)");
            pr.setString(1, username);
            pr.setString(2, firstname);
            pr.setString(3, lastname);
            pr.setString(4, password);
            pr.setString(5, unitMaker(userType));
            pr.setString(6, phone);
            pr.executeUpdate();
            daoUtils.log("Admin1", "Foydalanuvchi qo'sish", "", String.valueOf(u.getId()), "Yangi foydalanuvchi qo'shildi" + username + " ," + firstname);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                try {
                    pr.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateUser(String id, String username, String firstname, String lastname, String phone, String password, String userType) {
        PreparedStatement pr = null;
        try {
            pr = myConn.prepareStatement("update  user set username=?, firstname=?, lastname=?, password=?, userType=?, phone=? where id=" + id);
            pr.setString(1, username);
            pr.setString(2, firstname);
            pr.setString(3, lastname);
            pr.setString(4, password);
            pr.setString(5, unitMaker(userType));
            pr.setString(6, phone);
            pr.executeUpdate();
            daoUtils.log("Admin1", "O'zgartirish", "", String.valueOf(u.getId()), "Foydalanuvchi ma'lumotlari o'zgartirildi" + username + " ," + firstname);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                try {
                    pr.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteUser(String id) {
        PreparedStatement pr = null;
        try {
            pr = myConn.prepareStatement("DELETE FROM user WHERE id=?");
            pr.setString(1, id);
            pr.executeUpdate();
            daoUtils.log("Admin1", "Foydalanuvchi qo'sish", "", String.valueOf(u.getId()), "Foydalanuchi o'chirildi " + id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                try {
                    pr.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addToMarketing(String company, String name, String barcode_o, String barcode, String color, String cost) {

        String company_id = getCompanyId("suplier", "companyName", company);

        PreparedStatement pr = null;

        try {
            pr = myConn.prepareStatement("INSERT INTO marketing_filter(company, name, barcode_o, barcode, color, cost) VALUES (?,?,?,?,?,?)");
            pr.setString(1, company_id);
            pr.setString(2, name);
            pr.setString(3, barcode_o);
            pr.setString(4, barcode);
            pr.setString(5, color);
            pr.setString(6, cost);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                try {
                    pr.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateMarketing(String name, String barcode_o, String barcode, String color, String cost, String id) {
        PreparedStatement pr = null;
        try {
            pr = myConn.prepareStatement("update  marketing_filter set name=?, barcode_o=?, barcode=?, color=?, cost=? where id=" + id);
            pr.setString(1, name);
            pr.setString(2, barcode_o);
            pr.setString(3, barcode);
            pr.setString(4, color);
            pr.setString(5, cost);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                try {
                    pr.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteMarketing(String id) {
        PreparedStatement pr = null;
        try {
            pr = myConn.prepareStatement("DELETE FROM marketing_filter WHERE id=?");
            pr.setString(1, id);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                try {
                    pr.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getCompanyId(String tableName, String columnName, String name) {
        try {
            String q = "SELECT Id FROM " + tableName + " WHERE " + columnName.trim() + "= '" + name.trim() + "'";
            Statement st = myConn.createStatement();
            ResultSet rs = st.executeQuery(q);
            if (rs.next()) {
                return rs.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void addColorCombobox(ComboBox<String> comboBox) {

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT color FROM marketing_v order by id desc");
            while (resultSet.next()) {  // loop
                // Now add the comboBox addAll statement
                comboBox.getItems().addAll(resultSet.getString("color"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DaoUtils.close(statement, resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private String unitMaker(String userType) {
        String unit_id = "1";
        switch (userType) {
            case "Ombor":
                unit_id = "1";
                break;
            case "1-ish/ch":
                unit_id = "2";
                break;
            case "2-ish/ch":
                unit_id = "3";
                break;
            case "Savdo":
                unit_id = "4";
                break;
            case "Admin1":
                unit_id = "5";
                break;
            case "Accounting":
                unit_id = "6";
                break;
        }
        return unit_id;
    }

    public void total_balance(Label sum, Label dollar, Label account, Label VAL) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
            symbols.setGroupingSeparator(' ');
            DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("Select * from total_balance where id =1");
            while (resultSet.next()) {
                sum.setText(formatter.format(resultSet.getDouble("sum")));
                dollar.setText(formatter.format(resultSet.getDouble("dollar")));
                account.setText(formatter.format(resultSet.getDouble("hr")));
                VAL.setText(formatter.format(resultSet.getDouble("vhr")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void chart(String dan, String gacha, AreaChart areaChart) throws SQLException {
        /*
         *   Getting data from database to XYChart
         */

        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pr = null;
        try {


            if (!dan.equals("1") && !gacha.equals("1")) {
                pr = myConn.prepareStatement("SELECT * FROM history WHERE substr(date_cr,7,10) BETWEEN ? AND ?");
                pr.setString(1, dan);
                pr.setString(2, gacha);
                resultSet = pr.executeQuery();
            } else {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM history ORDER BY id limit 10");
            }


            XYChart.Series<String, Double> series = new XYChart.Series<>();

            while (resultSet.next()) {
                series.getData().add(new XYChart.Data<>(resultSet.getString("date_cr"), resultSet.getDouble("cost")));
            }
            areaChart.getData().add(series);

            /*
             * End of XYChart
             *
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Inserting into balance table to with excell sheet
    public void InsertRowInDB(String who, String sum_in, String sum_out, String dollar_in, String dollar_out, String hr_in, String hr_out) throws SQLException {
        PreparedStatement pr = null;
        try {
            String sql = "INSERT INTO balance (who, sum_in, sum_out, dollar_in, dollar_out, hr_in, hr_out) VALUES (?,?,?,?,?,?,?)";
            pr = myConn.prepareStatement(sql);
            pr.setString(1, who);
            pr.setString(2, sum_in);
            pr.setString(3, sum_out);
            pr.setString(4, dollar_in);
            pr.setString(5, dollar_out);
            pr.setString(6, hr_in);
            pr.setString(7, hr_out);
            pr.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                pr.close();
            }
        }
    }

    public void adminLog(TableView table_log, String dan, String gacha) {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);

        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement pr = null;
        try {
            ObservableList<LogTable> marketings = FXCollections.observableArrayList();

            if (!dan.equals("1") && !gacha.equals("1")) {
                pr = myConn.prepareStatement("SELECT * FROM admin_history_v WHERE substr(date,7,10) BETWEEN ? AND ?");
                pr.setString(1, dan);
                pr.setString(2, gacha);
                resultSet = pr.executeQuery();
            } else {
                statement = myConn.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM admin_history_v ORDER BY id limit 100");
            }

            while (resultSet.next()) {
                LogTable marketing = new LogTable();
                marketing.setId(resultSet.getString("id"));
                marketing.setModule(resultSet.getString("module"));
                marketing.setType(resultSet.getString("type"));
                marketing.setCost(resultSet.getString("cost"));
                marketing.setCr_by(resultSet.getString("cr_by"));
                marketing.setDate(resultSet.getString("date"));
                marketing.setComment(resultSet.getString("comment"));
                marketings.add(marketing);
            }
            table_log.setItems(marketings);

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            try {
                DaoUtils.close(statement, resultSet);
                if (pr != null) {
                    pr.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}




