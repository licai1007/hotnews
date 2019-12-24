<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>微信登录</title>
</head>
<body bgcolor="#333333">
<div style="border:none;position:absolute;width:230px;height:260px;left:500px;top:60px;">
	<div style="height:30px;text-align:center;">
		<font color="white"><strong>微信登录</strong></font>
	</div>
	<div  id="qrcode" style="border:10px solid #fff;" title=""></div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/res/js/jquery-1.8.2.min.js"></script>
<!-- 该js是微信官方所提供的一个用来生成二维码的一个js文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/res/js/qrcode.js"></script>
<script type="text/javascript">
	var qrCode = new QRCode('qrcode',{
		width: 210,
		height: 210
	});
	qrCode.makeCode("${codeurl}");
	$(function(){
		$('#qrcode').attr('title','');//鼠标放上去不显示链接
	});
	
	(function polling() {
		$.ajax({
			url: "${pageContext.request.contextPath }/wechatCallback",
			cache: false, // 禁用缓存
			data:{"uuid":"${uuid}"},
			success: function(data){
				if(data.isLogin == "true"){
					location.href = "${pageContext.request.contextPath }/${position}";
				}else {
					polling(); // 递归调用
				}
			},
			// 发生错误的时候执行的方法
			error:function(){
				polling(); // 递归调用
			}
		});
	})();
</script>
</body>
</html>