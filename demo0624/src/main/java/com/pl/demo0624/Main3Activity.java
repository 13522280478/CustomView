package com.pl.demo0624;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class Main3Activity extends AppCompatActivity {

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            Log.w("tagg","Callback 收到消息");
            return true;
        }
    }){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Log.w("tagg","handleMessage 收到消息");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Log.w("tagg","onCreate ");

        int isBoo = 0;
         Log.w("tagg", " isBoo 1 : "+isBoo);
        isBoo ^= 0;
        Log.w("tagg", " isBoo 2 : "+isBoo);
        isBoo ^= 1;
        Log.w("tagg", " isBoo 3 : "+isBoo);
        isBoo ^= 0;
        Log.w("tagg", " isBoo 4 : "+isBoo);
        isBoo ^= 1;
        Log.w("tagg", " isBoo 5 : "+isBoo);


//       Message message =  Message.obtain(handler, new Runnable() {
//            @Override
//            public void run() {
//                Log.w("tagg","message 收到消息");
//            }
//        });
////        handler.sendMessage(message);
//        handler.sendEmptyMessage(1);
    }
}
