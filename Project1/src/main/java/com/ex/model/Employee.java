package com.ex.model;

public class Employee
{
    private int empid;
    private String name;
    private String username;
    private String password;
    private String phoneNum;
    private String email;

    public Employee() {
    }

    public Employee(int empid, String name, String username,
                    String password, String phoneNum, String email)
    {
        this.empid = empid;
        this.name = name;
        this.username = username;
        this.password = password;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public Employee(int empid) {
        this.empid = empid;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
