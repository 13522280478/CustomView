package com.pl.demo0624.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.pl.demo0624.R;
import com.pl.demo0624.weather.bean.CurrentDisplay;
import com.pl.demo0624.weather.bean.StudentData;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Log.w("tagg","我的测试");

        StudentData studentData = new StudentData();
        CurrentDisplay currentDisplay = new CurrentDisplay(studentData);

        studentData.joinStudent("小王",20);
        studentData.joinStudent("小李",21);
        studentData.joinStudent("小张",19);

    }
}
