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
		<title>积分商城</title>
		<link href="static/front/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/front/fonts/iconfont.css" />
		<link rel="stylesheet" href="static/front/css/jquery.hiSlider.min.css" />
		<link href="static/front/css/style.css" rel="stylesheet" />

		<style type="text/css">
			.mui-table-view p{
				color: #000;
				text-align: center;
			}
		</style>
	</head>

	<body>
		<div class="mui-content pbom50">
			<!--<div class="colourhead">
				<img src="images/logo02.png" class="applogo" />
				<div class="searchshop"><input type="search" placeholder="输入关键词"></div>
			</div>-->

			<div class="silderbox">
				<ul class="hiSlider hiSlider3">
					<c:forEach items="${jfCarousel}" var="var">
						<li class="hiSlider-item">
							<a href="front/mallshop"><img src="${var.IMGPATH}"></a>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="newsbox">
				<ul class="mui-table-view">
					<li class="mui-table-view-cell">
						<label>公告</label>
						<marquee>
							<a href="javascript:;">${newsPd.CONTENT}</a>
						</marquee>
					</li>
				</ul>
			</div>
			<div class="menunav mui-text-center">
				<ul class="mui-table-view">
					<li class="mui-table-view-cell auto flexaroundplay linehide">
						<a href="front/clothes1"><img src="static/front/images/indexicon7.png" class="indexicon"/><p style="width: 50px;">生鲜</p></a>
						<a href="front/luxury1"><img src="static/front/images/indexicon8.png" class="indexicon"/><p style="width: 50px;">美食</p></a>
						<a href="front/car1"><img src="static/front/images/indexicon1.png" class="indexicon"/><p style="width: 50px;">美妆日化</p></a>
					</li>
					<li class="mui-table-view-cell auto flexaroundplay">
						<a href="front/bedclothes1"><img src="static/front/images/indexicon5.png" class="indexicon"/><p style="width: 50px;">数码家电</p></a>
						<a href="front/phone1"><img src="static/front/images/indexicon4.png" class="indexicon"/><p style="width: 50px;">日用百货</p></a>
						<a href="javascript:;"><img src="static/front/images/indexicon6.png" class="indexicon"/><p style="width: 50px;">分类</p></a>
					</li>
				</ul>
			</div>

			<ul class="mui-table-view">
				<li class="mui-table-view-cell">
					<a href="JavaScript:;" class="mui-navigate-right">
						热卖商品 
					</a>
				</li>
			</ul>
			<div class="equallylist mtop0" style="margin-bottom: 10px;">

				<div>
					<ul class="shoplist">
						<c:forEach items="${jfProduct}" var="var">
							<li class="shoplistli">
								<a href="front/shopdetail1?JFPRODUCT_ID=${var.JFPRODUCT_ID}">
									<img src="${var.IMGPATH1}" />
									<p>${var.NAME}</p>
									<p>库存:${var.STOCK}</p>
									<p><span>积分:${var.PRICE}</span></p>
								</a>
							</li>
						</c:forEach>
						<!-- <li class="shoplistli">
							<a href="#">
								<img src="static/front/images/minebg.png" />
								<p>新款苹果安卓二合一数据线1米 适用iPhone5 7 6S</p>
								<p>库存:552454</p>
								<p><span>￥13</span></p>
							</a>
						</li> -->
					</ul>
				</div>

			</div>

		</div>

		<footer class="footer">
			<div class="flexaroundplay footnav">
				<a href="front/index"><i class="iconfont icon-shouye6"></i>财富商城</a>
				<a href="front/mallshop" class="active"><i class="iconfont icon-chanpinzhengce"></i>积分商城</a>
				<a href="front/mine"><i class="iconfont icon-wode6"></i>我的</a>
			</div>
		</footer>

		<script src="static/front/js/mui.min.js"></script>
		<script type="text/javascript" src="static/front/js/flexible.js"></script>
		<script type="text/javascript" src="static/front/js/jquery.min.js"></script>
		<script type="text/javascript" src="static/front/js/jquery.hiSlider.min.js"></script>
		<script>
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
		</script>

	</body>

</html>