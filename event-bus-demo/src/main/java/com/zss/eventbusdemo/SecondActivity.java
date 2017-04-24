/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.eventbusdemo;

import org.greenrobot.eventbus.EventBus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 类名
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/4/20
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.btn_go);
        button.setText(R.string.to_first);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSyncEvent dataSyncEvent = new DataSyncEvent();
                dataSyncEvent.setCount(10);
                dataSyncEvent.setMsg("HelloWorld");
                //发送事件
                EventBus.getDefault().post(dataSyncEvent);
            }
        });
    }
}
