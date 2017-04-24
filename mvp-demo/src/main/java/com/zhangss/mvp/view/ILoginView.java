/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zhangss.mvp.view;

/**
 * View接口
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/4/20
 */
public interface ILoginView {

    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity();

    void showFailedError();

}
