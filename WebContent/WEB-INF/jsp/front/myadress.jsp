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
		<link rel="stylesheet" href="static/front/css/mui.picker.min.css" />
		<link rel="stylesheet" href="static/front/fonts/iconfont.css" />
		<link href="static/front/css/style.css" rel="stylesheet" />

	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">地址管理</h1>
		</header>
		<div class="mui-content">
			<!--联系人-->
			<div class="addres">
				<div class="mui-input-row">
					<label>联系人</label>
					<input type="hidden" value="${member.MEMBER_ID}" id="MEMBER_ID">
				</div>
				<div class="mui-input-row">
					<label>姓名：</label>
					<input type="text" placeholder="请输入姓名" id="RECIPIENT">
				</div>
				<div class="mui-input-row">
					<label>手机：</label>
					<input type="text" placeholder="请输入收货手机号" id="PHONE">
				</div>
			</div>
			<!--收货地址-->
			<div class="addres">
				<div class="mui-input-row">
					<label>收货地址</label>
				</div>
				<div class="mui-input-row">
					<label>所在地区：</label>
					<input type="text" id="ADDRESS" placeholder="请点击选择" readonly>
					<i class="iconfont icon-you- you"></i>
				</div>
				<div class="mui-input-row">
					<label>详细地址：</label>
					<input type="text" placeholder="民族大道150号" id="DETAILEDADD">
				</div>
			</div>
			<!--提现按钮-->
			<div class="curr_cash">
				<button type="button" class="logoutbtn" onclick="myadress_do();">保存</button>
			</div>

			<!--添加地址-->
			<!-- <div class="addres_add">
				<button type="button" class="logoutbtn"><i class="iconfont icon-weibiaoti37"></i>新增地址</button>
			</div> -->

		</div>

		<script src="static/front/js/mui.min.js"></script>
		<script type="text/javascript" src="static/front/js/city.data-3.js" ></script>
		<script type="text/javascript" src="static/front/js/mui.picker.min.js" ></script>
		<script type="text/javascript" src="static/front/js/jquery.min.js" ></script>
		<script type="text/javascript" src="static/front/js/flexible.js"></script>
		<script src="static/js/jquery.tips.js"></script>
		<script>
		
			function myadress_do(){
				if(check()){
					var MEMBER_ID = $("#MEMBER_ID").val();
					var PHONE = $("#PHONE").val();
					var RECIPIENT = $("#RECIPIENT").val();
					var ADDRESS = $("#ADDRESS").val();
					var DETAILEDADD = $("#DETAILEDADD").val();
					
					$.ajax({
						type:'post',
						url :'front/myadress_do',
						data:{MEMBER_ID:MEMBER_ID,PHONE:PHONE,RECIPIENT:RECIPIENT,ADDRESS:ADDRESS,DETAILEDADD:DETAILEDADD},
						success:function(data){
							if(data == 'success'){
								window.location.href = 'front/myadresslist';
							}
						}
					});
				}
			}
		
			function check(){
				if($("#RECIPIENT").val() == ""){
					$("#RECIPIENT").tips({
						side : 1,
						msg : '请输入姓名',
						bg : '#AE81FF',
						time : 3
					});
					
					$("#RECIPIENT").focus();
					return false;
				}
				if($("#PHONE").val() == ""){
					$("#PHONE").tips({
						side : 1,
						msg : '请输入手机号',
						bg : '#AE81FF',
						time : 3
					});
					
					$("#PHONE").focus();
					return false;
				}
				if($("#ADDRESS").val() == ""){
					$("#ADDRESS").tips({
						side : 1,
						msg : '请选择地址',
						bg : '#AE81FF',
						time : 3
					});
					
					$("#ADDRESS").focus();
					return false;
				}
				if($("#DETAILEDADD").val() == ""){
					$("#DETAILEDADD").tips({
						side : 1,
						msg : '请输入详细地址',
						bg : '#AE81FF',
						time : 3
					});
					
					$("#DETAILEDADD").focus();
					return false;
				}
				return true;
			}
		
			var city_picker = new mui.PopPicker({
				layer: 3
			});
			city_picker.setData(cityData3);
			
			$("#ADDRESS").on("tap", function() {
				setTimeout(function() {
					city_picker.show(function(items) {
						$("#ADDRESS").val((items[0] || {}).text + "-" + (items[1] || {}).text + "-" + (items[2] || {}).text); //该ID为接收城市ID字段
						$("#ADDRESS").html((items[0] || {}).text + " " + (items[1] || {}).text + " " + (items[2] || {}).text);
					});
				}, 200);
			});
		</script>
		<script type="text/javascript">
			//	<!--<input id="geographic_position" value="" name="geographicPosition" type="text">-->
			var currentLon, currentLat, city, district, street, map;
			mui.plusReady(function() {
				plus.geolocation.getCurrentPosition(translatePoint, function(e) {
					mui.toast("异常:" + e.message);
				});
			});

			function translatePoint(position) {
				currentLon = position.coords.longitude;
				currentLat = position.coords.latitude;
				city = position.address.city;
				district = position.address.district;
				street = position.address.street;
				console.log("我当前所在的城市是：" + city);
				$('#geographic_position').val(city + district + street); //输出当前位置
			}
		</script>
	</body>

</html>