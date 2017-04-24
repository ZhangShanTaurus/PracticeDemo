/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.eventbusdemo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mInfoText;
    private StringBuilder mInfoStr = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册EventBus
        EventBus.getDefault().register(this);

        mInfoText = (TextView) findViewById(R.id.info_text);
        Button button = (Button) findViewById(R.id.btn_go);
        button.setText(R.string.to_second);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }

    @Subscribe
    public void onEventMainThread(DataSyncEvent event) {
        mInfoStr.append(event.getCount()).append(event.getMsg()).append("\n");
        mInfoText.setText(mInfoStr);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解注册EventBus
        EventBus.getDefault().unregister(this);
    }
}
