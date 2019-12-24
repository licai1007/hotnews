<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico">
<title>视频</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/res/style/layout.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/res/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/res/js/common.js"></script>
<jsp:include page="communal.jsp"/>
<script type="text/javascript">
	$(function(){
		$('#selectVideo').on('click','.playone',function(){
			var videoName = $(this).attr('value');
			$('embed').remove();
			var str = '<embed src="${pageContext.request.contextPath }/video/' + videoName + '" width="620" height="350">';
			$('#play').html(str);
		});
		$('#upload').click(function(){
			var allFile = $('#wenjian')[0];
			if(allFile.files.length==0){
				alert('请选择上传视频！');
				return;
			}
			var formData = new FormData();
			for(var i=0;i<allFile.files.length;i++){
				formData.append('video',allFile.files[i]);
			}
			$.ajax({
			    url: '${pageContext.request.contextPath }/upload',
			    type: 'POST',
			    cache: false,
			    data: formData,
			    processData: false,
			    contentType: false,
			    success:function(data){
				    if(data.result=='notLogin'){
					    $('#login').css({'display':'block'});
					}else if(data.result=='ok'){
						alert('上传成功！');
						for(var i=0;i<allFile.files.length;i++){
							var videoName = allFile.files[i].name;
							$('#selectVideo').append('<tr><td><a class="playone" href="javascript:void(0);" value="'+videoName+'">'+videoName+'</a></td></tr>');
						}
					}
				}
			});
		});
	});
</script>
</head>
<body>
	<%pageContext.setAttribute("flag","video"); %>
	<%@ include file="header.jsp" %>
	<div style="width:620px;margin:40px auto;">
		<div style="margin-left:50px;margin-bottom:10px;">
		<form enctype="multipart/form-data">
			上传视频：
			<input id="wenjian" type="file" name="video" multiple="multiple"/>
			<input id="upload" type="button" value="提交"/>
		</form>
		</div>
		<div id="play">
			<embed src="${pageContext.request.contextPath }/video/${player}" width="620" height="350">
		</div>
		<div style="text-align:right;margin-right:50px;">
			<a href="${pageContext.request.contextPath }/admin/download?fileName=${player}">下载</a>
		</div>
		<div style="position:absolute;right:80px;top:180px;">
		<table>
			<thead>
				<tr>
					<td>播放列表</td>
				</tr>
			</thead>
			<tbody id="selectVideo">
				<c:forEach items="${videos}" var="video">
					<tr>
						<td>
							<a class="playone" href="javascript:void(0);" value="${video}">${video}</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>