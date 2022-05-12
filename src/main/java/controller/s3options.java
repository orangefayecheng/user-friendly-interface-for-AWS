package controller;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import value_entity.MessageModelS3;
import service.s3Service;
import java.io.IOException;
@WebServlet("/s3options")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class s3options extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private s3Service s3Service = new s3Service();
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	   	String bucketOption = req.getParameter("bucketOption");
	   	String objectName = req.getParameter("objectName");
	   	String bucketName = req.getParameter("bucketName");
	   	Part filepart = req.getPart("addFile");
	   	String filename = filepart.getSubmittedFileName();
	   	String filepath = "/Users/aashishpokhrel/user-friendly-interface-for-AWS/src/main/webapp/images"+filename;
	   	if (bucketOption.equals("Add")) {
		   	for (Part part : req.getParts()) {
		   		part.write(filepath);
		   	}
	   	}
	   	MessageModelS3 returnmessage = s3Service.s3AddOrDeleteObject(bucketOption, objectName, bucketName, filename, filepath);
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