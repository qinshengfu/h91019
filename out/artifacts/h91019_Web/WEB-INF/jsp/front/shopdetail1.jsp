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
		<title>商品详情</title>
		<link href="static/front/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/front/fonts/iconfont.css" />
		<link rel="stylesheet" href="static/front/css/jquery.hiSlider.min.css" />
		<link href="static/front/css/style.css" rel="stylesheet" />

	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">商品详情</h1>
		</header>
		<div class="mui-content">
			<!--轮播图-->
			<div class="">
				<ul class="">
					<li class="hiSlider-item">
						<a href="javascript:;"><img src="${pd.IMGPATH1}"></a>
					</li>
					<%-- <li class="hiSlider-item">
						<a href="javascript:;"><img src="${pd.IMGPATH2}"></a>
					</li>
					<li class="hiSlider-item">
						<a href="javascript:;"><img src="${pd.IMGPATH3}"></a>
					</li>
					<li class="hiSlider-item">
						<a href="javascript:;"><img src="${pd.IMGPATH5}"></a>
					</li>
					<li class="hiSlider-item">
						<a href="javascript:;"><img src="${pd.IMGPATH5}"></a>
					</li> --%>
				</ul>
			</div>

			<!--商品信息-->
			<div class="prot_mess">
				<div class="prot_mess_1">
					<label>${pd.NAME}</label>
					<div>积分：${pd.PRICE}</div>
				</div>
				<div class="prot_mess_2">库存${pd.STOCK}件</div>
			</div>
			<!--选择产品数量-->
			<div class="prot_xuanz">
				<p>请选择：</p>
				<div class="prot_xuanz_1">
					<div>产品数量</div>
				</div>
				<i class="iconfont icon-you-"></i>
			</div>
			<!--选择产品数量弹窗-->
			<div class="mui-backdrop" style="display:none;bottom: 50px;">
				<div class="prot_num">
					<div class="prot_num_1">
						<img src="${pd.IMGPATH1}" alt="" />
						<div class="prot_num_4">
							<label class="mui-ellipsis-2">${pd.NAME}</label>
							<div>积分:${pd.PRICE}</div>
						</div>
					</div>
					<div class="prot_num_2">
						<div class="prot_num_3">数量
							<p>剩余${pd.STOCK}件</p>
						</div>
						<div>
							<div class="mui-numbox" data-numbox-step='1' data-numbox-min='1' data-numbox-max='100'>
								<button class="mui-btn mui-numbox-btn-minus" type="button">-</button>
								<input class="mui-numbox-input" type="number" id="QUANTITY" />
								<button class="mui-btn mui-numbox-btn-plus" type="button">+</button>
							</div>
						</div>
					</div>
					<div class="tubiaozhizuo"><i class="iconfont icon-tubiaozhizuo-"></i></div>
				</div>

			</div>

			<!--商品详情-->

			<div class="prot_detail" style="margin-bottom: 0;">
				<div class="prot_detail_1">
					<span class="line"></span>
					<span class="txt">商品详情</span>
					<span class="line"></span>
				</div>
			</div>
			<div class="prot_detail_2" style="margin-bottom: 100px;">
				<img src="${pd.IMGPATH1}" style="width: 100%; height: 200px;" alt="" />
				<img src="${pd.IMGPATH2}" style="width: 100%; height: 200px;" alt="" />
				<img src="${pd.IMGPATH3}" style="width: 100%; height: 200px;" alt="" />
				<img src="${pd.IMGPATH4}" style="width: 100%; height: 200px;" alt="" />
				<img src="${pd.IMGPATH5}" style="width: 100%; height: 200px;" alt="" />
			</div>
			
			<!--底部-->
			<div class="prot_bot">
				<button type="button" class="mallexchange" onclick="order('${pd.JFPRODUCT_ID}');" style="width: 100%;">积分兑换</button>
			</div>
		</div>

		<script type="text/javascript" src="static/front/js/jquery.min.js"></script>
		<script src="static/front/js/mui.min.js"></script>
		<script type="text/javascript" src="static/front/js/flexible.js"></script>
		<script type="text/javascript" src="static/front/js/jquery.min.js"></script>
		<script type="text/javascript" src="static/front/js/jquery.hiSlider.min.js"></script>
		<script>
		
			function order(Id){
				var QUANTITY = $("#QUANTITY").val();
				var MEMBER_ID = '${member.MEMBER_ID}';
				window.location.href = 'front/order?JFPRODUCT_ID='+ Id +'&MEMBER_ID='+ MEMBER_ID + '&QUANTITY=' + QUANTITY + '&mark=1';
				/* $.ajax({
					type:'post',
					url :'front/order',
					data:{JFPRODUCT_ID:Id,MEMBER_ID:MEMBER_ID,QUANTITY:QUANTITY,mark:'1'},
					success:function(data){
						
					}
				}); */
			}
		
			mui('.mui-content').on('tap', 'a', function() {
				var id = this.getAttribute('href');
				var href = this.href;
				mui.openWindow({
					id: id,
					url: this.href,
					show: {
						autoShow: true
					}
				});
			});
		</script>

		<script type="text/javascript">
			$('.hiSlider3').hiSlider({
				isFlexible: true,
				isSupportTouch: true,
			});
			$(function() {
				mui(document.body).on('tap', '.prot_xuanz', function(event) {
					//取消事件冒泡  
					event.stopPropagation();
					//按钮的toggle,如果div是可见的,点击按钮切换为隐藏的;如果是隐藏的,切换为可见的。  
					$('.mui-backdrop').css('display', 'block');
				});
				mui(document.body).on('tap', '.tubiaozhizuo', function(event) {
					//取消事件冒泡  
					event.stopPropagation();
					//按钮的toggle,如果div是可见的,点击按钮切换为隐藏的;如果是隐藏的,切换为可见的。  
					$('.mui-backdrop').css('display', 'none');
				});
			})
		</script>

	</body>

</html>