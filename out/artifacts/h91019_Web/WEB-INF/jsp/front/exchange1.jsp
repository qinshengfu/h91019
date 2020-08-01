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
		<title>兑换</title>
		<link href="static/front/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/front/fonts/iconfont.css" />
		<link href="static/front/css/style.css" rel="stylesheet" />

	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-icon mui-icon-left-nav mui-pull-left" href="front/mine"></a>
			<h1 class="mui-title">互转</h1>
		</header>
		
		<div class="prot_bot">
			<a href="front/exchange"><button type="button" class="mallexchange" onclick="">积分兑换</button></a>
			<a href="front/exchange1"><button type="button" class="mallexchange" onclick="">积分互转</button></a>
			<a href="front/exchange2"><button type="button" class="mallexchange" onclick="">财富兑换</button></a>
		</div>
		
		<div class="mui-content">
			<div class="rep_all">
				<div class="mui-input-row">
					<label>积分：</label>
					<input type="text" value="${member.INTEGRAL}">
					<input type="hidden" value="${member.MEMBER}">
				</div>
				<div class="mui-input-row">
					<label>账号：</label>
					<input type="text" value="" id="MEMBER">
				</div>
				<div class="mui-input-row">
					<label>积分：</label>
					<input type="text" id="INTEGRAL" placeholder="请输入数量" onchange="positive_integer(this);">
				</div>
			</div>
			<div class="mui-input-row" style="padding:11px 15px;color: #FF8E43;">
					注：积分满${parameter.MUTUAL}可以${parameter.MUTUALTIMES}倍数互转
			</div>
			<!--提现按钮-->
			<div class="curr_cash">
				<button type="button" class="logoutbtn" onclick="exchange1_do();">确定</button>
			</div>
		</div>
		<script type="text/javascript" src="static/front/js/jquery.min.js"></script>
		<script src="static/front/js/mui.min.js"></script>
		<script type="text/javascript" src="static/front/js/flexible.js" ></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
		<script type="text/javascript">
		
			function exchange1_do(){
				var MUTUAL = '${parameter.MUTUAL}';
				var MUTUALTIMES = '${parameter.MUTUALTIMES}';
				var INTEGRAL = $("#INTEGRAL").val();
				var integral = '${member.INTEGRAL}';
				if(parseInt(integral) >= parseInt(MUTUAL)){
					if(check()){
						if(parseInt(INTEGRAL)%parseInt(MUTUALTIMES) == 0){
							var MEMBER = $("#MEMBER").val();
							var MEMBER_ID = '${member.MEMBER_ID}';
							$.ajax({
								type:'post',
								url :'front/exchange1_do',
								data:{INTEGRAL:INTEGRAL,MEMBER:MEMBER,MEMBER_ID:MEMBER_ID},
								success:function(data){
									if(data == 'success'){
										alert('转账成功');
										window.location.href = 'front/exchange1';
									}else if(data == 'moneyError'){
										alert('积分不足');
									}else if(data == 'memberError'){
										alert('收账方不存在');
									}
								}
							});
						}else{
							$("#INTEGRAL").tips({
								side : 3,
								msg : '转账数量不符',
								bg : '#AE81FF',
								time : 3
							});
							$("#INTEGRAL").focus();
						}
					}
				}else{
					alert('未达到转账条件');
				}
			}
		
			function check(){
				if($("#MEMBER").val() == ''){
					$("#MEMBER").tips({
						side : 3,
						msg : '请输入收账账号',
						bg : '#AE81FF',
						time : 3
					});
					$("#MEMBER").focus();
					return false;
				}
				if($("#INTEGRAL").val() == ''){
					$("#INTEGRAL").tips({
						side : 3,
						msg : '请输入转账数量',
						bg : '#AE81FF',
						time : 3
					});
					$("#INTEGRAL").focus();
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
		</script>
		
	</body>

</html>