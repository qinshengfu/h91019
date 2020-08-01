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
		<title>佣金明细</title>
		<link href="static/front/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/front/fonts/iconfont.css" />
		<link href="static/front/css/style.css" rel="stylesheet" />

	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-icon mui-icon-left-nav mui-pull-left" href="front/mine"></a>
			<h1 class="mui-title">佣金明细</h1>
		</header>
		<div class="mui-content">
			<div>
				<ul class="mui-table-view">
					<c:forEach items="${listAll}" var="var">
						<li class="mui-table-view-cell auto">
							<p class="flexbetweenplay">
								<b>${var.PHONE}</b>
								<c:if test="${var.TYPE == '退回' || var.TYPE == '直推奖' || var.TYPE == '领导奖'}">
									<span>佣金：<font style="color: green;">+${var.QUANTITY}</font></span>
								</c:if>
								<c:if test="${var.TYPE == '提现' || var.TYPE == '兑换积分' || var.TYPE == '兑换财富'}">
									<span>佣金：<font style="color: red;">-${var.QUANTITY}</font></span>
								</c:if>
							</p>
							<p class="flexbetweenplay">
								<span>类型：${var.TYPE}</span>
								<c:if test="${var.TYPE == '直推奖' || var.TYPE == '领导奖'}">
									<span>来源：${var.PRODUCE}</span>
								</c:if>
								<%-- <span>时间：${var.TIME}</span> --%>
							</p>
							<p>时间：${var.TIME}</p>
						</li>
					</c:forEach>
					<!-- <li class="mui-table-view-cell auto">
						<p class="flexbetweenplay">
							<b>5454545424</b>
							<span>数量：<font style="color: #FF8E43;">+354646.00</font></span>
						</p>
						<p class="flexbetweenplay">
							<span>类型：leixing</span>
							<span>状态：转入</span>
						</p>
						<p>时间：2019-15-25 19:80:95</p>
					</li> -->
					<!-- <li class="mui-table-view-cell auto">
						<p class="flexbetweenplay">
							<b>5454545424</b>
							<span>数量：<font style="color: #2AC845;">-354646.00</font></span>
						</p>
						<p class="flexbetweenplay">
							<span>类型：leixing</span>
							<span>状态：转出</span>
						</p>
						<p>时间：2019-15-25 19:80:95</p>
					</li> -->
				</ul>
			</div>
		</div>
		<script src="static/front/js/mui.min.js"></script>
		<script type="text/javascript" src="static/front/js/flexible.js"></script>

	</body>

</html>