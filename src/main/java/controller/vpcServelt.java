package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import value_entity.MessageModelEC2;
import service.vpcService;

import java.io.IOException;
@WebServlet("/vpc")
public class vpcServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private vpcService vpcService = new vpcService();
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	    String cidrBlock = req.getParameter("cidrBlock");
	    String regionName = req.getParameter("regionName");
	    MessageModelEC2 returnmessage = vpcService.vpcCreate(cidrBlock, regionName);
	    if (returnmessage.getStatus_code() == 1) {
	    	req.getSession().setAttribute("user", returnmessage.getMessage_object() );
	    	res.sendRedirect("index.jsp");
	    }
	    else {
	    	req.getSession().setAttribute("returnmessage", returnmessage);
	    	req.getRequestDispatcher("vpc.jsp").forward(req, res);
	    }
   }
}
