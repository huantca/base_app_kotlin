package com.example.base.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseHolder<T : ViewDataBinding>(binding: T) : RecyclerView.ViewHolder(binding.root) {
    open fun onBind(item: Any, position: Int, category: String?) {
    }
}