/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.calldemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * 不使用xml布局的Activity
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/5/24
 */
public class NoLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getRootView());
    }

    private static final int BUTTON_ID = R.id.btn_id;

    private View getRootView() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.HORIZONTAL);
        root.setBackgroundColor(Color.parseColor("#0000ff"));
        FrameLayout.LayoutParams rootLayoutParams =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
        root.setLayoutParams(rootLayoutParams);
        Button button = new Button(this);
        button.setId(BUTTON_ID);
        button.setLayoutParams(new LinearLayout.LayoutParams(200, 120));
        button.setText("Hello");
        button.setOnClickListener(this);
        root.addView(button);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case BUTTON_ID:
                Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
