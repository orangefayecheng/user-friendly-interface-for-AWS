<%@page isELIgnored="false"%>
<html>
<head>
     <title> log into the system</title>
</head>
<body>
<div style = "text-align:center">
     <form action ="login" method="post" id="loginForm">
             username: <input type="text" name="uname" id="uname" value ="${returnmessage.message_object.username}"> <br>
             password: <input type="password" name="upwd" id="upwd" value ="${returnmessage.message_object.userpwd}"> <br>
             <span id="msg" style="font-size: 12px;color: red"> ${returnmessage.message}</span> <br>
             <button type="button" id="loginBtn">login</button>
     </form>
</div>
</body>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$("#loginBtn").click(function(){
	var uname = $("#uname").val();
	var upwd = $("#upwd").val();
	if(isNull(uname)){
		$("#msg").html("user name cannot be empty")
		return;
	}
	if(isNull(upwd)){
		$("#msg").html("user password cannot be empty")
		return;
	}
	$("#loginForm").submit();
	
});

function isNull(str){
	if(str == null || str.trim() == ""){
		 return true;
	}
	return false;
}
</script>
</html>