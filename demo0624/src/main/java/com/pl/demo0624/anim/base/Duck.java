package com.pl.demo0624.anim.base;

import com.pl.demo0624.anim.Iinterface.FlyBehavior;
import com.pl.demo0624.anim.Iinterface.QuackBehavior;

/**
 * @author YueShuai
 * @date 2020/7/1
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class Duck {
    private FlyBehavior flyBehavior;
    private QuackBehavior quackBehavior;

    protected void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    protected void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public void  performFly(){
        if (flyBehavior!=null){
            flyBehavior.fly();
        }
    }

    public void performQuack(){
       if (quackBehavior!=null){
           quackBehavior.quack();
       }
    }
}
