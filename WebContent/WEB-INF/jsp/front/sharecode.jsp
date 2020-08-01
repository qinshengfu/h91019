<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>

<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>分享</title>
    <link href="static/front/css/mui.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="static/front/fonts/iconfont.css"/>
    <link rel="stylesheet" href="static/front/css/jquery.hiSlider.min.css"/>
    <link href="static/front/css/style.css" rel="stylesheet"/>

</head>

<body class="colourbg">
<header class="mui-bar mui-bar-nav">
    <a class="mui-icon mui-icon-left-nav mui-pull-left" href="front/mine"></a>
    <h1 class="mui-title">分享</h1>
</header>
<div class="mui-content nobg">


    <!--推荐码-->
    <div class="pro_code">
        <%-- <div class="pro_code_tit">
            推荐码
        </div>
        <div class="pro_code_tex">
            ${member.MEMBER}
        </div> --%>
    </div>

    <!--二维码-->
    <div class="pro_qr">
        <div class="pro_qr_1">
            请扫二维码
        </div>
        <div class="pro_qr_2">
            <img src="front/qr_code.do" alt="邀请码" />
        </div>
    </div>

    <!--链接-->
    <div class="pro_link">
        <div class="pro_link_1 mui-ellipsis">
            邀请链接
        </div>
        <div class="pro_link_2">
            <button data-clipboard-action="copy" class="copy"
                    data-clipboard-text="<%=basePath%>release/register?MEMBER=${pd.MEMBER}" id="d_clip_button">点击复制
            </button>
        </div>
    </div>

</div>


<script src="static/front/js/mui.min.js"></script>
<script type="text/javascript" src="static/front/js/flexible.js"></script>
<script type="text/javascript" src="static/front/js/jquery.min.js"></script>
<script type="text/javascript" src="static/front/js/jquery.hiSlider.min.js"></script>
<script type="text/javascript" src="static/front/js/clipboard.min.js"></script>
<script>

    var clipboard = new ClipboardJS('.copy');
    //优雅降级:safari 版本号>=10,提示复制成功;否则提示需在文字选中后，手动选择“拷贝”进行复制
    clipboard.on('success', function (e) {
        mui.toast('复制成功！');
        e.clearSelection();
    });
    clipboard.on('error', function (e) {
        mui.toast('请选择“拷贝”进行复制！');
    });

    mui('.mui-content').on('tap', 'a', function () {
        var id = this.getAttribute('href');
        var href = this.href;
        mui.openWindow({
            id: id,
            url: this.href,
            show: {
                autoShow: true
            }
        });
    });
</script>

</body>

