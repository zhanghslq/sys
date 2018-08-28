package com.yb.controller;

import com.yb.entity.DataPack;
import com.yb.entity.DouPack;
import com.yb.entity.StationPack;
import com.yb.service.MapDashboardService;
import com.yb.service.StationService;
import com.yb.util.ArryToListUtil;
import com.yb.util.DoubleFormatUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("mapDashboard")
@Scope("prototype")
public class MapDashboardController {
    @Resource
    private MapDashboardService mapDashboardService;
    @Resource
    private StationService stationService;
    /**
     * 整体的先放一起
     * Top榜单不区分油站
     * 然后单个油站的放一起
     */
    @RequestMapping("queryByArea")
    @ResponseBody
    public Map<String,Object> queryByArea(@RequestParam(required=false,value="idss[]")String[] idss){
        DecimalFormat df0 = new DecimalFormat("#,###");
        List<String> ids = ArryToListUtil.format(idss);
        System.out.println(ids);
        String vip="vip";
        String all="all";
        String area="BJSHELL";

        StationPack stationPack = stationService.queryById(ids.get(0));
        if("承德".equals(stationPack.getCity())){
            area="CDSHELL";
        }

        DouPack douPack=new DouPack("",0.0,0.0);
        douPack = mapDashboardService.queryOilTradeNumberAndAmount(ids, all);
        DouPack douPack1=new DouPack("",0.0,0.0);
        douPack1 = mapDashboardService.queryOilTradeNumberAndAmount(ids, vip);
        DouPack douPack2=new DouPack("",0.0,0.0);
        douPack2 = mapDashboardService.queryNotOilTradeNumberAndAmount(ids, all);
        DouPack douPack3=new DouPack("",0.0,0.0);
        douPack3 = mapDashboardService.queryNotOilTradeNumberAndAmount(ids, vip);
        //会员数量 integer
        Integer integer=0;
        integer = mapDashboardService.queryVipAmountByArea(area);
        List<DataPack> dataPacks=new ArrayList<>();
        dataPacks = mapDashboardService.queryOilsByType(ids, all);
        List<DataPack> dataPacks1=new ArrayList<>();
        dataPacks1 = mapDashboardService.queryOilsByType(ids, vip);
        List<DouPack> douPacks=new ArrayList<>();
        douPacks = mapDashboardService.queryNotOilAndOil(ids);

        List<DataPack> dataPacks2=new ArrayList<>();
        dataPacks2 = mapDashboardService.queryTop10(ids);


        //油品交易笔数
        Double drainNum = douPack.getDrainNum();
        //油品交易量（升）
        Double other = douPack.getOther();


        //油品交易金额
        Double value3 = douPack.getValue3();

        //非油交易额
        Double other1 = douPack2.getOther();
        //非油交易笔数
        Double drainNum3 = douPack2.getDrainNum();

        //会员燃油交易额
        Double value31 = douPack1.getValue3();
        //会员非油交易额
        Double value32 = douPack3.getOther();

        //总交易额
        Double allTradeMoney=value3+other1;

        //会员总交易额
        Double vipTradeMoney=value31+value32;



        //会员数量 integer
        //会员燃油交易笔数
        Double drainNum1 = douPack1.getDrainNum();
        //会员非油交易笔数
        Double drainNum2 = douPack3.getDrainNum();

        //总的交易笔数
        Double allTradeNumber=drainNum+drainNum3;

        //会员总交易笔数
        Double vipTradeNumber=drainNum1+drainNum2;

        Double litre92=0.0;
        Double litre95=0.0;
        Double diesel=0.0;
        //柱状图，以及环形图的数据
        for (DataPack dataPack : dataPacks) {
            if("92#".equals(dataPack.getName())){
                litre92+=dataPack.getValue();
            }else if ("95#".equals(dataPack.getName())){
                litre95+=dataPack.getValue();
            }else {
                diesel+=dataPack.getValue();
            }
        }
        List<String> names = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        names.add("今日柴油");
        names.add("92#");
        names.add("95#");
        values.add(diesel.intValue());
        values.add(litre92.intValue());
        values.add(litre95.intValue());

        List<DataPack> pie1 = new ArrayList<>();
        pie1.add(new DataPack("92#",litre92));
        pie1.add(new DataPack("95#",litre95));
        pie1.add(new DataPack("今日柴油",diesel));

        //半环形图
        Double vipLitre92=0.0;
        Double vipLitre95=0.0;
        for (DataPack dataPack : dataPacks1) {
            if("92#".equals(dataPack.getName())){
                vipLitre92+=dataPack.getValue();
            }else if ("95#".equals(dataPack.getName())){
                vipLitre95+=dataPack.getValue();
            }
        }

        List<String> nameMonth = new ArrayList<>();
        List<Double> oilMonth = new ArrayList<>();
        List<Double> notOilMonth = new ArrayList<>();
        for (DouPack pack : douPacks) {
            nameMonth.add(pack.getMonth());
            oilMonth.add(pack.getDrainNum()/1000);
            notOilMonth.add(pack.getOther()/1000);
        }

        Double format00 = vipLitre92 / litre92*100;
        Integer format =format00.intValue();

        Double format11 = vipLitre95 / litre95*100;
        Integer format1=format11.intValue();

        Double format22= vipTradeNumber / allTradeNumber*100;
        Integer format2=format22.intValue();

        Double format33 = vipTradeMoney / allTradeMoney*100;
        Integer format3 =format33.intValue();


        List<String> namesTop = new ArrayList<>();
        List<Double> valuesTop = new ArrayList<>();
        for (DataPack dataPack : dataPacks2) {
            namesTop.add(dataPack.getName());
            valuesTop.add(dataPack.getValue());
        }


        Map<String, Object> map = new HashMap<>();



        map.put("bar1",df0.format(values.get(0)));
        map.put("bar2",df0.format(values.get(1)));
        map.put("bar3",df0.format(values.get(2)));
        map.put("namesTop",namesTop);
        map.put("valuesTop",valuesTop);
        map.put("per",format+"%");
        map.put("per1",format1+"%");
        map.put("per2",format2+"%");
        map.put("per3",format3+"%");
        map.put("oilLitre",other);
        map.put("oilLitreString",df0.format(other));
        map.put("vipNumber",integer);
        map.put("vipNumberString",df0.format(integer.intValue()));
        map.put("notOilMoney",other1);
        map.put("notOilMoneyString",df0.format(other1));
        map.put("vipTradeNumber",vipTradeNumber);
        map.put("vipTradeNumberString",df0.format(vipTradeNumber));
        map.put("namesToday",names);
        map.put("valuesToday",values);
        map.put("todayPie1",pie1);
        map.put("notVipLitre92",litre92-vipLitre92);
        map.put("notVipLitre95",litre95-vipLitre95);
        map.put("vipLitre92",vipLitre92);
        map.put("vipLitre95",vipLitre95);

        map.put("notVipTradeNumber",allTradeNumber-vipTradeNumber);
        map.put("notVipTradeAmount",allTradeMoney-vipTradeMoney);
        map.put("vipTradeAmount",vipTradeMoney);
        map.put("namesMonth",nameMonth);
        map.put("oilMonth",oilMonth);
        map.put("notOilMonth",notOilMonth);
        return map;
    }
    /**
     * 单个油站的数据
     *
     *
     */
    @ResponseBody
    @RequestMapping("queryByStation")
    public Map<String,Object>queryByStation(String id){
        DecimalFormat df0 = new DecimalFormat("#,###");
        System.out.println(id);
        List<String> ids = new ArrayList<>(5);
        ids.add(id);
        String vip="vip";
        String all="all";
        String area="BJSHELL";
        StationPack stationPack = stationService.queryById(id);
        if("承德".equals(stationPack.getCity())){
            area="CDSHELL";
        }

        DouPack
        douPack = mapDashboardService.queryOilTradeNumberAndAmount(ids, all);
        if(douPack==null){
            douPack=new DouPack("",0.0,0.0);
        }
        DouPack douPack1;
        douPack1 = mapDashboardService.queryOilTradeNumberAndAmount(ids, vip);
        if(douPack1==null){
            douPack1=new DouPack("",0.0,0.0);
        }
        DouPack douPack2=new DouPack("",0.0,0.0);
        douPack2 = mapDashboardService.queryNotOilTradeNumberAndAmount(ids, all);
        if(douPack2==null){
            douPack2=new DouPack("",0.0,0.0);
        }
        DouPack
        douPack3 = mapDashboardService.queryNotOilTradeNumberAndAmount(ids, vip);
        if(douPack3==null){
            douPack3=new DouPack("",0.0,0.0);
        }
        List<DataPack> dataPacks=new ArrayList<>();
        dataPacks = mapDashboardService.queryOilsByType(ids, all);
        List<DataPack> dataPacks1=new ArrayList<>();
        dataPacks1 = mapDashboardService.queryOilsByType(ids, vip);
        List<DouPack> douPacks=new ArrayList<>();
        douPacks = mapDashboardService.queryNotOilAndOil(ids);

        List<DataPack> dataPacks2=new ArrayList<>();
        dataPacks2 = mapDashboardService.queryOilByHour(id);


        //油品交易笔数
        Double drainNum=0.0;
        drainNum = douPack.getDrainNum();
        //油品交易量（升）
        Double other=0.0;
        other = douPack.getOther();


        //油品交易金额
        Double value3=0.0;
        value3 = douPack.getValue3();

        //非油交易额
        Double other1=0.0;
        other1 = douPack2.getOther();
        //非油交易笔数
        Double drainNum3=0.0;
        drainNum3 = douPack2.getDrainNum();

        //会员燃油交易额
        Double value31=0.0;
        value31 = douPack1.getValue3();

        //会员非油交易额
        Double value32=0.0;
        value32 = douPack3.getOther();

        //总交易额
        Double allTradeMoney=0.0;
        if(value3==null){
            value3=0.0;
        }
        if(other1==null){
            other1=0.0;
        }
        allTradeMoney = value3+other1;

        //会员总交易额
        Double vipTradeMoney=0.0;
        if(value31==null){
            value31=0.0;
        }
        if(value32==null){
            value32=0.0;
        }
        vipTradeMoney = value31+value32;

        //会员数量 integer
        //会员燃油交易笔数
        Double drainNum1=0.0;
        drainNum1 = douPack1.getDrainNum();
        //会员非油交易笔数
        Double drainNum2=0.0;
        drainNum2 = douPack3.getDrainNum();

        //总的交易笔数
        Double allTradeNumber=0.0;
        if(drainNum==null){
            drainNum=0.0;
        }
        if(drainNum3==null){
            drainNum3=0.0;
        }
        allTradeNumber = drainNum+drainNum3;

        //会员总交易笔数
        Double vipTradeNumber=0.0;
        if(drainNum1==null){
            drainNum1=0.0;
        }
        if(drainNum2==null){
            drainNum2=0.0;
        }
        vipTradeNumber = drainNum1+drainNum2;

        Double litre92=0.0;
        Double litre95=0.0;
        Double diesel=0.0;
        //柱状图，以及环形图的数据
        for (DataPack dataPack : dataPacks) {
            if("92#".equals(dataPack.getName())){
                litre92+=dataPack.getValue();
            }else if ("95#".equals(dataPack.getName())){
                litre95+=dataPack.getValue();
            }else {
                diesel+=dataPack.getValue();
            }
        }
        List<String> names = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        names.add("今日柴油");
        names.add("92#");
        names.add("95#");
        values.add(diesel.intValue());
        values.add(litre92.intValue());
        values.add(litre95.intValue());

        List<DataPack> pie1 = new ArrayList<>();
        pie1.add(new DataPack("92#",litre92));
        pie1.add(new DataPack("95#",litre95));
        pie1.add(new DataPack("今日柴油",diesel));

        //半环形图
        Double vipLitre92=0.0;
        Double vipLitre95=0.0;
        for (DataPack dataPack : dataPacks1) {
            if("92#".equals(dataPack.getName())){
                vipLitre92+=dataPack.getValue();
            }else if ("95#".equals(dataPack.getName())){
                vipLitre95+=dataPack.getValue();
            }
        }

        List<String> namesHour= new ArrayList<>(24);
        List<Double> valuesHour= new ArrayList<>(24);
        //分时段销量
        for (DataPack dataPack : dataPacks2) {
            namesHour.add(dataPack.getName());
            valuesHour.add(dataPack.getValue());
        }


        if(vipLitre92==null){
            vipLitre92=0.0;
        }
        if(litre92==null){
            litre92=0.0;
        }
        if(vipLitre95==null){
            vipLitre95=0.0;
        }
        if(litre95==null){
            litre95=0.0;
        }
        Double format00 = vipLitre92 / litre92*100;
        Integer format =format00.intValue();

        Double format11 = vipLitre95 / litre95*100;
        Integer format1=format11.intValue();
        /*$("#oilTradeLitre").html(map.oilLitre+"升");
        $("#vipNumber").html(map.vipNumber+"人");
        $("#notOilTradeMoney").html(map.notOilMoney+"元");
        $("#vipTradeNumber").html(map.vipTradeNumber+"笔");
         $("#oneOilTradeAmount").html(map.oilLitre+"升");
                        $("#oneVipTradeMoney").html(map.vipTradeAmount+"元");
                        $("#oneNotoilTradeMoney").html(map.notOilMoney+"元");
                        $("#oneVipTradeNumber").html(map.vipTradeNumber+"笔");*/
        Double format22= vipTradeNumber / allTradeNumber*100;
        Integer format2=format22.intValue();

        Double format33 = vipTradeMoney / allTradeMoney*100;
        Integer format3 =format33.intValue();
        Map<String, Object> map = new HashMap<>(20);

        map.put("bar1",df0.format(values.get(0)));
        map.put("bar2",df0.format(values.get(1)));
        map.put("bar3",df0.format(values.get(2)));
        map.put("format",format);
        map.put("format1",format1);
        map.put("format2",format2);
        map.put("format3",format3);
        map.put("oilLitre",other);
        map.put("oilLitreString",df0.format(other));
        map.put("notOilMoney",other1);
        map.put("notOilMoneyString",df0.format(other1));
        map.put("vipTradeNumber",vipTradeNumber);
        map.put("vipTradeNumberString",df0.format(vipTradeNumber));
        map.put("namesToday",names);
        map.put("valuesToday",values);
        map.put("todayPie1",pie1);
        map.put("notVipLitre92",litre92-vipLitre92);
        map.put("notVipLitre95",litre95-vipLitre95);
        map.put("vipLitre92",vipLitre92);
        map.put("vipLitre95",vipLitre95);

        map.put("notVipTradeNumber",allTradeNumber-vipTradeNumber);
        map.put("notVipTradeAmount",allTradeMoney-vipTradeMoney);
        map.put("vipTradeAmount",vipTradeMoney);
        map.put("vipTradeAmountString",df0.format(vipTradeMoney));

        map.put("namesHour",namesHour);
        map.put("valuesHour",valuesHour);

        return map;
    }


}
