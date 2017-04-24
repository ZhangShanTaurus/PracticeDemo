/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zhangss.mvp.biz;

import com.zhangss.mvp.model.User;

/**
 * 业务逻辑层（Model层）
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/4/20
 */
public class UserBiz implements IUserBiz {

    @Override
    public void login(final String userName, final String password, final OnLoginListener onLoginListener) {
        //开启线程模拟耗时操作
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (userName.equals("zss") && password.equals("123")) {
                    User user = new User();
                    user.setUsername(userName);
                    user.setPassword(password);
                    onLoginListener.loginSuccess(user);
                } else {
                    onLoginListener.loginFailed();
                }
            }
        }.start();
    }
}
