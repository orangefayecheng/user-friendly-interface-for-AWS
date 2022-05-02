package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import value_entity.MessageModelEC2;
import value_entity.MessageModelS3;
import service.s3Service;

import java.io.IOException;
@WebServlet("/s3")
public class s3servelt extends HttpServlet {
	private s3Service s3Service = new s3Service();
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	   	String bucketName = req.getParameter("bucketName");
	   	MessageModelS3 returnmessage = s3Service.s3CreateBucket(bucketName);
	    if (returnmessage.getStatus_code() == 1) {
	    	req.getSession().setAttribute("user", returnmessage.getMessage_object() );
	    	res.sendRedirect("s3message.jsp");
	    }
	    else {
	    	req.getSession().setAttribute("returnmessage", returnmessage);
	    	req.getRequestDispatcher("s3.jsp").forward(req, res);
	    }
   }
}
