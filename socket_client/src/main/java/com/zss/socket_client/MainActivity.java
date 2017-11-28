/*
 * Copyright (C) 2017 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.zss.socket_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int SOCKET_PORT = 19890;
    private static final String IP = "172.29.0.70";
    private static final String KEY_CONTENT = "key_content";

    private TextView textView;
    private MyHandler myHandler = new MyHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String s = BuildConfig.Name;
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        initSocket();
    }

    private void initSocket() {
        Socket socket1 = null;
        Socket socket2 = null;
        try {
            socket1 = new Socket(IP, SOCKET_PORT);
            socket2 = new Socket(IP, SOCKET_PORT);
            new Thread(new ClientThread(socket1)).start();
            new Thread(new ClientThread(socket2)).start();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket1 != null) {
                try {
                    socket1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket2 != null) {
                try {
                    socket2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ClientThread implements Runnable {
        BufferedReader bufferedReader = null;

        ClientThread(Socket socket) throws IOException {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        @Override
        public void run() {
            try {
                String content = null;
                while (bufferedReader != null && (content = bufferedReader.readLine()) != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(KEY_CONTENT, content);
                    Message msg = new Message();
                    msg.setData(bundle);            //将数据封装到Message对象中
                    myHandler.sendMessage(msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String content = bundle.getString(KEY_CONTENT);
            textView.setText(content);
        }
    }
}
