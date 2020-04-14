package com.ex.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class DatabaseAccess extends HttpServlet {

    String url ="jdbc:postgresql://postgresdb.cel1vbjywlkp.us-east-2.rds.amazonaws.com:5432/remibursementapp";
    String username = "remibursementdb";
    String password = "pa$$word";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String title = "Database Result";

        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n");

        try{
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url,username,password);
            String st ="SELECT * FROM employee";
            Statement s = conn.createStatement();
            ResultSet re = s.executeQuery(st);
            while(re.next())
            {
                int empid = re.getInt("empid");
                String name = re.getString("name");
                String username_ = re.getString("username");
                String phone = re.getString("phone");
                String email = re.getString("email");

                out.println("empid: " + empid + "<br>");
                out.println("name: " + name + "<br>");
                out.println("username: " + username_ + "<br>");
                out.println("phone" + phone + "<br>");
                out.println("email" + email + "<br>");
            }
            out.println("</body></html>");
//            out.


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
