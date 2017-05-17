/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.calldemo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * ClassCastException测试工具类
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/5/17
 */
public class ClassCastUtil {

    private static List<String> stringList = new LinkedList<>();

    public static void addData(String data) {
        stringList.add(data);
    }

    public static ArrayList<String> getData() {
        if (stringList instanceof ArrayList) {//类型转换检查，避免产生ClassCastException
            return (ArrayList<String>) stringList;
        } else {
            return new ArrayList<>(stringList);
        }
    }
}
