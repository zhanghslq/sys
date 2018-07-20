package com.yb.service.impl;

import com.yb.dao.WarnDao;
import com.yb.entity.DouPack;
import com.yb.entity.InterPack;
import com.yb.entity.Interval;
import com.yb.service.WarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class WarnServiceImpl implements WarnService {
    @Autowired
    private WarnDao warnDao;


    @Override
    public List<InterPack> queryByV14(String area) {
        List<InterPack> interPacks = warnDao.queryByV14(area);
        return interPacks;
    }

    @Override
    public List<DouPack> queryRand(String area) {
        List<DouPack> douPacks = warnDao.queryRand(area);
        return douPacks;
    }

    @Override
    public Interval queryByCardUserId(Integer cardUserId, String area) {
        Interval intervals = warnDao.queryByCardUserId(cardUserId, area);
        return intervals;
    }

    @Override
    public DouPack queryV13AndV14ById(Integer cardUserId, String area) {
        DouPack douPack = warnDao.queryV13AndV14ById(cardUserId, area);
        return douPack;
    }

    @Override
    public List<DouPack> queryListById(String area) {
        List<Integer> integers = warnDao.queryIdsByCardUserId();
        if(integers!=null&&integers.size()!=0){
            List<DouPack> douPacks = warnDao.queryByListId(integers,area);
            return douPacks;
        }else{
            return null;
        }
    }
}
