package com.yb.dao;

import com.yb.entity.DouPack;
import com.yb.entity.InterPack;
import com.yb.entity.Interval;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhs
 */
public interface WarnDao {
    /**
     * 根据V14分类，做饼图
     * @return 返回值用来做流失预警的饼图
     * @param area 会员所在地区
     */
    List<InterPack> queryByV14(@Param("area") String area);

    /**
     * 为了散点图取数据，一个横坐标，一个纵坐标
     * @return 返回值，drainNum存V14，other存V13
     * 随机取2000数
     * @param area 地区
     */
    List<DouPack> queryRand(@Param("area") String area);
    /**
     * 某会员消费习惯分析
     * @return 返回值为各种消费间隔的次数
     * @param cardUserId 传入会员id查询会员消费信息
     * @param area 会员所属地区
     */
    Interval queryByCardUserId(@Param("cardUserId") Integer cardUserId,@Param("area") String area);
    /**
     * 根据会员id查询方差和加权平均数
     * @return 求方差和加权平均数
     * @param area 查询会员所在地区
     * @param cardUserId 会员id
     */
    DouPack queryV13AndV14ById(@Param("cardUserId") Integer cardUserId,@Param("area") String area);

    /**
     * 取与此会员消费习惯相近的会员id集合
     * @return 返回与此会员相似的会员集合

     */
    List<Integer> queryIdsByCardUserId();

    /**
     * 根据id取出warn_result的加权平均和方差,随机两千条
     * @return 返回的加权平均和方差
     * @param area 会员所在地区
     * @param ids 相似会员id的集合
     */
    List<DouPack> queryByListId(@Param("ids")List<Integer> ids,@Param("area") String area);
}
