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
    <title>S3 bucket created</title>
    <link rel="stylesheet" href="styles/styles.css" type="text/css" media="screen">
</head>
<body>
	<div class="section grid grid5 gridlast ec2">
            <h2>Amazon EC2 Instances:</h2>
            <form action ="s3" method="post" id="s3bucket">
             bucketName: <input type="text" name="bucketName" id="bucketName"> <br>
             <span id="msg" style="font-size: 12px;color: red"> ${returnmessage.message}</span> <br>
             <button type="button" id="createS3button">Create S3 bucket</button>
     </form>
        </div>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$("#createS3button").click(function(){
	var bucketName = $("#bucketName").val();
	$("#s3bucket").submit();
	
});

</script>
          		  
</body>
</html>