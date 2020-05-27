package com.example.core.utils

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import com.example.core.BaseApplication


@JvmOverloads
fun toast(string: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(BaseApplication.currentApplication(), string, duration).show()
}

fun Float.dp2px(): Float {
    val displayMetrics = Resources.getSystem().displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this,  displayMetrics
    )
}
