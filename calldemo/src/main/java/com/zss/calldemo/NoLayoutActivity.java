/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.calldemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * 不使用xml布局的Activity
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/5/24
 */
public class NoLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = NoLayoutActivity.class.getSimpleName();

    public static final String KEY_DATA = "key_data";

    private CallRecord callRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getRootView());
        initData(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState()");
        outState.putSerializable(KEY_DATA, callRecord);
    }

    void initData(Bundle bundle) {
        if (bundle == null) {
            if (getIntent() != null) {
                callRecord = (CallRecord) getIntent().getSerializableExtra(KEY_DATA);
                Log.d(TAG, "getIntent()");
            }
        } else {
            callRecord = (CallRecord) bundle.getSerializable(KEY_DATA);
            Log.d(TAG, "bundle ()");
        }

        if (callRecord != null) {
            Log.d(TAG, callRecord.toString());
        } else {
            Log.d(TAG, "callRecord is null");
        }
    }

    private static final int BUTTON_ID = R.id.btn_id;

    private View getRootView() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.HORIZONTAL);
        root.setBackgroundColor(Color.parseColor("#0000ff"));
        FrameLayout.LayoutParams rootLayoutParams =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
        root.setLayoutParams(rootLayoutParams);
        ImageView img = new ImageView(this);
        img.setId(BUTTON_ID);
        img.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        img.setOnClickListener(this);
        img.setImageDrawable(getResources().getDrawable(R.drawable.ic_3d_rotation_black_24dp));
        root.addView(img);
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
