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
    <title>个人中心</title>
    <link href="static/front/css/mui.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="static/front/fonts/iconfont.css"/>
    <link rel="stylesheet" href="static/front/css/jquery.hiSlider.min.css"/>
    <link href="static/front/css/style.css" rel="stylesheet"/>

</head>

<body>
<div class="mui-content pbom50">
    <div class="minetop">
        <div class="mui-content-padded" style="position: relative;">
            <form action="front/avatar" id="Form" method="post">
                <c:if test="${! not empty member.AVATAR}">
                    <img id="headimg" src="static/front/images/minebg.png" class="userlooimg" onclick="file0();"/>
                </c:if>
                <c:if test="${not empty member.AVATAR}">
                    <img id="headimg" src="${member.AVATAR}" class="userlooimg" onclick="file0();"/>
                </c:if>
                <input type="file" id="file" style="display: none;" name="file" onchange="previewImage(this);">
                <input type="hidden" id="AVATAR" value="${member.AVATAR}">
                <div class="minename">
                    <%-- <p>${member.NAME}</p> --%>
                    <p>ID：${member.MEMBER }</p>
                    <p>等级：${member.MEM_LEVEL}</p>
                    <p>推荐人：${member.REFERRER}</p>
                    <p style="position: absolute;left: 230px;top:30px">
                        <c:if test="${member.CHECKIN == 'no'}">
                            <input type="button" value="签到" onclick="checkin('${member.MEMBER_ID}');">
                        </c:if>
                        <c:if test="${member.CHECKIN == 'yes'}">
                            <input type="button" value="签到" style="background-color: #aaa;color: #ccc;">
                        </c:if>
                    </p>
                </div>
            </form>
        </div>
    </div>
    <div class="mineassets">
        <ul class="flexaroundplay">
            <li>
                <a href="front/yj" style="color: #000;">
                    <b>${member.COMMISSION}</b>
                    <p style="margin-bottom: 0;">佣金</p>
                    <b>${member.YJ}</b>
                </a>
            </li>
            <li>
                <a href="front/jf" style="color: #000;">
                    <b>${member.INTEGRAL}</b>
                    <p style="margin-bottom: 0;">积分</p>
                    <b>${member.JF}</b>
                </a>
            </li>
            <li>
                <a href="front/cf" style="color: #000;">
                    <b>${member.WEALTH}</b>
                    <p style="margin-bottom: 0;">财富钱包</p>
                    <b>${member.CF}</b>
                </a>
            </li>
        </ul>
    </div>
    <div class="mineoption">
        <ul>
            <li>
                <a href="front/myorderlist">
                    <img src="static/front/images/mine1.png"/>
                    <p>订单</p>
                </a>
            </li>
            <li>
                <a href="front/recharge">
                    <img src="static/front/images/mine2.png"/>
                    <p>充值</p>
                </a>
            </li>
            <li>
                <a href="front/withdrawal">
                    <img src="static/front/images/mine3.png"/>
                    <p>提现</p>
                </a>

            </li>
            <li>
                <a href="front/exchange">
                    <img src="static/front/images/mine4.png"/>
                    <p>兑换</p>
                </a>
            </li>
            <li>
                <a href="front/sharecode">
                    <img src="static/front/images/mine5.png"/>
                    <p>分享</p>
                </a>
            </li>
            <li>
                <a href="front/mybonuslist">
                    <img src="static/front/images/mine6.png"/>
                    <p>资产明细</p>
                </a>
            </li>
            <li>
                <a href="front/myteam">
                    <img src="static/front/images/mine7.png"/>
                    <p>我的团队</p>
                </a>
            </li>
            <li>
                <a href="front/verified">
                    <img src="static/front/images/verified2.png"/><!-- mine8.png -->
                    <p>实名认证</p>
                </a>
            </li>
            <li>
                <a href="front/updatepsw">
                    <img src="static/front/images/mine9.png"/>
                    <p>修改密码</p>
                </a>
            </li>
            <li>
                <a href="front/gift">
                    <img src="static/front/images/gift.png"/>
                    <p>星级赠送</p>
                </a>
            </li>
            <li>
                <a href="front/liuyan">
                    <img src="static/front/images/ly1.png"/>
                    <p>留言</p>
                </a>
            </li>
            <li>
                <div onclick="kefu()">
                    <img src="static/front/images/kf1.png"/>
                    <p>客服</p>
                </div>
            </li>
        </ul>
    </div>
    <div class="mui-content-padded">
        <button type="button" class="logoutbtn" onclick="logout();">退出</button>
    </div>
    <!-- <label style='text-align: center;'>11111</label> -->
</div>

<footer class="footer">
    <div class="flexaroundplay footnav">
        <a href="front/index"><i class="iconfont icon-shouye6"></i>财富商城</a>
        <a href="front/mallshop"><i class="iconfont icon-chanpinzhengce"></i>积分商城</a>
        <a href="front/mine" class="active"><i class="iconfont icon-wode6"></i>我的</a>
    </div>
</footer>

<script src="static/front/js/mui.min.js"></script>
<script type="text/javascript" src="static/front/js/flexible.js"></script>
<script type="text/javascript" src="static/front/js/jquery.min.js"></script>
<script type="text/javascript" src="static/front/js/jquery.hiSlider.min.js"></script>
<script type="text/javascript" src="static/front/layui/layui.all.js"></script>
<script type="text/javascript" src="static/front/js/jquery.form.js"></script>
<script src="static/js/jquery.tips.js"></script>
<script>

    //客服
    function kefu() {
        var kefu = [];

        kefu += "<p style='text-align: center;'>客服电话：" + "${parameter.PHONE}" + "</p>";
        kefu += "<p style='text-align: center;'>微信二维码</p>";
        kefu += "<p style='text-align: center;'>" + "<img height='150' width='150' src='${parameter.WECHAT1}'>" + "</p>";

        layer.open({
            type: 1,
            title: '客服',
            offset: '100px',
            shadeClose: true,
            area: ['80%', 'auto'],
            skin: 'kefu',
            content: kefu,
            btn: ['确定'],
            btn1: function (index) {
                //按钮【确定】的回调
                layer.close(index)
            },
        });
    }

    //签到
    function checkin(Id) {
        $.ajax({
            type: 'post',
            url: 'front/checkin',
            data: {MEMBER_ID: Id},
            success: function (data) {
                if (data == 'success') {
                    window.location.href = 'front/mine';
                }
            }
        });
    }

    //上传头像
    function file0() {
        $("#file").click();
    }

    //上传图片
    function previewImage(file) {
        var filePath = file.value;
        var fileExt = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();
        if (!fileExt.match(/.jpg|.gif|.png|.bmp|.JPG|.GIF|.BNG|.BMP/i)) {
            $("#headimg").tips({side: 3, msg: '请上传图片文件!!!', bg: '#AE81FF', time: 2});
            //$("#file").focus();
            return false;
        }
        if ($("#file").val()) {
            // $("#imghead").addClass("tx");
            var url = "front/upload";

            //异步提交表单(先确保jquery.form.js已经引入了)
            var options = {
                url: url,
                success: function (data) {
                    register_path = (data + "").trim();
                    var sta = register_path;
                    <%--  "<%=basePath%>"+ --%>
                    $("#AVATAR").val(register_path);
                    $("#headimg").attr({src: sta});
                    $("#headimg").tips({side: 3, msg: '上传成功', bg: '#AE81FF', time: 1});
                    /* $("#file").focus(); */
                    var AVATAR = $("#AVATAR").val();
                    $.ajax({
                        type: 'post',
                        url: 'front/avatar',
                        data: {MEMBER_ID: '${member.MEMBER_ID}', AVATAR: AVATAR},
                        success: function (data) {
                            window.location.href = 'front/mine';
                        }
                    });
                }
            };
            $("#Form").ajaxSubmit(options);
        }

    }

    //退出登录
    function logout() {
        layer.confirm('您确定要退出系统吗？', {
            title: '提示',
            btn: ['确定', '取消'] //可以无限个按钮
            , offset: '200px'
            , btn2: function (index, layero) {
                //按钮【取消】的回调
            }
        }, function (index) {
            //按钮【确定】的回调
            window.location.href = 'front/logout';
        });
    }

    // 生成二维码
    function createTwoD() {
        var encoderContent = "<%=basePath%>release/register?MEMBER=${member.MEMBER}";
        $.ajax({
            type: "post",
            url: "front/createTwoDimensionCode",
            data: {encoderContent: encoderContent, tm: new Date().getTime()},
            success: function (data) {
                if (data.result == "success") {
                    window.location.href = "front/sharecode?encoderImgId=" + data.encoderImgId;
                }
            }
        });
    }

    mui('.mineoption').on('tap','a',function(){
        window.top.location.href=this.href;
    });

</script>


</body>

</html>