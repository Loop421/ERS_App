package com.ex.web;

import com.ex.model.Employee;
import com.ex.model.LoginAuto;
import com.ex.model.Reimbursement;
import com.ex.services.ReimbursementService;
import com.ex.web.exceptions.InitializationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ReimbursementServlet extends HttpServlet
{
    private ReimbursementService reimbursementService;
    private final Logger LOG = Logger.getLogger(this.getClass());

    @Override
    public void init(ServletConfig config) throws ServletException {
        LOG.info("ReimbursementServlet initialization failed");
        ServletContext context = config.getServletContext();
        String serviceLookupName = config.getInitParameter("reimbursementServiceLookupName");
        reimbursementService =(ReimbursementService)context.getAttribute(serviceLookupName);

        if(reimbursementService == null)
        {
            LOG.warn("ReimbursementServlet Initialization failed");
            InitializationException ie = new InitializationException("Missing dependency "
                    + reimbursementService.getClass().getName());
            throw new ServletException("Error initializing ReimbursementServlet", ie);
        }
        LOG.info("ReimbursementServlet initialization complete");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        boolean queryComplete = false;

        LOG.debug("Request for " + pathInfo);
        List<Reimbursement> reimbursements = null;
        switch (pathInfo)
        {
            case "/":
                reimbursements = retrieveReimbursements();
                queryComplete = true;
                break;
            case "/pending":
                reimbursements = retrievePending();
                queryComplete = true;
                break;
            case "/approve":
                reimbursements = retrieveApprove();
                queryComplete = true;
                break;
        }

        if(queryComplete)
        {
//            resp.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
//            resp.setHeader("Access-Control-Allow-Headers", "Authencation, My-Custom-Header");
//            resp.setHeader("Access-Control-Allow-Method", "get, put");

            ObjectMapper objectMapper = new ObjectMapper();
            String ret = objectMapper.writeValueAsString(reimbursements);
            resp.getWriter().write(ret);
            resp.setStatus(200);
            resp.setContentType("application/json");
        }
        else
        {
            resp.setStatus(404);
        }
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pathInfo = req.getPathInfo();
        boolean queryComplete = false;
        List<Reimbursement> reimbursements = null;

        InputStream in = req.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        Reimbursement reimbursement = objectMapper.readValue(in, Reimbursement.class);
        resp.setStatus(200);

        switch (pathInfo)
        {
            case "/emp":
                reimbursements = retrieveSingleEmp(reimbursement.getEmpid());
                queryComplete = true;
                break;
            case "/addExpense":
                addNewExpense(reimbursement.getExpenseType(), reimbursement.getTotalAmount(),
                        reimbursement.getStatus(), reimbursement.getEmpid());
                reimbursements = retrieveSingleEmp(reimbursement.getEmpid());
                queryComplete = true;
                break;
            case "/changeStatus":
                statusUpdate(reimbursement.getStatus(), reimbursement.getReimId(), reimbursement.getManid());
                reimbursements = retrieveReimbursements();
                queryComplete = true;
                break;


        }

        if(queryComplete)
        {

            objectMapper = new ObjectMapper();
            String ret = objectMapper.writeValueAsString(reimbursements);
            resp.getWriter().write(ret);
            resp.setStatus(200);
            resp.setContentType("application/json");
        }
        else
        {
            resp.setStatus(404);
        }
        return;
    }

    private List<Reimbursement> retrieveReimbursements()
    {
        return this.reimbursementService.getAllReimbursement();
    }

    private List<Reimbursement> retrievePending(){
        return this.reimbursementService.getAllPending();
    }

    private List<Reimbursement> retrieveApprove()
    {
        return this.reimbursementService.getAllApprove();
    }

    private List<Reimbursement> retrieveSingleEmp(int id){return this.reimbursementService.getSingleEmp(id);}

    private void addNewExpense (String expensetype, double total_amount, String status, int empid)
    {
        this.reimbursementService.addNewReimbursement(expensetype, total_amount, status, empid);
    }

    private  void statusUpdate (String status, int reimId, int manid)
    {
        this.reimbursementService.changeStatus(status, reimId, manid);
    }

}
