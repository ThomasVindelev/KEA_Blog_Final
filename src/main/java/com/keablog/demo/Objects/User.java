package com.keablog.demo.Objects;

public class User {

    private int id;
    private String username;
    private String fullName;
    private String password;
    private int id_role;

    public User() {

    }

    public User(int id, String username, String fullName, String password, int id_role) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.id_role = id_role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }
}
