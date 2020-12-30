package com.shpowernode.crm.settings.service;

import com.shpowernode.crm.settings.bean.User;

import java.util.List;

public interface UserService {

    User login(User user);

    List<User> selectAll();

    void fileUpload(User user);

    User selectById(String createby);
}
