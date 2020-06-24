package com.example.touch.touch1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.touch.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ViewGroup2 viewGroup1 = findViewById(R.id.viewGroup2);
        viewGroup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("","onClick");
            }
        });
    }
}
