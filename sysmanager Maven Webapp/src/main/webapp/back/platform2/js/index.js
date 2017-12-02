/*左侧效果*/
function navLeft(){
  //nav 固定高度
	var bodyHeight=$(".contentLeft").height(),
		listHeight=$(".logoMain").height()+$(".footermenu").height()+$(".shrink").height();
	$(".downMenu").css("height",bodyHeight-listHeight-30);
    // nav收缩展开
    $('.menuItem>a').on('click',function(){
        if (!$('.contentLeft').hasClass('menuMini')) {
            if ($(this).next().css('display') == "none") {
                //展开未展开
                $('.menuItem').children('ul').slideUp(300);
                $(this).next('ul').slideDown(300);
                $(this).parent('li').addClass('Cur').siblings('li').removeClass('Cur');
            }else{
                //收缩已展开
                $(this).next('ul').slideUp(300);
                $('.menuItem.Cur').removeClass('Cur');
            }
        }
        $('.menuItem ul li').bind('click', function(){
            $(this).addClass('downCur').siblings().removeClass('downCur');
        });
    });
    //nav-mini切换
    $('#mini').on('click',function(){
        if (!$('.contentLeft').hasClass('menuMini')) {
                $('.menuItem a span,.logoMain .logoText,.footermenu a span').hide();
            $('.contentLeft').animate({width:"70px"},100,function(){
                $(".shrink i").addClass('narrow');
                $('.menuItem').children('ul').hide();
                $('.contentLeft').addClass('menuMini');
                $('.content .contentRight').css("paddingLeft","100px")
            })
        }else{
              $('.contentLeft').animate({width:"257px"},100,function(){
                $(".shrink i").removeClass('narrow');
                $('.contentLeft').removeClass('menuMini');
                $('.menuItem a span,.logoMain .logoText,.footermenu a span').show();
                $('.content .contentRight').css("paddingLeft","290px")
              });
        }
    });
    //底部按钮
    $(".footermenu li").on("click",function(){
        $('.downMenu li').removeClass('Cur');
        $('.downMenu .menuItem ul').hide();
        $(this).addClass('fotCur').siblings().removeClass('fotCur');
    });
    $(".downMenu li").on("click",function(){
        $(".footermenu li").removeClass('fotCur');
    });
}
/*右侧效果*/
function downTab(){
  $(".tabNav li").each(function(i){
    $(this).on("click",function(){
       $(this).addClass("on").siblings().removeClass("on");$(".rightDownMain .downDetails:eq("+i+")").show().siblings().hide();
    })  
  });
}
//右侧下拉
function rightDown(){
    $(".selemenu").click(function(){
        $(this).next().slideToggle();
        $(this).parents().siblings().find(".seleContent").slideUp();
    });
    $(".downCont .downNav a").each(function(i){
        $(this).on("click",function(){
           $(this).addClass("titleCur").siblings().removeClass("titleCur");$(".downContInfo ul:eq("+i+")").show().siblings().hide();
        })  
    });
    $(".crowdNav a").on("click",function(){
        $(".crowd").slideUp();
    });
    $(function(){
        $(document).not($(".selectbox")).click(function(){
            $(".seleContent").slideUp();
         })
         $(".selectbox").click(function(event){
            event.stopPropagation();
        });
        $(".downOperation .determine").click(function(){
            $(".seleContent").slideUp();
         });
         $(".downOperation .cancel").click(function(){
            $(".seleContent").slideUp();
        });
    });
}

