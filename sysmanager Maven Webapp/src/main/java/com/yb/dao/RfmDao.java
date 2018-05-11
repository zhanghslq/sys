package com.yb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yb.entity.DataPack;
import com.yb.entity.DouRfm;

public interface RfmDao {
	//查询rfm模型的结果，两个环形图，加上两个散点图，一个柱状图
	//1 查询90天内有消费的，90天内无消费的
	
	//2, R《90重要客户，一般客户，R》90一次消费，多次消费
	
	//3, 90天内有消费的各个细节，大于90天的，一般唤醒，重要唤醒，一次消费的
	//环形图的话只需要名字以及数量
	List<DataPack> queryByGroup(@Param("area")String area);
	List<DouRfm> queryThreeRfms(@Param("area")String area);
	List<DouRfm> queryHistoryRfms(@Param("area")String area);
}