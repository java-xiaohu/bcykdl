package com.shpowernode.crm.workbench.service.impl;

import com.shpowernode.crm.base.constants.CrmExceptionEnum;
import com.shpowernode.crm.base.exception.CrmException;
import com.shpowernode.crm.base.util.DateTimeUtil;
import com.shpowernode.crm.base.util.UUIDUtil;
import com.shpowernode.crm.settings.bean.User;
import com.shpowernode.crm.settings.mapper.UserMapper;
import com.shpowernode.crm.workbench.bean.Activity;
import com.shpowernode.crm.workbench.bean.ActivityQueryVo;
import com.shpowernode.crm.workbench.mapper.ActivityDao;
import com.shpowernode.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private UserMapper userMapper;

    @Override
    @RequestMapping("/activity/list")
    @ResponseBody
    public List<Activity> list(ActivityQueryVo activityQueryVo) {
       /* String owner = activityQueryVo.getOwner();
        List<User> users=userMapper.selectLikeByOwner(owner);*/

        List<Activity> list = activityDao.selectCondition(activityQueryVo);
        /*for(Activity activity:list){
            User user = new User();
            user.setId(activity.getOwner());
            User user1 = userMapper.selectOne(user);
            activity.setOwner(user1.getName());
        }*/
        return list;
    }

    @Override
    public void addActivity(Activity activity) {
        if (activity.getId()==null){
            activity.setId(UUIDUtil.getUUID());
            activity.setCreatetime(DateTimeUtil.getSysTime());
            int i = activityDao.insertSelective(activity);
            if (i==0){
                throw new CrmException(CrmExceptionEnum.ACTIVITY_CREATE);
            }
        }else {
            activity.setEdittime(DateTimeUtil.getSysTime());
            int i = activityDao.updateByPrimaryKeySelective(activity);
            if (i==0){
                throw new CrmException(CrmExceptionEnum.ACTIVITY_CREATE);
            }
        }


    }
    @Override
    public void updateActivity(Activity activity) {

            activity.setEdittime(DateTimeUtil.getSysTime());
            int i = activityDao.updateByPrimaryKeySelective(activity);
            if (i==0){
                throw new CrmException(CrmExceptionEnum.ACTIVITY_CREATE);
            }



    }

    @Override
    public Activity queryOne(String id) {
        return activityDao.selectByPrimaryKey(id);
    }

    @Override
    public int deleteAll(String[] s) {
        int i = activityDao.deleteAll(s);
        if (i==0){
            throw new CrmException(CrmExceptionEnum.ACTIVITY_DELETE);
        }

        return i;

    }

    @Override
    public Activity selectById(String id) {
        Activity activity = activityDao.selectByPrimaryKey(id);
        User user = userMapper.selectByPrimaryKey(activity.getOwner());
        activity.setOwner(user.getName());
        return activity;
    }
}
