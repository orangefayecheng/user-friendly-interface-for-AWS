package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.userService;
import value_entity.MessageModel;

import java.io.IOException;
@WebServlet("/login")
public class userServelt extends HttpServlet {
   private userService u_serv= new userService();
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	     String username = req.getParameter("uname");
	     String userpwd = req.getParameter("upwd");
	     MessageModel returnmessage = u_serv.userLogin(username,userpwd);
	     if (returnmessage.getStatus_code() == 1) {
	    	 req.getSession().setAttribute("user", returnmessage.getMessage_object() );
	    	 res.sendRedirect("index.jsp");
	     }
	     else {
	    	 req.getSession().setAttribute("returnmessage", returnmessage);
	    	 req.getRequestDispatcher("login.jsp").forward(req, res);
	     }
   }
}
