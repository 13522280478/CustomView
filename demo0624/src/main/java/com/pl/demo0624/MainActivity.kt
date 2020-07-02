package com.pl.demo0624

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var str = "我";

//        val str = "一"
//        val length = str.toByteArray().size
//        Log.w("tagg", length.toString() + "")
//        val list: List<Int> = ArrayList<Int>()

        var list: ArrayList<Int> = ArrayList()
        list.add(5)
        list.add(3)
        list.add(6)
        list.add(7)
        list.add(4)
        list.add(8)
        list.add(9)
        list.add(2)
        list.add(1)

//         var list = mutableListOf<Int>(5,3,6,7,4,8,9,6,2,1)
//        bubbleSort(list)
//        printList(list)
//        selectSort(list)
//        printList(list)

        printList(list)
        quickSort(list, 0, list.size-1)
        printList(list)

    }

    fun quickSort(arr: ArrayList<Int>, low: Int, high: Int) {
        if (low < high) {
            val part = partition(arr, low, high)
            quickSort(arr, low, part - 1)
            quickSort(arr, part + 1, high)
        }
    }

    private fun partition(arr: ArrayList<Int>, low: Int, high: Int): Int {
        var low = low
        var high = high
        val pivot = arr[low]
        while (low < high) {
            while (low < high && arr[high] >= pivot) {
                high--
            }
            arr[low] = arr[high]
            while (low < high && arr[low] <= pivot) {
                low++
            }
            arr[high] = arr[low]
        }
        arr[low] = pivot
        return low
    }

    fun bubbleSort(list: ArrayList<Int>) {
        for (i in list.indices) {
            for (j in 0 until list.size - i - 1) {
                if (list[j] < list[j + 1]) {
                    var temp = list[j]
                    list[j] = list[j + 1]
                    list[j + 1] = temp
                }
            }
        }

    }


    fun selectSort(list: ArrayList<Int>) {
        for (i in list.indices) {
            for (j in i until list.size) {
                if (list[i] > list[j]) {
                    var temp = list[i]
                    list[i] = list[j]
                    list[j] = temp
                }
            }
        }
    }

    fun printList(list: List<Int>) {
        for (i in list.indices) {
            Log.w("tagg", "" + i + " : " + list[i])
        }
        Log.w("tagg", "-------------------------")
    }
}
