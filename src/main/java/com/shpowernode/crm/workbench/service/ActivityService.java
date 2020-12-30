package com.shpowernode.crm.workbench.service;

import com.shpowernode.crm.workbench.bean.Activity;
import com.shpowernode.crm.workbench.bean.ActivityQueryVo;

import java.util.List;

public interface ActivityService {
    List<Activity> list(ActivityQueryVo activityQueryVo);

    void addActivity(Activity activity);
    void updateActivity(Activity activity);


    Activity queryOne(String id);

    int deleteAll(String[] s);

    Activity selectById(String id);
}
