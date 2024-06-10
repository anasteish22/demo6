package com.example.demo6.model;

public class User {
    private int id;
    private String login;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        StringBuilder builder = new StringBuilder("User{");
        builder.append("id=").append(id).append(", ");
        builder.append("login=").append(login).append(", ");
        builder.append("password=").append(password).append("}");
        return builder.toString();
    }
}
