package com.shpowernode.crm.base.exception;

import com.shpowernode.crm.base.constants.CrmExceptionEnum;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.base.exception
 * @Description: java类作用描述
 * @Author: admin
 * @CreateDate: 2020/12/17 11:28
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class CrmException extends RuntimeException {

    private CrmExceptionEnum crmExceptionEnum;

    public CrmException(CrmExceptionEnum crmExceptionEnum) {
        //因为如果想获取到堆栈中的异常信息，必须得调用父类的构造方法
        super(crmExceptionEnum.getMess());
        this.crmExceptionEnum = crmExceptionEnum;
    }
}
