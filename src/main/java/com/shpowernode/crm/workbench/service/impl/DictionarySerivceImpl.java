package com.shpowernode.crm.workbench.service.impl;

import com.shpowernode.crm.workbench.bean.DicType;
import com.shpowernode.crm.workbench.bean.DicValue;
import com.shpowernode.crm.workbench.mapper.DicTypeDao;
import com.shpowernode.crm.workbench.mapper.DicValueDao;
import com.shpowernode.crm.workbench.service.DictionarySerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DictionarySerivceImpl implements DictionarySerivce {
    @Autowired
    private DicTypeDao dicTypeDao;
    @Autowired
    private DicValueDao dicValueDao;

    @Override
    public Map<String, List<DicValue>> selectAll() {

        List<DicType> dicTypeList=dicTypeDao.selectALL();
        HashMap<String, List<DicValue>> map = new HashMap<>();
        for (DicType dicType : dicTypeList) {
            DicValue dicValue = new DicValue();
            dicValue.setTypecode(dicType.getCode());
            List<DicValue> dicValueList=dicValueDao.selectByValue(dicValue);
            System.out.println("value值="+dicValueList);
            map.put(dicType.getCode(),dicValueList);
        }
        return map;
    }
}
