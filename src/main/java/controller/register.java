package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.userService;
import value_entity.MessageModel;
@WebServlet("/register")
public class register extends HttpServlet{
	private userService u_serv= new userService();
	   @Override
	   protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		     String username = req.getParameter("uname");
		     String userpwd = req.getParameter("upwd");
		     String repeatpwd = req.getParameter("rpwd");
		     String usermasterkey = req.getParameter("umaster");
		     String useraccesskey = req.getParameter("uaccess");
		     MessageModel returnmessage = u_serv.userRegister(username,userpwd,repeatpwd,usermasterkey,useraccesskey);
		     if (returnmessage.getStatus_code() == 1) {
		    	 req.getSession().setAttribute("user", returnmessage.getMessage_object());
		    	 res.sendRedirect("login.jsp");
		     }
		     else {
		    	 req.getSession().setAttribute("returnmessage", returnmessage);
		    	 req.getRequestDispatcher("register.jsp").forward(req, res);
		     }
	   }
}
