package sample.components.sell.Core;

import com.sun.istack.internal.Nullable;

import javax.swing.*;
import java.util.Date;


/*
       Humoyun Qo'rg'onov  SBD  ( Software Business Development)
 */
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String telNumber;
    private ImageIcon imageIcon;
    private float salary;
    private Date date;
    private String status;
    private boolean admin;
    private String password;

    public static User getInstance() {
        return new User();
    }

    public User() {

    }

    private User(String firstName, String lastName, String telNumber, @Nullable ImageIcon imageIcon, float salary, @Nullable Date date, String status, boolean admin, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telNumber = telNumber;
        this.imageIcon = imageIcon;
        this.salary = salary;
        this.date = date;
        this.status = status;
        this.admin = admin;
        this.password = password;

    }

    public User(int id, String firstName, String lastName, String telNumber, @Nullable ImageIcon imageIcon, float salary,@Nullable Date date, String status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telNumber = telNumber;
        this.imageIcon = imageIcon;
        this.salary = salary;
        this.date = date;
        this.status = status;
    }



    public User(int id, String firstName, String lastName, String telNumber, @Nullable ImageIcon imageIcon, float salary,@Nullable Date date, String status, boolean admin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telNumber = telNumber;
        this.imageIcon = imageIcon;
        this.salary = salary;
        this.date = date;
        this.status = status;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {

        this.salary = salary;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return firstName + "  " + lastName;
    }
}
