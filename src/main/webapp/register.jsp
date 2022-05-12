<%@page isELIgnored="false"%>
<html>
<head>
     <title> Register a user account</title>
     <link rel="stylesheet" href="styles/style.css" type="text/css" media="screen">
</head>
<body>
<div class="login">
     <form action ="register" method="post" id="registerForm">
             username: <input type="text" name="uname" id="uname" value ="${returnmessage.message_object.username}"> <br>
             password: <input type="password" name="upwd" id="upwd" value ="${returnmessage.message_object.userpwd}"> <br>
             repeatpassword:  <input type="password" name="rpwd" id="rpwd" value ="${returnmessage.message_object.userpwd}"> <br>
             usermasterkey: <input type="text" name="umaster" id="umaster" value ="${returnmessage.message_object.usermasterkey}"> <br>
             useraccesskey: <input type="text" name="uaccess" id="uaccess" value ="${returnmessage.message_object.useraccesskey}"> <br>
             <span id="msg" style="font-size: 12px;color: red"> ${returnmessage.message}</span> <br>
             <button type="button" id="RegisterBtn">Register</button>
     </form>
     
     <a href="login.jsp">Go back to login</a>
</div>
</body>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$("#RegisterBtn").click(function(){
	var uname = $("#uname").val();
	var upwd = $("#upwd").val();
	var rpwd = $("#rpwd").val();
	var umaster =  $("#umaster").val();
	var uaccess = $("#uaccess").val();
	if(isNull(uname)){
		$("#msg").html("user name cannot be empty")
		return;
	}
	if(isNull(upwd)){
		$("#msg").html("user password cannot be empty")
		return;
	}
	if(isNull(rpwd)){
		$("#msg").html("You need to input repeat password")
		return;
	}
	if(isNull(umaster)){
		$("#msg").html("You need to input a master key")
		return;
	}
	if(isNull(uaccess)){
		$("#msg").html("You need to input a access key")
		return;
	}
	if(rpwd != upwd){
		$("#msg").html("The password does not match")
		return;
	}
	$("#registerForm").submit();
	
});

function isNull(str){
	if(str == null || str.trim() == ""){
		 return true;
	}
	return false;
}
</script>
</html>