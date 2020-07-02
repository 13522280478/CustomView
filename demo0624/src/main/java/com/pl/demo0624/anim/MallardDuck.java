package com.pl.demo0624.anim;

import com.pl.demo0624.anim.base.Duck;
import com.pl.demo0624.anim.fly.FlyWithWings;
import com.pl.demo0624.anim.quack.Quack;

/**
 * @author YueShuai
 * @date 2020/7/1
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class MallardDuck extends Duck {

   public MallardDuck(){
       setFlyBehavior(new FlyWithWings());
       setQuackBehavior(new Quack());
   }
}


