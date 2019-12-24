function login(){
	if(document.getElementById("username-input").value=="用户名"){
		document.getElementById("prompt-info").innerHTML="请输入用户名";
		return false;
	}else if(document.getElementById("input1").style.display=="block"){
		document.getElementById("prompt-info").innerHTML="请输入密码";
		return false;
	}else if(document.getElementById("char-input").value=="请输入图片中的字符"){
		document.getElementById("prompt-info").innerHTML="请输入图片中的字符";
		return false;
	}else{
			document.getElementById("prompt-info").innerHTML="";
			return true;
		}
}
function register(){
	if(document.getElementById("register-char-input").value=="请输入图片中的字符"){
		document.getElementById("message").innerHTML="请输入图片中的字符";
		return false;
	}else if(document.getElementById("name-input").value=="请输入用户名"){
		document.getElementById("message").innerHTML="请输入用户名";
		return false;
	}else if(document.getElementById("invite-input").value=="请输入密码"){
		document.getElementById("message").innerHTML="请输入密码";
		return false;
	}else{
		document.getElementById("message").innerHTML="";
		return true;
	}
}
function find(){
	if(document.getElementById("findPwd-char-input").value=="请输入图片中的字符"){
		document.getElementById("news").innerHTML="请输入图片中的字符";
		return false;
	}else if(document.getElementById("findPwd-input").value=="请输入用户名"){
		document.getElementById("news").innerHTML="请输入用户名";
		return false;
	}else{
		document.getElementById("news").innerHTML="";
		return true;
	}
}


	




















