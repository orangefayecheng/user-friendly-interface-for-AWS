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
<%@ page import="service.ec2optionsService" %>

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
            <% for (Bucket bucket : s3.listBuckets()) { %>
            <% String bName = bucket.getName(); %>
               <li> <%= bName %>
               <% String regionNaam = s3.getBucketLocation(bName);
               String myRegionName = null;
               if (regionNaam == "US") {
               		 myRegionName = "us-east-1";
               }
               else {
               		myRegionName = regionNaam;
               }
               s3Second = AmazonS3ClientBuilder.standard().withCredentials(credentialsProvider).withRegion(myRegionName).build();
               ObjectListing objectListing = s3Second.listObjects(new ListObjectsRequest().withBucketName(bName));
               for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) { %>
               <ul><li><%=	objectSummary.getKey() %></li></ul>
               <%} %>
               <!-- <form action ="s3options" method="post" id="s3opt"></form>
               <select name="s3bucket" id="s3bucket">
               <option value="Add">Add</option>
               <option value="Start">Start</option>
               <option value="Terminate">Terminate</option>
               <option value="Reboot">Reboot</option>
               </select>
               <button type="button" id="s3options">Select</button> -->
            <% } %>
            </ul>
        </div>
        
        <div class="section grid grid5 gridlast ec2">
        
            <h2>Amazon EC2 Instances:</h2>
          		  
            <ul>
            <% for (Reservation reservation : ec2.describeInstances().getReservations()) { %>
                <% for (Instance instance : reservation.getInstances()) { %>
                <% String insId = instance.getInstanceId(); %>
                   <li><%= insId %>
                   <% request.getSession().setAttribute("instance_id", insId); %>
                   <% RequestDispatcher dispatcher = request.getRequestDispatcher("/ec2options"); %>
                   <form action ="ec2options" method="post" id="ec2opt"></form>
                   <select name="instance" id="instance">
                   <option value="Stop">Stop</option>
                   <option value="Start">Start</option>
                   <option value="Terminate">Terminate</option>
                   <option value="Reboot">Reboot</option>
                   </select>
                   <button type="button" id="ec2optionsButton">Select</button>
                   <script type="text/javascript" src="js/jquery.js"></script>
					<script type="text/javascript">
					$("#ec2optionsButton").click(function(){
						selectElement = document.querySelector('#instance');
						optionName = selectElement.value;
						<%-- instance_id = "<%=insId%>"; --%>
						$("#ec2opt").submit();
						
					});
		
					</script>
					</li>
                <% } %>
            <% } %>
            </ul>
          
        </div>
		<div class="section grid grid5 gridlast ec2">
		<h2>
		<a href="ec2.jsp">Create new ec2</a>
		</h2>
		<h3>
		<a href="s3.jsp">Create new s3 bucket</a>
		</h3>
		</div>
		
</body>
</html>