package sample.model;

public class User {
    private int id;
    private String userName;
    private String firstName;
    private String lastname;
    private String password;
    private int  userType;
    private String phone;

    public User(int id, String userName, String firstName, String lastname, String password, int userType, String phone) {
        this.id =id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastname = lastname;
        this.password = password;
        this.userType = userType;
        this.phone = phone;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
