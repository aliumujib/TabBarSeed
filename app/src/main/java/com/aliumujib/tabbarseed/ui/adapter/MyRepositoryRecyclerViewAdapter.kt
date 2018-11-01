package com.aliumujib.tabbarseed.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aliumujib.tabbarseed.data.model.Repository
import com.aliumujib.tabbarseed.R

import kotlinx.android.synthetic.main.item_repository.view.*


class MyRepositoryRecyclerViewAdapter(
        private val values: MutableList<Repository>)
    : RecyclerView.Adapter<MyRepositoryRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_repository, parent, false)
        return ViewHolder(view)
    }


    fun addItems(list: List<Repository>) {
        values.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.mIdView.text = "${position + 1}"
        holder.mContentView.text = item.repoName
        holder.mURLRepo.text = item.repoDescription

        with(holder.mView) {
            tag = item
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content
        val mURLRepo: TextView = mView.repo_url

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
