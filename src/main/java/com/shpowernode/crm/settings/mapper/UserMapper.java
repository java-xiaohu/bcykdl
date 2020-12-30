package com.shpowernode.crm.settings.mapper;

import com.shpowernode.crm.settings.bean.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);
    User selectOne(User user);
    List<User> selectAll();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectLikeByOwner(String owner);
}