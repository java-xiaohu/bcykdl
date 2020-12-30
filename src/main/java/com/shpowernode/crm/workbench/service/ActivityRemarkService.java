package com.shpowernode.crm.workbench.service;

import com.shpowernode.crm.workbench.bean.ActivityRemark;

import java.util.List;

public interface ActivityRemarkService {
    List<ActivityRemark> selectById(String id);

    void update(ActivityRemark activityRemark);

    void del(String id);

    ActivityRemark addActivityRemark(ActivityRemark activityRemark);
}
