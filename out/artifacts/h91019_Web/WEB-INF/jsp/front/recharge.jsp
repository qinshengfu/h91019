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
		<title>充值</title>
		<link href="static/front/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/front/css/mui.picker.min.css" />
		<link rel="stylesheet" href="static/front/fonts/iconfont.css" />
		<link href="static/front/css/style.css" rel="stylesheet" />

	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-icon mui-icon-left-nav mui-pull-left" href="front/mine"></a>
			<h1 class="mui-title">充值</h1>
		</header>
		<div class="mui-content">
			<!--联系人-->
			<div class="addres">
				<div class="mui-input-row"></div>
				<div class="mui-input-row">
					<label>余额：</label>
					<input type="number" readonly value="${member.WEALTH}">
					<input type="hidden" value="${member.MEMBER}" id="MEMBER">
				</div>
				<div class="mui-input-row">
					<label>充值金额：</label>
					<input type="text" placeholder="请输入充值金额" onchange="positive_integer(this);" id="MONEY">
				</div>
				<div class="mui-input-row">
					<label>充值方式：</label>
					<input type="text" id="RSTYLE"><!-- id="showUserPicker" -->
				</div>
				<!-- <div class="mui-input-row">
					<label>交易密码：</label>
					<input type="text" placeholder="请输入交易密码">
				</div> -->
				<div class="mui-input-row">
					<form action="" name="Form" id="Form" method="post">
					<label>上传凭证：</label>
					<input type="hidden" name="CERTIFICATE" id="CERTIFICATE" value="">
					<input type="file" id="file" name="file" onchange="previewImage(this);"/>
					</form>
				</div>
				<div class="mui-input-row" style="padding:11px 15px;color: #FF8E43;">
					注：<br>
					<%-- 微信：${parameter.WECHAT}<br>
					支付宝：${parameter.ALIPAY}<br> --%>
					银行卡：${parameter.BANKCARD}
				</div>
				<div align="center">
					<img id="img123" alt="" src="" style="width: 150px;height: 150px;">
				</div>

			</div>
			<!--提现按钮-->
			<div class="curr_cash">
				<button type="button" class="logoutbtn" onclick="recharge_do();">确定</button>
			</div>
			<div align="center"><img id="img" alt="" src="" height="150" width="150"></div>
		</div>

		<script src="static/front/js/mui.min.js"></script>
		<script type="text/javascript" src="static/front/js/city.data-3.js"></script>
		<script type="text/javascript" src="static/front/js/mui.poppicker.js"></script>
		<script type="text/javascript" src="static/front/js/mui.picker.min.js"></script>
		<script type="text/javascript" src="static/front/js/jquery.min.js"></script>
		<script type="text/javascript" src="static/front/js/flexible.js"></script>
		<script type="text/javascript" src="static/front/js/jquery.form.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script>
		
			function recharge_do(){
				if(check()){
					var MEMBER = $("#MEMBER").val();
					var MONEY = $("#MONEY").val();
					var RSTYLE = $("#RSTYLE").val();
					var CERTIFICATE = $("#CERTIFICATE").val();
					$.ajax({
						type:'post',
						url :'front/recharge_do',
						data:{PHONE:MEMBER,MONEY:MONEY,RSTYLE:RSTYLE,CERTIFICATE:CERTIFICATE},
						success:function(data){
							if(data == 'success'){
								alert('上传凭证成功');
								window.location.href = 'front/recharge';
							}
						}
					});
				}
			}
		
			function check(){
				if($("#MONEY").val() == ""){
					$("#MONEY").tips({
						side : 3,
						msg : '请输入充值金额',
						bg : '#AE81FF',
						time : 3
					});
					$("#MONEY").focus();
					return false;
				}
				if($("#RSTYLE").val() == ""){
					$("#RSTYLE").tips({
						side : 3,
						msg : '请选择支付方式',
						bg : '#AE81FF',
						time : 3
					});
					$("#RSTYLE").focus();
					return false;
				}
				if($("#CERTIFICATE").val() == ""){
					$("#file").tips({
						side : 3,
						msg : '请上传凭证',
						bg : '#AE81FF',
						time : 3
					});
					return false;
				}
				return true;
			}
		
			//正整数
			function positive_integer(self){
				var reg = /^[1-9]\d*$/;
				if(!reg.test(self.value)){
					self.value = '';
					self.focus();
				}
			}
		
			//上传图片
			function previewImage(file) {
		        var filePath = file.value;	 
		        var fileExt = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();
		        if (!fileExt.match(/.jpg|.gif|.png|.bmp|.JPG|.GIF|.BNG|.BMP/i)) {
		            $("#file").tips({side:3,msg:'请上传图片文件!!!',bg:'#AE81FF',time:2});
					//$("#file").focus();
		            return false;
		        }
		        if ($("#file").val()) {	  
		           // $("#imghead").addClass("tx");
		            var url = "front/upload";
		            
		            //异步提交表单(先确保jquery.form.js已经引入了)
		            var options = {
		                url: url,	               
		                success: function (data) {
		                    register_path = (data + "").trim();
		                    var sta = register_path;  <%--  "<%=basePath%>"+ --%>
		                    $("#CERTIFICATE").val(register_path);
		                    $("#img").attr({src:sta });
		                    $("#file").tips({side:3,msg:'上传成功',bg:'#AE81FF',time:1});
		                    /* $("#file").focus(); */
		                }
		            };
		            $("#Form").ajaxSubmit(options);
		        }
		    }
		
			(function($, doc) {
				$.init();
				$.ready(function() {
					var userPicker = new $.PopPicker();
					userPicker.setData([{
						value: '1',
						text: '支付宝'
					}, {
						value: '2',
						text: '微信'
					}, {
						value: '3',
						text: '银行卡'
					}]);

					var showUserPickerButton = doc.getElementById('RSTYLE');
					showUserPickerButton.addEventListener('tap', function(event) {
						userPicker.show(function(items) {
							showUserPickerButton.value = items[0].text;
							if(showUserPickerButton.value == '支付宝'){
								doc.getElementById('img123').src = '${parameter.ALIPAY}';
							}
							if(showUserPickerButton.value == '微信'){
								//$("#img").attr({src:'${parameter.WECHAT}' });
								doc.getElementById('img123').src = '${parameter.WECHAT}';
							}
							//返回 false 可以阻止选择框的关闭
							//return false;
						});
					}, false);
				});
			})(mui, document);
		</script>

	</body>

</html>