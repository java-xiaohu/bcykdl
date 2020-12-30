package com.shpowernode.crm.base.constants;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.base.constants
 * @Description: java类作用描述
 * @Author: admin
 * @CreateDate: 2020/12/17 11:29
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public enum  CrmExceptionEnum {

    LOGIN_ACCOUNT_EXCEPTION("001","用户名或密码错误！"),
    LOGIN_EXPIRE_EXCEPTION("001","账户已失效！"),
    LOGIN_LOCK_EXCEPTION("001","账户被锁定，请联系管理员！"),
    LOGIN_IP_EXCEPTION("001","不允许的IP地址！"),

    ACTIVITY_CREATE("002","添加市场活动失败！"),
    ACTIVITY_UPDATE("002","修改市场活动失败！"),
    ACTIVITY_DELETE("002","删除市场活动失败！"),


    ACTIVITYREMARK_UPDATE("003","修改备注失败"),
    ACTIVITYREMARK_DELETE("003","删除备注失败！"),
    ACTIVITYREMARK_INSERT("003","添加备注失败！"),

    USER_UPLOAD("004","上传文件失败！"),


    CREATE_CLUE("005","创建线索失败！");








    private String code;//业务状态码，因为项目复杂的时候，需要把业务细分

    private String mess;

    CrmExceptionEnum(String code, String mess) {
        this.code = code;
        this.mess = mess;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }}
