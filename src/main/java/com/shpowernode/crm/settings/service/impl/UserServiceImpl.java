package com.shpowernode.crm.settings.service.impl;

import com.shpowernode.crm.base.constants.CrmExceptionEnum;
import com.shpowernode.crm.base.exception.CrmException;
import com.shpowernode.crm.base.util.*;
import com.shpowernode.crm.settings.bean.User;
import com.shpowernode.crm.settings.mapper.UserMapper;
import com.shpowernode.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(User user) {

        user.setLoginpwd(MD5Util.getMD5(user.getLoginpwd()));
        //在查出用户数据之前要先取出客户端的ip地址信息
        String address = user.getAllowips();
        //得把客户端的ip置为null，如果不是null将会参与条件查询
        user.setAllowips(null);
        user = userMapper.selectOne(user);
        if(user == null){
            //用户名或密码错误
            throw new CrmException(CrmExceptionEnum.LOGIN_ACCOUNT_EXCEPTION);
        }else{
            //比较失效日期
            /*
            如果两个日期字符串比较大小，使用compareTo方法进行比较 t1 t2
            如果返回值<0 t1 < t2
            返回值=0 t1 == t2
            返回值>0 t1 > t2
             */
            //账户失效了
            if(user.getExpiretime().compareTo(DateTimeUtil.getSysTime()) < 0){
                throw new CrmException(CrmExceptionEnum.LOGIN_EXPIRE_EXCEPTION);
            }

            //账户是否被禁用
            if("0".equals(user.getLockstate())){
                throw new CrmException(CrmExceptionEnum.LOGIN_LOCK_EXCEPTION);
            }

            //ip地址是否合法
            if(!user.getAllowips().contains(address)){
                throw new CrmException(CrmExceptionEnum.LOGIN_IP_EXCEPTION);
            }
        }
        //登录用户所有条件都满足了
        return user;


    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public void fileUpload(User user) {
        int i = userMapper.updateByPrimaryKeySelective(user);
        if (i==0){
            throw new CrmException(CrmExceptionEnum.USER_UPLOAD);
        }
    }

    @Override
    public User selectById(String createby) {
        return userMapper.selectByPrimaryKey(createby);
    }
}
