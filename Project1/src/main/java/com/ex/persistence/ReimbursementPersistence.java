package com.ex.persistence;

import com.ex.model.Reimbursement;
import com.ex.persistence.exceptions.ConnectionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementPersistence implements Persistable {

    private ConnectionFactory connectionFactory;
    private Reimbursement reim;

    public ReimbursementPersistence(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Object getByAccount(Object o) {
        return null;
    }

    @Override
    public List<Reimbursement> getAll()
    {
        List<Reimbursement> reimbursements = new ArrayList<>();
        String query = "SELECT * FROM remibursement";

        try(Connection conn = connectionFactory.newConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query)){

            reimbursements = createReimbursementCollection(rs);

        } catch (SQLException e) {
            throw new ConnectionException("An error occurred while querying in " + this.getClass().getName()
                                            + "#getAll", e);
        }
        return reimbursements;
    }

    public List<Reimbursement> getAllReimbursementPending()
    {
        List<Reimbursement> reimbursements = new ArrayList<>();
        String query = "SELECT * FROM remibursement WHERE status = 'pending'";

        try(Connection c = connectionFactory.newConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(query))
        {
            reimbursements = createReimbursementCollection(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursements;
    }

    public List<Reimbursement> getAllApproveReimbursement()
    {
        List<Reimbursement> reimbursements = new ArrayList<>();
        String query = "SELECT * FROM remibursement WHERE status = 'Approve'";

        try(Connection c = connectionFactory.newConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(query))
        {
            reimbursements = createReimbursementCollection(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursements;
    }

    public List<Reimbursement> getSingleEmployeeReim(int id)
    {
        List<Reimbursement> reimbursements = new ArrayList<>();
        String query = "SELECT * FROM remibursement WHERE empid = ?";

        try(Connection c = connectionFactory.newConnection();
            PreparedStatement s = c.prepareStatement(query))
        {
            s.setInt(1,id);
            ResultSet rs = s.executeQuery();
            reimbursements = createReimbursementCollection(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursements;
    }

    public void newEmployeeReimbursement(String expensetype, double total_amount, String status, int empid)
    {
        String query = "INSERT INTO remibursement (expensetype, totalamount, status, empid) VALUES (?, ?, ?, ?)";

        try(Connection c = connectionFactory.newConnection();
            PreparedStatement s = c.prepareStatement(query))
        {
            s.setString(1,expensetype);
            s.setDouble(2,total_amount);
            s.setString(3,status);
            s.setInt(4,empid);
            s.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Couldn't insert");
        }
    }

    public void managerChangeReimburseStatus(String status, int reimId, int manid){
        String query = "UPDATE remibursement SET status = ?, manid = ? WHERE reimId = ?";

        try(Connection c = connectionFactory.newConnection();
            PreparedStatement s = c.prepareStatement(query))
        {
            s.setString(1,status);
            s.setInt(2,manid);
            s.setInt(3,reimId);
            s.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Couldn't Update #managerChangeReimburseStatus");
        }
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

    private List<Reimbursement> createReimbursementCollection(ResultSet rs)
    {
        List<Reimbursement> reimbursements = new ArrayList<>();
        try {
            while(rs.next())
            {
                reim = new Reimbursement(rs.getInt("reimId"),
                                        rs.getString("expensetype"),
                                        rs.getDouble("totalamount"),
                                        rs.getString("status"),
                                        rs.getString("createdate"),
                                        rs.getInt("empid"),
                                        rs.getInt("manid"));
                reimbursements.add(reim);
            }
            return reimbursements;
        } catch (SQLException e) {
            throw new ConnectionException("Error creating reimbursement list", e);
        }
    }
}
