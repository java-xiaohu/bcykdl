package com.shpowernode.crm.workbench.mapper;

import com.shpowernode.crm.workbench.bean.DicType;

import java.util.List;

public interface DicTypeDao {
    int deleteByPrimaryKey(String code);

    int insert(DicType record);

    int insertSelective(DicType record);

    DicType selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(DicType record);

    int updateByPrimaryKey(DicType record);

    List<DicType> selectALL();
}