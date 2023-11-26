package com.example.securo;

public class User {
    private int id;
    private String username;
    private String password;
    private String login;
    private String clas;

    public User(){
    }

    public User(String username, String password, String login, String clas){
        this.username = username;
        this.password = password;
        this.login = login;
        this.clas = clas;
    }

    public User(int id, String username, String password, String login, String clas){
        this.id = id;
        this.username = username;
        this.password = password;
        this.login = login;
        this.clas = clas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    @Override
    public String toString(){
        return getClass().getSimpleName() + ": {id: " + id + ", username: " + username + ", class: " + clas + ", login: " + login + ", password: " + password + "}";
    }
}
