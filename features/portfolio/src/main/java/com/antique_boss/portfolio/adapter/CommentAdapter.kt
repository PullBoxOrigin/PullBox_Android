package com.antique_boss.portfolio.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.antique_boss.portfolio.R
import com.antique_boss.portfolio.databinding.ListItemCommentBinding

class CommentAdapter(
    private val onPopupMenuClickListener: (View, Int) -> Unit
) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    private lateinit var comments: MutableList<String>

    inner class CommentViewHolder(private val binding: ListItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.commentMainTextView.text = comments[position]

            binding.popupButton.visibility = View.VISIBLE
            binding.popupButton.setOnClickListener {
                onPopupMenuClickListener(it, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = DataBindingUtil.inflate<ListItemCommentBinding>(LayoutInflater.from(parent.context),
            R.layout.list_item_comment, parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = comments.size

    fun submitList(comments: MutableList<String>) {
        this.comments = comments
        notifyDataSetChanged()
    }

    fun notifyInserted() {
        notifyItemInserted(0)
    }

    fun excludeComment(position: Int) {
        comments.removeAt(position)

        notifyItemRemoved(position)
        notifyItemRangeRemoved(position, comments.size - position)
    }
}