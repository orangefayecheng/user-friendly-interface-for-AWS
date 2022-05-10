package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ec2Service;
import value_entity.MessageModelEC2;

import java.io.IOException;
@WebServlet("/ec2")
public class ec2servelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ec2Service ec2Service = new ec2Service();
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	   	String instance_type = req.getParameter("instance_type");
	   	String key_name = req.getParameter("key_name");
	    String security_group = req.getParameter("security_group");
	    String regionName = req.getParameter("regionName");
	    String amiId = req.getParameter("amiId");
	    MessageModelEC2 returnmessage = ec2Service.ec2Create(instance_type,key_name,security_group, regionName, amiId);
	    if (returnmessage.getStatus_code() == 1) {
	    	req.getSession().setAttribute("user", returnmessage.getMessage_object() );
	    	res.sendRedirect("ec2message.jsp");
	    }
	    else {
	    	req.getSession().setAttribute("returnmessage", returnmessage);
	    	req.getRequestDispatcher("ec2.jsp").forward(req, res);
	    }
   }
}
