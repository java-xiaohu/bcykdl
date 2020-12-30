package com.shpowernode.crm.workbench.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shpowernode.crm.base.bean.ResultVo;
import com.shpowernode.crm.base.constants.CrmConstants;
import com.shpowernode.crm.base.exception.CrmException;
import com.shpowernode.crm.settings.bean.User;
import com.shpowernode.crm.settings.service.UserService;
import com.shpowernode.crm.workbench.bean.Activity;
import com.shpowernode.crm.workbench.bean.ActivityQueryVo;
import com.shpowernode.crm.workbench.bean.ActivityRemark;
import com.shpowernode.crm.workbench.service.ActivityRemarkService;
import com.shpowernode.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ActivityController {
    @Autowired
    private ActivityRemarkService activityRemarkService;

     @Autowired
    private ActivityService activityService;
     @Autowired
     private UserService userService;
     //添条件分页查询
     @RequestMapping("/activity/list")
     @ResponseBody
     public ResultVo<Activity> list(@RequestParam(defaultValue = "1") Integer pageNum, Integer pageSize, ActivityQueryVo activityQueryVo){
         PageHelper.startPage(pageNum,pageSize);
         List<Activity> list=activityService.list(activityQueryVo);
         PageInfo<Activity> pageInfo=new PageInfo<>(list);

         ResultVo<Activity> activityResultVo = new ResultVo<Activity>();
         activityResultVo.setPageInfo(pageInfo);
         return activityResultVo;
     }

     //点击添加
    @RequestMapping("/activity/createOrUpdateActivity")
    @ResponseBody
    public ResultVo createOrUpdateActivity(Activity activity, HttpSession session){
        ResultVo<Activity> resultVo = new ResultVo<Activity>();
      if (activity.getId()==null){
          try {
              User user = (User) session.getAttribute("user");
              activity.setCreateby(user.getName());
              activityService.addActivity(activity);
              resultVo.setOk(true);
              resultVo.setMess("添加成功");
          }catch (CrmException e){
              e.printStackTrace();
              resultVo.setOk(false);
              resultVo.setMess("添加失败");

          }
      }else {
          try {
              User user = (User) session.getAttribute("user");
              activity.setCreateby(user.getName());
              activityService.updateActivity(activity);
              resultVo.setOk(true);
              resultVo.setMess("修改成功");
          }catch (CrmException e){
              e.printStackTrace();
              resultVo.setOk(false);
              resultVo.setMess("修改失败");

          }
      }


        return resultVo;
    }
    //按id查询
    @RequestMapping("/activity/querybyid")
    @ResponseBody
    public Activity queryById(String id){
         return activityService.queryOne(id);
    }

    //全部删除
    @RequestMapping("/activity/deleteBatch")
    @ResponseBody
    public ResultVo<Activity> deleteBatch(String ids){
        ResultVo<Activity> resultVo = new ResultVo<Activity>();
        String[] split = ids.split(",");
        try {
            activityService.deleteAll(split);
            resultVo.setMess("删除成功");
            resultVo.setOk(true);
        }catch (CrmException e){
            e.printStackTrace();
            resultVo.setMess("删除失败");
            resultVo.setOk(false);
        }
        return  resultVo;

    }

    //转发到备注页面
    @RequestMapping("/activity/toDetail")
    public String toDetail(String id, Model model){

        Activity activity=activityService.selectById(id);
        model.addAttribute("activity",activity);
        List<ActivityRemark> activityRemarkList=activityRemarkService.selectById(id);
        model.addAttribute("activityRemarkList",activityRemarkList);

        return "activity/detail" +
                "";
    }
    //修改备注
    @RequestMapping("/activity/activityRemark")
    @ResponseBody
    public ResultVo<ActivityRemark> activityRemarkUpdate(ActivityRemark activityRemark, HttpSession session){
        ResultVo<ActivityRemark> resultVo = new ResultVo<>();
        try {
            User user = (User) session.getAttribute(CrmConstants.LOGIN_USER);
            activityRemark.setEditby(user.getName());
            activityRemarkService.update(activityRemark);
            resultVo.setT(activityRemark);
            resultVo.setOk(true);
            resultVo.setMess("修改成功");
        }catch (CrmException e){
            e.printStackTrace();
            resultVo.setOk(false);
            resultVo.setMess("修改失败");
        }

        return resultVo;
    }

    //删除备注
   @RequestMapping("/activity/activityRemarkDelete")
   @ResponseBody
    public ResultVo<ActivityRemark> deleteActivityRemark(ActivityRemark activityRemark){
       ResultVo<ActivityRemark> resultVo = new ResultVo<>();
       try {
           resultVo.setT(activityRemark);
           activityRemarkService.del(activityRemark.getId());
           resultVo.setOk(true);
           resultVo.setMess("删除成功");
       }catch (CrmException e){
           e.printStackTrace();
           resultVo.setOk(false);
           resultVo.setMess("删除失败");
       }

       return resultVo;

     }


     //添加备注
    @RequestMapping("/activity/addActivityRemark")
    @ResponseBody
    public ResultVo addActivityRemark(ActivityRemark activityRemark,HttpSession session){
        ResultVo<ActivityRemark> resultVo = new ResultVo<>();
        try {
            User user = (User) session.getAttribute(CrmConstants.LOGIN_USER);
            activityRemark.setCreateby(user.getName() );
            ActivityRemark activityRemark1 = activityRemarkService.addActivityRemark(activityRemark);
            resultVo.setT(activityRemark1);
            resultVo.setOk(true);
            resultVo.setMess("添加成功");
        }catch (CrmException e){
            e.printStackTrace();
            resultVo.setOk(false);
            resultVo.setMess("添加失败");
        }

        return resultVo;

    }







}
