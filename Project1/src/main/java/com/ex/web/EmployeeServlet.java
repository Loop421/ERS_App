package com.ex.web;

import com.ex.model.Employee;
import com.ex.model.LoginAuto;
import com.ex.services.EmployeesService;
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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class EmployeeServlet extends HttpServlet
{
    private EmployeesService employeesService;
    private final Logger LOG = Logger.getLogger(this.getClass());

    @Override
    public void init(ServletConfig config) throws ServletException {
        LOG.info("EmployeeServlet initialization failed");
        ServletContext context = config.getServletContext();
        String serviceLookupName = config.getInitParameter("employeeServiceLookupName");
        employeesService =(EmployeesService) context.getAttribute(serviceLookupName);

        if(employeesService == null)
        {
            LOG.warn("EmployeeServlet Initialization failed");
            InitializationException ie = new InitializationException("Missing dependency "
                    + employeesService.getClass().getName());
            throw new ServletException("Error initializing EmployeeServlet", ie);
        }
        LOG.info("EmployeeServlet initialization complete");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        boolean queryComplete = false;

//        String n=req.getParameter("username");
//        String p=req.getParameter("password");

        LOG.debug("Request for " + pathInfo);
        List<Employee> employees = null;
        switch (pathInfo)
        {
            case "/":
                employees = retrieveEmployees();
                queryComplete = true;
                break;
            case "/q":
//                isUserEmployee(n,p);
                break;
        }

        if(queryComplete)
        {
            ObjectMapper objectMapper = new ObjectMapper();
            String ret = objectMapper.writeValueAsString(employees);
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

        InputStream in = req.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        LoginAuto loginAuto = objectMapper.readValue(in, LoginAuto.class);
        resp.setStatus(200);

        String p = loginAuto.getPassword();
        String n = loginAuto.getUsername();

//        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
//        System.out.println(out);
//
//        String n=req.getParameter("username");
//        String p=req.getParameter("pwd");
        switch (pathInfo)
        {
            case "/":
                if (isUserEmployee(n,p)) {

                    out.print("employee");
                    resp.setStatus(200);
                }
                else if(isUserManager(n,p))
                {
                    out.print("manager");
                    resp.setStatus(200);
                }
                else {
                    out.print(false);
                    resp.setStatus(403);
                }
                break;
            case "/id":
                out.print(findId(n,p));
                resp.setStatus(200);
                break;
        }
        out.close();





//        switch (pathInfo)
//        {
//            case "?":
//                isUserEmployee(n,p);
//                break;
//            case "/q":
//                break;
//        }



    }

    private List<Employee> retrieveEmployees()
    {
        return this.employeesService.getAllEmployee();
    }

    private boolean isUserEmployee(String username, String password) {
        return this.employeesService.isEmployee(username, password);
    }

    private boolean isUserManager(String username, String password){
        return this.employeesService.isManager(username, password);
    }

    private int findId(String username, String password)
    {
        return this.employeesService.findEmployee(username, password);
    }
}
