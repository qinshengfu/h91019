<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!doctype html>
<html><!-- style="background: #f1f4fd !important;" -->

<head>
	<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>我的团队</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link href="static/front/css/mui.min.css?v=1" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="static/front/css/modify.css?v=1"/>
    <link rel="stylesheet" type="text/css" href="static/front/css/iconfont.css?v=1"/>
    <link rel="stylesheet" href="<%=basePath%>static/front/css/style.css">
    <script src="static/front/js/jquery.min.js"></script>
    <style type="text/css">
	   
    </style>

    <style type="text/css">
    	.mui-col-xs-20{width: 28%;}
    	.mui-col-xs-21{width: 7%;}
        .card a{color: #59a3ff;position: absolute;right: 20px;font-size: 12px}/* float: right; */
        html, body, ul, li { margin:0 auto; padding:0;}
        ul, li { list-style-type:none; color:#59a3ff; }
        ul.sTree, ul{ padding:0px;}
        ul.sTree2 li, ul#sortableListsBase li { padding-left:30px; margin:0px; }
        li div { font-size: 12px;padding:7px;}/* padding:7px; */
        li, ul, div { border-radius: 0px; }
        .red { background-color:#ff9999; }
        .blue { background-color:#aaaaff;}
        .green { background-color:#99ff99; }
        .gree2 { background-color:#bbffbb; }
        .yellow { background-color:#ff8; }
        .brown { background-color:#c26b2b; }
        .pT20 { padding-top:20px; }
        .pV10 { padding-top:10px; padding-bottom:10px; }
        .dN { display:none; }
        .zI1000 { z-index:1000; }
        .c1 { color:#b5e853; }
        .c2 { color:#63c0f5; }
        .c3 { color: #f77720; }
        .c4 { color: #888; }
        .bgC1 { background-color:#ccc; }
        .bgC2 { background-color:#ff8; }
        .small1 { font-size:0.8em; }
        .small2 { font-size:0.7em; }
        .small3 { font-size:0.6em; }
        .tAR { text-align:right; }
        .clear { clear:both; }
        img.descPicture { display:block; width:100%; margin:0 7px 30px 0; float:left; cursor:pointer; /*transition: all 0.5s ease;*/ }
        img.descPicture.descPictureClose { width:150px; }
        #sTree2 { margin:10px auto; }
       .chacunk{
       		float: right;
       }
       .fanhui{
       		float: left;
       }
       .cxun:after{
       	dispay:block;
       	clear:both;
       	content:'';
       }
       /* div {
    display: block !important;
} */
    </style>

    <script type="text/javascript">
        $(function()
        {
            var options = {
                    placeholderCss: {'background-color': '#ff8'},
                    hintCss: {'background-color':'#bbf'},
                    isAllowed: function(cEl, hint, target)
                    {
                        if(hint.parents('li').first().data('module') === 'c' && cEl.data('module') !== 'c')
                        {
                            hint.css('background-color', '#ff9999');
                            return false;
                        }
                        else
                        {
                            hint.css('background-color', '#99ff99');
                            return true;
                        }
                    },
                    opener: {
                        active: true,
                        close: 'static/front/images/Remove2.png',
                        open: 'static/front/images/Add2.png',
                        openerCss: {
                            'display': 'inline-block',
                            'width': '18px',
                            'height': '18px',
                            'float': 'left',
                            'margin-left': '-35px',
                            'margin-right': '5px',
                            'background-position': 'center center',
                            'background-repeat': 'no-repeat'
                        },
                        openerClass: ''
                    }
                },
                sWrapper = $('#settingsWrapper');
            $('#sTree2, #sTree').sortableLists(options);
            // $('#toArrBtn').on('click', function(){ console.log($('#sTree2').sortableListsToArray()); });
            // $('#toHierBtn').on('click', function() { console.log($('#sTree2').sortableListsToHierarchy()); });
            // $('#toStrBtn').on('click', function() { console.log($('#sTree2').sortableListsToString()); });
            // $('.descPicture').on('click', function(e) { $(this).toggleClass('descPictureClose'); });
        });
    </script> 
    <%--<script>--%>
		<%--$(function(){--%>
			<%--$('#btn').click(function(){--%>
				<%--var id = $('#cha').val();--%>
				<%--window.location.href="${pageContext.request.contextPath}/member/tree.do?username="+id;--%>
			<%--})--%>
		<%--});    --%>
    <%----%>
    <%--</script>--%>
</head>

<body>
<!-- <header class="mui-bar mui-bar-nav">
    <a href="index.do"><i class="iconfont fh">&#xe606;</i></a>
    <h1 class="mui-title">我的团队</h1>
</header> -->
<header class="mui-bar mui-bar-nav">
	<a class="mui-icon mui-icon-left-nav mui-pull-left" href="front/mine"></a>
	<h1 class="mui-title">我的团队</h1>
</header>

<div class="mui-content"><!--  style="background: #f1f4fd !important;" -->
	<div>
		<div class="release-content">
			<div class="mui-row team-view">
				<div class="mui-col-xs-6" style="border:1px solid #ccc;">
					<p align="center" class="team-p">市场总业绩</p>
					<p align="center" class="team-pw">${market.SCYJ}</p>
				</div>
				<div class="mui-col-xs-6" style="border:1px solid #ccc;">
					<p align="center" class="team-p">市场总人数</p>
					<p align="center" class="team-pw">${market.SCRS}</p>
				</div>
			</div>
			<div class="mui-row team-view">
				<div class="mui-col-xs-4" style="border:1px solid #ccc;">
					<p align="center" class="team-p">团队总业绩</p>
					<p align="center" class="team-pw">
						${team.TDYJ}
						<c:if test="${! not empty team.TDYJ}">0</c:if>
					</p>
				</div>
				<div class="mui-col-xs-4" style="border:1px solid #ccc;">
					<p align="center" class="team-p">团队总人数</p>
					<p align="center" class="team-pw">${team.TDRS}</p>
				</div>
				<div class="mui-col-xs-4" style="border:1px solid #ccc;">
					<p align="center" class="team-p">新增业绩</p>
					<p align="center" class="team-pw">${team.TDXYJ}<c:if test="${! not empty team.TDXYJ}">0</c:if></p>
				</div>
			</div>
		</div>
	</div>
    <div class="content-thtb">

        <%--<div class="chacunyh">--%>
	        <%--<div class="chacunyh-right">--%>
	        	<%--<span>用户查询：</span>--%>
	        <%--</div>--%>
	         <%--<div class="chacunyh-left">--%>
	        	 <%--<input id="cha">--%>
	        <%--</div>--%>
            <%--<div class="cxun"><button onclick="history.back();" class="fanhui">返回</button><button id="btn" class="chacunk">查询</button></div>--%>
        <%--</div>--%>
        <div class="tabs">
            <div class="card">
                <ul class="sTree2 listsClass" id="sTree2">
                    <li id="item_a" data-module="a">
                        <div>${top.MEMBER}&nbsp;&nbsp;级别:${top.MEM_LEVEL}&nbsp;&nbsp;直推:${top.DIRECTPUSH}</div>
                        <c:set var="id1" value="${top.MEMBER}"></c:set>
                        <c:if test="${not empty map[id1]}">
                            <ul>
                                <c:forEach items="${map[id1]}" var="item1">
                                    <li>
                                        <div>${item1.MEMBER}&nbsp;&nbsp;级别:${item1.MEM_LEVEL}&nbsp;&nbsp;直推:${item1.DIRECTPUSH}<a href="front/myteam?MEMBER=${item1.MEMBER}">置顶</a></div>
                                        <c:set var="id2" value="${item1.MEMBER}"></c:set>
                                        <c:if test="${not empty map[id2]}">
                                            <ul style="display:block;">
                                                <c:forEach items="${map[id2]}" var="item2">
                                                    <li>
                                                        <div>${item2.MEMBER}&nbsp;&nbsp;级别:${item2.MEM_LEVEL}&nbsp;&nbsp;直推:${item2.DIRECTPUSH}<a href="front/myteam?MEMBER=${item2.MEMBER}">置顶</a></div>
                                                        <c:set var="id3" value="${item2.MEMBER}"></c:set>
                                                        <c:if test="${not empty map[id3]}">
                                                            <ul>
                                                                <c:forEach items="${map[id3]}" var="item3">
                                                                    <li>
                                                                        <div>${item3.MEMBER}&nbsp;&nbsp;级别:${item3.MEM_LEVEL}&nbsp;&nbsp;直推:${item3.DIRECTPUSH}<a href="front/myteam?MEMBER=${item3.MEMBER}">置顶</a></div>
                                                        				<c:set var="id4" value="${item3.MEMBER}"></c:set>
                                                                        <c:if test="${not empty map[id4]}">
                                                                           <%--  <ul>
                                                                                <c:forEach items="${map[id4]}" var="item4">
                                                                                    <li>
                                                                                        <div>${item4.MEMBER}&nbsp;&nbsp;级别:${item4.MEM_LEVEL}&nbsp;&nbsp;直推:${item4.DIRECTPUSH}<a href="front/myteam?MEMBER=${item4.MEMBER}">置顶</a></div>
                                                        								<c:set var="id5" value="${item4.MEMBER}"></c:set>
                                                                                        <c:if test="${not empty map[id5]}">
                                                                                            <ul>
                                                                                                <c:forEach items="${map[id5]}" var="item5">
                                                                                                    <li>
                                                                                                        <div>${item5.MEMBER}&nbsp;&nbsp;级别:${item5.MEM_LEVEL}&nbsp;&nbsp;直推:${item5.DIRECTPUSH}<a href="front/myteam?MEMBER=${item5.MEMBER}">置顶</a></div>
                                                                                                        
                                                                                                    </li>
                                                                                                </c:forEach>
                                                                                            </ul>
                                                                                        </c:if>
                                                                                    </li>
                                                                                </c:forEach>
                                                                            </ul> --%>
                                                                        </c:if>
                                                                    </li>
                                                                </c:forEach>
                                                            </ul>
                                                        </c:if>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </c:if>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </li>
                </ul>
            </div>
        </div>
        <div style="height: 5em;"></div>
    </div>
</div>
<script src="static/front/js/mui.min.js"></script>
<script src="static/front/js/jquery-git1.min.js"></script>
<script src="static/front/js/jquery-sortable-lists.min.js"></script>
<script type="text/javascript">
    mui.init()
</script>

</body>

</html>