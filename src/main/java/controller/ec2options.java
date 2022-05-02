package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import value_entity.MessageModelEC2;
import service.ec2optionsService;
import java.io.IOException;
@WebServlet("/ec2options")
public class ec2options extends HttpServlet {
	private ec2optionsService ec2options = new ec2optionsService();
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	   	String optionName = req.getParameter("instance");
	   	String instance_id = req.getParameter("instance_id");
	   	MessageModelEC2 returnmessage = ec2options.ec2Options(optionName, instance_id);
	    if (returnmessage.getStatus_code() == 1) {
	    	req.getSession().setAttribute("user", returnmessage.getMessage_object() );
	    	res.sendRedirect("ec2optionsmessage.jsp");
	    }
	    else {
	    	req.getSession().setAttribute("returnmessage", returnmessage);
	    	req.getRequestDispatcher("index.jsp").forward(req, res);
	    }
   }
}