<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>澎湃新闻</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/res/style/layout.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/res/js/common.js"></script>
<jsp:include page="communal.jsp"/>    <!-- el表达式必须放在jsp页面中才能执行 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/res/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/res/editor/kindeditor-all-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/res/editor/lang/zh_CN.js"></script>
<script type="text/javascript">
	KindEditor.ready(function(K){
		var editor = K.create('#commentFrame',{
			width:'750px',
			height:'50px',
			resizeType:0,
			items:['emoticons']
		});
		K(this).blur(function(){
			var content = editor.text();
			if(content == '我要跟贴。。。'){
				editor.text('');
			}		
		});
		K(this).focus(function(){
			var ct = $.trim(editor.text());
			if(ct == ''){
				editor.text('我要跟贴。。。');
			}
		});
		$('#publish').click(function(){
			var message = editor.html();
			if(message == '我要跟贴。。。'){
				alert('您还没有填写内容！');
				return;
			}
			window.location.href="${pageContext.request.contextPath}/admin/publishComment?nid=${news.newsid}&message="+message;
		});
	}); 
</script>
<script type="text/javascript">
if('' != '${commentMsg}'){
	alert('${commentMsg}');
}
	$(function(){
		var flag = false;
		$('#dianzan').click(function(){
			if(!flag){
				$(this).css({"background":"url(${pageContext.request.contextPath}/res/images/zan.png) no-repeat 0px -16px"});
				var count = parseInt($('#zannum').html())+1;
				$('#zannum').html(count);
				flag = true;
				$.ajax({
					url:"${pageContext.request.contextPath}/dianzan",
					type:"post",
					data:{"newsid":"${news.newsid}","thumbsUp":count}
				});
			}
			
		});

		var yidian = new Array();
		var i = 0;
		$('.newscommentMsg>.dianzan').click(function(){
				var param = $(this).attr('value');
				var commentid = $('#'+param+'>.cd').val();
				if(!exist(commentid)){
					var number = parseInt($('#'+param+'>.agreenum').html())+1;
					$(this).css({"background":"url(${pageContext.request.contextPath}/res/images/zan.png) no-repeat 0px -16px"});
					$('#'+param+'>.agreenum').html(number);
					$.ajax({
						url:"${pageContext.request.contextPath}/comment/agree",
						type:"post",
						data:{"commentid":commentid,"agree":number}
					});
					yidian[i] = commentid;
					i++;
				}
				
		});

		function exist(fg){
			for(var index=0;index<yidian.length;index++){
				if(fg==yidian[index]){
					return true;
				}
			}
			return false;
		}

		var vclick = new Array();
		var x = 0;
		$('.replyMsg').click(function(){
			var v = $(this).attr('value');
			if(!ishave(v)){
				$('#r'+v+'>.replytext').css({"display":"block"});
				if(!check(v)){
					vclick[x] = v;
					x++;
				}
			}else{
				$('#r'+v+'>.replytext').css({"display":"none"});
			}
			 
		});
		function ishave(parameter){
			for(var y=0;y<vclick.length;y++){
				if(parameter==vclick[y]){
					vclick[y] = -1;
					return true;
				}
			}
			return false;
		}
		function check(n){
			for(var z=0;z<vclick.length;z++){
				if(vclick[z]==-1){
					vclick[z] = n;
					return true;
				}
			}
			return false;
		}
		$('.phReply').click(function(){
			var vr = $(this).attr('value');
			var a = $('#'+vr+'>.cd').val();
			var m = $('#r'+vr+'>.replytext>.rmsg').val();
			if(m == '说些什么好呢'){
				alert('您还没有填入内容！');
				return;
			}
			window.location.href = "${pageContext.request.contextPath}/admin/publishReply?cid="+a+"&msg="+m+"&nid=${news.newsid}";
		});
	});
</script>
</head>
<body>
<%pageContext.setAttribute("flag","news"); %>
<%@ include file="header.jsp" %>
<div class="tools">
	<div id="weather" class="tool-left"></div>
	<div id="count" class="tool-right"></div>
</div>
<div class="main">
<div class="left">
    <div class="newsContent">
    	<br/><br/><br/>
    	<h1 style="text-align:center;margin-top:0px;">${news.title }</h1>
    	<div style="margin:0px 80px;">
    		<hr/>
    		来源：${news.source } <br/>
    		时间：<fmt:formatDate value="${news.createdate }" pattern="yyyy-MM-dd HH:mm"/>
    		<br/><br/>
    		<p style="font-size:18px;line-height:35px;">${news.content }</p>
    		<div class="newsbottom" style="text-align:right;font-size:14px;overflow:hidden;">
    			<span id="dianzan" class="dianzan"></span><div id="zannum" style="float:left;margin-left:10px;">${news.thumbsUp }</div>
    			<strong>作者：${news.author }</strong>
    		</div>
    		<br/><br/><br/>
    		<h3>评论</h3>
    		<hr/>
    		<textarea id="commentFrame">我要跟贴。。。</textarea><br/>
    		<input id="publish" type="button" style="margin-left:650px;" value="发表"/>
    		<div style="margin-top:80px;">
    			<c:forEach items="${comments }" var="comment" varStatus="vs">
    			<div style="border-bottom:1px dashed gray;margin-top:10px;">
    				<div style="overflow:hidden;">
    					<div style="float:left;">
		    				<strong>${comment.writer }</strong><br/>
		    				<span><fmt:formatDate value="${comment.writertime }" pattern="yyyy/MM/dd HH:mm:ss"/></span>
	    				</div>
	    				<div id="${vs.index }" class="newscommentMsg" style="float:right;">
		    				<span class="dianzan" value="${vs.index }"></span>
		    				&nbsp;
		    				<span class="agreenum">${comment.agree }</span>
		    				|
		    				<span class="replyMsg" style="cursor:pointer;color:blue;" value="${vs.index }">回复</span>
    						<input type="hidden" class="cd" value="${comment.commentid }"/>
	    				</div>
    				</div>
    				<div id="r${vs.index }" style="margin:10px 20px;font-size:18px;">
    					<div>${comment.message }</div>
    					<div class="replytext" style="display:none;">
    						<textarea class="rmsg" rows="2" cols="80" 
    						onfocus="if(value=='说些什么好呢'){value='';}"
    						onblur="if(value==''){value='说些什么好呢';}">说些什么好呢</textarea>&nbsp;
    						<a class="phReply" href="#" value="${vs.index }">发表</a>
    					</div>
    					<div style="margin-left:15px;font-size:15px;">
    						<c:forEach items="${comment.replys }" var="reply">
    							<span style="font-size:12px;">${reply.rauthor}</span>
    							<span style="color:gray;"><fmt:formatDate value="${reply.rtime}" pattern="yyyy/MM/dd HH:mm:ss"/></span><br/>
    							&nbsp;&nbsp;&nbsp;<span style="color:gray;">${reply.msg }</span><br/>
    						</c:forEach>
    					</div>
    				</div>
    			</div>
    			</c:forEach>
    		</div>
    		
    		
    	</div>
    </div>
</div>
<%@ include file="right.jsp"%>
</div>
<div style="clear:both"></div>
<%@ include file="footer.jsp"%>
</body>
</html>