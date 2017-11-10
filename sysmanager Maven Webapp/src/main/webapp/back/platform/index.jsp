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
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/IconExtension.css">
    <script src="/sysmanager/back/easyui/js/jquery.min.js"></script>
    <script src="/sysmanager/back/easyui/js/jquery.easyui.min.js"></script>
    <script src="/sysmanager/back/easyui/js/form.validator.rules.js"></script>
    <script src="/sysmanager/back/easyui/js/easyui-lang-zh_CN.js"></script>
    <script src="/sysmanager/back/echar/echarts.js"></script>
</head>

<body>
<div class="content">
    <!--左边 开始-->
    <div class="contentLeft">
    
    
        <div class="logo"><img src="images/common/logo.png"></div>
        
        <ul class="downMenu" id="downMenu">
            <li>
                <div class="menuLink"><i class="business"></i><span>业务仪表盘</span></div>
                <ul class="twoLevelmenu">
                    <li ><a onclick="changeIframe('../main/compare/oilCompare.jsp')">油品同比环比</a></li>
                    <li><a onclick="changeIframe('../main/compare/shopCompare.jsp')">便利店数据同比环比</a></li>
                    <li><a onclick="changeIframe('../main/compare/rateCompare.jsp')">便利店开单同比环比增长率</a></li>
                </ul>
            </li>
            <li>
                <div class="menuLink"><i class="sale"></i><span>销售报表</span></div>
                <ul class="twoLevelmenu">
                    <li><a onclick="changeIframe('../main/base/hhtipt.jsp')">前庭室内支付对比</a></li>
                    <li><a onclick="changeIframe('../main/base/oilzoushi.jsp')">分油品销售量</a></li>
                    <li><a onclick="changeIframe('../main/base/payment.jsp')">总支付方式对比</a></li>
                    <li><a onclick="changeIframe('../main/base/price.jsp')">各油品价格走势</a></li>
                    <li><a onclick="changeIframe('../main/base/productivity.jsp')">劳动生产率</a></li>
                    <li><a onclick="changeIframe('../main/base/target.jsp')">油品销量完成率</a></li>
                    <li><a onclick="changeIframe('../main/base/zhanbi.jsp')">各油品销量占比</a></li>
                    <li><a onclick="changeIframe('../main/base/zoushi.jsp')">油站营业额</a></li>
                    <li><a onclick="changeIframe('../main/shop/byNotOil.jsp')">便利店分品类销售量</a></li>
                    <li><a onclick="changeIframe('../main/shop/lube.jsp')">润滑油销售额</a></li>
                    <li><a onclick="changeIframe('../main/shop/notOil.jsp')">便利店销售额</a></li>
                    <li><a onclick="changeIframe('../main/shop/rate.jsp')">便利店开单率</a></li>
                    <li><a onclick="changeIframe('../main/shop/top10.jsp')">便利店Top榜单</a></li>
                </ul>
            </li>
            <li>
                <div class="menuLink"><i class="member"></i><span>会员报表</span></div>
                <ul class="twoLevelmenu">
                    <li><a onclick="changeIframe('../main/vip/addVip.jsp')">全网日新增会员</a></li>
                    <li><a onclick="changeIframe('../main/vip/comeFrom.jsp')">会员来源</a></li>
                    <li><a onclick="changeIframe('../main/vip/compare.jsp')">活跃会员消费频次及占比</a></li>
                    <li><a onclick="changeIframe('../main/vip/coupon.jsp')">优惠券</a></li>
                    <li><a onclick="changeIframe('../main/vip/couponRate.jsp')">优惠券余量及占比</a></li>
                    <li><a onclick="changeIframe('../main/vip/credit.jsp')">积分发放兑换数量</a></li>
                    <li><a onclick="changeIframe('../main/vip/creditRate.jsp')">积分余量占比</a></li>
                    <li><a onclick="changeIframe('../main/vip/drain.jsp')">流失会员人数及占比</a></li>
                    <li><a onclick="changeIframe('../main/vip/evals.jsp')">评价射线图</a></li>
                    <li><a onclick="changeIframe('../main/vip/evaluation.jsp')">评价平均分趋势</a></li>
                    <li><a onclick="changeIframe('../main/vip/evaluationDistribution.jsp')">评价平均分分布</a></li>
                    <li><a onclick="changeIframe('../main/vip/firstExpend.jsp')">首次消费间隔</a></li>
                    <li><a onclick="changeIframe('../main/vip/funnel.jsp')">会员漏斗图</a></li>
                    <li><a onclick="changeIframe('../main/vip/lastDeal.jsp')">各阶段流失会员（距最后一次消费时间）</a></li>
                    <li><a onclick="changeIframe('../main/vip/recharge.jsp')">充值金额走势</a></li>
                    <li><a onclick="changeIframe('../main/vip/refuel.jsp')">会员加油区间</a></li>
                    <li><a onclick="changeIframe('../main/vip/vipGap.jsp')">两次消费时间间隔</a></li>
                    <li><a onclick="changeIframe('../main/vip/vipRecharge.jsp')">消费散点图</a></li>
                    <li><a onclick="changeIframe('../main/vip/VipRechargeMonth.jsp')">会员充值月度</a></li>
                    <li><a onclick="changeIframe('../main/vip/vipThirtyRate.jsp')">日招募会员30天转化率</a></li>
                </ul>
            </li>
            <li>
                <div class="menuLink"><i class="memberLabel"></i><span>会员标签管理</span></div>
                <ul class="twoLevelmenu">
                    <li><a onclick="changeIframe('http://www.baidu.com')">业务仪表盘一</a></li>
                </ul>
            </li>
            <li>
                <div class="menuLink"><i class="system"></i><span>系统管理</span></div>
                <ul class="twoLevelmenu">
                    <li><a onclick="changeIframe('../main/sys/category/showAll.jsp')">油站类别管理</a></li>
                    <li><a onclick="changeIframe('../main/sys/pos/showAll.jsp')">数据传输接口IP</a></li>
                    <li><a onclick="changeIframe('../main/sys/station/showAll.jsp')">油站管理</a></li>
                    <li><a onclick="changeIframe('../main/sys/tag/showAll.jsp')">油站标签管理</a></li>
                </ul>
            </li>
            <li>
                <div class="menuLink"><i class="account"></i><span>账户管理</span></div>
                <ul class="twoLevelmenu">
                    <li><a onclick="changeIframe('http://www.baidu.com')">业务仪表盘一</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <!--左边 结束-->
    <!--右边 开始-->
    <div class="contentRight">
    
       <div class="personnel" style="folat:right">
       <%-- <div style="float: right">
	       <h5>当前用户：<shiro:principal/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <a href="">修改密码</a>
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	<a href="/sysmanager/admin/logout">退出</a></h5>
       </div> --%>
       <div style="float: left">
    	<h5>当前用户：<shiro:principal/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	<a href="/sysmanager/admin/logout">退出</a></h5>
    	</div>
       <a   class="personnelIcon" onclick="changeIframe('../main/admin/updateSelf.jsp')"></a></div>
       
       
       
       <div class="rightMain" style="border:0;" id="rightMain">
       
           <iframe src="2.html" id="Iframe"></iframe>
       </div>
    </div>
    <!--右边 结束-->
</div>
</body>
<script type="text/javascript" src="js/libs/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	function changeIframe(src) {
		$("#Iframe").prop("src",src);
	}
    $(function() {
        var Accordion = function(el, multiple) {
            this.el = el || {};
            this.multiple = multiple || false;
            var links = this.el.find('.menuLink');
            links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown);
        };

        Accordion.prototype.dropdown = function(e) {
            var $el = e.data.el;
                $this = $(this),
                $next = $this.next();

            $next.slideToggle();
            $this.parent().toggleClass('Cur');

            if (!e.data.multiple) {
                $el.find('.twoLevelmenu').not($next).slideUp().parent().removeClass('Cur');
            };
        };   

        var accordion = new Accordion($('#downMenu'), false);
    });
    $('.twoLevelmenu li').bind('click', function(){
        $(this).addClass('downCur').siblings().removeClass('downCur');
    });
</script>
</html>
