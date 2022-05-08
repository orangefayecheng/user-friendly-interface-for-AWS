<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="com.amazonaws.*" %>
<%@ page import="com.amazonaws.auth.*" %>
<%@ page import="com.amazonaws.auth.profile.*" %>
<%@ page import="com.amazonaws.services.ec2.*" %>
<%@ page import="com.amazonaws.services.ec2.model.*" %>

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
              </select></br>
             key_name: <input type="text" name="key_name" id="key_name"> <br>
             security_group: <input type="text" name="security_group" id="security_group"> <br>
             regionName: <input type="text" name="regionName" id="regionName"> <br>
             <span id="msg" style="font-size: 12px;color: red"> ${returnmessage.message}</span> <br>
             <button type="button" id="createEC2button">Create EC2 instance</button>
     </form>
        </div>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$("#createEC2button").click(function(){
	selectElement = document.querySelector('#instance_type');
	instance_type = selectElement.value;
	key_name = $("#key_name").val();
	security_group = $("#security_group").val();
	regionName = $("#regionName").val();
	$("#ec2instance").submit();
	
});

</script>
        		  
</body>
</html>