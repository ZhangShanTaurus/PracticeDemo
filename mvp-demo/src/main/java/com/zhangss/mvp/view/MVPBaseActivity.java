/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zhangss.mvp.view;

import com.zhangss.mvp.presenter.BaseLoginPresenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * MVPBaseActivity
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/4/21
 */
public abstract class MVPBaseActivity<V,P extends BaseLoginPresenter<V>> extends AppCompatActivity {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract P createPresenter();
}
