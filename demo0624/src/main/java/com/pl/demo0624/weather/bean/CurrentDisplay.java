package com.pl.demo0624.weather.bean;

import com.pl.demo0624.Util;
import com.pl.demo0624.weather.Iinter.DisplayElement;
import com.pl.demo0624.weather.Iinter.Observer;
import com.pl.demo0624.weather.Iinter.Subject;

/**
 * @author YueShuai
 * @date 2020/7/2
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class CurrentDisplay implements Observer, DisplayElement {

    private String name;
    private int age;
    private Subject subject;

    public CurrentDisplay(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(String name, int age) {
        this.name = name;
        this.age = age;
        display();
    }

    @Override
    public void display() {
        Util.p("name : " + name + "  age : " + age);
    }
}
