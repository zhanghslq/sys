package com.yb.service.impl;

import com.yb.dao.MapDashboardDao;
import com.yb.entity.DataPack;
import com.yb.entity.DouPack;
import com.yb.service.MapDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MapDashboardServiceImpl implements MapDashboardService {
    @Autowired
    private MapDashboardDao mapDashboardDao;

    @Override
    public Integer queryVipAmountByArea(String area) {
        Integer integer = mapDashboardDao.queryVipAmountByArea(area);
        return integer;
    }

    @Override
    public DouPack queryOilTradeNumberAndAmount(List<String> ids, String isVip) {
        DouPack douPack = mapDashboardDao.queryOilTradeNumberAndAmount(ids, isVip);
        return douPack;
    }

    @Override
    public DouPack queryNotOilTradeNumberAndAmount(List<String> ids, String isVip) {
        DouPack douPack = mapDashboardDao.queryNotOilTradeNumberAndAmount(ids, isVip);
        return douPack;
    }

    @Override
    public List<DataPack> queryOilsByType(List<String> ids, String isVip) {
        List<DataPack> dataPacks = mapDashboardDao.queryOilsByType(ids, isVip);
        return dataPacks;
    }

    @Override
    public List<DouPack> queryNotOilAndOil(List<String> ids) {
        List<DouPack> douPacks = mapDashboardDao.queryNotOilAndOil(ids);
        return douPacks;
    }

    @Override
    public List<DataPack> queryTop10(List<String> ids) {
        List<DataPack> dataPacks = mapDashboardDao.queryTop10(ids);
        return dataPacks;
    }

    @Override
    public List<DataPack> queryOilByHour(String id) {
        List<DataPack> dataPacks = mapDashboardDao.queryOilByHour(id);
        return dataPacks;
    }

    @Override
    public List<Map<String, String>> queryEvaluationByStars() {
        List<Map<String, String>> maps = mapDashboardDao.queryEvaluationByStars();

        return maps;
    }
}
