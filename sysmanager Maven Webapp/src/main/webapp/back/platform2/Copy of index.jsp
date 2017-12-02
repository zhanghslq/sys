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
    <link rel="stylesheet" href="css/common.css"/>
    <link rel="stylesheet" href="css/index.css"/>
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
                <a href="javascript:void(0);" onclick="changeIframe('../main/base.jsp')" class="headline"><i class="navIcon icon_1"></i><span>业务仪表盘</span></a>
                <ul>
                </ul>
            </li>
            <li class="menuItem">
                <a href="javascript:void(0);"  class="headline"><i class="navIcon icon_2"></i><span>销售报表</span></a>
                <ul>
                    <li><a onclick="changeIframe('../main/shop/search.jsp')">按照商品编码查询</a></li>
                    <li><a onclick="changeIframe('../main/shop/rate.jsp')">便利店开单率</a></li>
                    <li><a onclick="changeIframe('../main/oilDetail.jsp')">燃油详细</a></li>
                    <li><a onclick="changeIframe('../main/base/payment.jsp')">支付详细</a></li>
                    <li><a onclick="changeIframe('../main/base/target.jsp')">目标达成情况</a></li>
                    <li><a onclick="changeIframe('../main/shop/byNotOil.jsp')">便利店分品类销售量</a></li>
                </ul>
            </li>
            <li class="menuItem">
                <a href="javascript:void(0);" class="headline"><i class="navIcon icon_3"></i><span>会员报表</span></a>
                <ul>
                    <li><a onclick="changeIframe('../main/vip/addVip.jsp')">会员招募</a></li>
                    <li><a onclick="changeIframe('../main/vip/vipThirtyRate.jsp')">会员留存</a></li>
                    <li><a onclick="changeIframe('../main/vip/firstExpend.jsp')">会员消费周期</a></li>
                    <li><a onclick="changeIframe('../main/vip/credit.jsp')">会员积分</a></li>
                    <li><a onclick="changeIframe('../main/vip/coupon.jsp')">会员优惠券</a></li>
                    <li><a onclick="changeIframe('../main/vip/recharge.jsp')">会员充值</a></li>
                    <li><a onclick="changeIframe('../main/vip/evaluation.jsp')">口碑分析</a></li>
                </ul>
            </li>
            <li class="menuItem">
                <a href="javascript:void(0);" class="headline"><i class="navIcon icon_4"></i><span>会员标签管理</span></a>
                <ul>
                    <li><a href="javascript:void(0);">报表一</a></li>
                </ul>
            </li>
        </ul>
        <!--底部导航-->
        <ul class="footermenu">
            <li><a onclick="changeIframe('../main/sys/system.html')"><i class="system"></i><span>系统管理</span></a></li>
            <li><a onclick="changeIframe('../main/admin/updateSelf.jsp')"><i class="account"></i><span>账户管理</span></a></li>
            <li><a href="/sysmanager/admin/logout"><i class="signOut"></i><span>退出登录</span></a></li>
        </ul>
        <!--收缩箭头-->
        <div class="shrink" id="mini"><i class="open"></i></div>
    </div>
    <!--左边 结束-->
    <!--右边 开始-->
    <div class="contentRight">
      <iframe style="min-height: 1000px"  src="2.html" class="introduceNav"  id="iframepage" frameborder="0"  scrolling="auto"></iframe>
    </div>
    <!--右边 结束-->
</div>
</body>
<script>
function changeIframe(src) {//改变中间内容区域地址的方法
	$("#iframepage").prop("src",src);
}
</script>
<script type="text/javascript" src="js/libs/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
<script>
$(function(){
    $(window).resize(function(){
      navLeft();  
    });
    //iframe高度
    $(window).load(function(){
        var cH = $("#iframepage").contents().find("#contentRightHeight").height();
        $("#iframepage").height(cH).attr("height",cH);
    }); 
    $("#iframepage").attr("src","rightContent.html?random="+ Math.floor(Math.random()*100000));
});
</script>
</html>
