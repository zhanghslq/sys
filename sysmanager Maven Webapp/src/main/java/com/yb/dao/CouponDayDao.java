package com.yb.dao;

import com.yb.entity.CouponDaySend;

import java.util.List;

/**
 * @author Administrator
 */
public interface CouponDayDao {


    /** 取昨天的数据
     * @return
     */
    List<CouponDaySend> queryDataYesterDay();

}
