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

<%! // Share the client objects across threads to
    // avoid creating new clients for each web request
    private AmazonEC2         ec2;
    private AmazonS3           s3;
    private AmazonS3	 s3Second;
    private AmazonDynamoDB dynamo;
 %>

<%
        AWSCredentialsProviderChain credentialsProvider = new AWSCredentialsProviderChain(
            new InstanceProfileCredentialsProvider(),
            new ProfileCredentialsProvider("default"));
        ec2    = AmazonEC2ClientBuilder.standard().withCredentials(credentialsProvider).withRegion("us-east-2").build();
        s3     = AmazonS3ClientBuilder.standard().withCredentials(credentialsProvider).withRegion("us-east-2").build();
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>Hello AWS Web World!</title>
    <link rel="stylesheet" href="styles/styles.css" type="text/css" media="screen">
</head>
<body>
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
            
            <form action ="s3options" method="post" id="s3options" enctype="multipart/form-data">
            bucket_option:
             <select name="bucketOption" id="bucketOption">
                   <option value="Add">Add</option>
                   <option value="Delete">Delete</option>
              </select></br>
             objectName: <input type="text" name="objectName" id="objectName"> <br>
             bucketName: <input type="text" name="bucketName" id="BucketName"> <br>
             addFile: <input type="file" name="addFile" id="addFile">
             <button type="button" id="s3optionsButton">Select</button>
    		 </form>
           
        </div>
        
        <div class="section grid grid5 gridlast ec2">
        
            <h2>Amazon EC2 Instances:</h2>
          		  
            <ul>
            <% for (Reservation reservation : ec2.describeInstances().getReservations()) { %>
                <% for (Instance instance : reservation.getInstances()) { %>
                <% String insId = instance.getInstanceId(); %>
                   <li><%= insId %>
				<% } %>
			<% } %>
					
            </ul>
            
            <form action ="ec2options" method="post" id="ec2options">
            instance_option:
             <select name="optionName" id="optionName">
                   <option value="Stop">Stop</option>
                   <option value="Start">Start</option>
                   <option value="Terminate">Terminate</option>
                   <option value="Reboot">Reboot</option>
              </select></br>
             instance: <input type="text" name="instance_id" id="instance_id"> <br>
             <button type="button" id="ec2optionsButton">Select</button>
    		 </form>
     
          
        </div>
		<div class="section grid grid5 gridlast ec2">
		<h2>
		<a href="ec2.jsp">Create new ec2</a>
		</h2>
		<h3>
		<a href="s3.jsp">Create new s3 bucket</a>
		</h3>
		<h3>
		<a href="vpc.jsp">Create a new vpc</a>
		</h3>
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