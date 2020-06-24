package com.pltest.demo0227

import android.util.Log

/**
 * @author YueShuai
 * @date 2020/6/24
 * @Describe
 *
 *
 * Email : yueshuai@pupupula.com
 */
class Dog : Animal() {
    override fun call() {
        Log.w("tagg", " 狗叫  汪汪汪")
    }

    fun run() {
        Log.w("tagg", " 狗   跑跑跑")
    }
}