package com.ex.services;

import com.ex.model.Employee;
import com.ex.persistence.EmployeesPersistence;

import java.sql.SQLException;
import java.util.List;

public class EmployeesService
{
    private EmployeesPersistence employeesPersistence;

    public EmployeesService(EmployeesPersistence employeesPersistence) {
        this.employeesPersistence = employeesPersistence;
    }

    public List<Employee> getAllEmployee()
    {
        return this.employeesPersistence.getAll();
    }
    public boolean isEmployee(String username, String password) {
        return this.employeesPersistence.validate(username, password);
    }
    public boolean isManager(String username, String password){
        return this.employeesPersistence.validateManager(username, password);
    }

    public int findEmployee(String username, String password)
    {
        return this.employeesPersistence.findEmployeeID(username, password);
    }

}
