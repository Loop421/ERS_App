package com.ex.model;

public class Reimbursement
{
    private int reimId;
    private String expenseType;
    private double totalAmount;
    private String status;
    private String createDate;
    private int empid;
    private int manid;

    public Reimbursement() {
    }

    public Reimbursement(int reimId, String expenseType, double totalAmount,
                         String status, String createDate, int empid, int manid) {
        this.reimId = reimId;
        this.expenseType = expenseType;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createDate = createDate;
        this.empid = empid;
        this.manid = manid;
    }

    public Reimbursement(String expenseType, double totalAmount, String status, int empid) {
        this.expenseType = expenseType;
        this.totalAmount = totalAmount;
        this.status = status;
        this.empid = empid;
    }

    public Reimbursement(int reimId, String status, int manid) {
        this.reimId = reimId;
        this.status = status;
        this.manid = manid;
    }

    public Reimbursement(int empid) {
        this.empid = empid;
    }

    public int getReimId() {
        return reimId;
    }

    public void setReimId(int reimId) {
        this.reimId = reimId;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public int getManid() {
        return manid;
    }

    public void setManid(int manid) {
        this.manid = manid;
    }
}
