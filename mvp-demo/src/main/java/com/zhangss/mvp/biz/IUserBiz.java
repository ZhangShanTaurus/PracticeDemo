/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zhangss.mvp.biz;

/**
 * 类名
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/4/19
 */
public interface IUserBiz {

    void login(String userName, String password, OnLoginListener onLoginListener);
}
