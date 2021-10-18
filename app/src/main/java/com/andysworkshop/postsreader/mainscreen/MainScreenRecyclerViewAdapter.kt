package com.andysworkshop.postsreader.mainscreen

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.andysworkshop.postsreader.databinding.FragmentMainScreenBinding

import com.andysworkshop.postsreader.mainscreen.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MainScreenRecyclerViewAdapter(
    private val values: List<PostListData>
) : RecyclerView.Adapter<MainScreenRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentMainScreenBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.userName.text = item.userName
        holder.postTitle.text = item.title
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentMainScreenBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val userName: TextView = binding.userName
        val postTitle: TextView = binding.postTitle
    }

}