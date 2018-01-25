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
            <shiro:hasPermission name="service">
                <a href="../main/dashboard/service.jsp" target="contentFrame" class="headline"><i class="navIcon icon_1"></i><span>业务仪表盘</span></a>
            </shiro:hasPermission>
                <ul>
                </ul>
            </li>
            <li class="menuItem">
            <shiro:hasPermission name="sales">
                <a href="javascript:void(0);"  class="headline"><i class="navIcon icon_2"></i><span>销售报表</span></a>
            </shiro:hasPermission>
                <ul>
                    <shiro:hasPermission name="oil"><li><a href="../main/base.jsp" target="contentFrame">燃油销售报表</a></li></shiro:hasPermission>
                    <shiro:hasPermission name="shop"><li><a href="../main/shop.jsp" target="contentFrame">便利店销售报表</a></li></shiro:hasPermission> 
                    <shiro:hasPermission name="mop"><li><a href="../main/mop.jsp" target="contentFrame">支付方式报表</a></li></shiro:hasPermission>
                    <shiro:hasPermission name="hose"><li><a href="../main/hose/gasStation.html" target="contentFrame">油枪效率</a></li></shiro:hasPermission>
                    <shiro:hasPermission name="weather"><li><a href="../main/base/weather.html" target="contentFrame">辅助数据</a></li></shiro:hasPermission>
                </ul>
            </li>
            <li class="menuItem">
               <shiro:hasPermission name="vip"><a href="javascript:void(0);" class="headline"><i class="navIcon icon_3"></i><span>会员报表</span></a></shiro:hasPermission> 
                <ul>
                    <shiro:hasPermission name="addVip"><li><a href="../main/vip/addVip.jsp" target="contentFrame">会员招募</a></li></shiro:hasPermission>
                    <shiro:hasPermission name="vipKeep"><li><a href="../main/vip/vipThirtyRate.jsp" target="contentFrame">会员留存</a></li></shiro:hasPermission>
                    <shiro:hasPermission name="vipConsume"><li><a href="../main/vip/firstExpend.jsp" target="contentFrame">会员消费周期</a></li></shiro:hasPermission>
                    <shiro:hasPermission name="vipCredit"><li><a href="../main/vip/credit.jsp" target="contentFrame">会员积分</a></li></shiro:hasPermission>
                    <shiro:hasPermission name="vipCoupon"><li><a href="../main/vip/coupon.jsp" target="contentFrame">会员优惠券</a></li></shiro:hasPermission>
                    <shiro:hasPermission name="vipRecharge"><li><a href="../main/vip/recharge.jsp" target="contentFrame">会员充值</a></li></shiro:hasPermission>
                    <shiro:hasPermission name="vipEvaluation"><li><a href="../main/vip/baseEvaluation.jsp" target="contentFrame">口碑分析</a></li></shiro:hasPermission>
                </ul>
            </li>
            <li class="menuItem">
                <shiro:hasPermission name="tag"><a href="javascript:void(0);"  class="headline"><i class="navIcon icon_4"></i><span>会员标签管理</span></a></shiro:hasPermission>
                <ul>
                    <shiro:hasPermission name="vipTag"><li><a  href="../main/tag/tag.jsp" target="contentFrame" >按照标签查询</a></li></shiro:hasPermission>
                    <shiro:hasPermission name="vipGroup"><li><a  href="../main/tag/group.jsp" target="contentFrame" >按照分组查询</a></li></shiro:hasPermission>
                    <shiro:hasPermission name="vipDescription"><li><a  href="javascript:void(0);" target="contentFrame" >标签释义</a></li></shiro:hasPermission>
                    <shiro:hasPermission name="tagActive"><li><a  href="../main/tag/active/showAll.jsp" target="contentFrame" >活动标签</a></li></shiro:hasPermission>
                </ul>
            </li>
        </ul>
        <!--底部导航-->
        <ul class="footermenu">
            <shiro:hasPermission name="system"><li><a href="../main/sys/system.html" target="contentFrame"><i class="system"></i><span>系统管理</span></a></li></shiro:hasPermission>
            <li><a href="../main/admin/updateSelf.jsp" target="contentFrame"><i class="account"></i><span>账户管理</span></a></li>
            <li><a onclick="LogOut()"><i class="signOut"></i><span>退出登录</span></a></li>
        </ul>
        <!--收缩箭头-->
        <div class="shrink" id="mini"><i class="open" ></i></div>
    </div>
   </div>

</body>
<script type="text/javascript">
	function LogOut() {
		top.location.href="/sysmanager/admin/logout";
	}
</script>
<script type="text/javascript" src="js/libs/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript">navLeft();</script>
</html>
