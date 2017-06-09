/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.calldemo;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mInfoText;
    private TextView mDurationText;
    private Task mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ClassCastUtil.addData("Hello World");
        List<String> list = ClassCastUtil.getData();
        Log.d(TAG, list.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTask = new Task(this);
        mTask.execute(getContentResolver());
    }

    @Override
    protected void onPause() {
        super.onPause();
        cancelTask();
    }

    private void cancelTask() {
        boolean isCancel = mTask != null
                && !mTask.isCancelled()
                && mTask.getStatus() == AsyncTask.Status.RUNNING;
        if (isCancel) {
            mTask.cancel(true);
            mTask = null;
        }
    }

    private void initView() {
        mInfoText = (TextView) findViewById(R.id.tv_info);
        mDurationText = (TextView) findViewById(R.id.tv_duration);
        final EditText phoneNumber = (EditText) findViewById(R.id.edit_phone_number);
        Button callBtn = (Button) findViewById(R.id.btn_call);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(phoneNumber.getText().toString());
            }
        });
//        ObjectAnimator.ofFloat(mInfoText, "scaleY",0f, 4.0f).setDuration(1000).start();

    }

    private void call(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, NoLayoutActivity.class));
            return;
        }
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri uri = Uri.parse("tel:" + phoneNumber);
        intent.setData(uri);
        startActivity(intent);
    }

    /**
     * 异步任务获取通讯录信息
     */
    static class Task extends AsyncTask<ContentResolver, Void, List<CallRecord>> {
        WeakReference<MainActivity> mWeakReference;

        public Task(MainActivity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        protected List<CallRecord> doInBackground(ContentResolver... params) {
            if (null == mWeakReference.get() || isCancelled()) {
                return Collections.emptyList();
            }
            return CallLogUtils.callHistoryList(mWeakReference.get(), params[0], false, listener);
        }

        @Override
        protected void onPostExecute(List<CallRecord> records) {
            MainActivity activity = mWeakReference.get();
            if (null == activity) {
                return;
            }
            if (records != null && records.size() > 0) {
                activity.mDurationText.setText(records.get(0).getDuration());
                activity.mInfoText.setText(records.toString());
            } else {
                activity.mInfoText.setText("未授权或无记录");
            }
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                }
            };
        }

        CallLogUtils.OnPermissionDeniedListener listener = new CallLogUtils.OnPermissionDeniedListener() {
            @Override
            public void onPermissionDenied() {
                mWeakReference.get().handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mWeakReference.get(), "请授与权限", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void givePermissionInfo() {
                mWeakReference.get().handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mWeakReference.get(), "请给出使用该权限的理由", Toast.LENGTH_SHORT).show();
                        //测试编码
                    }
                });
            }
        };
    }

    Handler handler = new Handler();

}
