package com.company.users;

public class User {

    private String login;
    private String password;
    private boolean isAdmin;
    private String email;

    public User(String login, String password, boolean isAdmin, String email) {
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
