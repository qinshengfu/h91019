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
					
					<form action="jfproduct/${msg}.do" name="Form" id="Form" method="post">
						<input type="hidden" name="JFPRODUCT_ID" id="JFPRODUCT_ID" value="${pd.JFPRODUCT_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">商品名称:</td>
								<td><input type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="255" placeholder="这里输入商品名称" title="商品名称" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">价格:</td>
								<td><input type="number" name="PRICE" id="PRICE" value="${pd.PRICE}" maxlength="32" placeholder="这里输入价格" title="价格" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">库存:</td>
								<td><input type="number" name="STOCK" id="STOCK" value="${pd.STOCK}" maxlength="32" placeholder="这里输入库存" title="库存" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">类型:</td>
								<td>
									<select name="PTYPE" id="PTYPE">
										<option value="${pd.PTYPE}">${pd.PTYPE}</option>
										<c:forEach items="${listAll}" var="var">
											<option value="${var.TYPENAME}">${var.TYPENAME}</option>
										</c:forEach>
									</select>
									<%-- <input type="text" name="PTYPE" id="PTYPE" value="${pd.PTYPE}" maxlength="100" placeholder="这里输入类型" title="类型" style="width:98%;"/> --%>
								</td>
							</tr>
							<c:if test="${msg == 'edit'}">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">状态:</td>
								<td>
								<%-- <input type="text" name="STATE" id="STATE" value="${pd.STATE}" maxlength="20" placeholder="这里输入状态" title="状态" style="width:98%;"/> --%>
									<select name="STATE">
										<option value="${pd.STATE}"></option>
										<option value="1">上架</option>
										<option value="0">下架</option>
									</select>
								</td>
							</tr>
							</c:if>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">简介:</td>
								<td><input type="text" name="INTRODUCTION" id="INTRODUCTION" value="${pd.INTRODUCTION}" maxlength="4000" placeholder="这里输入简介" title="简介" style="width:98%;"/></td>
							</tr>
							<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">图片:</td>
								<td><input type="text" name="IMGPATH1" id="IMGPATH1" value="${pd.IMGPATH1}" maxlength="255" placeholder="这里输入图片" title="图片" style="width:98%;"/></td>
							</tr> --%>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;line-height: 150px;">
									图片:
									<input type="hidden" name="IMGPATH1" id="IMGPATH1" value="${pd.IMGPATH1}" maxlength="255" placeholder="这里输入图片路径" title="图片路径" style="width:98%;"/>
								</td>
								<td>
									<img alt="" onclick="fileInput('1');" id="headimg1" src="static/images/upload.png" width="150" height="150">
									<input type="file" id="file1" style="display: none;" name="file1" onchange="previewImage(this,'1');">
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;line-height: 150px;">
									图片:
									<input type="hidden" name="IMGPATH2" id="IMGPATH2" value="${pd.IMGPATH2}" maxlength="255" placeholder="这里输入图片路径" title="图片路径" style="width:98%;"/>
								</td>
								<td>
									<img alt="" onclick="fileInput('2');" id="headimg2" src="static/images/upload.png" width="150" height="150">
									<input type="file" id="file2" style="display: none;" name="file2" onchange="previewImage(this,'2');">
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;line-height: 150px;">
									图片:
									<input type="hidden" name="IMGPATH3" id="IMGPATH3" value="${pd.IMGPATH3}" maxlength="255" placeholder="这里输入图片路径" title="图片路径" style="width:98%;"/>
								</td>
								<td>
									<img alt="" onclick="fileInput('3');" id="headimg3" src="static/images/upload.png" width="150" height="150">
									<input type="file" id="file3" style="display: none;" name="file3" onchange="previewImage(this,'3');">
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;line-height: 150px;">
									图片:
									<input type="hidden" name="IMGPATH4" id="IMGPATH4" value="${pd.IMGPATH4}" maxlength="255" placeholder="这里输入图片路径" title="图片路径" style="width:98%;"/>
								</td>
								<td>
									<img alt="" onclick="fileInput('4');" id="headimg4" src="static/images/upload.png" width="150" height="150">
									<input type="file" id="file4" style="display: none;" name="file4" onchange="previewImage(this,'4');">
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;line-height: 150px;">
									图片:
									<input type="hidden" name="IMGPATH5" id="IMGPATH5" value="${pd.IMGPATH5}" maxlength="255" placeholder="这里输入图片路径" title="图片路径" style="width:98%;"/>
								</td>
								<td>
									<img alt="" onclick="fileInput('5');" id="headimg5" src="static/images/upload.png" width="150" height="150">
									<input type="file" id="file5" style="display: none;" name="file5" onchange="previewImage(this,'5');">
								</td>
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
	
	<script type="text/javascript" src="static/front/js/jquery.form.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		
		function fileInput(index){
			$("#file"+index).click();
		}
		
		//上传图片
		function previewImage(file,index) {
	        var filePath = file.value;	
	        
	        var fileExt = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();
	        if (!fileExt.match(/.jpg|.gif|.png|.bmp|.JPG|.GIF|.BNG|.BMP/i)) {
	            $("#headimg"+index).tips({side:3,msg:'请上传图片文件!!!',bg:'#AE81FF',time:2});
				//$("#file").focus();
	            return false;
	        }
	        if ($("#file"+index).val()) {	  
	           // $("#imghead").addClass("tx");
	            var url = "upload/upload";
	            
	            //异步提交表单(先确保jquery.form.js已经引入了)
	            var options = {
	                url: url,	               
	                success: function (data) {
	                    register_path = (data + "").trim();
	                    var sta = register_path;  <%--  "<%=basePath%>"+ --%>
	                    $("#IMGPATH"+index).val(register_path);
	                    $("#headimg"+index).attr({src:sta });
	                    $("#headimg"+index).tips({side:3,msg:'上传成功',bg:'#AE81FF',time:1});
	                    /* $("#file").focus(); */
	                }
	            };
	            $("#Form").ajaxSubmit(options);
	        }
	    }
		
		//保存
		function save(){
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
		            msg:'请输入价格',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PRICE").focus();
			return false;
			}
			if($("#STOCK").val()==""){
				$("#STOCK").tips({
					side:3,
		            msg:'请输入库存',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#STOCK").focus();
			return false;
			}
			if($("#PTYPE").val()==""){
				$("#PTYPE").tips({
					side:3,
		            msg:'请输入类型',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PTYPE").focus();
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
			if($("#INTRODUCTION").val()==""){
				$("#INTRODUCTION").tips({
					side:3,
		            msg:'请输入简介',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#INTRODUCTION").focus();
			return false;
			}
			if($("#IMGPATH1").val()==""){
				$("#headimg1").tips({
					side:3,
		            msg:'请输入图片',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if($("#IMGPATH2").val()==""){
				$("#headimg2").tips({
					side:3,
		            msg:'请输入图片',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if($("#IMGPATH3").val()==""){
				$("#headimg3").tips({
					side:3,
		            msg:'请输入图片',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if($("#IMGPATH4").val()==""){
				$("#headimg4").tips({
					side:3,
		            msg:'请输入图片',
		            bg:'#AE81FF',
		            time:2
		        });
			return false;
			}
			if($("#IMGPATH5").val()==""){
				$("#headimg5").tips({
					side:3,
		            msg:'请输入图片',
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