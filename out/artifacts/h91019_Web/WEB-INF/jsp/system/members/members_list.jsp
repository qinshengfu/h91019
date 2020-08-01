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
<style type="text/css">
.yulantu{
	z-index: 9999999999999999;
	position:absolute;
	
	border:3px solid #438EB9;
	display: none;
}
</style>
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
						<form action="members/list.do" method="post" name="Form" id="Form">
						<table style="margin-top:5px;">
							<tr>
								<td>
									<div class="nav-search">
										<span class="input-icon">
											<input type="text" placeholder="这里输入关键词" class="nav-search-input" id="nav-search-input" autocomplete="off" name="keywords" value="${pd.keywords }" placeholder="这里输入关键词"/>
											<i class="ace-icon fa fa-search nav-search-icon"></i>
										</span>
									</div>
								</td>
								<!-- <td style="padding-left:2px;"><input class="span10 date-picker" name="lastStart" id="lastStart"  value="" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="开始日期" title="开始日期"/></td>
								<td style="padding-left:2px;"><input class="span10 date-picker" name="lastEnd" id="lastEnd"  value="" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="结束日期" title="结束日期"/></td> -->
								<td style="vertical-align:top;padding-left:2px;">
								 	<select class="chosen-select form-control" name="name" id="id"
								 	 data-placeholder="请选择实名认证状态" style="vertical-align:top;width: 160px;">
									<option value=""></option>
									<option value="">全部</option>
									<option value="0" <c:if test="${pd.name == 4 }">selected</c:if>>未上传</option>
									<option value="2" <c:if test="${pd.name == 2 }">selected</c:if>>待确认</option>
									<option value="1" <c:if test="${pd.name == 1 }">selected</c:if>>已确认</option>
									<option value="3" <c:if test="${pd.name == 3 }">selected</c:if>>已拒绝</option>
								  	</select>
								</td>
								<c:if test="${QX.cha == 1 }">
								<td style="vertical-align:top;padding-left:2px"><a class="btn btn-light btn-xs" onclick="tosearch();"  title="检索"><i id="nav-search-icon" class="ace-icon fa fa-search bigger-110 nav-search-icon blue"></i></a></td>
								</c:if>
								<c:if test="${QX.toExcel == 1 }"><td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="ace-icon fa fa-download bigger-110 nav-search-icon blue"></i></a></td></c:if>
							</tr>
						</table>
						<!-- 检索  -->
					
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
							<thead>
								<tr>
									<th class="center" style="width:35px;">
									<label class="pos-rel"><input type="checkbox" class="ace" id="zcheckbox" /><span class="lbl"></span></label>
									</th>
									<th class="center" style="width:50px;">序号</th>
									<th class="center">手机号</th>
									<th class="center">会员编号</th>
									<!-- <th class="center">登录密码</th>
								    <th class="center">二级密码</th> -->
									<th class="center">账号状态</th> 
									<th class="center">注册时间</th>
									<th class="center">上级</th>
								<!-- 	<th class="center">会员级别</th>
									<th class="center">助记词</th> -->
									<th class="center">姓名</th>
									<th class="center">身份证号</th>
								<!-- 	<th class="center">eth私钥</th> -->
									<th class="center">身份证正面照</th>
									<th class="center">身份证反面照</th>
									<th class="center">手持身份证照片</th>
									<th class="center">直推人数</th>
									<th class="center">激活状态</th>
									<!-- <th class="center">代数</th> -->
									<!-- <th class="center">备注</th> -->
									<!-- <th class="center">eth钱包地址</th> -->
									<th class="center">操作</th>
								</tr>
							</thead>
													
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty varList}">
									<c:if test="${QX.cha == 1 }">
									<c:forEach items="${varList}" var="var" varStatus="vs">
										<tr>
											<td class='center'>
												<label class="pos-rel"><input type='checkbox' name='ids' value="${var.MEMBERS_ID}" class="ace" /><span class="lbl"></span></label>
											</td>
											<td class='center' style="width: 30px;">${vs.index+1}</td>
											<td class='center'>${var.PHONE}</td>
											<td class='center'>${var.SERIAL}</td>
											<%-- <td class='center'>${var.LOGIN_PASSWORD}</td>
											<td class='center'>${var.TRADE_PASSWORD}</td> --%>
											<td class='center'>
												<c:if test="${var.STATUS==1}">使用中</c:if>
												<c:if test="${var.STATUS==0}">封禁</c:if>
											</td>
											<td class='center'>${var.REGISTER_TIME}</td>
											<td class='center'>${var.SUPERIOR}</td>
											<%-- <td class='center'>${var.LEVEL_P}</td> --%>
									<%-- 		<td class='center'>${var.MNEMONIC}</td> --%>
											<td class='center'>${var.MNAME}</td>
											<td class='center'>${var.ID_CARD}</td>
										<%-- 	<td class='center'>${var.ETH_PRIVATE_KEY}</td> --%>
											<td class='center'>
												<c:if test="${var.POSITIVE!=null}">
													<img style="margin-top: -3px;" alt="" src="static/images/extension/tupian.png">												
													<a style="cursor:pointer;" onmouseover="showTU('${var.POSITIVE}','yulantu${vs.index+1}');" onmouseout="hideTU('yulantu${vs.index+1}');">[预览]</a>
													<div class="yulantu" id="yulantu${vs.index+1}"></div>
												</c:if>
											</td>
											<td class='center'>
												<c:if test="${var.OTHER_SIDE!=null}">
													<img style="margin-top: -3px;" alt="" src="static/images/extension/tupian.png">												
													<a style="cursor:pointer;" onmouseover="showTU('${var.OTHER_SIDE}','xqt${vs.index+1}');" onmouseout="hideTU('xqt${vs.index+1}');">[预览]</a>
													<div class="yulantu" id="xqt${vs.index+1}"></div>
												</c:if>
											</td>
											<td class='center'>
												<c:if test="${var.HANDHELD!=null}">
													<img style="margin-top: -3px;" alt="" src="static/images/extension/tupian.png">											
													<a style="cursor:pointer;" onmouseover="showTU('${var.HANDHELD}','xqt2${vs.index+1}');" onmouseout="hideTU('xqt2${vs.index+1}');">[预览]</a>
													<div class="yulantu" id="xqt2${vs.index+1}"></div>
												</c:if>
											</td>
											<td class='center'>${var.DIRECT_PUSH}</td>
											<td class='center'>
												<c:if test="${var.ACTIVATION_STATE==0}">待激活</c:if>
												<c:if test="${var.ACTIVATION_STATE!=0}">已激活</c:if>
											</td>
										<%-- 	<td class='center'>${var.ALGEBRA}</td> --%>
											<%-- <td class='center'>${var.REMARKS}</td> --%>
										<%-- 	<td class='center'>${var.ETH}</td> --%>
											<td class="center">
												<c:if test="${QX.edit != 1 && QX.del != 1 }">
												<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
												</c:if>
												<div class="hidden-sm hidden-xs btn-group">
													<c:if test="${QX.edit == 1 }">
													<a class="btn btn-xs btn-success" title="开放" onclick="edit('${var.MEMBERS_ID}');">
														<i class="ace-icon fa fa-pencil-square-o bigger-120" title="开放">开放</i>
													</a>
													</c:if>
													<c:if test="${QX.del == 1 }">
													<a class="btn btn-xs btn-danger" onclick="del('${var.MEMBERS_ID}');">
														<i class="ace-icon fa fa-trash-o bigger-120" title="封禁">封禁</i>
													</a>
													</c:if>
												</div>
												<div class="hidden-md hidden-lg">
													<div class="inline pos-rel">
														<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
															<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
														</button>
			
														<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
															<c:if test="${QX.edit == 1 }">
															<li>
																<a style="cursor:pointer;" onclick="edit('${var.MEMBERS_ID}');" class="tooltip-success" data-rel="tooltip" title="开放">
																	<span class="green">
																		<i class="ace-icon fa fa-pencil-square-o bigger-120">开放</i>
																	</span>
																</a>
															</li>
															</c:if>
															<c:if test="${QX.del == 1 }">
															<li>
																<a style="cursor:pointer;" onclick="del('${var.MEMBERS_ID}');" class="tooltip-error" data-rel="tooltip" title="封禁">
																	<span class="red">
																		<i class="ace-icon fa fa-trash-o bigger-120">封禁</i>
																	</span>
																</a>
															</li>
															</c:if>
														</ul>
													</div>
												</div>
											</td>
										</tr>
									
									</c:forEach>
									</c:if>
									<c:if test="${QX.cha == 0 }">
										<tr>
											<td colspan="100" class="center">您无权查看</td>
										</tr>
									</c:if>
								</c:when>
								<c:otherwise>
									<tr class="main_info">
										<td colspan="100" class="center" >没有相关数据</td>
									</tr>
								</c:otherwise>
							</c:choose>
							</tbody>
						</table>
						<div class="page-header position-relative">
						<table style="width:100%;">
							<tr>
							
								<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
							</tr>
						</table>
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
	<script type="text/javascript">
		$(top.hangge());//关闭加载状态
		//检索
		function tosearch(){
			top.jzts();
			$("#Form").submit();
		}
		$(function() {
		
			//日期框
			$('.date-picker').datepicker({
				autoclose: true,
				todayHighlight: true
			});
			
			//下拉框
			if(!ace.vars['touch']) {
				$('.chosen-select').chosen({allow_single_deselect:true}); 
				$(window)
				.off('resize.chosen')
				.on('resize.chosen', function() {
					$('.chosen-select').each(function() {
						 var $this = $(this);
						 $this.next().css({'width': $this.parent().width()});
					});
				}).trigger('resize.chosen');
				$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
					if(event_name != 'sidebar_collapsed') return;
					$('.chosen-select').each(function() {
						 var $this = $(this);
						 $this.next().css({'width': $this.parent().width()});
					});
				});
				$('#chosen-multiple-style .btn').on('click', function(e){
					var target = $(this).find('input[type=radio]');
					var which = parseInt(target.val());
					if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
					 else $('#form-field-select-4').removeClass('tag-input-style');
				});
			}
			
			
			//复选框全选控制
			var active_class = 'active';
			$('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
				var th_checked = this.checked;//checkbox inside "TH" table header
				$(this).closest('table').find('tbody > tr').each(function(){
					var row = this;
					if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
					else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
				});
			});
		});
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>members/goAdd.do';
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.Modal = true;				//有无遮罩窗口
			 diag. ShowMaxButton = true;	//最大化按钮
		     diag.ShowMinButton = true;		//最小化按钮
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 tosearch();
					 }else{
						 tosearch();
					 }
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function del(Id){
			bootbox.confirm("确定封禁该账号么?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>members/apply_for?MEMBERS_ID="+Id+"&STATUS=0";
					$.get(url,function(data){
						tosearch();
					});
				}
			});
		}
		
		//修改
		function edit(Id){
			bootbox.confirm("确定开放该账号么?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>members/apply_for?MEMBERS_ID="+Id+"&STATUS=1";
					$.get(url,function(data){
						tosearch();
					});
				}
			});
		}
		
		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++){
					  if(document.getElementsByName('ids')[i].checked){
					  	if(str=='') str += document.getElementsByName('ids')[i].value;
					  	else str += ',' + document.getElementsByName('ids')[i].value;
					  }
					}
					if(str==''){
						bootbox.dialog({
							message: "<span class='bigger-110'>您没有选择任何内容!</span>",
							buttons: 			
							{ "button":{ "label":"确定", "className":"btn-sm btn-success"}}
						});
						$("#zcheckbox").tips({
							side:1,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>members/deleteAll.do?tm='+new Date().getTime(),
						    	data: {DATA_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									 $.each(data.list, function(i, list){
											tosearch();
									 });
								}
							});
						}
					}
				}
			});
		};
		
		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>members/excel.do';
		}
		
		//显示图片
		function showTU(path,TPID){
			 $("#"+TPID).html('<img width="300" src="'+path+'">');
			 $("#"+TPID).show();
		}
		
		//隐藏图片
		function hideTU(TPID){
			 $("#"+TPID).hide();
		}
		
	</script>


</body>
</html>