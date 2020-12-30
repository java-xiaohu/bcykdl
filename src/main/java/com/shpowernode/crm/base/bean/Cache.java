package com.shpowernode.crm.base.bean;

import com.shpowernode.crm.settings.bean.User;
import com.shpowernode.crm.settings.mapper.UserMapper;
import com.shpowernode.crm.workbench.bean.DicValue;
import com.shpowernode.crm.workbench.mapper.DicTypeDao;
import com.shpowernode.crm.workbench.service.DictionarySerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;

@Component
public class Cache {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DictionarySerivce dictionarySerivce;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private DicTypeDao dicTypeDao;
    @PostConstruct
    public void init(){
        Map<String, List<DicValue>> dicmap = dictionarySerivce.selectAll();
        servletContext.setAttribute("dicmap",dicmap);
        List<User> users = userMapper.selectAll();
        servletContext.setAttribute("users",users);
    }

}
