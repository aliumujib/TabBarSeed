package com.aliumujib.tabbarseed.ui.adapter.base

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView


class DataBindingViewHolder<T>(private val binding: ViewDataBinding, var itemClickListener: BindableItemClickListener<T>) :
        RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T) {
        binding.setVariable(BR.item, item)
        binding.setVariable(BR.clickListener, itemClickListener)
        binding.executePendingBindings()
    }
}