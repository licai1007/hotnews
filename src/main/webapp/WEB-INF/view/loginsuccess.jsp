<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath }/res/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="http://res2.wx.qq.com/open/js/jweixin-1.4.0.js "></script>
<script type="text/javascript">
$(function(){
	$.ajax({
        url: "${pageContext.request.contextPath}/selectUserLocation",
        type: 'post',
        dataType: 'json',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {
            'url': location.href.split('#')[0]
        },
        success: function (data) {
            wx.config({
                debug: false,
                appId: data.info.appId,
                timestamp: data.info.timestamp,
                nonceStr: data.info.nonceStr,
                signature: data.info.signature,
                jsApiList: ['checkJsApi']//此处只申请使用了一个接口，可根据需要申请多个接口的使用权
            });
            wx.ready(function () {//配置成功会调用此方法
                $('#result').text(data.info.data);
            	wx.hideOptionMenu();
        		window.setTimeout(function(){
        			wx.closeWindow();
        		},1000);
            });
            wx.error(function (res) {//配置失败调用此方法

                }
            );
        }
    });
});
</script>
<title>success</title>
</head>
<body>
	<table width="100%" height="40%" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="middle" align="center">
				<font id="result" color="green" size="10"></font>
			</td>
		</tr>
	</table>
</body>
</html>