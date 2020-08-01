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
		<title>留言</title>
		<link href="static/front/css/mui.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/front/fonts/iconfont.css" />
		<link href="static/front/css/style.css" rel="stylesheet" />
		<script type="text/javascript" src="static/front/js/jquery.min.js"></script>
		<script src="static/front/js/mui.min.js"></script>
		<script type="text/javascript" src="static/front/js/flexible.js" ></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript" src="static/front/js/jquery.form.js"></script>
		<script>
			$(document).ready(function() {
				window.location.href ="front/liuyan?#bottom"; //注意有个#
			});
		</script>
	</head>

	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-icon mui-icon-left-nav mui-pull-left" href="front/mine"></a>
			<h1 class="mui-title">留言</h1>
		</header>
		
		<div class="mui-content">
			<div class="rep_all">
				
			</div>
			<div class="mui-input-row" style="padding:11px 15px;color: #FF8E43;padding-bottom: 100px;">
				<c:forEach items="${listAll}" var="var">
					<p align="center">${var.TIME}</p>
					<p <c:if test="${var.MARK == '1'}">align="right"</c:if>>
						<c:if test="${var.IMG == 'yes'}"><img alt="" src="${var.CONTENT}" width="60" height="100"></c:if>
						<c:if test="${var.IMG == 'no'}">${var.CONTENT}</c:if>
					</p>
				</c:forEach>
				<a name="bottmom" id="bottom"></a>
			</div>
			<!--提现按钮-->
			<form action="" id="Form" name="Form" method="post">
			<input type="hidden" name="CONTENT1" id="CONTENT1" value="">
			<div class="curr_cash" style="position: fixed;bottom: 0;width: 100%;border-top: 1px solid #ccc;background: #fff;padding-top: 10px;"><!-- relative -->
				<p style="clear: both;display: block;"><input type="file" id="file" name="file" onchange="previewImage(this);" style="width: 70%;"/><input onclick="liuyan1_do();" type="button" value="发送"></p>
				<p style="float: left;margin-bottom: 0;"><input type="text" id="CONTENT" style="margin-bottom: 0;" value=""></p>
				<p style="float: left;"><button type="button" class="logoutbtn" onclick="liuyan_do();" style="width: 60px;">确定</button></p>
			</div>
			</form>
		</div>
		
		<script type="text/javascript">
		
			$(document).ready(function() {
				window.location.href ="front/liuyan?#bottom"; 
			});
		
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
		                    $("#CONTENT1").val(register_path);
		                    //$("#img").attr({src:sta });
		                    $("#file").tips({side:3,msg:'上传成功',bg:'#AE81FF',time:1});
		                    /* $("#file").focus(); */
		                }
		            };
		            $("#Form").ajaxSubmit(options);
		        }
		    }
			
			function liuyan1_do(){
				var MEMBER = '${member.MEMBER}';
				var CONTENT1 = $("#CONTENT1").val();
				if(CONTENT != ''){
					$.ajax({
						type:'post',
						url :'front/liuyan1_do',
						data:{MEMBER:MEMBER,CONTENT1:CONTENT1},
						success:function(data){
							if(data == 'success'){
								window.location.href = 'front/liuyan';
							}
						}
					});
				}else{
					$("#file").tips({side:3,msg:'请上传图片文件!!!',bg:'#AE81FF',time:2});
				}
			}
			
			function liuyan_do(){
				var MEMBER = '${member.MEMBER}';
				var CONTENT = $("#CONTENT").val();
				if(CONTENT != ''){
					$.ajax({
						type:'post',
						url :'front/liuyan_do',
						data:{MEMBER:MEMBER,CONTENT:CONTENT},
						success:function(data){
							if(data == 'success'){
								window.location.href = 'front/liuyan';
							}
						}
					});
				}else{
					$("#CONTENT").focus();
				}
			}
		</script>
		
	</body>

</html>