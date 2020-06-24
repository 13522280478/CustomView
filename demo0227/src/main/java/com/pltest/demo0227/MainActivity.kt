package com.pltest.demo0227

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //        findViewById(R.id.btStartAct).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, SecondActivity.class));
//            }
//        });
        val dog: Animal = Dog()
        val bird: Animal = Bird()
        dog.call()
        bird.call()
        (dog as Dog).run()
        (bird as Bird).fly()
    }
}