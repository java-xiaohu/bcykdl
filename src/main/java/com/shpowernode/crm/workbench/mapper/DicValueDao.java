package com.shpowernode.crm.workbench.mapper;

import com.shpowernode.crm.workbench.bean.DicValue;

import java.util.List;

public interface DicValueDao {
    int deleteByPrimaryKey(String id);

    int insert(DicValue record);

    int insertSelective(DicValue record);

    DicValue selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DicValue record);

    int updateByPrimaryKey(DicValue record);

    List<DicValue> selectByValue(DicValue dicValue);
}