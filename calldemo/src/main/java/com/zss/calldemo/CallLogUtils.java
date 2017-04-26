/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.calldemo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;

/**
 * 获取通话记录工具类
 * 作者 zhangss
 * 实现的主要功能。
 * 创建日期 2017/4/24
 */
public class CallLogUtils {
    //从GitHub上修改内容进行测试

    /**
     * 获取通话记录
     *
     * @param context
     * @param contentResolver
     * @param isAllRecord     是否获取全部记录
     *
     * @return
     */
    public static List<CallRecord> callHistoryList(Context context, ContentResolver contentResolver, boolean
            isAllRecord, OnPermissionDeniedListener listener) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {//"没有授权"
            if (listener != null) {
                listener.onPermissionDenied();
            }
            return Collections.emptyList();
        }
        List<CallRecord> callRecordList = new ArrayList<>();

        Cursor cursor = contentResolver.query(CallLog.Calls.CONTENT_URI,//系统方式获取通讯录存储地址
                new String[] {CallLog.Calls.CACHED_NAME,//名字
                        CallLog.Calls.NUMBER,//号码
                        CallLog.Calls.TYPE,//类型
                        CallLog.Calls.DATE,//时间
                        CallLog.Calls.DURATION//时长
                }, null, null, CallLog.Calls.DEFAULT_SORT_ORDER);
        if (cursor != null && cursor.getCount() > 0) {
            CallRecord callRecord;
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                callRecord = new CallRecord();
                callRecord.setName(cursor.getString(0));
                callRecord.setNumber(cursor.getString(1));
                callRecord.setType(parseType(cursor.getString(2)));
                callRecord.setDate(parseDate(cursor.getString(3)));
                callRecord.setDuration(parseDuration(cursor.getString(4)));
                callRecordList.add(callRecord);
                if (!isAllRecord) {
                    break;
                }
            }
            cursor.close();
        }
        return callRecordList;
    }

    private static String parseDuration(String durationStr) {
        int duration = Integer.parseInt(durationStr);
        int min = duration / 60;
        int sec = duration % 60;
        return min + "分" + sec + "秒";
    }

    private static String parseDate(String dateStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(Long.parseLong(dateStr));
        return simpleDateFormat.format(date);
    }

    private static String parseType(String typeStr) {
        int type = Integer.parseInt(typeStr);
        switch (type) {
            case CallLog.Calls.INCOMING_TYPE:
                return "呼入";
            case CallLog.Calls.OUTGOING_TYPE:
                return "呼出";
            case CallLog.Calls.MISSED_TYPE:
                return "未接";
            default:
                return typeStr;
        }
    }

    public interface OnPermissionDeniedListener {
        void onPermissionDenied();
    }
}
