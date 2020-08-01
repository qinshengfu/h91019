<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html class="ui-page-login">

<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>找回密码</title>
    <link href="static/front/css/mui.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="static/front/fonts/iconfont.css"/>
    <link href="static/front/css/style.css" rel="stylesheet"/>

</head>

<body class="loginbg">
<header class="mui-bar mui-bar-nav nobg">
    <a class="mui-icon mui-icon-left-nav mui-pull-left whitecolor" href="release/login"></a>
    <h1 class="mui-title whitecolor">找回密码</h1>
</header>
<div class="mui-content nobg">
    <div class="mui-text-center login">
        <img src="static/front/images/logo.png" alt="logo" class="logo">
    </div>
    <div class="formbox">
        <div class="forminp">
            <i class="iconfont icon-yonghu4 chingcolor"></i>
            <input type="text" placeholder="请输入账号" id="MEMBER" onchange="getPhone();"/>
        </div>
        <div class="forminp">
            <i class="iconfont icon-shouji chingcolor"></i>
            <input type="text" placeholder="请输入手机号" id="PHONE"/>
        </div>
        <div class="forminp">
            <i class="iconfont icon-anquan2 chingcolor"></i>
            <input type="text" placeholder="请输入短信验证码" id="inCode"/>
            <input type="button" value="获取验证码" onclick="settime(this,'1')"/>
        </div>
        <div class="forminp">
            <i class="iconfont icon-mima chingcolor"></i>
            <input type="password" placeholder="请输入密码" id="PWD"/>
        </div>

        <div class="forminp">
            <i class="iconfont icon-mima chingcolor"></i>
            <input type="password" placeholder="请确认密码" id="PWD1"/>
        </div>
        <div class="mui-content-padded">
            <button type="button" class="mui-btn mui-btn-block stmbutton" onclick="forget();">确定</button>
        </div>
    </div>

</div>
<script src="static/front/js/jquery-1.11.3.min.js"></script>
<script src="static/front/js/mui.min.js"></script>
<script src="static/js/jquery.tips.js"></script>
<script>

    function getPhone() {
        var MEMBER = $("#MEMBER").val();
        $.ajax({
            type: 'post',
            url: 'release/getPhone',
            data: {MEMBER: MEMBER},
            success: function (data) {
                if (data == 'memberError') {
                    $("#PHONE").val('');
                    $("#MEMBER").tips({
                        side: 3,
                        msg: '账号未注册',
                        bg: '#AE81FF',
                        time: 3
                    });
                    $("#MEMBER").focus();
                } else {
                    $("#PHONE").val(data);
                }
            }
        });
    }

    function forget() {
        if (check()) {
            var MEMBER = $("#MEMBER").val();
            var PHONE = $("#PHONE").val();
            var inCode = $("#inCode").val();
            var PWD = $("#PWD").val();

            $.ajax({
                type: 'post',
                url: 'release/forget_do',
                data: {PHONE: PHONE, inCode: inCode, PWD: PWD, MEMBER: MEMBER},
                success: function (data) {
                    if (data == 'success') {
                        window.location.href = 'release/login';
                    } else if (data == 'code') {
                        //短信验证码错误
                        $("#inCode").tips({
                            side: 3,
                            msg: '短信验证码错误',
                            bg: '#AE81FF',
                            time: 3
                        });
                        $("#inCode").focus();
                    } else if (data == 'memberError') {
                        //手机号未注册
                        $("#MEMBER").tips({
                            side: 3,
                            msg: '账号未注册',
                            bg: '#AE81FF',
                            time: 3
                        });
                        $("#MEMBER").focus();
                    }
                }
            });
        }
    }

    //效验
    function check() {
        if ($("#MEMBER").val() == "") {
            $("#MEMBER").tips({
                side: 3,
                msg: '请输入账号',
                bg: '#AE81FF',
                time: 3
            });
            $("#MEMBER").focus();
            return false;
        }
        if ($("#PHONE").val() == "") {
            $("#PHONE").tips({
                side: 3,
                msg: '请输入手机号',
                bg: '#AE81FF',
                time: 3
            });
            $("#PHONE").focus();
            return false;
        }
        if ($("#inCode").val() == "") {
            $("#inCode").tips({
                side: 3,
                msg: '请输入短信验证码',
                bg: '#AE81FF',
                time: 3
            });
            $("#inCode").focus();
            return false;
        }
        if ($("#PWD").val() == "") {
            $("#PWD").tips({
                side: 3,
                msg: '请输入密码',
                bg: '#AE81FF',
                time: 3
            });
            $("#PWD").focus();
            return false;
        }
        if ($("#PWD").val() != $("#PWD1").val()) {
            $("#PWD1").tips({
                side: 3,
                msg: '两次密码不一致!',
                bg: '#AE81FF',
                time: 3
            });
            $("#PWD1").focus();
            return false;
        }
        return true;
    }

    var countdown = 60;

    function settime(obj, mark) {
        var PHONE = $("#PHONE").val();
        if (PHONE != '') {
            if (mark == '1') {
                console.log('发送验证码');
                $.ajax({
                    type: 'post',
                    url: 'release/sendCode',
                    data: {PHONE: PHONE},
                    success: function (data) {
                        console.log(data)
                        if (data == '手机号未注册！') {
                            $("#PHONE").tips({
                                side: 3,
                                msg: data,
                                bg: '#AE81FF',
                                time: 3
                            });
                            $("#PHONE").focus();
                            return false;
                        }
                        countDown(obj)
                    }
                });
            }
        } else {
            if ($("#PHONE").val() == "") {
                $("#PHONE").tips({
                    side: 3,
                    msg: '请输入手机号',
                    bg: '#AE81FF',
                    time: 3
                });
                $("#PHONE").focus();
                return false;
            }
        }
    }

    // 倒计时
    function countDown(obj) {
        if (countdown == 0) {
            obj.removeAttribute("disabled");
            obj.value = "获取验证码";
            countdown = 60;
            return;
        } else {
            obj.setAttribute("disabled", true);
            obj.value = "重新发送(" + countdown + ")";
            countdown--;
        }

        setTimeout(function () {
            settime(obj, '0')
        }, 1000)
    }

</script>

</body>

</html>