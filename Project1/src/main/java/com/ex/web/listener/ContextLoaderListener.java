package com.ex.web.listener;

import com.ex.persistence.ConnectionFactory;
import com.ex.persistence.EmployeesPersistence;
import com.ex.persistence.PostgresConnectionFactory;
import com.ex.persistence.ReimbursementPersistence;
import com.ex.services.EmployeesService;
import com.ex.services.ReimbursementService;
import com.ex.web.exceptions.InitializationException;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
The ServletContext is a set of parameters (immutable)
and attributes (mutable) that are available to all servlet in your application.

The ServletContextListener is a class that will be invoked when an application is being
deployed (contextInitialized) and undeployed (contextDestroyed) you can use these opportunities
to either add attributes or cleanup resources that will be used by servlets in the application
 */

public class ContextLoaderListener implements ServletContextListener
{
    private final Logger LOG = Logger.getLogger(ContextLoaderListener.class);
    ConnectionFactory connectionFactory;

    //create the object we will need throughout the whole application
    // placing them in the servlet context allows us to have easy
    // consistent access to the object in each dependent class
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        initialzeConnectionFactory(context);
        initializeDataAccess(context);
        initializeService(context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOG.info("Cleaning up context");
        connectionFactory.destroy();
    }

    private void initialzeConnectionFactory(ServletContext context)
    {
        LOG.info("Initializing database connection factory");
        connectionFactory = new PostgresConnectionFactory(
                "jdbc:postgresql://postgresdb.cel1vbjywlkp.us-east-2.rds.amazonaws.com:5432/remibursementapp",
                "remibursementdb",
                "pa$$word");
        context.setAttribute("connectionFactory", connectionFactory);
        LOG.info("ConnectionFactory added to context");
    }

    private void initializeDataAccess(ServletContext context)
    {
        LOG.info("Initializing data access");
        EmployeesPersistence employeesPersistence = new EmployeesPersistence(connectionFactory);
        context.setAttribute("employeePersistence",employeesPersistence);
        ReimbursementPersistence reimbursementPersistence = new ReimbursementPersistence(connectionFactory);
        context.setAttribute("reimbursementPersistence",reimbursementPersistence);
        LOG.info("Data access initialized");
    }

    private void initializeService(ServletContext context)
    {
        LOG.info("Initializing service layer");
        EmployeesPersistence employeesPersistence = (EmployeesPersistence)context.getAttribute("employeePersistence");
        ReimbursementPersistence reimbursementPersistence = (ReimbursementPersistence)context.getAttribute("reimbursementPersistence");

        if(employeesPersistence == null && reimbursementPersistence == null)
        {
            throw new InitializationException("Application not initialize, missing dependency"
                                                + employeesPersistence.getClass().getName());
        } else
        {
            EmployeesService employeesService = new EmployeesService(employeesPersistence);
            context.setAttribute("employeeService", employeesService);
            ReimbursementService reimbursementService = new ReimbursementService((reimbursementPersistence));
            context.setAttribute("reimbursementService", reimbursementService);
        }
        LOG.info("Service layer initialized");
    }
}
