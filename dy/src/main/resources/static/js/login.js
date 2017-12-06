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

