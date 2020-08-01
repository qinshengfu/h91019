<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
							
						<!-- 检索  -->
						<form action="parameter/edit.do" method="post" name="Form" id="Form">
					
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">						
							<tbody>
								<tr>
									<td>
										1、<%-- 微信：<input onchange="positive_integer(this);" type="text" value="${parameter.WECHAT}" name="WECHAT" id="WECHAT">
										支付宝：<input onchange="positive_integer(this);" type="text" value="${parameter.ALIPAY}" name="ALIPAY" id="ALIPAY"> --%>
										银行卡：<input onchange="positive_integer(this);" type="text" value="${parameter.BANKCARD}" name="BANKCARD" id="BANKCARD">
									</td>
								</tr>
								<tr>
									<td>
										2、直推奖：<input onchange="positive_integer(this);" type="text" value="${parameter.DIRECT}" name="DIRECT" id="DIRECT">%佣金，领导奖：<input onchange="positive_integer(this);" type="text" value="${parameter.LEADER}" name="LEADER" id="LEADER">%佣金。
									</td>
								</tr>
								<tr>
									<td>
										3、<input onchange="positive_integer(this);" type="text" value="${parameter.SQUAD}" name="SQUAD" id="SQUAD">人为一个小团队。分享积分倍数：
										一星<input onchange="positive_integer(this);" type="text" value="${parameter.LEVEL1}" name="LEVEL1" id="LEVEL1">倍，
										二星<input onchange="positive_integer(this);" type="text" value="${parameter.LEVEL2}" name="LEVEL2" id="LEVEL2">倍，
										三星<input onchange="positive_integer(this);" type="text" value="${parameter.LEVEL3}" name="LEVEL3" id="LEVEL3">倍，
										四星<input onchange="positive_integer(this);" type="text" value="${parameter.LEVEL4}" name="LEVEL4" id="LEVEL4">倍，<br><br>
										五星<input onchange="positive_integer(this);" type="text" value="${parameter.LEVEL5}" name="LEVEL5" id="LEVEL5">倍，
										六星<input onchange="positive_integer(this);" type="text" value="${parameter.LEVEL6}" name="LEVEL6" id="LEVEL6">倍，
										七星<input onchange="positive_integer(this);" type="text" value="${parameter.LEVEL7}" name="LEVEL7" id="LEVEL7">倍，
										八星<input onchange="positive_integer(this);" type="text" value="${parameter.LEVEL8}" name="LEVEL8" id="LEVEL8">倍，
										九星<input onchange="positive_integer(this);" type="text" value="${parameter.LEVEL9}" name="LEVEL9" id="LEVEL9">倍，
										十星<input onchange="positive_integer(this);" type="text" value="${parameter.LEVEL10}" name="LEVEL10" id="LEVEL10">倍。
									</td>
								</tr>
								<tr>
									<td>
										4、人民币：积分 = 1 ：<input onchange="positive_integer1(this);" type="text" value="${parameter.EXCHANGE}" name="EXCHANGE" id="EXCHANGE">。
									</td>
								</tr>
								<tr>
									<td>
										5、会员总收益达到<input onchange="positive_integer(this);" type="text" value="${parameter.REVENUE}" name="REVENUE" id="REVENUE">倍${parameter.INITIAL}就得重复消费一个${parameter.INITIAL}，自动升级一星。
									</td>
								</tr>
								<tr>
									<td>
										6、静态总收益<input onchange="positive_integer(this);" type="text" value="${parameter.MULTIPLE}" name="MULTIPLE" id="MULTIPLE">倍出局，
										一次性消费<input onchange="positive_integer(this);" type="text" value="${parameter.COUNT}" name="COUNT" id="COUNT">倍${parameter.INITIAL}享受<input onchange="positive_integer(this);" type="text" value="${parameter.ONEMULTIPLE}" name="ONEMULTIPLE" id="ONEMULTIPLE">倍出局。
									</td>
								</tr>
								<tr>
									<td>
										7、提现：满<input onchange="positive_integer(this);" type="text" value="${parameter.WITHDRAW}" name="WITHDRAW" id="WITHDRAW">提现，手续费<input onchange="positive_integer(this);" type="text" value="${parameter.FEE}" name="FEE" id="FEE">%。
									</td>
								</tr>
								<tr>
									<td>
										8、任何人注册送<input onchange="positive_integer(this);" type="text" value="${parameter.REGINTEGRAL}" name="REGINTEGRAL" id="REGINTEGRAL">积分，每天签到加分享一次送<input onchange="positive_integer(this);" type="text" value="${parameter.SIGNINTEGRAL}" name="SIGNINTEGRAL" id="SIGNINTEGRAL">积分，
										积分可以会员之间任意互转，满<input onchange="positive_integer(this);" type="text" value="${parameter.MUTUAL}" name="MUTUAL" id="MUTUAL">可以<input onchange="positive_integer(this);" type="text" value="${parameter.MUTUALTIMES}" name="MUTUALTIMES" id="MUTUALTIMES">倍数任意互转。
									</td>
								</tr>
								<tr>
									<td>
										9、客户电话：<input onchange="positive_integer1(this);" type="text" value="${parameter.PHONE}" name="PHONE" id="PHONE">
									</td>
								</tr>
								<tr>
									<td>
										<input type="hidden" value="${parameter.ALIPAY}" name="ALIPAY" id="ALIPAY">
										<input type="file" id="file1" style="display: none;" name="file1" onchange="previewImage(this,'ALIPAY');">
										<input type="hidden" value="${parameter.WECHAT}" name="WECHAT" id="WECHAT">
										<input type="file" id="file2" style="display: none;" name="file2" onchange="previewImage(this,'WECHAT');">
										<input type="hidden" value="${parameter.WECHAT1}" name="WECHAT1" id="WECHAT1">
										<input type="file" id="file3" style="display: none;" name="file3" onchange="previewImage(this,'WECHAT1');">
										<div style="float: left;margin-right: 30px;">
										支付宝收款码
										<c:if test="${! not empty parameter.ALIPAY}">
											<div onclick="fileInput('1');" style="width: 150px;height: 150px;border:1px solid #ccc;"><img width="150" height="150" id="imgALIPAY" src="static/images/upload.png"></div>
										</c:if>
										<c:if test="${not empty parameter.ALIPAY}">
											<div onclick="fileInput('1');" style="width: 150px;height: 150px;border:1px solid #ccc;"><img width="150" height="150" id="imgALIPAY" src="${parameter.ALIPAY}"></div>
										</c:if>
										</div>
										<div style="float: left;margin-right: 30px;">
										微信收款码
										<c:if test="${! not empty parameter.WECHAT}">
											<div onclick="fileInput('2');" style="width: 150px;height: 150px;border:1px solid #ccc;"><img width="150" height="150" id="imgWECHAT" src="static/images/upload.png"></div>
										</c:if>
										<c:if test="${not empty parameter.WECHAT}">
											<div onclick="fileInput('2');" style="width: 150px;height: 150px;border:1px solid #ccc;"><img width="150" height="150" id="imgWECHAT" src="${parameter.WECHAT}"></div>
										</c:if>
										</div>
										<div style="float: left;margin-right: 30px;">
										微信二维码
										<c:if test="${! not empty parameter.WECHAT1}">
											<div onclick="fileInput('3');" style="width: 150px;height: 150px;border:1px solid #ccc;"><img width="150" height="150" id="imgWECHAT1" src="static/images/upload.png"></div>
										</c:if>
										<c:if test="${not empty parameter.WECHAT1}">
											<div onclick="fileInput('3');" style="width: 150px;height: 150px;border:1px solid #ccc;"><img width="150" height="150" id="imgWECHAT1" src="${parameter.WECHAT1}"></div>
										</c:if>
										</div>
									</td>
								</tr>
								<tr>
									<td align="center">
										<input type="hidden" value="${parameter.INITIAL}" name="INITIAL" id="INITIAL">
										<input type="button" onclick="edit()" value="保存">
									</td>
								</tr>
							</tbody>
						</table>
						<div class="page-header position-relative">
						
						</div>
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

		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 删除时确认窗口 -->
	<script src="static/ace/js/bootbox.js"></script>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	
	<script type="text/javascript" src="static/front/js/jquery.form.js"></script>
	<script type="text/javascript">
		$(top.hangge());//关闭加载状态
		
		function fileInput(index){
			$("#file"+index).click();
		}
		
		//上传图片
		function previewImage(file,str) {
	        var filePath = file.value;	     
	        var fileExt = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();
	        if (!fileExt.match(/.jpg|.gif|.png|.bmp|.JPG|.GIF|.BNG|.BMP/i)) {
	            $("#img" + str).tips({side:3,msg:'请上传图片文件!!!',bg:'#AE81FF',time:2});
				//$("#file").focus();
	            return false;
	        }
	        if (file.value) {	  
	           // $("#imghead").addClass("tx");
	            var url = "upload/upload";
	            
	            //异步提交表单(先确保jquery.form.js已经引入了)
	            var options = {
	                url: url,	               
	                success: function (data) {
	                    register_path = (data + "").trim();
	                    var sta = register_path;  <%--  "<%=basePath%>"+ --%>
	                    $("#"+str).val(register_path);
	                    $("#img" + str).attr({src:sta });
	                    $("#img" + str).tips({side:3,msg:'上传成功',bg:'#AE81FF',time:1});
	                    /* $("#file").focus(); */
	                }
	            };
	            $("#Form").ajaxSubmit(options);
	        }
	    }
		
		//正整数
		function positive_integer(self){
			var reg = /^[1-9]\d*$/;
			if(!reg.test(self.value)){
				self.value = '';
				self.focus();
			}
			if(self.value == ''){
				self.value = 1;
			}
		}
		
		//
		function positive_integer1(self){
			var reg = /^[0-9]\d*$/;
			if(!reg.test(self.value)){
				self.value = '';
				self.focus();
			}
			if(self.value == ''){
				self.value = 1;
			}
		}
		
		//修改
		function edit(Id){
			bootbox.confirm("确定要保存吗?", function(result) {
				if(result) {
					$("#Form").submit();
				}
			});
		}
		
	</script>


</body>
</html>