<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<%-- <link rel="bookmark"  type="image/x-icon"  href="favicon.ico"/>
<link rel="icon" href="favicon.ico"> --%>
<link rel="shortcut icon" href="favicon.ico">
<title>澎湃新闻</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/res/style/layout.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common.js"></script>
<jsp:include page="communal.jsp"/>    <!-- el表达式必须放在jsp页面中才能执行 -->
<script type="text/javascript">
	$(function(){
		$(document).ready(function(){
			  $.ajax({
				url:"${pageContext.request.contextPath}/weather",
				type:"post",
				dataType:"json",
				success:function(result){
					$('#weather').html('<table><tr><td align="center">'+result.listInfo[1]+'</td></tr>'
							+'<tr><td>'+result.listInfo[7]+'</td></tr>'
							+'<tr><td>'+result.listInfo[8]+'</td></tr>'
							+'<tr><td>'+result.listInfo[9]+'</td></tr></table>'
							);
				}
			});  
			$.ajax({
				url:"${pageContext.request.contextPath}/count",
				type:"post",
				dataType:"json",
				success:function(data){
					 $('#count').html('<span>访问次数：'+data.num+'</span>'); 
				}
			});
		});
	});

</script>
</head>
<body>
<%pageContext.setAttribute("flag","index");%>
<%@ include file="header.jsp" %>
<div class="tools">
	<div id="weather" class="tool-left"></div>
	<div id="count" class="tool-right"></div>
</div>
<div class="main">
<div class="left">
    <div class="left-head">
        <div class="leftImg">
            <a href="#">
            <img src="${pageContext.request.contextPath}/res/images/xjp.jpg" alt="为政之要莫先于用人，习近平眼中好干部有哪些标准？"/>
            </a>
        </div>
        <div class="word-right">
            <div class="start">
            <h2><a href="#">为政之要莫先于用人，习近平眼中好干部有哪些标准？</a></h2>
            <p>习近平强调，要“把党和人民需要的好干部精心培养起来、及时发现出来、合理使用起来”。那么，什么是“党和人民需要的好干部”？怎样成为这样的好干部？</p>
            </div>
        </div>
    </div>
<div class="information">
	<div class="information-list">
	<c:forEach items="${newss}" var="news">
    	<div class="info-list">
        	<div class="info-img">
            	<a href="${pageContext.request.contextPath}/news?nid=${news.newsid}" target="_blank">
                	<img alt="${news.title}" src="${pageContext.request.contextPath}/${news.pictureUrl}"/>
                </a>
            </div>
			<div class="info">
            <h2>
            	<a href="http://localhost:8080/hotnews/html/${news.newsid}.html" target="_blank">${news.title}</a>
            </h2>
            <p>${news.at}</p>
			</div>
        </div>
	</c:forEach>	
    </div>
</div>
</div>
<%@ include file="right.jsp" %>
</div>
<div style="clear:both"></div>
<%@ include file="footer.jsp" %>
</body>
</html>