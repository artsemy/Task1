package com.company.users;

public class User {

    private String login;
    private String password;
    private boolean isAdmin;
    private String email;

    //constructor
    public User(String login, String password, boolean isAdmin, String email) {
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
        this.email = email;
    }

    //get method
    public String getLogin() {
        return login;
    }

    //set method
    public void setLogin(String login) {
        this.login = login;
    }

    //get method
    public String getPassword() {
        return password;
    }

    //set method
    public void setPassword(String password) {
        this.password = password;
    }

    //get method
    public boolean isAdmin() {
        return isAdmin;
    }

    //set method
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    //get method
    public String getEmail() {
        return email;
    }

    //set method
    public void setEmail(String email) {
        this.email = email;
    }

    //to string
    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    //equals
    @Override
    public boolean equals(Object obj) {
        User u = (User) obj;
        return (login.equals(u.login) && password.equals(u.password) && email.equals(u.email));
    }

}
