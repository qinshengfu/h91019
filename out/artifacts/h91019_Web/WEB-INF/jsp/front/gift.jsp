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
		<title>赠送星级</title>
		<link href="static/front/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/front/fonts/iconfont.css" />
		<link href="static/front/css/style.css" rel="stylesheet" />

	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-icon mui-icon-left-nav mui-pull-left" href="front/mine"></a>
			<h1 class="mui-title">赠送星级</h1>
		</header>
		
		<div class="mui-content">
			<div class="rep_all">
				<div class="mui-input-row">
					<label>多余星级：</label>
					<input type="text" value="${member.GIFT}" readonly="readonly">
					<input type="hidden" value="${member.MEMBER_ID}" id="MEMBER_ID">
				</div>
				<div class="mui-input-row">
					<label>赠送账号：</label>
					<input type="text" id="MEMBER" placeholder="请输入赠送账号">
				</div>
				<div class="mui-input-row">
					<label>赠送数量：</label>
					<input type="text" id="GIFT" placeholder="请输入赠送数量" onchange="positive_integer(this);">
				</div>
			</div>
			<div class="mui-input-row" style="padding:11px 15px;color: #FF8E43;">
				
			</div>
			<!--提现按钮-->
			<div class="curr_cash">
				<button type="button" class="logoutbtn" onclick="gift_do();">确定</button>
			</div>
		</div>
		<script type="text/javascript" src="static/front/js/jquery.min.js"></script>
		<script src="static/front/js/mui.min.js"></script>
		<script type="text/javascript" src="static/front/js/flexible.js" ></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
		<script type="text/javascript">
		
			function gift_do(){
				var MEMBER = $("#MEMBER").val();
				var GIFT = $("#GIFT").val();
				var MEMBER_ID = $("#MEMBER_ID").val();
				if(MEMBER != ''){
					if(GIFT != ''){
						$.ajax({
							type:'post',
							url :'front/gift_do',
							data:{GIFT:GIFT,MEMBER:MEMBER,MEMBER_ID:MEMBER_ID},
							success:function(data){
								if(data == 'success'){
									alert('赠送成功');
									window.location.href = 'front/gift';
								}else if(data == 'giftError'){
									alert('多余星级不足');
								}else if(data == 'memberError'){
									alert('赠送账号不存在');
								}
							}
						});
					}else{
						$("#GIFT").tips({
							side : 3,
							msg : '请输入赠送数量',
							bg : '#AE81FF',
							time : 3
						});
						$("#GIFT").focus();
					}
				}else{
					$("#MEMBER").tips({
						side : 3,
						msg : '请输入赠送账号',
						bg : '#AE81FF',
						time : 3
					});
					$("#MEMBER").focus();
				}
			}
		
			//正整数
			function positive_integer(self){
				var reg = /^\d*$/;
				if(!reg.test(self.value)){
					self.value = '';
					self.focus();
				}
			}
		</script>
		
	</body>

</html>