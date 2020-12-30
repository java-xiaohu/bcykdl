package com.shpowernode.crm.workbench.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shpowernode.crm.base.bean.ResultVo;
import com.shpowernode.crm.base.exception.CrmException;
import com.shpowernode.crm.workbench.bean.Clue;
import com.shpowernode.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ClueController {
    @Autowired
    private ClueService clueService;

    //添加线索
    @RequestMapping("/clue/add")
    @ResponseBody
    public ResultVo addclue(Clue clue){
        ResultVo<Clue> clueResultVo = new ResultVo<>();
        try {
            clueService.insertclue(clue);
            clueResultVo.setMess("添加线索成功");
            clueResultVo.setOk(true);

        }catch (CrmException e){
            e.printStackTrace();
            clueResultVo.setMess(e.getMessage());
            clueResultVo.setOk(false);

        }
        return clueResultVo;
    }

    //查询线索
    @RequestMapping("/clue/queryAll")
    @ResponseBody
    public ResultVo queryAll(Integer pageNum, Integer pageSize, Clue clue, HttpSession session){
        ResultVo<Clue> clueResultVo = new ResultVo<>();
        PageHelper.startPage(pageNum,pageSize);
        List<Clue> clueList=clueService.queryAll(clue);
        PageInfo<Clue> cluePageInfo = new PageInfo<>(clueList);
        clueResultVo.setPageInfo(cluePageInfo);
        session.setAttribute("pageinfo",cluePageInfo);
        return clueResultVo;
    }

}
