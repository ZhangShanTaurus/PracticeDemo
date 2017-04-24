/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zhangss.mvp.presenter;

import java.lang.ref.WeakReference;

/**
 * Presenter接口
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/4/21
 */
public abstract class BaseLoginPresenter<V> {

    private WeakReference<V> mWeakReference;

    public void attachView(V view) {
        mWeakReference = new WeakReference<>(view);
    }

    public void detachView() {
        if (mWeakReference != null) {
            mWeakReference.clear();
            mWeakReference = null;
        }
    }

    public V getView() {
        return mWeakReference.get();
    }

    public abstract void login();

    public abstract void clear();
}
