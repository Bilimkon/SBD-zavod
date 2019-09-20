package sample.components.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.Login;
import sample.components.models.Suplier;
import sample.components.sell.Utils.Utils;
import sample.dao.DaoUtils;
import sample.dao.database;

import java.sql.*;

public class SuplierDao {
    Connection myConn = null;

    String user_id = String.valueOf(Login.currentUser.getId());
    public SuplierDao() {
        try {
            myConn = database.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initializeTable(TableView tableView) throws SQLException {


        Statement statement = null;
        ResultSet resultSet = null;

        //List to add items
        ObservableList<Suplier> supliers = FXCollections.observableArrayList();
        try {
            statement = myConn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM person ORDER BY id");
            while (resultSet.next()) {
                Suplier suplier = new Suplier();
                suplier.setId(resultSet.getString("id"));
                suplier.setCompanyName(resultSet.getString("companyName"));
                suplier.setAccount(resultSet.getString("account"));
                suplier.setPhone(resultSet.getString("phone"));
                suplier.setInfo(resultSet.getString("info"));
                suplier.setDate_cr(resultSet.getString("date_cr"));
                suplier.setCr_by(resultSet.getString("cr_by"));

                supliers.add(suplier);
            }
            tableView.setItems(supliers);

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            DaoUtils.close(statement, resultSet);
        }
    }

    public void addSuplier(String name, String account, String info, String phone) throws SQLException {

        try (PreparedStatement pr = myConn.prepareStatement("INSERT INTO person(type, companyName, account, phone, info,  date_cr, cr_by) VALUES (?,?,?,?,?,?,?)")) {
            pr.setString(1, "1");
            pr.setString(2, name);
            pr.setString(3, account);
            pr.setString(4, phone);
            pr.setString(5, info);
            pr.setString(6, Utils.getCurrnetDateInStandardFormat());
            pr.setString(7, user_id);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getPersonId(String name){
        Statement pr = null;
         ResultSet rs = null;
         String  id= null;
        try{
            pr = myConn.createStatement();
           rs =  pr.executeQuery("Select id from person where companyName='"+name+"'");
           while (rs.next()){
               id = rs.getString("id");
           }
           return id;
        }catch (Exception e){
            e.printStackTrace();
        }
     return null;
    }

    public void updateSuplier(String name, String account, String phone, String info, String id) throws SQLException {
        try (PreparedStatement pr = myConn.prepareStatement("UPDATE  person t set t.companyName=?, t.account=?, t.phone=?, t.info=? where t.id=?")) {
            pr.setString(1, name);
            pr.setString(2, account);
            pr.setString(3, phone);
            pr.setString(4, info);
            pr.setString(5, id);

            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteSuplier(String id) throws SQLException {
        PreparedStatement pr = null;
        try{
            /*pr = myConn.prepareStatement("DELETE FROM person WHERE id=?");
            pr.setString(1,id);
            pr.executeUpdate();*/
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
