package com.example.viewprocessstudy

import android.content.res.Resources

/**
 * @author YueShuai
 * @date 2019-11-05
 * @Describe
 *
 * <p>
 * Email : yueshuai@pupupula.com
 */

fun Int.dp2px(): Int {
    val scale = Resources.getSystem().displayMetrics.density
    return (this * scale + 0.5f).toInt()
}

fun Float.dp2px():Int{
    val scale = Resources.getSystem().displayMetrics.density
    return (this * scale + 0.5f).toInt()
}