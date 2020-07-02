package com.pl.demo0624.weather.bean;

import com.pl.demo0624.weather.Iinter.Observer;
import com.pl.demo0624.weather.Iinter.Subject;

import java.util.ArrayList;

/**
 * @author YueShuai
 * @date 2020/7/2
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class StudentData implements Subject {
    private ArrayList<Observer> observers;
    private String name;
    private int age;

    public StudentData() {
          observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if (observers.contains(o)) {
            observers.remove(o);
        }
    }

    @Override
    public void notifyObserver(){
        for (int i = 0; i <observers.size() ; i++) {
             observers.get(i).update(name,age);
        }
    }

     public void changed(){
        notifyObserver();
     }

     public void joinStudent(String name,int age){
         this.name = name;
         this.age = age;
         notifyObserver();
     }

}
