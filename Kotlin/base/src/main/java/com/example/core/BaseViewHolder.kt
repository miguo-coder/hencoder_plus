package com.example.core

import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var viewHashMap = hashMapOf<Int, View>()

    protected open fun <T : View> getView(@IdRes id: Int): T {
        var view = viewHashMap[id] ?: itemView.findViewById(id)
        viewHashMap[id] = view
        return view as T
    }

    protected open fun setText(@IdRes id: Int, text: String?): Unit? {
        return getView<TextView>(id).setText(text)
    }


}