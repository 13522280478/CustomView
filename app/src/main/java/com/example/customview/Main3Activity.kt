package com.example.customview

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.activity_main3.*

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

//        var str = "<h1>Header</h1><h2>Subheader</h2><p>Some <em>text</em></p><img src='http://blogs.babble.com/famecrawler/files/2010/11/mickey_mouse-1097.jpg' width=70 height=100 />"

        var str = "div id='content' style='width: 130px; height: 39px'>" +
                "<div id='state' style='width: 70px; height: 20px'>" +
                "<em style='font-size: 14px;line-height: 20px; color: #F1A487; font-style: normal;'>今日未完成</em></div>" +
                "<div id='desc' style='margin-top: 2px; width: 80px; height: 17px; float: left;'>" +
                "<em style='font-size: 12px; line-height: 17px; color: #878787; font-style: normal;'>已打卡30天&nbsp;></em></div></div>"

//        var str1= "<div id='content' style='width: 130px; height: 39px'><div id='state' style='width: 56px; height: 20px'><em style='font-size: 14px;line-height: 20px; color: #F1A487; font-style: normal;'>打卡中断</em></div><div id='desc' style='margin-top: 2px; width: 120px; height: 17px; float: left;'><em style='font-size: 12px; line-height: 17px; color: #878787; font-style: normal;'>进入详情可删除该活动</em></div><div id='desc' style='width: 5px; height: 17px; float: right; margin: 1.1px 0  3px 0'><em style='font-size: 12px; line-height: 17px; color: #878787; font-style: normal;'>></em></div></div>"

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            tvTest.text = Html.fromHtml(str1, Html.FROM_HTML_MODE_COMPACT)
//
//        } else {
//            tvTest.text = Html.fromHtml("<h2>Title</h2><br><p>Description here</p>")
//        }


        llActivityStatus.removeAllViews()
        var webView = WebView(this)
        webView.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        webView.loadDataWithBaseURL("",str , "text/html", "utf-8", null)
        llActivityStatus.addView(webView)

        val newConstraintSet = ConstraintSet()
        newConstraintSet.clone(rootView)
        newConstraintSet.setVisibility(R.id.llActivityStatus, View.VISIBLE)

        TransitionManager.beginDelayedTransition(rootView)
        newConstraintSet.applyTo(rootView)


    }
}
