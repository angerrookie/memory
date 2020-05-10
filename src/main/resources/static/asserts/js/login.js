// 点击半透明部分隐藏登录注册表单
$(function () {
    $(document).click(function (e) {
        if ($(e.target).attr('id') == "form") {
            $("#form").hide();
        }
    })
});
/*$(function () {
        var stater = window.href.indexOf("=");
        var state = window.href.substring(stater+1,window.href.length);
        alert(state);
});*/
window.onload=function () {
    var url=$(location).attr("href");
    var start = url.lastIndexOf("/");
    var nation = url.lastIndexOf("=");
    var national = url.substring(nation+1,url.length);
    var state = url.substring(start+1,url.length);
    if (($.trim(state))=="login.html"||(nation!=-1&&national=="login")){
        $(".div-form-back").show();
        $(".btn-login").css("background-color", "red");
        $(".btn-register").css("background-color", "#258EFE");
        $(".form-register").hide();
    }
    if (($.trim(state))=="register.html"||(nation!=-1&&national=="register")){
        $(".div-form-back").show();
        $(".btn-register").css("background-color", "red");
        $(".btn-login").css("background-color", "#258EFE");
        $(".form-register").show();
    }
    if (($.trim(state))=="userInfo"){
        $(".state-btn-exit").css("background-color","#ACACAC");
    }
}

function national(str) {
    var color = $(".btn-login").css("background-color");
    var url="&state=login";
    // alert(color);
    if (color!='rgb(255, 0, 0)'){
        // alert("中文");
        url="&state=register";
    }
    window.location.href = str+url;
}
//点击登录按钮  显示登录注册表单  切换到登录
function login(valu) {
    var value = $(valu).attr("value");
    if (value=="登录"){
        $(".div-form-back").show();
        $(".btn-login").css("background-color", "red");
        $(".btn-register").css("background-color", "#258EFE");
        $(".form-register").hide();
        $(".span-error1").hide();
    }else if (value=="用户名"){
        //点击用户名 跳转到基本信息
        window.location.href="/userInfo";
    }else if (value=="注册"){
        $(".div-form-back").show();
        $(".btn-register").css("background-color", "red");
        $(".btn-login").css("background-color", "#258EFE");
        $(".form-register").show();
        $(".span-error").hide();
        $(".span-error1").hide();
    }else if (value=="退出"){
        window.location.href="/exit";
    }
};