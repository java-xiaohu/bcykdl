package com.shpowernode.crm.workbench.mapper;

import com.shpowernode.crm.workbench.bean.Activity;
import com.shpowernode.crm.workbench.bean.ActivityQueryVo;

import java.util.List;

public interface ActivityDao {
    int deleteByPrimaryKey(String id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(String id);
    List<Activity> selectCondition(ActivityQueryVo activityQueryVo);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    int deleteAll(String[] s);
}