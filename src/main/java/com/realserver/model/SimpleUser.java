package com.realserver.model;

public class SimpleUser {
    private String login;
    private String password;

    public SimpleUser() {
    }

    public SimpleUser(String login, String password) {
        this.login = login;
        this.password = password;
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

    @Override
    public String toString() {
        return "SimpleUser{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
