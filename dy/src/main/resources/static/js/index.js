var left=0;
$(function () {
    $(".layout_1").css({top:'20%'});
    $("#banner2").css({left:'-1600px',top:'5%'});
    $("#banner3").css({left:'1600px',top:'5%'});
    $(".Navigation").animate({top:"0%"},1500);
    $(window).scroll(function () {
        if($(this).scrollTop()>=650 ){
            $("#banner1").animate({top:'5%'});
        }
        if($(this).scrollTop()>=900 ){
            $("#banner2").animate({left:'0%'});
        }
        if($(this).scrollTop()>=1400 ){
            $("#banner3").animate({left:'0%'});
        }
        if($(this).scrollTop()>=1700 ){
            $("#banner4").animate({top:'5%'});
        }
        if($(this).scrollTop()>=2000 ){
            $("#banner5").animate({top:'5%'});
        }

    })
    });




