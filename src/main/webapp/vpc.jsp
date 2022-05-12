<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="com.amazonaws.*" %>
<%@ page import="com.amazonaws.auth.*" %>
<%@ page import="com.amazonaws.auth.profile.*" %>
<%@ page import="com.amazonaws.services.ec2.*" %>
<%@ page import="com.amazonaws.services.ec2.model.*" %>
<%@ page import="service.vpcService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>VPC created</title>
    <link rel="stylesheet" href="styles/styles.css" type="text/css" media="screen">
</head>
<body>
<div>
<p>
<form id="region" method="get" >
            <select name="regionName" id="regionName">
            	<option value="choose">Choose region</option>
                   <option value="us-east-1">us-east-1</option>
                   <option value="us-east-2">us-east-2</option>
                   <option value="us-west-1">us-west-1</option>
                   <option value="us-west-2">us-west-2</option>
              </select>
             <input type="submit" value="submit"/>
             </form>
</div>
	<div class="section grid grid5 s3">
            <h2>Amazon VPCs list:</h2>
            
            <ul>
            <% vpcService vpcService = new vpcService(); %>
            <% String regionName = request.getParameter("regionName"); %>
            <% if (regionName == null) { %>
            <% regionName = "us-east-2"; %>
            <% } %>
            <% List<Vpc> vpcList = new ArrayList<Vpc>(); %>
            <% vpcList = vpcService.listVpcs(regionName); %>
            <% for (Vpc vpc : vpcList) { %>
            	<li><%= vpc.getVpcId() %>
            	<ul>
	            <li><%= vpc.getCidrBlock() %></li>
	            </ul>
	        <% } %>
            </ul>
            </div>
	<div class="section grid grid5 gridlast ec2">
            <h2>VPC Create/Delete</h2>
            <form action ="vpc" method="post" id="createVPC">
            VPC Action:
             <select name="vpcOption" id="vpcOption">
             <option value="Choose">Choose an option</option>
                   <option value="Add">Add</option>
                   <option value="Delete">Delete</option>
              </select><br>
            Region: 
            <select name="vpcRegion" id="vpcRegion">
            <option value="Choose">Choose region</option>
                   <option value="us-east-1">us-east-1</option>
                   <option value="us-east-2">us-east-2</option>
                   <option value="us-west-1">us-west-1</option>
                   <option value="us-west-2">us-west-2</option>
              </select><br>
             Cidr Block: <input type="text" placeholder="10.0.0.0/24" name="cidrBlock" id="cidrBlock"> <br>
             VPC Id: <input type="text" name="vpcIdDel" id="vpcIdDel"> <br>
             <button type="button" id="createVPCbutton">Submit</button>
     </form>
        </div>
        
     <div class="section grid grid5 s3">
            <h2>Amazon VPC Peering Connection:</h2>
            
            <form action ="vpcPeering" method="post" id="vpcPeering">
             Accepter VPC Id: <input type="text" name="peerId" id="peerId"> <br>
             Accepter VPC Region: <input type="text" name="peerRegion" id="peerRegion"> <br>
             Requester VPC Id: <input type="text" name="vpcId" id="vpcId"> <br>
             <button type="button" id="createVpcPeeringButton">Create vpc peering</button>
             </form>
            </div>
            
            <div>
        <a href="index.jsp">Home</a>
        </div>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$("#createVPCbutton").click(function(){
	cidrBlock = $("#cidrBlock").val();
	selectRegion = document.querySelector('#vpcRegion');
	vpcRegion = selectRegion.value;
	selectOption = document.querySelector('#vpcOption');
	vpcOption = selectOption.value;
	vpcIdDel = $("#vpcIdDel").val();
	$("#createVPC").submit();
	
});

$("#createVpcPeeringButton").click(function(){
	peerId = $("#peerId").val();
	peerRegion = $("#peerRegion").val();
	vpcId = $("#vpcId").val();
	$("#vpcPeering").submit();
	
}); 

</script>
        		  
</body>
</html>