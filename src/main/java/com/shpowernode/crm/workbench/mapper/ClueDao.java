package com.shpowernode.crm.workbench.mapper;

import com.shpowernode.crm.workbench.bean.Clue;

import java.util.List;

public interface ClueDao {
    int deleteByPrimaryKey(String id);

    int insert(Clue record);

    int insertSelective(Clue record);

    Clue selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Clue record);

    int updateByPrimaryKey(Clue record);

    List<Clue> selectAll(Clue clue);
}