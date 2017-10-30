/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.calldemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 类名
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/8/29
 */
public class TestAddViewActivity extends AppCompatActivity {

    private LinearLayout mRoot;
    private TextView mTextView;
    private int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_view_layout);
        mRoot = (LinearLayout) findViewById(R.id.root);

        Button testBtn = (Button) findViewById(R.id.test);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestAddViewActivity.this, getPersonName(), Toast.LENGTH_SHORT).show();
            }
        });

        Button changeBtn = (Button) findViewById(R.id.change);
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                Person person = new Person();
                person.name = "张山" + count;
                initPerson(person);
            }
        });
    }

    void test() {
        if (mRoot == null) {
            return;
        }
        if (mTextView == null) {
            mTextView = new TextView(this);
            mTextView.setText("Hello World");
            mTextView.setTextColor(Color.parseColor("#000000"));
            mRoot.addView(mTextView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        mTextView.setText("点击" + count);
    }

    Person person;

    static class Person {
        String name;
    }

    private void initPerson(Person p) {
        person = p;
    }

    private String getPersonName() {
        return person.name;
    }
}
