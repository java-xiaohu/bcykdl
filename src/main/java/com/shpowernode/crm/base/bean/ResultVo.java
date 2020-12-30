package com.shpowernode.crm.base.bean;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.base.bean
 * @Description: java类作用描述
 * @Author: admin
 * @CreateDate: 2020/12/18 11:31
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class ResultVo<T> {

   /* private int page;//当前页
    private int pageSize;//每页记录数
    private int totalPages;//总页数
    private long totalRows;//总记录数
    private List<T> dataList;//每页的数据*/

    private boolean isOk;//操作是否成功
    private String mess;//代表后台给前台响应的信息

    private PageInfo<T> pageInfo;

    //后台返回单个对象
    T t;

    //后台返回多个对象
    List<T> ts;

    @Override
    public String toString() {
        return "ResultVo{" +
                "isOk=" + isOk +
                ", mess='" + mess + '\'' +
                ", pageInfo=" + pageInfo +
                ", t=" + t +
                ", ts=" + ts +
                '}';
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public List<T> getTs() {
        return ts;
    }

    public void setTs(List<T> ts) {
        this.ts = ts;
    }

    public PageInfo<T> getPageInfo() {
        return pageInfo;
    }

    public ResultVo() {
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }
    public void setPageInfo(PageInfo<T> pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getMess() {
        return mess;
    }

    public ResultVo(boolean isOk, String mess) {
        this.isOk = isOk;
        this.mess = mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }
}
