package com.yb.controller;

import com.yb.entity.*;
import com.yb.service.WarnService;
import com.yb.util.SingleRengine;
import org.rosuda.JRI.Rengine;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.*;
/**
 * @author Administrator
 */
@Controller
@RequestMapping("/warn")
@Scope("prototype")
public class WarnController {
    @Resource
    private WarnService warnService;
    /**
     * 可用于饼图加直方图
     * @return 返回的是一个map存放结果
     */
    @RequestMapping("/queryByV14")
    @ResponseBody
    public Map<String,Object> queryByV14(String area){
        List<InterPack> interPacks = warnService.queryByV14(area);
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> values = new ArrayList<>();
        //饼图
        for (InterPack interPack : interPacks) {
            names.add(interPack.getName());
            values.add(interPack.getValue());
            if("0".equals(interPack.getName())){
                interPack.setName("t<M");
            }else if ("1".equals(interPack.getName())){
                interPack.setName("M<t<2M");
            }else if ("2".equals(interPack.getName())){
                interPack.setName("2M<t<3M");
            }else if ("3".equals(interPack.getName())){
                interPack.setName("t>3M");
            }else if ("4".equals(interPack.getName())){
                interPack.setName("只消费过2次");
            }else if ("5".equals(interPack.getName())){
                interPack.setName("只消费过1次");
            }else if ("6".equals(interPack.getName())){
                interPack.setName("只消费过3次");
            }
        }
        //v12 drainNum 家加权平均 v13 other 加权方差   v14 month 分类
        // 这是散点图的数据
        List<DouPack> douPacks = warnService.queryRand(area);
        //新建7个集合，用来存储根据方差分类的七类人群
        ArrayList<List> pack0 = new ArrayList<>();
        ArrayList<List> pack1 = new ArrayList<>();
        ArrayList<List> pack2 = new ArrayList<>();
        ArrayList<List> pack3 = new ArrayList<>();
        ArrayList<List> pack4 = new ArrayList<>();
        ArrayList<List> pack5 = new ArrayList<>();
        ArrayList<List> pack6 = new ArrayList<>();
        for (DouPack douPack : douPacks) {
            if("0".equals(douPack.getMonth())){
                ArrayList<Double> doubles = new ArrayList<>(2);
                doubles.add(douPack.getDrainNum());
                doubles.add(douPack.getOther());
                pack0.add(doubles);
            }else if("1".equals(douPack.getMonth())){
                ArrayList<Double> doubles = new ArrayList<>(2);
                doubles.add(douPack.getDrainNum());
                doubles.add(douPack.getOther());
                pack1.add(doubles);
            }else if("2".equals(douPack.getMonth())){
                ArrayList<Double> doubles = new ArrayList<>(2);
                doubles.add(douPack.getDrainNum());
                doubles.add(douPack.getOther());
                pack2.add(doubles);
            }else if("3".equals(douPack.getMonth())){
                ArrayList<Double> doubles = new ArrayList<>(2);
                doubles.add(douPack.getDrainNum());
                doubles.add(douPack.getOther());
                pack3.add(doubles);
            }else if("4".equals(douPack.getMonth())){
                ArrayList<Double> doubles = new ArrayList<>(2);
                doubles.add(douPack.getDrainNum());
                doubles.add(douPack.getOther());
                pack4.add(doubles);
            }else if("5".equals(douPack.getMonth())){
                ArrayList<Double> doubles = new ArrayList<>(2);
                doubles.add(douPack.getDrainNum());
                doubles.add(douPack.getOther());
                pack5.add(doubles);
            }else if("6".equals(douPack.getMonth())){
                ArrayList<Double> doubles = new ArrayList<>(2);
                doubles.add(douPack.getDrainNum());
                doubles.add(douPack.getOther());
                pack6.add(doubles);
            }
        }
        Map<String, Object> map = new HashMap<>(9);
        map.put("names",names);
        map.put("values",values);
        map.put("datas",interPacks);
        map.put("pack0",pack0);
        map.put("pack1",pack1);
        map.put("pack2",pack2);
        map.put("pack3",pack3);
        map.put("pack4",pack4);
        map.put("pack5",pack5);
        map.put("pack6",pack6);
        return map;
    }

    /**
     * 展示  解释权重的图
     * @return 自定义数组
     */
    @RequestMapping("/queryShow")
    @ResponseBody
    public Map<String,Object> queryShow(){
        List<Integer> names = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        names.add(1);
        names.add(2);
        names.add(3);
        names.add(4);
        names.add(5);
        names.add(6);
        names.add(7);
        names.add(8);
        names.add(9);
        values.add(0.2);
        values.add(0.2);
        values.add(0.15);
        values.add(0.15);
        values.add(0.1);
        values.add(0.08);
        values.add(0.06);
        values.add(0.04);
        values.add(0.02);
        Map<String, Object> map = new HashMap<>(2);
        map.put("names",names);
        map.put("values",values);
        return map;
    }

    /**
     * v13是other V14是drainNum
     * @param area 北京还是承德，用来标识地区
     * @return 直接返回的一个list数组，数组里面还是数组，用来绘制散点图
     */
    @ResponseBody
    @RequestMapping("/queryRand")
    public List<List<Double>> queryRand(String area){
        List<DouPack> douPacks = warnService.queryRand(area);
        List<List<Double>> lists = new ArrayList<>();
        for (DouPack douPack : douPacks) {
            List<Double> doubles = new ArrayList<>();
            doubles.add(douPack.getDrainNum());
            doubles.add(douPack.getOther());
            lists.add(doubles);
        }
        return lists;
    }

    /**
     * 调用R执行函数，
     * @param area 地区
     * @param id 会员id
     * @return 返回值包括会员的近9次消费间隔还有加权平均以及方差做对比
     */
    @ResponseBody
    @RequestMapping("/querySomeVip")
    public Map<String, Object> querySomeVip(String area,Integer id){
        try {
            Rengine engine = SingleRengine.getRegineInstance();
            // 等待解析类初始化完毕
            if(engine!=null){
                if (!engine.waitForR()) {
                    System.out.println("exception");
                    return null;
                }
                if(id==null){
                    id=100080;
                }
                Integer areaId;
                if("BJSHELL".equals(area)){
                    areaId=1;
                }else{
                    areaId=0;
                }
                // 加载包
                System.out.println("开始执行");
//                String filePath = "D:/ap/warnsomevip.R";
                String filePath = "/opt/RFile/warnsomevip.R";
                // 将文件全路径复制给R中的一个变量
                engine.assign("fileName", filePath);
                // 在R中执行文件。执行后，文件中的两个函数加载到R环境中，后续可以直接调用
                engine.eval("source(fileName)");
                System.err.println("R文件执行完毕");
                //支持度，置信度先定义好
                engine.eval("idJava <-"+id);
                engine.eval("areaJava <-"+areaId);
                 // 入参为list，出参为list。调用R中函数，将结果保存到一个对象中。
                engine.eval("somevip(idJava, areaJava)");
                System.out.println("调用R结束");
                //可以开始进行数据库查询
                Interval interval = warnService.queryByCardUserId(id, area);
                /*
                 * names存放回购天数，
                 * values存放回购对应天数的回购次数
                 */
                List<String> names = new ArrayList<>();
                ArrayList<Integer> values = new ArrayList<>();
                values.add(interval.getOne_two());
                names.add("1~2");
                values.add(interval.getTwo_three());
                names.add("2~3");
                values.add(interval.getThree_four());
                names.add("3~4");
                values.add(interval.getFour_five());
                names.add("4~5");
                values.add(interval.getFive_six());
                names.add("5~6");
                values.add(interval.getSix_seven());
                names.add("6~7");
                values.add(interval.getSeven_eight());
                names.add("7~8");
                values.add(interval.getEight_nine());
                names.add("8~9");
                values.add(interval.getNine_ten());
                names.add("9~10");
                //12 drainNum 13 other 14 month
                DouPack douPack = warnService.queryV13AndV14ById(id, area);
                //散点图,查出来的数据
                List<DouPack> douPacks = warnService.queryListById(area);
                ArrayList<List> doubleScatter = new ArrayList<>();
                if(douPacks!=null&&douPacks.size()!=0){
                    for (DouPack pack : douPacks) {
                        if(pack!=null){
                            ArrayList<Double> doubles = new ArrayList<>(2);
                            doubles.add(pack.getDrainNum());
                            doubles.add(pack.getOther());
                            doubleScatter.add(doubles);
                        }
                    }
                }else {
                    ArrayList<Double> doubles = new ArrayList<>(2);
                    doubles.add(0.0);
                    doubles.add(0.0);
                    doubleScatter.add(doubles);
                }
                DouPack douPack1 = warnService.queryV13AndV14ById(id, area);
                ArrayList<List> self = new ArrayList<>();
                ArrayList<Double> doubles = new ArrayList<>();
                if(douPack1!=null){
                    doubles.add(douPack1.getDrainNum());
                    doubles.add(douPack1.getOther());
                    self.add(doubles);
                }else{
                    doubles.add(0.0);
                    doubles.add(0.0);
                    self.add(doubles);
                }
                System.out.println(doubleScatter.size());
                Map<String,Object> map = new HashMap<>(5);
                map.put("self",self);
                map.put("douScatter",doubleScatter);
                map.put("names",names);
                map.put("values",values);
                map.put("v12",douPack.getDrainNum());
                map.put("v13",douPack.getOther());
                return map;
            }else{//没获取到regine的时候
                return null;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

}
