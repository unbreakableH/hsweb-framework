package org.hsweb.web.bean.po.config;

import com.alibaba.fastjson.JSON;
import org.hsweb.web.bean.po.GenericPo;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * 系统配置
 * Created by generator
 */
public class Config extends GenericPo<String> {

    private static final long serialVersionUID = 5328848488856425388L;

    //备注
    private String remark;

    //配置内容
    private String content;

    //创建日期
    private java.util.Date createDate;

    //最后一次修改日期
    private java.util.Date updateDate;

    //配置类型: properties,json
    private String type;

    /**
     * 获取 备注
     *
     * @return String 备注
     */
    public String getRemark() {
        if (this.remark == null)
            return "";
        return this.remark;
    }

    /**
     * 设置 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 配置内容
     *
     * @return String 配置内容
     */
    public String getContent() {
        if (this.content == null)
            return "";
        return this.content;
    }

    /**
     * 设置 配置内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取 创建日期
     *
     * @return java.util.Date 创建日期
     */
    public java.util.Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 设置 创建日期
     */
    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取 最后一次修改日期
     *
     * @return java.util.Date 最后一次修改日期
     */
    public java.util.Date getUpdateDate() {
        return this.updateDate;
    }

    /**
     * 设置 最后一次修改日期
     */
    public void setUpdateDate(java.util.Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<Object, Object> toMap() {
        switch (type) {
            case "properties":
                Properties properties = new Properties();
                try {
                    properties.load(new StringReader(getContent()));
                } catch (IOException e) {
                }
                return properties;
            case "json":
                if (getContent().trim().startsWith("[")) {
                    Map<Object, Object> map = new LinkedHashMap<>();
                    List<Map> arr = JSON.parseArray(getContent(), Map.class);
                    for (int i = 0; i < arr.size(); i++) {
                        map.put(String.valueOf(i), arr.get(i));
                    }
                    return map;
                }
                return JSON.parseObject(getContent(), Map.class);
            default:
                return new HashMap<>();
        }
    }
}
