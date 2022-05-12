package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import value_entity.MessageModelEC2;
import service.ec2Service;
import java.io.IOException;
@WebServlet("/ec2KeyPair")
public class ec2KeyPair extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ec2Service ec2Service = new ec2Service();
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	   	String keyPairName = req.getParameter("keyPairName");
	   	String keyRegion = req.getParameter("keyRegion");
	   	String keyOption = req.getParameter("keyOption");
	   	MessageModelEC2 returnmessage = ec2Service.createKeyPair(keyOption, keyPairName, keyRegion);
	    if (returnmessage.getStatus_code() == 1) {
	    	req.getSession().setAttribute("user", returnmessage.getMessage_object() );
	    	res.sendRedirect("ec2.jsp");
	    }
	    else {
	    	req.getSession().setAttribute("returnmessage", returnmessage);
	    	req.getRequestDispatcher("index.jsp").forward(req, res);
	    }
   }
}
