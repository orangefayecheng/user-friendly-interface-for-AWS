package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import value_entity.MessageModelEC2;
import service.vpcService;

import java.io.IOException;
@WebServlet("/vpcPeering")
public class vpcPeeringServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private vpcService vpcService = new vpcService();
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	    String peerId = req.getParameter("peerId");
	    String peerRegion = req.getParameter("peerRegion");
	    String vpcId = req.getParameter("vpcId");
	    MessageModelEC2 returnmessage = vpcService.vpcPeering(peerId, peerRegion, vpcId);
	    if (returnmessage.getStatus_code() == 1) {
	    	req.getSession().setAttribute("user", returnmessage.getMessage_object() );
	    	res.sendRedirect("vpcPeering.jsp");
	    }
	    else {
	    	req.getSession().setAttribute("returnmessage", returnmessage);
	    	req.getRequestDispatcher("vpc.jsp").forward(req, res);
	    }
   }
}
