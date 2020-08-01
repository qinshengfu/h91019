<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>

	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<title>订单详情</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="static/front/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/front/fonts/iconfont.css" />
		<link href="static/front/css/style.css" rel="stylesheet" />

	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">订单详情</h1>
		</header>
		<div class="mui-content">

			<!--顶部-->
			<div class="order_detail">
				<div><i class="iconfont icon-tishichenggong"></i>${pd.STATE}</div>
				<c:if test="${pd.STATE == '待付款'}">
					<div>订单状态待付款</div>
				</c:if>
				<c:if test="${pd.STATE == '待发货'}">
					<div>订单状态待发货</div>
				</c:if>
				<c:if test="${pd.STATE == '待收货'}">
					<div>订单状态待收货</div>
				</c:if>
				<c:if test="${pd.STATE == '已完成'}">
					<div>订单状态已完成</div>
				</c:if>
			</div>
			<!--收货地址-->
			<div class="detail_adr">
				<div>${pd.RECIPIENT} ${pd.PHONE}</div>
				<p>${pd.ADDRESS}${pd.DETAILEDADD}</p>
			</div>

			<div class="order_list">
				<div class="order_tit">
					<div class="order_tit_1">
						<div><i class="iconfont icon-dianpu"></i>发荻港的烦恼</div>
					</div>
				</div>

				<div class="order_txt order_txt1">
					<div class="order_txt_1">
						<div class="order_txt_pic">
							<img src="${pd.IMGPATH}" />
							<div class="width_pic">
								<div class="mui-ellipsis">
									${pd.NAME}
								</div>
								<p>398商城</p>
								<div>${pd.SOURCE == '1' ? '￥' : '积分'}${pd.PRICE}</div>
							</div>
						</div>
					</div>
					<div class="order_txt_2">
						x${pd.QUANTITY}
					</div>
				</div>

				<!-- <div class="order_txt order_txt1">
					<div class="order_txt_1">
						<div class="order_txt_pic">
							<img src="../images/dianp_pic.png" />
							<div class="width_pic">
								<div class="mui-ellipsis">
									的师傅师傅的师傅师傅的说法的师傅师傅的说法的说法
								</div>
								<p>发荻港的烦恼</p>
								<div>￥500</div>
							</div>
						</div>
					</div>
					<div class="order_txt_2">
						x1
					</div>
				</div> -->

				<div class="order_bot detail_psf">
					<div>配送费</div>
					<div>
						￥0.00
					</div>
				</div>
				<div class="order_txt_3">
					<div>共${pd.QUANTITY}件商品</div>
					<div>支付：<span>${pd.SOURCE == '1' ? '￥' : '积分'}${pd.TOTAL}</span></div>
				</div>
			</div>
			<!--订单信息-->
			<div class="order_messge">
				<div class="order_messge_1">
					订单信息
				</div>
				<div class="order_messge_2">
					<p>订单编号：${pd.ORDERNUM}</p>
					<p>下单时间：${pd.ORDERTIME}</p>
					<c:if test="${not empty pd.PAYMENTTIME}">
						<p>付款时间：${pd.PAYMENTTIME}</p>
					</c:if>
					<c:if test="${not empty pd.DELIVERYTIME}">
						<p>发货时间：${pd.pd.DELIVERYTIME}</p>
					</c:if>
					<c:if test="${not empty pd.RECEIPTTIME}">
						<p>收货时间：${pd.pd.RECEIPTTIME}</p>
					</c:if>
				</div>
			</div>
			<!--备注-->
			<div class="remarks">
				<div>备注:</div>
				<textarea name="" rows="" cols=""></textarea>
			</div>

			<!--删除订单-->
			<!-- <div class="dele_order">
				<button type="button">删除订单</button>
				<button type="button">删除订单</button>
			</div> -->
		</div>
		<script src="static/front/js/mui.min.js"></script>
		<script type="text/javascript" src="static/front/js/jquery.min.js" ></script>
		<script type="text/javascript" src="static/front/js/flexible.js"></script>

	</body>

</html>