package com.ex.persistence;

import com.ex.model.Employee;
import com.ex.model.Manager;
import com.ex.persistence.exceptions.ConnectionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeesPersistence implements Persistable {

    private ConnectionFactory connectionFactory;
    private Employee e;

    public EmployeesPersistence(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employee";

        try(Connection c = connectionFactory.newConnection(); //try-with-resources Statement
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(query)) {
            employees = createEmployeeCollection(rs);
        } catch (SQLException se) {
            throw new ConnectionException("An error occurred while querying in " + this.getClass().getName()
                                            + "#getAll", se);
        }
        return employees;
    }

    public boolean validate(String username, String password) {
        List<Employee> employees;
        boolean status = false;

        String query = "SELECT * FROM employee WHERE username=? AND password_=?";


        try(Connection c = connectionFactory.newConnection();
            PreparedStatement s = c.prepareStatement(query))
            {

            s.setString(1,username);
            s.setString(2,password);
            ResultSet rs = s.executeQuery();
            employees = createEmployeeCollection(rs);
            if(employees.size() != 0)
            {
                status = true;
            }
            else
            {
                status = false;
            }
        } catch (SQLException ex) {
            throw new ConnectionException("An error occurred while querying in " + this.getClass().getName()
                                            + "#validate", ex);
        }
        return status;
    }

    public int findEmployeeID (String username, String password)
    {
        List<Employee> employees;
        String query = "SELECT * FROM employee WHERE username=? AND password_=?";

        try(Connection c = connectionFactory.newConnection();
            PreparedStatement s = c.prepareStatement(query))
        {

            s.setString(1,username);
            s.setString(2,password);
            ResultSet rs = s.executeQuery();
            employees = createEmployeeCollection(rs);

        } catch (SQLException ex) {
            throw new ConnectionException("An error occurred while querying in " + this.getClass().getName()
                    + "#validate", ex);
        }
        return employees.get(0).getEmpid();

    }

    public boolean validateManager(String username, String password) {
        List<Manager> managers;
        boolean status = false;
//        Connection c = connectionFactory.newConnection();
        String query = "SELECT * FROM manager WHERE username=? AND password_=?";
//        PreparedStatement s = c.prepareStatement(query);
//        s.setString(1, username);
//        s.setString(2, password);
//        ResultSet rs = s.executeQuery();
//        employees = createEmployeeCollection(rs);
//
//        if(!employees.isEmpty())
//        {
//            status = true;
//        }


        try(Connection c = connectionFactory.newConnection();
            PreparedStatement s = c.prepareStatement(query))
        {

            s.setString(1,username);
            s.setString(2,password);
            ResultSet rs = s.executeQuery();
            managers = createManagerCollection(rs);
            if(managers.size() != 0)
            {
                status = true;
            }
        } catch (SQLException ex) {
            throw new ConnectionException("An error occurred while querying in " + this.getClass().getName()
                    + "#validate", ex);
        }
        return status;
    }

    @Override
    public Object getByAccount(Object o) {
        return null;
    }

    @Override
    public Object save(Object obj) {
        return null;
    }

    @Override
    public void update(Object obj) {

    }

    @Override
    public void delete(Object obj) {

    }

    @Override
    public void persist() {

    }

    private List<Employee> createEmployeeCollection(ResultSet rs)
    {
        List<Employee> employees = new ArrayList<>();
        try {
            Employee e = null;
            while(rs.next())
            {

//                System.out.println("Not empty");
//                Employee next;
//                final String employeeName = rs.getString("name");
//                next = employees.stream().filter(e -> e.getName().equals(employeeName)).findFirst().orElse(null);
//                if(!employees.isEmpty() && next != null)
//                {
//
//                }
//                next = new Employee();
                e = new Employee(rs.getInt("empid"),rs.getString("name"),
                        rs.getString("username"),rs.getString("password_")
                        ,rs.getString("phone"), rs.getString("email"));
                employees.add(e);

            }
            System.out.println(employees.size());
            return employees;
        } catch (SQLException se) {
            throw new ConnectionException("Error creating employee list", se);
        }
    }

    private List<Manager> createManagerCollection(ResultSet rs)
    {
        List<Manager> managers = new ArrayList<>();
        try {
            Manager m = null;
            while(rs.next())
            {

                m = new Manager(rs.getInt("manid"),rs.getString("name"),
                        rs.getString("username"),rs.getString("password_"));
                managers.add(m);

            }
            return managers;
        } catch (SQLException se) {
            throw new ConnectionException("Error creating manager list", se);
        }
    }
}
