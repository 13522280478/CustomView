package com.pl.demo0624.obs.bean;

import com.pl.demo0624.Util;
import com.pl.demo0624.obs.listener.Listener;

/**
 * @author YueShuai
 * @date 2020/7/1
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class LiveBean implements Listener {

    @Override
    public void success() {
        Util.p("LiveBean success");
    }

    @Override
    public void fail() {

    }

    @Override
    public void next() {

    }
}
