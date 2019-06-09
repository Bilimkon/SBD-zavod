package sample.components.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.components.models.Suplier;
import sample.controller.Login;
import sample.dao.DaoUtils;
import sample.dao.database;
import sample.utils.utils;

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
            resultSet = statement.executeQuery("SELECT * FROM suplier ORDER BY id");
            while (resultSet.next()) {
                Suplier suplier = new Suplier();
                suplier.setId(resultSet.getString("id"));
                suplier.setCompanyName(resultSet.getString("companyName"));
                suplier.setPerson(resultSet.getString("person"));
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

    public void addSuplier(String name, String person, String info) throws SQLException {
        PreparedStatement pr = null;

        try {
            pr = myConn.prepareStatement("INSERT INTO suplier(companyName, person, info, date_cr, cr_by) VALUES (?,?,?,?,?)");
            pr.setString(1, name);
            pr.setString(2, person);
            pr.setString(3, info);
            pr.setString(4, String.valueOf(utils.getCurrentDate()));
            pr.setString(5, user_id);
            pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pr != null) {
                pr.close();
            }
        }
    }
    public void updateSuplier(String name, String person, String info, String id) throws SQLException {
        PreparedStatement pr = null;
        try{
            pr = myConn.prepareStatement("UPDATE  suplier t set t.companyName=?, t.person=?, t.info=? where t.id=?");
            pr.setString(1,name);
            pr.setString(2,person);
            pr.setString(3,info);
            pr.setString(4,id);

            pr.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (pr != null) {
                pr.close();
            }
        }
    }

    public void deleteSuplier(String id) throws SQLException {
        PreparedStatement pr = null;
        try{
            pr = myConn.prepareStatement("DELETE FROM suplier WHERE id=?");
            pr.setString(1,id);
            pr.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (pr != null) {
                pr.close();
            }
        }

    }
}
