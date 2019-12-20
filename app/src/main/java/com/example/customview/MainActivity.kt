package com.example.customview

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var press = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list = mutableListOf<String>()
        list.add("我")
        list.add("和")
        list.add("你")
//        progressText.setDataList(list)

//        btStart.setOnClickListener {
//            startRun()
//        }
    }

    fun findString(str:String,list:List<String>):List<String>{

        val resList = mutableListOf<String>()

        if (str.isNullOrEmpty()||list.isNullOrEmpty()) return resList

        var isFirst = true
        var index = 0

        list.forEach{
            if (str.contains(it)){
                val indexOf = str.indexOf(it)
                if (isFirst){
                    index = indexOf
                }else{
                    resList.add(str.substring(index,indexOf))
                    index = index
                }
            }
        }
        if (index!=0){
            resList.add(str.substring(index,str.length))
        }

        return resList

    }


//    fun startRun() {
//        val random = Random()
//        press = 0
//        Thread {
//            kotlin.run {
//                while (press < 100) {
//                    press += random.nextInt(10)
//                    if (press >= 100) {
//                        press = 100
//                    }
//                    handler.sendEmptyMessage(press)
//                    Thread.sleep(500)
//                }
//            }
//        }.start()
//
//    }

//    internal var handler: Handler = object : Handler() {
//
//        override fun handleMessage(msg: Message) {
//            super.handleMessage(msg)
//            var float = msg.what.toFloat()
//            pressVew.mProgress = float
//            updateBar.mProgress = float.toInt()
//
//        }
//    }
}
