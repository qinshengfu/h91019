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
					
					<form action="news/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="NEWS_ID" id="NEWS_ID" value="${pd.NEWS_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">标题:</td>
								<td><input type="text" name="NEWS_TITLE" id="NEWS_TITLE" value="${pd.NEWS_TITLE}" maxlength="255" placeholder="这里输入标题" title="标题" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">作者:</td>
								<td><input type="text" name="AUTHOR" id="AUTHOR" value="${pd.AUTHOR}" maxlength="20" placeholder="这里输入作者" title="作者" style="width:98%;"/></td>
							</tr> --%>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">内容:</td>
								<td>
									<%-- <input type="text" name="CONTENT" id="CONTENT" value="${pd.CONTENT}" maxlength="4000" placeholder="这里输入内容" title="内容" style="width:98%;"/> --%>
									<textarea name="CONTENT" id="CONTENT" rows="3" cols="38">${pd.CONTENT}</textarea>
								</td>
							</tr>
							<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;line-height: 150px;">
									图片:
									<input type="hidden" name="IMG" id="IMG" value="${pd.IMG}" maxlength="255" placeholder="这里输入图片路径" title="图片路径" style="width:98%;"/>
								</td>
								<td>
									<img alt="" onclick="file1();" id="headimg" src="static/images/upload.png" width="150" height="150">
									<input type="file" id="file" style="display: none;" name="file" onchange="previewImage(this);">
								</td>
							</tr> --%>
							<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">图片:</td>
								<td><input type="text" name="IMG" id="IMG" value="${pd.IMG}" maxlength="255" placeholder="这里输入图片" title="图片" style="width:98%;"/></td>
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
	
	<script type="text/javascript" src="static/front/js/jquery.form.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		
		function file1(){
			$("#file").click();
		}
		
		//上传图片
		function previewImage(file) {
	        var filePath = file.value;	     
	        var fileExt = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();
	        if (!fileExt.match(/.jpg|.gif|.png|.bmp|.JPG|.GIF|.BNG|.BMP/i)) {
	            $("#headimg").tips({side:3,msg:'请上传图片文件!!!',bg:'#AE81FF',time:2});
				//$("#file").focus();
	            return false;
	        }
	        if ($("#file").val()) {	  
	           // $("#imghead").addClass("tx");
	            var url = "upload/upload";
	            
	            //异步提交表单(先确保jquery.form.js已经引入了)
	            var options = {
	                url: url,	               
	                success: function (data) {
	                    register_path = (data + "").trim();
	                    var sta = register_path;  <%--  "<%=basePath%>"+ --%>
	                    $("#IMG").val(register_path);
	                    $("#headimg").attr({src:sta });
	                    $("#headimg").tips({side:3,msg:'上传成功',bg:'#AE81FF',time:1});
	                    /* $("#file").focus(); */
	                }
	            };
	            $("#Form").ajaxSubmit(options);
	        }
	    }
		
		//保存
		function save(){
			if($("#NEWS_TITLE").val()==""){
				$("#NEWS_TITLE").tips({
					side:3,
		            msg:'请输入标题',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#NEWS_TITLE").focus();
			return false;
			}
			if($("#AUTHOR").val()==""){
				$("#AUTHOR").tips({
					side:3,
		            msg:'请输入作者',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#AUTHOR").focus();
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
			if($("#IMG").val()==""){
				$("#headimg").tips({
					side:3,
		            msg:'请选择图片',
		            bg:'#AE81FF',
		            time:2
		        });
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