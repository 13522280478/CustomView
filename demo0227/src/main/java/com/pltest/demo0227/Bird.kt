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
class Bird : Animal() {
    override fun call() {
        Log.w("tagg", " 鸟叫  吱吱吱")
    }

    fun fly() {
        Log.w("tagg", " 鸟   飞飞飞")
    }
}