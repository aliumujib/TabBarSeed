package com.aliumujib.tabbarseed.ui.adapter.base


import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aliumujib.tabbarseed.utils.extensions.inflater
import kotlin.properties.Delegates.observable

class SingleLayoutAdapter<T>(@LayoutRes private val resId: Int) :
        RecyclerView.Adapter<SingleLayoutAdapter.ViewHolder<T>>(), BindableAdapter<T> {

     override fun setData(data: T) {
         if (data is List<*>) {
             items = data as List<T>
         }
     }

     fun setData(data: List<T>) {
         items = data
    }

    var items by observable<List<T>>(listOf()) { _, oldValue, newValue ->
        DiffUtil
                .calculateDiff(DiffUtilCallback(oldValue, newValue))
                .dispatchUpdatesTo(this)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) = holder.bind(items[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder<T>(parent.inflate())

    private fun ViewGroup.inflate() = DataBindingUtil.inflate<ViewDataBinding>(inflater, resId, this, false)

    class ViewHolder<in T>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T) = with(binding) {
            setVariable(BR.item, item)
            executePendingBindings()
        }
    }

}
