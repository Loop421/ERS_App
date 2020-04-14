package com.ex.services;

import com.ex.model.Reimbursement;
import com.ex.persistence.ReimbursementPersistence;

import java.util.List;

public class ReimbursementService
{
    private ReimbursementPersistence reimbursementPersistence;

    public ReimbursementService(ReimbursementPersistence reimbursementPersistence) {
        this.reimbursementPersistence = reimbursementPersistence;
    }

    public List<Reimbursement> getAllReimbursement()
    {
        return this.reimbursementPersistence.getAll();
    }
    public List<Reimbursement> getAllPending(){return this.reimbursementPersistence.getAllReimbursementPending();}
    public List<Reimbursement> getAllApprove(){return this.reimbursementPersistence.getAllApproveReimbursement();}
    public List<Reimbursement> getSingleEmp(int id){
        return this.reimbursementPersistence.getSingleEmployeeReim(id);
    }
    public void addNewReimbursement(String expensetype, double total_amount, String status, int empid)
    {
        this.reimbursementPersistence.newEmployeeReimbursement(expensetype, total_amount, status, empid);
    }
    public void changeStatus(String status, int reimId, int manid)
    {
        this.reimbursementPersistence.managerChangeReimburseStatus(status, reimId, manid);
    }
}
