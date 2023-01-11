package com.antique_boss.portfolio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.antique_boss.portfolio.databinding.ListItemCommentBinding

class CommentAdapter(
    private val comments: List<String>
) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    inner class CommentViewHolder(private val binding: ListItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = DataBindingUtil.inflate<ListItemCommentBinding>(LayoutInflater.from(parent.context), R.layout.list_item_comment, parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = comments.size
}