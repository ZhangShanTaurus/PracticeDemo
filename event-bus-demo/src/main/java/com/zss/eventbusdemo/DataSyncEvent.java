/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.eventbusdemo;

/**
 * 数据同步事件
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/4/20
 */
public class DataSyncEvent {

    private int mCount;

    private String mMsg;

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String mMsg) {
        this.mMsg = mMsg;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int mCount) {
        this.mCount = mCount;
    }

    @Override
    public String toString() {
        return "DataSyncEvent{" +
                "mCount=" + mCount +
                ", mMsg='" + mMsg + '\'' +
                '}';
    }
}
