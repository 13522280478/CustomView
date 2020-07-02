package com.pl.demo0624.anim.quack;

import android.util.Log;

import com.pl.demo0624.Util;
import com.pl.demo0624.anim.Iinterface.QuackBehavior;

/**
 * @author YueShuai
 * @date 2020/7/1
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        Util.p("橡皮鸭子 吱吱叫");
    }
}
