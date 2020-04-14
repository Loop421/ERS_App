package com.ex.model;

public class Manager
{
    private int manid;
    private String name;
    private String username;
    private String password;

    public Manager() {
    }

    public Manager(int manid, String name, String username, String password) {
        this.manid = manid;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public int getManid() {
        return manid;
    }

    public void setManid(int manid) {
        this.manid = manid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
