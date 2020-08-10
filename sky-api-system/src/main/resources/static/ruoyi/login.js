$(function () {
    validateKickout();
    validateRule();
    $('.captchaCode').click(function () {
        let url = "captcha" + "?s=" + Math.random();
        $(".captchaCode").attr("src", url);
    });
});

$.validator.setDefaults({
    submitHandler: function () {
        login();
    }
});

function login() {
    $.modal.loading($("#btnSubmit").data("loading"));
    let username = $.common.trim($("input[name='username']").val());
    let password = $.common.trim($("input[name='password']").val());
    let remember = $("input[name='remember-me']").val();
    let captcha = $("input[name='captcha']").val();
    $.ajax({
        type: "post",
        url: ctx + "login",
        data: {
            "username": username,
            "password": password,
            "remember-me": remember,
            "captcha": captcha,
        },
        success: function (result) {
            if (result.status === 200) {
                location.href = ctx + 'index';
            } else {
                $.modal.closeLoading();
                $('.captchaCode').click();
                $(".captcha").val("");
                $.modal.msg(result.msg);
            }
        }
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            username: {
                required: icon + "账号为空",
            },
            password: {
                required: icon + "密码为空",
            }
        }
    })
}

function validateKickout() {
    if (getParam("kickout") == 1) {
        layer.alert("<font color='red'>您已在别处登录，请您修改密码或重新登录</font>", {
                icon: 0,
                title: "系统提示"
            },
            function (index) {
                //关闭弹窗
                layer.close(index);
                if (top != self) {
                    top.location = self.location;
                } else {
                    var url = location.search;
                    if (url) {
                        var oldUrl = window.location.href;
                        var newUrl = oldUrl.substring(0, oldUrl.indexOf('?'));
                        self.location = newUrl;
                    }
                }
            });
    }
}

function getParam(paramName) {
    var reg = new RegExp("(^|&)" + paramName + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]);
    return null;
}