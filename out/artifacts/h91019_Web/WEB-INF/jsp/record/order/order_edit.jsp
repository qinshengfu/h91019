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
					
					<form action="order/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="ORDER_ID" id="ORDER_ID" value="${pd.ORDER_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">订单号:</td>
								<td><input type="text" name="ORDERNUM" id="ORDERNUM" value="${pd.ORDERNUM}" maxlength="20" placeholder="这里输入订单号" title="订单号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">商品名称:</td>
								<td><input type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="255" placeholder="这里输入商品名称" title="商品名称" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">商品价格:</td>
								<td><input type="number" name="PRICE" id="PRICE" value="${pd.PRICE}" maxlength="32" placeholder="这里输入商品价格" title="商品价格" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">图片:</td>
								<td><input type="text" name="IMGPATH" id="IMGPATH" value="${pd.IMGPATH}" maxlength="255" placeholder="这里输入图片" title="图片" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">购买数量:</td>
								<td><input type="number" name="QUANTITY" id="QUANTITY" value="${pd.QUANTITY}" maxlength="32" placeholder="这里输入购买数量" title="购买数量" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">总价:</td>
								<td><input type="number" name="TOTAL" id="TOTAL" value="${pd.TOTAL}" maxlength="32" placeholder="这里输入总价" title="总价" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">收件人:</td>
								<td><input type="text" name="RECIPIENT" id="RECIPIENT" value="${pd.RECIPIENT}" maxlength="20" placeholder="这里输入收件人" title="收件人" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">手机号:</td>
								<td><input type="text" name="PHONE" id="PHONE" value="${pd.PHONE}" maxlength="20" placeholder="这里输入手机号" title="手机号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">地址:</td>
								<td><input type="text" name="ADDRESS" id="ADDRESS" value="${pd.ADDRESS}" maxlength="255" placeholder="这里输入地址" title="地址" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">详细地址:</td>
								<td><input type="text" name="DETAILEDADD" id="DETAILEDADD" value="${pd.DETAILEDADD}" maxlength="255" placeholder="这里输入详细地址" title="详细地址" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">状态:</td>
								<td><input type="text" name="STATE" id="STATE" value="${pd.STATE}" maxlength="20" placeholder="这里输入状态" title="状态" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">下单时间:</td>
								<td><input class="span10 date-picker" name="ORDERTIME" id="ORDERTIME" value="${pd.ORDERTIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="下单时间" title="下单时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">付款时间:</td>
								<td><input class="span10 date-picker" name="PAYMENTTIME" id="PAYMENTTIME" value="${pd.PAYMENTTIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="付款时间" title="付款时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">发货时间:</td>
								<td><input class="span10 date-picker" name="DELIVERYTIME" id="DELIVERYTIME" value="${pd.DELIVERYTIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="发货时间" title="发货时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">收货时间:</td>
								<td><input class="span10 date-picker" name="RECEIPTTIME" id="RECEIPTTIME" value="${pd.RECEIPTTIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="收货时间" title="收货时间" style="width:98%;"/></td>
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
			if($("#ORDERNUM").val()==""){
				$("#ORDERNUM").tips({
					side:3,
		            msg:'请输入订单号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ORDERNUM").focus();
			return false;
			}
			if($("#NAME").val()==""){
				$("#NAME").tips({
					side:3,
		            msg:'请输入商品名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#NAME").focus();
			return false;
			}
			if($("#PRICE").val()==""){
				$("#PRICE").tips({
					side:3,
		            msg:'请输入商品价格',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PRICE").focus();
			return false;
			}
			if($("#IMGPATH").val()==""){
				$("#IMGPATH").tips({
					side:3,
		            msg:'请输入图片',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#IMGPATH").focus();
			return false;
			}
			if($("#QUANTITY").val()==""){
				$("#QUANTITY").tips({
					side:3,
		            msg:'请输入购买数量',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#QUANTITY").focus();
			return false;
			}
			if($("#TOTAL").val()==""){
				$("#TOTAL").tips({
					side:3,
		            msg:'请输入总价',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TOTAL").focus();
			return false;
			}
			if($("#RECIPIENT").val()==""){
				$("#RECIPIENT").tips({
					side:3,
		            msg:'请输入收件人',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#RECIPIENT").focus();
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
			if($("#ADDRESS").val()==""){
				$("#ADDRESS").tips({
					side:3,
		            msg:'请输入地址',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ADDRESS").focus();
			return false;
			}
			if($("#DETAILEDADD").val()==""){
				$("#DETAILEDADD").tips({
					side:3,
		            msg:'请输入详细地址',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#DETAILEDADD").focus();
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
			if($("#ORDERTIME").val()==""){
				$("#ORDERTIME").tips({
					side:3,
		            msg:'请输入下单时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ORDERTIME").focus();
			return false;
			}
			if($("#PAYMENTTIME").val()==""){
				$("#PAYMENTTIME").tips({
					side:3,
		            msg:'请输入付款时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PAYMENTTIME").focus();
			return false;
			}
			if($("#DELIVERYTIME").val()==""){
				$("#DELIVERYTIME").tips({
					side:3,
		            msg:'请输入发货时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#DELIVERYTIME").focus();
			return false;
			}
			if($("#RECEIPTTIME").val()==""){
				$("#RECEIPTTIME").tips({
					side:3,
		            msg:'请输入收货时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#RECEIPTTIME").focus();
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