window.onload = function () {
    $(".table-input").attr("readonly", true);
    $(".table-input").css("background", "none");
    $(".table-input").css("border", "none");
    $(".table-input").css("text-align", "center");
    $(".table-submit").hide();
    $(".table-select").attr("disabled", true);
    $(".table-style").css("background","#E3DFDF");
    $(".state-btn-exit").css("background-color","#ACACAC");
};

function update() {
    $(".table-input").attr("readonly", false);
    $(".table-input").css("background", "white");
    $(".table-input").css("border", "1px solid black");
    $(".table-input").css("text-align", "center");
    $(".table-submit").show();
    $(".table-select").attr("disabled", false);
    $(".table-style").css("background","#EAE7E7");
};

//ajax实现学院与专业绑定
function setMajor(collegeId) {
    //获取选择的学院ID
    // var collegeId = $(".table-select").val();
    $.ajax({
        type: "POST",
        url: "/userInfo",
        data: {"collegeId": collegeId},
        dataType: "json",
        success: function (data) {
            // 移除以前的绑定数据
            $(".table-select-major option").remove();
            //把后台传来的JSON格式转化为对象
            majors = eval(data);
            //jsonData是List数组
            for (x in majors) {
                //遍历JSON格式的数组取元素, x代表下标
                var option = "<option value=\"" + majors[x].majorId + "\"";
                option += ">" + majors[x].majorName + "</option>";
                $("select[name=majorId]").append(option);
            }
            // 保证jQuery的选择插件动态绑定数据生效
            $(".table-select-major").trigger("liszt:updated");
            $(".table-select-major").chosen();
        }
    });
};
function setSelected(obj) {
    alert("--select--")
    $(obj).attr("selected","selected");
};