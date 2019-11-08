package com.example.util;

import android.app.Application;

/**
 * @author YueShuai
 * @date 2019-11-06
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class BaseApp extends Application {
    private static BaseApp INSTANCE;

    public static BaseApp getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
