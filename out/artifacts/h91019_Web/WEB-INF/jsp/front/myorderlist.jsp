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
		<title>我的订单</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="static/front/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/front/fonts/iconfont.css" />
		<link href="static/front/css/style.css" rel="stylesheet" />

	</head>

	<body>

		<header class="mui-bar mui-bar-nav">
			<a class="mui-icon mui-icon-left-nav mui-pull-left" onclick="mine();"></a>
			<h1 class="mui-title">我的订单</h1>
		</header>
		<div class="mui-content">
			<!--头部导航-->
			<div class="order_top">
				<ul>
					<li class="order_active all_order">全部</li>
					<li class="dfk1">待付款<!--  <span>1</span> --></li>
					<li class="dfh1">待发货<!-- <span>1</span> --></li>
					<li class="dsh1">待收货<!-- <span>1</span> --></li>
					<li class="ywc1">已完成</li>
				</ul>
			</div>
			<!-- 待付款 -->
			<c:forEach items="${listAll}" var="var">
				<div class="order_list 
				<c:if test="${var.STATE == '待付款'}">dfk</c:if>
				<c:if test="${var.STATE == '待发货'}">dfh</c:if>
				<c:if test="${var.STATE == '待收货'}">dsh</c:if>
				<c:if test="${var.STATE == '已完成'}">ywc</c:if>
				">
					<div class="order_tit">
						<div class="order_tit_1">
							<div><i class="iconfont icon-dianpu"></i>398商城</div>
							<p>订单编号：${var.ORDERNUM}</p>
						</div>
						<div class="order_tit_2">${var.STATE}</div>
					</div>
					<div class="order_txt" data-link="" onclick="detail('${var.ORDER_ID}');">
						<div class="order_txt_1">
							<div class="order_txt_pic">
								<img src="${var.IMGPATH}" />
								<div class="width_pic">
									<div class="mui-ellipsis" >
										${var.NAME}
									</div>
									<p>398商城</p>
									<div>
									${var.SOURCE == '1' ? '￥' : '积分'}${var.PRICE}
									</div>
								</div>
							</div>
						</div>
						<div class="order_txt_2">
							x${var.QUANTITY}
						</div>
					</div>
					<div class="order_txt_3">
						<div>共${var.QUANTITY}件商品</div>
						<div>支付：<span>${var.SOURCE == '1' ? '￥' : '积分'}${var.TOTAL}</span></div>
					</div>
					<div class="order_bot">
						<div>邮费：免费</div>
						<div>
							<!-- <button>取消订单</button> -->
							<c:if test="${var.STATE == '待付款'}">
								<button onclick="Pay('${var.ORDER_ID}');">立即支付</button>
								<button onclick="delOrder('${var.ORDER_ID}');">取消订单</button>
							</c:if>
							<c:if test="${var.STATE == '待发货'}">
								<button>提醒发货</button>
							</c:if>
							<c:if test="${var.STATE == '待收货'}">
								<button onclick="Receipt('${var.ORDER_ID}');">确认收货</button>
							</c:if>
							<c:if test="${var.STATE == '已完成'}">
								<button onclick="delOrder('${var.ORDER_ID}');">删除订单</button>
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>
			<!--待发货-->
			<!-- <div class="order_list dfh">
				<div class="order_tit">
					<div class="order_tit_1">
						<div><i class="iconfont icon-dianpu"></i>发荻港的烦恼</div>
						<p>订单编号：1266866565655</p>
					</div>
					<div class="order_tit_2">待发货</div>
				</div>
				<div class="order_txt">
					<div class="order_txt_1">
						<div class="order_txt_pic">
							<img src="static/front/images/mine1.png" />
							<div class="width_pic">
								<div class="mui-ellipsis" >
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
				</div>
				<div class="order_txt_3">
					<div>共1件商品</div>
					<div>支付金额：<span>￥500</span></div>
				</div>
				<div class="order_bot">
					<div>邮费：免费</div>
					<div>
						<button>取消订单</button>
						<button>提醒发货</button>
					</div>
				</div>
			</div> -->
			
			<!--待收货-->
			<!-- <div class="order_list dsh">
				<div class="order_tit">
					<div class="order_tit_1">
						<div><i class="iconfont icon-dianpu"></i>发荻港的烦恼</div>
						<p>订单编号：1266866565655</p>
					</div>
					<div class="order_tit_2">待收货</div>
				</div>
				<div class="order_txt">
					<div class="order_txt_1">
						<div class="order_txt_pic">
							<img src="static/front/images/mine1.png" />
							<div class="width_pic">
								<div class="mui-ellipsis" >
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
				</div>
				<div class="order_txt_3">
					<div>共1件商品</div>
					<div>支付金额：<span>￥500</span></div>
				</div>
				<div class="order_bot">
					<div>邮费：免费</div>
					<div>
						<button>取消订单</button>
						<button>提醒发货</button>
					</div>
				</div>
			</div> -->
			
			<!--已完成-->
			<!-- <div class="order_list ywc">
				<div class="order_tit">
					<div class="order_tit_1">
						<div><i class="iconfont icon-dianpu"></i>发荻港的烦恼</div>
						<p>订单编号：1266866565655</p>
					</div>
					<div class="order_tit_2">已完成</div>
				</div>
				<div class="order_txt">
					<div class="order_txt_1">
						<div class="order_txt_pic">
							<img src="static/front/images/mine1.png" />
							<div class="width_pic">
								<div class="mui-ellipsis" >
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
				</div>
				<div class="order_txt_3">
					<div>共1件商品</div>
					<div>支付金额：<span>￥500</span></div>
				</div>
				<div class="order_bot">
					<div>邮费：免费</div>
					<div>
						<button>删除订单</button>
					</div>
				</div>
			</div> -->
			
		</div>
		<script src="static/front/js/mui.min.js"></script>
		<script type="text/javascript" src="static/front/js/jquery.min.js" ></script>
		<script type="text/javascript" src="static/front/js/flexible.js" ></script>
		<script type="text/javascript">
		
			//删除
			function delOrder(Id){
				$.ajax({
					type:'post',
					url :'front/delOrder',
					data:{ORDER_ID:Id},
					success:function(data){
						if(data == 'success'){
							window.location.href = 'front/myorderlist';
						}
					}
				});
			}
		
			//确认收货
			function Receipt(Id){
				$.ajax({
					type:'post',
					url :'front/Receipt',
					data:{ORDER_ID:Id},
					success:function(data){
						if(data == 'success'){
							window.location.href = 'front/myorderlist';
						}
					}
				});
			}
			
			//立即支付
			function Pay(Id){
				$.ajax({
					type:'post',
					url :'front/Pay',
					data:{ORDER_ID:Id},
					success:function(data){
						if(data == 'success'){
							alert('支付成功');
							window.location.href = 'front/myorderlist';
						}else if(data == 'stock'){
							alert('库存不足');
						}else if(data == 'wealth'){
							alert('财富余额不足');
						}else if(data == 'integral'){
							alert('积分不足');
						}else if(data == 'xiajia'){
							alert('商品已下架');
						}
					}
				});
			}
		
			function detail(Id){
				window.location.href = 'front/myorderdetail?ORDER_ID=' + Id;
			}
		
			function mine(){
				window.location.href = 'front/mine';
			}
		
			mui(document.body).on('tap', '.order_top li', function() {
				$('.order_top li').removeClass('order_active');
				$(this).addClass('order_active');
			})
			
			mui(document.body).on('tap','.all_order',function(){
				$('.dfk').show();
				$('.dfh').show();
				$('.dsh').show();
				$('.ywc').show();
			})
			mui(document.body).on('tap','.dfk1',function(){
				$('.dfk').show();
				$('.dfh').hide();
				$('.dsh').hide();
				$('.ywc').hide();
			})
			mui(document.body).on('tap','.dfh1',function(){
				$('.dfh').show();
				$('.dfk').hide();
				$('.dsh').hide();
				$('.ywc').hide();
			})
			mui(document.body).on('tap','.dsh1',function(){
				$('.dsh').show();
				$('.dfk').hide();
				$('.dfh').hide();
				$('.ywc').hide();
			})
			mui(document.body).on('tap','.ywc1',function(){
				$('.ywc').show();
				$('.dfk').hide();
				$('.dfh').hide();
				$('.dsh').hide();
			})
		</script>
	</body>

</html>