package sample.components.sell.productTableView;

import javafx.beans.property.SimpleStringProperty;

public class Sotuvchi {

    SimpleStringProperty id = new SimpleStringProperty();
    SimpleStringProperty username = new SimpleStringProperty();
    SimpleStringProperty firstname = new SimpleStringProperty();
    SimpleStringProperty lastname = new SimpleStringProperty();
    SimpleStringProperty admin = new SimpleStringProperty();
    SimpleStringProperty salary = new SimpleStringProperty();
    SimpleStringProperty password = new SimpleStringProperty();
    SimpleStringProperty birthdate = new SimpleStringProperty();
    SimpleStringProperty date_cr = new SimpleStringProperty();

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

    public String getFirstname() {
        return firstname.get();
    }

    public SimpleStringProperty firstnameProperty() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
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

    public String getAdmin() {
        return admin.get();
    }

    public SimpleStringProperty adminProperty() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin.set(admin);
    }

    public String getSalary() {
        return salary.get();
    }

    public SimpleStringProperty salaryProperty() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary.set(salary);
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

    public String getBirthdate() {
        return birthdate.get();
    }

    public SimpleStringProperty birthdateProperty() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate.set(birthdate);
    }

    public String getDate_cr() {
        return date_cr.get();
    }

    public SimpleStringProperty date_crProperty() {
        return date_cr;
    }

    public void setDate_cr(String date_cr) {
        this.date_cr.set(date_cr);
    }
}
