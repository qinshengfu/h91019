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
			<h1 class="mui-title">兑换积分</h1>
		</header>
		
		<div class="prot_bot">
			<a href="front/exchange"><button type="button" class="mallexchange" onclick="">积分兑换</button></a>
			<a href="front/exchange1"><button type="button" class="mallexchange" onclick="">积分互转</button></a>
			<a href="front/exchange2"><button type="button" class="mallexchange" onclick="">财富兑换</button></a>
		</div>
		
		<div class="mui-content">
			<div class="rep_all">
				<div class="mui-input-row">
					<label>佣金：</label>
					<input type="text" value="${member.COMMISSION}" readonly="readonly">
					<input type="hidden" value="${member.MEMBER}" id="MEMBER">
				</div>
				<div class="mui-input-row">
					<label>积分：</label>
					<input type="text" id="INTEGRAL" placeholder="请输入兑换量" onchange="positive_integer(this);">
				</div>
			</div>
			<div class="mui-input-row" style="padding:11px 15px;color: #FF8E43;">
					注：佣金：积分 = 1 ：${parameter.EXCHANGE}<br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最低兑换1000
			</div>
			<!--提现按钮-->
			<div class="curr_cash">
				<button type="button" class="logoutbtn" onclick="exchange_do();">确定</button>
			</div>
		</div>
		<script type="text/javascript" src="static/front/js/jquery.min.js"></script>
		<script src="static/front/js/mui.min.js"></script>
		<script type="text/javascript" src="static/front/js/flexible.js" ></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
		<script type="text/javascript">
		
			function exchange_do(){
				var MEMBER = $("#MEMBER").val();
				var INTEGRAL = $("#INTEGRAL").val();
				if(INTEGRAL != ''){
					$.ajax({
						type:'post',
						url :'front/exchange_do',
						data:{INTEGRAL:INTEGRAL,MEMBER:MEMBER},
						success:function(data){
							if(data == 'success'){
								alert('兑换成功');
								window.location.href = 'front/exchange';
							}else if(data == 'moneyError'){
								alert('佣金不足');
							}
						}
					});
				}else{
					$("#INTEGRAL").tips({
						side : 3,
						msg : '请输入兑换数量',
						bg : '#AE81FF',
						time : 3
					});
				}
			}
		
			//正整数
			function positive_integer(self){
				var reg = /^[1-9][0-9]{3,}\d*$/;
				if(!reg.test(self.value)){
					self.value = '';
					self.focus();
				}
			}
		</script>
		
	</body>

</html>