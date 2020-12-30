package com.shpowernode.crm.workbench.service.impl;

import com.shpowernode.crm.base.constants.CrmExceptionEnum;
import com.shpowernode.crm.base.exception.CrmException;
import com.shpowernode.crm.base.util.DateTimeUtil;
import com.shpowernode.crm.base.util.UUIDUtil;
import com.shpowernode.crm.settings.bean.User;
import com.shpowernode.crm.settings.mapper.UserMapper;
import com.shpowernode.crm.workbench.bean.ActivityRemark;
import com.shpowernode.crm.workbench.mapper.ActivityDao;
import com.shpowernode.crm.workbench.mapper.ActivityRemarkDao;
import com.shpowernode.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityRemarkServiceImpl implements ActivityRemarkService {
    @Autowired
    private ActivityRemarkDao activityRemarkDao;
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<ActivityRemark> selectById(String id) {
        List<ActivityRemark> activityRemarkList = activityRemarkDao.selectByActivityId(id);
        for (ActivityRemark activityRemark:activityRemarkList) {
            User user = new User();
            user.setName(activityRemark.getCreateby());
            User user1 = userMapper.selectOne(user);
            activityRemark.setPhoto(user1.getPhoto());
            activityRemarkDao.updateByPrimaryKeySelective(activityRemark);

        }
        return activityRemarkList;
    }

    @Override
    public void update(ActivityRemark activityRemark) {
        activityRemark.setEditflag("1");
        activityRemark.setEdittime(DateTimeUtil.getSysTime());
        int i = activityRemarkDao.updateByPrimaryKeySelective(activityRemark);
        if (i==0){
            throw new CrmException(CrmExceptionEnum.ACTIVITYREMARK_UPDATE);
        }

    }

    @Override
    public void del(String id) {
        int i = activityRemarkDao.deleteByPrimaryKey(id);
        if (i==0){
            throw new CrmException(CrmExceptionEnum.ACTIVITYREMARK_DELETE);
        }
    }

    @Override
    public ActivityRemark addActivityRemark(ActivityRemark activityRemark) {
        activityRemark.setId(UUIDUtil.getUUID());
        activityRemark.setCreatetime(DateTimeUtil.getSysTime());
        int i = activityRemarkDao.insertSelective(activityRemark);
        if (i==0){
            throw new CrmException(CrmExceptionEnum.ACTIVITYREMARK_INSERT);
        }else {
            return activityRemark;
        }
    }
}
