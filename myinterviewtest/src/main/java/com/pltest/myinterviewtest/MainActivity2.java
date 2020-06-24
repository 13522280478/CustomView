package com.pltest.myinterviewtest;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w("MainActivity2","onCreate");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.w("MainActivity2","onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w("MainActivity2","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("MainActivity2","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("MainActivity2","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("MainActivity2","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("MainActivity2","onDestroy");
    }
}
