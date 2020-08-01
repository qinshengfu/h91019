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
			<h1 class="mui-title">兑换财富</h1>
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
					<label>数量：</label>
					<input type="text" id="WEALTH" placeholder="请输入兑换量" onchange="positive_integer(this);">
				</div>
			</div>
			<div class="mui-input-row" style="padding:11px 15px;color: #FF8E43;">
					注：佣金：财富 = 1 ：1<br>
			</div>
			<!--提现按钮-->
			<div class="curr_cash">
				<button type="button" class="logoutbtn" onclick="exchange2_do();">确定</button>
			</div>
		</div>
		<script type="text/javascript" src="static/front/js/jquery.min.js"></script>
		<script src="static/front/js/mui.min.js"></script>
		<script type="text/javascript" src="static/front/js/flexible.js" ></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
		<script type="text/javascript">
		
			function exchange2_do(){
				var MEMBER = $("#MEMBER").val();
				var WEALTH = $("#WEALTH").val();
				if(WEALTH != ''){
					$.ajax({
						type:'post',
						url :'front/exchange2_do',
						data:{WEALTH:WEALTH,MEMBER:MEMBER},
						success:function(data){
							if(data == 'success'){
								alert('兑换成功');
								window.location.href = 'front/exchange2';
							}else if(data == 'moneyError'){
								alert('佣金不足');
							}
						}
					});
				}else{
					$("#WEALTH").tips({
						side : 3,
						msg : '请输入兑换数量',
						bg : '#AE81FF',
						time : 3
					});
				}
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