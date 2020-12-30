package com.shpowernode.crm.workbench.service;

import com.shpowernode.crm.workbench.bean.Clue;

import java.util.List;

public interface ClueService {
    void insertclue(Clue clue);

    List<Clue> queryAll(Clue clue);
}
