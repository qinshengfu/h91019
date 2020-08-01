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
		<title>提现</title>
		<link href="static/front/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/front/fonts/iconfont.css" />
		<link href="static/front/css/style.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/front/css/mui.picker.min.css" />

	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-icon mui-icon-left-nav mui-pull-left" href="front/mine"></a>
			<h1 class="mui-title">提现</h1>
		</header>
		<div class="mui-content">
			<!--联系人-->
			<div class="addres">
				<div class="mui-input-row"></div>
				<div class="mui-input-row">
					<label>余额：</label>
					<input type="text" readonly value="${member.COMMISSION}">
					<input type="hidden" value="${member.MEMBER}" id="MEMBER">
				</div>
				<div class="mui-input-row">
					<label>提现金额：</label>
					<input type="text" placeholder="" onchange="positive_integer(this);" id="MONEY">
				</div>
				<div class="mui-input-row">
					<label>提现方式：</label>
					<input type="text" id="RSTYLE" placeholder="">
				</div>
				<div class="mui-input-row">
					<label>真实姓名：</label>
					<input type="text" placeholder="" id="NAME">
				</div>
				<!-- <div class="mui-input-row">
					<label>到账账号：</label>
					<input type="text" placeholder="请输入提现账号" id="ACCOUNT">
				</div> -->
				<div class="mui-input-row" id="fileImg" style="display: none;">
					<form action="" name="Form" id="Form" method="post">
					<label>上传图片：</label>
					<input type="hidden" name="IMGPATH" id="IMGPATH" value="">
					<input type="file" id="file" name="file" onchange="previewImage(this);"/>
					</form>
				</div>
				<div class="mui-input-row" style="padding:11px 15px;color: #FF8E43;">注：扣除提现手续费${parameter.FEE}%，可以100的倍数提现。</div>
			</div>
			<!--提现按钮-->
			<div class="curr_cash">
				<button type="button" class="logoutbtn" onclick="withdrawal_do();">确定</button>
			</div>
			<div align="center"><img id="img" alt="" src="" height="150" width="150"></div>
		</div>

		<script src="static/front/js/mui.min.js"></script>
		<script type="text/javascript" src="static/front/js/city.data-3.js" ></script>
		<script type="text/javascript" src="static/front/js/mui.poppicker.js"></script>
		<script type="text/javascript" src="static/front/js/mui.picker.min.js" ></script>
		<script type="text/javascript" src="static/front/js/jquery.min.js" ></script>
		<script type="text/javascript" src="static/front/js/flexible.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript" src="static/front/js/jquery.form.js"></script>
		
		<script type="text/javascript">
		
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
		                    $("#IMGPATH").val(register_path);
		                    $("#img").attr({src:sta });
		                    $("#file").tips({side:3,msg:'上传成功',bg:'#AE81FF',time:1});
		                    /* $("#file").focus(); */
		                }
		            };
		            $("#Form").ajaxSubmit(options);
		        }
		    }
		
			function withdrawal_do(){
				if(check()){
					var MEMBER = $("#MEMBER").val();
					var MONEY = $("#MONEY").val();
					var RSTYLE = $("#RSTYLE").val();
					/* var ACCOUNT = $("#ACCOUNT").val(); */
					var NAME = $("#NAME").val();
					var IMGPATH = $("#IMGPATH").val();
					if(parseInt(MONEY)%100 == 0){
						$.ajax({
							type:'post',
							url :'front/withdrawal_do',
							data:{MEMBER:MEMBER,MONEY:MONEY,RSTYLE:RSTYLE,NAME:NAME,IMGPATH:IMGPATH},
							success:function(data){
								if(data == 'success'){
									alert('提现申请成功');
									window.location.href = 'front/withdrawal';
								}else if(data == 'money'){
									//佣金不足
									$("#MONEY").tips({
										side : 3,
										msg : '佣金不足',
										bg : '#AE81FF',
										time : 3
									});
									$("#MONEY").focus();
								}else if(data == 'bank'){
									alert('请完善信息');
									window.location.href = 'front/verified';
								}else if(data == 'txmoney'){
									alert('提现额度不足，剩余提现额度${member.TXMONEY}')
								}
							}
						});
					}else{
						alert('提现金额不符');
					}
				}
			}
		
			function check(){
				if($("#MONEY").val() == ""){
					$("#MONEY").tips({
						side : 3,
						msg : '请输入提现金额',
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
				if($("#NAME").val() == ""){
					$("#NAME").tips({
						side : 3,
						msg : '请输入真实姓名',
						bg : '#AE81FF',
						time : 3
					});
					$("#NAME").focus();
					return false;
				}
				/* if($("#ACCOUNT").val() == ""){
					$("#ACCOUNT").tips({
						side : 3,
						msg : '请输入到账账号',
						bg : '#AE81FF',
						time : 3
					});
					$("#ACCOUNT").focus();
					return false;
				} */
				if($("#IMGPATH").val() == ""){
					$("#file").tips({
						side : 3,
						msg : '请上传图片',
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
							if(showUserPickerButton.value == '银行卡'){
								//$("#IMGPATH").val('银行卡');
								doc.getElementById('IMGPATH').value = '银行卡';
							}else{
								//$("#fileImg").css('dispaly','inline');
								doc.getElementById('fileImg').style.display="inline";
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