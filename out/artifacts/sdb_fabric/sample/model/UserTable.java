package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class UserTable {

    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty username = new SimpleStringProperty();
    SimpleStringProperty fisrtname = new SimpleStringProperty();
    SimpleStringProperty lastname = new SimpleStringProperty();
    SimpleStringProperty phone = new SimpleStringProperty();
    SimpleStringProperty password = new SimpleStringProperty();
    SimpleStringProperty userType = new SimpleStringProperty();

    public UserTable(SimpleStringProperty id, SimpleStringProperty username, SimpleStringProperty fisrtname, SimpleStringProperty lastname, SimpleStringProperty phone, SimpleStringProperty password, SimpleStringProperty userType) {
        this.id = id;
        this.username = username;
        this.fisrtname = fisrtname;
        this.lastname = lastname;
        this.phone = phone;
        this.password = password;
        this.userType = userType;
    }

    public UserTable() {

    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getFisrtname() {
        return fisrtname.get();
    }

    public SimpleStringProperty fisrtnameProperty() {
        return fisrtname;
    }

    public void setFisrtname(String fisrtname) {
        this.fisrtname.set(fisrtname);
    }

    public String getLastname() {
        return lastname.get();
    }

    public SimpleStringProperty lastnameProperty() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getUserType() {
        return userType.get();
    }

    public SimpleStringProperty userTypeProperty() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType.set(userType);
    }
}

