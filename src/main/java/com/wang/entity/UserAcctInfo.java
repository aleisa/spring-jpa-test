package com.wang.entity;

import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


/**
 * create by DBEntityGenerator tools v1.0
 * @author huangzw 
 * @date 2016-04-16 15:39:30
 * @version 1.0
 */
@Entity
@Table(name = "user_acct_info")
public class UserAcctInfo extends BaseEntity{
    public static final String FIELD_NAME_REAL_NAME = "realName";
    public static final String FIELD_NAME_MOBILE_PHONE = "mobilePhone";
    public static final String FIELD_NAME_CONTACT_TEL = "contactTel";
    public static final String FIELD_NAME_EMAIL = "email";
    
    public static final String FIELD_NAME_ACCT_NAME = "acctName";
    public static final String FIELD_NAME_ACCT_PWD = "acctPwd";
    
    public static final String FIELD_NAME_OPEN_ID = "thirdOpenId";
    @Id
    public String acctId;
    @Column(name = "ACCT_NAME")
    private String acctName;
    @Column(name = "ACCT_PWD")
    private String acctPwd;
    @Column(name = "REAL_NAME")
    private String realName;
    @Column(name = "SEX")
    private String sex;
    @Column(name = "BIRTHDAY")
    private String birthday;
    @Column(name = "ORG_ID")
    private Integer orgId;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "MOBILE_PHONE")
    private String mobilePhone;
    @Column(name = "CONTACT_TEL")
    private String contactTel;
    @Column(name = "ACCT_ROLE")
    private Short acctRole;
    @Column(name = "IP_LIMIT")
    private Short ipLimit = 0;
    @Column(name = "VALID_IPS")
    private String validIps;
    @Column(name = "REC_STATUS")
    private Short recStatus = 1;
    @Column(name = "FAILED_LOGIN_TIMES")
    private Short failedLoginTimes;
    @Column(name = "LOCK_STATUS")
    private Short lockStatus = 0;
    @Column(name = "LOCKED_DATE")
    private Date lockedDate;
    @Column(name = "FAILED_LOGIN_DATE")
    private Date failedLoginDate;
    @Column(name = "THIRD_OPEN_ID")
    private String thirdOpenId;
    @Column(name = "CREATE_ACCT_ID")
    private String createAcctId;
    @Column(name = "CREATE_USER_NAME")
    private String createUserName;
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Column(name = "UPDATE_ACCT_ID")
    private String updateAcctId;
    @Column(name = "UPDATE_USER_NAME")
    private String updateUserName;
    @Column(name = "UPDATE_TIME")
    private Date updateTime;
    @Column(name = "IS_ALLOW_WEB")
    private Short isAllowWeb = 0;//允许web登录
    @Column(name = "IS_ALLOW_APP")
    private Short isAllowApp = 0;// 允许app登录
    @Column(name = "IS_ALLOW_WEIXIN")
    private Short isAllowWeixin = 0;//允许微信登录

    public void setAcctId(String acctId){
        this.acctId = acctId;
    }

    public String getAcctId(){
        return this.acctId;
    }

    public void setAcctName(String acctName){
        this.acctName = acctName;
    }

    public String getAcctName(){
        return this.acctName;
    }

    public void setAcctPwd(String acctPwd){
        this.acctPwd = acctPwd;
    }

    public String getAcctPwd(){
        return this.acctPwd;
    }

    public void setRealName(String realName){
        this.realName = realName;
    }

    public String getRealName(){
        return this.realName;
    }

    public void setSex(String sex){
        this.sex = sex;
    }

    public String getSex(){
        return this.sex;
    }

    public void setBirthday(String birthday){
        this.birthday = birthday;
    }

    public String getBirthday(){
        return this.birthday;
    }

    public void setOrgId(Integer orgId){
        this.orgId = orgId;
    }

    public Integer getOrgId(){
        return this.orgId;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setMobilePhone(String mobilePhone){
        this.mobilePhone = mobilePhone;
    }

    public String getMobilePhone(){
        return this.mobilePhone;
    }

    public void setContactTel(String contactTel){
        this.contactTel = contactTel;
    }

    public String getContactTel(){
        return this.contactTel;
    }

    public void setAcctRole(Short acctRole){
        this.acctRole = acctRole;
    }

    public Short getAcctRole(){
        return this.acctRole;
    }

    public void setIpLimit(Short ipLimit){
        this.ipLimit = ipLimit;
    }

    public Short getIpLimit(){
        return this.ipLimit;
    }

    public void setValidIps(String validIps){
        this.validIps = validIps;
    }

    public String getValidIps(){
        return this.validIps;
    }

    public void setRecStatus(Short recStatus){
        this.recStatus = recStatus;
    }

    public Short getRecStatus(){
        return this.recStatus;
    }

    public void setFailedLoginTimes(Short failedLoginTimes){
        this.failedLoginTimes = failedLoginTimes;
    }

    public Short getFailedLoginTimes(){
        return this.failedLoginTimes;
    }

    public void setLockStatus(Short lockStatus){
        this.lockStatus = lockStatus;
    }

    public Short getLockStatus(){
        return this.lockStatus;
    }

    public void setLockedDate(Date lockedDate){
        this.lockedDate = lockedDate;
    }

    public Date getLockedDate(){
        return this.lockedDate;
    }

    public void setFailedLoginDate(Date failedLoginDate){
        this.failedLoginDate = failedLoginDate;
    }

    public Date getFailedLoginDate(){
        return this.failedLoginDate;
    }

    public void setThirdOpenId(String thirdOpenId){
        this.thirdOpenId = thirdOpenId;
    }

    public String getThirdOpenId(){
        return this.thirdOpenId;
    }

    public void setCreateAcctId(String createAcctId){
        this.createAcctId = createAcctId;
    }

    public String getCreateAcctId(){
        return this.createAcctId;
    }

    public void setCreateUserName(String createUserName){
        this.createUserName = createUserName;
    }

    public String getCreateUserName(){
        return this.createUserName;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Date getCreateTime(){
        return this.createTime;
    }

    public void setUpdateAcctId(String updateAcctId){
        this.updateAcctId = updateAcctId;
    }

    public String getUpdateAcctId(){
        return this.updateAcctId;
    }

    public void setUpdateUserName(String updateUserName){
        this.updateUserName = updateUserName;
    }

    public String getUpdateUserName(){
        return this.updateUserName;
    }

    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    public Date getUpdateTime(){
        return this.updateTime;
    }

    public Short getIsAllowWeb(){
        return isAllowWeb;
    }

    public void setIsAllowWeb(Short isAllowWeb){
        this.isAllowWeb = isAllowWeb;
    }

    public Short getIsAllowApp(){
        return isAllowApp;
    }

    public void setIsAllowApp(Short isAllowApp){
        this.isAllowApp = isAllowApp;
    }

    public Short getIsAllowWeixin(){
        return isAllowWeixin;
    }

    public void setIsAllowWeixin(Short isAllowWeixin){
        this.isAllowWeixin = isAllowWeixin;
    }
    
}
