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
					
					<form action="comments/save.do" name="Form" id="Form" method="post">
						<input type="hidden" name="COMMENTS_ID" id="COMMENTS_ID" value="${pd.COMMENTS_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">账号:</td>
								<td><input type="text" name="MEMBER" id="MEMBER" value="${pd.MEMBER}" maxlength="20" placeholder="这里输入账号" title="账号" style="width:98%;"/></td>
							</tr> --%>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">内容:</td>
								<td>
									<textarea name="CONTENT" id="CONTENT" rows="" cols=""></textarea>
									<%-- <input type="text" name="CONTENT" id="CONTENT" value="${pd.CONTENT}" maxlength="1000" placeholder="这里输入内容" title="内容" style="width:98%;"/> --%>
									<input type="hidden" name="MEMBER" id="MEMBER" value="${pd.MEMBER}" maxlength="20" placeholder="这里输入账号" title="账号" style="width:98%;"/>
									<input type="hidden" name="MARK" id="MARK" value="0" maxlength="10" placeholder="这里输入标识" title="标识" style="width:98%;"/>
									<input type="hidden" name="STATE" id="STATE" value="1" maxlength="10" placeholder="这里输入状态" title="状态" style="width:98%;"/>
									<input type="hidden" name="COMMENTS_ID" id="COMMENTS_ID" value="${pd.COMMENTS_ID}">
									<input type="hidden" name="IMG" id="IMG" value="${pd.IMG}">
								</td>
							</tr>
							<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">标识:</td>
								<td><input type="text" name="MARK" id="MARK" value="${pd.MARK}" maxlength="10" placeholder="这里输入标识" title="标识" style="width:98%;"/></td>
							</tr> --%>
							<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">状态:</td>
								<td><input type="text" name="STATE" id="STATE" value="${pd.STATE}" maxlength="10" placeholder="这里输入状态" title="状态" style="width:98%;"/></td>
							</tr> --%>
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">回复</a>
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
			if($("#MEMBER").val()==""){
				$("#MEMBER").tips({
					side:3,
		            msg:'请输入账号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#MEMBER").focus();
			return false;
			}
			if($("#CONTENT").val()==""){
				$("#CONTENT").tips({
					side:3,
		            msg:'请输入内容',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CONTENT").focus();
			return false;
			}
			if($("#MARK").val()==""){
				$("#MARK").tips({
					side:3,
		            msg:'请输入标识',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#MARK").focus();
			return false;
			}
			if($("#STATE").val()==""){
				$("#STATE").tips({
					side:3,
		            msg:'请输入状态',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#STATE").focus();
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