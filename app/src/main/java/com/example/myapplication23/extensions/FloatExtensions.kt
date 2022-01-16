package com.example.myapplication23.extensions

import android.content.res.Resources

/**
 * @author Doyeop Kim
 * @since 2022/01/10
 * @throws
 * @description Float의 기능을 확장시키는 kotlin file
 */

fun Float.fromDpToPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}