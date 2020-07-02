package com.pl.demo0624.obs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pl.demo0624.R;
import com.pl.demo0624.Util;
import com.pl.demo0624.obs.bean.LiveBean;
import com.pl.demo0624.obs.bean.LiveBean1;
import com.pl.demo0624.obs.bean.ObsBean;

public class ObsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obs);

        ObsBean.subscribe(new LiveBean());
        ObsBean.subscribe(new LiveBean1());
        ObsBean.success();
    }
}
