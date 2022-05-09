<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="com.amazonaws.*" %>
<%@ page import="com.amazonaws.auth.*" %>
<%@ page import="com.amazonaws.auth.profile.*" %>
<%@ page import="com.amazonaws.services.s3.*" %>
<%@ page import="com.amazonaws.services.s3.model.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>VPC Peering created</title>
    <link rel="stylesheet" href="styles/styles.css" type="text/css" media="screen">
</head>
<body>
	<div class="section grid grid5 gridlast ec2">
            <h2>VPC peering connection created</h2>
            
            <div class="section grid grid5 s3">
            <h2>Amazon VPC Peering Connection:</h2>
            
            <form action ="ec2" method="post" id="vpcPeering">
             peerId: <input type="text" name="peerId" id="peerId"> <br>
             peerRegion: <input type="text" name="peerRegion" id="peerRegion"> <br>
             vpcId: <input type="text" name="vpcId" id="vpcId"> <br>
             <button type="button" id="createVpcPeeringButton">Create vpc peering</button>
             </form>
            </div>
            
        </div>
        <script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
        $("#createVpcPeeringbutton").click(function(){
	peerId = $("#peerId").val();
	peerRegion = $("#peerRegion").val();
	vpcId = $("#vpcId").val();
	$("#vpcPeering").submit();
	
});
        
        </script>
          		  
</body>
</html>