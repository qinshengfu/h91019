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
					
					<form action="withdraw/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="WITHDRAW_ID" id="WITHDRAW_ID" value="${pd.WITHDRAW_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">提现账号:</td>
								<td><input type="text" name="PHONE" id="PHONE" value="${pd.PHONE}" maxlength="20" placeholder="这里输入提现账号" title="提现账号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">提现金额:</td>
								<td><input type="number" name="MONEY" id="MONEY" value="${pd.MONEY}" maxlength="32" placeholder="这里输入提现金额" title="提现金额" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">提现方式:</td>
								<td><input type="text" name="RSTYLE" id="RSTYLE" value="${pd.RSTYLE}" maxlength="20" placeholder="这里输入提现方式" title="提现方式" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">真实姓名:</td>
								<td><input type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="20" placeholder="这里输入真实姓名" title="真实姓名" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">到账账号:</td>
								<td><input type="text" name="ACCOUNT" id="ACCOUNT" value="${pd.ACCOUNT}" maxlength="50" placeholder="这里输入到账账号" title="到账账号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">时间:</td>
								<td><input class="span10 date-picker" name="TIME" id="TIME" value="${pd.TIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="时间" title="时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">手续费:</td>
								<td><input type="text" name="FEE" id="FEE" value="${pd.FEE}" maxlength="20" placeholder="这里输入手续费" title="手续费" style="width:98%;"/></td>
							</tr>
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
			if($("#PHONE").val()==""){
				$("#PHONE").tips({
					side:3,
		            msg:'请输入提现账号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PHONE").focus();
			return false;
			}
			if($("#MONEY").val()==""){
				$("#MONEY").tips({
					side:3,
		            msg:'请输入提现金额',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#MONEY").focus();
			return false;
			}
			if($("#RSTYLE").val()==""){
				$("#RSTYLE").tips({
					side:3,
		            msg:'请输入提现方式',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#RSTYLE").focus();
			return false;
			}
			if($("#NAME").val()==""){
				$("#NAME").tips({
					side:3,
		            msg:'请输入真实姓名',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#NAME").focus();
			return false;
			}
			if($("#ACCOUNT").val()==""){
				$("#ACCOUNT").tips({
					side:3,
		            msg:'请输入到账账号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ACCOUNT").focus();
			return false;
			}
			if($("#TIME").val()==""){
				$("#TIME").tips({
					side:3,
		            msg:'请输入时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TIME").focus();
			return false;
			}
			if($("#FEE").val()==""){
				$("#FEE").tips({
					side:3,
		            msg:'请输入手续费',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#FEE").focus();
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