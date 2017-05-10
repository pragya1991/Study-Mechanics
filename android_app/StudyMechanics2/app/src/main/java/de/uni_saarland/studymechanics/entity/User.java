package de.uni_saarland.studymechanics.entity;

/**
 * Created by asus on 5/10/17.
 */

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User(){}

    public User(String fName, String lName, String email, String pwd){
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.password = pwd;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
