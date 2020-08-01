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
		<title>下单</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="static/front/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/front/fonts/iconfont.css" />
		<link href="static/front/css/style.css" rel="stylesheet" />

	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">下单</h1>
		</header>
		<div class="mui-content">

			<!--顶部
			<div class="order_detail">
				<div><i class="iconfont icon-tishichenggong"></i>已完成</div>
				<div>订单已确认收货</div>
			</div> -->
			<!--收货地址-->
			<div class="detail_adr" style="position: relative;">
				<c:if test="${not empty addPd}">
					<div>
						${addPd.RECIPIENT} ${addPd.PHONE}
						<p>${addPd.ADDRESS}${addPd.DETAILEDADD}</p>
					</div>
				</c:if>
				<c:if test="${!not empty addPd}">
					<div>
						<p><a href="front/verified">添加地址</a></p>
					</div>
				</c:if>
			</div>

			<div class="order_list">

				<div class="order_txt order_txt1">
					<div class="order_txt_1">
						<div class="order_txt_pic">
							<img src="${cfProduct.IMGPATH1}" />
							<div class="width_pic">
								<div class="mui-ellipsis">
									${cfProduct.NAME}
								</div>
								<!-- <p>发荻港的烦恼</p> -->
								<div>￥${cfProduct.PRICE}</div>
							</div>
						</div>
					</div>
					<div class="order_txt_2">
						x${pd.QUANTITY}
					</div>
				</div>

				<div class="order_bot detail_psf">
					<div>配送费</div>
					<div>
						免邮
					</div>
				</div>
				<div class="order_txt_3">
					<div>共${pd.QUANTITY}件商品</div>
					<div>￥<span>${pd.TOTAL}</span></div>
				</div>
			</div>

			<div class="dele_order">
				<button type="button" onclick="order_do();">确认支付</button>
			</div>
		</div>
		
		<script type="text/javascript" src="static/front/js/jquery.min.js"></script>
		<script src="static/front/js/mui.min.js"></script>
		<script type="text/javascript" src="static/front/js/jquery.min.js" ></script>
		<script type="text/javascript" src="static/front/js/flexible.js"></script>

		<script type="text/javascript">
			
			function order_do(){
				var QUANTITY = '${pd.QUANTITY}';
				var TOTAL = '${pd.TOTAL}';
				var ADDRESS_ID = '${addPd.ADDRESS_ID}';
				var CFPRODUCT_ID = '${cfProduct.CFPRODUCT_ID}';
				var MEMBER = '${member.MEMBER}';
				if(ADDRESS_ID != ''){
					$.ajax({
						type:'post',
						url :'front/order1_do',
						data:{CFPRODUCT_ID:CFPRODUCT_ID,ADDRESS_ID:ADDRESS_ID,QUANTITY:QUANTITY,TOTAL:TOTAL,MEMBER:MEMBER},
						success:function(data){
							if(data == 'success'){
								alert('支付成功');
								window.location.href = 'front/myorderlist';
							}else if (data == ''){
								alert('库存不足');
							}else if(data == 'money'){
								alert('订单已生成余额不足');
								window.location.href = 'front/myorderlist';
							}
						}
					});
				}else{
					alert('请添加地址');
				}
			}
		
		</script>

	</body>

</html>