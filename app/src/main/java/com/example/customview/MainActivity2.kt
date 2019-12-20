package com.example.customview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    var str =
        "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in 我" +
                " a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard 你" +
                "McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the 和" +
                "more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the 我" +
                "cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum 和" +
                " comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes 我" +
                " of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics,和" +
                " very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit 你" +
                "amet..\", comes from a line in section 1.10.32."

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list = mutableListOf<String>()
        list.add("我")
        list.add("和")
        list.add("你")
        Log.w("tagg", "开始")
        val findString = findString(str, list)
        findString.forEach{
            Log.w("tagg", "结束   :  " +it)
        }

    }

    fun findString(string: String, list: List<String>): List<String> {
        var str = string
        val resList = mutableListOf<String>()

        if (str.isNullOrEmpty() || list.isNullOrEmpty()) return resList

        var isFirst = true
        var index = 0

        var isContains = true
        var minIndex = Int.MAX_VALUE

        var stringChat = ""
        while (isContains) {
            isContains = false

            list.forEach {
                if (str.contains(it)) {
                    isContains = true
                    val indexOf = str.indexOf(it)
                    if (indexOf < minIndex) {
                        stringChat = it
                        minIndex = indexOf
                    }
                }
            }

            if (isContains) {
                if (isFirst) {
                    index = minIndex
                    isFirst = !isFirst
                    str = str.substring(index+stringChat.length)
                } else {
                    index = minIndex
                    resList.add(stringChat+str.substring(0, index))
                    str = str.substring(index+stringChat.length)
                }
            }
            minIndex = Int.MAX_VALUE
        }
        if (index != 0) {
            resList.add(stringChat +str)
        }

        return resList

    }

}
