<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="com.amazonaws.*" %>
<%@ page import="com.amazonaws.auth.*" %>
<%@ page import="com.amazonaws.auth.profile.*" %>
<%@ page import="com.amazonaws.services.ec2.*" %>
<%@ page import="com.amazonaws.services.ec2.model.*" %>
<%@ page import="service.ec2Service" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

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
            <form action ="ec2" method="post" id="ec2instance">
             instance_type:
             <select name="instance_type" id="instance_type">
                   <option value="t2.micro">t2.micro</option>
                   <option value="t2.mini">t2.mini</option>
                   <option value="c2.micro">c2.micro</option>
                   <option value="c2.large">c2.large</option>
              </select><br>
             key_name: <input type="text" name="key_name" id="key_name"> <br>
             security_group: <input type="text" name="security_group" id="security_group"> <br>
             regionName: <input type="text" name="regionName" id="regionName"> <br>
             amiId: <input type="text" name="amiId" id="amiId"> <br>
             <button type="button" id="createEC2button">Create EC2 instance</button>
     </form>
        </div>
        
        <div class="section grid grid5 gridlast ec2">
        <h2>KeyPair list:</h2>
        <form id="region" method="get" >
            <select name="regionName" id="regionName">
            	   <option value="choose">Choose region</option>
                   <option value="us-east-1">us-east-1</option>
                   <option value="us-east-2">us-east-2</option>
              </select>
             <input type="submit" value="submit"/>
             </form>
        <ul>
        <% ec2Service ec2Service = new ec2Service(); %>
        <% String regionName = request.getParameter("regionName"); %>
            <% if (regionName == null) { %>
            <% regionName = "us-east-2"; %>
        <% } %>
        <% List<KeyPairInfo> keyPairList = new ArrayList<KeyPairInfo>(); %>
        <% keyPairList = ec2Service.listKeyPair(regionName); %>
        <% for (KeyPairInfo key_pair : keyPairList) { %>
        	<li><%= key_pair.getKeyName() %>
        	<% } %>
        </ul>
        </div>
        
        <div class="section grid grid5 gridlast ec2">
        <h2>Create or delete keyPair</h2>
        <form action ="ec2KeyPair" method="post" id="ec2KeyPair">
        keyOption:
             <select name="keyOption" id="keyOption">
                   <option value="Create">Create</option>
                   <option value="Delete">Delete</option>
              </select><br>
             keyPairName: <input type="text" name="keyPairName" id="keyPairName"> <br>
             regionName: <input type="text" name="keyRegion" id="keyRegion"> <br>
             <button type="button" id="createKeyPairbutton">Submit</button>
             </form>
        </div>
        
        <div>
        <a href="index.jsp">Home</a>
        </div>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$("#createEC2button").click(function(){
	selectElement = document.querySelector('#instance_type');
	instance_type = selectElement.value;
	key_name = $("#key_name").val();
	security_group = $("#security_group").val();
	regionName = $("#regionName").val();
	amiId = $("#amiId").val();
	$("#ec2instance").submit();
	
});

$("#createKeyPairbutton").click(function() {
	selectElementKeyOption = document.querySelector('#keyOption');
	keyOption = selectElementKeyOption.value;
	keyPairName = $("#keyPairName").val();
	keyRegion = $("#keyRegion").val();
	$("#ec2KeyPair").submit();
});

</script>
        		  
</body>
</html>