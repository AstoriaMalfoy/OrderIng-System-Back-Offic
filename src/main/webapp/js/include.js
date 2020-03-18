$(function () {
//    首先加载header
    $.get("header.html",function (headerData) {
        $("#header").html(headerData);
    //    加载侧边栏
        $.get("sidebar.html",function (sideBarData) {
            $("#sidebar").html(sideBarData);
        //    加载底部
            $.get("footer.html",function (footerData) {
                $("#footer").html(footerData);
                global();
            })
        })
    });
});