package com.aliumujib.tabbarseed.utils.binding


import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.aliumujib.tabbarseed.ui.adapter.base.BindableAdapter
import com.aliumujib.tabbarseed.ui.adapter.base.BindableItemClickListener
import com.aliumujib.tabbarseed.ui.adapter.base.DataBindingAdapter

@set:BindingAdapter("visible")
var View.visible
    get() = visibility == View.VISIBLE
    set(visible) {
        visibility = if (visible) View.VISIBLE else View.GONE
    }

@set:BindingAdapter("invisible")
var View.invisible
    get() = visibility == View.INVISIBLE
    set(invisible) {
        visibility = if (invisible) View.INVISIBLE else View.VISIBLE
    }

@set:BindingAdapter("gone")
var View.gone
    get() = visibility == View.GONE
    set(gone) {
        visibility = if (gone) View.GONE else View.VISIBLE
    }

@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data: T?) {
    if (recyclerView.adapter is BindableAdapter<*> && data!=null) {
        (recyclerView.adapter as BindableAdapter<T>).setData(data)
    }
}

@BindingAdapter("data")
fun <T> setViewPagerProperties(viewPager: ViewPager, data: T?) {
    if (viewPager.adapter is BindableAdapter<*> && data!=null) {
        (viewPager.adapter as BindableAdapter<T>).setData(data)
    }
}

@BindingAdapter("clickListener")
fun <T> setRecyclerViewClickListener(recyclerView: RecyclerView, clickListener: BindableItemClickListener<T>) {
    if (recyclerView.adapter is DataBindingAdapter<*>) {
        (recyclerView.adapter as DataBindingAdapter<T>).itemClickListener = clickListener
    }
}

@BindingAdapter("verticalList")
fun setRecyclerViewOrientation(recyclerView: RecyclerView, verticalList: Boolean) {
    if (verticalList) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, RecyclerView.VERTICAL, false)
    } else {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, RecyclerView.HORIZONTAL, false)
    }
}

