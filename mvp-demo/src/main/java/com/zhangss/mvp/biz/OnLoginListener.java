/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zhangss.mvp.biz;

import com.zhangss.mvp.model.User;

/**
 * 类名
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/4/20
 */
public interface OnLoginListener {

    /**
     * 登录成功
     *
     * @param user 用户信息
     */
    void loginSuccess(User user);

    /**
     * 登录失败
     */
    void loginFailed();

}
