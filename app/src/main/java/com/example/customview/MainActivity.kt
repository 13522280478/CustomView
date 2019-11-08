package com.example.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customview.utils.DimensionUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        DimensionUtils.dp2Px(1)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
