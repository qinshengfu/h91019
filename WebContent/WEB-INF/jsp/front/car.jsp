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
		<title>美妆日化</title>
		<link href="static/front/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/front/fonts/iconfont.css" />
		<link rel="stylesheet" href="static/front/css/jquery.hiSlider.min.css" />
		<link href="static/front/css/style.css" rel="stylesheet" />
		
	</head>

	<body>
	
		<header class="mui-bar mui-bar-nav">
			<a class="mui-icon mui-icon-left-nav mui-pull-left" href="front/index"></a>
			<h1 class="mui-title">美妆日化</h1>
		</header>
	
		<div class="mui-content pbom50">
			<div class="equallylist mtop0" style="margin-bottom: 10px;">

				<div>
					<ul class="shoplist">
						<c:forEach items="${carList}" var="var">
							<li class="shoplistli">
								<a href="front/shopdetail?CFPRODUCT_ID=${var.CFPRODUCT_ID}">
									<img src="${var.IMGPATH1}" />
									<p>${var.NAME}</p>
									<p>库存:${var.STOCK}</p>
									<p><span>￥${var.PRICE}</span></p>
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

		<script src="static/front/js/mui.min.js"></script>
		<script type="text/javascript" src="static/front/js/flexible.js"></script>
		<script type="text/javascript" src="static/front/js/jquery.min.js"></script>
		<script type="text/javascript" src="static/front/js/jquery.hiSlider.min.js"></script>
		<script>
			mui('.mui-content').on('tap', 'a[href]', function() {
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

<!-- 		<script type="text/javascript">
			$('.hiSlider3').hiSlider({
				isFlexible: true,
				isSupportTouch: true,
			});
		</script> -->

	</body>

</html>