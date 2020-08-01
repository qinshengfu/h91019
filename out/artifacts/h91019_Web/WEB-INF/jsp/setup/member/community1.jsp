<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>jOrgChart - A jQuery OrgChart Plugin</title>
    <link rel="stylesheet" href="<%=basePath%>/static/ace/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>/static/front/css/jquery.jOrgChart.css"/>
    <link rel="stylesheet" href="<%=basePath%>/static/front/css/custom.css"/>
    <link href="<%=basePath%>/static/front/css/prettify.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=basePath%>/static/front/js/prettify.js"></script>
    <script type="text/javascript" src="<%=basePath%>/static/front/js/jquery.min.js"></script>
    <script src="<%=basePath%>/static/front/js/jquery.jOrgChart.js"></script>

    <script>
        $(top.hangge());
        jQuery(document).ready(function () {
            $("#org").jOrgChart({
                chartElement: '#chart',
                dragAndDrop: true
            });
        });
    </script>
    <style>
        table{
            margin: auto;
        }
    </style>
</head>

<body onload="prettyPrint();">
<div style="text-align:center;margin:10px 0; font:normal 14px/24px 'MicroSoft YaHei';">
    <div style="font-size: xx-large;">接点关系图</div><br>
    <form method="post" id="chaxun" action="<%=basePath%>/member/contact.do">
        置顶会员账户：<input type="text" name="MEMBER" id="MEMBER" value="${pd.MEMBER}" style="color: black" maxlength="20" title="帐号查询">
        <input type="submit" name="Submit" style="background: red" value="置顶" class="btn1">
    </form>
	
</div>

<ul id="org" style="display:none;">
    <c:choose>
        <c:when test="${fn:length(map)>1}">
            <c:forEach items="${map}" var="item">
                <c:if test="${pd.MEMBER eq item.key}">
                    <li data-module="b">
                        <div onclick="zhiding('${item.key}')">
                        	<p>所处位置：${top.PLACE}</p>
	                        <p>层数：${top.C_LEVEL}</p>
	                        <p>账号：${item.key }</p>
	                        <p>等级：${top.MEM_LEVEL}</p>
	                        <p>佣金：${top.COMMISSION}</p>
	                        <p>积分：${top.INTEGRAL}</p>
                        </div>
                        <ul class="">
                            <c:forEach items="${item.value}" var="item2">
                                <li class="" data-module="b">
                               		<div onclick="zhiding('${item2.MEMBER}')">
                                          <p>所处位置：${item2.PLACE}</p>
					                      <p>层数：${item2.C_LEVEL}</p>
					                      <p>账号：${item2.MEMBER}</p>
					                      <p>等级：${item2.MEM_LEVEL}</p>
					                      <p>佣金：${item2.COMMISSION}</p>
	                        			  <p>积分：${item2.INTEGRAL}</p>
	                                </div>
	                                
                                    <ul class="">
                                        <c:forEach items="${map}" var="item3">
                                            <c:if test="${item2.MEMBER eq item3.key}">
                                                <c:forEach items="${item3.value}" var="item4">
                                                    <li class="" data-module="b">
                                                  		<div onclick="zhiding('${item4.MEMBER}')">
                                                        	<p>所处位置：${item4.PLACE}</p>
									                        <p>层数：${item4.C_LEVEL}</p>
									                        <p>账号：${item4.MEMBER}</p>
									                        <p>等级：${item4.MEM_LEVEL}</p>
									                        <p>佣金：${item4.COMMISSION}</p>
									                        <p>积分：${item4.INTEGRAL}</p>
						                                </div>
                                                        <ul class="">
                                                            <c:forEach items="${map}" var="item5">
                                                                <c:if test="${item4.MEMBER eq item5.key}">
                                                                    <c:forEach items="${item5.value}" var="item6">
                                                                        <li class="" data-module="b">
                                                                           	<div onclick="zhiding('${item6.MEMBER}')">
                                                                               <p>所处位置：${item6.PLACE}</p>
														                       <p>层数：${item6.C_LEVEL}</p>
														                       <p>账号：${item6.MEMBER}</p>
														                       <p>等级：${item6.MEM_LEVEL}</p>
														                       <p>佣金：${item6.COMMISSION}</p>
									                        				   <p>积分：${item6.INTEGRAL}</p>
											                                </div>
                                                                            
                                                                            <ul class="">
                                                                                <c:forEach items="${map}" var="item7">
                                                                                    <c:if test="${item6.MEMBER eq item7.key}">
                                                                                        <c:forEach items="${item7.value}" var="item8">
                                                                                            <li class="" data-module="b">
                                                                                                
                                                                                               		<div onclick="zhiding('${item8.MEMBER}')">
                                                                                                        <p>所处位置：${item8.PLACE}</p>
																				                        <p>层数：${item8.C_LEVEL}</p>
																				                        <p>账号：${item8.MEMBER}</p>
																				                        <p>等级：${item8.MEM_LEVEL}</p>
																				                        <p>佣金：${item8.COMMISSION}</p>
									                        											<p>积分：${item8.INTEGRAL}</p>
																                                	</div>
	                                                                                                <ul class="">
	                                                                                                    <li
	                                                                                                        data-module="b">
	                                                                                                        <a onclick="chaxun('${item8.MEMBER}')">查询上级加载更多。。</a>
	                                                                                                    </li>
	                                                                                                </ul>
                                                                                            </li>
                                                                                        </c:forEach>
                                                                                    </c:if>
                                                                                </c:forEach>
                                                                            </ul>
                                                                        </li>
                                                                    </c:forEach>
                                                                </c:if>
                                                            </c:forEach>
                                                        </ul>
                                                    </li>
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </c:if>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <li data-module="b">
                <div onclick="zhiding('${top.MEMBER}');">
                   <p>所处位置：${top.PLACE}</p>
                   <p>层数：${top.C_LEVEL}</p>
                   <p>账号：${top.MEMBER}</p>
                   <p>等级：${top.MEM_LEVEL}</p>
                   <p>佣金：${top.COMMISSION}</p>
				   <p>积分：${top.INTEGRAL}</p>
                </div>
                <%-- <ul>
                	<c:set var="var" value="${top.MEMBER}"></c:set>
               		<c:forEach var="v" items="${map[var]}">
	               		<li data-module="b">
                			<div onclick="zhiding('${v.MEMBER}');">
	                			<p>${v.MEMBER }|${v.C_LEVEL}</p>
	                        </div>
	                	</li>
               		</c:forEach>
                </ul> --%>
            </li>
            
        </c:otherwise>
    </c:choose>

</ul>
<!-- </li>
</ul> -->


<div id="chart" class="orgChart"></div>
<script>
    function chaxun(id) {
        $("#MEMBER").val(id);
        $("#chaxun").submit();
    }
</script>
<script type="text/javascript">
function zhiding(cellphone){
	location.href='<%=basePath%>/member/contact.do?MEMBER='+cellphone;
}
</script>


</body>
</html>