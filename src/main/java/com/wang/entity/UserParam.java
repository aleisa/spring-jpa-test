package com.wang.entity;

/**
 * Created by 王欣宇 on 2017/11/10.
 */
public class UserParam extends BaseEntity {
    private String realName;

    private String orgName;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
