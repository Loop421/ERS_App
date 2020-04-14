package com.ex.model;

import java.util.ArrayList;

public class Employees
{
    ArrayList<Employee> employees;

    public Employees()
    {
        employees = new ArrayList<Employee>();
    }

    public ArrayList<Employee> getEmployees()
    {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees)
    {
        this.employees = employees;
    }
}
