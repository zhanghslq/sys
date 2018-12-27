package com.yb.dao;

import com.yb.entity.DataPack;
import com.yb.entity.DouPack;
import com.yb.entity.EvalPack;
import com.yb.entity.InterPack;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MapDashboardDao {


    /**
     * 查询大区会员数量
     * @param area 地区
     * @return 返回当前地区会员数量
     */
    Integer queryVipAmountByArea(@Param("area") String area);

    /**
     * 当天
     * 燃油交易笔数  交易量   会员与非会员的
     * 查询会员交易笔数  交易额   会员和非会员的
     * @param ids 油站编号集合
     *  @param isVip 是否是会员
     * @return 交易笔数  交易额汇总  drainNum 交易笔数  other交易额
     * 可以查出会员交易笔数，会员交易额，也可以做下面的会员交易笔数交易额与总的对比
     */
    DouPack queryOilTradeNumberAndAmount(@Param("ids") List<String> ids,@Param("isVip") String isVip);
    /**
     * 非油交易笔数，交易额
     * 查询会员交易笔数  交易额   会员和非会员的
     * @param ids 油站编号集合
     *  @param isVip 是否是会员
     * @return 交易笔数  交易额汇总  drainNum 交易笔数  other交易额
     * 可以查出会员交易笔数，会员交易额，也可以做下面的会员交易笔数交易额与总的对比
     */
    DouPack queryNotOilTradeNumberAndAmount(@Param("ids") List<String> ids,@Param("isVip") String isVip);
    /**
     * 查询按照油品种类汇总的数据
     * @param ids 油站id
     * @param isVip 是否是会员的
     * @return 油品以及销量的集合
     * 既可以查询会员的数据 ，也可以查询非会员的数据
     */
    List<DataPack> queryOilsByType(@Param("ids") List<String> ids,@Param("isVip") String isVip);

    /**
     * 燃油 非油 月度销量
     *
     */
    /**
     * 查询非油月度销售额  查询油品月度销量  可以放在一起，通过时间和油站id进行关联
     * @param ids
     * @return 返回值是DouPack集合，包括时间，非油销售额和燃油销量 drainNum
     * other  非油销量
     *
     */
    List<DouPack> queryNotOilAndOil(@Param("ids") List<String> ids);

    /**
     * 销量TOP榜单
     * @param ids
     * @return
     */
    List<DataPack> queryTop10(@Param("ids") List<String> ids);

    /**
     * 分时段销量
     * @param id 油站标号
     * @return
     */
    List<DataPack> queryOilByHour(@Param("id") String id);

    /**
     * 查询评价
     * @return 全五星评价人的姓名和评价
     */
    List<EvalPack> queryEvaluationByStars();



}
