package com.example.myapplication23.util.provider

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

/**
 * @author Doyeop Kim
 * @since 2022/01/10
 * @throws
 * @description
 */
class DefaultResourcesProvider(
    private val context: Context
) : ResourcesProvider {

    override fun getString(resId: Int): String = context.getString(resId)

    override fun getString(resId: Int, vararg formArgs: Any): String = context.getString(resId, *formArgs)

    override fun getColor(resId: Int): Int = ContextCompat.getColor(context, resId)

    override fun getColorStateList(resId: Int): ColorStateList = getColorStateList(resId)

    override fun getDrawable(resId: Int): Drawable? = context.getDrawable(resId)
}