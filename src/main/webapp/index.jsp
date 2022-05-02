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

<%! // Share the client objects across threads to
    // avoid creating new clients for each web request
    private AmazonEC2         ec2;
    private AmazonS3           s3;
    private AmazonDynamoDB dynamo;
 %>

<%
    if (ec2 == null) {
        AWSCredentialsProviderChain credentialsProvider = new AWSCredentialsProviderChain(
            new InstanceProfileCredentialsProvider(),
            new ProfileCredentialsProvider("default"));
        ec2    = AmazonEC2ClientBuilder.standard().withCredentials(credentialsProvider).withRegion("us-east-2").build();
        s3     = new AmazonS3Client(credentialsProvider);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>Hello AWS Web World!</title>
    <link rel="stylesheet" href="styles/styles.css" type="text/css" media="screen">
</head>
<body>
<p>
        <div class="section grid grid5 s3">
            <h2>Amazon S3 Buckets:</h2>
            <ul>
            <% for (Bucket bucket : s3.listBuckets()) { %>
               <li> <%= bucket.getName() %> </li>
            <% } %>
            </ul>
        </div>
        </p>

		<p>
        <div class="section grid grid5 gridlast ec2">
            <h2>Amazon EC2 Instances:</h2>
          		  
            <ul>
            <% for (Reservation reservation : ec2.describeInstances().getReservations()) { %>
                <% for (Instance instance : reservation.getInstances()) { %>
                   <li><%= instance.getInstanceId() %>
                   <form action ="ec2options" method="post" id="ec2opt"></form>
                   <select name="instance" id="instance">
                   <option value="Stop">Stop</option>
                   <option value="Start">Start</option>
                   <option value="Terminate">Terminate</option>
                   <option value="Reboot">Reboot</option>
                   </select>
                   <button type="button" id="ec2options">Select</button>
                   <script type="text/javascript" src="js/jquery.js"></script>
					<script type="text/javascript">
					$("#ec2options").click(function(){
						var option = document.querySelector("#instance");
						var optionName = option.options[option.selectedIndex].value;
						var instance_id = "<%=instance.getInstanceId()%>";
						$("#ec2opt").submit();
						
					});
		
					</script>
                <% } %>
            <% } %>
            </ul>
          
        </div>
		<div class="section grid grid5 gridlast ec2">
		<h2>
		<a href="ec2.jsp">Create new ec2</a>
		</h2>
		<h2>
		<a href="s3.jsp">Create new s3 bucket</a>
		</h2>
		</div>
		
</body>
</html>