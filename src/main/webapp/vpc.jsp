<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="com.amazonaws.*" %>
<%@ page import="com.amazonaws.auth.*" %>
<%@ page import="com.amazonaws.auth.profile.*" %>
<%@ page import="com.amazonaws.services.ec2.*" %>
<%@ page import="com.amazonaws.services.ec2.model.*" %>

<%! // Share the client objects across threads to
    // avoid creating new clients for each web request
    private AmazonEC2         ec2;
 %>

<%
        AWSCredentialsProviderChain credentialsProvider = new AWSCredentialsProviderChain(
            new InstanceProfileCredentialsProvider(),
            new ProfileCredentialsProvider("default"));
        ec2    = AmazonEC2ClientBuilder.standard().withCredentials(credentialsProvider).withRegion("us-east-2").build();
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>VPC created</title>
    <link rel="stylesheet" href="styles/styles.css" type="text/css" media="screen">
</head>
<body>
	<div class="section grid grid5 s3">
            <h2>Amazon VPCs:</h2>
            <ul></ul>
            <% DescribeVpcsResult result = ec2.describeVpcs(); %>
            <% for (Vpc vpc : result.getVpcs()) { %>
            <li><%=vpc.getVpcId() %></li>
            <ul><li><%=vpc.getCidrBlock() %></ul>
            <% } %>
            </ul>
            </div>
	<div class="section grid grid5 gridlast ec2">
            <h2>VPC</h2>
            <form action ="vpc" method="post" id="createVPC">
            regionName: <input type="text" name="regionName" id="regionName"> <br>
             cidrblock: <input type="text" name="cidrBlock" id="cidrBlock"> <br>
             <span id="msg" style="font-size: 12px;color: red"> ${returnmessage.message}</span> <br>
             <button type="button" id="createVPCbutton">Create VPC</button>
     </form>
        </div>
        
     <!-- <div class="section grid grid5 s3">
            <h2>Amazon VPC Peering Connection:</h2>
            
            <form action ="ec2" method="post" id="vpcPeering">
             peerId: <input type="text" name="peerId" id="peerId"> <br>
             peerRegion: <input type="text" name="peerRegion" id="peerRegion"> <br>
             vpcId: <input type="text" name="vpcId" id="vpcId"> <br>
             <button type="button" id="createVpcPeeringButton">Create vpc peering</button>
            </div> -->

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$("#createVPCbutton").click(function(){
	cidrBlock = $("#cidrBlock").val();
	regionName = $("#regionName").val();
	$("#createVPC").submit();
	
});

/* $("#createVpcPeeringbutton").click(function(){
	peerId = $("#peerId").val();
	peerRegion = $("#peerRegion").val();
	vpcId = $("#vpcId").val();
	$("#vpcPeering").submit();
	
}); */

</script>
        		  
</body>
</html>