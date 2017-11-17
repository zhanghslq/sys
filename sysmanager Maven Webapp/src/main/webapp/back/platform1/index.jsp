<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" import="java.util.*" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>大数据分析平台</title>
    <link rel="stylesheet" href="css/common.css" />
    <link rel="stylesheet" href="css/index.css" />
</head>

<body>
<div class="content">
    <!--左边 开始-->
    <div class="contentLeft">
        <!--logo-->
        <div class="logoMain">
            <a href="javascript:void(0);" class="logo"></a>
            <div class="logoText">
                <p>北京壳牌</p>
                <p>大数据分析平台</p>
            </div>
        </div>
        <!--中间导航-->
        <ul class="downMenu" id="downMenu">
            <li class="menuItem">
                <a href="javascript:void(0);" class="headline"><i class="navIcon icon_1"></i><span>业务仪表盘</span></a>
                <ul>
                    <!--  <li ><a onclick="changeIframe('../main/compare/oilCompare.jsp')" style="cursor:pointer">油品同比环比</a></li>
                    <li><a onclick="changeIframe('../main/compare/shopCompare.jsp')">便利店数据同比环比</a></li>
                    <li><a onclick="changeIframe('../main/compare/rateCompare.jsp')">便利店开单同比环比增长率</a></li> -->
                </ul>
            </li>
            <li class="menuItem">
                <a href="javascript:void(0);" class="headline"><i class="navIcon icon_2"></i><span>销售报表</span></a>
                <ul>
                    <li><a onclick="changeIframe('../main/base/zoushi.jsp')">油品营业额</a></li>
                    <li><a onclick="changeIframe('../main/shop/notOil.jsp')">便利店营业额</a></li>
                    <li><a onclick="changeIframe('../main/shop/lube.jsp')">润滑油销售额</a></li>
                    <li><a onclick="changeIframe('../main/shop/rate.jsp')">便利店开单率</a></li>
                    <li><a onclick="changeIframe('../main/base/oilzoushi.jsp')">分油品销售量</a></li>
                    <li><a onclick="changeIframe('../main/base/price.jsp')">各标号油价</a></li>
                    <li><a onclick="changeIframe('../main/base/payment.jsp')">总支付方式对比</a></li>
                    <li><a onclick="changeIframe('../main/base/hhtipt.jsp')">前庭室内支付对比</a></li>
                    <li><a onclick="changeIframe('../main/base/productivity.jsp')">劳动生产率</a></li>
                    <li><a onclick="changeIframe('../main/base/target.jsp')">目标达成情况</a></li>
                    <li><a onclick="changeIframe('../main/base/zhanbi.jsp')">各油品销量占比</a></li>
                    <li><a onclick="changeIframe('../main/shop/byNotOil.jsp')">便利店分品类销售量</a></li>
                    <li><a onclick="changeIframe('../main/shop/top10.jsp')">便利店Top榜单</a></li>
                </ul>
            </li>
            <li class="menuItem">
                <a href="javascript:void(0);" class="headline"><i class="navIcon icon_3"></i><span>会员报表</span></a>
                <ul>
                    <li><a onclick="changeIframe('../main/vip/addVip.jsp')">全网日新增会员</a></li>
                    <li><a onclick="changeIframe('../main/vip/comeFrom.jsp')">各渠道新招募会员数量占比</a></li>
                    <li><a onclick="changeIframe('../main/vip/vipThirtyRate.jsp')">新招募会员30天转化率</a></li>
                    <li><a onclick="changeIframe('../main/vip/compare.jsp')">活跃会员消费频次及占比</a></li>
                    <li><a onclick="changeIframe('../main/vip/drain.jsp')">流失会员人数及占比</a></li>
                    <li><a onclick="changeIframe('../main/vip/funnel.jsp')">会员漏斗图</a></li>
                    <li><a onclick="changeIframe('../main/vip/firstExpend.jsp')">注册到首次消费时间间隔</a></li>
                    <li><a onclick="changeIframe('../main/vip/vipGap.jsp')">每两次消费时间间隔分布</a></li>
                    <li><a onclick="changeIframe('../main/vip/vipDealMonth.jsp')">会员消费次数及金额分布</a></li>
                    <li><a onclick="changeIframe('../main/vip/lastDeal.jsp')">各阶段流失会员</a></li>
                    <li><a onclick="changeIframe('../main/vip/credit.jsp')">积分发放兑换数量</a></li>
                    <li><a onclick="changeIframe('../main/vip/creditRate.jsp')">当前积分余量及占比</a></li>
                    <li><a onclick="changeIframe('../main/vip/coupon.jsp')">优惠券发放兑换金额</a></li>
                    <li><a onclick="changeIframe('../main/vip/couponRate.jsp')">优惠券余量及占比</a></li>
                    <li><a onclick="changeIframe('../main/vip/recharge.jsp')">充值金额走势</a></li>
                    <li><a onclick="changeIframe('../main/vip/VipRechargeMonth.jsp')">会员充值月度</a></li>
                    <li><a onclick="changeIframe('../main/vip/vipRecharge.jsp')">会员充值次数及金额分布</a></li>
                    
                    <li><a onclick="changeIframe('../main/vip/evals.jsp')">评价射线图</a></li>
                    <li><a onclick="changeIframe('../main/vip/evaluation.jsp')">评价平均分趋势</a></li>
                    <li><a onclick="changeIframe('../main/vip/evaluationDistribution.jsp')">评价平均分分布</a></li>
                    <!-- <li><a onclick="changeIframe('../main/vip/refuel.jsp')">会员加油区间</a></li> -->
                </ul>
            </li>
            <li class="menuItem">
                <a href="javascript:void(0);" class="headline"><i class="navIcon icon_4"></i><span>会员标签管理</span></a>
                <ul>
                   <!--  <li><a href="javascript:void(0);">报表一</a></li>
                    <li><a href="javascript:void(0);">报表二</a></li>
                    <li><a href="javascript:void(0);">报表三</a></li>
                    <li><a href="javascript:void(0);">报表四</a></li> -->
                </ul>
            </li>
        </ul>
        <!--底部导航-->
        <ul class="footermenu">
            <li><a onclick="changeIframe('../main/sys/station/showAll.jsp')"><i class="system"></i><span>系统管理</span></a></li>
            <li><a href="#" onclick="changeIframe('../main/admin/updateSelf.jsp')"><i class="account"></i><span>账户管理</span></a></li>
            <li><a href="/sysmanager/admin/logout"><i class="signOut"></i><span>退出登录</span></a></li>
        </ul>
        <!--收缩箭头-->
        <div class="shrink" id="mini"><i class="open"></i></div>
    </div>
    <!--左边 结束-->
    <!--右边 开始-->
    <div class="contentRight">
       <div class="rightMain" style="border:0;" id="rightMain">
           <iframe src="2.html" id="Iframe"></iframe>
       </div>
    </div>
    <!--右边 结束-->
</div>
</body>
<script type="text/javascript" src="js/libs/jquery-1.11.3.min.js"></script>
<script type="text/javascript">

function changeIframe(src) {//改变中间内容区域地址的方法
	$("#Iframe").prop("src",src);
}
$(function(){
    //nav 固定高度
    var bodyHeight=$(document).height(),listHeight=$(".logoMain").height()+$(".footermenu").height()+$(".shrink").height();
    $(".downMenu").css("height",bodyHeight-listHeight-30)
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
                $('.content .contentRight').css("paddingLeft","120px")
            })
        }else{
              $('.contentLeft').animate({width:"257px"},100,function(){
                $(".shrink i").removeClass('narrow');
                $('.contentLeft').removeClass('menuMini');
                $('.menuItem a span,.logoMain .logoText,.footermenu a span').show();
                $('.content .contentRight').css("paddingLeft","307px")
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
});

</script>
</html>
