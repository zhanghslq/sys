package com.yb.dao;

import com.yb.entity.NotOil;
import com.yb.entity.Oil;

import java.util.List;

/**
 * 每日数据自动发送
 * @author Administrator
 */
public interface DailyDataDao {

    /**
     * 按照油站区分昨天销售量
     * @return 油品的返回，oilLitre 油品的销售量
     */
    List<Oil> queryOilByStation();

    /**
     * 每个油站便利店的销售额
     * @return 便利店的notOilMoney 便利店的销售额，sta油品tionid记录油站
     */
     List<NotOil> queryShopByStation();

    /**
     * 油品分品类的销量
     * @return 油品的oils代表油品号，oilLitre代表销量
     */
     List<Oil> queryByOilsLitre();

}
