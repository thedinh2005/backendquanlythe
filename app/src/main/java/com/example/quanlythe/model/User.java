package com.example.quanlythe.model;

public class User {
    private String username;
    private String password;

    public User() {
        // Constructor rỗng bắt buộc nếu GSON cần
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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
}
