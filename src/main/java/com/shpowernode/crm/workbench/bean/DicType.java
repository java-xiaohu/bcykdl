package com.shpowernode.crm.workbench.bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * tbl_dic_type
 * @author 
 */
@Component
public class DicType implements Serializable {
    /**
     * 编码是主键，不能为空，不能含有中文。
     */
    private String code;

    private String name;

    private String description;

    private static final long serialVersionUID = 1L;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DicType{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}