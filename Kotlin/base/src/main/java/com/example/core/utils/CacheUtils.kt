package com.example.core.utils

import android.content.Context
import com.example.core.BaseApplication

object CacheUtils {
    @JvmStatic
    val context = BaseApplication.currentApplication()

    @JvmStatic
    val SP = context.getSharedPreferences("Hencoder", Context.MODE_PRIVATE)

    @JvmStatic
    fun save(key: String, value: String) {
        SP.edit().putString(key, value).apply()
    }

    @JvmStatic
    fun get(key: String): String? = SP.getString(key, null)
}