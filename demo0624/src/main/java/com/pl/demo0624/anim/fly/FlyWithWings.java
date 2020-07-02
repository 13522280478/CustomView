package com.pl.demo0624.anim.fly;

import android.util.Log;

import com.pl.demo0624.anim.Iinterface.FlyBehavior;
import com.pl.demo0624.anim.base.BaseAnim;

/**
 * @author YueShuai
 * @date 2020/7/1
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class FlyWithWings implements FlyBehavior {

    // 实现鸭子的飞行
    @Override
    public void fly() {
        Log.w("tagg","鸭子 起飞");
    }
}
