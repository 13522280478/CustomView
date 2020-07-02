package com.pl.demo0624;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YueShuai
 * @date 2020/6/28
 * @Describe <p>
 * Email : yueshuai@pupupula.com
 */
public class Test {
    public static void test() {
        String str = "我";
        int length = str.getBytes().length;
        Log.w("tagg", length + "");

        List list = new ArrayList<Integer>();
        list.add(1);
//        int i = 1;
//        int j = 3;
//        list.set(i, list.get(j));
    }

    public static void quickSort(Integer [] arr,int low,int high){
        if (low < high){
            int part = partition(arr,low,high);
            quickSort(arr,low,part-1);
            quickSort(arr,part+1,high);
        }
    }

    private  static int partition(Integer [] arr ,int low ,int high){
        int pivot = arr[low];
        while (low < high){
            while (low< high && arr[high] >= pivot){
                high --;
            }
            arr[low] = arr[high];
            while (low<high && arr[low] <= pivot){
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }
}
