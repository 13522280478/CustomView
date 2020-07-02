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
public class FlyNoWay  implements FlyBehavior {

    // 不会飞的类
    @Override
    public void fly() {
        Log.w("tagg","这个鸭子 不会飞");
    }

}
