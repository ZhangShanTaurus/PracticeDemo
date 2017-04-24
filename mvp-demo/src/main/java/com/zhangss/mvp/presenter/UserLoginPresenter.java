/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zhangss.mvp.presenter;

import com.zhangss.mvp.biz.IUserBiz;
import com.zhangss.mvp.biz.OnLoginListener;
import com.zhangss.mvp.biz.UserBiz;
import com.zhangss.mvp.model.User;
import com.zhangss.mvp.view.ILoginView;

import android.os.Handler;
import android.os.Looper;

/**
 * Presenter:处理Model层与View层交互逻辑
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/4/20
 */
public class UserLoginPresenter extends BaseLoginPresenter {

    private IUserBiz userBiz;
    private ILoginView loginView;

    private Handler handler = new Handler(Looper.getMainLooper());

    public UserLoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
        this.userBiz = new UserBiz();
    }

    @Override
    public void login() {
        loginView.showLoading();
        userBiz.login(loginView.getUserName(), loginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(User user) {
                //需要在UI线程执行
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.hideLoading();
                        loginView.toMainActivity();
                    }
                });
            }

            @Override
            public void loginFailed() {
                //需要在UI线程执行
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.hideLoading();
                        loginView.showFailedError();
                    }
                });
            }
        });
    }

    @Override
    public void clear() {
        loginView.clearUserName();
        loginView.clearPassword();
    }
}
