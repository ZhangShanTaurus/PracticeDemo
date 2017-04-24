/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.calldemo;

/**
 * 通话记录实体
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/4/24
 */
public class CallRecord {
    private String name;
    private String number;
    private String type;
    private String date;
    private String duration;

    public CallRecord() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "CallRecord{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
