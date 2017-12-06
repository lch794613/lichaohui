$(function () {
    var project;
    var conts;
    $.ajax({url:"http://localhost:8080/json/show.json",success:function (data) {
         project=data.project;
         conts=data.cont;
        $(".display").children("h1").text(project);
        var n=0;
        for(cont in conts ){
            $(".display").children("p").eq(n).text(conts[cont]);
            n+=1;
        }

    }}),
    $(".project").click(function () {
        var num=$(this).attr("id");
        $.ajax({url:"http://localhost:8080/json/"+num+".json",success:function (data) {
             project=data.project;
             conts=data.cont;
            var imges=data.images;
            $(".display").children("h1").text(project);
            $(".display").children("p").slice(5).remove();
            var c=0;
            for(cont in conts ){

                $(".display").children("p").eq(c).text(conts[cont]);
                c+=1;
            }
            var i=0;
            $(".display").children("img").css('display','block');
            for(img in imges ){
                $(".display").children("img").eq(i).attr("src","images/"+imges[img]);
                i+=1;

            }

            $('body,html').animate({scrollTop:1950},1000);

        }})
    })



    });


