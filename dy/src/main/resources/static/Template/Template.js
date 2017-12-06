var left=0;
$(document).ready(function () {
    $(".Navigation").animate({top:"0%"},1500);
    $("#right").click(function () {
        switch(left) {
            case -3:
                $(".photo").stop(false, true).animate({ left:'+=16%',});

                left+=1;
                break;
            case -2:
                $(".photo").stop(false, true).animate({ left:'+=16%',});

                left+=1;
                break;
            case -1:
                $(".photo").stop(false, true).animate({ left:'+=16%',});

                left+=1;
                break;
        }
    })
});
$(document).ready(function () {
    $("#left").click(function () {
        switch(left){
            case -2:
                $(".photo").stop(false, true).animate({ left:'-=16%',});

                left-=1;
                break;
            case 0:
                $(".photo").stop(false, true).animate({ left:'-=16%',});

                left-=1;
                break;
            case -1:
                $(".photo").stop(false, true).animate({left:'-=16%',});

                left-=1;
                break;
        }
    })
});
$(document).ready(function () {

});
$(document).ready(function () {
    $(".Navigation").hover(function () {
        $(".click2").css('display','block');
    }, function () {
        $(".click2").css('display','none')});

    $(".photo").hover(function () {
        $(this).find("img").stop(false, true).animate({ top:'0px',height:"170px",width:"170px"})
    },(function () {
        $(this).find("img").stop(false, true).animate({ top:'+305px',height:"100%",width:"100%"})
    }));

    $(".photo").hover(function () {
        $(this).find(".p1").stop(false, true).animate({ height:"170px",width:"170px"})
    },function () {
        $(this).find(".p1").stop(false, true).animate({ height:"150px",width:"150px"})});
});
var b=0;
$(document).ready(function () {
    $(window).scroll(function () {
        if($(this).scrollTop()>200&&b===0 ){
            $(".back").queue(function () {
                $(this).rotate({angle: 0,animateTo: 0});
                $(this).dequeue();
            });
            $(".back").animate({top:'80%'},4000);
            b=1;
        };
        if($(this).scrollTop()===0&& b===1){
            $(".back").queue(function () {
                $(this).rotate({angle: 0,animateTo: 180,duration:2000});
                $(this).dequeue();
            });
            $(".back").animate({top:'101%'},4000);
            b=0;
        };
    });
    $(".back").click(function () {
        $('body,html').animate({scrollTop:0},1000);
    })
});
setInterval(function() {
    clock();
}, 1000);
function clock() {

    var time = new Date();
    var hours = time.getHours();
    var minutes = time.getMinutes();
    var seconds = time.getSeconds();

    if ($('.h').text() != ((hours < 10 ? "0" : "") + hours)){
        $('.h').text((hours < 10 ? "0" : "") + hours);
        shake($('.h'));
    }

    if ($('.m').text() != ((minutes < 10 ? "0" : "") + minutes)) {
        $('.m').text((minutes < 10 ? "0" : "") + minutes);
        shake($('.m'));
    }

    if ($('.s').text() != ((seconds < 10 ? "0" : "") + seconds)) {
        $('.s').text((seconds < 10 ? "0" : "") + seconds);
        shake($('.s'));
    }
}

$(document).ready(function(){
    clock();
});

function shake(t) {
    t.addClass('shake-constant');
    setTimeout(function() {
    t.removeClass('shake-constant');
    }, 470)
}



var i=0;
$(document).ready(function () {
    $("#a1").click(function () {
        $("#h2").stop(false,true).animate({left:'45px'});
        $(".box2").stop(false,true).animate({height:'310px'});
        $("#cont2").stop(false,true).hide();
        $("#cont1").stop(false,true).show();
        $(".in").val("");
    });
    $("#a2").click(function () {
        $("#h2").stop(false,true).animate({left:'180px'});
        $(".in").val("");
        $(".box2").stop(false,true).animate({height:'370px'},500,function () {
            $("#cont1").stop(false,true).hide();
            $("#cont2").stop(false,true).show();
        });
    });
    $(".login_img").click(function () {
        switch (i) {
            case 1:
                $(".box2").stop(false,true).animate({top: "-10%"});
                i = 0;
                break;
            case 0:
                $(".box2").stop(false,true).animate({top: "6%"});
                i= 1;
                break;
        }
    })
});
$(document).ready(function () {
   $("#photo1").click(function () {
       window.location.href="http://localhost:8080/"
   })
    $("#photo2").click(function () {
        window.location.href="http://localhost:8080/resume"
    })
    $("#photo3").click(function () {
        window.location.href="http://localhost:8080/project"
    })
    $("#photo4").click(function () {
        window.location.href="http://localhost:8080/build"
    })
    $("#photo5").click(function () {
        window.location.href="http://localhost:8080/commit"
    })
});
var setTime;
var time=60;
$(function () {
    $("#fasong").click(function () {
        $("#fasong").attr('disabled', true);
        setTime=setInterval(function(){

            if(time<=0){
                clearInterval(setTime);
                $("#fasong").attr("disabled",false);
                $("#fasong").val("发送");
                time=60;
                return;
            }
            console.log(time);
            time--;
            $("#fasong").val(time);
        },1000);
        var email=$("#email").val();
        $.ajax({url:"http://localhost:8080/email/ajax",
            data:{"email":email},
            success:function (data) //每次返回8个对象
            {
                alert(data)
            }
        })
    })

});

