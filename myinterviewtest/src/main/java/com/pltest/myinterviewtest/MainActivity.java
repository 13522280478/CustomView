package com.pltest.myinterviewtest;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecycler;
    ProgressView mProgress;
    Handler handler;
    int itemWidth = 0;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycler = findViewById(R.id.recycler);
        mProgress = findViewById(R.id.progress);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecycler.setLayoutManager(linearLayoutManager);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(" item : " + i);
        }

        MyAdapter adapter = new MyAdapter(list, new MyAdapter.OnChange() {
            @Override
            public void chagne(int value) {
                itemWidth = value;
                Log.w("tagg","itemWidth : "+itemWidth);
            }
        });

        mRecycler.setAdapter(adapter);

//        handler = new Handler() {
//            @Override
//            public void handleMessage(@NonNull Message msg) {
//                super.handleMessage(msg);
//                handler.sendEmptyMessage(1);
//                if (msg.what < 100) {
//                    mProgress.setChangeValue(msg.what);
//                    change(msg.what);
//                }
//            }
//        };

//        handler.sendEmptyMessage(1);
//        Log.w("MainActivity", "onCreate");
//        String s3 = "abc";
//        String s2 = new String("abc");
//        String s1 = "abc";
//
//        Log.w("tagg", (s1 == s2) + "");
//        Log.w("tagg", (s1 == s3) + "");
//        Log.w("tagg", (s3 == s2) + "");

        mRecycler.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
               int x =  getScollYDistance((LinearLayoutManager) mRecycler.getLayoutManager());
                Log.w("tagg", " x  : " + x);
                float changeVa =   (x/1800f)*100f;
                mProgress.setChangeValue((int) changeVa);
            }
        });
    }

    public int getScollYDistance(LinearLayoutManager layoutManager) {
        int position = layoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        return (position) * itemHeight - firstVisiableChildView.getTop();
    }

    public void change(int what) {
        handler.sendEmptyMessageDelayed(++what, 50);
    }

    public void clickAct(View v) {
        startActivity(new Intent(this, MainActivity2.class));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.w("MainActivity", "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w("MainActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("MainActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("MainActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("MainActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("MainActivity", "onDestroy");
    }
}
