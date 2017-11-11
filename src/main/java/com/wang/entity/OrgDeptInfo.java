package com.wang.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * create by DBEntityGenerator tools v1.0
 * @author huangzw 
 * @date 2016-04-16 15:21:23
 * @version 1.0
 */
@Entity
@Table(name = "org_dept_info")
public class OrgDeptInfo extends BaseEntity{
    @Id
    private Integer orgId;
    @Column(name = "ORG_TYPE")
    private Short orgType;
    @Column(name = "ADMIN_ORG_ID")
    private Integer adminOrgId;
    @Column(name = "ORDER_ID")
    private Short orderId;
    @Column(name = "ORG_NAME")
    private String orgName;
    @Column(name = "ORG_SHORT_NAME")
    private String orgShortName;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "ZIPCODE")
    private String zipcode;
    @Column(name = "CONTACT_MAN")
    private String contactMan;
    @Column(name = "CONTACT_TEL")
    private String contactTel;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "REMARK")
    private String remark;
    @Column(name = "OTHER_INFO")
    private String otherInfo;
    @Column(name = "REC_STATUS")
    private Short recStatus=1;
    @Column(name = "AUTO_DISPATCH_VEH")
    private Short autoDispatchVeh = 1;
    @Column(name = "AUTO_SIGN_FLAG")
    private Short autoSignFlag = 0;
    @Column(name = "SPECIAL_RESCUE_FLAG")
    private Short specialRescueFlag = 0;
    @Column(name = "SERVICE_START_TIME")
    private String serviceStartTime = "00:00";
    @Column(name = "SERVICE_END_TIME")
    private String serviceEndTime = "23:59";
    @Column(name = "BARGAINING_RATE")
    private Double bargainingRate = 0.0;
    @Column(name = "APP_ID")
    private String appId;
    @Column(name = "APP_SECRET")
    private String appSecret;
    @Column(name = "VIP_SCORE")
    private Integer vipScore = 0;
    @Column(name = "ONDUTY_PHONE")
    private String ondutyPhone;
    @Column(name = "ONDUTY_BEGIN_TIME")
    private Time ondutyBeginTime = Time.valueOf("17:30:00");
    @Column(name = "ONDUTY_END_TIME")
    private Time ondutyEndTime = Time.valueOf("08:30:00");
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
    @Column(name = "PRICE_PLAN_TYPE")
    private Short pricePlanType =0;
    @Column(name = "FREE_BACK_MILEAGE")
    private Integer freeBackMileage=0;//免费背车里程
    @Column(name = "TAX_RATE")
    private Double taxRate =0.0;
    @Column(name = "ORG_LEVEL")
    private Integer orgLevel;

    public Integer getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(Integer orgLevel) {
        this.orgLevel = orgLevel;
    }

    public void setOrgId(Integer orgId){
        this.orgId = orgId;
    }

    public Integer getOrgId(){
        return this.orgId;
    }

    public void setOrgType(Short orgType){
        this.orgType = orgType;
    }

    public Short getOrgType(){
        return this.orgType;
    }

    public void setAdminOrgId(Integer adminOrgId){
        this.adminOrgId = adminOrgId;
    }

    public Integer getAdminOrgId(){
        return this.adminOrgId;
    }

    public void setOrderId(Short orderId){
        this.orderId = orderId;
    }

    public Short getOrderId(){
        return this.orderId;
    }

    public void setOrgName(String orgName){
        this.orgName = orgName;
    }

    public String getOrgName(){
        return this.orgName;
    }

    public void setOrgShortName(String orgShortName){
        this.orgShortName = orgShortName;
    }

    public String getOrgShortName(){
        return this.orgShortName;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }

    public void setZipcode(String zipcode){
        this.zipcode = zipcode;
    }

    public String getZipcode(){
        return this.zipcode;
    }

    public void setContactMan(String contactMan){
        this.contactMan = contactMan;
    }

    public String getContactMan(){
        return this.contactMan;
    }

    public void setContactTel(String contactTel){
        this.contactTel = contactTel;
    }

    public String getContactTel(){
        return this.contactTel;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    public String getRemark(){
        return this.remark;
    }

    public void setOtherInfo(String otherInfo){
        this.otherInfo = otherInfo;
    }

    public String getOtherInfo(){
        return this.otherInfo;
    }

    public void setRecStatus(Short recStatus){
        this.recStatus = recStatus;
    }

    public Short getRecStatus(){
        return this.recStatus;
    }

    public void setAutoDispatchVeh(Short autoDispatchVeh){
        this.autoDispatchVeh = autoDispatchVeh;
    }

    public Short getAutoDispatchVeh(){
        return this.autoDispatchVeh;
    }

    public Short getAutoSignFlag(){
        return autoSignFlag;
    }

    public void setAutoSignFlag(Short autoSignFlag){
        this.autoSignFlag = autoSignFlag;
    }

    public Short getSpecialRescueFlag(){
        return specialRescueFlag;
    }

    public void setSpecialRescueFlag(Short specialRescueFlag){
        this.specialRescueFlag = specialRescueFlag;
    }

    public void setServiceStartTime(String serviceStartTime){
        this.serviceStartTime = serviceStartTime;
    }

    public String getServiceStartTime(){
        return this.serviceStartTime;
    }

    public void setServiceEndTime(String serviceEndTime){
        this.serviceEndTime = serviceEndTime;
    }

    public String getServiceEndTime(){
        return this.serviceEndTime;
    }

    public Double getBargainingRate(){
        return bargainingRate;
    }

    public void setBargainingRate(Double bargainingRate){
        this.bargainingRate = bargainingRate;
    }

    public void setAppId(String appId){
        this.appId = appId;
    }

    public String getAppId(){
        return this.appId;
    }

    public void setAppSecret(String appSecret){
        this.appSecret = appSecret;
    }

    public String getAppSecret(){
        return this.appSecret;
    }

    public Integer getVipScore(){
        return vipScore;
    }

    public void setVipScore(Integer vipScore){
        this.vipScore = vipScore;
    }

    public String getOndutyPhone(){
        return ondutyPhone;
    }

    public void setOndutyPhone(String ondutyPhone){
        this.ondutyPhone = ondutyPhone;
    }

    public Time getOndutyBeginTime(){
        return ondutyBeginTime;
    }

    public void setOndutyBeginTime(Time ondutyBeginTime){
        this.ondutyBeginTime = ondutyBeginTime;
    }

    public Time getOndutyEndTime(){
        return ondutyEndTime;
    }

    public void setOndutyEndTime(Time ondutyEndTime){
        this.ondutyEndTime = ondutyEndTime;
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
    
    public void setPricePlanType(Short pricePlanType){
        this.pricePlanType = pricePlanType;
    }

    public Short getPricePlanType(){
        return this.pricePlanType;
    }

    public Integer getFreeBackMileage() {
        return freeBackMileage;
    }

    public void setFreeBackMileage(Integer freeBackMileage) {
        this.freeBackMileage = freeBackMileage;
    }

    public void setTaxRate(Double taxRate){
        this.taxRate = taxRate;
    }

    public Double getTaxRate(){
        return this.taxRate;
    }
}
