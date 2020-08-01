<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>

	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>地址管理</title>
		<link href="static/front/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/front/fonts/iconfont.css" />
		<link href="static/front/css/style.css" rel="stylesheet" />

	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-icon mui-icon-left-nav mui-pull-left" href="front/mine"></a>
			<h1 class="mui-title">地址管理</h1>
		</header>
		<div class="mui-content">
			<!-- <div class="mui-card addresslist">
				<div class="mui-card-content">
					<div class="mui-card-content-inner">
						<p class="flexstartplay"><span>名字</span><span>15555555555</span></p>
						<p>这是一个最简单的卡片视图控件；卡片视图常用来显示完整独立的一段信息，比如一篇文章的预览图、作者信息、点赞数量等</p>
					</div>
				</div>
				<div class="mui-card-footer">
					<div class="mui-input-row mui-radio mui-left">
						<label>默认地址</label>
						<input name="radio1" type="radio">
					</div>
					<div><a class="operationa" href="adressedit.html">编辑</a>|<a class="operationa">删除</a></div>
				</div>
			</div> -->
			
			<c:forEach items="${listAll}" var="var">
				<div class="mui-card addresslist">
					<div class="mui-card-content">
						<div class="mui-card-content-inner">
							<p class="flexstartplay"><span>${var.RECIPIENT}</span><span>${var.PHONE}</span></p>
							<p>${var.ADDRESS}&nbsp;&nbsp;${var.DETAILEDADD}</p>
						</div>
					</div>
					<div class="mui-card-footer">
						<div class="mui-input-row mui-radio mui-left">
							<label>默认地址</label>
							<input name="radio1" type="radio" onclick="editDefault('${var.ADDRESS_ID}');" <c:if test="${var.DEFAULT == 'yes'}">checked="checked"</c:if>>
						</div>
						<div><!-- <a class="operationa" href="">编辑</a>| --><a class="operationa" onclick="del('${var.ADDRESS_ID}');">删除</a></div>
					</div>
				</div>
			</c:forEach>

			<div class="mui-content-padded">
				<a href="front/myadress" class="logoutbtn">添加新地址</a>
			</div>
		</div>
		<script type="text/javascript" src="static/front/js/jquery.min.js" ></script>
		<script src="static/front/js/mui.min.js"></script>
		<script src="static/front/js/flexible.js"></script>
		
		<script type="text/javascript">
		
			//删除地址
			function del(Id){
				$.ajax({
					type:'post',
					url :'front/del',
					data:{ADDRESS_ID:Id},
					success:function(data){
						window.location.href = 'front/myadresslist';
					}
				});
			}
		
			//默认地址
			function editDefault(Id){
				$.ajax({
					type:'post',
					url :'front/editDefault',
					data:{ADDRESS_ID:Id},
					success:function(data){
						window.location.href = 'front/myadresslist';
					}
				});
			}
		
		</script>
	
	</body>

</html>