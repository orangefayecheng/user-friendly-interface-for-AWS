<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="com.amazonaws.*" %>
<%@ page import="com.amazonaws.auth.*" %>
<%@ page import="com.amazonaws.auth.profile.*" %>
<%@ page import="com.amazonaws.services.ec2.*" %>
<%@ page import="com.amazonaws.services.ec2.model.*" %>

<%! private AmazonEC2 ec2; %>
<%
    if (ec2 == null) {
        AWSCredentialsProviderChain credentialsProvider = new AWSCredentialsProviderChain(
            new InstanceProfileCredentialsProvider(),
            new ProfileCredentialsProvider("default"));

        ec2    = AmazonEC2ClientBuilder.standard().withCredentials(credentialsProvider).withRegion("us-east-2").build();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>EC2 instance created</title>
    <link rel="stylesheet" href="styles/styles.css" type="text/css" media="screen">
</head>
<body>
	<div class="section grid grid5 gridlast ec2">
            <h2>Amazon EC2 Instances:</h2>
            <% RunInstancesRequest runInstancesRequest = new RunInstancesRequest()
          		  .withImageId("ami-0489c6c0a2c0b6281")
          		  .withInstanceType("t2.micro") 
          		  .withKeyName("assignment2") 
          		  .withMinCount(1)
          		  .withMaxCount(1)
          		  .withSecurityGroups("default"); 
            ec2.runInstances(runInstancesRequest);
            String myInstanceId = ec2.runInstances(runInstancesRequest).getReservation().getInstances().get(0).getInstanceId();
            System.out.println("Successfully created " + myInstanceId + " instance");	  
          		  %>
          		  <h3>Created instance:
          		  <%= myInstanceId %>
          		  </h3>
        </div>
          		  
</body>
</html>