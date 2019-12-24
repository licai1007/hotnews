<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/res/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#bindMail').click(function(){
			var email = $.trim(prompt('请输入您的邮箱',''));
			if('' == email){
				return false;
			}
			var reg=new RegExp('^([a-zA-Z0-9]+)@([a-zA-Z0-9]+)\.([a-z]{2,})$');
			if(!reg.test(email)){
				alert('您输入的邮箱格式不正确！');
				return false;
			}
			var judgePage = $('#hn').val();
			window.location.href="/hotnews/user/bindMail_"+judgePage+"?email="+email+"&nid=${news.newsid}";
			return true;
		});
		$('#weixin').click(function(){
			window.location.href = "${pageContext.request.contextPath}/user/wechatSaoma?position=${flag}";
		});
	});
</script>
<div class="header">
<div class="header-logo">
<div class="logo">
<a href="#">
<img src="${pageContext.request.contextPath}/res/images/logo.png" title="澎湃新闻" width="116" height="68"/>
</a>
</div>
</div>
<div class="top-nav">
	<div class="nav"><a href="${pageContext.request.contextPath }/index">主<br/>页</a></div>
	<div class="nav"><a href="#">时<br/>事</a></div>
	<div class="nav"><a href="${pageContext.request.contextPath }/video">视<br/>频</a></div>
	<div class="nav"><a href="#">思<br/>想</a></div>
	<div class="nav"><a href="#">生<br/>活</a></div>
	<div class="nav"><a href="#">订<br/>阅</a></div>
	<div class="nav"><a href="#">问<br/>吧</a></div>
</div>
<div class="top-right">
	<c:choose>
		<c:when test="${empty user }">
	<div class="right-info">
		<div class="infos"><a href="#">报<br/>料</a></div>
		<div class="infos" id="reg">
		<a href="#" onclick="document.getElementById('register').style.display='block'">
		注<br/>册
		</a>
		</div>
		<div class="infos">
		<a href="#" onclick="document.getElementById('login').style.display='block'">
		登<br/>录
		</a>
		</div>
	</div>
		</c:when>
		<c:otherwise>
	<div class="rightLogin-info">
	<ul style="margin-top:1px;">
		<li>
			<a href="#">消<br/>息</a>
		</li>
		<li>
			<a href="#">话<br/>题</a>
		</li>
		<li>
			<a href="#">收<br/>藏</a>
		</li>
		<li>
			<a href="#">跟<br/>踪</a>
		</li>
	</ul>
	<a class="loginImg" href="#" 
	onmouseover="document.getElementById('loginDown').style.display='block'">
		<c:choose>
			<c:when test="${empty user.headimgurl}">
				<img src="${pageContext.request.contextPath}/res/images/login.jpg" style="width:30px;height:30px;"/>
			</c:when>
			<c:otherwise>
				<img src="${user.headimgurl}" style="width:30px;height:30px;"/>
			</c:otherwise>
		</c:choose>
	</a>
	<div id="loginDown" class="loginDown" style="display:none;overflow:hidden;margin-top:30px;"
	onmouseout="document.getElementById('loginDown').style.display='none'">
		<div>
			<c:choose>
				<c:when test="${user.username!='微信用户'}">
					${user.username}
				</c:when>
				<c:otherwise>
					${user.nickname}
				</c:otherwise>
			</c:choose>
		</div>
		<div>
			<a href="#">我的动态</a>
			<a id="bindMail" href="#">绑定邮箱</a>
			<a href="${pageContext.request.contextPath }/user/logout_<%=pageContext.getAttribute("flag")%>?nid=${news.newsid}">退出登录</a>
		</div>
	</div>
	
	</div>
	</c:otherwise>
	</c:choose>
</div>
<c:choose>
<c:when test="${!empty fieldErrors.loginError.get(0)}">
	<div class="login" id="login" style="display:block;">
</c:when>
<c:when test="${!empty notAdmin}">
	<div class="login" id="login" style="display:block;">
</c:when>
<c:otherwise>
	<div class="login" id="login">
</c:otherwise>
</c:choose>
<div class="title">登录</div>
<div class="close" onclick="document.getElementById('login').style.display='none'
;document.getElementById('username-input').value='用户名';
document.getElementById('input2').style.display='none';
document.getElementById('input1').style.display='block';
document.getElementById('char-input').value='请输入图片中的字符';
document.getElementById('prompt-info').innerHTML='';"></div>
<div class="prompt">澎友先来登录吧！</div>
<div class="login-input">
<span>社交账号直接登录</span>
<%! public void qq(int i){
		try{
			i++;
			if(i == 2){
			Runtime.getRuntime().exec("C:\\Program Files\\TENCENT\\QQ\\Bin\\QQScLauncher.exe");
			}
		}catch(Exception e){
			
		}
	}
 %>
<a href="#"><img src="${pageContext.request.contextPath}/res/images/renren.png" width="32" height="32"/></a>
<a href='<%qq(0);%>'>
<img src="${pageContext.request.contextPath}/res/images/qq.png" width="32" height="32"/></a>
<a href="${pageContext.request.contextPath}/user/weiboLogin"><img src="${pageContext.request.contextPath}/res/images/weibo.png" width="32" height="32"/></a>
<a id="weixin" href="#"><img src="${pageContext.request.contextPath}/res/images/weixin.png" width="32" height="32"/></a>
</div>
<form action="${pageContext.request.contextPath}/user/login_<%=pageContext.getAttribute("flag") %>?nid=${news.newsid}" method="post" onsubmit="return login();">
	<div class="username">
	<input class="username-input" id="username-input" type="text" name="username" value="用户名" onfocus="if(value=='用户名'){value=''}" onblur="if(value==''){value='用户名'}"/>
	</div>
	<div class="pwd">
	<input id="input1" class="pwd-input" type="text" style="display:block;" value="密码" onfocus="document.getElementById('input1').style.display='none';
document.getElementById('input2').style.display='block';
document.getElementById('input2').focus();"/>
	<input id="input2" class="pwd-input" type="password" name="password" onfocus="value=''" onblur="if(value==''){document.getElementById('input2').style.display='none';
document.getElementById('input1').style.display='block';}" style="display:none;"/>
	</div>
	<div class="char">
	<input class="char-input" id="char-input" type="text" name="code" 
	value="请输入图片中的字符" onfocus="if(value=='请输入图片中的字符'){value=''}" onblur="if(value==''){value='请输入图片中的字符'}"/>
	<img src="${pageContext.request.contextPath}/user/code" onclick="this.src='${pageContext.request.contextPath}/user/code?d='+new Date()" title="换一张" style="vertical-align:middle;margin-left:30px;"/>
	</div>
	<div class="prompt-info" id="prompt-info">
		<s:fielderror fieldName="loginError"></s:fielderror>
	</div>
	<div class="enter">
	<input class="enter-input" type="submit" value="登录"/>
	</div>
	<div style="margin-top:7px;">
	<span style="cursor:pointer;margin-left:30px;" 
	onclick="document.getElementById('login').style.display='none';
		document.getElementById('findPwd').style.display='block';">忘记了密码？</span>
	<span style="cursor:pointer;margin-left:200px;" onclick="document.getElementById('login').style.display='none';
document.getElementById('register').style.display='block';
document.getElementById('username-input').value='用户名';
document.getElementById('input2').style.display='none';
document.getElementById('input1').style.display='block';
document.getElementById('char-input').value='请输入图片中的字符';
document.getElementById('prompt-info').innerHTML='';">注册</span>
	</div>
	<input id="hn" type="hidden" value="<%=pageContext.getAttribute("flag")%>"/>
</form>
</div>
<c:choose>
	<c:when test="${empty fieldErrors.findError.get(0)}">
		<div class="findPwd" id="findPwd">
	</c:when>
	<c:otherwise>
		<div class="findPwd" id="findPwd" style="display:block;">
	</c:otherwise>
</c:choose>
	<div class="findPwd-title">通过邮箱找回密码</div>
	<div class="findPwd-close" onclick="document.getElementById('findPwd').style.display='none';
		document.getElementById('findPwd-char-input').value='请输入图片中的字符';
		document.getElementById('findPwd-input').value='请输入用户名';
		document.getElementById('news').innerHTML='';"></div>
	<div class="findPwd-prompt">请填写以下信息</div>
	<form action="${pageContext.request.contextPath}/user/findPwd_<%=pageContext.getAttribute("flag") %>?nid=${news.newsid}" method="post" onsubmit="return find();">
		<div class="findPwd-char">
		<input id="findPwd-char-input" class="findPwd-char-input" type="text" name="code" value="请输入图片中的字符" onfocus="if(value=='请输入图片中的字符'){value=''}" onblur="if(value==''){value='请输入图片中的字符'}"/>
		<img src="${pageContext.request.contextPath}/user/code" onclick="this.src='${pageContext.request.contextPath}/user/code?d='+new Date()" title="换一张" style="margin-left:30px;vertical-align:middle;" />
		</div>
		<div class="findPwd-name">
			<input id="findPwd-input" class="findPwd-input" type="text" name="username" value="请输入用户名" onfocus="if(value=='请输入用户名'){value=''}" onblur="if(value==''){value='请输入用户名'}"/>
		</div>
		<div id="news" class="message">
			<s:fielderror fieldName="findError"></s:fielderror>
		</div>
		<div class="tijiao">
			<input class="tijiao-input" type="submit" value="提交"/>
		</div>
	</form>
</div>
<c:choose>
<c:when test="${empty fieldErrors.registerError.get(0)}">
	<div class="register" id="register">
</c:when>
<c:otherwise>
	<div class="register" id="register" style="display:block;">
</c:otherwise>
</c:choose>
<div class="register-title">注册</div>	
<div class="register-prompt">请填写以下注册信息</div>
<form action="${pageContext.request.contextPath }/user/register_<%=pageContext.getAttribute("flag") %>?nid=${news.newsid}" onsubmit="return register();" method="post">
<div class="register-char">
<input id="register-char-input" class="register-char-input" type="text" name="code" value="请输入图片中的字符" onfocus="if(value=='请输入图片中的字符'){value=''}" onblur="if(value==''){value='请输入图片中的字符'}"/>
<img src="${pageContext.request.contextPath}/user/code" onclick="this.src='${pageContext.request.contextPath}/user/code?d='+new Date()" title="换一张" style="margin-left:30px;vertical-align:middle;" />
</div>
<div class="name">
<c:choose>
<c:when test="${empty fieldErrors.registerError.get(0)}">
	<input id="name-input" class="name-input" type="text" name="username" value="请输入用户名" onfocus="if(value=='请输入用户名'){value=''}" onblur="if(value==''){value='请输入用户名'}"/>
</c:when>
<c:otherwise>
	<input id="name-input" class="name-input" type="text" name="username" value="${username }" onfocus="if(value=='请输入用户名'){value=''}" onblur="if(value==''){value='请输入用户名'}"/>
</c:otherwise>
</c:choose>
</div>
<div class="invite">
<c:choose>
<c:when test="${empty fieldErrors.registerError.get(0)}">
	<input id="invite-input" class="invite-input" type="text" name="password" value="请输入密码" onfocus="if(value=='请输入密码'){value=''}" onblur="if(value==''){value='请输入密码'}"/>
</c:when>
<c:otherwise>
	<input id="invite-input" class="invite-input" type="text" name="password" value="${password }" onfocus="if(value=='请输入密码'){value=''}" onblur="if(value==''){value='请输入密码'}"/>
</c:otherwise>
</c:choose>
</div>
<div id="message" class="message">
	<s:fielderror fieldName="registerError"></s:fielderror>
</div>
<div class="zc">
<input class="zc-input" type="submit" value="注册"/>
</div>
<div style="margin-top:10px;">
<span style="cursor:pointer;margin-left:330px;"  onclick="document.getElementById('register').style.display='none';
document.getElementById('login').style.display='block';
document.getElementById('register-char-input').value='请输入图片中的字符';
document.getElementById('name-input').value='请输入用户名';
document.getElementById('invite-input').value='请输入密码';
document.getElementById('message').innerHTML='';">登录</span>
</div>
</form>
<div class="register-close" onclick="document.getElementById('register').style.display='none';
document.getElementById('register-char-input').value='请输入图片中的字符';
document.getElementById('name-input').value='请输入用户名';
document.getElementById('invite-input').value='请输入密码';
document.getElementById('message').innerHTML='';"></div>
</div>
</div>