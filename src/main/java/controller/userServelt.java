package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.user;
import service.userService;
import value_entity.MessageModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
@WebServlet("/login")
public class userServelt extends HttpServlet {
   private userService u_serv= new userService();
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	     String username = req.getParameter("uname");
	     String userpwd = req.getParameter("upwd");
	     MessageModel returnmessage = u_serv.userLogin(username,userpwd);
	     user u = returnmessage.getMessage_object();
	     if (returnmessage.getStatus_code() == 1) {
	    	 req.getSession().setAttribute("user", returnmessage.getMessage_object() );
	    	 PrintWriter writer = new PrintWriter("/Users/aashishpokhrel/user-friendly-interface-for-AWS/src/test/key.txt");
	    	 writer.print("");
	    	 writer.println(u.getUsermasterkey());
	    	 writer.println(u.getUseraccesskey());
	    	 writer.close();
	    	 
	    	 
	    	 res.sendRedirect("index.jsp");
	     }
	     else {
	    	 req.getSession().setAttribute("returnmessage", returnmessage);
	    	 req.getRequestDispatcher("login.jsp").forward(req, res);
	     }
   }
}
