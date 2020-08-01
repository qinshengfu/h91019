<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
	<!-- 下拉框 -->
	<link rel="stylesheet" href="static/ace/css/chosen.css" />
	<!-- jsp文件头和头部 -->
	<%@ include file="../../system/index/top.jsp"%>
	<!-- 日期框 -->
	<link rel="stylesheet" href="static/ace/css/datepicker.css" />
</head>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					
					<form action="member/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="MEMBER_ID" id="MEMBER_ID" value="${pd.MEMBER_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">用户名:</td>
								<td><input type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="20" placeholder="这里输入用户名" title="用户名" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">手机号:</td>
								<td><input type="text" name="PHONE" id="PHONE" value="${pd.PHONE}" maxlength="20" placeholder="这里输入手机号" title="手机号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">登录密码:</td>
								<td><input type="text" name="PWD" id="PWD" value="${pd.PWD}" maxlength="20" placeholder="这里输入登录密码" title="登录密码" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">推荐码:</td>
								<td><input type="text" name="REFERRER" id="REFERRER" value="${pd.REFERRER}" maxlength="20" placeholder="这里输入推荐码" title="推荐码" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">佣金:</td>
								<td><input type="number" name="COMMISSION" id="COMMISSION" value="${pd.COMMISSION}" maxlength="32" placeholder="这里输入佣金" title="佣金" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">积分:</td>
								<td><input type="number" name="INTEGRAL" id="INTEGRAL" value="${pd.INTEGRAL}" maxlength="32" placeholder="这里输入积分" title="积分" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">财富:</td>
								<td><input type="number" name="WEALTH" id="WEALTH" value="${pd.WEALTH}" maxlength="32" placeholder="这里输入财富" title="财富" style="width:98%;"/></td>
							</tr>
							<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">注册时间:</td>
								<td><input class="span10 date-picker" name="TIME" id="TIME" value="${pd.TIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="注册时间" title="注册时间" style="width:98%;"/></td>
							</tr> --%>
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
								</td>
							</tr>
						</table>
						</div>
						<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
					</form>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
</div>
<!-- /.main-container -->


	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		//保存
		function save(){
			if($("#NAME").val()==""){
				$("#NAME").tips({
					side:3,
		            msg:'请输入用户名',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#NAME").focus();
			return false;
			}
			if($("#PHONE").val()==""){
				$("#PHONE").tips({
					side:3,
		            msg:'请输入手机号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PHONE").focus();
			return false;
			}
			if($("#PWD").val()==""){
				$("#PWD").tips({
					side:3,
		            msg:'请输入登录密码',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PWD").focus();
			return false;
			}
			if($("#REFERRER").val()==""){
				$("#REFERRER").tips({
					side:3,
		            msg:'请输入推荐码',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#REFERRER").focus();
			return false;
			}
			if($("#COMMISSION").val()==""){
				$("#COMMISSION").tips({
					side:3,
		            msg:'请输入佣金',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#COMMISSION").focus();
			return false;
			}
			if($("#INTEGRAL").val()==""){
				$("#INTEGRAL").tips({
					side:3,
		            msg:'请输入积分',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#INTEGRAL").focus();
			return false;
			}
			if($("#WEALTH").val()==""){
				$("#WEALTH").tips({
					side:3,
		            msg:'请输入财富',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#WEALTH").focus();
			return false;
			}
			if($("#TIME").val()==""){
				$("#TIME").tips({
					side:3,
		            msg:'请输入注册时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TIME").focus();
			return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>