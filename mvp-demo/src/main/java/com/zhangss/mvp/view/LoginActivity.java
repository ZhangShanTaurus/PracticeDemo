/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zhangss.mvp.view;

import com.zhangss.mvp.MainActivity;
import com.zhangss.mvp.R;
import com.zhangss.mvp.presenter.BaseLoginPresenter;
import com.zhangss.mvp.presenter.UserLoginPresenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * 类名
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/4/20
 */
public class LoginActivity extends MVPBaseActivity implements ILoginView, View.OnClickListener {

    private EditText mUsernameEdit;
    private EditText mPasswordEdit;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPresenter = createPresenter();
        initView();
    }

    private void initView() {
        Button mBtnLogin = (Button) findViewById(R.id.login_btn);
        mBtnLogin.setOnClickListener(this);
        Button mBtnClear = (Button) findViewById(R.id.clear_btn);
        mBtnClear.setOnClickListener(this);
        mUsernameEdit = (EditText) findViewById(R.id.username_edit);
        mPasswordEdit = (EditText) findViewById(R.id.pass_word_edit);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                mPresenter.login();
                break;
            case R.id.clear_btn:
                mPresenter.clear();
                break;
        }
    }

    @Override
    public BaseLoginPresenter createPresenter() {
        return new UserLoginPresenter(this);
    }

    @Override
    public String getUserName() {
        return mUsernameEdit.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPasswordEdit.getText().toString();
    }

    @Override
    public void clearUserName() {
        mUsernameEdit.setText("");
    }

    @Override
    public void clearPassword() {
        mPasswordEdit.setText("");
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity() {
        Toast.makeText(this, "Login Success !", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this, "Login Failed !", Toast.LENGTH_SHORT).show();
    }
}
