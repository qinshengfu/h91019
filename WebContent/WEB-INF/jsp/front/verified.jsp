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
		<title>实名认证</title>
		<link href="static/front/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/front/css/mui.picker.min.css" />
		<link rel="stylesheet" href="static/front/fonts/iconfont.css" />
		<link href="static/front/css/style.css" rel="stylesheet" />

	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class=" mui-icon mui-icon-left-nav mui-pull-left" href="front/mine"></a>
			<h1 class="mui-title">实名认证</h1>
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
					<input type="text" placeholder="请输入姓名" id="RECIPIENT" value="${addPd.RECIPIENT}">
				</div>
				<div class="mui-input-row">
					<label>手机：</label>
					<input type="text" placeholder="请输入收货手机号" id="PHONE" value="${addPd.PHONE}">
				</div>
				<div class="mui-input-row">
					<label>身份证：</label>
					<input type="text" placeholder="请输入身份证号码" id="IDCARD" value="${addPd.IDCARD}">
				</div>
			</div>
			<!--身份证正反面-->
			<div class="addres">
				<div class="mui-input-row">
					<label>身份证正反面</label>
				</div>
				<form action="" id="Form" name="Form" method="post">
					<div class="mui-input-row" align="center">
						<div>正面</div>
						<input type="hidden" placeholder="正面" id="POSITIVE" value="${addPd.POSITIVE}">
						<div style="height: 150px;border: 1px solid #ccc;width: 250px" onclick="file1('POSITIVE');">
							<img id="imgPOSITIVE" src="${addPd.POSITIVE}" height="150" width="250">
						</div>
						<input type="file" id="filePOSITIVE" name="filePOSITIVE" style="display: none;" onchange="previewImage(this,'POSITIVE')">
					</div>
					<div class="mui-input-row" align="center">
						<div>反面</div>
						<input type="hidden" placeholder="反面" id="NEGATIVE" value="${addPd.NEGATIVE}">
						<div style="height: 150px;border: 1px solid #ccc;width: 250px" onclick="file1('NEGATIVE');">
							<img id="imgNEGATIVE" src="${addPd.NEGATIVE}" height="150" width="250">
						</div>
						<input type="file" id="fileNEGATIVE" name="fileNEGATIVE" style="display: none;" onchange="previewImage(this,'NEGATIVE')">
					</div>
				</form>
			</div>
			<!--银行卡-->
			<div class="addres">
				<div class="mui-input-row">
					<label>银行卡</label>
				</div>
				<div class="mui-input-row">
					<label>开户银行：</label>
					<input type="text" id="BANK" value="${addPd.BANK}">
				</div>
				<div class="mui-input-row">
					<label>所属支行：</label>
					<input type="text" id="BRANCH" value="${addPd.BRANCH}">
				</div>
				<div class="mui-input-row">
					<label>银行卡号：</label>
					<input type="text" id="CARDNUM" value="${addPd.CARDNUM}">
				</div>
			</div>
			<!--收货地址-->
			<div class="addres">
				<div class="mui-input-row">
					<label>收货地址</label>
				</div>
				<div class="mui-input-row">
					<label>所在地区：</label>
					<input type="text" id="ADDRESS" placeholder="请点击选择" value="${addPd.ADDRESS}" readonly>
					<i class="iconfont icon-you- you"></i>
				</div>
				<div class="mui-input-row">
					<label>详细地址：</label>
					<input type="text" placeholder="民族大道150号" id="DETAILEDADD" value="${addPd.DETAILEDADD}">
				</div>
			</div>
			<!--提现按钮-->
			<div class="curr_cash">
				<button type="button" class="logoutbtn" onclick="verified_do();">保存</button>
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
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript" src="static/front/js/jquery.form.js"></script>
		<script>
		
			function file1(Id){
				$("#file" + Id).click();
			}
		
			//上传图片
			function previewImage(file,Id) {
		        var filePath = file.value;	  
		        var fileExt = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();
		        if (!fileExt.match(/.jpg|.gif|.png|.bmp|.JPG|.GIF|.BNG|.BMP/i)) {
		            $("#img" + Id).tips({side:3,msg:'请上传图片文件!!!',bg:'#AE81FF',time:2});
					//$("#file").focus();
		            return false;
		        }
		        if ($("#file" + Id).val()) {	  
		           // $("#imghead").addClass("tx");
		            var url = "front/upload";
		            
		            //异步提交表单(先确保jquery.form.js已经引入了)
		            var options = {
		                url: url,	               
		                success: function (data) {
		                    register_path = (data + "").trim();
		                    var sta = register_path;  <%--  "<%=basePath%>"+ --%>
		                    $("#" + Id).val(register_path);
		                    $("#img" + Id).attr({src:sta });
		                    $("#img" + Id).tips({side:3,msg:'上传成功',bg:'#AE81FF',time:1});
		                    /* $("#file").focus(); */
		                }
		            };
		            $("#Form").ajaxSubmit(options);
		        }
		        
		    }
			
			function verified_do(){
				if(check()){
					var MEMBER_ID = $("#MEMBER_ID").val();
					var PHONE = $("#PHONE").val();
					var RECIPIENT = $("#RECIPIENT").val();
					var ADDRESS = $("#ADDRESS").val();
					var DETAILEDADD = $("#DETAILEDADD").val();
					var IDCARD = $("#IDCARD").val();
					var POSITIVE = $("#POSITIVE").val();
					var NEGATIVE = $("#NEGATIVE").val();
					var BANK = $("#BANK").val();
					var BRANCH = $("#BRANCH").val();
					var CARDNUM = $("#CARDNUM").val();
					
					$.ajax({
						type:'post',
						url :'front/verified_do',
						data:{
							MEMBER_ID:MEMBER_ID,PHONE:PHONE,RECIPIENT:RECIPIENT,ADDRESS:ADDRESS,DETAILEDADD:DETAILEDADD,
							IDCARD:IDCARD,POSITIVE:POSITIVE,NEGATIVE:NEGATIVE,BANK:BANK,BRANCH:BRANCH,CARDNUM:CARDNUM
							},
						success:function(data){
							if(data == 'success'){
								window.location.href = 'front/verified';
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
				if($("#IDCARD").val() == ""){
					$("#IDCARD").tips({
						side : 1,
						msg : '请输入身份证号',
						bg : '#AE81FF',
						time : 3
					});
					
					$("#IDCARD").focus();
					return false;
				}
				if($("#POSITIVE").val() == ""){
					$("#imgPOSITIVE").tips({
						side : 1,
						msg : '请上传正面',
						bg : '#AE81FF',
						time : 3
					});
					
					return false;
				}
				if($("#NEGATIVE").val() == ""){
					$("#imgNEGATIVE").tips({
						side : 1,
						msg : '请上传反面',
						bg : '#AE81FF',
						time : 3
					});
					return false;
				}
				if($("#BANK").val() == ""){
					$("#BANK").tips({
						side : 1,
						msg : '请输入开户银行',
						bg : '#AE81FF',
						time : 3
					});
					
					$("#BANK").focus();
					return false;
				}
				if($("#BRANCH").val() == ""){
					$("#BRANCH").tips({
						side : 1,
						msg : '请输入所属支行',
						bg : '#AE81FF',
						time : 3
					});
					
					$("#BRANCH").focus();
					return false;
				}
				if($("#CARDNUM").val() == ""){
					$("#CARDNUM").tips({
						side : 1,
						msg : '请输入银行卡号',
						bg : '#AE81FF',
						time : 3
					});
					
					$("#CARDNUM").focus();
					return false;
				}
				if($("#ADDRESS").val() == ""){
					$("#ADDRESS").tips({
						side : 1,
						msg : '请选择地址',
						bg : '#AE81FF',
						time : 3
					});
					
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