<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="com.amazonaws.*" %>
<%@ page import="com.amazonaws.auth.*" %>
<%@ page import="com.amazonaws.auth.profile.*" %>
<%@ page import="com.amazonaws.services.ec2.*" %>
<%@ page import="com.amazonaws.services.ec2.model.*" %>
<%@ page import="com.amazonaws.services.s3.*" %>
<%@ page import="com.amazonaws.services.s3.model.*" %>
<%@ page import="com.amazonaws.services.dynamodbv2.*" %>
<%@ page import="com.amazonaws.services.dynamodbv2.model.*" %>
<%@ page import="service.ec2optionsService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="service.s3Service" %>
<%@ page import="service.ec2Service" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>Hello AWS Web World!</title>
    <link rel="stylesheet" href="styles/styles.css" type="text/css" media="screen">
</head>
<body>
<p>
<div>
<form id="region" method="post" >
            <select name="regionName" id="regionName">
            <option value="Choose">Choose region</option>
                   <option value="us-east-1">us-east-1</option>
                   <option value="us-east-2">us-east-2</option>
                   <option value="us-west-1">us-west-1</option>
                   <option value="us-west-2">us-west-2</option>
              </select>
             <input type="submit" value="submit"/>
             </form>
             </div>
        <div class="section grid grid5 s3">
            <h2>Amazon S3 Buckets:</h2>
            
            <ul>
            <% s3Service s3Service = new s3Service(); %>
            <% String regionName = "us-east-2"; %>
            <% List<String> bucketList = new ArrayList<String>(); %>
            <% List<String> objectList = new ArrayList<String>(); %>
            <% bucketList = s3Service.s3ListBuckets(regionName); %>
            <% for (String bucket : bucketList) { %>
            	<li><%= bucket %>
            	<% objectList = s3Service.s3ListObjects(regionName, bucket); %>
            	<ul>
            	<% for (String objectName : objectList) { %>
            		<li><%= objectName %>
            	<% } %>
            	 
            	</ul>
            <% } %>
            </ul>
            
            <div class="section grid grid5 s3">
            <h2>Add or delete objects and buckets:</h2>
            
            <h3>How to use this:</h3>
            
            <ul>
            <li>To add a file inside bucket, choose add as bucket action and provide bucket name and upload file.
            <li>To delete a file inside bucket, choose delete as bucket option and provide file name and bucket name.
            <li>To delete a bucket, delete everything inside it and choose delete as bucket option and provide bucket name.
            </ul>
            
            <form action ="s3options" method="post" id="s3options" enctype="multipart/form-data">
            Bucket Action:
             <select name="bucketOption" id="bucketOption">
             <option value="Choose">Choose an option</option>
                   <option value="Add">Add</option>
                   <option value="Delete">Delete</option>
              </select><br>
             File Name: <input type="text" name="objectName" id="objectName"> <br>
             Bucket Name: <input type="text" name="bucketName" id="BucketName"> <br>
             Upload file: <input type="file" name="addFile" id="addFile">
             <button type="button" id="s3optionsButton">Select</button>
    		 </form>
    		 </div>
           
        </div>
        
        <div class="section grid grid5 gridlast ec2">
        
            <h2>Amazon EC2 Instances:</h2>
          		  
            
            <% ec2Service ec2Service = new ec2Service(); %>
            <% String myRegion = request.getParameter("regionName"); %>
            <% if (myRegion == null) { %>
            <% myRegion = "us-east-2"; %>
            <% } %>
            <% List<Instance> instanceList = new ArrayList<Instance>(); %>
            <% instanceList = ec2Service.listInstance(myRegion); %>
            <table>
            	<tr>
            	<th> Instance Id</th>
            	<th> Instance Key Pair</th>
            	<th> Instance type</th>
            	<th> Instance status</th>
            	</tr>
           	<% for (Instance instance : instanceList) { %>
                <tr>
                <td><%= instance.getInstanceId() %></td>
                <td><%= instance.getKeyName() %></td>
                <td><%= instance.getInstanceType() %>
                <td><%= instance.getState() %></td>
                <tr>
			<% } %>
			</table>
					
           
            
            <div class="section grid grid5 gridlast ec2">
        
            <h2>Amazon EC2 Instances:</h2>
            <form action ="ec2options" method="post" id="ec2options">
            instance_option:
             <select name="optionName" id="optionName">
                   <option value="Stop">Stop</option>
                   <option value="Start">Start</option>
                   <option value="Terminate">Terminate</option>
                   <option value="Reboot">Reboot</option>
              </select><br>
             instance: <input type="text" name="instance_id" id="instance_id"> <br>
             <button type="button" id="ec2optionsButton">Select</button>
    		 </form>
    		 </div>
     
          
        </div>
		<div class="section grid grid5 gridlast ec2">
		<h2>
		<a href="ec2.jsp">Create new ec2</a>
		</h2>
		<h2>
		<a href="s3.jsp">Create new s3 bucket</a>
		</h2>
		<h2>
		<a href="vpc.jsp">Create a new vpc</a>
		</h2>
		</div>
		
		<script type="text/javascript" src="js/jquery.js"></script>
					<script type="text/javascript">
					$("#ec2optionsButton").click(function(){
						selectElement = document.querySelector('#optionName');
						optionName = selectElement.value;
						instance_id = $("#instance_id").val();
						$("#ec2options").submit();
						
					});
					
					$("#s3optionsButton").click(function(){
						selectElementOption = document.querySelector('#bucketOption');
						bucketOption = selectElementOption.value;
						objectName = $("#objectName").val();
						bucketName = $("#bucketName").val();
						$("#s3options").submit();
						
					});
		
					</script>
		
</body>
</html>