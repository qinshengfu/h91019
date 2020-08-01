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
					
					<form action="parameter/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="PARAMETER_ID" id="PARAMETER_ID" value="${pd.PARAMETER_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">直推奖:</td>
								<td><input type="text" name="DIRECT" id="DIRECT" value="${pd.DIRECT}" maxlength="20" placeholder="这里输入直推奖" title="直推奖" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">领导奖:</td>
								<td><input type="text" name="LEADER" id="LEADER" value="${pd.LEADER}" maxlength="20" placeholder="这里输入领导奖" title="领导奖" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">注册积分:</td>
								<td><input type="text" name="REGINTEGRAL" id="REGINTEGRAL" value="${pd.REGINTEGRAL}" maxlength="20" placeholder="这里输入注册积分" title="注册积分" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">签到积分:</td>
								<td><input type="text" name="SIGNINTEGRAL" id="SIGNINTEGRAL" value="${pd.SIGNINTEGRAL}" maxlength="20" placeholder="这里输入签到积分" title="签到积分" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">积分互转条件:</td>
								<td><input type="text" name="MUTUAL" id="MUTUAL" value="${pd.MUTUAL}" maxlength="20" placeholder="这里输入积分互转条件" title="积分互转条件" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">互转倍数:</td>
								<td><input type="text" name="MUTUALTIMES" id="MUTUALTIMES" value="${pd.MUTUALTIMES}" maxlength="20" placeholder="这里输入互转倍数" title="互转倍数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">兑换比:</td>
								<td><input type="text" name="EXCHANGE" id="EXCHANGE" value="${pd.EXCHANGE}" maxlength="20" placeholder="这里输入兑换比" title="兑换比" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">出局倍数:</td>
								<td><input type="text" name="MULTIPLE" id="MULTIPLE" value="${pd.MULTIPLE}" maxlength="20" placeholder="这里输入出局倍数" title="出局倍数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">一次性出局倍数:</td>
								<td><input type="text" name="ONEMULTIPLE" id="ONEMULTIPLE" value="${pd.ONEMULTIPLE}" maxlength="20" placeholder="这里输入一次性出局倍数" title="一次性出局倍数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">一次性数量:</td>
								<td><input type="text" name="COUNT" id="COUNT" value="${pd.COUNT}" maxlength="20" placeholder="这里输入一次性数量" title="一次性数量" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">初始:</td>
								<td><input type="text" name="INITIAL" id="INITIAL" value="${pd.INITIAL}" maxlength="20" placeholder="这里输入初始" title="初始" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">小队人数:</td>
								<td><input type="text" name="SQUAD" id="SQUAD" value="${pd.SQUAD}" maxlength="20" placeholder="这里输入小队人数" title="小队人数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">一星分享积分倍数:</td>
								<td><input type="text" name="LEVEL1" id="LEVEL1" value="${pd.LEVEL1}" maxlength="20" placeholder="这里输入一星分享积分倍数" title="一星分享积分倍数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">二星分享积分倍数:</td>
								<td><input type="text" name="LEVEL2" id="LEVEL2" value="${pd.LEVEL2}" maxlength="20" placeholder="这里输入二星分享积分倍数" title="二星分享积分倍数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">三星分享积分倍数:</td>
								<td><input type="text" name="LEVEL3" id="LEVEL3" value="${pd.LEVEL3}" maxlength="20" placeholder="这里输入三星分享积分倍数" title="三星分享积分倍数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">四星分享积分倍数:</td>
								<td><input type="text" name="LEVEL4" id="LEVEL4" value="${pd.LEVEL4}" maxlength="20" placeholder="这里输入四星分享积分倍数" title="四星分享积分倍数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">五星分享积分倍数:</td>
								<td><input type="text" name="LEVEL5" id="LEVEL5" value="${pd.LEVEL5}" maxlength="20" placeholder="这里输入五星分享积分倍数" title="五星分享积分倍数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">六星分享积分倍数:</td>
								<td><input type="text" name="LEVEL6" id="LEVEL6" value="${pd.LEVEL6}" maxlength="20" placeholder="这里输入六星分享积分倍数" title="六星分享积分倍数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">七星分享积分倍数:</td>
								<td><input type="text" name="LEVEL7" id="LEVEL7" value="${pd.LEVEL7}" maxlength="20" placeholder="这里输入七星分享积分倍数" title="七星分享积分倍数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">八星分享积分倍数:</td>
								<td><input type="text" name="LEVEL8" id="LEVEL8" value="${pd.LEVEL8}" maxlength="20" placeholder="这里输入八星分享积分倍数" title="八星分享积分倍数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">九星分享积分倍数:</td>
								<td><input type="text" name="LEVEL9" id="LEVEL9" value="${pd.LEVEL9}" maxlength="20" placeholder="这里输入九星分享积分倍数" title="九星分享积分倍数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">十星分享积分倍数:</td>
								<td><input type="text" name="LEVEL10" id="LEVEL10" value="${pd.LEVEL10}" maxlength="20" placeholder="这里输入十星分享积分倍数" title="十星分享积分倍数" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">提现条件:</td>
								<td><input type="text" name="WITHDRAW" id="WITHDRAW" value="${pd.WITHDRAW}" maxlength="20" placeholder="这里输入提现条件" title="提现条件" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">手续费:</td>
								<td><input type="text" name="FEE" id="FEE" value="${pd.FEE}" maxlength="20" placeholder="这里输入手续费" title="手续费" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">收益到达倍数:</td>
								<td><input type="text" name="REVENUE" id="REVENUE" value="${pd.REVENUE}" maxlength="20" placeholder="这里输入收益到达倍数" title="收益到达倍数" style="width:98%;"/></td>
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
			if($("#DIRECT").val()==""){
				$("#DIRECT").tips({
					side:3,
		            msg:'请输入直推奖',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#DIRECT").focus();
			return false;
			}
			if($("#LEADER").val()==""){
				$("#LEADER").tips({
					side:3,
		            msg:'请输入领导奖',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#LEADER").focus();
			return false;
			}
			if($("#REGINTEGRAL").val()==""){
				$("#REGINTEGRAL").tips({
					side:3,
		            msg:'请输入注册积分',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#REGINTEGRAL").focus();
			return false;
			}
			if($("#SIGNINTEGRAL").val()==""){
				$("#SIGNINTEGRAL").tips({
					side:3,
		            msg:'请输入签到积分',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#SIGNINTEGRAL").focus();
			return false;
			}
			if($("#MUTUAL").val()==""){
				$("#MUTUAL").tips({
					side:3,
		            msg:'请输入积分互转条件',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#MUTUAL").focus();
			return false;
			}
			if($("#MUTUALTIMES").val()==""){
				$("#MUTUALTIMES").tips({
					side:3,
		            msg:'请输入互转倍数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#MUTUALTIMES").focus();
			return false;
			}
			if($("#EXCHANGE").val()==""){
				$("#EXCHANGE").tips({
					side:3,
		            msg:'请输入兑换比',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#EXCHANGE").focus();
			return false;
			}
			if($("#MULTIPLE").val()==""){
				$("#MULTIPLE").tips({
					side:3,
		            msg:'请输入出局倍数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#MULTIPLE").focus();
			return false;
			}
			if($("#ONEMULTIPLE").val()==""){
				$("#ONEMULTIPLE").tips({
					side:3,
		            msg:'请输入一次性出局倍数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ONEMULTIPLE").focus();
			return false;
			}
			if($("#COUNT").val()==""){
				$("#COUNT").tips({
					side:3,
		            msg:'请输入一次性数量',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#COUNT").focus();
			return false;
			}
			if($("#INITIAL").val()==""){
				$("#INITIAL").tips({
					side:3,
		            msg:'请输入初始',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#INITIAL").focus();
			return false;
			}
			if($("#SQUAD").val()==""){
				$("#SQUAD").tips({
					side:3,
		            msg:'请输入小队人数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#SQUAD").focus();
			return false;
			}
			if($("#LEVEL1").val()==""){
				$("#LEVEL1").tips({
					side:3,
		            msg:'请输入一星分享积分倍数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#LEVEL1").focus();
			return false;
			}
			if($("#LEVEL2").val()==""){
				$("#LEVEL2").tips({
					side:3,
		            msg:'请输入二星分享积分倍数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#LEVEL2").focus();
			return false;
			}
			if($("#LEVEL3").val()==""){
				$("#LEVEL3").tips({
					side:3,
		            msg:'请输入三星分享积分倍数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#LEVEL3").focus();
			return false;
			}
			if($("#LEVEL4").val()==""){
				$("#LEVEL4").tips({
					side:3,
		            msg:'请输入四星分享积分倍数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#LEVEL4").focus();
			return false;
			}
			if($("#LEVEL5").val()==""){
				$("#LEVEL5").tips({
					side:3,
		            msg:'请输入五星分享积分倍数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#LEVEL5").focus();
			return false;
			}
			if($("#LEVEL6").val()==""){
				$("#LEVEL6").tips({
					side:3,
		            msg:'请输入六星分享积分倍数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#LEVEL6").focus();
			return false;
			}
			if($("#LEVEL7").val()==""){
				$("#LEVEL7").tips({
					side:3,
		            msg:'请输入七星分享积分倍数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#LEVEL7").focus();
			return false;
			}
			if($("#LEVEL8").val()==""){
				$("#LEVEL8").tips({
					side:3,
		            msg:'请输入八星分享积分倍数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#LEVEL8").focus();
			return false;
			}
			if($("#LEVEL9").val()==""){
				$("#LEVEL9").tips({
					side:3,
		            msg:'请输入九星分享积分倍数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#LEVEL9").focus();
			return false;
			}
			if($("#LEVEL10").val()==""){
				$("#LEVEL10").tips({
					side:3,
		            msg:'请输入十星分享积分倍数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#LEVEL10").focus();
			return false;
			}
			if($("#WITHDRAW").val()==""){
				$("#WITHDRAW").tips({
					side:3,
		            msg:'请输入提现条件',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#WITHDRAW").focus();
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
			if($("#REVENUE").val()==""){
				$("#REVENUE").tips({
					side:3,
		            msg:'请输入收益到达倍数',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#REVENUE").focus();
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