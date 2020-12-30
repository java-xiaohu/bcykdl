package com.shpowernode.crm.workbench.service;

import com.shpowernode.crm.workbench.bean.DicValue;

import java.util.List;
import java.util.Map;

public interface DictionarySerivce {
    Map<String, List<DicValue>> selectAll();
}
