package com.shpowernode.crm.workbench.service.impl;

import com.shpowernode.crm.base.constants.CrmExceptionEnum;
import com.shpowernode.crm.base.exception.CrmException;
import com.shpowernode.crm.base.util.UUIDUtil;
import com.shpowernode.crm.workbench.bean.Clue;
import com.shpowernode.crm.workbench.mapper.ClueDao;
import com.shpowernode.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueDao clueDao;
    @Override
    public void insertclue(Clue clue) {
        clue.setId(UUIDUtil.getUUID());
        int i = clueDao.insertSelective(clue);
        if (i==0){
            throw new CrmException(CrmExceptionEnum.CREATE_CLUE);
        }

    }

    @Override
    public List<Clue> queryAll(Clue clue) {

        return clueDao.selectAll(clue);



    }
}
