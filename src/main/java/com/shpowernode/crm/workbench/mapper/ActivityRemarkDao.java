package com.shpowernode.crm.workbench.mapper;

import com.shpowernode.crm.workbench.bean.ActivityRemark;

import java.util.List;

public interface ActivityRemarkDao {
    int deleteByPrimaryKey(String id);

    int insert(ActivityRemark record);

    int insertSelective(ActivityRemark record);

    ActivityRemark selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ActivityRemark record);

    int updateByPrimaryKey(ActivityRemark record);

    List<ActivityRemark> selectByActivityId(String id);
}