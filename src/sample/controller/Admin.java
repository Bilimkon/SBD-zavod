package sample.controller;

import javafx.fxml.Initializable;
import sample.dao.database;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class Admin implements Initializable {

    private Connection myConn;

    public Admin(){
        try {
            myConn = database.getConnection();
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Xatolik" + exc, "Xatolik", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
