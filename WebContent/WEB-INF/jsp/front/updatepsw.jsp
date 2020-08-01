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
		<title>修改密码</title>
		<link href="static/front/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/front/fonts/iconfont.css" />
		<link href="static/front/css/style.css" rel="stylesheet" />

	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-icon mui-icon-left-nav mui-pull-left" href="front/mine"></a>
			<h1 class="mui-title">修改密码</h1>
		</header>
		<div class="mui-content">
			<div class="rep_all">
				<div class="mui-input-row">
					<label>手机号：</label>
					<input type="text" placeholder="请输入手机号" id="PHONE" value="${member.PHONE}" readonly="readonly">
				</div>
				<div class="mui-input-row">
					<label>验证码：</label>
					<input type="text" placeholder="请输入短信验证码" id="inCode">
					<input class="weui-vcode-btn" type="button" id="btn" value="获取验证码" onclick="settime(this,'1')" />
				</div>
				<div class="mui-input-row">
					<label>旧密码：</label>
					<input type="password" placeholder="请输入旧密码" id="oldPwd">
				</div>
				<div class="mui-input-row">
					<label>新密码：</label>
					<input type="password" placeholder="请输入密码" id="newPwd">
				</div>
				<div class="mui-input-row">
					<label>确认密码：</label>
					<input type="password" placeholder="请再次输入密码" id="newPwd1">
				</div>
			</div>
			<!--提现按钮-->
			<div class="curr_cash">
				<button type="button" class="logoutbtn" onclick="updatepsw_do();">提交</button>
			</div>
		</div>
		<script type="text/javascript" src="static/front/js/jquery.min.js"></script>
		<script src="static/front/js/mui.min.js"></script>
		<script type="text/javascript" src="static/front/js/flexible.js" ></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		
			function updatepsw_do(){
				if(check()){
					var PHONE = $("#PHONE").val();
					var inCode = $("#inCode").val();
					var oldPwd = $("#oldPwd").val();
					var newPwd = $("#newPwd").val();
					var MEMBER = '${member.MEMBER}';
					
					$.ajax({
						type:'post',
						url :'front/updatepsw_do',
						data:{PHONE:PHONE,inCode:inCode,oldPwd:oldPwd,newPwd:newPwd,MEMBER:MEMBER},
						success:function(data){
							if(data == 'success'){
								alert('修改成功');
								window.location.href = 'release/login';
							}else if(data == 'code'){
								//短信验证码错误
								$("#inCode").tips({
									side : 3,
									msg : '短信验证码错误',
									bg : '#AE81FF',
									time : 3
								});
								$("#inCode").focus();
							}else if(data == 'oldPwd'){
								//旧密码错误
								$("#oldPwd").tips({
									side : 3,
									msg : '旧密码错误',
									bg : '#AE81FF',
									time : 3
								});
								$("#oldPwd").focus();
							}
						}
					});
				}
			}
		
			function check(){
				if($("#inCode").val() == ""){
					$("#inCode").tips({
						side : 3,
						msg : '请输入短信验证码',
						bg : '#AE81FF',
						time : 3
					});
					$("#inCode").focus();
					return false;
				}
				if($("#oldPwd").val() == ""){
					$("#oldPwd").tips({
						side : 3,
						msg : '请输入旧密码',
						bg : '#AE81FF',
						time : 3
					});
					$("#oldPwd").focus();
					return false;
				}
				if($("#newPwd").val() == ""){
					$("#newPwd").tips({
						side : 3,
						msg : '请输入新密码',
						bg : '#AE81FF',
						time : 3
					});
					$("#newPwd").focus();
					return false;
				}
				if($("#newPwd").val() != $("#newPwd1").val()){
					$("#newPwd1").tips({
						side : 3,
						msg : '两次新密码不一致!',
						bg : '#AE81FF',
						time : 3
					});
					$("#newPwd1").focus();
					return false;
				}
				return true;
			}
		
			var countdown = 60;

			function settime(obj,mark) { 
				var PHONE = $("#PHONE").val(); 
				if(PHONE != ''){
					if(mark == '1'){
						$.ajax({
							type:'post',
							url :'release/sendCode',
							data:{PHONE:PHONE},
							success:function(data){
								
							}
						});
					}  
					
					if(countdown == 0) {        
						obj.removeAttribute("disabled");           
						obj.value = "获取验证码";        
						countdown = 60;        
						return;    
					} else {        
						obj.setAttribute("disabled", true);        
						obj.value = "重新发送(" + countdown + ")";
						countdown--;    
					}
	
					setTimeout(function() {    
						settime(obj,'0')
					}    , 1000)
				}else{
					
				}
			}
		</script>

	</body>

</html>