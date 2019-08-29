package sample.dao;

import javafx.scene.control.Label;
import sample.Login;
import sample.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class accountingDao {

    private Connection myConn = null;
    DaoUtils daoUtils = new DaoUtils();
    User u = Login.currentUser;

    public accountingDao() {
        try {
            myConn = database.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLabels(Label accHR_label, Label accVAL_label) throws SQLException {
        try (PreparedStatement pr = myConn.prepareStatement("select * from total_balance"); ResultSet rs = pr.executeQuery()) {
            while (rs.next()) {
                accHR_label.setText(rs.getString("hr"));
                accVAL_label.setText(rs.getString("vhr"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exchangeHr(String operType, String sum, String dollarRate) {
        PreparedStatement pr = null;
        try {
            pr = myConn.prepareStatement("update total_balance set hr=(hr-?), vhr=(vhr+?)");
            pr.setString(1, sum);
            pr.setDouble(2, Double.valueOf(sum) / Double.valueOf(dollarRate));
            pr.executeUpdate();
            daoUtils.log("Admin1", "Valyuta almashtirish", "", String.valueOf(u.getId()), "hr-vhr: sum -> " +sum+" dollar kursi ->"+dollarRate+" Dollar ->"+Double.valueOf(sum) / Double.valueOf(dollarRate));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
