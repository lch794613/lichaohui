var start=0;
$(function () {
    if(eval(start)===eval(0)){
        $(".up").css("display","none");
    }
    $.ajax({url:"http://localhost:8080/commit/ajax",
        data:{"start":start},
        success:function (data) //每次返回8个对象
        {
            for(d in data){
                var index=data[d]["id"]
                var i=parseInt(d)+parseInt(start)+parseInt(1);
                var user=data[d]["ip"];
                var cont=data[d]["cont"];
                var Time=data[d]["time"];
                var time=formatDate(Time);
                var title="第"+i+"条评论,用户:"+user+",时间:"+time;
                var t=parseInt(d)+parseInt(1);
                var u="u"+t;
                $("#"+u).find(".l1").text(title);
                $("#"+u).find(".l2").text(cont);
            }

    }})
});
function add0(m){return m<10?'0'+m:m }
function   formatDate(timestamp)   {
    var time = new Date(timestamp);
    var year = time.getFullYear();
    var month = time.getMonth()+1;
    var date = time.getDate();
    var hours = time.getHours();
    var minutes = time.getMinutes();
    var seconds = time.getSeconds();
    return year+'-'+add0(month)+'-'+add0(date)+' '+add0(hours)+':'+add0(minutes)+':'+add0(seconds);
}
$(function () {

    $(".up").click(function () {
        start-=8;
        $.ajax({url:"http://localhost:8080/commit/ajax",
            data:{"start":start},
            success:function (data) //每次返回9个对象
            {
                if(eval(data.length)==eval(9)){
                    $(".down").css("display","block");
                }
                for(d in data){
                    var index=data[d]["id"]
                    var i=parseInt(d)+parseInt(start)+parseInt(1);
                    var user=data[d]["ip"];
                    var cont=data[d]["cont"];
                    var Time=data[d]["time"];
                    var time=formatDate(Time);
                    var title="第"+i+"条评论,用户:"+user+",时间:"+time;
                    var t=parseInt(d)+parseInt(1);
                    var u="u"+t;
                    $("#"+u).find(".l1").text(title);
                    $("#"+u).find(".l2").text(cont);
                }
                if(eval(start)!==eval(0)){
                    $(".up").css("display","block");
                }
                if(eval(start)===eval(0)){
                    $(".up").css("display","none");
                }
            }})
        $('body,html').animate({scrollTop:1600},1000);
    });
    $(".down").click(function () {
        start+=8;
        $.ajax({url:"http://localhost:8080/commit/ajax",
            data:{"start":start},
            success:function (data) //每次返回8个对象
            {
                if(eval(data.length)==eval(8)){
                    $(".down").css("display","none");
                }

                $(".commit").find("span").text("");

                for(d in data){
                    var index=data[d]["id"]
                    var i=parseInt(d)+parseInt(start)+parseInt(1);
                    var user=data[d]["ip"];
                    var cont=data[d]["cont"];
                    var Time=data[d]["time"];
                    var time=formatDate(Time);
                    var title="第"+i+"条评论,用户:"+user+",时间:"+time;
                    var t=parseInt(d)+parseInt(1);
                    var u="u"+t;
                    $("#"+u).find(".l1").text(title);
                    $("#"+u).find(".l2").text(cont);

                }
                $('body,html').animate({scrollTop:1600},1000);

                if(eval(start)!==eval(0)){
                    $(".up").css("display","block");
                }
                if(eval(start)===eval(0)){
                    $(".up").css("display","none");
                }
            }})
    })
});
