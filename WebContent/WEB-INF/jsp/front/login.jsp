<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html class="ui-page-login">

	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>登录</title>
		<link href="static/front/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/front/fonts/iconfont.css" />
		<link href="static/front/css/style.css" rel="stylesheet" />

	</head>
	<style>
		.chingcolor a{
			font-size: 13px;
		}
		.spliter{
			font-size: 13px;
		}
	</style>
	<body class="loginbg">
		<header class="mui-bar mui-bar-nav nobg">
			<!-- <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left whitecolor"></a> -->
			<h1 class="mui-title whitecolor">登录</h1>
		</header>
		<div class="mui-content nobg">
			<div class="mui-text-center login">
				<img src="static/front/images/logo.png" alt="logo" class="logo">
			</div>
			<div class="formbox">
				<div class="forminp">
					<i class="iconfont icon-yonghu4 chingcolor"></i>
					<input type="text" placeholder="请输入账号" id="MEMBER" /> 
				</div>
				<div class="forminp">
					<i class="iconfont icon-mima chingcolor"></i>
					<input type="password" placeholder="请输入密码" id="PWD" />
				</div>
				<div class="forminp" style="position: relative;">
					<i class="iconfont icon-anquan2 chingcolor"></i>
					<input type="text" placeholder="请输入验证码" id="code" />
					<img alt="" id="codeImg" src="static/front/images/loginbg.png" height="30" width="50" style="position: absolute;right: 10px;">
					<!-- <input type="button" value="获取验证码" onclick="settime(this)" /> -->
				</div>
				<div class="mui-content-padded">
					<button id='login' type="button" class="mui-btn mui-btn-block stmbutton" onclick="login();">登录</button>
					<div class="mui-content-padded mui-text-center chingcolor">
						<a href="static/front/中首农业.apk">下载APP</a> <span class="spliter">|</span>
						<a id='reg' href="release/register">注册账号</a> <span class="spliter">|</span>
						<a id='forgetPassword' href="release/forget">忘记密码</a>
					</div>
				</div>
			</div>

		</div>
		<script src="static/front/js/mui.min.js"></script>
		<script src="static/front/js/jquery-1.11.3.min.js"></script>
		<script src="static/js/jquery.tips.js"></script>
		<script>
		
			//登录
			function login(){
				if(check()){
					var MEMBER = $("#MEMBER").val();
					var PWD = $("#PWD").val();
					var code = "qq313596790fh"+MEMBER+",fh,"+PWD+"QQ978336446fh"+",fh,"+$("#code").val();
	
					$.ajax({
						type: "POST",
						url: '<%=basePath%>release/login_do',
						data:{KEYDATA:code},
						success: function(data) {
							if("success" == data){
								window.location.href="<%=basePath%>front/index";
							}else if("usererror" == data){
								$("#MEMBER").tips({
									side: 1,
									msg : "账号或密码有误",
									bg : '#FF5080',
									time: 15
								});
								$("#MEMBER").focus();
								changeCode1();
							}else if("codeerror" == data){
								$("#code").tips({
									side: 1,
									msg: "验证码输入有误",
									bg : '#FF5080',
									time : 15
								});
								$("#code").focus();
								changeCode1();
							}		
						}
					});
				}
			}
		
			//效验
			function check(){
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
				if($("#PWD").val() == ""){
					$("#PWD").tips({
						side : 1,
						msg : '请输入密码',
						bg : '#AE81FF',
						time : 3
					});
					
					$("#PWD").focus();
					return false;
				}
				if($("#code").val() == ""){
					$("#code").tips({
						side : 1,
						msg : '请输入验证码',
						bg : '#AE81FF',
						time : 3
					});
					
					$("#code").focus();
					return false;
				}
				
				return true;
			}
			
			/* 验证码 */
			function changeCode1() {
				$("#codeImg").attr("src", "code.do?t=" + genTimestamp());
			}
			
			function genTimestamp() {
				var time = new Date();
				return time.getTime();
			}
			
			$(document).ready(function() {
				changeCode1();
				$("#codeImg").bind("click", changeCode1);
			});
		
			/* var countdown = 60;

			function settime(obj) {    
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
					settime(obj)
				}    , 1000)
			} */

			//键盘回车事件，执行登录
			$(document).keyup(function(event) {
				if (event.keyCode == 13) {
					$("#login").trigger("click");
				}
			});
			
		</script>

	</body>

</html>