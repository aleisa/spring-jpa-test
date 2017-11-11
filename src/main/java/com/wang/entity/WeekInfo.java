package com.wang.entity;

import java.util.Date;

public class WeekInfo extends BaseEntity{
    private int year;
    private int week;
    private Date begin;
    private Date end;

    public int getYear(){
        return year;
    }

    public void setYear(int year){
        this.year = year;
    }

    public int getWeek(){
        return week;
    }

    public void setWeek(int week){
        this.week = week;
    }

    public Date getBegin(){
        return begin;
    }

    public void setBegin(Date begin){
        this.begin = begin;
    }

    public Date getEnd(){
        return end;
    }

    public void setEnd(Date end){
        this.end = end;
    }
}
