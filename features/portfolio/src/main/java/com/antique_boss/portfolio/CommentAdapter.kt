package com.antique_boss.portfolio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.antique_boss.portfolio.databinding.ListItemCommentBinding

class CommentAdapter(
    private var comments: List<String>,
    private val onPopupMenuClickListener: (View, Int) -> Unit
) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

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
        val binding = DataBindingUtil.inflate<ListItemCommentBinding>(LayoutInflater.from(parent.context), R.layout.list_item_comment, parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = comments.size

    fun excludeComment(position: Int) {
        comments = comments.toMutableList().apply {
            removeAt(position)
        }.toList()

        notifyItemRemoved(position)
        notifyItemRangeRemoved(position, comments.size - position )
    }
}