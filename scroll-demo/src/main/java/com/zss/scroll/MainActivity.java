/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.scroll;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Scroller;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "DNF";
    private View topLayout;
    private Button showBtn;
    private Button hidnBtn;
    private ListView listView;
    private Scroller scroller;

    private int screenWidth;

    private List<String> getDisplayData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("测试" + i);
        }
        return data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scroller = new Scroller(this);
        screenWidth = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        initView();
    }

    private void initView() {
        topLayout = findViewById(R.id.top_layout);
        showBtn = (Button) findViewById(R.id.button_visible);
        hidnBtn = (Button) findViewById(R.id.button_hide);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, getDisplayData()));
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private SparseArray<ItemRecord> recordSp = new SparseArray<>(0);
            private int mCurrentFirstVisibleItem = 0;

            @Override
            public void onScrollStateChanged(AbsListView absListView, int paramInt) {
                //do nothing
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                mCurrentFirstVisibleItem = firstVisibleItem;
                View firstView = absListView.getChildAt(0);
                if (null != firstView) {
                    ItemRecord itemRecord = recordSp.get(firstVisibleItem);
                    if (null == itemRecord) {
                        itemRecord = new ItemRecord();
                    }
                    itemRecord.height = firstView.getHeight();
                    itemRecord.top = firstView.getTop();
                    recordSp.append(firstVisibleItem, itemRecord);

                    int h = getScrollY();//滚动距离
                    if (h < 30) {
                        int topBottom = topLayout.getBottom();
                        topLayout.layout(0, 0 - h, screenWidth, topBottom - h);
//                        topLayout.scrollTo(0, h);
                    }
                    Log.d(TAG, "h = " + h);
                }
            }

            private int getScrollY() {
                int height = 0;
                for (int i = 0; i < mCurrentFirstVisibleItem; i++) {
                    ItemRecord itemRecod = recordSp.get(i);
                    height += itemRecod.height;
                }
                ItemRecord itemRecod = recordSp.get(mCurrentFirstVisibleItem);
                if (null == itemRecod) {
                    itemRecod = new ItemRecord();
                }
                return height - itemRecod.top;
            }

            class ItemRecord {
                int height = 0;
                int top = 0;
            }
        });
    }
}
