package com.antique_boss.portfolio.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.antique_boss.portfolio.R
import com.antique_boss.portfolio.databinding.ListItemCommentBinding
import com.bumptech.glide.Glide

class CommentAdapter(
    private val onPopupMenuClickListener: (View, Int) -> Unit
) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    private lateinit var comments: MutableList<String>

    inner class CommentViewHolder(private val binding: ListItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            Glide.with(binding.profileImageView.context)
                .load("https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2022/10/13/4d8ef85c-ed4f-4f08-843e-19f406616942.jpg")
                .into(binding.profileImageView)

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