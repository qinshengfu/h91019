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
	<script src="<%=basePath%>/static/front/js/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="<%=basePath%>/static/front/js/jquery.form.js"></script>
</head>
<style>
.store_upad{
	text-align: center;
}
.upload_pic{
	height: 100px;width: 100px;
}
.upload_pic_1{
	height: 100px;width: 100px;margin-left: -104px;background: transparent;
}
.preimg{position: absolute;
    top: 50%;
    right: 214px;
    width: 95px;
    height: 99px;
    opacity: 0;
    margin-top: -52px;}	
</style>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					
					<form action="fhfile/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="TOKEN_ID" id="TOKEN_ID" value="${pd.TOKEN_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<!-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">文件名:</td>
								<td><input type="text" name="NAME" id="NAME" value="" maxlength="30" placeholder="这里输入文件名" title="文件名" style="width:98%;"/></td>
							</tr> -->
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;" id="FILEPATHn">图片上传:</td>
								<td>
								    <input type="hidden" name="FILEPATH" id="FILEPATH" value=""/>
									<input type="hidden" name="NAME" id="NAME" value=""/>
									<p style="position:relative">
										<a class="imageup">
											<c:if test="${pd.ICON==null}">
												<img class="upload_pic" id="headimg" height="150" width="150" src="<%=basePath%>static/front/images/upload.png" />
											</c:if>
											<c:if test="${pd.ICON!=null}">
												<img class="upload_pic" id="headimg" height="150" width="150" src="<%=basePath%>${pd.FILEPATH}" />											
											</c:if>										
											<input type="file" name="upfile" onchange="previewImage(this)" id="previewImg" class="preimg"/>
										</a>
									</p>
								
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注:</td>
								<td><input type="text" name="BZ" id="BZ" value="" maxlength="100" placeholder="这里输入备注" title="备注" style="width:98%;"/></td>
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
			/* if($("#NAME").val()==""){
				$("#NAME").tips({
					side:3,
		            msg:'请输入文件名',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#NAME").focus();
			return false;
			} */
			if($("#hasTp1").val()=="no"){
				$("#FILEPATHn").tips({
					side:2,
			        msg:'请上传图片',
			        bg:'#AE81FF',
			        time:2
			    });
			return false;
			}
			if($("#BZ").val()==""){
				$("#BZ").tips({
					side:3,
		            msg:'请输入备注',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#BZ").focus();
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
		
		  function previewImage(file) {
		        var filePath = file.value;	     
		        var fileExt = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();
		        if (!fileExt.match(/.jpg|.gif|.png|.bmp|.JPG|.GIF|.BNG|.BMP/i)) {		           
		            $("#previewImg").tips({side:3,msg:'请上传图片文件!!!',bg:'#AE81FF',time:2});
					$("#previewImg").focus();
		            return false;
		        }
		        if ($("#previewImg").val()) {
		           // $("#imghead").addClass("tx");
		            var url = "fhfile/upload";
		            //异步提交表单(先确保jquery.form.js已经引入了)
		            var options = {
		                url: url,
		                success: function (data) {
		                    register_path = (data + "").trim();
		                    var sta = "<%=basePath%>"+register_path;
		                    $("#FILEPATH").val(register_path);
		                    $("#headimg").attr({src:sta });
		                 /*    $("#WEIXIN").val(register_path); */
		                    $("#previewImg").tips({side:3,msg:'上传成功',bg:'#AE81FF',time:2});
							$("#previewImg").focus();
		                }
		            };
		            $("#Form").ajaxSubmit(options);

		        }
		    }
		
		</script>
</body>
</html>