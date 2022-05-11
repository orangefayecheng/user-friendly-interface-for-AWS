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
    <title>S3 bucket creation</title>
    <link rel="stylesheet" href="styles/styles.css" type="text/css" media="screen">
</head>
<body>
	<div class="section grid grid5 gridlast ec2">
            <h2>Create a S3 bucket:</h2>
            <form action ="s3" method="post" id="s3bucket">
             bucketName: <input type="text" name="bucketName" id="bucketName"> <br>
             regionName:
             <select name="regionName" id="regionName">
            <option value="Choose">Choose region</option>
                   <option value="us-east-1">us-east-1</option>
                   <option value="us-east-2">us-east-2</option>
                   <option value="us-west-1">us-west-1</option>
                   <option value="us-west-2">us-west-2</option>
              </select><br>
             <span id="msg" style="font-size: 12px;color: red"> </span> <br>
             <button type="button" id="createS3button">Create S3 bucket</button>
     </form>
        </div>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$("#createS3button").click(function(){
	selectRegion = document.querySelector('#regionName');
	regionName = selectRegion.value;
	bucketName = $("#bucketName").val();
	$("#s3bucket").submit();
	
});

</script>
          		  
</body>
</html>