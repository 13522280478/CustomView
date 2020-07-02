package com.pl.demo0624.obs.bean;

import com.pl.demo0624.obs.listener.Listener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YueShuai
 * @date 2020/7/1
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class ObsBean {
   private static List<Listener> mListener = new ArrayList<>();

    public static void subscribe(Listener listener){
        mListener.add(listener);
    }

    public static void onSubscribe(Listener listener){
        if (mListener.contains(listener)){
            mListener.remove(listener);
        }
    }

    public static void next(){
        for (int i = 0; i <mListener.size() ; i++) {
            mListener.get(i).next();
        }
    }

    public static void success(){
        for (int i = 0; i <mListener.size() ; i++) {
            mListener.get(i).success();
        }
    }

    public static void fail(){
        for (int i = 0; i <mListener.size() ; i++) {
            mListener.get(i).fail();
        }
    }
}
