/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.calldemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 类名
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/9/6
 */
public class InflateDemo extends AppCompatActivity {

    private View inflateView;
    private LinearLayout linearLayout;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflate_demo);
        inflateView = LayoutInflater.from(this).inflate(R.layout.layout_inflate, null, false);
        textView = (TextView) inflateView.findViewById(R.id.tv_info);
        linearLayout = (LinearLayout) findViewById(R.id.container);
        if (inflateView != null) {
            linearLayout.addView(inflateView);
        }
        Button button = (Button) findViewById(R.id.test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inflateView != null) {
                    if (inflateView.getVisibility() == View.VISIBLE) {
                        inflateView.setVisibility(View.GONE);
                    } else {
                        inflateView.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }
}
