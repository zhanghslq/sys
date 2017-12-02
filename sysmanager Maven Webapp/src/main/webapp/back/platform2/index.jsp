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
                <a href="../main/base.jsp" target="contentFrame" class="headline"><i class="navIcon icon_1"></i><span>业务仪表盘</span></a>
                <ul>
                </ul>
            </li>
            <li class="menuItem">
                <a href="javascript:void(0);"  class="headline"><i class="navIcon icon_2"></i><span>销售报表</span></a>
                <ul>
                    <li><a href="../main/shop/search.jsp" target="contentFrame">按照商品编码查询</a></li>
                    <li><a href="../main/shop/rate.jsp" target="contentFrame">便利店开单率</a></li>
                    <li><a href="../main/oilDetail.jsp" target="contentFrame">燃油详细</a></li>
                    <li><a href="../main/base/payment.jsp" target="contentFrame">支付详细</a></li>
                    <li><a href="../main/base/target.jsp" target="contentFrame">目标达成情况</a></li>
                    <li><a href="../main/shop/byNotOil.jsp" target="contentFrame">便利店分品类销售量</a></li>
                </ul>
            </li>
            <li class="menuItem">
                <a href="javascript:void(0);" class="headline"><i class="navIcon icon_3"></i><span>会员报表</span></a>
                <ul>
                    <li><a href="../main/vip/addVip.jsp" target="contentFrame">会员招募</a></li>
                    <li><a href="../main/vip/vipThirtyRate.jsp" target="contentFrame">会员留存</a></li>
                    <li><a href="../main/vip/firstExpend.jsp" target="contentFrame">会员消费周期</a></li>
                    <li><a href="../main/vip/credit.jsp" target="contentFrame">会员积分</a></li>
                    <li><a href="../main/vip/coupon.jsp" target="contentFrame">会员优惠券</a></li>
                    <li><a href="../main/vip/recharge.jsp" target="contentFrame">会员充值</a></li>
                    <li><a href="../main/vip/evaluation.jsp" target="contentFrame">口碑分析</a></li>
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
            <li><a href="../main/sys/system.html" target="contentFrame"><i class="system"></i><span>系统管理</span></a></li>
            <li><a href="../main/admin/updateSelf.jsp" target="contentFrame"><i class="account"></i><span>账户管理</span></a></li>
            <li><a href="/sysmanager/admin/logout"><i class="signOut"></i><span>退出登录</span></a></li>
        </ul>
        <!--收缩箭头-->
        <div class="shrink" id="mini"><i class="open"></i></div>
    </div>
   </div>

</body>
<script type="text/javascript" src="js/libs/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</html>
